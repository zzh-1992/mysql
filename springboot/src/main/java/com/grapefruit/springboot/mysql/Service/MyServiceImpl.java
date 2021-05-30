/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Service;

import com.grapefruit.springboot.mysql.Dao.MyDao;
import com.grapefruit.springboot.mysql.entity.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:41 下午
 */
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    MyDao myDao;

    //@Transactional
    @Override
    public Grape selectGrapeById(int id) {
        return myDao.selectGrapeById(id);
    }

    @Override
    public int insert(Grape grape) {
        return myDao.insert(grape);
    }

    @Override
    public int insertList(List<Grape> list) {
        return myDao.insertList(list);
    }

    @Override
    public int deleteList(List<String> list) {
        return myDao.deleteList(list);
    }
}
