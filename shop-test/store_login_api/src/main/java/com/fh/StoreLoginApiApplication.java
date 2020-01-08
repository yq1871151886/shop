package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringCloudApplication
@EnableRedisRepositories
public class StoreLoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreLoginApiApplication.class, args);
    }

}
