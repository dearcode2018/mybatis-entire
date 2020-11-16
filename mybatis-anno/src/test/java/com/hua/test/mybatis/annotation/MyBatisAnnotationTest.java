/**
 * 描述: 
 * MyBatisAnnotationTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mybatis.annotation;

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

import com.hua.entity.City;
import com.hua.mapper.CityMapper;
import com.hua.test.BaseTest;
import com.hua.util.MyBatisUtil;
import com.hua.util.SqlUtil;
import com.hua.vo.CityVo;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * MyBatisAnnotationTest
 */
public final class MyBatisAnnotationTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMyBatisAnnotation() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			log.info("testMyBatisAnnotation =====> " + cityMapper);
			
		} catch (Exception e) {
			log.error("testMyBatisAnnotation =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsert() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			City city = new City();
			city.setId("2003");
			city.setName("桂林");
			city.setShortName("桂");
			city.setFullName("广西自治区桂林市");
			city.setProvince("广西");
			city.setPostalCode("5286956");
			
			cityMapper.insert(city);
			
			sqlSession.commit();
			
		} catch (Exception e) {
			log.error("testInsert =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);			
			String id = "2003";
			cityMapper.delete(id);
			
			sqlSession.commit();
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpdate() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);		
			
			City city = new City();
			city.setId("2003");
			city.setName("桂林2");
			city.setShortName("桂");
			city.setFullName("广西自治区桂林市");
			city.setProvince("广西");
			city.setPostalCode("5286956");
			cityMapper.update(city);
			
			sqlSession.commit();
		} catch (Exception e) {
			log.error("testUpdate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSelect() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);		
			String id = "2001";
			String name = null;
			String shortName = null;
			String fullName = "中国广东省";
			
			 Collection<CityVo> vos = cityMapper.get(id, name, shortName, fullName);
			
			 log.info("testSelect =====> " + vos.size());
			 
		} catch (Exception e) {
			log.error("testSelect =====> ", e);
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
