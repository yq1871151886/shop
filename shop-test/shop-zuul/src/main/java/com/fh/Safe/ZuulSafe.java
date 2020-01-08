package com.fh.Safe;

import com.alibaba.fastjson.JSONObject;
import com.fh.utils.MD5Util;
import com.fh.utils.enumbean.ZuulEnum;
import com.fh.utils.enumbean.ZuulResponse;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
@Component
public class ZuulSafe extends ZuulFilter {

    @Value("${appKey}")
    private String oldAppKey;
    @Value("${secRet}")
    private String oldSecRet;

    @Autowired
    private RedisTemplate redisTemplate;


    private final RateLimiter rateLimiter = RateLimiter.create(100.0);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取request
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        /**
         * 限流
         */
            if (!rateLimiter.tryAcquire()){
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                currentContext.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                try {
                    response.getWriter().write("TOO MANY REQUESTS");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                currentContext.setResponseStatusCode(200);
            }





        //第一次请求,经过内部负责分发的请求,应该是options方法,所以第一次请求肯定不是
        if (request.getMethod().equals("OPTIONS")){
            return null;
        }
        //获取前台传过来的信息
        String appKey = request.getHeader("appKey");
        String checkSumCode = request.getHeader("checkSumCode");
        String randoms = request.getHeader("randoms");
        String dataTime = request.getHeader("dataTime");
        //判断前台传过来的值是否为空，其中一个有空值则返回
        if (StringUtils.isBlank(appKey) || StringUtils.isBlank(checkSumCode) || StringUtils.isBlank(randoms) || StringUtils.isBlank(dataTime)){
            return ZuulResponse.error(ZuulEnum.ZUUL_ATTACK);
        }
        //判断sppKey是否存在
        if (!oldAppKey.equals(appKey)){
            return ZuulResponse.error(ZuulEnum.ZUUL_ATTACK);
        }
        //将前台时间戳转换为long类型，如果不能转化则认为前台传的值中含有字符串导致类型转换错误抛出异常，将该方法过滤掉
        try {
            Long oldTime = Long.valueOf(dataTime);
            Long newTime = System.currentTimeMillis();
            Long time = newTime-oldTime;
            if (time>600000){
                return ZuulResponse.error(ZuulEnum.ZUUL_TIMEOUT);
            }
        }catch (Exception e){
            bulidErrorResult(currentContext,ZuulResponse.error(ZuulEnum.ZUUL_REPLOY_ATTACK));
            return null;
        }
        /**
         * 在redis中存值，如果获取到该值，证明该方法已经处理过。
         * 这个方法初步测试，有问题，因为每次发起请求的时间都是不一样的，所以每次请求i过来的时候redis中都取不到值
         *
         */
        String rands = (String) redisTemplate.opsForValue().get(checkSumCode);
        if (StringUtils.isBlank(rands)){
            redisTemplate.opsForValue().set(checkSumCode,randoms,90L,TimeUnit.SECONDS);
        }else {
            bulidErrorResult(currentContext,ZuulResponse.error(ZuulEnum.ZUUL_REPLOY_ATTACK));
            return null;
        }
        /**
         * 在后台将字符串以相同的方法进行拼接，并且
         * 将前台加密之后的签名
         *
         */
        String md5 = MD5Util.getMD5(oldSecRet);
        String md51 = MD5Util.getMD5(appKey+md5 + randoms + dataTime);
        if (!md51.equals(checkSumCode)){
            bulidErrorResult(currentContext,ZuulResponse.error(ZuulEnum.ZUUL_REPLOY_ATTACK));
            return null;
        }

        return null;
    }
    private void bulidErrorResult(RequestContext requestContext, ZuulResponse error){
        //不在进行路由转发
        requestContext.setSendZuulResponse(false);
        // 设置编码格式和响应类型
        requestContext.getResponse().setContentType("application/json;charset=utf-8");
        requestContext.setResponseBody(JSONObject.toJSONString(error));
    }
}
