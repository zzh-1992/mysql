/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Dao;

import com.grapefruit.springboot.mysql.entity.Grape;
import com.grapefruit.springboot.mysql.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * impl
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:40 下午
 */
@Service
public class MyDaoImpl implements MyDao {
  @Autowired MyMapper myMapper;

  @Override
  public Grape selectGrapeById(int id) {
    return myMapper.selectGrapeById(id);
  }

  @Override
  public int upGrapeById(Grape grape) {
    return myMapper.upGrapeById(grape);
  }

  @Override
  public int insert(Grape grape) {
    return myMapper.insert(grape);
  }

  @Override
  public int insertList(List<Grape> list) {
    return myMapper.insertList(list);
  }

  @Override
  public int deleteList(List<String> list) {
    return myMapper.deleteList(list);
  }
}
