/**
  * @filename DataSourceContextHolder.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.datasource;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @type DataSourceContextHolder
 * @description 数据源上下文
 * @author qianye.zheng
 */
public class DataSourceContextHolder {

	// 一个线程可以持有多个数据连接对象，因此value是集合 | 适用于嵌套使用数据源的场景
	private static final ThreadLocal<Deque<String>> CONTEXT_HOLDER;
	
	static {
		// 初始化
		CONTEXT_HOLDER = ThreadLocal.withInitial(ArrayDeque :: new);
	}
	
	private DataSourceContextHolder() {}
	
	/**
	 * 
	 * @description 获取当前线程 数据源名 (读取，不删除)
	 * @return
	 * @author qianye.zheng
	 */
	public static final String peek() {
		return CONTEXT_HOLDER.get().peek();
	}
	
	/**
	 * 
	 * @description 设置当前线程数据源名
	 * @param dataSourceName 数据源名
	 * @author qianye.zheng
	 */
	public static final void push(final String dataSourceName) {
		CONTEXT_HOLDER.get().push(dataSourceName);
	}
	
	/**
	 * 
	 * @description 移除当前线程 数据源名
	 * @author qianye.zheng
	 */
	public static final String poll() {
		final Deque<String> deque = CONTEXT_HOLDER.get();
		// 后进先出
		final String value = deque.poll();
		if (deque.isEmpty()) { // 为空则从上下文移除掉
			CONTEXT_HOLDER.remove();
		}
		
		return value;
	}
	
	/**
	 * 
	 * @description 清空当前线程 所有数据源名
	 * @author qianye.zheng
	 */
	public static final void clear() {
		CONTEXT_HOLDER.get().clear();
	}
	
}
