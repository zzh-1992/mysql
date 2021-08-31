/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.mysql.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.val;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.IOException;

/**
 * 以代码方式将相关对象注入容器
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-30 1:17 下午
 */
@Configuration
public class Config {
    /**
     * 获取mapper路径资源
     *
     * @return Resource[]
     */
    public Resource[] getResource() {
        val resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources("classpath:/mapper/*.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    /**
     * 获取DataSource
     *
     * @return DataSource
     */
    @Bean(initMethod = "init")
    public DruidDataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl(
                "jdbc:mysql://127.0.0.1:3306/grapefruit?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    // 官方链接:http://mybatis.org/spring/zh/factorybean.html
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 数据源设置
        DruidDataSource druidDataSource = getDruidDataSource();
        bean.setDataSource(druidDataSource);

        // mapper路径设置
        bean.setMapperLocations(getResource());
        return bean;
    }

    /**
     * 方式1:以代码方式扫描mapper 方式2:在主程序到类上加@MapperScan(basePackages =
     * "com.grapefruit.springboot.mysql.mapper") 方式3:mapper类上加@Mapper
     *
     * @return MapperScannerConfigurer
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        scanner.setBasePackage("com.grapefruit.springboot.mysql.mapper");
        return scanner;
    }

    /**
     * 设置事务管理
     *
     * @return DataSourceTransactionManager
     */
    @Bean()
    public DataSourceTransactionManager getDataSourceTransactionManager() {
        DataSourceTransactionManager dtm = new DataSourceTransactionManager();
        DruidDataSource druidDataSource = getDruidDataSource();
        dtm.setDataSource(druidDataSource);
        return dtm;
    }
}
