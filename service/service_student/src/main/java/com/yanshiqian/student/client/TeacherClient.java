package com.yanshiqian.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@FeignClient("service-teacher")
public interface TeacherClient {

    @GetMapping("/teacher/homework/getEnd/{times}/{id}")
    public Date getEnd(@PathVariable("times") String times, @PathVariable("id") String id);
}
