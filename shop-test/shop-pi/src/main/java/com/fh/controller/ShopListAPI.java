package com.fh.controller;

import com.fh.beans.Shops;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "shop-list")
public interface ShopListAPI {

    @GetMapping("/product/shop/queryStockByShopId/{shopId}")
    Integer queryStockByShopId(@PathVariable(value = "shopId") Integer shopId);

    @GetMapping("/product/shops/{shopsId}")
    Shops queryById(@PathVariable(value = "shopsId") Integer shopsId);

}
