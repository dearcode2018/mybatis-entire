/**
  * @filename DynamicTableNameContex.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configuration;

 
/**
 * @type DynamicTableNameContex
 * @description 动态表名上下文
 * @author qianye.zheng
 */
public class DynamicTableNameContex {
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    
    private DynamicTableNameContex() {}
    
    /**
     * 
     * @description 
     * @return
     * @author qianye.zheng
     */
    public static final String getTableName() {
        return  contextHolder.get();
    }
    
    /**
     * 
     * @description 
     * @param tableName
     * @author qianye.zheng
     */
    public static final void setTableName(final String tableName) {
        contextHolder.set(tableName);
    }
    
    /**
     * 
     * @description 
     * @author qianye.zheng
     */
    public static final void clear() {
        contextHolder.remove();
    }
    
}
