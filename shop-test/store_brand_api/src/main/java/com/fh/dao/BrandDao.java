package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.Brand;
import com.fh.beans.vo.BrandVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BrandDao extends BaseMapper<Brand> {

    List<BrandVo> queryBrandByElectricid(String electricid);

}
