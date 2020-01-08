package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringCloudApplication
@EnableFeignClients
@EnableEurekaClient
@EnableZuulProxy
@EnableRedisRepositories
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class ShopZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopZuulApplication.class, args);
    }

}
