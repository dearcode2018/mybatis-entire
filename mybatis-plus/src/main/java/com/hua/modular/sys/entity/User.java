package com.hua.modular.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author qianye.zheng
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /* 姓名 */
    @TableField("name")
    private String name;

    /* 年龄 */
    @TableField("age")
    private Integer age;

    /* 邮箱 */
    @TableField("email")
    private String email;

    public static final String NAME = "name";

    public static final String AGE = "age";

    public static final String EMAIL = "email";

}
