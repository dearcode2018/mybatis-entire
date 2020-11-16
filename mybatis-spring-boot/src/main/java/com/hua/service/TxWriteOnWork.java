/**
  * @filename TxWriteOnWork.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type TxWriteOnWork
 * @description 日常工作中事务的写法
 * 包含了错误写法、规范 写法
 * @author qianye.zheng
 */
@Service
public class TxWriteOnWork {
	
	@Resource
	private PersonMapper personMapper;
	
	@Resource
	private TxWriteOnWork self;
	
	
	/**
	 * 在通常的CRUD业务中，需要通过事务来控制数据同时生效/失效，而不是独立起来
	 * 
	 * 
	 * 错误提示
	 * 1) 对象内部方法，使用	@Transactional 不起作用, sonar也提示了(Make this method "public" or remove the "@Transactional" annotation)
	 * 2) 对象内方法调用方法，分2种情况
	 *  a) 当前对象的最外层方法，声明并使用了事务(不管是新建还是使用上游的事务)，那边它调用当前对象的所有方法都会使用该事务
	 *  b) 当前对象的最外层方法，没有事务，那么它调用当前对象的所有方法都是采用自动提交
	 */
	
	
	
	/* =========================== 错误的写法 ========================== */
	
	/**
	 * 
	 * @description 错误的写法1
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void wrongWrite1(final Person entity) {
		// 在当前方法声明的事务内
		personMapper.insert(entity);
		
		// 在当前方法声明的事务内
		this.updateOther1();
		
		System.out.println("do other thing1");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	private void updateOther1() {
		Person entity = new Person();
		entity.setName("张三updateOther1");
		entity.setNation("汉族");
		entity.setAddress("广州市海珠区新港西路102号");
		entity.setBirthday(new Date());
		personMapper.insert(entity);
		
		System.out.println("do other thing10");
	}
	
	/**
	 * 
	 * @description 错误的写法2
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void wrongWrite2(final Person entity) {
		// 在当前方法声明的事务内
		personMapper.insert(entity);
		
		// 在当前方法声明的事务内, 但是updateOther2事务声明无效
		this.updateOther2();
		
		System.out.println("do other thing2");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	@Transactional
	private void updateOther2() {
		Person entity = new Person();
		entity.setName("张三updateOther2");
		entity.setNation("汉族");
		entity.setAddress("广州市海珠区新港西路102号");
		entity.setBirthday(new Date());
		personMapper.insert(entity);
	}
	
	/**
	 * 
	 * @description 错误的写法3
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void wrongWrite3(final Person entity) {
		// 在当前方法声明的事务内
		personMapper.insert(entity);
		
		// 在当前方法声明的事务内, 但是updateOther3事务声明无效
		this.updateOther3();
		
		System.out.println("do other thing3");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void updateOther3() {
		Person entity = new Person();
		entity.setName("张三updateOther3");
		entity.setNation("汉族");
		entity.setAddress("广州市海珠区新港西路102号");
		entity.setBirthday(new Date());
		personMapper.insert(entity);
	}
	
	/**
	 * 
	 * @description 没有事务声明，它调用当前对象的方法都是使用自动提交
	 * @param entity
	 * @author qianye.zheng
	 */
	public void wrongWrite4(final Person entity) {
		// 没有事务，使用自动提交
		personMapper.insert(entity);
		
		// 调用方没有事务，自身也没有事务，则使用自动提交
		this.updateOther4();
		
		System.out.println("do other thing1");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	private void updateOther4() {
		Person entity = new Person();
		entity.setName("张三updateOther4");
		entity.setNation("汉族");
		entity.setAddress("广州市海珠区新港西路102号");
		entity.setBirthday(new Date());
		// 调用方没有事务，自身也没有事务，则使用自动提交
		personMapper.insert(entity);
	}
	
	/* =========================== 正确/规范的写法 ========================== */
	
	
	
	/**
	 * 
	 * @description 正确的写法1
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void rightWrite1(final Person entity) {
		// 在当前方法声明的事务内
		personMapper.insert(entity);
		
		// 模拟实际业务中，修改另外一个数据模型
		Person entity2 = new Person();
		entity2.setName("张三rightWrite1");
		entity2.setNation("汉族");
		entity2.setAddress("广州市海珠区新港西路102号");
		entity2.setBirthday(new Date());
		// 在当前方法声明的事务范围内
		personMapper.insert(entity2);
		
		System.out.println("do other thing20");
	}
	
	/**
	 * 
	 * @description 正确的写法1
	 * @param entity
	 * @author qianye.zheng
	 */
	@Transactional
	public void rightWrite2(final Person entity) {
		// 在当前方法声明的事务内
		personMapper.insert(entity);
		
		// 模拟实际业务中，修改另外一个数据模型
		// 在当前方法声明的事务范围内
		self.subModule();
		
		System.out.println("do other thing21");
	}
	
	/**
	 * 
	 * @description 子模块，声明为使用主模块的事务
	 * @author qianye.zheng
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void subModule() {
		Person entity = new Person();
		entity.setName("张三2020");
		entity.setNation("汉族");
		entity.setAddress("广州市海珠区新港西路102号");
		entity.setBirthday(new Date());
		// 自动提交
		personMapper.insert(entity);
	}
	
	
}
