package com.fh.utils;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UtilsTools {



    public static HttpServletRequest getRequest(){
        HttpServletRequest request=((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
            return request;
    }

    public static HttpServletResponse getResponse(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }


    public static String getSnowId(){
        return IdWorker.getIdStr();
    }



}
