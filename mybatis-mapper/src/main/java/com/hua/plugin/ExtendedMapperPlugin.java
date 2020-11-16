package com.hua.plugin;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import com.google.common.collect.Sets;

import tk.mybatis.mapper.annotation.ColumnType;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;
import tk.mybatis.mapper.generator.MapperPlugin;

/**
 * 扩展通用Mapper插件
 *
 * @author haifan.chen
 * @date 2018/12/18 16:48
 */
public class ExtendedMapperPlugin extends MapperPlugin implements ICommentRemarks {

    /**
     * 数据库ID方言
     */
    private IdentityDialect identityDialect = null;

    /**
     * Java中关键词字段属性过滤，如果数据库字段中含有Java关键词的字段属性名称，需要进行特殊处理
     */
    private static final Set<String> JAVA_KEYWORDS = Collections.unmodifiableSet(Sets.newHashSet(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for",
            "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package",
            "private", "protected", "public", "return", "strictfp", "short", "static", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "try", "void", "volatile", "while"));

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        introspectedTable.getAllColumns().forEach(introspectedColumn -> {
            String javaProperty = introspectedColumn.getJavaProperty();
            if (JAVA_KEYWORDS.contains(javaProperty)) {
                javaProperty += "0";
            }
            introspectedColumn.setJavaProperty(javaProperty);
        });
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String database = getProperty("databaseDialect");
        if (database == null) {
            this.identityDialect = IdentityDialect.NULL;
        } else {
            this.identityDialect = IdentityDialect.getDatabaseDialect(database);
        }
    }

    /**
     * 
     * @description 
     * @param topLevelClass
     * @param introspectedTable
     * @return
     * @author qianye.zheng
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("com.hua" + ".entity.DynamicTableEntity");
        topLevelClass.setSuperClass("DynamicTableEntity");

        topLevelClass.addImportedType("lombok.EqualsAndHashCode");
        topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = true)");

        addCommentRemarks(topLevelClass, introspectedTable.getRemarks());
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addCommentRemarks(topLevelClass, introspectedTable.getRemarks());
        return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addCommentRemarks(topLevelClass, introspectedTable.getRemarks());
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        topLevelClass.addImportedType(ColumnType.class.getName());
        topLevelClass.addImportedType(JdbcType.class.getName());

        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        if (introspectedColumn.isAutoIncrement()
                || introspectedColumn.isIdentity()
                || (primaryKeyColumns.size() == 1 && primaryKeyColumns.get(0) == introspectedColumn)) {
            if (identityDialect == null || identityDialect == IdentityDialect.DEFAULT || identityDialect == IdentityDialect.NULL) {
                field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            } else {
                topLevelClass.addImportedType(KeySql.class.getName());
                topLevelClass.addImportedType(IdentityDialect.class.getName());

                field.addAnnotation("@KeySql(dialect = IdentityDialect." + identityDialect.name() + ")");
            }
        }

        JdbcType jdbcType = JdbcType.forCode(introspectedColumn.getJdbcType());
        if (jdbcType == null) {
            jdbcType = JdbcType.VARCHAR;
        }
        field.addAnnotation("@ColumnType(jdbcType = JdbcType." + jdbcType.name() + ")");

        return true;
    }
}
