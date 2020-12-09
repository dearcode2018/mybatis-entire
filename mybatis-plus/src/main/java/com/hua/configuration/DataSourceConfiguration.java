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
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.hua.interceptor.DynamicTableNameInterceptor;

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
     * @description 
     * @return
     * @author qianye.zheng
     */
    @Bean
    public MybatisPlusInterceptor interceptor() {
        final MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        final DynamicTableNameInnerInterceptor tbNameInterceptor = new DynamicTableNameInterceptor();
        interceptor.addInnerInterceptor(tbNameInterceptor);
        
        return interceptor;
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
