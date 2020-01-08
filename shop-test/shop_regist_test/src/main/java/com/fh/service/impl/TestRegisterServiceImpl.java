package com.fh.service.impl;

import com.fh.controller.TestFeign;
import com.fh.service.TestRegisterService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestRegisterServiceImpl implements TestRegisterService {

    @Autowired
    private TestFeign feignClient;

    @HystrixCommand(fallbackMethod = "getTest")
    public String feignTest(String name) {
        String s = feignClient.feignTest(name);
        return s;
    }

    public String getTest(String name){
        return "接口关闭";
    }
}
