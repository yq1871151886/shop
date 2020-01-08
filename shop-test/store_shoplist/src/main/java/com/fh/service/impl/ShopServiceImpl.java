package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.fh.bean.po.ShopsPo;
import com.fh.bean.vo.ShopsVo;
import com.fh.beans.Shops;
import com.fh.dao.ShopsDao;
import com.fh.service.ShopService;
import com.fh.utils.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopsDao shopDao;





    /*增加*/
    public void addShop(Shops shops){
        shops.setCreateTime(new Date());
        shopDao.insert(shops);
    }
    /*修改*/
    public void updateShops(Shops shops){
        shopDao.updateById(shops);
    }
    /*删除*/
    public void deleteShops(Integer id){
        shopDao.deleteById(id);
    }


    public String queryShopsPage(ShopsPo shops){
        Integer start = shops.getLimit()* (shops.getPage()-1);
        Long aLong = shopDao.queryShopsCount(shops);

        List<ShopsVo> shopsVos = shopDao.queryShopsPage(start, shops);
        Layui data = Layui.data(aLong, shopsVos);
        String s = JSON.toJSONString(data);
        return s;
    }

    public Shops queryShopsById(Integer id){
        return shopDao.selectById(id);
    }

    public List<ShopsVo> queryShopsAllByElectricid(String electricid){
        List<ShopsVo> shopsVos = shopDao.queryShopsAllByElectricid(electricid);
        return shopsVos;
    }

    public List<ShopsVo> queryShopsByBrandid(String brandid,String electricid){
        List<ShopsVo> shopsVos = shopDao.queryShopsByBrandid(brandid,electricid);
        return shopsVos;
    }

    public String queryShopsByparameter(ShopsPo shops){
        Integer start =  shops.getLimit()*(shops.getPage()-1);
        Long aLong = shopDao.queryShopsByparameterCount(shops);
        List<ShopsVo> shopsVos = shopDao.queryShopsByparameter(shops,start);
        Layui data = Layui.data(aLong, shopsVos);
        String s = JSON.toJSONString(data);
        return s;
    }

    @Override
    public Integer queryStockByShopId(Integer shopId) {
        Integer stock = shopDao.queryStockByShopId(shopId);
        return stock;
    }

    @Override
    public void updateStockByPassTimer(Integer shopId, Integer stock) {
        shopDao.updateStockByPassTimer(shopId,stock);
    }


}