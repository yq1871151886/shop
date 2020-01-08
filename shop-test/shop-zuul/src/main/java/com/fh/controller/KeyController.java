package com.fh.controller;

import com.fh.utils.MD5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("queryKey")
@CrossOrigin
public class KeyController {

    @Value("${appKey}")
    private String appKey;

    @Value("${secRet}")
    private String secRet;

    @GetMapping
    public Map<String,String> getSafe(){
        Map<String,String> map =  new HashMap<String, String>(2);
        map.put("appKey",appKey);
        map.put("secRet",MD5Util.getMD5(secRet));
        return map;
    }



}
