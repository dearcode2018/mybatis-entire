/**
  * @filename CitySqlProvider.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.Map;

import com.hua.util.SqlUtil;
import com.hua.util.StringUtil;

 /**
 * @type CitySqlProvider
 * @description 
 * @author qye.zheng
 */
public class CitySqlProvider {

	private static final String TABLE_NAME = "city";
	
	private static final String ESCAPE  = " ESCAPE '/' ";
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public final String insertSql()
	{
		BEGIN();
		INSERT_INTO(TABLE_NAME);
		VALUES("id", "#{id}");
		VALUES("name", "#{name}");
		VALUES("shortName", "#{shortName}");
		VALUES("fullName", "#{fullName}");
		VALUES("province", "#{province}");
		VALUES("postalCode", "#{postalCode}");
		String sql = SQL();
		System.out.println(sql);
		
		return sql;
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public final String deleteSql()
	{
		BEGIN();
		DELETE_FROM(TABLE_NAME);
		WHERE("id = #{id}");
		
		return SQL();
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qye.zheng
	 */
	public final String updateSql()
	{
		BEGIN();
		UPDATE(TABLE_NAME);
		SET("id = #{id}");
		SET("name = #{name}");
		SET("shortName = #{shortName}");
		SET("fullName = #{fullName}");
		SET("province = #{province}");
		SET("postalCode = #{postalCode}");
		WHERE("id = #{id}");
		
		return SQL();
	}
	
	/* ============== 查询 ============ */
	
	/**
	 * 
	 * @description 
	 * @param param
	 * @return
	 * @author qye.zheng
	 */
	public final String getSql(final Map<String, Object> param)
	{
		BEGIN();
		String id = (String) param.get("param1");
		String name = (String) param.get("param2");
		String shortName = (String) param.get("param3");
		String fullName = (String) param.get("param4");
		SELECT("a.*");
		FROM(TABLE_NAME + " a");
		if (!StringUtil.isEmpty(id))
		{
			WHERE("a.id = " + id);	
		}
		
		// WHERE 自动添加 AND
		if (!StringUtil.isEmpty(name))
		{
			WHERE("a.name LIKE '" + SqlUtil.likeQuery(name) + "'" + ESCAPE);
		}
		
		if (!StringUtil.isEmpty(shortName))
		{
			WHERE("a.shortName LIKE '" + SqlUtil.likeQuery(shortName) + "'" + ESCAPE);	
		}
		
		if (!StringUtil.isEmpty(fullName))
		{
			WHERE("a.fullName LIKE '" + SqlUtil.likeQuery(fullName) + "'" + ESCAPE);	
		}
		
		String sql = SQL();
		System.out.println(sql);
		
		return sql;
	}
	
}
