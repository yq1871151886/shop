package com.fh.controller;



import com.fh.beans.User;
import com.fh.enumbean.LoginCode;
import com.fh.enumbean.LoginEnum;
import com.fh.jwt.JwtUtils;
import com.fh.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("users")
public class UserController {
        @Autowired
        private UserService userService;

        @Autowired
        private RedisTemplate redisTemplate;

    @GetMapping("/{phoneNum}")
    @CrossOrigin(maxAge = 3000,origins = "http://localhost:8080")
    public LoginCode phoneCode(@PathVariable String phoneNum) throws IOException {
        String s = "123456";
        redisTemplate.opsForValue().set("user"+phoneNum,s,60*30,TimeUnit.SECONDS);

        /*Map<String,Object> map = new HashMap<String, Object>();
        map.put("userPhone",phoneNum);
        map.put("userId",users.get(0).getId());
        String token = JwtUtils.createToken(map);
        userService.addUserToKen(token);
        HttpServletResponse response = UtilsTools.getResponse();

        response.setHeader("Access-Control-Expose-Headers","Cache-Control,Content-Type,Expires,Pragma,Content-Language,Last-Modified,token");
        response.setHeader("login",token);*/
        return LoginCode.success(LoginEnum.LOGIN_SENDCODE_SUCCESS);
    }

    @PostMapping
    @CrossOrigin(maxAge = 3000,origins = "http://localhost:8080")
    public LoginCode phoneLogin(String phoneNum,String code) throws IOException {

        if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(code)){
            return LoginCode.error(LoginEnum.LOGIN_PHONEORCODE_ISNULL);
        }
        String o = (String) redisTemplate.opsForValue().get("user"+phoneNum);
        if (!code.equals(o)){
            return LoginCode.error(LoginEnum.LOGIN_PHONECODE_ERROR);
        }
        redisTemplate.delete("user"+phoneNum);

        User users = userService.queryUserByPhone(phoneNum);
        redisTemplate.opsForValue().set(phoneNum,users.getCardid());
        redisTemplate.opsForValue().set("user_"+phoneNum,users);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userPhone",phoneNum);
        map.put(phoneNum,users.getCardid());
            map.put("user_"+phoneNum,users);
        String token = JwtUtils.createToken(map);


        userService.addUserToKen(phoneNum,token);


        return LoginCode.success(token);


        /*if (users.size()<1){
            return LoginCode.error(LoginEnum.LOGIN_PHONE_NULL);
        }
        boolean b = SendCode.verifyCode(phoneNum, code);
        if (!b){
            return LoginCode.error(LoginEnum.LOGIN_PHONECODE_ERROR);
        }else {
            return LoginCode.success();
        }*/

    }








}
