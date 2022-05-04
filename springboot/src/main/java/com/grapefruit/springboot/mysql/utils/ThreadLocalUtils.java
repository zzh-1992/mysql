/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.utils;

/**
 * 线程副本工具
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-05-04 16:46
 */
public class ThreadLocalUtils {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String get() {
        return (String) threadLocal.get();
    }

    public static void set(String key) {
        threadLocal.set(key);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
