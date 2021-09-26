package com.yanshiqian.serviceregister.service.impl;

import com.yanshiqian.serviceregister.entity.AclUser;
import com.yanshiqian.serviceregister.mapper.AclUserMapper;
import com.yanshiqian.serviceregister.service.AclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yanshiqian
 * @since 2021-09-25
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

}
