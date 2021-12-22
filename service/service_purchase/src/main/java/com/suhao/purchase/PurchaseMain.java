package com.suhao.purchase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.suhao"})
@MapperScan("com.suhao.purchase.mapper")
public class PurchaseMain {
    public static void main(String[] args) {
        SpringApplication.run(PurchaseMain.class, args);
    }
}
