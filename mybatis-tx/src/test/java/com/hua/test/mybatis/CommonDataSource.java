/**
  * @filename CommonDataSource.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.test.mybatis;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.hua.test.BaseTest;

/**
 * @type CommonDataSource
 * @description 
 * @author qianye.zheng
 */
public abstract class CommonDataSource extends BaseTest {

	
	/**
	 * 
	 * @description 
	 * @param dataSource
	 * @param autoCommit
	 * @return
	 * @author qianye.zheng
	 */
	protected SqlSession newSqlSession(final DataSource dataSource, final boolean autoCommit) {
		final TransactionFactory txFactory = new JdbcTransactionFactory();
		final SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		final Environment env = new Environment("envId",  txFactory, dataSource);
		final org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration(env);
		config.setCacheEnabled(true);
		
		/*
		 * 等待锁的时候，若此时间小于服务器的innodb_lock_wait_timeout (服务器默认 50s)
		 * 则直接在客户端抛出: Statement cancelled due to timeout or client request
		 * 
		 * 若大于 服务器的innodb_lock_wait_timeout 则会收到服务器的异常提醒: Lock wait timeout exceeded; try restarting transaction
		 * 
		 */
		config.setDefaultStatementTimeout(10);
		// 类型别名
		config.getTypeAliasRegistry().registerAliases("com.hua.entity");
		/**
		 * mybatis默认情况下，将mapper接口和xml文件放在同一个包下
		 * 
		 */
		config.addMappers("com.hua.mapper");
		// 如下的设置是无效的
		//config.addLoadedResource("classpath:mapper/CityMapper.xml");
		SqlSessionFactory factory = builder.build(config);
		//SqlSession sqlSession = factory.openSession(autoCommit);
		
		return factory.openSession(autoCommit);
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	protected DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		/**
		 * DBCP 不是通过 DriverManager创建的连接，而是直接通过调用Driver实现类的connect()方法来创建连接
		 * java.sql.Driver.connect(String url, Properties info)
		 * 
		 * mysql: com.mysql.cj.jdbc.Driver.connect(String url, Properties info)
		 * 
		 */
		// 输出异常日志到控制台，便于查看 (DBCP不是通过DriverManager创建连接)
		// DriverManager.setLogWriter(new PrintWriter(System.out));

		/** 固定参数 */
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);

		/**
		 * 可调参数 连接池级别的参数，适用于所有的连接对象 线程在借用连接对象时，可以覆盖某些属性，归还的时候连接池是否对其属性进行还原(未知)
		 * 不支持此方法
		 */
		/*
		 * try { // 连接池向数据库申请建立连接的超时时间 dataSource.setLoginTimeout(10); } catch
		 * (SQLException e) { e.printStackTrace(); }
		 */
		
		/*
		 * 连接归还时，自动提交(true)
		 */
		dataSource.setDefaultAutoCommit(false);
		// 连接归还连接池时是否自动提交
		/*
		 * 如果设置为true，则连接被归还到连接池时，会指定设置为autoCommit = true
		 * 只有在连接池的defaultAutoCommit为空的时候，该设置才会有效
		 * 否则每次归还之后，依然被设置为和defaultAutoCommit一样的值
		 * 也就是说设置了defaultAutoCommit，该功能就无效了. autoCommit在平常运行下，一般都不会开启
		 */
		dataSource.setAutoCommitOnReturn(false);
		/*连接是否默认为只读
		 * 若设置为只读连接，发生了写操作，则会抛如下异常: 
		 * Connection is read-only. Queries leading to data modification are not allowed
		 */
		dataSource.setDefaultReadOnly(false);
		// 连接的默认事务隔离级别
		dataSource.setDefaultTransactionIsolation(
				Connection.TRANSACTION_REPEATABLE_READ);
		// mysq/oralce不支持，sqlserver值为数据库名
		// dataSource.setDefaultCatalog(null);
		/*
		 * 是否开启缓存状态，连接对象直接用池的缓存状态，而不是查询数据库 这些状态指的是
		 * 连接的一些公共属性，例如事务隔离级别、是否自动提交、是否只读
		 */
		dataSource.setCacheState(true);
		// 默认查询超时时间，在创建Statement时设置，秒
		dataSource.setDefaultQueryTimeout(60);

		/*
		 * 连接归还连接池时是否执行rollback() autoCommit=false且非只读
		 * 在某些场景，例如运行测试用例的时候，不希望污染数据库，就设置为true
		 */
		dataSource.setRollbackOnReturn(false);
		/** 连接数设置，负数-不限制 */
		// 连接池初始化连接数
		dataSource.setInitialSize(1);
		// 连接池最大能创建的连接数(已借用 + 空闲的)，负数(通常设置为-1)-无限制
		dataSource.setMaxTotal(2);
		/*
		 * 最大空闲连接数 (空闲: 没有被客户端使用的连接) 在访问负载较高时，最大空闲值应该调大，以避免 频繁释放和创建(释放:
		 * 将连接归还数据库. 创建: 从数据库获取连接) 空闲连接数量超过该值，符合条件的空闲连接将被释放
		 * 
		 * 空闲连接数量处于 最大和最小值开区间时，符合条件的将被驱逐线程释放，直到等于最小值.
		 */
		dataSource.setMaxIdle(5);
		/*
		 * 最小空闲连接数，低于该值，空闲连接将被创建 开启驱逐线程，设置timeBetweenEvictionRunsMillis为正数
		 */
		dataSource.setMinIdle(2);
		/*
		 * 某个工作线程从连接池借用连接的最大等待时间: 毫秒数 负数表示一直等待
		 */
		dataSource.setMaxWaitMillis(3 * 1000);
		// 连接的校验语句
		dataSource.setValidationQuery("SELECT 1");
		// 校验语句的查询超时时间，创建Statement时设置. 秒
		dataSource.setValidationQueryTimeout(5);
		// 连接池创建连接之后是否测试连接的有效性
		dataSource.setTestOnCreate(true);
		// 工作线程借用连接之前是否测试连接的有效性
		dataSource.setTestOnBorrow(true);
		// 工作线程归还连接之前是否测试连接的有效性
		dataSource.setTestOnReturn(false);
		// 是否测试空闲连接的有效性
		dataSource.setTestWhileIdle(false);

		// 创建/借用/归还/空闲，所有环节检查出有问题的连接，直接释放

		/*
		 * 驱逐(者)线程(evictor): 创建连接、释放连接，测试连接的有效性(释放有问题的连接)
		 */
		// 驱逐线程休眠时间，单位: 毫秒 (工作一次后的休息时间)
		dataSource.setTimeBetweenEvictionRunsMillis(10 * 1000);
		// 每个驱逐线程检查的连接数
		dataSource.setNumTestsPerEvictionRun(3);
		// 最小可被驱逐的空闲时间，单位: 毫秒，默认30分钟
		dataSource.setMinEvictableIdleTimeMillis(5 * 60 * 1000);
		// 最小可被驱逐的空闲时间 (同时也要满足空闲连接数不能小于minIdle的条件)，单位: 毫秒. 默认不限制
		dataSource.setSoftMinEvictableIdleTimeMillis(5 * 1000);

		/*
		 * 最小可被驱逐的空闲时间: 空闲连接最小的空闲时间，超过则有可能被释放 软最小可被驱逐的空闲时间:
		 * 空闲连接最小的空闲时间，在同时满足空闲连接数不小于minIdle时，释放某些连接
		 * 
		 * 若2个都开启，则第一个的时间应该长一些，避免被回收了太多，导致系统又忙着去创建新的连接
		 */

		/*
		 * 连接的最大的生命周期，单位: 毫秒，超时则在下次激活、钝化、校验时都将失败， 0/负数是无限的
		 */
		dataSource.setMaxConnLifetimeMillis(0);
		// 初始化执行的sql集合，在某些场景有用，例如 上报数据连接情况
		// dataSource.setConnectionInitSqls(null);
		// 后进先出: 优先借用最后一次借用的对象，相对而言，最近使用的连接相对会更加健康一些
		dataSource.setLifo(true);
		// 是否记录过期的连接
		dataSource.setLogExpiredConnections(true);
		// 是否开启预处理语句池
		dataSource.setPoolPreparedStatements(true);
		// 预处理语句池的大小
		dataSource.setMaxOpenPreparedStatements(500);
		// 是否允许PoolGuard访问底层连接
		dataSource.setAccessToUnderlyingConnectionAllowed(false);
		// 借用时是否移除被丢弃的连接
		dataSource.setRemoveAbandonedOnBorrow(true);
		// 在维护期间是否移除被丢弃的连接
		dataSource.setRemoveAbandonedOnMaintenance(true);
		// 丢弃的连接在移除之前的存活时间
		dataSource.setRemoveAbandonedTimeout(300);
		// 是否为丢弃语句、连接开启日志
		dataSource.setLogAbandoned(true);
		/*
		 * 是否开启快速校验是失败
		 * 
		 * 发生致命异常，快速失败，不再去做过多校验，例如再去执行isValid() 异常码（SQL_STATE）指以下： 57P01 (ADMIN
		 * SHUTDOWN) 57P02 (CRASH SHUTDOWN) 57P03 (CANNOT CONNECT NOW) 01002
		 * (SQL92 disconnect error) JZ0C0 (Sybase disconnect error) JZ0C1
		 * (Sybase disconnect error) Any SQL_STATE code that starts with "08"
		 * 
		 */
		dataSource.setFastFailValidation(true);
		// 断开连接的SQL_STATE码集合 57P01... 默认
		// org.apache.commons.dbcp2.Utils.DISCONNECTION_SQL_CODES
		// dataSource.setDisconnectionSqlCodes();

		return dataSource;
	}
	
}
