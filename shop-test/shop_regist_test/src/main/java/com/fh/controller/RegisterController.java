package com.fh.controller;

import com.fh.beans.User;
import com.fh.beans.po.UserPo;
import com.fh.enumbean.LoginCode;
import com.fh.enumbean.LoginEnum;
import com.fh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("gi")
public class RegisterController {

        @Autowired
        private RestTemplate restTemplate;


        @PutMapping
        public LoginCode addUser(User user){
            String url = "http://shop-register/register";
            restTemplate.put(url,user);
            return LoginCode.success(LoginEnum.MAKE_SUCCESS);
        }


        @DeleteMapping
        public LoginCode deleteUserById(String id){
            String url = "http://shop-register/register";
            restTemplate.delete(url+"?id="+id);
            return LoginCode.success(LoginEnum.MAKE_SUCCESS);
        }


        @PostMapping
        public LoginCode updateUserById(User user){
            String url = "http://shop-register/register";
            LoginCode loginCode = restTemplate.postForObject(url, user, LoginCode.class);
            return loginCode;
        }

        @GetMapping
        public PageBean queryUserPageList(PageBean pageBean,UserPo user){
            String url = "http://shop-register/register";
            /*Map<String,Object> map = new HashMap<String, Object>();
            map.put("pageBean",pageBean);
            map.put("user",user);*/
            pageBean = restTemplate.getForObject(url, PageBean.class,pageBean,user);
            //pageBean = restTemplate.postForObject(url, map, PageBean.class);
            return pageBean;
        }




}
