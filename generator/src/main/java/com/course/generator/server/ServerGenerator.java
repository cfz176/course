package com.course.generator.server;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreeMarkerUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerGenerator {

    static String MODULE = "businesss";
    static String toDtoPath = "server\\src\\main\\java\\com\\course\\server\\dto\\";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = MODULE + "\\src\\main\\java\\com\\course\\" + MODULE + "\\controller\\admin\\";

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaType(fieldList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        //service
        FreeMarkerUtil.initConfig("service.ftl");
        FreeMarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        //controller
        FreeMarkerUtil.initConfig("controller.ftl");
        FreeMarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);

        //dto
        FreeMarkerUtil.initConfig("dto.ftl");
        FreeMarkerUtil.generator(toDtoPath + Domain + "Dto.java", map);
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
