package com.fh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
public class StoreBrandApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreBrandApiApplication.class, args);
    }

}
