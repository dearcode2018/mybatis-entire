/**
  * @filename DataSourceAspect.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.aspect;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hua.annotation.DataSource;
import com.hua.datasource.DataSourceContextHolder;
import com.hua.datasource.DataSourceNames;

/**
 * @type DataSourceAspect
 * @description 
 * @author qianye.zheng
 */
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceAspect {
	
	private static final String DATA_SOURCE_PATH = "com.hua.annotation.DataSource";
	
	protected final Logger log = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	@Pointcut("@annotation(" + DATA_SOURCE_PATH + ") || @within(" + DATA_SOURCE_PATH + ") || execution (* com.hua.service.*.*(..))")
	public void dataSourcePointCut() {throw  new UnsupportedOperationException();}
	
	/**
	 * 
	 * @description 
	 * @param point
	 * @return
	 * @throws Throwable
	 * @author qianye.zheng
	 */
	@Around("dataSourcePointCut()")
	public Object around(final ProceedingJoinPoint point) throws Throwable {
		final MethodSignature signature = (MethodSignature) point.getSignature(); 
		final Class<?> targetClass = point.getTarget().getClass();
		final Method method = signature.getMethod();
		final DataSource classDataSource = targetClass.getAnnotation(DataSource.class);
		final DataSource methodDataSource = method.getAnnotation(DataSource.class);
		String dataSourceName = null;
		if (null != classDataSource || null != methodDataSource) {
			if (null != methodDataSource) { // 方法级别的优先
				dataSourceName = methodDataSource.name();
			} else { // 使用类级注解
				dataSourceName = classDataSource.name();
			}
		} else { // 为空则使用指定的，也可不用指定，直接用默认的数据源
			dataSourceName = DataSourceNames.DB1;
		}
		DataSourceContextHolder.push(dataSourceName);
		log.info("set datasource is {}", dataSourceName);
		try {
			return point.proceed();
		} finally { // 标注注解的方法执行完成后，移除掉当前线程最近使用的数据源名
			dataSourceName = DataSourceContextHolder.poll();
			log.info("clean datasource is {}", dataSourceName);
		}
	}
	
}
