/**
  * @filename DynamicDataSource.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @type DynamicDataSource
 * @description 
 * @author qianye.zheng
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.peek();
	}

}
