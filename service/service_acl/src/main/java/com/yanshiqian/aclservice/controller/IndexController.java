package com.yanshiqian.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yanshiqian.aclservice.entity.User;
import com.yanshiqian.aclservice.service.IndexService;
import com.yanshiqian.aclservice.service.UserService;
import com.yanshiqian.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")

public class IndexController {

    @Autowired
    private IndexService indexService;
    @Autowired
    private UserService userService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public R info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return R.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public R getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return R.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
    //获取当前用户的id
    @GetMapping("/getId")
    public String getId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",username);
        User user = userService.selectByUsername(username);
        System.out.println("123456");
        return "user.getId()";

    }

}
