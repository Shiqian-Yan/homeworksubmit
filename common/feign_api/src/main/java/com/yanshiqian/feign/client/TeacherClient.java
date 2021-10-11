package com.yanshiqian.feign.client;

import com.yanshiqian.feign.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@FeignClient(value = "service-teacher")
public interface TeacherClient {

    @GetMapping("/teacher/homework/getEnd/{times}/{id}")
    public Date getEnd(@PathVariable("times") String times, @PathVariable("id") String id);
}
