package com.fh.service;

import com.fh.enumbean.LoginCode;

public interface CartService {


    LoginCode addCart(Integer shopsid, String phone);

    LoginCode queryCart(String phone);

    LoginCode queryCartOrdersCommit(String phone);

    LoginCode changeCheck(Integer shopId, String phone);

    LoginCode changeCount(Integer shopId, String type, Integer oldCount,String phone);

    LoginCode blurChangeCount(Integer shopId, Integer count, String phone);

    LoginCode checkAll(String phone,String check);

    LoginCode deleteShops(Integer shopId, String phone);

    LoginCode queryCartShopCount(String phone);
}
