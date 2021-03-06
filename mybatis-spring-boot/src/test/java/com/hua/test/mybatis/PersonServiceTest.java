/**
 * 描述: 
 * PersonServiceTest.java
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

import com.hua.ApplicationStarter;
import com.hua.entity.Person;
import com.hua.service.PersonService;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * PersonServiceTest
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
public final class PersonServiceTest extends BaseTest {

	
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
	private PersonService personService;
	
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
	public void testExecuteWithAutoCommit() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			personService.executeWithAutoCommit(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testExecuteWithAutoCommit =====> ", e);
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
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			personService.insert(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
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
	public void testInsertWithRuntimeException() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			//personService.insertWithRuntimeException(entity, false);
			personService.insertWithRuntimeException(entity, true);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testInsertWithRuntimeException =====> ", e);
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
	public void testInsertWithException() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			//personService.insertWithRuntimeException(entity, false);
			personService.insertWithException(entity, true);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testInsertWithException =====> ", e);
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
	public void testInsertWithExceptionName() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			personService.insertWithExceptionName(entity, true);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testInsertWithExceptionName =====> ", e);
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
	public void testInsertNoRollbackForForException() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			personService.insertNoRollbackForException(entity, true);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testInsertNoRollbackForException =====> ", e);
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
	public void testInsertNoRollbackForExceptionName() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			personService.insertNoRollbackForExceptionName(entity, true);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testInsertNoRollbackForExceptionName =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 验证事务超时的场景
	 * 结论
	 * 1) 开始事务: 从数据源获取连接对象，然后创建事务对象，开始生命周期
	 * 2) 事务超时定义: 从开启事务开始计时，执行每个 update statement就检查一次当前事务是否超时，超过则抛事务超时异常
	 * 3) 查询方法中，开启事务，执行select statement也会检查超时时间，因此如果是查询，就不要开启事务
	 * 4) 没有事务或者事务的超时时间为无限，才不会出现事务超时的情况
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testInsertTwo() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			personService.insertTwo(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
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
	public void testSelectWithTx() {
		try {
			Person person = personService.selectWithTx("3");
			System.out.println(JacksonUtil.writeAsString(person));
			
		} catch (Exception e) {
			log.error("testSelectWithTx =====> ", e);
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
	public void testSelectWithoutTx() {
		try {
			Person person = personService.selectWithoutTx("3");
			System.out.println(JacksonUtil.writeAsString(person));
			
		} catch (Exception e) {
			log.error("testSelectWithoutTx =====> ", e);
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
