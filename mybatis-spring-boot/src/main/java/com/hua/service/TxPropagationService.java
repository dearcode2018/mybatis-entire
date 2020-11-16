/**
  * @filename TxPropagationService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

/**
 * @type TxPropagationService
 * @description 
 * @author qianye.zheng
 */
@Service
public class TxPropagationService {

	@Resource
	private PersonMapper personMapper;
	
	@Resource
	private TxSomeService txSomeService;
	
	/**
	 * 若不标注@Transactional或使用手工方式创建事务，则MyBatis的 SqlSessionTemplate
	 * 会设置连接的为自动提交: connection.setCommit(true)
	 * 
	 * 
	 * 结论:
	 * 1) 凡是开启/创建一个新事务，则必然对应着新的数据库连接
	 * 所谓开启事务: 获取一个新的数据库连接，然后创建新的事务对象，与之关联
	 * 2) 可以通过将数据源的最大连接数设置为1，来模拟这种场景
	 * 3) 一个数据库连接(客户端)，在同一时刻只能存在一个事务，在程序中，事务对象可以重复创建，
	 * 事务提交了之后，就可以创建新的事务对象，在一个数据库客户端生命周期中，事务可以多次创建/提交，
	 * 但一个连接一个时刻只存在一个事务对象.
	 * 
	 * 
	 * 
	 * 1) 并行事务: 独立成功、失败
	 * 2) 同一个事务内: 在事务影响的范围内有方法执行异常，且那个方法没有捕获，则该事务失败
	 * 3) 异常发生，若不主动捕获，则会导致牵涉到的事务回滚
	 * 4) 只有多个事务内，调用方主动catch住下游方法的异常，才不会因为下游方法的异常影响到自己的事务
	 * 
	 * 多事务协调建议
	 * 1) 调用方主动catch住下游方法，则在发生异常的时候，可以不执行Statement
	 * 2) 调用方主动catch住下游方法，且必选首选执行Statement，则在发生异常时，主动向上抛异常(或获取到事务执行rollback)，以回滚当前事务
	 * 3) 或者保持异常畅通，事务自动回滚
	 * 
	 * 
	 */
	
	/* ================================= 多事务协调建议 ================================== */	
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int multipleTx1(final Person entity) {
		try {
			txSomeService.insertRequiresNewWithException(entity, true);
		} catch (Exception e) {
			e.printStackTrace();
			// 有异常，可以选择不往下执行.也可以继续执行
			return -1;
		}
		// 无异常则继续往下执行
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
	@Transactional
	public int multipleTx2(final Person entity) {
		/*
		 * 因为某种原因，必须要先执行
		 * 例如，要拿到主表的主键，先执行Statement
		 */
		personMapper.insert(entity);
		try {
			//
			System.out.println("获取到主表的主键: " + entity.getId());
			final Person entity2 = new Person();
			entity2.setName("李四200023");
			entity2.setNation("汉族2");
			entity2.setAddress("广州市天河区中山大道中102号， 主键: " + entity.getId());
			entity2.setBirthday(new Date());
			txSomeService.insertRequiresNewWithException(entity2, true);
		} catch (Exception e) {
			e.printStackTrace();

			return -1;
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 经典写法
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int multipleTx3(final Person entity) {
		/*
		 * 因为某种原因，必须要先执行
		 * 例如，要拿到主表的主键，先执行Statement
		 */
		personMapper.insert(entity);
		
		System.out.println("获取到主表的主键: " + entity.getId());
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号， 主键: " + entity.getId());
		entity2.setBirthday(new Date());
		txSomeService.insertRequiresNewWithException(entity2, true);

		
		return entity.getId();
	}
	
	
	/* =============================== 事务传播 ==================================== */	
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callOtherWithoutTx(final Person entity) {
		System.out.println("做其他事情");
		txSomeService.propagationRequired(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callMandatoryWithoutTx(final Person entity) {
		System.out.println("做其他事情");
		/*没有事务调用此方法会抛异常
		 * org.springframework.transaction.IllegalTransactionStateException: 
		 * No existing transaction found for transaction marked with propagation 'mandatory'
		 */
		txSomeService.propagationMandatory(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callOtherWithAutoCommit(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		txSomeService.propagationRequired(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 两个都是在一个事务当中，所以只要有一个方法事务有问题，那么都不会执行成功。
	 * 和下游方法在一个事务里面，下游方法发生异常，不管是否catch住，都会一齐回滚
	 * 只要在发生异常的地方进行了捕获，则不影响整体事务
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	/*
	 * 在同一个事务中，方法调用链中，存在一个方法执行异常，则事务回滚
	 */
	@Transactional
	public int callRequiredOccurExceptionWithTx(final Person entity) {
		personMapper.insert(entity);
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		txSomeService.propagationRequiredOccurException(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 两个都是在一个事务当中，所以只要有一个方法事务有问题，那么都不会执行成功。
	 * 和下游方法在一个事务里面，下游方法发生异常，不管是否catch住，都会一齐回滚
	 * 只要在发生异常的地方进行了捕获，则不影响整体事务
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callRequiredOccurExceptionWithTxWitCatch(final Person entity) {
		personMapper.insert(entity);
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		try {
			// 在调用方catch住没有用，因为下游方法也在事务范围内，只要它执行失败，该事务整体都是失败的
			txSomeService.propagationRequiredOccurException(entity2);
		} catch (Exception e) { // 捕获了下游方法的异常
			e.printStackTrace();
		}
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 两个都是在一个事务当中，所以只要有一个方法事务有问题，那么都不会执行成功。
	 * 和下游方法在一个事务里面，下游方法发生异常，不管是否catch住，都会一齐回滚
	 * 只要在发生异常的地方进行了捕获，则不影响整体事务
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callRequiredOccurExceptionWithCatchWithTx(final Person entity) {
		personMapper.insert(entity);
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		/*
		 * 发生异常的方法，在内部捕获了，不往外抛，因此，事务提交了
		 */
		txSomeService.propagationRequiredOccurExceptionWithCatch(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callRequiredWithTx(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		txSomeService.propagationRequired(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callRequiredWithoutTx(final Person entity) {
		// 无事务则开启事务
		txSomeService.propagationRequired(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callRequiresNewWithTx(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		/*
		 * 获取一个新的数据库连接，然后创建事务与之关联
		 * 一个数据库连接对象，在某个时刻只跟一个事务对象关联
		 */
		txSomeService.propagationRequiresNew(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 无事务调用
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callRequiresNewWithoutTx(final Person entity) {
		// 开启一个新事务
		txSomeService.propagationRequiresNew(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callNeverWithTx(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		/*
		 * 带事务去调用了一个事务为NEVER的，抛如下异常: 
		 * org.springframework.transaction.IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'
		 */
		txSomeService.propagationNever(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public Integer callNeverWithoutTx(final Person entity) {
		// 执行正常
		txSomeService.propagationNever(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callSupportWithTx(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		// 
		txSomeService.propagationSupport(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callSupportWithoutTx(final Person entity) {
		// 无事务执行，则Statement执行了，但没有commit
		txSomeService.propagationSupport(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callNotSupportedWithTx(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		// 有事务，则挂起当前事务
		txSomeService.propagationNotSupported(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callNotSupportedWithoutTx(final Person entity) {
		txSomeService.propagationNotSupported(entity);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	@Transactional
	public int callNestedWithTx(final Person entity) {
		personMapper.insert(entity);
		
		final Person entity2 = new Person();
		entity2.setName("李四200023");
		entity2.setNation("汉族2");
		entity2.setAddress("广州市天河区中山大道中102号");
		entity2.setBirthday(new Date());
		/*
		 * 在当前事务里面执行，即嵌套事务
		 */
		txSomeService.propagationNested(entity2);
		
		return entity.getId();
	}
	
	/**
	 * 
	 * @description 
	 * @param entity
	 * @return
	 * @author qianye.zheng
	 */
	public int callNestedWithoutTx(final Person entity) {
		// autocommit
		//personMapper.insert(entity);
		/*
		 * 当前没有事务，则开启一个事务，并在其中执行
		 */
		txSomeService.propagationNested(entity);
		
		return entity.getId();
	}
	
	/* =================================================================== */	
	
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
