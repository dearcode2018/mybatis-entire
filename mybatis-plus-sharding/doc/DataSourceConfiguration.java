/**
  * @filename DataSourceConfiguration.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.hua.entity.DynamicTableEntity;
import com.hua.util.StringUtil;

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
        InnerInterceptor sqlInterceptor = new InnerInterceptor() {
            /**
             * @description 
             * @param sh
             * @param connection
             * @param transactionTimeout
             * @author qianye.zheng
             */
            @Override
            public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
                
                BoundSql boundSql = sh.getBoundSql();
                final Object target = boundSql.getParameterObject();
                String originalName = null;
                String resultName = null;
                if (target instanceof DynamicTableEntity) {
                    TableName table = target.getClass().getAnnotation(TableName.class);
                    originalName = table.value();
                    final DynamicTableEntity dynamic = (DynamicTableEntity) target;
                    resultName = dynamic.getDynamicTableName();
                }
                if (target instanceof MapperMethod.ParamMap) {
                    final MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) target;
                    Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
                    for (Map.Entry<String, Object> entry : entrySet) {
                        final Object temp = entry.getValue();
                        if (temp instanceof DynamicTableEntity) {
                            TableName table = temp.getClass().getAnnotation(TableName.class);
                            originalName = table.value();
                            final DynamicTableEntity dynamic = (DynamicTableEntity) temp;
                            resultName = dynamic.getDynamicTableName();
                            // 结束循环
                            break;
                        }
                    }
                }
                
                try {
                    final Field field = BoundSql.class.getDeclaredField("sql");
                    field.setAccessible(true);
                    // 替换表名 生成新的sql
                    if (StringUtil.isNotEmpty(originalName)) {
                        String sql = field.get(boundSql).toString().replace(originalName, resultName);
                        field.set(boundSql, sql);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        interceptor.addInnerInterceptor(sqlInterceptor);
        
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
