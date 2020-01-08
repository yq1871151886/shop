package com.fh.controller;

import com.fh.enumbean.LoginCode;
import com.fh.loginAop.LoginAnnotation;
import com.fh.service.CartService;
import com.fh.utils.UtilsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("carts/cartJump")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders = "NOLOGIN")
public class JumpCart {

    @Autowired
    private CartService cartService;




    @LoginAnnotation
    @GetMapping
    public LoginCode toCart(){
        return LoginCode.success();
    }

    @LoginAnnotation
    @GetMapping("/changeCheck/{shopId}")
    public LoginCode changgeCheck(@PathVariable Integer shopId){
        HttpServletRequest request = UtilsTools.getRequest();
        String phone = (String) request.getAttribute("phone");
        LoginCode loginCode = cartService.changeCheck(shopId,phone);
        return loginCode;
    }

    @LoginAnnotation
    @GetMapping("/changeCount/{shopId}/{type}/{oldCount}")
    public LoginCode changeCount(@PathVariable Integer shopId,@PathVariable String type,@PathVariable Integer oldCount){
        HttpServletRequest request = UtilsTools.getRequest();
        String phone = (String) request.getAttribute("phone");
        LoginCode loginCode  = cartService.changeCount(shopId,type,oldCount,phone);
        return loginCode;
    }
    @LoginAnnotation
    @GetMapping("/blurChangeCount/{shopId}/{count}")
    public LoginCode blurChangeCount(@PathVariable Integer shopId,@PathVariable Integer count){
        HttpServletRequest request = UtilsTools.getRequest();
        String phone = (String) request.getAttribute("phone");
        LoginCode loginCode = cartService.blurChangeCount(shopId,count,phone);
        return loginCode;
    }
    @LoginAnnotation
    @GetMapping("/checkAll/{check}")
    public LoginCode checkAll(@PathVariable String check){
        HttpServletRequest request = UtilsTools.getRequest();
        String phone = (String) request.getAttribute("phone");
        LoginCode loginCode =  cartService.checkAll(phone,check);
        return loginCode;
    }
    @LoginAnnotation
    @GetMapping("/deleteShops/{shopId}")
    public LoginCode deleteShops(@PathVariable Integer shopId){
        HttpServletRequest request = UtilsTools.getRequest();
        String phone = (String) request.getAttribute("phone");
        LoginCode loginCode =  cartService.deleteShops(shopId,phone);
        return loginCode;
    }

}
