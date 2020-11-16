package com.hua.entity;

import java.io.Serializable;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * 城市表
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "city")
public class City extends DynamicTableEntity implements Serializable {
    /**
     * 主键-自增长
     */
    @Id
    @Column(name = "ID")
    @KeySql(dialect = IdentityDialect.MYSQL)
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "name")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String name;

    /**
     * 简称
     */
    @Column(name = "shortName")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String shortname;

    /**
     * 全称
     */
    @Column(name = "fullName")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String fullname;

    /**
     * 省份
     */
    @Column(name = "province")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String province;

    /**
     * 邮政编码
     */
    @Column(name = "postalCode")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String postalcode;

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";

    public static final String DB_ID = "ID";

    public static final String NAME = "name";

    public static final String DB_NAME = "name";

    public static final String SHORTNAME = "shortname";

    public static final String DB_SHORTNAME = "shortName";

    public static final String FULLNAME = "fullname";

    public static final String DB_FULLNAME = "fullName";

    public static final String PROVINCE = "province";

    public static final String DB_PROVINCE = "province";

    public static final String POSTALCODE = "postalcode";

    public static final String DB_POSTALCODE = "postalCode";

    /**
     * 获取主键-自增长
     *
     * @return ID - 主键-自增长
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键-自增长
     *
     * @param id 主键-自增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取简称
     *
     * @return shortName - 简称
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * 设置简称
     *
     * @param shortname 简称
     */
    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    /**
     * 获取全称
     *
     * @return fullName - 全称
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 设置全称
     *
     * @param fullname 全称
     */
    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取邮政编码
     *
     * @return postalCode - 邮政编码
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalcode 邮政编码
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode == null ? null : postalcode.trim();
    }
}