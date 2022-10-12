package com.grapefruit.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 主程序
 *
 * @author grapefruit
 * @date 2021/05/29
 */

@SpringBootApplication
@EnableTransactionManagement
//@NacosPropertySource(dataId = "grape",groupId = "dev_group",autoRefreshed = true)
public class SpringbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
        Object masterDB = context.getBean("masterDB");
        Object slaveDB = context.getBean("slaveDB");
    }
}
