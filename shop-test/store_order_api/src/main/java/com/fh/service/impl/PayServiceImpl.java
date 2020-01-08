package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.beans.Order;
import com.fh.beans.OrderDetail;
import com.fh.beans.PayLog;
import com.fh.dao.DetailDao;
import com.fh.dao.OrderDao;
import com.fh.dao.PayLogDao;
import com.fh.enumbean.LoginCode;
import com.fh.enumbean.LoginEnum;
import com.fh.service.PayService;
import com.fh.utils.BigDecimalUtil;
import com.fh.utils.DateUtil;
import com.fh.utils.HttpclientUtils;
import com.fh.utils.MyWxConfig;
import com.github.wxpay.sdk.WXPay;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayLogDao payLogDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DetailDao detailDao;


    /**
     * 发起一个
     * @param phone
     * @param outTradeNo
     * @return
     */
    @Override
    public LoginCode getTotalPriceByOutTradeNo(String phone, String outTradeNo) {
        PayLog payLog = (PayLog) redisTemplate.opsForHash().get("yqg"+phone,outTradeNo);
        if (payLog==null){
            return LoginCode.error(LoginEnum.PAY_ISNULL);
        }
        MyWxConfig myWxConfig = new MyWxConfig();
        try {
            WXPay pay = new WXPay(myWxConfig);
            Map<String,String> data = new HashMap<String, String>();
            //设置支付标题
            data.put("body","拒绝白嫖");
            //设置订单号
            data.put("out_trade_no",outTradeNo);
            //设置币种
            data.put("fee_type","CNY");
            //设置支付的失效时间
            Date date =DateUtils.addMinutes(new Date(),1);
            data.put("time_expire",DateUtil.getYyyyMMhhmmss(date,DateUtil.yyyyMMhhmmss));
            //设置支付的金额
            int i = BigDecimalUtil.mul(payLog.getPayMoney() + "", "100").intValue();
            data.put("total_fee",i+"");
            //设置接口的调用路径
            data.put("notify_url","http://www.example.com/wxpay/notify");
            //制定扫码支付
            data.put("trade_type","NATIVE");
            //验证接口是否能够正常调用
            Map<String, String> resultMap = pay.unifiedOrder(data);
            //验证接口是否能够正常访问
            String returnCode = resultMap.get("return_code");
            String returnMsg = resultMap.get("return_msg");
            if (!"SUCCESS".equalsIgnoreCase(returnCode)){
                return LoginCode.error(9999999,returnMsg);
            }
            //验证业务是否成功
            String resultCode = resultMap.get("result_code");
            String errCedeDes = resultMap.get("err_cede_des");
            if (!"SUCCESS".equalsIgnoreCase(resultCode)){
                return LoginCode.error(9999999,errCedeDes);
            }
            String codeUrl = resultMap.get("code_url");
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("codeUrl",codeUrl);
            map.put("outTradeNo",outTradeNo);
            map.put("totalPrice",payLog.getPayMoney());
            return LoginCode.success(map);
        }catch (Exception e){
            return LoginCode.error(LoginEnum.PAY_CREATE_ERR);
        }
    }

    /**
     * 刷新支付状态
     * @param phone
     * @param outTradeNo
     * @return
     */
    @Override
    public LoginCode ewmMinutes(String phone, String outTradeNo) {
        PayLog payLog = (PayLog) redisTemplate.opsForHash().get("yqg"+phone,outTradeNo);
        if (payLog==null){
            return LoginCode.error(LoginEnum.PAY_ISNULL);
        }
        MyWxConfig config = new MyWxConfig();
        int count =0;
        while (true) {
            try {
                WXPay wxPay = new WXPay(config);
                Map<String, String> data = new HashMap<String, String>();
                data.put("out_trade_no", outTradeNo);
                Map<String, String> resultMap = wxPay.orderQuery(data);
                //验证接口是否能够正常访问
                String returnCode = resultMap.get("return_code");
                String returnMsg = resultMap.get("return_msg");
                if (!"SUCCESS".equalsIgnoreCase(returnCode)) {
                    return LoginCode.error(9999999, returnMsg);
                }
                //验证业务是否成功
                String resultCode = resultMap.get("result_code");
                String errCedeDes = resultMap.get("err_cede_des");
                if (!"SUCCESS".equalsIgnoreCase(resultCode)) {
                    return LoginCode.error(9999999, errCedeDes);
                }
                String tradeState = resultMap.get("trade_state");
                if ("SUCCESS".equalsIgnoreCase(tradeState)) {
                    //更新支付表状态
                    payLog.setPayStatus(200);
                    //微信支付的订单号
                    String transactionId = resultMap.get("transaction_id");
                    payLog.setTransactionId(transactionId);
                    payLog.setPayTime(new Date());
                    payLogDao.updateById(payLog);

                    //更新订单表的状态
                    Order order = new Order();
                    order.setStatus(200);
                    order.setPayTime(new Date());
                    order.setId(payLog.getOrderId());
                    orderDao.updateById(order);
                    //支付成功！！！
                    redisTemplate.opsForHash().delete("yqg"+phone,outTradeNo);
                    return LoginCode.success(payLog.getPayMoney());
                }
                Thread.sleep(3000l);
                count++;
                if (count>20){
                    return LoginCode.error(LoginEnum.PAY_IS_TIMEOUT);
                }

            } catch (Exception e) {
                return LoginCode.error(LoginEnum.PAY_IS_ERR);
            }

        }


    }

    public void queryPassTimeAndNoPay(){
        List<String> list = payLogDao.queryPassTimeAndNoPay();
        if (list.size()>0){
            String url = "http://localhost:8083/shop/updateStockByPassTimer/";
            List<OrderDetail> orderDetails = payLogDao.queryShopByPassTimeOrderId(list);
            for (OrderDetail detail:orderDetails){
                Integer shopId = detail.getShopId();
                Integer stock = detail.getCount();
                HttpclientUtils.doGet(url+shopId+"/"+stock);

            }
            for (String orderId:list){
                QueryWrapper<OrderDetail> detailQueryWrapper = new QueryWrapper<OrderDetail>();
                detailQueryWrapper.eq("orderId",orderId);
                detailDao.delete(detailQueryWrapper);

                orderDao.deleteById(orderId);

                QueryWrapper<PayLog> wrapper = new QueryWrapper<PayLog>();
                wrapper.eq("orderId",orderId);
                payLogDao.delete(wrapper);
            }

        }

    }














}
