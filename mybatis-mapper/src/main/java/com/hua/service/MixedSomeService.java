/**
  * @filename MixedSomeService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type MixedSomeService
 * @description 
 * @author qianye.zheng
 */
@Service
public class MixedSomeService {

	
	@Resource
	private PlatformTransactionManager platformTransactionManager;
	
	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 
	 * @description 纯手工事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void manualTxMandatory(final Person entity) {
		
		// 事务定义
		final DefaultTransactionDefinition definetion = new DefaultTransactionDefinition();
		definetion.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definetion.setName("事务名称a123");
		definetion.setReadOnly(false);
		// 事务超时时间: 创建事务
		definetion.setTimeout(10);
		definetion.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);
		
		/*
		 * 在事务开启之前，执行一下，走的自动提交
		 */
		personMapper.insert(entity);
		
		// 开始事务
		final TransactionStatus txStatus = platformTransactionManager.getTransaction(definetion);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023手动提交");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		personMapper.insert(entity2);
		
		// 提交事务
		platformTransactionManager.commit(txStatus);
		
		// 回滚事务
		//platformTransactionManager.rollback(txStatus);
		
	}
	
	
}
