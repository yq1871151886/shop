package com.fh.beans;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_electric")
@Data
public class Electric implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "pid")
    private Integer pid;
    @TableField(value = "electricName")
    private String name;
    @TableField(value = "isValid")
    private Integer isValid;

}
