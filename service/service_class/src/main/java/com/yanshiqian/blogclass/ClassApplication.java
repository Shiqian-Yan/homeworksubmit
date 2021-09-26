package com.yanshiqian.blogclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.yanshiqian"})
@MapperScan("com.yanshiqian.blogclass.mapper")
public class ClassApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassApplication.class,args);
    }
}
