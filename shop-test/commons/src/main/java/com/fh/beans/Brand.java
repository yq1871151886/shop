package com.fh.beans;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "r_brand")
@Data
public class Brand implements Serializable {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    @TableField("brandName")
    private String brandName;
    @TableField("brandPhone")
    private String brandPhone;
    @TableField("status")
    private Integer status;
    @TableField("image")
    private String image;

}
