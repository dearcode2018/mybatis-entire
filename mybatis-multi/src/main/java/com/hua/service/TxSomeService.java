/**
  * @filename TxSomeService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type TxSomeService
 * @description 
 * @author qianye.zheng
 */
@Service
public class TxSomeService {

	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 若不标注@Transactional或使用手工方式创建事务，则MyBatis的 SqlSessionTemplate
	 * 会设置连接的为自动提交: connection.setCommit(true)
	 * 
	 */
	
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int propagationRequired(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 发生异常
	 * 和调用方在同一个事务中，该方法发生异常，会导致整个事务回滚
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int propagationRequiredOccurException(final Person entity) {
		personMapper.insert(entity);
		String str = null;
		str.length();
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 发生异常
	 * 和调用方在同一个事务中，该方法发生异常，会导致整个事务回滚
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int propagationRequiredOccurExceptionWithCatch(final Person entity) {
		personMapper.insert(entity);
		try {
			String str = null;
			str.length();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int propagationRequiresNew(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 没有事务调用此方法会抛异常
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public int propagationMandatory(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.NEVER)
	public Integer propagationNever(final Person entity) {
		// do otherthing
		System.out.println("做其他事情");
		// Execute non-transactionally. 自动提交也没有起作用，Statement正常执行了，只是没commit.
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public int propagationSupport(final Person entity) {
		personMapper.insert(entity);
		System.out.println("正在做其他事情");
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.NOT_SUPPORTED)
	public int propagationNotSupported(final Person entity) {
		// 执行Statement，但没有commit，说明autocommit在此并不生效
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public int propagationNested(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false)
	public int insert(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, timeout = 20)
	public int insertWithTimeout(final Person entity) {
		personMapper.insert(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public int insertWithException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRES_NEW)
	public int insertRequiresNewWithException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	// timeout覆盖全局值
	@Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
	public int insertWithRuntimeException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	//  会匹配自身或子类的，支持完整、简单类名
	@Transactional(readOnly = false, rollbackForClassName  = {"RuntimeException", "IllegalArgumentException", "java.lang.IllegalMonitorStateException"})
	public int insertWithExceptionName(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalAccessError("发生运行时异常");
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @param ifThrow
	 * @return
	 * @author qianye.zheng
	 */
	// 
	//  会匹配自身或子类的，支持完整、简单类名
	@Transactional(readOnly = false, noRollbackFor  = {RuntimeException.class})
	//@Transactional(readOnly = false, noRollbackFor  = {RuntimeException.class, IllegalArgumentException.class, IllegalAccessError.class})
	public int insertNoRollbackForException(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalArgumentException("发生运行时异常");
		}
		
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @param ifThrow
	 * @return
	 * @author qianye.zheng
	 */
	//  会匹配自身或子类的，支持完整、简单类名
	@Transactional(readOnly = false, noRollbackForClassName  = {"RuntimeException", "IllegalArgumentException", "java.lang.IllegalMonitorStateException"})
	public int insertNoRollbackForExceptionName(final Person entity, final boolean ifThrow) {
		personMapper.insert(entity);
		if (ifThrow) {
			throw new IllegalArgumentException("发生运行时异常");
		}
		
		
		return entity.getId();
	}
	
}
