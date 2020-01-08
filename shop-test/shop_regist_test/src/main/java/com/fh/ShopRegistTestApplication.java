package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@SpringCloudApplication
public class ShopRegistTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopRegistTestApplication.class, args);
    }

}
