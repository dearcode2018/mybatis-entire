/**
  * @filename CityVo.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.vo;

import com.hua.vo.BaseVo;

 /**
 * @type CityVo
 * @description 
 * @author qye.zheng
 */
public class CityVo extends BaseVo {

	private static final long serialVersionUID = 1L;
	
	/* 名称 */
	private String name;
	
	/* 简称 */
	private String shortName;
	
	/* 全称 */
	private String fullName;
	
	/* 省份 */
	private String province;
	
	/* 邮政编码 */
	private String postalCode;

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shortName
	 */
	public final String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public final void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the fullName
	 */
	public final String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public final void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the province
	 */
	public final String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public final void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the postalCode
	 */
	public final String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public final void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}
