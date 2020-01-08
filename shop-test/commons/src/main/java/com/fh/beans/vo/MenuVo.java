package com.fh.beans.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuVo implements Serializable {


    private String id;

    private String name;

    private String pid;

    private String image;

    private String ele;

}
