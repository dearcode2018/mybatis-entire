/**
 * 描述: 
 * MapParamTest.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.mapper.CityMapper;
import com.hua.test.BaseTest;
import com.hua.util.MyBatisUtil;
import com.hua.util.SqlUtil;
import com.hua.vo.CityVo;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * MapParamTest
 */
public final class MapParamTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMapParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
		} catch (Exception e) {
			log.error("testMapParam =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleMapNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", "2001");
			map.put("fullName", SqlUtil.likeQuery("中国广东省"));

			Collection<CityVo> vos = cityMapper.singleMapNoNameParam(map);
			
			log.info("testSingleMapNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleMapNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleMapNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", "2001");
			map.put("fullName", SqlUtil.likeQuery("中国广东省"));

			Collection<CityVo> vos = cityMapper.singleMapNameParam(map);
			
			log.info("testSingleMapNameParam =====> " + vos.size());
		} catch (Exception e) {
			log.error("testSingleMapNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleMapAllNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Map<String, Object> map = new HashMap<String, Object>();
			String[] idArray = new String[] {"2001", "2002"};
			// 放入数组
			map.put("idArray", idArray);
			
			List<String> provinceList = new ArrayList<String>();
			provinceList.add("广东");
			provinceList.add("上海");
			// 放入 List
			map.put("provinceList", provinceList);
			CityVo vo = new CityVo();
			vo.setShortName("湛");
			// 放入 bean
			map.put("vo", vo);
			
			// 放入 基本类型的值
			map.put("name", "湛江");
			
			Collection<CityVo> vos = cityMapper.singleMapAllNoNameParam(map);
			
			log.info("testSingleMapAllNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleMapAllNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSingleMapAllNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			String[] idArray = new String[] {"2001", "2002"};
			// 放入数组
			map.put("idArray", idArray);
			
			List<String> provinceList = new ArrayList<String>();
			provinceList.add("广东");
			provinceList.add("上海");
			// 放入 List
			map.put("provinceList", provinceList);
			CityVo vo = new CityVo();
			vo.setShortName("湛");
			// 放入 bean
			map.put("vo", vo);
			
			// 放入 基本类型的值
			map.put("name", "湛江");
			
			Collection<CityVo> vos = cityMapper.singleMapAllNameParam(map);
			
			log.info("testSingleMapAllNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testSingleMapAllNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleMapNoNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("id", "2001");
			map1.put("fullName", SqlUtil.likeQuery("中国广东省"));
	
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("id", "2002");
			map2.put("fullName", SqlUtil.likeQuery("中国广东省"));
			
			Collection<CityVo> vos = cityMapper.multipleMapNoNameParam(map1, map2);	
			
			log.info("testMultipleMapNoNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testMultipleMapNoNameParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMultipleMapNameParam() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("id", "2001");
			map1.put("fullName", SqlUtil.likeQuery("中国广东省"));
	
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("id", "2002");
			map2.put("fullName", SqlUtil.likeQuery("中国广东省"));
			
			Collection<CityVo> vos = cityMapper.multipleMapNameParam(map1, map2);
			
			log.info("testMultipleMapNameParam =====> " + vos.size());
			
		} catch (Exception e) {
			log.error("testMultipleMapNameParam =====> ", e);
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
