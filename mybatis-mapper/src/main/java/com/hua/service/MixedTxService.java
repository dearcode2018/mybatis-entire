/**
  * @filename MixedTxService.java
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type MixedTxService
 * @description 混合事务
 * @author qianye.zheng
 */
@Service
public class MixedTxService {

	@Resource
	private PersonMapper personMapper;
	
	@Resource
	private PlatformTransactionManager platformTransactionManager;
	
	@Resource
	private MixedSomeService mixedSomeService;
	
	/**
	 * 结论:
	 * 手工开始的事务，也受外部的影响，因此为了排除外部事务的影响，
	 * 将事务定义设置开启新的事务，这样程序比较健壮
	 * definetion.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	 * 
	 */
	
	/**
	 * 
	 * @description 纯手工事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void manualTx(final Person entity) {
		
		// 事务定义
		final DefaultTransactionDefinition definetion = new DefaultTransactionDefinition();
		definetion.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definetion.setName("事务名称a123");
		definetion.setReadOnly(false);
		/*
		 * 事务超时时间: 创建事务之后，距离第一次执行update statement之间的最大时间
		 * Transaction timed out: deadline was Wed Jun 17 15:48:24 CST 2020
		 * select statement不参与这个计时
		 * 
		 * 不设置则用全局的值
		 */
		//definetion.setTimeout(11);
		definetion.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
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
	
	/**
	 * 
	 * @description 事务注解 + 手工事务
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void manualTxRequiredWithTxAnno(final Person entity) {
		// 事务定义
		final DefaultTransactionDefinition definetion = new DefaultTransactionDefinition();
		definetion.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definetion.setName("事务名称a123");
		definetion.setReadOnly(false);
		// 事务超时时间: 创建事务
		definetion.setTimeout(10);
		/*
		 * 是否使用的是注解标注的事务，但该事务不能由程序去控制
		 * 而是被托管了。因此 platformTransactionManager.commit(txStatus); 是无效的
		 * 
		 * newSynchronization = false
		 * newTransaction = false
		 * 
		 * 
		 */
		definetion.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		/*
		 * 在事务开启之前，执行一下，走的方法级声明的事务
		 * 当前方法体正常执行完成后，才提交事务
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
		
		System.out.println("MixedTxService.manualTxRequiredWithTxAnno()");
		
		// 回滚事务
		//platformTransactionManager.rollback(txStatus);
	}
	
	/**
	 * 
	 * @description 事务注解 + 手工事务
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void manualTxRequiresNewWithTxAnno(final Person entity) {
		// 事务定义
		final DefaultTransactionDefinition definetion = new DefaultTransactionDefinition();
		definetion.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definetion.setName("事务名称a123");
		definetion.setReadOnly(false);
		// 事务超时时间: 创建事务
		definetion.setTimeout(10);
		/*
		 * 创建新的事务，不使用方法级声明的事务
		 * platformTransactionManager.commit(txStatus); 可以提交此事务
		 * 
		 * newSynchronization = true
		 * newTransaction = true
		 */
		definetion.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		
		/*
		 * 在事务开启之前，执行一下，走的方法级声明的事务
		 * 当前方法体正常执行完成后，才提交事务
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
	
	/**
	 * 
	 * @description 事务注解
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void manualTxNeverWithTxAnno(final Person entity) {
		// 事务定义
		final DefaultTransactionDefinition definetion = new DefaultTransactionDefinition();
		definetion.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definetion.setName("事务名称a123");
		definetion.setReadOnly(false);
		// 事务超时时间: 创建事务
		definetion.setTimeout(10);
		/*
		 * 会因为方法级声明的事务而出现异常
		 * 
		 * : Existing transaction found for transaction marked with propagation 'never'
		 * 
		 */
		definetion.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
		
		/*
		 * 在事务开启之前，执行一下，走的方法级声明的事务
		 * 当前方法体正常执行完成后，才提交事务
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
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @author qianye.zheng
	 */
	public void callManualTxMandatoryWithoutTx(final Person entity) {
		/*
		 * 在事务开启之前，执行一下，走的自动提交
		 */
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023手动提交");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		personMapper.insert(entity2);
		// 无事务的方式去调用，会导致下游方法异常
		mixedSomeService.manualTxMandatory(entity2);
	}
	
}
