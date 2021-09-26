package com.yanshiqian.courseclass.client;

import com.yanshiqian.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

//@Component
@FeignClient("service-student")
public interface StudentClient {
    //查询某一天注册人数
    @GetMapping("/student/homeworksubmit/deleteCourse/{id}")
    public R deleteCourse(@PathVariable("id") String id);

}
