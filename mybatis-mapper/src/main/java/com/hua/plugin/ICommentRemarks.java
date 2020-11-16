package com.hua.plugin;

import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * Java元素代码注释
 *
 * @author haifan.chen
 * @date 2018/12/18 16:48
 */
public interface ICommentRemarks {

    /**
     * 为Java元素添加代码注释
     *
     * @param javaElement Java元素
     * @param remarks     注释内容
     */
    default void addCommentRemarks(JavaElement javaElement, String remarks) {
        if (StringUtility.stringHasValue(remarks)) {
            javaElement.addJavaDocLine("/**");
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                javaElement.addJavaDocLine(" * " + remarkLine);
            }
            javaElement.addJavaDocLine(" */");
        }
    }
}
