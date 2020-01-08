package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.beans.User;
import com.fh.dao.UserDao;
import com.fh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDao userDao;


    public User queryUserByName(String loginName){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("loginName",loginName);
        User user = userDao.selectOne(wrapper);
        return user;
    }
    public void addUser(User user){
        userDao.insert(user);
    }



    public User queryById(String id){
        User user = userDao.selectById(id);
        return user;
    }


    public User queryUserByPhone(String phoneNum){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("phone",phoneNum);
        User user = userDao.selectOne(wrapper);
        if (user==null){
            user.setPhone(phoneNum);
            user.setCardid(UUID.randomUUID().toString());
            userDao.insert(user);
        }
        return user;

    }

    @Override
    public void addUserToKen(String phoneNum,String token) {
            redisTemplate.opsForValue().set("user"+phoneNum,token,60*60,TimeUnit.SECONDS);
    }


}
