package com.fh.authenticateException;


import com.fh.enumbean.LoginEnum;

public class AuthenticateException extends RuntimeException{



    private Integer code;
    public AuthenticateException(LoginEnum loginEnum) {
        super(loginEnum.getMessage());
        this.code=loginEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
