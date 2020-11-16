/**
  * @filename CoreEntity.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.orm.entity.extend;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

 /**
 * @type CoreEntity
 * @description  
 * @author qye.zheng
 */
public abstract class CoreEntity {

	/* 扩展字段 */
	private Map<String, Object> ext = new HashMap<String, Object>();
	
	/**
	 * @return the ext
	 */
	public final Map<String, Object> getExt() {
		return ext;
	}

	/**
	 * @param ext the ext to set
	 */
	public final void setExt(Map<String, Object> ext) {
		this.ext = ext;
	}
	
	 /**
	 * 描述: 
	 * @author qye.zhenge
	 * 
	 * @return
	 */
	@Override
	public String toString()
	{
		return new ReflectionToStringBuilder(this).toString();
	}
}
