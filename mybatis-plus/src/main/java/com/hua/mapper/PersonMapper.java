/**
  * @filename PersonMapper.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hua.entity.Person;

/**
 * @type PersonMapper
 * @description 
 * @author qianye.zheng
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
