package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.OrderDetail;
import com.fh.beans.PayLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PayLogDao extends BaseMapper<PayLog> {

    List<String> queryPassTimeAndNoPay();

    List<OrderDetail> queryShopByPassTimeOrderId(@Param("orderIds") List<String> orderIds);
}
