package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FreeMarkerUtil {

    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl";
    static Template template;

    public static void initConfig(String fileName) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        configuration.setDirectoryForTemplateLoading(new File(ftlPath));
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        template = configuration.getTemplate(fileName);
    }

    public static void generator (String fileName) throws IOException, TemplateException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        template.process(null, bufferedWriter);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

}
