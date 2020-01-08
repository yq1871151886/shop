package com.fh.service.impl;

import com.fh.beans.Card;
import com.fh.beans.Shops;
import com.fh.controller.ShopListAPI;
import com.fh.enumbean.LoginCode;
import com.fh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ShopListAPI shopListAPI;


    @Override
    public LoginCode addCart(Integer shopsId, String phone) {
        String cardid = (String) redisTemplate.opsForValue().get(phone);
        //String url="http://localhost:8083/product/shops/"+shopsId;
        //String s = HttpclientUtils.doGet(url);
        //JSONObject jsonObject = JSON.parseObject(s);
        //JSONObject productData=JSON.parseObject(jsonObject.get("data").toString());
        Shops shops = shopListAPI.queryById(shopsId);
        Card card = new Card();
        card.setCardId(cardid);
        card.setShopId(shops.getId());
        card.setShopName(shops.getName());
        card.setShopImg(shops.getMainImg());
        card.setPrice(shops.getPrice());
        if (redisTemplate.opsForHash().hasKey(cardid,shopsId)){
            card = (Card) redisTemplate.opsForHash().get(cardid, shopsId);
            card.setCount(card.getCount()+1);
        }else {
            card.setCount(1);
        }
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal bigcount1 = new BigDecimal(card.getCount());
        BigDecimal multiply = bigDecimal.add(card.getPrice()).multiply(bigcount1);
        card.setTotal(multiply);
        card.setIsChecked(true);
        redisTemplate.opsForHash().put(cardid,shopsId,card);

        Long size = redisTemplate.opsForHash().size(cardid);

        return LoginCode.success(size);
    }

    @Override
    public LoginCode queryCartOrdersCommit(String phone) {
        String cartdid = (String) redisTemplate.opsForValue().get(phone);

        List<Card> cards =  redisTemplate.opsForHash().values(cartdid);
        //符合条件的list
        List<Card> cartList = new ArrayList<Card>();
        //商品ID集合
        //String queryStockByShopId="http://localhost:8083/product/shop/queryStockByShopId/";
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        for (int i = 0;i<cards.size();i++){
            if (cards.get(i).getIsChecked()){
                //发送商品ID，查询库存
                //String s = HttpclientUtils.doGet(queryStockByShopId + cards.get(i).getShopId());
                //Integer stock = Integer.parseInt(s);
                Integer stock = shopListAPI.queryStockByShopId(cards.get(i).getShopId());
                if (stock>=cards.get(i).getCount()){
                    cards.get(i).setHasStocks(true);
                    bigDecimal=bigDecimal.add(cards.get(i).getTotal());
                }else {
                    cards.get(i).setHasStocks(false);
                }
                cartList.add(cards.get(i));
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("card",cartList);
        map.put("account",bigDecimal);
        return LoginCode.success(map);
    }

    public LoginCode queryCart(String phone) {
        String cartdid = (String) redisTemplate.opsForValue().get(phone);

        List<Card> cards =  redisTemplate.opsForHash().values(cartdid);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        for (int i = 0;i<cards.size();i++){
            if (cards.get(i).getIsChecked()){
                bigDecimal=bigDecimal.add(cards.get(i).getTotal());
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("card",cards);
        map.put("account",bigDecimal);
        return LoginCode.success(map);
    }

    @Override
    public LoginCode changeCheck(Integer shopId, String phone) {
         String cartid = (String) redisTemplate.opsForValue().get(phone);
        Card card = (Card) redisTemplate.opsForHash().get(cartid,shopId);
        card.setIsChecked(!card.getIsChecked());
        redisTemplate.opsForHash().put(cartid,shopId,card);
        return LoginCode.success();
    }

    @Override
    public LoginCode changeCount(Integer shopId, String type, Integer oldCount,String phone) {
        String cartid = (String) redisTemplate.opsForValue().get(phone);
        Card card = (Card) redisTemplate.opsForHash().get(cartid,shopId);
        if (type.equals("sub")){
            if (oldCount<=1){
                redisTemplate.opsForHash().delete(cartid,shopId);
                return LoginCode.success();
            }else {
                card.setCount(oldCount-1);
                BigDecimal bigDecimal =BigDecimal.valueOf(0.00);
                BigDecimal multiply = bigDecimal.add(card.getPrice()).multiply(new BigDecimal(card.getCount()));
                card.setTotal(multiply);
                redisTemplate.opsForHash().put(cartid,shopId,card);
                return LoginCode.success();
            }
        }else {
            card.setCount(oldCount+1);
            BigDecimal bigDecimal =BigDecimal.valueOf(0.00);
            BigDecimal multiply = bigDecimal.add(card.getPrice()).multiply(new BigDecimal(card.getCount()));
            card.setTotal(multiply);
            redisTemplate.opsForHash().put(cartid,shopId,card);
            return LoginCode.success();
        }


    }

    @Override
    public LoginCode blurChangeCount(Integer shopId, Integer count, String phone) {
        String cartid = (String) redisTemplate.opsForValue().get(phone);
        Card card = (Card) redisTemplate.opsForHash().get(cartid,shopId);
        card.setCount(count);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal multiply = bigDecimal.add(card.getPrice()).multiply(new BigDecimal(card.getCount()));
        card.setTotal(multiply);
        redisTemplate.opsForHash().put(cartid,shopId,card);
        return LoginCode.success();
    }

    @Override
    public LoginCode checkAll(String phone,String check) {
        String cartid = (String) redisTemplate.opsForValue().get(phone);
        List<Card> cards = redisTemplate.opsForHash().values(cartid);
        for (Card card:cards){
            if (check.equals("mark")){
                card.setIsChecked(false);
            }else {
                card.setIsChecked(true);
            }
            Integer shopId = card.getShopId();
            redisTemplate.opsForHash().put(cartid,shopId,card);
        }
        return LoginCode.success();
    }

    @Override
    public LoginCode deleteShops(Integer shopId, String phone) {
        String cartid = (String) redisTemplate.opsForValue().get(phone);
        redisTemplate.opsForHash().delete(cartid,shopId);
        return LoginCode.success();
    }

    @Override
    public LoginCode queryCartShopCount(String phone) {
        String cartid = (String) redisTemplate.opsForValue().get(phone);
        Long size = redisTemplate.opsForHash().size(cartid);
        return LoginCode.success(size);
    }


}
