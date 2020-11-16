package com.hua.plugin;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

import static org.mybatis.generator.api.dom.java.JavaVisibility.PRIVATE;
import static org.mybatis.generator.api.dom.java.JavaVisibility.PUBLIC;

public class GenerateServicePlugin extends org.mybatis.generator.api.PluginAdapter {

    final String VIEW_PACKAGE = "com.wehotel.price.view.";
    final String QUERY_PACKAGE = "com.wehotel.price.bean.";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        String domainName = getDomainName(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        System.out.println(introspectedTable.getDAOImplementationType());
        System.out.println(introspectedTable.getMyBatis3JavaMapperType());
        System.out.println(introspectedTable.getDAOInterfaceType());

        CompilationUnit view = getViewUnit(domainName,introspectedTable.getAllColumns());
        CompilationUnit query = getQueryUnit(domainName,introspectedTable.getBaseRecordType());
        CompilationUnit service = getServiceCompilationUnit(view,query,introspectedTable);

        GeneratedJavaFile serviceFile = new GeneratedJavaFile(service,"src/main/java",introspectedTable.getContext().getJavaFormatter());
        GeneratedJavaFile viewFile = new GeneratedJavaFile(view,"src/main/java",introspectedTable.getContext().getJavaFormatter());
        GeneratedJavaFile queryFile = new GeneratedJavaFile(query,"src/main/java",introspectedTable.getContext().getJavaFormatter());

        return Arrays.asList(viewFile,queryFile,serviceFile);

    }

    private String getDomainName(String origin){

        return "Zero" + origin;

    }

    private CompilationUnit getQueryUnit(String name,String superClass){
        TopLevelClass levelClass = new TopLevelClass(QUERY_PACKAGE + name + "Query");
        levelClass.setVisibility(PUBLIC);
        levelClass.setSuperClass(superClass);
        levelClass.addImportedType(superClass);
        return levelClass;
    }

    private CompilationUnit getViewUnit(String name, List<IntrospectedColumn> columns){

        TopLevelClass levelClass = new TopLevelClass(VIEW_PACKAGE + name + "VO");
        levelClass.setVisibility(PUBLIC);

        columns.stream().forEach(c -> {
            Field field = new Field(c.getJavaProperty(),c.getFullyQualifiedJavaType());
            field.setVisibility(PRIVATE);
            levelClass.addField(field);
            levelClass.addImportedType(c.getFullyQualifiedJavaType());
        });

        return levelClass;
    }

    private CompilationUnit getServiceCompilationUnit(CompilationUnit view,CompilationUnit query,IntrospectedTable introspectedTable){

        TopLevelClass unit = new TopLevelClass("com.wehotel.price.service.local." + getDomainName(introspectedTable.getFullyQualifiedTable().getDomainObjectName()) + "Service");
        unit.setVisibility(PUBLIC);
        unit.addImportedType(view.getType());
        unit.addImportedType(query.getType());
        unit.addImportedType(introspectedTable.getBaseRecordType());
        unit.addImportedType(introspectedTable.getMyBatis3JavaMapperType());
        unit.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        unit.addImportedType("org.springframework.beans.factory.annotation.Autowired");
        unit.addImportedType("org.springframework.beans.BeanUtils");

        Field mapper = new Field("mapper",new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType()));
        mapper.addAnnotation("@Autowired");
        unit.addField(mapper);

        unit.addMethod(genSave(view.getType(),introspectedTable.getBaseRecordType()));
        unit.addMethod(genGet(view.getType()));
        unit.addMethod(genList(query.getType(),introspectedTable.getBaseRecordType()));
        unit.addMethod(genUpdate(view.getType()));

        return unit;
    }


    private Method genGet(FullyQualifiedJavaType view){

        Method getMethod = new Method("get");
        getMethod.setVisibility(PUBLIC);

        Parameter parameter = new Parameter(FullyQualifiedJavaType.getIntInstance(),"id");
        getMethod.addParameter(parameter);
        getMethod.setReturnType(view);
        getMethod.addBodyLine("return null;");

        return getMethod;
    }

    private Method genList(FullyQualifiedJavaType query,String record){

        Method method = new Method("list" );
        method.setVisibility(PUBLIC);

        Parameter queryParam = new Parameter(query,"query");
        method.addParameter(queryParam);


        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(record);
        Class<? extends FullyQualifiedJavaType> aClass = fullyQualifiedJavaType.getClass();

        System.out.println(record);

        FullyQualifiedJavaType listResult = FullyQualifiedJavaType.getNewListInstance();
        listResult.addTypeArgument(new FullyQualifiedJavaType(record));


        method.setReturnType(listResult);
        method.addBodyLine("return null;");

        return method;
    }

    private Method genSave(FullyQualifiedJavaType view,String recordType){

        Method method = new Method("save" );
        method.setVisibility(PUBLIC);

        Parameter queryParam = new Parameter(view,"view");
        method.addParameter(queryParam);

        String simple = recordType.substring(recordType.lastIndexOf(".") + 1);

        try {
            File file = ResourceUtils.getFile("classpath:save.my");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.lines().forEach(line -> {
                String body = line.replaceAll("\\{record\\}", simple);
                method.addBodyLine(body);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return method;
    }

    private Method genUpdate(FullyQualifiedJavaType view){

        Method method = new Method("update");
        method.setVisibility(PUBLIC);

        Parameter queryParam = new Parameter(view,"view");
        method.addParameter(queryParam);

        method.addBodyLine("");

        return method;

    }

}
