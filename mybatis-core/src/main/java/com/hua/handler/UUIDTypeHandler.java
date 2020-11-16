package com.hua.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler; 
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * 
 * @type UUIDTypeHandler
 * @description 
 * @author qye.zheng
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

	/**
	 * 
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
			UUID parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, ((UUID) parameter).toString());  
		
	}

	/**
	 * 
	 * @description 
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public UUID getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		if(columnName==null||rs.getString(columnName)==null){
			return null;
		}else{
			return UUID.fromString(rs.getString(columnName)); 
		}
		 
	}

	/**
	 * 
	 * @description 
	 * @param rs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public UUID getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		return UUID.fromString((rs.getString(columnIndex)));  
	}

	/**
	 * 
	 * @description 
	 * @param cs
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 * @author qye.zheng
	 */
	@Override
	public UUID getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		return UUID.fromString((cs.getString(columnIndex)));  
	}


}
