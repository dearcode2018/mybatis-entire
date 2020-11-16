/**
 * 描述: 
 * Person.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hua.constant.ext.Gender;

import lombok.Data;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * Person
 */
@Data
@TableName("person")
public final class Person
{

	/* 对象唯一id */
	@TableId
	private int oid;
	
	/* 姓名 */
	private String name;
	
	/* 证件照片url 字段名和数据表 列名 一一对应，驼峰对应下划线 */
	//private String photoUrl;
	
	/* 性别 : 0-未知, 1-男(male), 2-女(female) */
	//private Gender gender = Gender.UNKNOW;
	
	/* 民族 */
	private String nation;
	
	/* 出生日期 yyyy-MM-dd */
	private Date birthday;
	
	/* 住址 */
	private String address;
	
}
