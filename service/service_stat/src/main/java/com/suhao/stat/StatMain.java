package com.suhao.stat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.suhao.stat.mapper")
@ComponentScan(basePackages = {"com.suhao"})
@EnableScheduling
public class StatMain {

    public static void main(String[] args) {
        SpringApplication.run(StatMain.class, args);
    }
}
