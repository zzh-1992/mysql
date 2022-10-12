/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Service;

import com.grapefruit.springboot.mysql.entity.Grape;

import java.util.List;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:41 下午
 */
public interface MyService {
    /**
     * 单个查询
     *
     * @param id id
     * @return 数据库影响行数
     */
    Grape selectGrapeById(int id);

    /**
     * 添加单个实体
     *
     * @param grape grape
     * @return 数据库影响行数
     */
    int insert(Grape grape);

    /**
     * 批量添加实体
     *
     * @param list list
     * @return 数据库影响行数
     */
    int insertList(List<Grape> list);

    /**
     * 依据id批量删除
     *
     * @param list list
     * @return 数据库影响行数
     */
    int deleteList(List<String> list);

    List<Grape> selectAll();
}
