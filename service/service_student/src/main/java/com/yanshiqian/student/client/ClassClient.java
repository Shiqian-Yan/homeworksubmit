package com.yanshiqian.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient("service-class")
public interface ClassClient {

    @GetMapping("/blogclass/class/getCourseName/{id}")
    public String getCourseName(@PathVariable("id") String id);
}
