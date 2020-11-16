/**
  * @filename DataSourceConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @type DataSourceConfiguration
 * @description 
 * @author qianye.zheng
 */
@Configuration
@Import(value = DataSourceProperties.class)
//扫描接口，在rg.apache.ibatis.session.Configuration中设置是无效的
@MapperScan(basePackages = {"com.hua.mapper"})
public class DataSourceConfiguration {
	
	/* 是否自动提交事务 */
	//@Value("${datasource.auto.commit:false}")
	//private boolean autoCommit;
	
	//@Value("${mybatis.cache.enabled:true}")
	//private boolean cacheEnabled;
	
	/* 单位未知 */
	//@Value("${mybatis.defaultStatement.timeout:5000}")
	//private int defaultStatementTimeout;
	
	/* 快速失败 */
	@Value("${mybatis.fail.fast:true}")
	private boolean failFast;
	
	/* 事务默认超时时间，单位: 秒 */
	@Value("${datasource.default.tx.timeoutSecond:5}")
	private int defaultTxTimeoutSecond;
	
	/* Statement默认超时时间，单位: 秒 */
	@Value("${datasource.default.statement.timeoutSecond:3}")
	private int defaultStatementTimeoutSecond;
	
	/**
	 * 
	 * @description 事务管理器
	 * @param dataSource
	 * @return
	 * @author qianye.zheng
	 */
    @Bean
	public PlatformTransactionManager transactionManager(final DataSource dataSource) {
    	final DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource);
    	/*
    	 * 此处的超时时间，其实是关联到Statement.queryTimeout 取二者最小值
    	 * 同时申请事务(连接)不能超过该时间，否则报超时异常.
    	 * 申请事务，本质就是从数据源获取连接，然后创建事务对象，检查各类条件，例如是否超时.
    	 * 
    	 */
    	manager.setDefaultTimeout(defaultTxTimeoutSecond);
    	//manager.setEnforceReadOnly(false);
    	//manager.setFailEarlyOnGlobalRollbackOnly(true);
    	// 允许嵌套事务
    	//manager.setNestedTransactionAllowed(true);
    	//manager.setTransactionSynchronization();
    	// 是否校验已经存在的事务
    	//manager.setValidateExistingTransaction(true);
    	
    	return manager;
	}
	
    /**
     * 
     * @description Sql会话工厂
     * @param dataSource
     * @return
     * @throws Exception
     * @author qianye.zheng
     */
    @Bean
    public SqlSessionFactory setSqlSessionFactory(final DataSource dataSource) throws Exception {
    	VFS.addImplClass(SpringBootVFS.class);
    	final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
		final org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setCacheEnabled(true);
		config.setDefaultStatementTimeout(defaultStatementTimeoutSecond);
		// 类型别名
		//config.getTypeAliasRegistry().registerAliases("com.hua.entity");
		/*
		 * mapper接口包路径，设置了mapper.xml文件路径后，可以无需设置
		 * 在集成Spring中，必须用@MapperScan标注包路径，使用此设置无效
		 */
		//config.addMappers("com.hua.mapper");
        bean.setTypeAliasesPackage("com.hua.entity");
        bean.setConfiguration(config);
        bean.setFailFast(failFast);
        // 指定mapper.xml文件路径
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*Mapper.xml"));
        
        return bean.getObject();
    }
	
	/**
	 * 
	 * @description 构造数据源/连接池
	 * @param properties
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	 public DruidDataSource buildDruidDataSource(DataSourceProperties properties) {
	        DruidDataSource dataSource = new DruidDataSource();
	        dataSource.setDriverClassName(properties.getDriverClassName());
	        // 异步关闭连接
	        //druidDataSource.setAsyncCloseConnectionEnable();
	        // 异步初始化
	        //druidDataSource.setAsyncInit(true);
	        dataSource.setLoginTimeout(properties.getLoginTimeoutSecond());
	        dataSource.setQueryTimeout(properties.getQueryTimeoutSecond());
	        dataSource.setRemoveAbandoned(properties.isRemoveAbandoned());
	        dataSource.setUrl(properties.getUrl());
	        dataSource.setUsername(properties.getUsername());
	        dataSource.setPassword(properties.getPassword());
	        dataSource.setDefaultAutoCommit(properties.isDefaultAutoCommit());
	        dataSource.setDefaultReadOnly(properties.isDefaultReadOnly());
	        dataSource.setInitialSize(properties.getInitialSize());
	        dataSource.setMaxActive(properties.getMaxActive());
	        dataSource.setMinIdle(properties.getMinIdle());
	        dataSource.setMaxWait(properties.getMaxWait());
	        dataSource.setDefaultAutoCommit(properties.isDefaultAutoCommit());
	        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
	        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
	        dataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
	        dataSource.setValidationQuery(properties.getValidationQuery());
	        dataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
	        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
	        dataSource.setTestOnReturn(properties.isTestOnReturn());
	        dataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
	        dataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
	        dataSource.setSharePreparedStatements(properties.isSharePreparedStatements());

	        try {
	            dataSource.setFilters(properties.getFilters());
	            dataSource.init();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return dataSource;
	    }
}
