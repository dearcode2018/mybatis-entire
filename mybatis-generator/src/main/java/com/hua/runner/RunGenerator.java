/**
  * @filename RunGenerator.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.hua.util.ClassPathUtil;

/**
 * @type RunGenerator
 * @description 
 * @author qianye.zheng
 */
public class RunGenerator
{
	
	/**
	 * 
	 * @description 在程序中执行调用 mybatis generator
	 * @author qianye.zheng
	 */
	@Test
	public void runWithXml()
	{
		final List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		try {
			final File configfile = new File(ClassPathUtil.getClassPath("/mybatis-generator.xml"));
			/*
			 * 	ConfigurationParser 构造函数接收 属性Properties对象
			 * 例如: generated.source.dir
			 */
			final ConfigurationParser parser = new ConfigurationParser(warnings);
			final Configuration config = parser.parseConfiguration(configfile);
			final DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			final MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
			// 调用生成
			// 无回调
			generator.generate(null);
			//
/*			generator.generate(new ProgressCallback() {
				*//**
				 * @description 
				 * @author qianye.zheng
				 *//*
				@Override
				public void done()
				{
					System.out.println("job has finished");
				}
				@Override
				public void introspectionStarted(int totalTasks)
				{
				}
				@Override
				public void generationStarted(int totalTasks)
				{
				}
				@Override
				public void saveStarted(int totalTasks)
				{
				}
				@Override
				public void startTask(String taskName)
				{
				}
				@Override
				public void checkCancel() throws InterruptedException
				{
				}
			});
			*/
			// 输出警告信息
			for (String e : warnings)
			{
				System.out.println(e);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 在程序中执行调用 mybatis generator
	 * @author qianye.zheng
	 */
	@Test
	public void run()
	{
		final List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		try
		{
			/*
			 * 直接通过构造Configuration对象来注入配置
			 * 可以实现配置动态注入
			 * 
			 */
			final Configuration config = new Configuration();
			final Context context = new Context(ModelType.FLAT);
			context.getCommentGeneratorConfiguration().addProperty("suppressDate", "true");
			context.getCommentGeneratorConfiguration().addProperty("suppressAllComments", "true");
			context.getJdbcConnectionConfiguration().setConnectionURL("jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=true");
			// ..
			config.addContext(context);
			
			final DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			final MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
			// 调用生成
			// 无回调
			//generator.generate(null);
			//
/*			generator.generate(new ProgressCallback() {
				*//**
				 * @description 
				 * @author qianye.zheng
				 *//*
				@Override
				public void done()
				{
					System.out.println("job has finished");
				}
				@Override
				public void introspectionStarted(int totalTasks)
				{
				}
				@Override
				public void generationStarted(int totalTasks)
				{
				}
				@Override
				public void saveStarted(int totalTasks)
				{
				}
				@Override
				public void startTask(String taskName)
				{
				}
				@Override
				public void checkCancel() throws InterruptedException
				{
				}
			});
			*/
			// 输出警告信息
			for (String e : warnings)
			{
				System.out.println(e);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 直接调用ShellRunner的主方法
	 * @author qianye.zheng
	 */
	@Test
	public void simpleRun()
	{
		/**
		 * -verbose: 输出进度
		 */
		String[] params = {"-configfile", ClassPathUtil.getClassPath("/mybatis-generator.xml"), 
				"-overwrite", "-verbose"};
		ShellRunner.main(params);
	}
	
}
