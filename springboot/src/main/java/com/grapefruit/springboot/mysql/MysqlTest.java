/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql;

import com.grapefruit.springboot.mysql.Service.MyService;
import com.grapefruit.springboot.mysql.entity.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:30 下午
 */
@Component
public class MysqlTest {
  @Autowired MyService myService;
  private static final BigInteger OUT_OF_STOCK = new BigInteger("0");

  @Autowired private DataSourceTransactionManager tx;

  @PostConstruct
  public List<Grape> execute() {
    TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());

    List<Grape> list = new ArrayList<>();
    try {
      list = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        Grape grape = myService.selectGrapeById(i);
        BigInteger num = grape.getNum();

        System.out.println(num.compareTo(OUT_OF_STOCK));
        list.add(grape);
      }
      System.out.println(list);

      // 程序正常运行时手动提交事务
      // tx.commit(status);
    } catch (Exception e) {
      // 报异常时手动回滚
      tx.rollback(status);
      e.printStackTrace();
    }
    return list;
  }
}
