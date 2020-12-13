/**
 * 描述: 
 * DynamicTableTest.java
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hua.ApplicationStarter;
import com.hua.modular.sys.entity.Person;
import com.hua.modular.sys.mapper.PersonMapper;
import com.hua.test.BaseTest;
import com.hua.util.JacksonUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * DynamicTableTest
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
public final class DynamicTableTest extends BaseTest {

	
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
    public void testDynamicTable() {
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
    //@DisplayName("test")
    @Test
    public void testInsert() {
        try {
            Person entity = new Person();
            entity.setName("广州新港西路-张三");
            entity.setNation("汉族");
            entity.setDynamicTableName("person_0");
            int result = personMapper.insert(entity);
            assertEquals(1, result);
            
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
    public void testDeleteById() {
        try {
            int id = 1;
            Person entity = new Person();
            entity.setDynamicTableName("person_0");
            // 这里的entity的作用主要是为了获取逻辑表名和真实表名
            QueryWrapper<Person> wrapper = Wrappers.query(entity);
            // 根据ID删除，实际场景可以根据具体条件删除
            wrapper.eq(Person.ID, id);
            int result = personMapper.delete(wrapper);
            assertEquals(1, result);
            /*
             * 在分表情况下，实际上不使用这个方法来删除
             * 因为缺少指定动态表名，可通过Wrapper来指定查询条件
             */
            //personMapper.deleteById(1);
            
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
    public void testDeleteByMap() {
        try {
            /*
             * 在分表情况下，实际上不使用这个方法来删除
             * 因为缺少指定动态表名，可通过Wrapper来指定查询条件
             */
            Map<String, Object> param = new HashMap<>();
            // 指定表名 和 查询条件
            param.put(Person.ID, 1);
            // 可以通过传入 逻辑表名和动态表名的方式获取，如果是这样不如采用其他方法
            //param.put(DynamicTableEntity.LOGICAL_TABLE_NAME, "person");
            //param.put(DynamicTableEntity.DYNAMIC_TABLE_NAME, "person_0");
           //personMapper.deleteByMap(param);
            
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
    public void testDelete() {
        try {
            int id = 1;
            // 这里的entity的作用主要是为了获取逻辑表名和真实表名
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            // 根据ID删除，实际场景可以根据具体条件删除
            wrapper.eq(Person.ID, id);
            int result = personMapper.delete(wrapper);
            assertEquals(1, result);
            
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
    public void testDeleteBatchIds() {
        try {
            /*
             * 在分表情况下，实际上不使用这个方法来删除
             * 因为缺少指定动态表名，可通过Wrapper来指定查询条件
             */
            //personMapper.deleteBatchIds(null);
            // 批量可以通过条件方式来执行
            List<Integer> ids = Arrays.asList(1, 3, 10);
            Person entity = new Person();
            entity.setDynamicTableName("person_0");
            // 这里的entity的作用主要是为了获取逻辑表名和真实表名
            QueryWrapper<Person> wrapper = Wrappers.query(entity);
            // 根据ID删除，实际场景可以根据具体条件删除
            wrapper.in(Person.ID, ids);
            int result = personMapper.delete(wrapper);
            assertEquals(1, result);
            
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
    public void testUpdateById() {
        try {
            Person entity = new Person();
            entity.setName("张三new");
            entity.setNation("汉族11");
            entity.setId(1);
            entity.setDynamicTableName("person_0");
            int result = personMapper.updateById(entity);
            assertEquals(1, result);
            
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
    public void testUpdate() {
        try {
            Person entity = new Person();
            entity.setName("张三new2");
            entity.setNation("汉族22");
            entity.setId(1);
            entity.setDynamicTableName("person_0");
            QueryWrapper<Person> wrapper = Wrappers.query();
            wrapper.eq(Person.ID, entity.getId());
            int result = personMapper.update(entity, wrapper);
            assertEquals(1, result);
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
    public void testSelectById() {
        try {
            /*
             * 在分表情况下，实际上不使用这个方法来删除
             * 因为缺少指定动态表名，可通过Wrapper来指定查询条件
             */
            //personMapper.selectById();
            
            int id = 2;
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.eq(Person.ID, id);
            Person person = personMapper.selectOne(wrapper);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
            
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
    public void testSelectBatchIds() {
        try {
            /*
             * 在分表情况下，实际上不使用这个方法来删除
             * 因为缺少指定动态表名，可通过Wrapper来指定查询条件
             */
            //personMapper.selectBatchIds(null);
            // 批量可以通过条件方式来执行
            List<Integer> ids = Arrays.asList(1, 2, 10);
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.in(Person.ID, ids);
            List<Person> list = personMapper.selectList(wrapper);
            Person person = list.get(0);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
            
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
    public void testSelectByMap() {
        try {
            /*
             * 在分表情况下，实际上不使用这个方法来删除
             * 因为缺少指定动态表名，可通过Wrapper来指定查询条件
             */
            // 可以通过传入 逻辑表名和动态表名的方式获取，如果是这样不如采用其他方法
            //param.put(DynamicTableEntity.LOGICAL_TABLE_NAME, "person");
            //param.put(DynamicTableEntity.DYNAMIC_TABLE_NAME, "person_0");
           //personMapper.deleteByMap(param);
            //personMapper.selectByMap(null);
            
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
    public void testSelectOne() {
        try {
            int id = 2;
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.eq(Person.ID, id);
            Person person = personMapper.selectOne(wrapper);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
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
    public void testSelectCount() {
        try {
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.like(Person.NAME, "张三");
            Integer count = personMapper.selectCount(wrapper);
            assertNotNull(count);
            System.out.println(count);
            
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
    public void testSelectList() {
        try {
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.like(Person.NAME, "张三");
            final List<Person> list = personMapper.selectList(wrapper);
            Person person = list.get(0);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
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
    public void testSelectMaps() {
        try {
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.like(Person.NAME, "张三");
            final List<Map<String, Object>> list = personMapper.selectMaps(wrapper);
            Map<String, Object> person = list.get(0);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
            
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
    public void testSelectObjs() {
        try {
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.like(Person.NAME, "张三");
            wrapper.select(Person.NAME);
            // 只返回符合条件所有列的第一个字段，通过select指定返回的字段
            final List<Object> list = personMapper.selectObjs(wrapper);
            Object person = list.get(0);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
            
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
    public void testSelectPage() {
        try {
            // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.like(Person.NAME, "张三");
            // 当前页，从1开始
            int currentPage = 1;
            int pageSize = 5;
            Page<Person> page = new Page<>(currentPage, pageSize);
            // 不执行count
            page.setSearchCount(false);
            final IPage<Person> resultPage = personMapper.selectPage(page, wrapper);
            Person person = resultPage.getRecords().get(0);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
            
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
    public void testSelectMapsPage() {
        try {
         // 指定表名 和 查询条件
            QueryWrapper<Person> wrapper = Wrappers.query(Person.dynamicTable("person_0"));
            wrapper.like(Person.NAME, "张三");
            // 当前页，从1开始
            int currentPage = 1;
            int pageSize = 5;
            Page<Map<String, Object>> page = new Page<>(currentPage, pageSize);
            // 不执行count
            page.setSearchCount(false);
            final IPage<Map<String, Object>> resultPage = personMapper.selectMapsPage(page, wrapper);
            List<Map<String, Object>> list = resultPage.getRecords();
            Object person = list.get(0);
            assertNotNull(person);
            System.out.println(JacksonUtil.writeAsString(person));
            
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
