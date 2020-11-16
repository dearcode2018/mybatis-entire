/**
  * @filename StartMyBatisGenerator.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.plugin;

import org.mybatis.generator.api.ShellRunner;

import com.hua.util.ClassPathUtil;

/**
 * @type StartMyBatisGenerator
 * @description 
 * @author qianye.zheng
 */
public final class StartMyBatisGenerator
{

	/**
	 * @description 
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		if (args.length == 0) {
			System.setProperty("ES_SERVER", "172.25.62.240:41414,172.25.62.241:41414");
		}
		String[] params = {"-configfile", ClassPathUtil.getClassPath("/mybatis-generator.xml"), "-overwrite"};
		ShellRunner.main(params);
	}

}
