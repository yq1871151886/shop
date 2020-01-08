package com.fh.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "shop-register")
public interface TestFeign {

    @GetMapping("/feign/{name}")
    String feignTest(@PathVariable(value = "name") String name);
}
