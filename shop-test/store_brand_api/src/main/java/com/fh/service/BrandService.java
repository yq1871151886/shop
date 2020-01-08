package com.fh.service;

import com.fh.beans.vo.BrandVo;

import java.util.List;

public interface BrandService {
    List<BrandVo> queryBrandByElectricid(String electricid);
}
