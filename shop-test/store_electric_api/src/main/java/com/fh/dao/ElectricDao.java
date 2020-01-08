package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.Electric;
import com.fh.beans.vo.ElectricVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ElectricDao extends BaseMapper<Electric> {

    List<ElectricVo> queryElectricAll();


}
