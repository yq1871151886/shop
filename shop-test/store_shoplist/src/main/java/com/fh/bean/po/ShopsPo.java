package com.fh.bean.po;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ShopsPo implements Serializable {


    private String name;

    private String subtitle;

    private String electricid;

    private String brandid;


    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Integer limit;

    private Integer page;


}
