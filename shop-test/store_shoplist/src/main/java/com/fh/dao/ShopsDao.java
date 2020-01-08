package com.fh.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.bean.po.ShopsPo;
import com.fh.bean.vo.ShopsVo;
import com.fh.beans.Shops;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper/*@CacheNamespace(size = 1024)*/

public interface ShopsDao extends BaseMapper<Shops> {

    List<Map<String,Object>> queryBrand();
    Long queryShopsCount(@Param("shops") ShopsPo shops);
    List<ShopsVo> queryShopsPage(@Param("start") Integer start, @Param("shops") ShopsPo shops);

    List<ShopsVo> queryShopsAllByElectricid(@Param("ele") String ele);

    List<ShopsVo> queryShopsByBrandid(@Param("brandid") String brandid,@Param("electricid") String electricid);




    Long queryShopsByparameterCount(@Param("shops") ShopsPo shops);
    List<ShopsVo> queryShopsByparameter(@Param("shops") ShopsPo shops,@Param("start") Integer start);


    Integer queryStockByShopId(@Param("shopId") Integer shopId);


    void updateStockByPassTimer(@Param("shopId") Integer shopId,@Param("stock") Integer stock);
}
