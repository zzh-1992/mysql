/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.aspect;

import com.grapefruit.springboot.mysql.config.Databases;
import com.grapefruit.springboot.mysql.config.MyRoutingDatasourceConfig;
import com.grapefruit.springboot.mysql.utils.ThreadLocalUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据源切换代理
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-05-04 14:32
 */
@Component
@Aspect
public class DataSourceAspect {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Autowired
    MyRoutingDatasourceConfig config;

    @Pointcut(value = "@annotation(DbType)")
    public void myPointCut() {

    }

    @Around("myPointCut()")
    public Object doInterception(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        // 获取目标方法
        String name = signature.getName();

        Class<?> targetClass = joinPoint.getTarget().getClass();

        // 获取目标方法上的数据源注解
        DbType annotation = targetClass.getDeclaredMethod(name).getAnnotation(DbType.class);

        if (annotation != null && annotation.value() == DB.SLAVE) {
            // 有定义数据源或者数据源是从数据源(更改数据源,默认为masterDB)
            ThreadLocalUtils.set(Databases.SLAVE_DATASOURCE_KEY);
            config.determineCurrentLookupKey();
        }

        // 执行被代理的方法
        return joinPoint.proceed();
    }
}
