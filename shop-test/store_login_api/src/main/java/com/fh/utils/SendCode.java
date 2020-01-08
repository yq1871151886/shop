package com.fh.utils;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendCode {

    //发送验证码的请求路径URL
    private static final String SERVER_URL="https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY="7e3f18f3c2cf5c0ccfb6372498688ba1";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="d8ddfdbbfc65";
    //随机数
    private static final String NONCE="123456";
    /* //短信模板ID
     private static final String TEMPLATEID="3057527";*/
    //手机号
   /* private static final String MOBILE="15225134450";*/
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";



    public static String sendCode(String phone) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("mobile", phone));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        HttpEntity user1 = response.getEntity();
        String us = EntityUtils.toString(user1);
        JSONObject obj = new JSONObject(us);
        String obj1 = obj.getString("obj");
        return obj1;
    }
    /**
     * 通知类短信或运营类短信，注意：①、该类短信验证码可以自己生成；②、运营类短信能只提供企业使用
     *
     * @param
     * @param params 发送的消息，可以是自定义验证码
     * @return 是否发送成功
     */
    public static final boolean sendSmg(String mobiles, String params) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/sendtemplate.action");

        String currentTime = String.valueOf(new Date().getTime()/1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET,NONCE,currentTime);

        //set header
        httpPost.setHeader("AppKey",APP_KEY);
        httpPost.setHeader("CurTime",currentTime);
        httpPost.setHeader("Nonce",NONCE);
        httpPost.setHeader("CheckSum",checkSum);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //set data
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("mobiles", mobiles));
        nameValuePairList.add(new BasicNameValuePair("params", params));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "utf-8"));

        //start request
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        String responseResult = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        System.out.println("responseResult:"+responseResult);

        String stateCode = JSON.parseObject(responseResult).getString("code");
        if(stateCode.equals("200")){
            return true;
        }
        return false;
    }
    /**
     * 判断用户输入验证码与网易云生成的验证码是否一致
     *
     * @param mobile 电话号码
     * @param code 发送到mobile上的短信
     */
    public static final boolean verifyCode(String mobile, String code) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/verifycode.action");

        String currentTime = String.valueOf(new Date().getTime()/1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET,NONCE,currentTime);

        //set header
        httpPost.setHeader("AppKey",APP_KEY);
        httpPost.setHeader("CurTime",currentTime);
        httpPost.setHeader("Nonce",NONCE);
        httpPost.setHeader("CheckSum",checkSum);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //set data
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("code",code));
        nameValuePairList.add(new BasicNameValuePair("mobile",mobile));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList,"utf-8"));

        //start request
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        String responseResult = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println("responseResult:"+responseResult);

        String stateCode = JSON.parseObject(responseResult).getString("code");
        if(stateCode.equals("200")){
            return true;
        }
        return false;
    }
}