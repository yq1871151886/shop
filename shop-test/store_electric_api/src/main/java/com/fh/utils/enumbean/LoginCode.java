package com.fh.utils.enumbean;


public class LoginCode {

    private Integer code;
    private String message;
    private Object data;


    private LoginCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public LoginCode(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static LoginCode error() {
        return new LoginCode(LoginEnum.ERROR.getCode(), LoginEnum.ERROR.getMessage());
    }
    public static LoginCode error(LoginEnum loginEnum){
        return new LoginCode(loginEnum.getCode(),loginEnum.getMessage());
    }
    public static LoginCode success(){
        return new LoginCode(LoginEnum.LOGIN_SUCCESS.getCode(),LoginEnum.LOGIN_SUCCESS.getMessage());
    }
    public static LoginCode success(LoginEnum loginEnum){
        return new LoginCode(loginEnum.getCode(),loginEnum.getMessage());
    }
    public static LoginCode success(Object data){
        return new LoginCode(LoginEnum.LOGIN_SUCCESS.getCode(),LoginEnum.LOGIN_SUCCESS.getMessage(),data);
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
