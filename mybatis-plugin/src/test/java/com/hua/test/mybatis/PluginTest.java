/**
 * 描述: 
 * PluginTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mybatis;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Collection;

import org.apache.ibatis.session.SqlSession;
//import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.entity.City;
import com.hua.mapper.CityMapper;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;
import com.hua.util.MyBatisUtil;
import com.hua.vo.CityVo;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PluginTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class PluginTest extends BaseTest {

	
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testPlugin() {
		try {
			/**
			 * 拦截的接口:
			 * ParameterHandler
			 * StatementHandler
			 * Executor
			 * ResultSetHandler
			 * 
			 * 修改SQL参数
			 * Executor.update 方法 拦截insert/update/delete
			 * Executor.query 方法拦截 select
			 */
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			Collection<CityVo> collection = cityMapper.multipleBasicNameParam("1", "广州");
			assertTrue(null != collection);
			System.out.println(JacksonUtil.writeAsString(collection));
			
			
			
		} catch (Exception e) {
			log.error("testPlugin =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testParamInterceptor() {
		try {
			/**
			 * 拦截的接口:
			 * ParameterHandler
			 * StatementHandler
			 * Executor
			 * ResultSetHandler
			 * 
			 * 
			 * 
			 * 
			 */
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			Collection<CityVo> collection = cityMapper.multipleBasicNameParam("1", "广州");
			assertTrue(null != collection);
			System.out.println(JacksonUtil.writeAsString(collection));
			
			
			
		} catch (Exception e) {
			log.error("testPlugin =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testExecutorInterceptorInsert() {
		try {
			/**
			 * 拦截的接口:
			 * ParameterHandler
			 * StatementHandler
			 * Executor
			 * ResultSetHandler
			 * 
			 * 
			 * 
			 * 
			 */
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			City city = new City();
			city.setName("桂林市");
			city.setShortName("桂");
			city.setFullName("广西自治区桂林市");
			city.setProvince("广西");
			city.setPostalCode("12345");
			cityMapper.insert(city);
			
			sqlSession.commit();
		} catch (Exception e) {
			log.error("testExecutorInterceptorInsert =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testExecutorInterceptorUpdate() {
		try {
			/**
			 * 拦截的接口:
			 * ParameterHandler
			 * StatementHandler
			 * Executor
			 * ResultSetHandler
			 * 
			 * 
			 * 
			 * 
			 */
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			City city = new City();
			city.setId("12");
			city.setName("桂林市update");
			city.setShortName("桂");
			city.setFullName("广西自治区桂林市2");
			city.setProvince("广西");
			city.setPostalCode("12345");
			cityMapper.update(city);
			
			sqlSession.commit();
		} catch (Exception e) {
			log.error("testExecutorInterceptorUpdate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testExecutorInterceptorSelect() {
		try {
			/**
			 * 拦截的接口:
			 * ParameterHandler
			 * StatementHandler
			 * Executor
			 * ResultSetHandler
			 * 
			 * 
			 * 
			 * 
			 */
			SqlSession sqlSession = MyBatisUtil.getSession();
			
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			//Collection<CityVo> collection = cityMapper.multipleBasicNameParam("1", "广州");
			//Collection<CityVo> collection = cityMapper.multipleBasicNameParam("1", null);
			Collection<CityVo> collection = cityMapper.multipleBasicNameParam(null, "广州");
			assertTrue(null != collection);
			System.out.println(JacksonUtil.writeAsString(collection));
		} catch (Exception e) {
			log.error("testExecutorInterceptorSelect =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
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
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
	}

}
