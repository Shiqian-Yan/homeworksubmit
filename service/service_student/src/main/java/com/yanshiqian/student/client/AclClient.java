package com.yanshiqian.student.client;

import com.yanshiqian.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

//@Component
@FeignClient("service-acl")
public interface AclClient {
    //查询某一天注册人数
    @GetMapping("/admin/acl/user/getUserInfo")
    public Map<String,Object> getUserInfo();
}
