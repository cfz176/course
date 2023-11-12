package com.course.generator.vue;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreeMarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VueGenerator {

    static String MODULE = "businesss";
    static String toVuePath = "admin\\src\\view\\admin\\";
    static String generatorConfigPath = "server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        String module = MODULE;

        //配置文件路径
        File file = new File(generatorConfigPath);
        //读取配置文件
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        //获取xml文件节点
        Element rootElement = document.getRootElement();
        Element context = rootElement.element("context");
        //定义一个Element用于遍历
        Element tableElement;
        //取第一个“table”的节点
        tableElement = (Element) context.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String tableNameCn = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("Domain:" + tableElement.attributeValue("tableName"));
        System.out.println("表：" + tableElement.attributeValue("tableName"));

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaType(fieldList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        //dto
        FreeMarkerUtil.initConfig("vue.ftl");
        FreeMarkerUtil.generator(toVuePath + domain + ".vue", map);
    }

    public static Set<String> getJavaType(List<Field> fieldList) {
        HashSet<String> fieldSet = new HashSet<>();
        for (int i = 0, j = fieldList.size(); i < j; i++) {
            Field field = fieldList.get(i);
            fieldSet.add(field.getJavaType());
        }
        return fieldSet;
    }
}
