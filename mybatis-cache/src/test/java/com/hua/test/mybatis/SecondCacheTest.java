/**
 * 描述: 
 * SecondCacheTest.java
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
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.mapper.CityMapper;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;
import com.hua.util.MyBatisUtil;
import com.hua.vo.CityVo;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SecondCacheTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class SecondCacheTest extends BaseTest {

	/**
	 * MyBatis缓存
	 * 一级缓存: 
	 * 1) 默认开启，在相同查询条件下，直接查询缓存，范围是SESSION.
	 * 2) SqlSession.close() 会释放掉一级缓存PerpetualCache对象，一级缓存将不可用
	 * 3) SqlSession.clearCache() 清空PerpetualCache对象中的数据，但该对象仍然可用，再次查询会查询数据库.
	 * 
	 * 
	 * 
	 * 二级缓存: 
	 * 1) 手动开启，具体到指定Mapper
	 * 2) 一级缓存失效后，可以使用二级缓存，二级缓存不存在再到数据库查询
	 * 3) 可以通过具体语句的flushCache=true，来刷新二级缓存. select方法默认是false，UpdateSQL默认是true
	 * 4) 主动刷新缓存: 在缓存时间较长，而对数据变更要尽早感知时，可以通过传入无关参数来改变查询条件，
	 * 从而达到刷新缓存的目的. 参考方案: 		where语句<if test="null != random">0 != #{random}</if>
	 * 第一: 在无需刷新缓存时，传入一个和之前查询一样的值，再需要刷新时，传入一个和之前不一样的随机或自增值.
	 * 第二: 配合缓存时间，在短时间内，数据变更可以忽略的 则继续用缓存，过期了再刷新缓存，再者在数据变更较大或
	 * 明显的情况下，可以主动去刷新缓存.
	 * -》 数据变更频率低，缓存时间长，需根据变更信号主动刷新
	 * -》 数据更变频率高，缓存时间短，无需维护变更信号
	 * 5) 不使用二级缓存
	 * 在大量数据缓存时，如果避免使用缓存，例如: 在页面导出功能中，要导出几十万条数据，此时的数据就不应该缓存
	 * 6) 
	 * 
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSecondLevelCache() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			log.info("testBaisicParam =====> " + sqlSession);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			assertTrue(null != cityMapper);
			CityVo vo = new CityVo();
			vo.setFullName("中国广东省");
			Collection<CityVo> vos = cityMapper.singleBeanNameParam(vo);
			
			// 清空PerpetualCache对象中的数据，但该对象仍然可用，若二级缓存不存在则再次查询会查询数据库.
			//sqlSession.clearCache();
			// SqlSession关闭之后，在这个场景下下一次使用二级缓存才有效
			sqlSession.close();
			
			// 重新获取会话
			sqlSession = MyBatisUtil.getSession();
			// 重新获取Mapper
			cityMapper = sqlSession.getMapper(CityMapper.class);
			// 改变查询条件
			vo.setFullName("广东省");
			// Cache Hit Ratio [com.hua.mapper.CityMapper]: 0.5
			cityMapper.singleBeanNameParam(vo);
			
			cityMapper.singleBeanNameParam(vo);
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 一级缓存失效之后，查询二级缓存
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSecondLevelCacheWithExpireFirstLevel() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			log.info("testBaisicParam =====> " + sqlSession);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			assertTrue(null != cityMapper);
			CityVo vo = new CityVo();
			vo.setFullName("中国广东省");
			List<CityVo> vos = cityMapper.singleBeanNameParam(vo);
			
			// 清空PerpetualCache对象中的数据，但该对象仍然可用，若二级缓存不存在则再次查询会查询数据库.
			//sqlSession.clearCache();
			// SqlSession关闭之后，在这个场景下下一次使用二级缓存才有效
			sqlSession.close();
			
			/*
			 * 	<cache eviction="LRU" flushInterval="10000" readOnly="true" size="500" />
			 * readOnly = true，可以修改缓存的数据，false-不能修改，默认false.
			 */
			vos.remove(0);
			vos.get(0).setFullName("modify xxx");
			
			// 重新获取会话
			sqlSession = MyBatisUtil.getSession();
			// 重新获取Mapper
			cityMapper = sqlSession.getMapper(CityMapper.class);
			vo.setName("chang hahha");
			// 改变查询条件
			//vo.setFullName("广东省");
			// Cache Hit Ratio [com.hua.mapper.CityMapper]: 0.5
			vos = cityMapper.singleBeanNameParam(vo);
			for (CityVo e : vos) {
				System.out.println(JacksonUtil.writeAsString(e));
			}
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 一级缓存失效之后，查询二级缓存
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSecondLevelCacheChange() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			log.info("testBaisicParam =====> " + sqlSession);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			assertTrue(null != cityMapper);
			CityVo vo = new CityVo();
			//vo.setFullName("中国广东省");
			Collection<CityVo> vos = cityMapper.singleBeanNameParam(vo);
			
			// 清空PerpetualCache对象中的数据，但该对象仍然可用，若二级缓存不存在则再次查询会查询数据库.
			//sqlSession.clearCache();
			// SqlSession关闭之后，在这个场景下下一次使用二级缓存才有效
			sqlSession.close();
			
			// 重新获取会话
			sqlSession = MyBatisUtil.getSession();
			// 重新获取Mapper
			cityMapper = sqlSession.getMapper(CityMapper.class);
			CityVo vo2 = new CityVo();
			// 改变查询条件
			vo2.setFullName("广东省");
			// Cache Hit Ratio [com.hua.mapper.CityMapper]: 0.5
			cityMapper.singleBeanNameParam(vo2);
			
			//cityMapper.singleBeanNameParam(vo2);
			
			cityMapper.feignUpdate();
			cityMapper.singleBeanNameParam(vo);
			cityMapper.singleBeanNameParam(vo2);
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 手动刷新缓存: 通过改变业务无关的入参实现
	 *主动刷新缓存: 在缓存时间较长，而对数据变更要尽早感知时，可以通过传入无关参数来改变查询条件，
	 * 从而达到刷新缓存的目的. 参考方案: 			<if test="null != random">0 != #{random}</if>
	 * 在无需刷新缓存时，传入一个和之前查询一样的值，再需要刷新时，传入一个和之前不一样的随机或自增值.
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testManualFlushCache() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			log.info("testBaisicParam =====> " + sqlSession);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			assertTrue(null != cityMapper);
			CityVo vo = new CityVo();
			vo.setFullName("中国广东省");
			vo.setRandom(1);
			Collection<CityVo> vos = cityMapper.singleBeanNameParam(vo);
			
			// 清空PerpetualCache对象中的数据，但该对象仍然可用，若二级缓存不存在则再次查询会查询数据库.
			//sqlSession.clearCache();
			// SqlSession关闭之后，在这个场景下下一次使用二级缓存才有效
			sqlSession.close();
			
			// 重新获取会话
			sqlSession = MyBatisUtil.getSession();
			// 重新获取Mapper
			cityMapper = sqlSession.getMapper(CityMapper.class);
			/*
			 * 主动改变查询条件，传入一个和之前查询不一样的值，一般为随机值或者自增值，
			 * 在缓存的生命周期内，传入不重复的值即可.
			 */
			vo.setRandom(1);
			// Cache Hit Ratio [com.hua.mapper.CityMapper]: 0.5
			cityMapper.singleBeanNameParam(vo);
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 一级缓存失效之后，查询二级缓存
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testRefreshCacheWithFeignUpdate() {
		try {
			SqlSession sqlSession = MyBatisUtil.getSession();
			log.info("testBaisicParam =====> " + sqlSession);
			CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
			assertTrue(null != cityMapper);
			CityVo vo = new CityVo();
			vo.setFullName("中国广东省");
			Collection<CityVo> vos = cityMapper.singleBeanNameParam(vo);
			
			// 清空PerpetualCache对象中的数据，但该对象仍然可用，若二级缓存不存在则再次查询会查询数据库.
			//sqlSession.clearCache();
			// SqlSession关闭之后，在这个场景下下一次使用二级缓存才有效
			sqlSession.close();
			
			// 重新获取会话
			sqlSession = MyBatisUtil.getSession();
			// 重新获取Mapper
			cityMapper = sqlSession.getMapper(CityMapper.class);
			
			// 改变查询条件
			//vo.setFullName("广东省");
			// Cache Hit Ratio [com.hua.mapper.CityMapper]: 0.5
			
			// 执行假的更新，刷新缓存
			cityMapper.feignUpdate();
			
			cityMapper.singleBeanNameParam(vo);
			
			//cityMapper.singleBeanNameParam(vo);
			
			
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
