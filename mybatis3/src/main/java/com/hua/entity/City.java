/**
  * @filename City.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.entity;

import lombok.Data;

/**
 * @type City
 * @description 
 * @author qye.zheng
 */
@Data
public final class City {
	
	/* 用UUID生成 */
	private int id;
	
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

}
