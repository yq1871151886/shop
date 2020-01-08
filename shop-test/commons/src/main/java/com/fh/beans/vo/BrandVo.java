package com.fh.beans.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandVo implements Serializable {
    private String id;
    private String brandName;
    private String brandPhone;
    private String status;
    private String image;

}
