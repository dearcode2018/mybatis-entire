/**
 * 描述: 
 * CollegeStudentMapperTest.java
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hua.entity.CollegeStudent;
import com.hua.mapper.auto.CollegeStudentMapper;
import com.hua.test.BaseTest;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CollegeStudentMapperTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
// for Junit 5.x
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
		"classpath:conf/spring-config.xml", 
		"classpath:conf/spring-mybatis.xml", 
		})
public final class CollegeStudentMapperTest extends BaseTest {

	
	/**
	 * 查询
	 * 等号条件: 直接使用实体构造多个条件，并且关系
	 * 
	 * Example条件: 与或非 like between in 查询
	 * 
	 */
	
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
	private CollegeStudentMapper collegeStudentMapper;
	
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
	public void testSelectByExample() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
			
		} catch (Exception e) {
			log.error("testSelectByExample =====> ", e);
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
	public void testSelectByPrimaryKey() {
		try {
			Integer key = 2;
			CollegeStudent e = collegeStudentMapper.selectByPrimaryKey(key);
			System.out.println(e.toString());
			
		} catch (Exception e) {
			log.error("testSelectByPrimaryKey =====> ", e);
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
	public void testSelectAll() {
		try {
			List<CollegeStudent> entities = collegeStudentMapper.selectAll();
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
			
		} catch (Exception e) {
			log.error("testSelectAll =====> ", e);
		}
	}

	/**
	 * 
	 * 描述:  根据实体中的属性值进行查询，查询条件使用等号
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSelect() {
		try {
			//  根据实体中的属性值进行查询，查询条件使用等号
			CollegeStudent entity = new CollegeStudent();
			//entity.setName("赵龙");
			entity.setType((byte) 1);
			List<CollegeStudent> entities = collegeStudentMapper.select(entity);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testSelect =====> ", e);
		}
	}		
	
	/**
	 * 分页查询
	 * 描述: 根据实体属性和RowBounds进行分页查询 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSelectByRowBounds() {
		try {
			// 根据实体属性和RowBounds进行分页查询
			CollegeStudent entity = new CollegeStudent();
			//entity.setName("赵龙");
			entity.setType((byte) 1);
			
			int pageNo = 1;
			int pageSize = 2;
			
			int offset = (pageNo - 1) * pageSize;
			
			RowBounds rowBounds = new RowBounds(offset, pageSize);
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByRowBounds(entity, rowBounds);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
			
		} catch (Exception e) {
			log.error("testSelectByRowBounds =====> ", e);
		}
	}		
	
	/**
	 * 分页查询
	 * 描述: 根据example条件和RowBounds进行分页查询
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSelectByExampleAndRowBounds() {
		try {
			// 根据example条件和RowBounds进行分页查询
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			int pageNo = 1;
			int pageSize = 2;
			int offset = (pageNo - 1) * pageSize;
			RowBounds rowBounds = new RowBounds(offset, pageSize);
	
			List<CollegeStudent> entities = collegeStudentMapper.selectByExampleAndRowBounds(example, rowBounds);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testSelectByExampleAndRowBounds =====> ", e);
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
			// 根据实体属性和RowBounds进行分页查询
			CollegeStudent entity = new CollegeStudent();
			//entity.setName("赵龙");
			entity.setType((byte) 1);
			Integer count = collegeStudentMapper.selectCount(entity);
			System.out.println("count = " + count);
			
		} catch (Exception e) {
			log.error("testSelectCount =====> ", e);
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
	public void testSelectCountByExample() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 指定 count的字段
			example.setCountProperty(CollegeStudent.NAME);
			
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			Integer count = collegeStudentMapper.selectCountByExample(example);
			System.out.println("count = " + count);
			
		} catch (Exception e) {
			log.error("testSelectCountByExample =====> ", e);
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
			/*
			 * 条件必须匹配到1条，匹配到多条将报错
			 */
			CollegeStudent entity = new CollegeStudent();
			entity.setId(1);
			CollegeStudent e = collegeStudentMapper.selectOne(entity);
			System.out.println(e.toString());
			
		} catch (Exception e) {
			log.error("testSelectOne =====> ", e);
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
	public void testSelectOneByExample() {
		try {
			/*
			 * 条件必须匹配到1条，匹配到多条将报错
			 */
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andEqualTo(CollegeStudent.ID, 1);
			CollegeStudent e = collegeStudentMapper.selectOneByExample(example);
			System.out.println(e.toString());
			
		} catch (Exception e) {
			log.error("testSelectOneByExample =====> ", e);
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
	public void testCriteriaBetween() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testCriteriaBetween =====> ", e);
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
	public void testCriteriaLike() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// like 查询关键字 带上%
			example.or().andLike(CollegeStudent.NAME, "王%");
	
			// 或
			example.or().andEqualTo(CollegeStudent.TYPE, 3);
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testCriteriaLike =====> ", e);
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
	public void testCriteriaAnd() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// like 查询关键字 带上%
			// 多个条件 同时满足多个条件
			example.or().andLike(CollegeStudent.NAME, "王%").andEqualTo(CollegeStudent.TYPE, 1);
	
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testCriteriaAnd =====> ", e);
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
	public void testCriteriaOr() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 或1
			// like 查询关键字 带上%
			example.or().andLike(CollegeStudent.NAME, "王%");
	
			// 或2
			example.or().andEqualTo(CollegeStudent.TYPE, 3);
			
			// 或3 ...
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testCriteriaOr =====> ", e);
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
	public void testAndCondition() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 或1
			/*
				这里的length，调用的数据库的length()函数，一个字符为3个长度
				select * from college_student where LENGTH(name) = 9;
			*/
			example.or().andCondition("length(" + CollegeStudent.NAME + ") = 9 ");
			//example.or().andCondition(CollegeStudent.CREDIT + " > 30");
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndCondition =====> ", e);
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
	public void testOrCondition1() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 或1
			/*
				这里的length，调用的数据库的length()函数，一个字符为3个长度
				select * from college_student where LENGTH(name) = 9;
			*/
			example.or().orCondition("length(" + CollegeStudent.NAME + ") = 9 ");
			
			// 或
			example.or().andCondition(CollegeStudent.CREDIT + " > 40");
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testOrCondition1 =====> ", e);
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
	public void testOrCondition2() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 或1
			/*
				这里的length，调用的数据库的length()函数，一个字符为3个长度
				select * from college_student where LENGTH(name) = 9;
			*/
			example.or().orCondition("length(" + CollegeStudent.NAME + ") = ", 9);
			
			// 或
			example.or().andCondition(CollegeStudent.CREDIT + " > 40");
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testOrCondition2 =====> ", e);
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
	public void testAndCondition1() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 或1
			/*
				这里的length，调用的数据库的length()函数，一个字符为3个长度
				select * from college_student where LENGTH(name) = 9;
			*/
			example.or().andCondition("length(" + CollegeStudent.NAME + ") = 9 ")
			.andCondition(CollegeStudent.CREDIT + " > 30");
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndCondition1 =====> ", e);
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
	public void testAndCondition2() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 或1
			/*
				这里的length，调用的数据库的length()函数，一个字符为3个长度
				select * from college_student where LENGTH(name) = 9;
			*/
			example.or().orCondition("length(" + CollegeStudent.NAME + ") = ", 9)
			.andCondition(CollegeStudent.CREDIT + " >", 30);
			
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndCondition2 =====> ", e);
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
	public void testAndEqualTo() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 所有字段参数作为相等查询条件
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(CollegeStudent.TYPE, 1);
			param.put(CollegeStudent.NAME, "陈明");
			
			example.or().andEqualTo(param);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndEqualTo =====> ", e);
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
	public void testAndEqualTo2() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 不为空的字段参数作为相等查询条件
			
			example.or().andEqualTo(CollegeStudent.TYPE, 1).andEqualTo(CollegeStudent.NAME, "陈明");
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndEqualTo2 =====> ", e);
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
	public void testAndAllEqualTo() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// 所有字段参数作为相等查询条件，如果字段为 null，则为 is null
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(CollegeStudent.TYPE, 1);
			// 等价于: and name is null
			param.put(CollegeStudent.NAME, "");
			
			example.or().andAllEqualTo(param);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndAllEqualTo =====> ", e);
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
	public void testAnd() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			Criteria criteria = example.and();
			criteria.andLike(CollegeStudent.NAME, "王%").andEqualTo(CollegeStudent.TYPE, 1);
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAnd =====> ", e);
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
	public void testAndCriteria() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// Example默认使用第一个创建的条件，后续的条件通过and/or加入
			Criteria criteria = example.or();
			criteria.andLike(CollegeStudent.NAME, "王%");

			Criteria criteria2 = example.createCriteria();
			criteria2.andEqualTo(CollegeStudent.TYPE, 1);
			
			// 增加条件
			example.and(criteria2);
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testAndCriteria =====> ", e);
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
	public void testGetCountColumn() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// Example默认使用第一个创建的条件，后续的条件通过and/or加入
			Criteria criteria = example.or();
			criteria.andLike(CollegeStudent.NAME, "王%");

			Criteria criteria2 = example.createCriteria();
			criteria2.andEqualTo(CollegeStudent.TYPE, 1);
			
			// 增加条件
			example.and(criteria2);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			System.out.println("countColumn = " + example.getCountColumn());
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testGetCountColumn =====> ", e);
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
	public void testGetSelectColumns() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// Example默认使用第一个创建的条件，后续的条件通过and/or加入
			Criteria criteria = example.or();
			criteria.andLike(CollegeStudent.NAME, "王%");

			Criteria criteria2 = example.createCriteria();
			criteria2.andEqualTo(CollegeStudent.TYPE, 1);
			
			// 增加条件
			example.and(criteria2);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			
			// 不返回哪些字段，默认返回所有
			//example.excludeProperties(CollegeStudent.ADDRESS, CollegeStudent.BIRTHDAY);
			// 设置了上面的excludeProperties 才生效
			System.out.println("selectColumns = " + example.getSelectColumns());
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testGetSelectColumns =====> ", e);
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
	public void testExcludeProperties() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			// Example默认使用第一个创建的条件，后续的条件通过and/or加入
			Criteria criteria = example.or();
			criteria.andLike(CollegeStudent.NAME, "王%");

			Criteria criteria2 = example.createCriteria();
			criteria2.andEqualTo(CollegeStudent.TYPE, 1);
			
			// 增加条件
			example.and(criteria2);
			
			// 不返回哪些字段，默认返回所有
			example.excludeProperties(CollegeStudent.ADDRESS, CollegeStudent.BIRTHDAY);
			//System.out.println("countColumn = " + example.getCountColumn());
			System.out.println("selectColumns = " + example.getSelectColumns());
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testExcludeProperties =====> ", e);
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
	public void testDistinct() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			
			// 去重
			example.setDistinct(true);
			// 指定返回属性列表
			example.selectProperties(CollegeStudent.TYPE);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testDistinct =====> ", e);
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
	public void testOrderBy() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			
			// 属性 不带 ASC 、DESC
			example.orderBy(CollegeStudent.NAME );
			
			// 属性 +  ASC、DESC
			//example.setOrderByClause(CollegeStudent.NAME + " ASC");
			//example.setOrderByClause(CollegeStudent.NAME + " DESC");
			
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testOrderBy =====> ", e);
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
	public void testSelectProperties() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			
			// 去重
			example.setDistinct(true);
			// 指定返回属性列表
			example.selectProperties(CollegeStudent.TYPE);
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testSelectProperties =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 分页查询3
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testPage() {
		try {
			int pageNo = 1;
			int pageSize = 3;
	        // 页码方式
	        Page<CollegeStudent> page = PageHelper.startPage(pageNo, pageSize);
			
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
			System.out.println(page.getTotal());
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
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
	public void testExample() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andBetween(CollegeStudent.CREDIT, 20, 30);
	
			List<CollegeStudent> entities = collegeStudentMapper.selectByExample(example);
			for (CollegeStudent e : entities)
			{
				System.out.println(e.toString());
			}
			
		} catch (Exception e) {
			log.error("testExample =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 保存一个实体，null的属性也会保存，不会使用数据库默认值
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testInsert() {
		try {
			CollegeStudent entity = new CollegeStudent();
			entity.setTableName("college_student2");
			entity.setName("赵东来");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			entity.setAddress("广西省桂林市阳朔县大山路201号");
			//entity.setType((byte) 1);
			entity.setRemark("备注啊啊啊");
			// 保存一个实体，null的属性也会保存，不会使用数据库默认值
			collegeStudentMapper.insert(entity);
			
		} catch (Exception e) {
			log.error("testInsert =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testInsertSelective() {
		try {
			CollegeStudent entity = new CollegeStudent();
			entity.setName("赵东来2");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			/*
			 * 保存一个实体，null的属性不会保存，会使用数据库默认值
			 *  通常情况下，insertSelectvie使用得较多
			 */
			collegeStudentMapper.insertSelective(entity);
			
		} catch (Exception e) {
			log.error("testInsertSelective =====> ", e);
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
	public void testInsertList() {
		try {
			List<CollegeStudent> list = new ArrayList<>();
			CollegeStudent entity = new CollegeStudent();
			entity.setName("赵东来22");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			list.add(entity);

			entity = new CollegeStudent();
			entity.setName("赵东来23");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			list.add(entity);			
			
			// 批量插入
			collegeStudentMapper.insertList(list);
			
		} catch (Exception e) {
			log.error("testInsertList =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 限制为实体包含`id`属性并且必须为自增列
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testInsertUseGeneratedKeys() {
		try {
			CollegeStudent entity = new CollegeStudent();
			entity.setName("赵东来2");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			/*
			 * 保存一个实体，null的属性不会保存，会使用数据库默认值
			 *  通常情况下，insertSelectvie使用得较多
			 *  使用数据表定义的自增主键
			 *  其他insert可以带id插入
			 *  限制为实体包含`id`属性并且必须为自增列
			 */
			collegeStudentMapper.insertUseGeneratedKeys(entity);
			
		} catch (Exception e) {
			log.error("testInsertUseGeneratedKeys =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testInsertWithId() {
		try {
			CollegeStudent entity = new CollegeStudent();
			// 带id插入
			entity.setId(20);
			entity.setName("赵东来2");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			/*
			 * 保存一个实体，null的属性不会保存，会使用数据库默认值
			 *  通常情况下，insertSelectvie使用得较多
			 */
			collegeStudentMapper.insertSelective(entity);
		} catch (Exception e) {
			log.error("testInsertWithId =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 根据主键更新实体全部字段，null值会被更新
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testUpdateByPrimaryKey() {
		try {
			CollegeStudent entity = new CollegeStudent();
			entity.setId(20);
			entity.setName("赵东来21");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			// 为空的字段将被清空
			//entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			/*
			 * 根据主键更新实体全部字段，null值会被更新
			 * 通常不采用这种方式去更新，避免已经存在的值被清空
			 */
			collegeStudentMapper.updateByPrimaryKey(entity);
			
		} catch (Exception e) {
			log.error("testUpdateByPrimaryKey =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 根据主键更新属性不为null的值
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testUpdateByPrimaryKeySelective() {
		try {
			CollegeStudent entity = new CollegeStudent();
			entity.setId(20);
			//entity.setName("赵东来21");
			entity.setCredit(new BigDecimal(15.5));
			entity.setBirthday(new Date());
			// 为空的字段将被清空
			//entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark(null);
			/*
			 * 根据主键更新属性不为null的值
			 * 通常采用这种方式去更新
			 */
			collegeStudentMapper.updateByPrimaryKeySelective(entity);
			
			
		} catch (Exception e) {
			log.error("testUpdateByPrimaryKeySelective =====> ", e);
		}
	}			
	
	/**
	 * 
	 * 描述: Example条件更新实体`record`包含的全部属性，null值会被更新
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testUpdateByExample() {
		try {
			CollegeStudent entity = new CollegeStudent();
			// 由于updateByExample 将所有null更新为空，因此可能会出现数据约束而更新失败
			entity.setName("赵东来XXX");
			// 为空的字段将被清空
			//entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark("更新备注哈哈啊");
			/*
			 * Example条件更新实体`record`包含的全部属性，null值会被更新
			 */
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andEqualTo(CollegeStudent.NAME, "赵东来22");
			collegeStudentMapper.updateByExample(entity, example);
			
			
		} catch (Exception e) {
			log.error("testUpdateByExample =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: Example条件更新实体`record`包含的全部属性，通常采用此方法
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testUpdateByExampleSelective() {
		try {
			CollegeStudent entity = new CollegeStudent();
			// 由于updateByExample 将所有null更新为空，因此可能会出现数据约束而更新失败
			entity.setName("赵东来XXX");
			// 为空的字段将被清空
			//entity.setAddress("广西省桂林市阳朔县大桂路345号");
			//entity.setType((byte) 1);
			entity.setRemark("更新备注哈哈啊");
			/*
			 * Example条件更新实体`record`包含的全部属性，null值会被更新
			 */
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andEqualTo(CollegeStudent.NAME, "赵东来23");
			collegeStudentMapper.updateByExampleSelective(entity, example);
		} catch (Exception e) {
			log.error("testUpdateByExample =====> ", e);
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
	public void testDeleteByPrimaryKey() {
		try {
			// 根据主键删除
			collegeStudentMapper.deleteByPrimaryKey(20);
			
		} catch (Exception e) {
			log.error("testDeleteByPrimaryKey =====> ", e);
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
	public void testDeleteByAndCondition() {
		try {
			CollegeStudent entity = new CollegeStudent();
			entity.setName("赵东来2");
			entity.setCredit(new BigDecimal(15.5));
			entity.setAddress("广西省桂林市阳朔县大桂路345号");
			// 根据
			collegeStudentMapper.delete(entity);
			
		} catch (Exception e) {
			log.error("testDeleteByAndCondition =====> ", e);
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
	public void testDeleteByExample() {
		try {
			Example example = new Example(CollegeStudent.class);
			/**
			 * createCriteria() 或 调用 or()
			 * 官方建议调用or生成Criteria对象
			 */
			example.or().andEqualTo(CollegeStudent.NAME, "赵东来21");
			collegeStudentMapper.deleteByExample(example);
			
		} catch (Exception e) {
			log.error("testDeleteByExample =====> ", e);
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
