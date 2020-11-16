/**
 * 描述: 
 * SetParamTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mybatis.param;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.mapper.param.CityMapper;
import com.hua.test.BaseTest;
import com.hua.util.MyBatisUtil;
import com.hua.util.SqlUtil;
import com.hua.vo.param.CityVo;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SetParamTest
 */
public final class SetParamTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSetParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
		} catch (Exception e) {
			log.error("testSetParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleSetNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Set<String> set = new HashSet<String>();
			set.add("2001");
			set.add("湛江");
			set.add(SqlUtil.likeQuery("湛"));
			set.add(SqlUtil.likeQuery("中国广东省"));
			
			Collection<CityVo> vos = cityMapper.singleSetNoNameParam(set);
			
			log.info("testSingleSetNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleSetNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleSetNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Set<String> set = new HashSet<String>();
			set.add("2001");
			set.add("湛江");
			set.add(SqlUtil.likeQuery("湛"));
			set.add(SqlUtil.likeQuery("中国广东省"));
			
			Collection<CityVo> vos = cityMapper.singleSetNameParam(set);
			
			log.info("testSingleSetNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleSetNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleSetNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			CityVo vo1 = new CityVo();
			vo1.setId("2001");
			vo1.setFullName(SqlUtil.likeQuery("中国广东省"));
			
			CityVo vo2 = new CityVo();
			vo2.setId("2002");
			vo2.setFullName(SqlUtil.likeQuery("中国广东省"));			
			
			Collection<CityVo> vos = cityMapper.multipleBeanNoNameParam(vo1, vo2);
			
			log.info("testMultipleSetNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testMultipleSetNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleSetNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			CityVo vo1 = new CityVo();
			vo1.setId("2001");
			vo1.setFullName(SqlUtil.likeQuery("中国广东省"));
			
			CityVo vo2 = new CityVo();
			vo2.setId("2002");
			vo2.setFullName(SqlUtil.likeQuery("中国广东省"));			
			
			Collection<CityVo> vos = cityMapper.multipleBeanNoNameParam(vo1, vo2);
			
			log.info("testMultipleSetNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testMultipleSetNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
