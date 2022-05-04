/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.aspect;

/**
 * 数据源枚举
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-05-04 14:48
 */
public enum DB {
    /**
     * 主
     */
    MASTER,

    /**
     * 副
     */
    SLAVE;

    private String name;
}
