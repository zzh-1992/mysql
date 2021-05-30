/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.Service;

import com.grapefruit.springboot.mysql.Dao.MyDao;
import com.grapefruit.springboot.mysql.entity.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * service
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:41 下午
 */
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private MyDao myDao;

    @Autowired
    private DataSourceTransactionManager tx;

    //@Transactional(rollbackFor = Exception.class)
    @Override
    public Grape selectGrapeById(int id) {
        return myDao.selectGrapeById(id);
    }

    @Override
    public int insert(Grape grape) {
        return myDao.insert(grape);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertList(List<Grape> list) {
        // 1.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 2.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 3.获得事务状态
        TransactionStatus status = tx.getTransaction(def);

        int i = 0;
        try {
            i = myDao.insertList(list);

            // 手动触发异常;
            int j = 1 / 0;

            // 手动提交事务
            tx.commit(status);
        } catch (Exception e) {

            // 手动回滚事务
            tx.rollback(status);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteList(List<String> list) {
        return myDao.deleteList(list);
    }
}
