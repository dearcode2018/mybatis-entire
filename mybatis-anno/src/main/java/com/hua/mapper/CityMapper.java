/**
  * @filename CityMapper.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

import com.hua.entity.City;
import com.hua.provider.CitySqlProvider;
import com.hua.vo.CityVo;



 /**
 * @type CityMapper
 * @description 
 * @author qye.zheng
 */
/*
 * 该命名空间允许使用的内置缓存，最大为可以被多少个对象引用，读写默认是开启的，
 * 缓存默认使用 LruCache，缓存内省刷新时间默认为3600000毫秒，
 * 写策略是拷贝整个对象镜像到全新堆(如 CopyOnWriteList ) 因此线程安全
 */
@CacheNamespace(size = 1000)
public abstract interface CityMapper {

	/**
	 * 
	 * @description 
	 * @param city
	 * @author qye.zheng
	 */
	@InsertProvider(type = CitySqlProvider.class, method = "insertSql")
	@Options(useCache = true, flushCache = FlushCachePolicy.DEFAULT, timeout = 50000)
	public abstract void insert(final City city);
	
	/**
	 * 
	 * @description 
	 * @param id
	 * @author qye.zheng
	 */
	@DeleteProvider(type = CitySqlProvider.class, method = "deleteSql")
	@Options(useCache = true, flushCache = FlushCachePolicy.DEFAULT, timeout = 50000)
	public abstract void delete(final String id);
	
	/**
	 * 
	 * @description 
	 * @param city
	 * @author qye.zheng
	 */
	@UpdateProvider(type = CitySqlProvider.class, method = "updateSql")
	@Options(useCache = true, flushCache = FlushCachePolicy.DEFAULT, timeout = 50000)
	public abstract void update(final City city);
	
	/**
	 * 
	 * @description 多个基本类型的无命名参数
	 * 说明: 在基本类型中，无论是单个还是多个，都必须通过@Param()标识其名称，才能通过名称的方式来引用
	 * 没有命名的参数，命名默认为param1, param2, ...，对应参数列表(从1开始)...
	 * @param id
	 * @param name
	 * @param shortName
	 * @param fullName
	 * @return
	 * @author qye.zheng
	 */
	@SelectProvider(type = CitySqlProvider.class, method = "getSql")
	@Options(useCache = true, flushCache = FlushCachePolicy.DEFAULT, timeout = 50000)
	public abstract Collection<CityVo> get(final String id, final String name, 
			final String shortName, final String fullName);
}
