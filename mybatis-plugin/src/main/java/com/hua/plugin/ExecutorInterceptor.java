/**
  * @filename ExecutorInterceptor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.plugin;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.hua.entity.City;

/**
 * @type ExecutorInterceptor
 * @description 
 * @author qianye.zheng
 */
@Intercepts({
	// 拦截目标类型的多个方法，每个方法一个签名
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, 
			ResultHandler.class, CacheKey.class, BoundSql.class}),
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class ExecutorInterceptor implements Interceptor {

	/**
	 * @description 
	 * @param invocation
	 * @return
	 * @throws Throwable
	 * @author qianye.zheng
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		/*
		 * 1) 限制指定业务Mapper使用
		 * 2) 限制指定方法使用
		 */
		
		
		Object[] args = invocation.getArgs();
		
		// class org.apache.ibatis.executor.CachingExecutor
		//System.out.println(invocation.getTarget().getClass());
		//MappedStatement ps = (MappedStatement) args[0];
		Object param = args[1];
		// 拦截并修改参数
		if ("update".equals(invocation.getMethod().getName())) { // 对应insert/update/delete
			if (param instanceof City) {
				City city = (City) param;
				city.setFullName(city.getFullName() + "_appendByInterceptor");
			}
		}
		// 修改查询参数
		if ("query".equals(invocation.getMethod().getName())) {
			if (param instanceof MapperMethod.ParamMap) {
				MapperMethod.ParamMap<Object> param2 = (MapperMethod.ParamMap<Object>) param;
				if(param2.containsKey("name") && null == param2.get("name"))
				{// 存在该key，且为空
					param2.put("name", "广州");
				} else {
					//param2.put("id", 8);
				}
				// 可传输任意参数
				param2.put("value", "广东");
				param2.put("valueX", "2222");
			}
			//city.setId("8");
		}
		Object obj = invocation.proceed();
		
		return obj;
	}

	/**
	 * @description 
	 * @param target
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Object plugin(Object target) {
	     return Plugin.wrap(target, this);
	}

	/**
	 * @description 
	 * @param properties
	 * @author qianye.zheng
	 */
	@Override
	public void setProperties(Properties properties) {
	}

}
