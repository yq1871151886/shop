package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@EnableDiscoveryClient
public class ShopRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopRegisterApplication.class, args);
    }

}
