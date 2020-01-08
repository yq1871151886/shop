package com.fh.authenticateException;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(AuthenticateException.class)
    public void authenticateException(AuthenticateException e, HttpServletRequest request, HttpServletResponse response){
                response.setHeader("NOLOGIN",e.getCode().toString());
    }
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e,HttpServletRequest request, HttpServletResponse response){
        response.setHeader("EXCEPTION",e.getMessage());

    }


}
