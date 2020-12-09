/**
  * @filename ParamInterceptor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.plugin;

import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * @type ParamInterceptor
 * @description 
 * @author qianye.zheng
 */
@Intercepts({
	// 拦截目标类型的多个方法，每个方法一个签名
	@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
})
public class ParamInterceptor implements Interceptor {

	/**
	 * @description 
	 * @param invocation
	 * @return
	 * @throws Throwable
	 * @author qianye.zheng
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		PreparedStatement ps = (PreparedStatement) args[0];
		//DefaultParameterHandler handler = () invocation.getTarget();
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
