package com.fh.beans.po;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public  class UserPo implements Serializable {
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
