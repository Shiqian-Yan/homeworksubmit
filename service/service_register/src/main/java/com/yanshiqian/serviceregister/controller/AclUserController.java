package com.yanshiqian.serviceregister.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.commonutils.MD5;
import com.yanshiqian.commonutils.R;
import com.yanshiqian.servicebase.exceptionHandler.GlobalException;
import com.yanshiqian.serviceregister.entity.AclUser;
import com.yanshiqian.serviceregister.service.AclUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-25
 */
@RestController
@RequestMapping("/serviceregister/acluser")
public class AclUserController {
    @Autowired
    private AclUserService aclUserService;
    @PostMapping("/register")
    public  synchronized R registerUser(@RequestBody AclUser aclUser){
        aclUser.setPassword(MD5.encrypt(aclUser.getPassword()));
        try {
            aclUserService.save(aclUser);
        }catch (Exception e){
            throw new GlobalException(20001,"学号/账号重复,请刷新页面重新填写");
        }
        return R.ok().data("userId",aclUser.getId());
    }
}

