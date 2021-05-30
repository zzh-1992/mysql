/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Service;

import com.grapefruit.springboot.mysql.Dao.MyDao;
import com.grapefruit.springboot.mysql.entity.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public Grape selectGrapeById(int id) {
        return myDao.selectGrapeById(id);
    }
}
