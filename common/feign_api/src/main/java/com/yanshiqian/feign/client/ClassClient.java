package com.yanshiqian.feign.client;

import com.yanshiqian.feign.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-class")
public interface ClassClient {

    @GetMapping("/blogclass/class/getCourseName/{id}")
    public String getCourseName(@PathVariable("id") String id);
}
