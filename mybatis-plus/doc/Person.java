package com.hua.modular.sys.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hua.entity.DynamicTableEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人信息表
 * </p>
 *
 * @author qianye.zheng
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("person")
public class Person extends  DynamicTableEntity {

    private static final long serialVersionUID = 1L;

    /* 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /* 姓名 */
    @TableField("name")
    private String name;

    /* 证件照片url */
    @TableField("photoUrl")
    private String photourl;

    /* 性别 : Unknow(未知), Male(男), Female-(女) */
    @TableField("gender")
    private String gender;

    /* 民族 */
    @TableField("nation")
    private String nation;

    /* 出生日期 yyyy-MM-dd */
    @TableField("birthday")
    private LocalDateTime birthday;

    /* 住址 */
    @TableField("address")
    private String address;

    /* 身份证主键 */
    @TableField("cardId")
    private Integer cardid;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PHOTOURL = "photoUrl";

    public static final String GENDER = "gender";

    public static final String NATION = "nation";

    public static final String BIRTHDAY = "birthday";

    public static final String ADDRESS = "address";

    public static final String CARDID = "cardId";
    
    /**
     * 
     * @description 
     * @param dynamicTableName
     * @return
     * @author qianye.zheng
     */
    public static final Person dynamicTable(final String dynamicTableName) {
        final Person entity = new Person();
        entity.setDynamicTableName(dynamicTableName);
        
        return entity;
    }

}
