package com.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplilcation {

    private static final Logger LOG = LoggerFactory.getLogger(EurekaApplilcation.class);

//    public static void main(String[] args) {
//        SpringApplication.run(EurekaApplilcation.class, args);
//    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EurekaApplilcation.class);
        ConfigurableEnvironment env = springApplication.run(args).getEnvironment();
        LOG.info("----启动成功----");
        LOG.info("Eureka地址：\t http://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
