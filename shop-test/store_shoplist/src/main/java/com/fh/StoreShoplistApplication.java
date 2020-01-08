package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringCloudApplication
@EnableRedisRepositories
public class StoreShoplistApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreShoplistApplication.class, args);
    }

}
