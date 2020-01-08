package com.fh.beans;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("r_shop_order")
public class Order implements Serializable {

    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    @TableField(value = "userId")
    private String userId;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "totalPrice")
    private BigDecimal totalPrice;

    @TableField(value = "totalCount")
    private Integer totalCount;

    @TableField(value = "payType")
    private Integer payType;

    @TableField(value = "addressId")
    private Integer addressId;

    @DateTimeFormat(pattern = "yyyy-HH-dd HH:mm:ss")
    @TableField(value = "payTime")
    private Date payTime;

    @DateTimeFormat(pattern = "yyyy-HH-dd HH:mm:ss")
    @TableField(value = "createTime")
    private Date createTime;







}
