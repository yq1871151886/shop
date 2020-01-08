package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@EnableScheduling
@EnableFeignClients
public class StoreOrderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreOrderApiApplication.class, args);
    }

}
