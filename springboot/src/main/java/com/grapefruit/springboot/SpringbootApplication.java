package com.grapefruit.springboot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 入口主程序
 *
 * @author grapefruit
 * @date 2021/05/29
 */

@SpringBootApplication()
//@MapperScan(basePackages = "com.grapefruit.springboot.mysql.mapper") /或是使用注解@Mapper
@EnableTransactionManagement
@NacosPropertySource(dataId = "grape",groupId = "dev_group",autoRefreshed = true)
public class SpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }
}
