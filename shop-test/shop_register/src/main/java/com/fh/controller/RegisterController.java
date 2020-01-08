package com.fh.controller;

import com.fh.beans.User;
import com.fh.beans.po.UserPo;
import com.fh.beans.vo.UserVo;
import com.fh.enumbean.LoginCode;
import com.fh.service.RegisterService;
import com.fh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    public PageBean queryUserPageList(PageBean pageBean,UserPo user){
        PageBean<UserVo> userVoPageBean = registerService.queryUserPageList(pageBean, user);
        return userVoPageBean;
    }

    @PutMapping
    public LoginCode addUser(@RequestBody User user){
        LoginCode loginCode = registerService.addUser(user);
        return loginCode;
    }

    @PostMapping
    public LoginCode updateUser(@RequestBody User user){
        LoginCode loginCode = registerService.UpdateUser(user);
        return loginCode;
    }

    @DeleteMapping
    public LoginCode deleteUserById(String id){
        LoginCode loginCode = registerService.deleteUserById(id);
        return loginCode;
    }

}
