package com.fh.utils.enumbean;


public class ZuulResponse {

    private Integer code;
    private String message;
    private Object data;


    private ZuulResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ZuulResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ZuulResponse() {
        super();
    }

    public static ZuulResponse error() {
        return new ZuulResponse(ZuulEnum.ZUUL_ATTACK.getCode(), ZuulEnum.ZUUL_ATTACK.getMessage());
    }
    public static ZuulResponse error(ZuulEnum zuulEnum){
        return new ZuulResponse(zuulEnum.getCode(),zuulEnum.getMessage());
    }
    public static ZuulResponse error(Integer code, String message){
        return new ZuulResponse(code,message);
    }
    public static ZuulResponse success(){
        return new ZuulResponse(ZuulEnum.ZUUL_SUCCESS.getCode(),ZuulEnum.ZUUL_SUCCESS.getMessage());
    }
    public static ZuulResponse success(ZuulEnum zuulEnum){
        return new ZuulResponse(zuulEnum.getCode(),zuulEnum.getMessage());
    }
    public static ZuulResponse success(Object data){
        return new ZuulResponse(ZuulEnum.ZUUL_SUCCESS.getCode(),ZuulEnum.ZUUL_SUCCESS.getMessage(),data);
    }






    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
