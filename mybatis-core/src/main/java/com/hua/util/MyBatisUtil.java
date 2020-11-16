/**
 * 
 * 描述: 
 * @author  qye.zheng
 * MyBatisUtil.java
 * version 1.0
 */

package com.hua.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * 描述: MyBatis - 工具类
 * @author  qye.zheng
 * MyBatisUtil
 */
public final class MyBatisUtil {
	
	/*
	 注意，这种方式下类路径不能以/开头 
	 */
	/* 配置文件路径 */
	private static final String PATH = "conf/xml/config/mybatis-config.xml";
	
	/* session 工厂 */
	private static SqlSessionFactory factory;
	
	static
	{
		try {
			final Reader reader = Resources.getResourceAsReader(PATH);
			final SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(reader);
			System.out.println("sqlSessionFactory has been build...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author qye.zheng
	 * 
	 */
	private MyBatisUtil()
	{
	}

	/**
	 * 
	 * 描述: 获取 sqlSession
	 * 每次调用都返回不同的sqlSession对象
	 *  factory.openSession() 返回原生对象
	 * @author qye.zheng
	 * @return
	 */
	public static SqlSession getSession() {
		final SqlSession sqlSession = factory.openSession();
		System.out.println("sqlSession: " + sqlSession + "  open successfully");
		
		return sqlSession;
	}
	
	/**
	 * 
	 * 描述: 关闭 sqlSession
	 * @author qye.zheng
	 * 
	 * @param sqlSession
	 */
	public static void closeSession(final SqlSession sqlSession) {
		// 为空，直接结束
		if (null == sqlSession) {
			System.out.println("closeSession =====> target sqlSession is null");
			
			return;
		}
		// 关闭指定的会话
		sqlSession.close();
		System.out.println("closeSession =====> sqlSession: " + sqlSession  + " close successfully");
	}
	
}
