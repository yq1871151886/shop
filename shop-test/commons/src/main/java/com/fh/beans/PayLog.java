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
@TableName("r_paylog")
public class PayLog implements Serializable {

   /* `outTradeNo` varchar(50) NOT NULL,
  `orderId` varchar(50) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `transactionId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
            `payTime` datetime DEFAULT NULL,
            `payMoney` decimal(10,2) DEFAULT NULL,
  `payType` int(11) DEFAULT NULL,
  `payStatus` int(11) DEFAULT NULL,*/
        @TableId(value = "outTradeNo",type = IdType.INPUT)
        private String  outTradeNo;

        @TableField(value = "orderId")
        private String orderId;

        @TableField(value = "userId")
        private String userId;

        @TableField(value = "transactionId")
        private String transactionId;

        @DateTimeFormat(pattern = "yyyy-HH-dd HH:mm:ss")
        @TableField(value = "createTime")
        private Date createTime;

        @TableField(value = "payType")
        private Integer payType;

        @DateTimeFormat(pattern = "yyyy-HH-dd HH:mm:ss")
        @TableField(value = "payTime")
        private Date payTime;

        @TableField(value = "payMoney")
        private BigDecimal payMoney;

        @TableField(value = "payStatus")
        private Integer payStatus;



}
