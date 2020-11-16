/**
  * @filename CityExtendMapper.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.mapper.extend;

import java.sql.Clob;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hua.mapper.CoreMapper;
import com.hua.orm.entity.extend.CityExtend;

 /**
 * @type CityExtendMapper
 * @description 
 * @author qye.zheng
 */
public abstract interface CityExtendMapper extends CoreMapper<String, CityExtend> {

	/**
	 * 
	 * @description 添加列 DDL
	 * ALTER TABLE ${tableName} ADD ${columName} ${dataType};
	 * tableName
	 * columName
	 * dataType
	 * @param param
	 * @author qye.zheng
	 */
	public abstract void addColumn(final Map<String, String> param);
	
	/**
	 * 
	 * @description 测试 sqlServer
	 * @param city
	 * @return
	 * @author qye.zheng
	 */
	public abstract int insertOfIdentity(final CityExtend city);
	
	public abstract CityExtend getFullObject(@Param("id") final String id);
	
	
	/**
	 * 
	 * @description 
	 * @param value
	 * @param key
	 * @return
	 * @author qianye.zheng
	 */
	public Clob getByte(@Param("value") final String value, @Param("key") final String key);
	
	/**
	 * 
	 * @description 
	 * @param value
	 * @param key
	 * @return
	 * @author qianye.zheng
	 */
	public String getHex(@Param("value") final String value, @Param("key") final String key);
}
