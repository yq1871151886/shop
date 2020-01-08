package com.fh.controller;

import com.fh.enumbean.LoginCode;
import com.fh.loginAop.LoginAnnotation;
import com.fh.service.OrderService;
import com.fh.utils.UtilsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("orders/order")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders = "NOLOGIN")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @GetMapping("/commitOrder/{addressId}")
    @LoginAnnotation
    public LoginCode commitOrder(@PathVariable Integer addressId){
        HttpServletRequest request = UtilsTools.getSession();
        String phone = (String) request.getAttribute("phone");
        LoginCode loginCode = orderService.commitOrder(addressId,phone);
        return loginCode;
    }


}
