/**
  * @filename PersonServiceDB2.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hua.annotation.DataSource;
import com.hua.datasource.DataSourceNames;
import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type PersonServiceDB2
 * @description 
 * @author qianye.zheng
 */
@DataSource(name = DataSourceNames.DB2)
@Service
public class PersonServiceDB2 {

	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 若不标注@Transactional或使用手工方式创建事务，则MyBatis的 SqlSessionTemplate
	 * 会设置连接的为自动提交: connection.setCommit(true)
	 * 
	 */
	
	/**
	 * 
	 * @description 
	 * @param id
	 * @return
	 * @author qianye.zheng
	 */
	public Person get(int id) {
		return personMapper.get(id);
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int executeWithAutoCommit(final Person entity) {
		/*
		 * 没有生命事务(注解或者手工获取事务)， SqlSessionTemplate 使用自动提交的方式
		 * 即执行一句Statement，提交一句.
		 * 因为自动提交执行一次则提交一次，无法解决多个执行的协调问题，事务的出现解决了这个问题，
		 * 因此事务在实际应用中具有很重要的低位
		 */
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		personMapper.insert(entity2);
		
		return entity.getId();
	}	
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false)
	public int insert(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, timeout = 20)
	public int insertWithTimeout(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public int insertWithException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
	public int insertWithRuntimeException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	//  会匹配自身或子类的，支持完整、简单类名
	@Transactional(readOnly = false, rollbackForClassName  = {"RuntimeException", "IllegalArgumentException", "java.lang.IllegalMonitorStateException"})
	public int insertWithExceptionName(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @param ifThrow
	 * @return
	 * @author qianye.zheng
	 */
	// 
	//  会匹配自身或子类的，支持完整、简单类名
	@Transactional(readOnly = false, noRollbackFor  = {RuntimeException.class})
	//@Transactional(readOnly = false, noRollbackFor  = {RuntimeException.class, IllegalArgumentException.class, IllegalAccessError.class})
	public int insertNoRollbackForException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalArgumentException("发生运行时异常");
		}
		
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @param ifThrow
	 * @return
	 * @author qianye.zheng
	 */
	//  会匹配自身或子类的，支持完整、简单类名
	@Transactional(readOnly = false, noRollbackForClassName  = {"RuntimeException", "IllegalArgumentException", "java.lang.IllegalMonitorStateException"})
	public int insertNoRollbackForExceptionName(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalArgumentException("发生运行时异常");
		}
		
		
		return entity.getId();
	}
	
	
}
