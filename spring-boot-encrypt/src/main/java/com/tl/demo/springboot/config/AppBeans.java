package com.tl.demo.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {

    @Bean
    public TestConfig testConfig(){
        return new TestConfig();
    }
}
