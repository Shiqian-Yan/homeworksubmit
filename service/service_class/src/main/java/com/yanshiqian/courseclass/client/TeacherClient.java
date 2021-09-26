package com.yanshiqian.courseclass.client;

import com.yanshiqian.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-teacher")
public interface TeacherClient {

    @GetMapping("/teacher/homework/deleteCourse/{id}")
    public R deleteCourse1(@PathVariable("id") String id);
}
