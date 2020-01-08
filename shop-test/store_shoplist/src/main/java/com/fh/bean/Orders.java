package com.fh.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@TableName("r_user_orders")
public class Orders {
    /*`id` int(11) NOT NULL AUTO_INCREMENT,
      `consignee` varchar(255) DEFAULT NULL,
      `addr` varchar(255) DEFAULT NULL,
      `oftenAddr` int(11) DEFAULT NULL,
      `consignPhone` varchar(255) DEFAULT NULL,
      `consignEmail` varchar(255) DEFAULT NULL,
      `consignName` varchar(255) DEFAULT NULL,
      `userId` varchar(255) DEFAULT NULL,
      `dafaultStatus` int(11) DEFAULT NULL,*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "consignee")
    private String consignee;

    @TableField(value = "addr")
    private String addr;

    @TableField(value = "oftenAddr")
    private String oftenAddr;

    @TableField(value = "consignPhone")
    private String consignPhone;

    @TableField(value = "consignEmail")
    private String consignEmail;

    @TableField(value = "consignName")
    private String consignName;

    @TableField(value = "userId")
    private String userId;

    @TableField(value = "defaultStatus")
    private Integer defaultStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "cuTime")
    private Date cuTime;


}
