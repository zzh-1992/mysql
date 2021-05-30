/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Dao;


import com.grapefruit.springboot.mysql.entity.Grape;

/**
 * Dao
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:38 下午
 */
public interface MyDao {

    Grape selectGrapeById(int id);
}
