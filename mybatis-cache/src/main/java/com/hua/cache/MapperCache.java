/**
  * @filename MapperCache.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.cache;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.type.Alias;

/**
 * @type MapperCache
 * @description MyBatis Mapper 接口缓存
 * 
 * 发现 定义该缓存，只在第一次查询的时候使用了putObject方法，
 * 查询条件变化后的再次查询，并不经过putObject，而是直接走PerpetualCache的putObject
 * 
 * @author qianye.zheng
 */
@Alias("MapperCache")
public class MapperCache implements Cache {
	
	/* 大小限制，超过了则不缓存 (应对列表导出或其他结果集很大的查询，可以避免缓存) */
	private int limitSize = 100;

	/* 代理 */
	private PerpetualCache delegate;
	
	/**
	 * @description 构造方法
	 * @param id
	 * @author qianye.zheng
	 */
	public MapperCache(String id) {
		delegate = new PerpetualCache(id);
	}
	
	@Override
	  public String getId() {
	    return delegate.getId();
	  }

	  @Override
	  public int getSize() {
	    return delegate.getSize();
	  }

	  /**
	   * 
	   * @description 
	   * @param key
	   * @param value
	   * @author qianye.zheng
	   */
	  @Override
	  public void putObject(final Object key, final Object value) {
		  if (null == value) {
			  return;
		  }
		  if (value instanceof Collection) {
			  final Collection<?> collect = (Collection<?>) value;
			  if (collect.size() >= limitSize) { // 不缓存
				  return;
			  }
		  }
		  if (value instanceof Map) {
			  final Map<?, ?> collect = (Map<?, ?>) value;
			  if (collect.size() >= limitSize) { // 不缓存
				  return;
			  }
		  }
		  delegate.putObject(key, value);
	  }

	  @Override
	  public Object getObject(Object key) {
	    return delegate.getObject(key);
	  }

	  @Override
	  public Object removeObject(Object key) {
	    return delegate.removeObject(key);
	  }

	  @Override
	  public void clear() {
		  delegate.clear();
	  }

	  @Override
	  public ReadWriteLock getReadWriteLock() {
	    return delegate.getReadWriteLock();
	  }

	  @Override
	  public boolean equals(Object o) {
		  return delegate.equals(o);
	  }

	  @Override
	  public int hashCode() {
		  return delegate.hashCode();
	  }
}
