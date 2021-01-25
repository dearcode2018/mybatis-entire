/**
  * @filename DynamicTableInterceptor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.hua.entity.DynamicTableEntity;
import com.hua.util.StringUtil;


/**
 * @type DynamicTableInterceptor
 * @description 
 * @author qianye.zheng
 */
public class DynamicTableInterceptor implements InnerInterceptor {
    
    /**
     * @description 
     * @param sh
     * @param connection
     * @param transactionTimeout
     * @author qianye.zheng
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public void beforePrepare(final StatementHandler sh, final Connection connection, final Integer transactionTimeout) {
        final BoundSql boundSql = sh.getBoundSql();
        final Object target = boundSql.getParameterObject();
        // 逻辑表名
        String logicalTableName = null;
        // 真实表名
        String realTableName = null;
        if (target instanceof DynamicTableEntity) { // insert/update
            final TableName table = target.getClass().getAnnotation(TableName.class);
            if (null == table) return;  // 忽略
            logicalTableName = table.value();
            final DynamicTableEntity dynamic = (DynamicTableEntity) target;
            realTableName = dynamic.getDynamicTableName();
        } else if (target instanceof MapperMethod.ParamMap) {
            final MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) target;
            final Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                final Object param = entry.getValue();
                // 真实类型
                Object realType = param;
                if (param instanceof Wrapper) { // 使用于: update/delete/select
                    realType = ((Wrapper<?>) param).getEntity();
                }
                if (realType instanceof DynamicTableEntity) {
                    final TableName table = realType.getClass().getAnnotation(TableName.class);
                    if (null == table) return;  // 忽略
                    logicalTableName = table.value();
                    final DynamicTableEntity tableEntity = (DynamicTableEntity) realType;
                    realTableName = tableEntity.getDynamicTableName();
                    // 结束循环
                    break;
                }
            }
        }
        try { // 反射 修改SQL
            // 替换表名 生成新的sql
            if (StringUtil.isNotEmpty(logicalTableName) && StringUtil.isNotEmpty(realTableName)) { // 非空才修改SQL语句
                final Field field = BoundSql.class.getDeclaredField("sql");
                // 允许访问和设置
                field.setAccessible(true);
                // 替换表名 ，用波浪号把 @Table中的表名包围起来
                final String sql = field.get(boundSql).toString().replace(logicalTableName, realTableName);
                field.set(boundSql, sql);
            }
        } catch (Exception e) { // keep silence
        }
    }
    
}
