/**
  * @filename CityVo.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

 /**
 * @type CityVo
 * @description 
 * @author qye.zheng
 */
@Data
@EqualsAndHashCode(callSuper=false)
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
	
	private Integer random = 1;
	
}
