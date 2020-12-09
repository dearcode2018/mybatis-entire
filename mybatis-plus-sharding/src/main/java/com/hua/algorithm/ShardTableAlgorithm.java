/**
  * @filename ShardTableAlgorithm.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shardingsphere.api.sharding.ShardingValue;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import com.hua.util.JacksonUtil;


/**
 * @type ShardTableAlgorithm
 * @description 
 * @author qianye.zheng
 */
public class ShardTableAlgorithm implements ComplexKeysShardingAlgorithm {

    /**
     * @description 
     * @param availableTargetNames
     * @param shardingValue
     * @return
     * @author qianye.zheng
     */
    @Override
    public Collection doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        // availableTargetNames:["t_new_order_0000","t_new_order_0001"],
        // shardingValues:[{"columnName":"order_id","logicTableName":"t_new_order","values":["OD010001011903261549424993200011"]},{"columnName":"user_id","logicTableName":"t_new_order","values":["UD030001011903261549424973200007"]}]
        Collection<String> collection = new ArrayList<>();
        if (shardingValue.getLogicTableName().equals("person")) {
            // 取第一个字符 拼接逻辑表名
            collection.add(shardingValue.getLogicTableName() + "_" + shardingValue.getColumnNameAndShardingValuesMap().get("name").toString().substring(1, 2));
        }
        System.out.println(JacksonUtil.writeAsString(shardingValue));
        
        // 7. 返回表路由结果
        return collection;
    }
    
}
