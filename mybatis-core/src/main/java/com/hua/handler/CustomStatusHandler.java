/**
  * @filename CustomStatusHandler.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.hua.constant.ext.CustomStatus;

 /**
 * @type CustomStatusHandler
 * @description 
 * @author qye.zheng
 */
public class CustomStatusHandler extends BaseTypeHandler<CustomStatus> {

	/**
	 * @description 
	 * @param ps
	 * @param i
	 * @param parameter
	 * @param jdbcType
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			CustomStatus parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	/**
	 * @description 
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public CustomStatus getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
	    int i = rs.getInt(columnName);
	    if (rs.wasNull()) {
	    	
	      return null;
	    } else {
	    	
		      return null;
	    	//return CustomStatus.valueOf(i);
	    }
	}

	/**
	 * @description 
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public CustomStatus getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
	    int i = rs.getInt(columnIndex);
	    if (rs.wasNull()) {
	    	
	      return null;
	    } else {
	    	
		      return null;
	    	//return CustomStatus.valueOf(i);
	    }
	}

	/**
	 * @description 
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public CustomStatus getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
	    int i = cs.getInt(columnIndex);
	    if (cs.wasNull()) {
	    	
	      return null;
	    } else {
		      return null;
	    	//return CustomStatus.valueOf(i);
	    }
	}

}
