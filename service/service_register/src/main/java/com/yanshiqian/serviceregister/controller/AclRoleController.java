package com.yanshiqian.serviceregister.controller;


import com.yanshiqian.commonutils.R;
import com.yanshiqian.serviceregister.entity.AclRole;
import com.yanshiqian.serviceregister.service.AclRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-25
 */
@RestController
@RequestMapping("/serviceregister/aclrole")
public class AclRoleController {
    @Autowired
    private AclRoleService aclRoleService;
    @GetMapping("allRole")
    public R getAllRole(){
        List<AclRole> list = aclRoleService.list(null);
        return R.ok().data("allRole",list);
    }


}

