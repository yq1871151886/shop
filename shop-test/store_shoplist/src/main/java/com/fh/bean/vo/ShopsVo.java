package com.fh.bean.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShopsVo implements Serializable {
    private Integer id;

    private String brandid;

    private String name;

    private String subtitle;

    private String mainImg;

    private String subImgs;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer limit;

    private Integer page;


}
