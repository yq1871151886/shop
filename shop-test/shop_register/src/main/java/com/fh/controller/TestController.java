package com.fh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("feign")
public class TestController {
    @GetMapping("/{name}")
    public String feignTest(@PathVariable(value = "name") String name){
        System.out.println(name);
        return name;
    }

}
