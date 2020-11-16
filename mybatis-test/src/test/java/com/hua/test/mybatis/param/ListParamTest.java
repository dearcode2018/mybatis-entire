/**
 * 描述: 
 * ListParamTest.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
 * ListParamTest
 */
public final class ListParamTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testListParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
		} catch (Exception e) {
			log.error("testListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleListNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			List<String> list = new ArrayList<String>();
			list.add("2001");
			list.add("湛江");
			list.add(SqlUtil.likeQuery("湛"));
			list.add(SqlUtil.likeQuery("中国广东省"));
			Collection<CityVo> vos = cityMapper.singleListNoNameParam(list);
			log.info("testSingleListNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleListNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleListNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			List<String> list = new ArrayList<String>();
			list.add("2001");
			list.add("湛江");
			list.add(SqlUtil.likeQuery("湛"));
			list.add(SqlUtil.likeQuery("中国广东省"));
			Collection<CityVo> vos = cityMapper.singleListNameParam(list);
			log.info("testSingleListNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleListNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleListNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			List<String> list1 = new ArrayList<String>();
			list1.add("2001");
			list1.add("湛江");
			list1.add(SqlUtil.likeQuery("湛"));
			list1.add(SqlUtil.likeQuery("中国广东省"));
			
			List<String> list2 = new ArrayList<String>();
			list2.add("2002");
			list2.add("深圳");
			list2.add(SqlUtil.likeQuery("深"));
			list2.add(SqlUtil.likeQuery("中国广东省"));
			Collection<CityVo> vos = cityMapper.multipleListNoNameParam(list1, list2);
			
			log.info("testMultipleListNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testMultipleListNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleListNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			List<String> list1 = new ArrayList<String>();
			list1.add("2001");
			list1.add("湛江");
			list1.add(SqlUtil.likeQuery("湛"));
			list1.add(SqlUtil.likeQuery("中国广东省"));
			
			List<String> list2 = new ArrayList<String>();
			list2.add("2002");
			list2.add("深圳");
			list2.add(SqlUtil.likeQuery("深"));
			list2.add(SqlUtil.likeQuery("中国广东省"));
			Collection<CityVo> vos = cityMapper.multipleListNameParam(list1, list2);
			
			log.info("testMultipleListNameParam =====> " + vos.size());			
		} catch (Exception e) {
			log.error("testMultipleListNameParam =====> ", e);
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
