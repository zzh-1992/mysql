/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源标识
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-05-04 14:34
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbType {
    /**
     * 默认为主数据库
     *
     * @return value
     */
    DB value() default DB.MASTER;
}
