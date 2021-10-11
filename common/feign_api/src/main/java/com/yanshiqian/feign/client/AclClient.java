package com.yanshiqian.feign.client;

import com.yanshiqian.feign.config.DefaultFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

//@Component
@FeignClient(value = "service-acl")
public interface AclClient {
    @GetMapping("/admin/acl/user/getUserInfo")
    public Map<String,Object> getUserInfo();
}
