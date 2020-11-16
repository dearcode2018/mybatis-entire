package com.hua.modular.sys.service.impl;

import com.hua.modular.sys.entity.User;
import com.hua.modular.sys.mapper.UserMapper;
import com.hua.modular.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author qianye.zheng
 * @since 2020-11-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
