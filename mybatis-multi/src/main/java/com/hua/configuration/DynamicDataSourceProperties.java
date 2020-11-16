/**
  * @filename DynamicDataSourceProperties.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @type DynamicDataSourceProperties
 * @description 动态数据源
 * @author qianye.zheng
 */
@ConfigurationProperties(prefix = "dynamic")
public class DynamicDataSourceProperties {

	
    private Map<String, DataSourceProperties> datasource = new LinkedHashMap<>();

    public Map<String, DataSourceProperties> getDatasource() {
        return datasource;
    }

    public void setDatasource(Map<String, DataSourceProperties> datasource) {
        this.datasource = datasource;
    }
	
}
