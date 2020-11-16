/**
  * @filename DataSourceConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.hua.mapper.PersonMapper;

/**
 * @type DataSourceConfiguration
 * @description 
 * @author qianye.zheng
 */
@Import(value = DataSourceProperties.class)
@Configuration
public class DataSourceConfiguration {
	
	/* 是否自动提交事务 */
	@Value("${datasource.auto.commit:false}")
	private boolean autoCommit;
	
	@Value("${mybatis.cache.enabled:true}")
	private boolean cacheEnabled;
	
	/* 单位未知 */
	@Value("${mybatis.defaultStatement.timeout:5000}")
	private int defaultStatementTimeout;
	
	/* ################# 实例化Mapper ################ */
	
	/**
	 * 
	 * @description 
	 * @param sqlSession
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public PersonMapper personMapper(final SqlSession sqlSession) {
		return sqlSession.getMapper(PersonMapper.class);
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
	
	/**
	 * 
	 * @description 
	 * @param factory
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public SqlSession session(final SqlSessionFactory factory) {
		return factory.openSession(autoCommit);
	}
	
	/**
	 * 
	 * @description 
	 * @param factory
	 * @return
	 * @author qianye.zheng
	 */
	@Primary
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(final SqlSessionFactory factory) {
		 return new SqlSessionTemplate(factory);
	}
	
	/**
	 * 
	 * @description 
	 * @param dataSource
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public SqlSessionFactory factory(final DataSource dataSource) {
		final SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		final Environment env = new Environment("envId",  txFactory(), dataSource);
		final org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration(env);
		config.setCacheEnabled(cacheEnabled);
		config.setDefaultStatementTimeout(defaultStatementTimeout);
		config.addMappers("com.hua.mapper");
		//config.addMappers("mapper");
		
		return builder.build(config);
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Bean
	public TransactionFactory txFactory() {
		final TransactionFactory txFactory = new JdbcTransactionFactory();
		txFactory.setProperties(null);
		
		return txFactory;
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
	        DruidDataSource druidDataSource = new DruidDataSource();
	        druidDataSource.setDriverClassName(properties.getDriverClassName());
	        druidDataSource.setUrl(properties.getUrl());
	        druidDataSource.setUsername(properties.getUsername());
	        druidDataSource.setPassword(properties.getPassword());
	        druidDataSource.setInitialSize(properties.getInitialSize());
	        druidDataSource.setMaxActive(properties.getMaxActive());
	        druidDataSource.setMinIdle(properties.getMinIdle());
	        druidDataSource.setMaxWait(properties.getMaxWait());
	        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
	        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
	        druidDataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
	        druidDataSource.setValidationQuery(properties.getValidationQuery());
	        druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
	        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
	        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
	        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
	        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
	        druidDataSource.setSharePreparedStatements(properties.isSharePreparedStatements());

	        try {
	            druidDataSource.setFilters(properties.getFilters());
	            druidDataSource.init();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return druidDataSource;
	    }
}
