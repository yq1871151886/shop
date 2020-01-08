package com.fh.service;

import com.fh.enumbean.LoginCode;

public interface OrderService {
    LoginCode commitOrder(Integer addressId, String phone);


}
