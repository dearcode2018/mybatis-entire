/**
 * 描述: 
 * TxPropagationServiceTest.java
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
import com.hua.service.TxPropagationService;
import com.hua.test.BaseTest;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * TxPropagationServiceTest
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
public final class TxPropagationServiceTest extends BaseTest {

	
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
	private TxPropagationService txPropagationService;
	
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
	public void testCallOtherWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callOtherWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallOtherWithoutTx =====> ", e);
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
	public void testCallMandatoryWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callMandatoryWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallMandatoryWithoutTx =====> ", e);
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
	public void testCallOtherWithAutoCommit() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callOtherWithAutoCommit(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallOtherWithAutoCommit =====> ", e);
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
	public void testCallRequiredOccurExceptionWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callRequiredOccurExceptionWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiredOccurExceptionWithTx =====> ", e);
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
	public void testCallRequiredOccurExceptionWithTxWitCatch() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			
			/*
			 * 在同一个事务中，方法调用链中，存在一个方法执行异常，则事务回滚
			 */
			txPropagationService.callRequiredOccurExceptionWithTxWitCatch(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiredOccurExceptionWithTxWitCatch =====> ", e);
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
	public void testCallRequiredOccurExceptionWithCatchWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callRequiredOccurExceptionWithCatchWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiredOccurExceptionWithCatchWithTx =====> ", e);
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
	public void testCallRequiredWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callRequiredWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiredWithTx =====> ", e);
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
	public void testCallRequiredWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callRequiredWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiredWithoutTx =====> ", e);
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
	public void testCallRequiresNewWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callRequiresNewWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiresNewWithTx =====> ", e);
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
	public void testCallRequiresNewWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callRequiresNewWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallRequiresNewWithoutTx =====> ", e);
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
	public void testCallNeverWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callNeverWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallNeverWithTx =====> ", e);
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
	public void testCallNeverWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callNeverWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallNeverWithoutTx =====> ", e);
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
	public void testCallSupportWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callSupportWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallSupportWithTx =====> ", e);
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
	public void testCallSupportWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callSupportWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallSupportWithoutTx =====> ", e);
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
	public void testCallNotSupportedWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callNotSupportedWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallNotSupportedWithTx =====> ", e);
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
	public void testCallNotSupportedWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callNotSupportedWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallNotSupportedWithoutTx =====> ", e);
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
	public void testCallNestedWithTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callNestedWithTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallNestedWithTx =====> ", e);
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
	public void testCallNestedWithoutTx() {
		try {
			Person entity = new Person();
			entity.setName("张三2020");
			entity.setNation("汉族");
			entity.setAddress("广州市海珠区新港西路102号");
			entity.setBirthday(new Date());
			txPropagationService.callNestedWithoutTx(entity);
			assertTrue(null != entity.getId());
			System.out.println("id = " + entity.getId());
			
		} catch (Exception e) {
			log.error("testCallNestedWithoutTx =====> ", e);
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
