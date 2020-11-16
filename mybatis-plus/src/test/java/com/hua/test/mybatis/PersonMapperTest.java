/**
 * 描述: 
 * PersonMapperTest.java
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

import java.util.Date;

import javax.annotation.Resource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.ApplicationStarter;
import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;
import com.hua.test.BaseTest;
import com.hua.util.StringUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PersonMapperTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
@ExtendWith(SpringExtension.class)
//@WebAppConfiguration(value = "src/main/webapp")
@SpringBootTest(classes = {ApplicationStarter.class}, 
webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@MapperScan(basePackages = {"com.hua.mapper"})
public final class PersonMapperTest extends BaseTest {

	
	/*
	配置方式1: 
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextConfiguration(locations = {
			"classpath:conf/xml/spring-bean.xml", 
			"classpath:conf/xml/spring-config.xml", 
			"classpath:conf/xml/spring-mvc.xml", 
			"classpath:conf/xml/spring-service.xml"
		})
	@ExtendWith(SpringExtension.class)
	
	配置方式2: 	
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextHierarchy({  
		 @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
		 @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
		}) 
	@ExtendWith(SpringExtension.class)
	 */
	
	/**
	 * 而启动spring 及其mvc环境，然后通过注入方式，可以走完 spring mvc 完整的流程.
	 * 
	 */
	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
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
	public void testMapper() {
		try {
			
			Person entity = new Person();
			entity.setName("广州新港西路-张三");
			entity.setNation("汉族");
			//entity.setPhotoUrl("https://photo.icon");
			entity.setBirthday(new Date());
			int result = personMapper.insert(entity);
			assertEquals(1, result);
			
		} catch (Exception e) {
			log.error("testMapper =====> ", e);
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
	public void testInsert() {
		try {
			Person entity = new Person();
			entity.setName("广州新港西路-张三");
			entity.setNation("汉族");
			//entity.setPhotoUrl("https://photo.icon");
			entity.setBirthday(new Date());
			int result = personMapper.insert(entity);
			assertEquals(1, result);
			
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
	//@DisplayName("test")
	@Test
	public void testInsert2() {
		try {
			int count = 20;
			for (int i = 0; i < count; i++) {
				Person entity = new Person();
				entity.setName("广州新港西路-张三");
				entity.setNation("汉族");
				//entity.setPhotoUrl("https://photo.icon");
				entity.setBirthday(new Date());
				int result = personMapper.insert(entity);
				assertEquals(1, result);
			}
			
		} catch (Exception e) {
			log.error("testInsert2 =====> ", e);
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
	public void testUpdateById() {
		try {
			Person entity = new Person();
			entity.setName("广州新港西路-张三2222");
			entity.setNation("汉族1");
			//entity.setPhotoUrl("https://photo.icon");
			entity.setBirthday(new Date());
			entity.setOid(13);
			int result = personMapper.updateById(entity);
			assertTrue(result > 0);
			
		} catch (Exception e) {
			log.error("testUpdateById =====> ", e);
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
	public void testUpdateByCondition() {
		try {
			Person entity = new Person();
			//entity.setName("广州新港西路-张三888");
			entity.setNation("汉族new");
			//entity.setPhotoUrl("https://photo.icon");
			entity.setBirthday(new Date());
			entity.setOid(13);
			QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
			String value = "汉族";
			queryWrapper.and(StringUtil.isNotEmpty(value), x -> x.eq("nation", value));
			int result = personMapper.update(entity, queryWrapper);
			assertTrue(result > 0);
			
		} catch (Exception e) {
			log.error("testUpdateByCondition =====> ", e);
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
	public void testDeleteByCondition() {
		try {
			QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
			String value = "汉族";
			queryWrapper.and(StringUtil.isNotEmpty(value), x -> x.like("nation", "%" + value + "%"));
			int result = personMapper.delete(queryWrapper);
			assertTrue(result > 0);
			
		} catch (Exception e) {
			log.error("testDeleteByCondition =====> ", e);
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
	public void testPage() {
		try {
			QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
			//String value = "汉族";
			String value = "";
			queryWrapper.and(StringUtil.isNotEmpty(value), x -> x.like("nation", "%" + value + "%"));
			int currentPage = 1;
			int limit = 5;
	        //分页对象
	        Page<Person> pageParam = new Page<>(currentPage, limit);
	        //pageParam.setAsc("name", "nation");
	        pageParam.setDesc("name", "nation");
	        IPage<Person> pageResult = personMapper.selectPage(pageParam, queryWrapper);
	        
			System.out.println("size = " + pageResult.getSize() + ", total = " + pageResult.getTotal() + ", pages = " + pageResult.getPages());
			pageResult.getRecords().forEach(x -> System.out.println(x.getName()));
		} catch (Exception e) {
			log.error("testPage =====> ", e);
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
	public void testPageWithoutCount() {
		try {
			QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
			//String value = "汉族";
			String value = "";
			queryWrapper.and(StringUtil.isNotEmpty(value), x -> x.like("nation", "%" + value + "%"));
			int currentPage = 1;
			int limit = 5;
	        //分页对象
	        Page<Person> pageParam = new Page<>(currentPage, limit);
	        /*
	         * 不执行 count，默认是执行
	         * 在某些场景下，比如执行任务，并不关心count，为了提高效率，不执行count.
	         */
	        pageParam.setSearchCount(false);
	        //pageParam.setAsc("name", "nation");
	        pageParam.setDesc("name", "nation");
	        IPage<Person> pageResult = personMapper.selectPage(pageParam, queryWrapper);
	        
			System.out.println("size = " + pageResult.getSize() + ", total = " + pageResult.getTotal() + ", pages = " + pageResult.getPages());
			pageResult.getRecords().forEach(x -> System.out.println(x.getName()));
		} catch (Exception e) {
			log.error("testPageWithoutCount =====> ", e);
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
	public void testPage2() {
		try {
			QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
			//String value = "汉族";
			String value = "";
			queryWrapper.and(StringUtil.isNotEmpty(value), x -> x.like("nation", "%" + value + "%"));

			//
			long currentPage = 4;
			long limit = 10;
	        //分页对象
	        Page<Person> pageParam = new Page<>(currentPage, limit);
	        //pageParam.setAsc("name");
	        //pageParam.setDesc("name", "nation");
	        IPage<Person> pageResult = personMapper.selectPage(pageParam, queryWrapper);
	        
			System.out.println("size = " + pageResult.getSize() + ", total = " + pageResult.getTotal() + ", pages = " + pageResult.getPages());
			pageResult.getRecords().forEach(x -> System.out.println(x.getName()));
		} catch (Exception e) {
			log.error("testPage2 =====> ", e);
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
