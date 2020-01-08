package com.fh.service;

import com.fh.bean.po.ShopsPo;
import com.fh.bean.vo.ShopsVo;
import com.fh.beans.Shops;

import java.util.List;

public interface ShopService {

    void addShop(Shops shops);
    String queryShopsPage(ShopsPo shops);
    Shops queryShopsById(Integer id);
    void updateShops(Shops shops);
    void deleteShops(Integer id);
    List<ShopsVo> queryShopsAllByElectricid(String electricid);
    List<ShopsVo> queryShopsByBrandid(String brandid,String electricid);
    String queryShopsByparameter(ShopsPo shops);

    Integer queryStockByShopId(Integer shopId);


    void updateStockByPassTimer(Integer shopId,Integer stock);
}
