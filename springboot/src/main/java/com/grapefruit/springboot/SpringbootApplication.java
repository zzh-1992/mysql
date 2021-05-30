package com.grapefruit.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 入口主程序
 *
 * @author grapefruit
 * @date 2021/05/29
 */
@SpringBootApplication()
// @MapperScan(basePackages = "com.grapefruit.springboot.mysql.mapper")
public class SpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootApplication.class, args);
  }
}
