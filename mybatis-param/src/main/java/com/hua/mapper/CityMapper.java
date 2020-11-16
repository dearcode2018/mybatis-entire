/**
  * @filename CityMapper.java
  * @description 
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hua.entity.City;
import com.hua.vo.CityVo;

 /**
 * @type CityMapper
 * @description 
 * @author qye.zheng
 */
public abstract interface CityMapper extends CoreMapper<String, City> {

	/**
	mybatis Mapper接口参数分析
	
	1) 单个bean/entity
	
	2) 多个bean/entity 命名或无命名
	
	3) 单个Map
	
	4) 多个Map
	
	5) 单个基本类型
	
	6) 多个基本类型 命名或无命名
	
	7) 单个List
	
	8) 多个List 命名或无命名
	
	9) 组合型: Map中value是List、数组、bean
	
	分析: 在mybatis中，所有入参全部封装成Map结构，通过key或index来获取相应的值	  
	*/

	/**
	 * basic / map / bean / list / set / 
	 * name / noName
	 * single / multiple
	 * 共20个，map再加2个作为综合应用
	 */
	
	/**
	 * 
	 * @description 单个基本类型
	 * @param id
	 * @return
	 * @author qye.zheng
	 */
	public abstract CityVo singleBasicNoNameParam(final String id);
	
	/**
	 * 
	 * @description 单个基本类型
	 * @param id
	 * @return
	 * @author qye.zheng
	 */
	public abstract CityVo singleBasicNameParam(@Param("id") final String id);
	
	/**
	 * 
	 * @description 多个基本类型的无命名参数
	 * 说明: 在基本类型中，无论是单个还是多个，都必须通过@Param()标识其名称，才能通过名称的方式来引用
	 * 没有命名的参数，命名默认为param1, param2, ...，对应参数列表(从1开始)...
	 * @param id
	 * @param name
	 * @param shortName
	 * @param fullName
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleBasicNoNameParam(final String id, final String name, 
			final String shortName, final String fullName);

	/**
	 * 
	 * @description 多个基本类型的命名参数
	 * @param id
	 * @param name
	 * @param shortName
	 * @param fullName
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleBasicNameParam(@Param("id") final String id, @Param("name") final String name, 
			@Param("shortName") final String shortName, @Param("fullName") final String fullName);
	
	/**
	 * 
	 * @description 单个Map
	 * @param map
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleMapNoNameParam(final Map<String, Object> map);

	/**
	 * 
	 * @description 单个命名的Map(一般情况下map/bean是无需再命名的)
	 * @param param
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleMapNameParam(@Param("myMap") final Map<String, Object> param);
	
	/**
	 * 
	 * @description 单个Map中含有数组/集合(List/Set/Map)/bean
	 * @param param
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleMapAllNoNameParam(final Map<String, Object> param);

	/**
	 * 
	 * @description 单个Map中含有数组/集合(List/Set/Map)/bean
	 * @param param
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleMapAllNameParam(@Param("myMap") final Map<String, Object> param);
	
	/**
	 * 
	 * @description 多个无命名Map参数
	 * @param map1
	 * @param map2
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleMapNoNameParam(final Map<String, String> map1, final Map<String, String> map2);
	
	/**
	 * 
	 * @description 多个命名Map参数
	 * @param map1
	 * @param map2
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleMapNameParam(@Param("map1") final Map<String, String> map1, 
			@Param("map2") final Map<String, String> map2);
	
	/**
	 * 
	 * @description 多个bean 无命名参数
	 * @param vo
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleBeanNoNameParam(final CityVo vo);

	/**
	 * 
	 * @description 多个bean 命名参数
	 * @param vo
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleBeanNameParam(@Param("vo") final CityVo vo);
	
	/**
	 * 
	 * @description 多个bean 无命名参数
	 * @param vo1
	 * @param vo2
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleBeanNoNameParam(final CityVo vo1, final CityVo vo2);
	
	/**
	 * 
	 * @description 多个bean 命名参数
	 * @param vo1
	 * @param vo2
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleBeanNameParam(@Param("vo1") final CityVo vo1, 
			@Param("vo2") final CityVo vo2);
	
	/**
	 * 
	 * @description 单个基本类型
	 * @param id
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleListNoNameParam(final List<String> list);

	/**
	 * 
	 * @description 单个基本类型
	 * @param id
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleListNameParam(@Param("myList") final List<String> list);
	
	/**
	 * 
	 * @description 
	 * @param list1
	 * @param list2
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleListNoNameParam(final List<String> list1, final List<String> list2);
	
	/**
	 * 
	 * @description 
	 * @param list1
	 * @param list2
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleListNameParam(@Param("list1") final List<String> list1, 
			@Param("list2") final List<String> list2);
	
	/* ====================================================================== */
	/**
	 * 
	 * <!-- Set类型 方式可行? -->
	 */
	
	/**
	 * 
	 * @description 
	 * @param set
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleSetNoNameParam(final Set<String> set);
	
	/**
	 * 
	 * @description 
	 * @param set
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> singleSetNameParam(@Param("mySet") final Set<String> set);
	
	/**
	 * 
	 * @description 
	 * @param set
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleSetNoNameParam(final Set<String> set1,  final Set<String> set2);
	
	/**
	 * 
	 * @description 
	 * @param set
	 * @return
	 * @author qye.zheng
	 */
	public abstract Collection<CityVo> multipleSetNameParam(@Param("set1") final Set<String> set1,
			@Param("set2") final Set<String> set2);
}
