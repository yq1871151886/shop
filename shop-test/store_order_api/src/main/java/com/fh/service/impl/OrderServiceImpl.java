package com.fh.service.impl;

import com.fh.beans.*;
import com.fh.controller.ShopListAPI;
import com.fh.dao.DetailDao;
import com.fh.dao.OrderDao;
import com.fh.dao.PayLogDao;
import com.fh.enumbean.LoginCode;
import com.fh.enumbean.LoginEnum;
import com.fh.service.OrderService;
import com.fh.utils.UtilStatus;
import com.fh.utils.UtilsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DetailDao detailDao;

    @Autowired
    private PayLogDao payLogDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ShopListAPI shopListAPI;

    @Override
    public LoginCode commitOrder(Integer addressId, String phone) {
        String cartId = (String) redisTemplate.opsForValue().get(phone);
        //取出当前用户购物车中所有的商品
        List<Card> cartlist = redisTemplate.opsForHash().values(cartId);

        //过滤出购物车中处于选中状态的商品
        List<Card> iscart = cartlist.stream().filter(cart->cart.getIsChecked()).collect(Collectors.toList());

        //从redis中取出用户信息
        User user = (User) redisTemplate.opsForValue().get("user_"+phone);
        //无货的商品集合
        List<Card>  nostock = new ArrayList<Card>();

        //需要删除的购物车的list
        List<Integer> shopIds = new ArrayList<Integer>();


        BigDecimal totalPrice = BigDecimal.valueOf(0.00);
        //定义用户需要购买的商品的总数量
        Integer totalCount = 0;
        //调用工具类获取一个根据机器码生成的id
        String orderId = UtilsTools.getSnowId();

        //String queryStockByShopId="http://localhost:8093/shop-list/product/shop/queryStockByShopId/";
        for(Card card:iscart){
            Integer shopId = card.getShopId();
            //调用接口通过商品id查出库存
            Integer stock = shopListAPI.queryStockByShopId(shopId);
            //String s = HttpclientUtils.doGet(queryStockByShopId + shopId);
            //int stock = Integer.valueOf(s);
            int count = card.getCount();
            if (count>stock){//如果当前购买数量大于库存，则将库存不足的商品
                nostock.add(card);
            }else {
                //为了防止超卖，如果用户同时进入了这个方法，在减库存的sql上加上库存减去购买数量必须大于等于0的判断
                Integer updateNum = detailDao.updateStock(card.getShopId(),card.getCount());
                if (updateNum==0){
                    nostock.add(card);
                }else {
                    //保存用户订单详情信息，每个购买的商品都会有一条记录
                    OrderDetail detail = new OrderDetail();
                    detail.setUserId(user.getId());
                    detail.setCount(card.getCount());
                    detail.setPrice(card.getPrice());
                    detail.setImage(card.getShopImg());
                    detail.setShopId(card.getShopId());
                    detail.setSubTotalPrice(card.getTotal());
                    detail.setOrderId(orderId);
                    detail.setShopName(card.getShopName());
                    detailDao.insert(detail);
                    totalPrice=totalPrice.add(card.getTotal());
                    totalCount+=card.getCount();
                    shopIds.add(card.getShopId());
                }
            }
        }
        //发起订单
        Order order  = new Order();
        order.setAddressId(addressId);
        order.setCreateTime(new Date());
        order.setPayType(1);
        order.setUserId(user.getId());
        order.setTotalCount(totalCount);
        order.setTotalPrice(totalPrice);
        order.setId(orderId);
        order.setStatus(UtilStatus.INIT_STATUS);
        orderDao.insert(order);

        //开始支付，发起一个支付单子
        PayLog payLog = new PayLog();
        payLog.setCreateTime(new Date());
        payLog.setOrderId(orderId);
        payLog.setOutTradeNo(orderId+phone);
        payLog.setPayMoney(totalPrice);
        payLog.setTransactionId("1");
        payLog.setPayStatus(UtilStatus.INIT_STATUS);
        payLog.setUserId(user.getId());
        payLog.setPayType(1);
        payLogDao.insert(payLog);

        //从购物车中删除这些已经提交订单的
        for (Integer shopId:shopIds){
            redisTemplate.opsForHash().delete(cartId,shopId);
        }
        redisTemplate.opsForHash().put("yqg"+phone,payLog.getOutTradeNo(),payLog);
        //redisTemplate.opsForValue().set(payLog.getOutTradeNo(),payLog);
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("noStockShops",nostock);
        data.put("totalPrice",totalPrice);
        data.put("orderId",orderId);
        data.put("outTradeNo",payLog.getOutTradeNo());
        return LoginCode.success(data);
    }


}
