/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.mapper;

import com.grapefruit.springboot.mysql.entity.Grape;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:38 下午
 */
//@Mapper
public interface MyMapper {

  /**
   * 通过id获取实体
   * @param id 主键id
   * @return 实体
   */
  Grape selectGrapeById(int id);
}
