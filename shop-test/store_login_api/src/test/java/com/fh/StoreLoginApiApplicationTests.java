package com.fh;

import com.alibaba.fastjson.JSON;
import com.fh.utils.Layui;
import io.jsonwebtoken.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class StoreLoginApiApplicationTests {
    //=============================111111111111111111111111111111=====================================================
    @Test
    public void contextLoads() {
        /*声明头部信息*/
        Map<String,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg","HS256");
        headerMap.put("user","2132112");

        /*设置负载*/
        Map<String,Object> payload = new HashMap<String, Object>();
        payload.put("userPhone","15225134450");
        payload.put("typ","JWT");
        /*设置失效时间*/
        Long nt = System.currentTimeMillis();
        payload.put("exp",nt+600001);
        payload.put("iat",nt);
        String token = Jwts.builder()
                .setHeader(headerMap)
                .setPayload(JSON.toJSONString(payload))
                .signWith(SignatureAlgorithm.HS256,getSecretKey("yqg"))
                .compact();
        System.out.println(token);
        /*设置签名值*/

    }


    public String getSecretKey(String key){

        return new BASE64Encoder().encode(key.getBytes());


    }


    @Test
    public void resolverTest(){
        String token = "eyJhbGciOiJIUzI1NiIsInVzZXIiOiIyMTMyMTEyIn0.eyJ1c2VyUGhvbmUiOiIxNTIyNTEzNDQ1MCIsInR5cCI6IkpXVCIsImV4cCI6MTU3NDc4MzIwMTU4MSwiaWF0IjoxNTc0NzgyNjAxNTgwfQ.XpK41UcgjqWBcEE1jZwYxi8XZM5W_rIPEIoMWpe51Nk";
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSecretKey("yqg"))
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("success");
        }catch (ExpiredJwtException e){
            System.out.println("超时");
        }catch (SignatureException e){
            System.out.println("失败");
        }
    }
//=============================2222222222222222222222222=====================================================



    @Test
    public void gettoken(){
        Map<String,Object> headerMap= new HashMap<String, Object>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");


        Map<String,Object> payload= new HashMap<String, Object>();
        payload.put("userPhone","15225134450");
        payload.put("userId","27836");

        Long iat = System.currentTimeMillis();

        payload.put("exp",iat+600001);
        payload.put("iat",iat);

        String token = Jwts.builder()
                .setHeader(headerMap)
                .setPayload(JSON.toJSONString(payload))
                .signWith(SignatureAlgorithm.HS256,getser("yqg"))
                .compact();
        System.out.println(token);
    }

    public String getser(String key){
        return new BASE64Encoder().encode(key.getBytes());
    }

    @Test
    public void resolverTest1(){

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUGhvbmUiOiIxNTIyNTEzNDQ1MCIsImV4cCI6MTU3NDc4NTc1OTA0NCwidXNlcklkIjoiMjc4MzYiLCJpYXQiOjE1NzQ3ODUxNTkwNDN9.4CEhwoxUNszA3-gA2zjr-QJ8OmCKoV7ZoPgaGHiHkc0";


        try {
            Claims claims=Jwts.parser()
                    .setSigningKey(getser("yqg"))
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("success");
        }catch (ExpiredJwtException e){
            System.out.println("超时");
        }catch (SignatureException e){
            System.out.println("解析失败");
        }

    }
//========================333333333333333333333333333333=========================
    @Test
    public void contextLoads1(){

        Map<String,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");
        Map<String,Object> poayload = new HashMap<String, Object>();
        poayload.put("userPhone","15225134450");
        poayload.put("userId","231");
        Long iat = System.currentTimeMillis();
        poayload.put("exp",iat+600001);
        poayload.put("iat",iat);


        String token = Jwts.builder()
                .setHeader(headerMap)
                .setPayload(JSON.toJSONString(poayload))
                .signWith(SignatureAlgorithm.HS256,getsecreKey("yqg2"))
                .compact();
        System.out.println(token);


    }

    @Test
    public void resolvertoken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyUGhvbmUiOiIxNTIyNTEzNDQ1MCIsImV4cCI6MTU3NDc4NzM1MTcxOSwidXNlcklkIjoiMjMxIiwiaWF0IjoxNTc0Nzg2NzUxNzE4fQ.7d-cH675jsXnpS0Pu-hFJ_nA2IbIG3fEvw2N8WbO1n8";

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getsecreKey("yqg2"))
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("success");
        }catch (ExpiredJwtException e){
            System.out.println("超时");
        }catch (SignatureException e){
            System.out.println("解析错误");
        }



    }


public String getsecreKey(String key){
        return new BASE64Encoder().encode(key.getBytes());
}



}
