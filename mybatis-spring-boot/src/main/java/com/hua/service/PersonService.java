/**
  * @filename PersonService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type PersonService
 * @description 
 * @author qianye.zheng
 */
@Service
public class PersonService {

	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 若不标注@Transactional或使用手工方式创建事务，则MyBatis的 SqlSessionTemplate
	 * 会设置连接的为自动提交: connection.setCommit(true)
	 * 
	 */
	
	/**
	 * 
	 * 描述: 验证事务超时的场景
	 * 结论
	 * 1) 开始事务: 从数据源获取连接对象，然后创建事务对象，开始生命周期
	 * 2) 事务超时定义: 从开启事务开始计时，执行每个 update statement就检查一次当前事务是否超时，超过则抛事务超时异常
	 * 3) 查询方法中，开启事务，执行select statement也会检查超时时间，因此如果是查询，就不要开启事务
	 * 4) 没有事务或者事务的超时时间为无限，才不会出现事务超时的情况
	 * @author qye.zheng
	 * 
	 */
	
	
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
	@Transactional(readOnly = false)
	public int insertTwo(final Person entity) {
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
	 * @param id
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional(readOnly = false)
	public Person selectWithTx(final String id) {
		Person person = personMapper.get(id);
		System.out.println("do other thing...");
		
		return person;
	}
	
	
	/**
	 * 
	 * 描述: 验证事务超时的场景
	 * 结论
	 * 1) 开始事务: 从数据源获取连接对象，然后创建事务对象，开始生命周期
	 * 2) 事务超时定义: 从开启事务开始计时，执行每个 update statement就检查一次当前事务是否超时，超过则抛事务超时异常
	 * 3) 查询方法中，开启事务，执行select statement也会检查超时时间，因此如果是查询，就不要开启事务
	 * 4) 没有事务或者事务的超时时间为无限，才不会出现事务超时的情况
	 * @author qye.zheng
	 * 
	 */
	
	/**
	 * 
	 * @description 
	 * @param id
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Person selectWithoutTx(final String id) {
		Person person = personMapper.get(id);
		System.out.println("do other thing...");
		
		return person;
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
