package com.hua.mapper.auto;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.LruCache;

import com.hua.entity.auto.City;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@CacheNamespace(flushInterval = 60000, eviction = LruCache.class, readWrite = true, blocking = false)
public interface CityMapper extends Mapper<City>, MySqlMapper<City> {
}