package com.fh;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fh.beans.Shops;
import com.fh.dao.ShopsDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StoreShoplistApplication.class)
public class StoreShoplistApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private ShopsDao shopsDao;

    @Test
    public void contextLoads() throws JsonProcessingException {
        //1.从redis中获取数据
        String students = redisTemplate.boundValueOps("findAll").get();
        //2.如果获取不到，从数据库中获取数据
        QueryWrapper<Shops> mapper = new QueryWrapper<Shops>();
        if(StringUtils.isEmpty(students)){
            List<Shops> shops = shopsDao.selectList(mapper);
            ObjectMapper om = new ObjectMapper();
            students = om.writeValueAsString(shops);
            //将转换成的json字符串放入到redis中去
            redisTemplate.boundValueOps("findAll").set(students);
            System.out.println(">>>>>>>>>>>>从数据库中获取数据<<<<<<<<<<<");

        }else{
            System.out.println(">>>>>>>>>>>>从redis中获取数据<<<<<<<<<<<");
        }
        //3.如果获取到数据，直接在控制台输出
        System.out.println(students);
    }

    public static void main(String[] args) {
        RedisTemplate<String,String> redisTemplate;

        //String s = redisTemplate.boundValueOps("").get();


    }




}
