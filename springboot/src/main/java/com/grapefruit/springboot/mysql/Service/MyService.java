/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Service;


import com.grapefruit.springboot.mysql.entity.Grape;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:41 下午
 */
public interface MyService {
    Grape selectGrapeById(int id);
}
