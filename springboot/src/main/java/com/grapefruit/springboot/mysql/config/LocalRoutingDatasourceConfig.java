/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.config;

import com.grapefruit.springboot.mysql.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义路由数据源
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-05-04 13:12
 */
@Slf4j
@Configuration
public class LocalRoutingDatasourceConfig extends AbstractRoutingDataSource {

    Map<Object, Object> targetDataSources = new HashMap<>();

    @Autowired
    @Qualifier(value = Databases.MASTER_DATASOURCE_KEY)
    private DataSource masterDataSource;
    @Autowired
    @Qualifier(value = Databases.SLAVE_DATASOURCE_KEY)
    private DataSource slaveDataSource;

    @PostConstruct
    public void init() {
        targetDataSources.put(Databases.MASTER_DATASOURCE_KEY, masterDataSource);
        targetDataSources.put(Databases.SLAVE_DATASOURCE_KEY, slaveDataSource);
    }


    @Override
    public Object determineCurrentLookupKey() {
        // 获取数据源标识
        String requireDb = ThreadLocalUtils.get();

        // 依据标识返回对应的lookupkey
        return Databases.SLAVE_DATASOURCE_KEY.equals(requireDb) ? Databases.SLAVE_DATASOURCE_KEY :
                Databases.MASTER_DATASOURCE_KEY;
    }

    @Override
    public void afterPropertiesSet() {
        // 设置目标数据源
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}
