/**
 * 描述: 
 * CoreService.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.service;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreService
 */
public abstract class CoreService {
	
	/* 是否自动提交事务 */
	@Value("${datasource.auto.commit:false}")
	private boolean autoCommit;
	
	@Resource
	private DataSource datasource;
	
	@Resource
	private TransactionFactory transactionFactory;
	
	@Resource
	private SqlSession sqlSession;
	
	/**
	 * 
	 * @description 开启事务
	 * @param level 隔离级别
	 * @return
	 * @author qianye.zheng
	 */
	protected Transaction newTransaction(final TransactionIsolationLevel level) {
		return transactionFactory.newTransaction(datasource, level, autoCommit);
	}
	
	/**
	 * 
	 * @description 提交事务
	 * @param tx
	 * @author qianye.zheng
	 */
	protected void commit(final Transaction tx) {
		try {
			tx.commit();
		} catch (SQLException e) { // 提交异常
			e.printStackTrace();
			try { // 清除 待提任务
				tx.rollback();
			} catch (SQLException e1) { // 回退异常，当前客户端 继续持有 待提交任务
				e1.printStackTrace();
			}
		}
	}
	
}
