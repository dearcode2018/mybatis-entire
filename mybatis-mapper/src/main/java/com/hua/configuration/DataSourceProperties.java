package com.hua.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 
 * @type DataSourceProperties
 * @description 数据源属性
 * @author qianye.zheng
 */
@Data
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    
    /* 是否自动提交 */
    private boolean defaultAutoCommit;
    
    /* 是否自读 */
    private boolean defaultReadOnly;
    
    /* 连接数据库的超时时间 */
    private int loginTimeoutSecond;
    
    /* Statement 执行超时时间 */
    private int queryTimeoutSecond;
    
    /* 是否移除 离线的连接 */
    private boolean removeAbandoned;
    
    /* Druid 默认参数 */
    private int initialSize = 2;
    private int maxActive = 10;
    private int minIdle = -1;
    private long maxWait = 60 * 1000L;
    private long timeBetweenEvictionRunsMillis = 60 * 1000L;
    private long minEvictableIdleTimeMillis = 1000L * 60L * 30L;
    private long maxEvictableIdleTimeMillis = 1000L * 60L * 60L * 7;
    private String validationQuery = "select 1";
    private int validationQueryTimeout = -1;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;
    private boolean testWhileIdle = true;
    private boolean poolPreparedStatements = false;
    private int maxOpenPreparedStatements = -1;
    private boolean sharePreparedStatements = false;
    private String filters = "stat,wall";
    
}