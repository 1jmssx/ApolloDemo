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

import java.util.List;
import java.util.Map;

/**
 * 测试
 * 各种数据类型支持
 * 加密支持
 * 多命名空间支持
 */
@SpringBootApplication
@EnableApolloConfig({"application","TEST1.product.switch"})//如果使用多个namespace需要在注解中指定，apollo会自动加载对应namespace的配置信息
public class DataTypeTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataTypeTestApplication.class, args);
    }

    @Value("${test.encrypt:远程及本地副本均无此配置}")
    private String desyptValue;
    @Value("${test.type.string}")
    private String testTypeString;
    @Value("${test.type.int}")
    private int testTypeInt;
    @Value("${test.type.long}")
    private long testTypeLong;
    @Value("${test.type.double}")
    private double testTypeDouble;
    @Value("${test.type.float}")
    private float testTypeFloat;
    @Value("${test.type.boolean}")
    private boolean testTypeBoolean;
    @Value("#{'${test.type.stringList}'.split(',')}")
    private List<String> testTypeStringList;
    @Value("#{'${test.type.intList}'.split(',')}")
    private List<Integer> testTypeIntList;
    @Value("#{${test.type.map}}")
    private Map<String, String> testTypeMap;
    @Value("${product.order.on:true}")
    private boolean orderFlag;

    @Autowired
    private ApplicationContext context;

    private void doTest(){

        //使用api获取指定namespace的配置内容
        Config appConfig = ConfigService.getAppConfig();

        //apollo会自动将远程配置加载到environment中，并将对应的propertysource置于第一个位置，保证远程配置优先级最高
        Environment environment = context.getEnvironment();


        System.err.println("test.encrypt 值 ENC(uXIr5MGo1vSFTnJfK0Y7mQ==) 解密后:" + desyptValue);
        System.err.println("test.type.string 可加载String类型配置:" + testTypeString);
        System.err.println("test.type.int 可加载int类型配置:" + testTypeInt);
        System.err.println("test.type.long 可加载long类型配置:" + testTypeLong);
        System.err.println("test.type.double 可加载double类型配置:" + testTypeDouble);
        System.err.println("test.type.float 可加载float类型配置:" + testTypeFloat);
        System.err.println("test.type.boolean 可加载boolean类型配置:" + testTypeBoolean);
        System.err.println("namespace:TEST1.product.switch   product.order.on config is:" + orderFlag);
        testTypeStringList.stream().forEach(value->{
            System.err.println("test.type.stringList value contains:" + value);
        });
        testTypeIntList.stream().forEach(value->{
            System.err.println("test.type.intList value contains:" + value);
        });
        testTypeMap.entrySet().stream().forEach(entry -> {
            System.err.println("test.type.map contains entry:"+entry.getKey()+"_"+entry.getValue());
        });
    }

    @Override
    public void run(String... args) throws Exception {

        doTest();
    }
}
