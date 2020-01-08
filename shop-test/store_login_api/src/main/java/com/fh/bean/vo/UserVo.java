package com.fh.bean.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public  class UserVo implements Serializable {
    private String  id;                   //char not null,
    private String  loginName;            //varchar(0),
    private String  realName;             //varchar(0),
    private String  loginPwd;             //varchar(0),
    private String  email;                //varchar(0),
    private String  phone;                //varchar(0),
    private String  idCard;               //varchar(0),
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    birthday;             //date,

    private Integer limit;
    private Integer page;


}
