package com.fh.controller;

import com.fh.service.TestRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feign")
public class TestFeignController {

    @Autowired
    private TestRegisterService testService;


    @GetMapping
    public String testFeign(String name){
        String s="";
        /*try {

        }catch (NullPointerException e){
            e.getMessage();
        }*/
        System.out.println(name);
        s = testService.feignTest(name);
        return s;
    }
}
