package com.fh.service.impl;

import com.fh.beans.vo.BrandVo;
import com.fh.dao.BrandDao;
import com.fh.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {


    @Autowired
    private BrandDao brandDao;


    @Override
    public List<BrandVo> queryBrandByElectricid(String electricid) {
        List<BrandVo> brandVos = brandDao.queryBrandByElectricid(electricid);
        return brandVos;
    }
}
