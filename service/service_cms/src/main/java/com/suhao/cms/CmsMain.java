package com.suhao.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.suhao"})
@EnableDiscoveryClient
@MapperScan(value = {"com.suhao.cms.mapper"})
public class CmsMain {

    public static void main(String[] args) {
        SpringApplication.run(CmsMain.class, args);
    }
}
