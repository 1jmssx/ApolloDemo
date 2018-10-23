package com.tl.apollo.demo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DemoApplication {


    public static void main(String... args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:conf/application-demo.xml");

        //通过spring context获取bean
        TestDataSource bean = context.getBean(TestDataSource.class);

        while (true){
            Thread.sleep(5000);
            System.out.println(bean);
            if ("stop".equals(bean.getName())){
                break;
            }
        }
    }
}
