package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.bean.Orders;
import com.fh.beans.User;
import com.fh.dao.OrdersDao;
import com.fh.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private RedisTemplate redisTemplate;

    public void addOrders(Orders orders,String phone){
        User user = (User) redisTemplate.opsForValue().get("user_"+phone);
        orders.setUserId(user.getId());
        orders.setCuTime(new Date());
        ordersDao.insert(orders);
    }

    @Override
    public Orders queryOrderById(Integer id) {
        Orders orders = ordersDao.selectById(id);
        return orders;
    }

    public void updateOrders(Orders orders,String phone){
        User user = (User) redisTemplate.opsForValue().get("user_"+phone);
        orders.setUserId(user.getId());
        orders.setCuTime(new Date());
        ordersDao.updateById(orders);
    }

    public void deleteOrders(Integer id){
        ordersDao.deleteById(id);
    }

    public List<Orders> queryOrders(String phone){
        User user = (User) redisTemplate.opsForValue().get("user_" + phone);
        QueryWrapper<Orders> wrapper = new QueryWrapper<Orders>();
        wrapper.eq("userId",user.getId());
        wrapper.orderByDesc("cuTime");
        List<Orders> orders = ordersDao.selectList(wrapper);
        return orders;
    }


}
