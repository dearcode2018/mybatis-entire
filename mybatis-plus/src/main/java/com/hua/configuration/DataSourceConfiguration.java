/**
  * @filename DataSourceConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * @type DataSourceConfiguration
 * @description 
 * @author qianye.zheng
 */
@Configuration
@Import(value = DataSourceProperties.class)
public class DataSourceConfiguration {
	
	/* 是否自动提交事务 */
	//@Value("${datasource.auto.commit:false}")
	//private boolean autoCommit;
	
	//@Value("${mybatis.cache.enabled:true}")
	//private boolean cacheEnabled;
	
	/* 单位未知 */
	//@Value("${mybatis.defaultStatement.timeout:5000}")
	//private int defaultStatementTimeout;
	

	/**
	 * 
	 * @description 分页拦截器
	 * 代理执行  select count(*) from xx where 与列表搜索条件相同
	 * @return
	 * @author qianye.zheng
	 */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
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
	        druidDataSource.setDefaultAutoCommit(properties.isDefaultAutoCommit());
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
