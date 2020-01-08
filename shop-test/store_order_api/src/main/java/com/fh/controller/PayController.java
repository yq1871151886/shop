package com.fh.controller;

import com.fh.enumbean.LoginCode;
import com.fh.loginAop.LoginAnnotation;
import com.fh.service.PayService;
import com.fh.utils.UtilsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("orders/pay")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders = "NOLOGIN")
public class PayController {

    @Autowired
    private PayService payService;

    @GetMapping("/getTotalPriceByOutTradeNo/{outTradeNo}")
    @LoginAnnotation
    public LoginCode getTotalPriceByOutTradeNo(@PathVariable String outTradeNo){
        HttpServletRequest request = UtilsTools.getSession();
        String phone = (String) request.getAttribute("phone");
        return payService.getTotalPriceByOutTradeNo(phone,outTradeNo);
    }

    @PostMapping
    @LoginAnnotation
    public LoginCode ewnMinutes(String outTradeNo){
        HttpServletRequest request = UtilsTools.getSession();
        String phone = (String) request.getAttribute("phone");
        return payService.ewmMinutes(phone,outTradeNo);
    }


}
