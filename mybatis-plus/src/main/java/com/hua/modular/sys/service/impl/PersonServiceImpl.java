package com.hua.modular.sys.service.impl;

import com.hua.modular.sys.entity.Person;
import com.hua.modular.sys.mapper.PersonMapper;
import com.hua.modular.sys.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人信息表 服务实现类
 * </p>
 *
 * @author qianye.zheng
 * @since 2020-12-14
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
