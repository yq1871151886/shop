package com.fh.service;

import com.fh.bean.Orders;

import java.util.List;

public interface OrdersService {
    public void addOrders(Orders orders,String phone);
    public Orders queryOrderById(Integer id);
    public void updateOrders(Orders orders,String phone);
    public void deleteOrders(Integer id);
    public List<Orders> queryOrders(String phone);
}
