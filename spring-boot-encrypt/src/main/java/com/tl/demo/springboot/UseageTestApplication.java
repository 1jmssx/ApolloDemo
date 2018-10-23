package com.tl.demo.springboot;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.tl.demo.springboot.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 测试使用方式
 * @ConfigurationProperty
 */
@SpringBootApplication
@EnableApolloConfig
public class UseageTestApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(UseageTestApplication.class, args);
    }

    private void doTest(){

        TestConfig testConfig = context.getBean(TestConfig.class);
        System.err.println("@ConfigurationProperty : " + testConfig);
    }

    @Override
    public void run(String... args) throws Exception {
//        doTest();
    }
}
