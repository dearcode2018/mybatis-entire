/**
  * @filename MultipleDSTxService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type MultipleDSTxService
 * @description 
 * @author qianye.zheng
 */
@Service
public class MultipleDSTxService {

	@Resource
	private MultipleTxSomeService multipleTxSomeService;
	
	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callDB2RequiredWithTx(final Person entity) {
		try {
			final Person entity2 = new Person();
			entity2.setName("李四200023 callDB2RequiredWithTx");
			entity2.setNation("汉族2");
			entity2.setAddress("广州市天河区中山大道中102号");
			entity2.setBirthday(new Date());
			// 下游方法事务声明为REQUIRED，因此会受到调用方的影响，自身声明的数据源并不生效
			multipleTxSomeService.propagationRequiredDB2(entity2);
		} catch (Exception e) {
			e.printStackTrace();
			// 有异常，可以选择不往下执行.也可以继续执行
			return -1;
		}
		// 无异常则继续往下执行
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
	public int callDB2RequiredWithoutTx(final Person entity) {
		try {
			final Person entity2 = new Person();
			entity2.setName("李四200023 callDB2RequiredWithoutTx");
			entity2.setNation("汉族2");
			entity2.setAddress("广州市天河区中山大道中102号");
			entity2.setBirthday(new Date());
			// 调用方没有事务，调用此方法将获取新的数据连接，创建新的事务
			multipleTxSomeService.propagationRequiredDB2(entity2);
		} catch (Exception e) {
			e.printStackTrace();
			// 有异常，可以选择不往下执行.也可以继续执行
			return -1;
		}
		// 无异常则继续往下执行，使用autocommit，返回之后继续使用当前线程上下文指定的数据源
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
	@Transactional
	public int callDB2RequiresNewWithTx(final Person entity) {
		try {
			final Person entity2 = new Person();
			entity2.setName("李四200023 callDB2RequiresNewWithTx");
			entity2.setNation("汉族2");
			entity2.setAddress("广州市天河区中山大道中102号");
			entity2.setBirthday(new Date());
			multipleTxSomeService.propagationRequiresNewDB2(entity2);
		} catch (Exception e) {
			e.printStackTrace();
			// 有异常，可以选择不往下执行.也可以继续执行
			return -1;
		}
		// 无异常则继续往下执行
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
}
