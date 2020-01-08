package com.fh.service;

import com.fh.enumbean.LoginCode;

public interface PayService {
    LoginCode getTotalPriceByOutTradeNo(String phone, String outTradeNo);

    LoginCode ewmMinutes(String phone, String outTradeNo);
    void queryPassTimeAndNoPay();
}
