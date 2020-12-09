package com.hua.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Getter;
import lombok.Setter;

/**
 * 提供动态表名支持
 */
public abstract class DynamicTableEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //@Transient
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy =  FieldStrategy.NEVER, 
            whereStrategy = FieldStrategy.NEVER)
    @Setter
    @Getter
    private String dynamicTableName;
    
}
