/**
 * 描述: 
 * CityExtendTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mybatis.extend;

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

import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.mapper.extend.CityExtendMapper;
import com.hua.orm.entity.extend.CityExtend;
import com.hua.test.BaseTest;
import com.hua.util.MyBatisUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CityExtendTest
 */
public final class CityExtendTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCityExtend() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			
		} catch (Exception e) {
			log.error("testCityExtend =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetByte() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			// 返回值类型，无法确定，byte[] Blob Clob 均不支持
			//Clob data = cityMapper.getByte("广东省广州市海珠区", "123");
			
			String value = cityMapper.getHex("广东省广州市海珠区", "123");
			System.out.println(value);
		} catch (Exception e) {
			log.error("testGetByte =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetFullObject() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			
			String id = "2001";
			
			CityExtend full = cityMapper.getFullObject(id);
			
			System.out.println(full.toString());
			
		} catch (Exception e) {
			log.error("testGetFullObject =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAddColumn() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			Map<String, String> param = new HashMap<String, String>();
			
			String tableName = "city_extend_ext";
			String columName = "txt14";
			String dataType = "VARCHAR(200)";
			param.put("tableName", tableName);
			param.put("columName", columName);
			param.put("dataType", dataType);
			
			cityMapper.addColumn(param);
			
			// 有些数据库 ddl 也需要主动提交事务，例如 sqlserver
			sqlSession.commit();
			
		} catch (Exception e) {
			log.error("testAddColumn =====> ", e);
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
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			CityExtend city = new CityExtend();
			city.setId(UUID.randomUUID().toString());
			city.setName("中山市");
			city.setShortName("中山");
			city.setFullName("中国广东省中山市");
			city.setPostalCode("525862");
			city.setProvince("广东");
			city.getExt().put("id", UUID.randomUUID().toString());
			city.getExt().put("cityId", city.getId());
			//city.getExt().put("txt1", "中国广东省中山市，欢迎您1!");
			//city.getExt().put("txt2", "中国广东省中山市，欢迎您2!");
			
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
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			CityExtend city = new CityExtend();
			
			cityMapper.delete("22398d50-f519-4911-bf02-c5f36d24bd0c");
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
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			CityExtend city = new CityExtend();
			
			city.setId("61f504af-790c-4835-b15e-ffdfc049cad9");
			city.setName("中山市1");
			city.setShortName("中山1");
			city.setFullName("中国广东省中山市1");
			city.setPostalCode("525862");
			city.setProvince("广东");
			city.getExt().put("cityId", city.getId());
			city.getExt().put("txt1", "中国广东省中山市，欢迎您12222!");
			city.getExt().put("txt2", "中国广东省中山市，欢迎您222222afasf!");
			
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
	public void test() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityExtendMapper cityMapper = sqlSession.getMapper(CityExtendMapper.class);
			
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
