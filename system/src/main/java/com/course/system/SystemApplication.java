package com.course.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class SystemApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SystemApplication.class);
        ConfigurableEnvironment env = springApplication.run(args).getEnvironment();
        LOG.info("----启动成功----");
        LOG.info("System地址：\t http://127.0.0.1:{}", env.getProperty("server.port"));
    }

}