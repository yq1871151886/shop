package com.fh.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("r_user")
@Data
public  class User implements Serializable {
    @TableId(value = "id",type = IdType.UUID)
    private String  id;                   //char not null,
    @TableField(value = "loginName",exist = true)
    private String  loginName;            //varchar(0),
    @TableField(value = "realName",exist = true)
    private String  realName;             //varchar(0),
    @TableField(value = "loginPwd",exist = true)
    private String  loginPwd;             //varchar(0),
    @TableField(value = "email",exist = true)
    private String  email;                //varchar(0),
    @TableField(value = "phone",exist = true)
    private String  phone;                //varchar(0),
    @TableField(value = "idCard",exist = true)
    private String  idCard;               //varchar(0),
    @TableField(value = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date    birthday;             //date,
    @TableField(value = "cardid")
    private String cardid;

}
