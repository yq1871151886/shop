package com.fh.utils.enumbean;

public enum ZuulEnum {
    ZUUL_SUCCESS(7000,"成功")
    ,ZUUL_ATTACK(7001,"非法请求")
    ,ZUUL_REPLOY_ATTACK(7002,"重放攻击")
    ,ZUUL_TIMEOUT(7003,"超时")
    ;


    private Integer code;
    private String message;

    private ZuulEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
