package com.fh.enumbean;

public enum LoginEnum {
    NAME_PWD_ERROR(501,"用户名或密码不存在")
    ,USER_ISNULL(503,"用户不存在")
    ,USER_PWD_ERROR(502,"密码错误")
    ,LOGIN_SUCCESS(200,"登录成功")
    ,IMG__SUCCESS(600,"上传成功")
    ,ERROR(500,"错误")
    ,LOGIN_NOTPOWER(504,"用户没有权限")
    ,LOGIN_SIVALID(505,"用户失效")
    ,REGISTER_NAMEPWDERROR(506,"用户名或密码不能为空")
    ,REGISTER_NAMEERROR(507,"用户名已存在")
    ,IMG_MORE_MAX(508,"当前图片过大")
    ,IMG_LAYOUT(507,"图片格式不对")
    ,IMG_UPLOAD_ERROR(509,"上传错误")
    ,LOGIN_PHONE_NULL(510,"手机号不存在或格式错误")
    ,LOGIN_PHONECODE_ERROR(511,"验证码错误")
    ,LOGIN_SENDCODE_SUCCESS(512,"验证码发送成功")
    ,LOGIN_TOKEN_INVALID(513,"token失效了")
    ,LOGIN_TOKEN_LOSE(514,"token解析失败")
    ,LOGIN_PHONEORCODE_ISNULL(515,"请输入手机号或者验证码")
    ,LOGIN_CONTENT_LONG(516,"服务器连接超时异常")
    ,LOGIN_SERVER_BUSYNESS(516,"服务器错误")
    ,PAY_ISNULL(517,"没有需要支付的订单")
    ,PAY_CREATE_ERR(518,"订单生成失败")
    ,PAY_IS_ERR(519,"订单支付失败")
    ,PAY_IS_TIMEOUT(520,"当前支付超时")
    ,MAKE_SUCCESS(200,"操作成功")
    ;


    private Integer code;
    private String message;

    private LoginEnum(Integer code, String message) {
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
