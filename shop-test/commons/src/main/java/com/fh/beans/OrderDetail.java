package com.fh.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("r_shop_order_detail")
public class OrderDetail implements Serializable {
            /*`orderId` varchar(50) NOT NULL COMMENT '订单id',
            `userId` int(11) DEFAULT NULL COMMENT '用户id',
            `productId` int(11) DEFAULT NULL COMMENT '商品id',
            `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
            `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
            `subTotalPrice` decimal(10,2) DEFAULT NULL COMMENT '订单表总金额',
            `image` varchar(255) DEFAULT NULL COMMENT '图片',
            `count` int(11) DEFAULT NULL COMMENT '商品数量',*/
        @TableId(value = "orderId",type = IdType.INPUT)
        private String orderId;

        @TableField("userId")
        private String userId;

        @TableField("productId")
        private Integer shopId;

        @TableField("productName")
        private String shopName;

        @TableField("price")
        private BigDecimal price;

        @TableField("subTotalPrice")
        private BigDecimal subTotalPrice;

        @TableField("image")
        private String image;

        @TableField("count")
        private Integer count;
}
