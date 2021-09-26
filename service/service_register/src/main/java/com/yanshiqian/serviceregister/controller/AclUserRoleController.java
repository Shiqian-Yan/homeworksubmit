package com.yanshiqian.serviceregister.controller;


import com.yanshiqian.commonutils.R;
import com.yanshiqian.serviceregister.entity.AclRole;
import com.yanshiqian.serviceregister.entity.AclUser;
import com.yanshiqian.serviceregister.entity.AclUserRole;
import com.yanshiqian.serviceregister.entity.vo.AddVo;
import com.yanshiqian.serviceregister.service.AclRoleService;
import com.yanshiqian.serviceregister.service.AclUserRoleService;
import com.yanshiqian.serviceregister.service.AclUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-26
 */
@RestController
@RequestMapping("/serviceregister/acluserrole")
public class AclUserRoleController {
    @Autowired
    private AclRoleService aclRoleService;
    @Autowired
    private AclUserRoleService aclUserRoleService;
    @PostMapping("add")
    public R add(@RequestBody AddVo addVo){
         AclRole aclRole = aclRoleService.getById(addVo.getRoleId());
        AclUserRole aclUserRole = new AclUserRole();
         aclUserRole.setRoleId(aclRole.getId());
         aclUserRole.setUserId(addVo.getId());
         aclUserRoleService.save(aclUserRole);
         return R.ok();

     }
}

