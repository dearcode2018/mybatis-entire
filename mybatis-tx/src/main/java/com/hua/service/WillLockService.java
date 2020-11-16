/**
  * @filename WillLockService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import org.apache.ibatis.session.SqlSession;

import com.hua.entity.City;
import com.hua.mapper.CityMapper;

/**
 * @type WillLockService
 * @description 
 * @author qianye.zheng
 */
public class WillLockService {
	
	
	/**
	 * 
	 * @description 
	 * @param sqlSession
	 * @author qianye.zheng
	 */
	public static void willLockRow1(final SqlSession sqlSession, final City entity) {
		final CityMapper mapper = sqlSession.getMapper(CityMapper.class);
		mapper.update(entity);
		
		System.out.println("do other thing1");
		// 提交
		sqlSession.commit();
	}
	
	/**
	 * 
	 * @description 
	 * @param sqlSession
	 * @author qianye.zheng
	 */
	public static void willLockRow2(final SqlSession sqlSession, final City entity) {
		final CityMapper mapper = sqlSession.getMapper(CityMapper.class);
		mapper.update(entity);
		
		System.out.println("do other thing2");
		// 提交
		sqlSession.commit();
	}
	
	/**
	 * 
	 * @description 
	 * @param sqlSession
	 * @author qianye.zheng
	 */
	public static void willLockTable1(final SqlSession sqlSession, final City entity) {
		
	}
	
	/**
	 * 
	 * @description 
	 * @param sqlSession
	 * @author qianye.zheng
	 */
	public static void willLockTable2(final SqlSession sqlSession, final City entity) {
		
	}
	
}
