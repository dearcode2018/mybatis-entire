/**
 * 描述: 
 * SpringMybatisUtil.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * SpringMybatisUtil
 */
public final class SpringMybatisUtil
{

	/*
	 配置基本相对路径
	 ioc部分，主要使用/conf/xml/ioc/ 下的配置
	 */
	private static final String CONFIG_BASE_DIR = "/conf/xml/spring/";
	
	// 基本路径下的多个配置文件，可以添加扩充
	private static final String[] configLocations = {
		"spring-config.xml",
		"spring-mybatis.xml",
		
		};

	private static BeanFactory beanFactory;
	
	static 
	{
		// 拼接上前缀路径
		for (int i = 0; i < configLocations.length; i++)
		{
			configLocations[i] = CONFIG_BASE_DIR + configLocations[i];
		}
		beanFactory = new ClassPathXmlApplicationContext(configLocations);
		/*
		 传一个Class<?> 对象仅仅是为了根据提供的路径获取本地配置文件的完整路径
		 传任何java类都可以，无论是否是当前项目的类，还是其他java类.
		 */
		//beanFactory = new ClassPathXmlApplicationContext(configLocations, String.class);
	}

	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private SpringMybatisUtil()
	{
	}
	
	/**
	 * 
	 * 描述: 获取 Bean 工厂 (xml方式)
	 * @author qye.zheng
	 * @return
	 */
	public static BeanFactory getBeanFactoryOfXml()
	{
		return beanFactory;
	
	}
	
	/**
	 * 
	 * 描述: 获取 Bean 工厂 (注解方式)
	 * @author qye.zheng
	 * @return
	 */
	public static BeanFactory getBeanFactoryOfAnnotation()
	{
		// 注解方式
		//new AnnotationConfigApplicationContext(configClazz);
		return null;
	}
	
	/**
	 * 
	 * 描述:销毁容器
	 * @author  qye.zheng
	 */
	public static void destroy(final BeanFactory beanFactory) {
		// 强转为ApplicationContext，然后再调用销毁方法
		if (beanFactory instanceof ClassPathXmlApplicationContext)
		{
			final ClassPathXmlApplicationContext classPathXmlApplicationContext = (ClassPathXmlApplicationContext) beanFactory;
			classPathXmlApplicationContext.destroy();
		} else
		{
			System.out.println("不是 ClassPathXmlApplicationContext 类型，不支持销毁!");
		}
	}
}
