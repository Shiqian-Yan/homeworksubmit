package com.yanshiqian.student;

import com.yanshiqian.feign.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yanshiqian")
@MapperScan("com.yanshiqian.student.mapper")
@EnableDiscoveryClient//注册
@EnableFeignClients(basePackages = "com.yanshiqian.feign.client",defaultConfiguration = DefaultFeignConfig.class)
public class ServiceStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStudentApplication.class, args);
    }

}
