package com.yanshiqian.teacher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yanshiqian")
@MapperScan("com.yanshiqian.teacher.mapper")
@EnableDiscoveryClient//注册
@EnableFeignClients
public class ServiceTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTeacherApplication.class, args);
    }

}
