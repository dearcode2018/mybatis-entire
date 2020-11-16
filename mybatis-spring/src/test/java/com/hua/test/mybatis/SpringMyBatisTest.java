/**
 * 描述: 
 * SpringMyBatisTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mybatis;

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

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ext.Gender;
import com.hua.entity.PagerEntity;
import com.hua.mapper.o2o.PersonMapper;
import com.hua.orm.entity.o2o.Person;
import com.hua.search.bean.o2o.PersonSearch;
import com.hua.test.BaseTest;
import com.hua.util.SpringMybatisUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SpringMyBatisTest
 */
public final class SpringMyBatisTest extends BaseTest {

	@Resource
	private PersonMapper personMapper;
	
	private Person person;
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSpringMyBatis() {
		try {
			 SpringMybatisUtil.getBeanFactoryOfXml();
			
		} catch (Exception e) {
			log.error("testSpringMyBatis =====> ", e);
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
			person = new Person();
			//person.setId(new Random().nextInt() + "");
			person.setGender(Gender.MALE);
			person.setAddress("广州市天河区");
			person.setBirthday(new Date());
			
			personMapper = SpringMybatisUtil.getBeanFactoryOfXml().getBean(PersonMapper.class);
			System.out.println(personMapper );
			personMapper.insert(person);
			
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
			person = new Person();
			person.setId(new Random().nextInt() + "");
			person.setGender(Gender.MALE);
			person.setAddress("广州市天河区");
			person.setBirthday(new Date());
			
			personMapper = SpringMybatisUtil.getBeanFactoryOfXml().getBean(PersonMapper.class);
			String oid = "1066677095";
			personMapper.delete(oid);
			
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
			person = new Person();
			person.setId("2001");
			person.setGender(Gender.MALE);
			person.setAddress("广州市天河区111");
			person.setBirthday(new Date());
			person.setPhotoUrl("http://photo/picture.png");
			personMapper = SpringMybatisUtil.getBeanFactoryOfXml().getBean(PersonMapper.class);
			personMapper.update(person);
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
	public void testGet() {
		try {
			personMapper = SpringMybatisUtil.getBeanFactoryOfXml().getBean(PersonMapper.class);
			String oid = "169350483";
			person =personMapper.get(oid);
			System.out.println(person.toString());
			
			
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSearch() {
		try {
			personMapper = SpringMybatisUtil.getBeanFactoryOfXml().getBean(PersonMapper.class);
			Long oid = 1066677095L;
			PersonSearch search = new PersonSearch();
			search.setAddress("广州市天河区");
			PagerEntity pagerEntity = new PagerEntity(search, 0, 10);
			personMapper.search(pagerEntity);
			
		} catch (Exception e) {
			log.error("testSearch =====> ", e);
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
