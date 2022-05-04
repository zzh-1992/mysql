/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * 加载多个数据源
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-05-04 16:00
 */
@Configuration
@Slf4j
public class Databases {

    public final static String MASTER_DATASOURCE_KEY = "masterDB";
    public final static String SLAVE_DATASOURCE_KEY = "slaveDB";

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @Bean(name = MASTER_DATASOURCE_KEY)
    @Qualifier(MASTER_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        log.info("create master datasource...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = SLAVE_DATASOURCE_KEY)
    @Qualifier(SLAVE_DATASOURCE_KEY)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        log.info("create slave datasource...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
