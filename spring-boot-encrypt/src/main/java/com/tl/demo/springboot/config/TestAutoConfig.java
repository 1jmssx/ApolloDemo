package com.tl.demo.springboot.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("test.autoconfig.enable")
public class TestAutoConfig {

    @Bean
    public DemoAutoConfigBean demoAutoConfigBean(){
        System.out.println("inject bean");
        return new DemoAutoConfigBean();
    }
}
