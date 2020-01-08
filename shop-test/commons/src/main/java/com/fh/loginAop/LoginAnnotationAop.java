package com.fh.loginAop;

import com.fh.authenticateException.AuthenticateException;
import com.fh.enumbean.LoginCode;
import com.fh.enumbean.LoginEnum;
import com.fh.jwt.JwtUtils;
import com.fh.utils.UtilsTools;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(7)
public class LoginAnnotationAop {

    @Around(value = "execution(* com.fh.controller.*.*(..))&&@annotation(loginAnnotation)")
    public Object LoginPoint(ProceedingJoinPoint joinPoint,LoginAnnotation loginAnnotation){

        HttpServletRequest request = UtilsTools.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token) || token==""){
            throw  new AuthenticateException(LoginEnum.LOGIN_TOKEN_INVALID);
        }



        LoginCode loginCode = JwtUtils.resolverToken(token);

        if (loginCode.getCode()!=200){
            throw  new AuthenticateException(LoginEnum.LOGIN_TOKEN_INVALID);
        }
        Claims  claims = (Claims) loginCode.getData();
        String phone = (String) claims.get("userPhone");
        request.setAttribute("phone",phone);
        Object object = null;

        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return object;
    }








}
