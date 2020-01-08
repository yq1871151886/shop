package com.fh.controller;

import com.fh.bean.Orders;
import com.fh.loginAop.LoginAnnotation;
import com.fh.service.OrdersService;
import com.fh.utils.UtilsTools;
import com.fh.utils.enumbean.LoginCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("product/orders")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @PutMapping
    @LoginAnnotation
    public LoginCode addOrders(Orders orders){
        HttpServletRequest request = UtilsTools.getSession();
        String phone = (String) request.getAttribute("phone");
        if (orders.getId()==null){
            ordersService.addOrders(orders,phone);
        }else {
            ordersService.updateOrders(orders,phone);
        }
        return LoginCode.success();
    }


    @GetMapping("/queryOrderById")
    @LoginAnnotation
    public LoginCode queryOrderById(Integer id){
        Orders orders = ordersService.queryOrderById(id);
        return LoginCode.success(orders);
    }




    @PostMapping("/updateChooseOrders")
    @LoginAnnotation
    public LoginCode updateChooseOrders(Orders orders){
        HttpServletRequest request = UtilsTools.getSession();
        String phone = (String) request.getAttribute("phone");
        ordersService.updateOrders(orders,phone);
        return LoginCode.success();
    }



    @DeleteMapping
    @LoginAnnotation
    public LoginCode deleteOrders(Integer id){
        ordersService.deleteOrders(id);
        return LoginCode.success();
    }

    @GetMapping
    @LoginAnnotation
    public LoginCode queryUserAddrs(){
        HttpServletRequest request = UtilsTools.getSession();
        String phone = (String) request.getAttribute("phone");
        List<Orders> orders = ordersService.queryOrders(phone);
        return LoginCode.success(orders);
    }


}
