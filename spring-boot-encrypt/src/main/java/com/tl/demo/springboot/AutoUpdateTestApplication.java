package com.tl.demo.springboot;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 测试自动更新生效情况
 * @value、environment、api是否生效。
 * 通过修改apollo配置apollo.autoUpdateInjectedSpringProperties = true，启动热更新，反射修改ioc中的bean实例
 */
@SpringBootApplication
@EnableApolloConfig
public class AutoUpdateTestApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(AutoUpdateTestApplication.class, args);
    }

    @Value("${test.type.string}")
    private String testTypeString;

    private void doTest() throws InterruptedException {

        System.err.println(String.format("@value value is : %s", testTypeString));

        Environment environment = context.getEnvironment();
        String testUpdateStringValue = environment.getProperty("testTypeString");

        System.err.println(String.format("environment value is : %s", testUpdateStringValue));

        Config appConfig = ConfigService.getAppConfig();
        System.err.println(String.format("api get config value is : %s", appConfig.getProperty("testTypeString", "404")));


        while (true){
            AutoUpdateTestApplication autoUpdateTestApplication = context.getBean(AutoUpdateTestApplication.class);
            System.err.println("spring context reget value is : " + autoUpdateTestApplication.testTypeString);
            System.err.println("current class testTypeString value is : " + this.testTypeString);
            if (testTypeString.equals("stop")){
                break;
            }
            Thread.sleep(5000);
        }
    }

    @Override
    public void run(String... args) throws Exception {
//        doTest();
    }
}
