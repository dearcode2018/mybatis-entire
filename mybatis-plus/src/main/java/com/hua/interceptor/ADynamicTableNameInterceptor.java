/**
  * @filename ADynamicTableNameInterceptor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.interceptor;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.hua.configuration.DynamicTableNameContex;
import com.hua.util.StringUtil;


/**
 * @type ADynamicTableNameInterceptor
 * @description 动态表名拦截器
 * @author qianye.zheng
 */
public class ADynamicTableNameInterceptor extends DynamicTableNameInnerInterceptor {
    
    /**
     * @description 
     * @param sql
     * @return
     * @author qianye.zheng
     */
    @Override
    protected String changeTable(final String sql) {
        final TableNameParser parser = new TableNameParser(sql);
        final List<TableNameParser.SqlToken> names = new ArrayList<>();
        parser.accept(names::add);
        final StringBuilder builder = new StringBuilder();
        int last = 0;
        for (TableNameParser.SqlToken name : names) {
            int start = name.getStart();
            if (start != last) {
                builder.append(sql, last, start);
                // 表名称
                final String value = name.getValue();
                final TableNameHandler handler = (sqlX, tableName) -> {
                    final String dynamic = DynamicTableNameContex.getTableName();
                    // 清理掉，需要使用的时候再设置
                    DynamicTableNameContex.clear();
                    // 非空 且 具有相同的前缀，减少替换错误的发生
                    if (StringUtil.isNotEmpty(dynamic) && dynamic.startsWith(value)) {
                        return dynamic;
                    }
                    // 使用原先的表名
                    return tableName;
                };
                builder.append(handler.dynamicTableName(sql, value));
            }
            last = name.getEnd();
        }
        if (last != sql.length()) {
            builder.append(sql.substring(last));
        }
        return builder.toString();
    }
    
}
