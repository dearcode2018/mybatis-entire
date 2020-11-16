/**
 * 描述: 
 * BaisicParamTest.java
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
 * BaisicParamTest
 */
public final class BaisicParamTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBaisicParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			log.info("testBaisicParam =====> " + sqlSession);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			log.info("testBaisicParam =====> " + cityMapper);
			
			
		} catch (Exception e) {
			log.error("testBaisicParam =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleBasicNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			String id = "2001";
			CityVo vo = cityMapper.singleBasicNoNameParam(id);
			
			System.out.println(vo.getFullName());
			
		} catch (Exception e) {
			log.error("testSingleBasicNoNameParam =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleBasicNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			String id = "2001";
			CityVo vo = cityMapper.singleBasicNameParam(id);
			
			System.out.println(vo.getFullName());
			
		} catch (Exception e) {
			log.error("testSingleBasicNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleBasicNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			String id = null;
			String name = null;
			String shortName = null;
			String fullName = "中国广东省";
			fullName = SqlUtil.likeQuery(fullName);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			Collection<CityVo> vos = cityMapper.multipleBasicNoNameParam(id, name, shortName, fullName);
			
			log.info("testMultipleBasicNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testMultipleBasicNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleBasicNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			String id = null;
			String name = null;
			String shortName = null;
			String fullName = "中国广东省";
			fullName = SqlUtil.likeQuery(fullName);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			Collection<CityVo> vos = cityMapper.multipleBasicNameParam(id, name, shortName, fullName);
			log.info("testMultipleBasicNameParam =====> " + vos.size());
		} catch (Exception e) {
			log.error("testMultipleBasicNameParam =====> ", e);
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
