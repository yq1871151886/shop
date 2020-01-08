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
@TableName("r_shop_product")
public class Shops implements Serializable {
   /* `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `brand_id` int(11) DEFAULT NULL COMMENT '类型ID',
    `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
    `subtitle` varchar(500) DEFAULT NULL COMMENT '宣传标题',
    `main_img` varchar(255) DEFAULT NULL COMMENT '主图片',
    `sub_imgs` text COMMENT '副图片',
    `detail` text COMMENT '商品描述',
    `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
    `stock` int(11) DEFAULT NULL COMMENT '库存',
    `status` int(11) DEFAULT NULL COMMENT '状态',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',*/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;


    @TableField(value ="name" )
    private String name;

    @TableField(value ="subtitle" )
    private String subtitle;

    @TableField(value ="main_img" )
    private String mainImg;

    @TableField(value ="sub_imgs" )
    private String subImgs;

    @TableField(value ="detail" )
    private String detail;

    @TableField(value ="price" )
    private BigDecimal price;

    @TableField(value ="stock" )
    private Integer stock;

    @TableField(value ="status" )
    private Integer status;

    @TableField(value ="create_time" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value ="update_time" )
    private Date updateTime;
   public Shops(){
     super();
   }
}
