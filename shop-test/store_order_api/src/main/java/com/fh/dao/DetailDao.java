package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DetailDao extends BaseMapper<OrderDetail> {

    Integer updateStock(@Param("shopId") Integer shopId,@Param("count") Integer count);
}
