package com.fh.beans;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Card implements Serializable {


    private Integer shopId;

    private String cardId;

    private String shopName;

    private String shopImg;

    private BigDecimal price;

    private Integer count;

    private Boolean isChecked;

    private BigDecimal total;

    private Boolean hasStocks;
}
