/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql;

import com.grapefruit.springboot.mysql.Service.MyService;
import com.grapefruit.springboot.mysql.entity.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求接口
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-30 2:23 下午
 */
@RestController
@RequestMapping("sql")
public class SelectController {
  @Autowired private MysqlTest mysqlTest;

  @Autowired MyService myService;

  @RequestMapping("/select")
  @ResponseBody
  public List<Grape> select() {
    return mysqlTest.execute();
  }

  @RequestMapping(value = "/selectById",method = RequestMethod.POST)
  public Grape selectById(@RequestBody @Valid Req req) {
    int id = req.getId();
    return myService.selectGrapeById(id);
  }

  @RequestMapping("/insert")
  public int insert() {
    Grape grape = new Grape();
    grape.setId(10);
    grape.setContent("数据添加测试");
    grape.setName("insert test");
    grape.setNum(new BigInteger("999"));
    return myService.insert(grape);
  }

  @RequestMapping("/insertList")
  public int insertList() {
    List<Grape> list = new ArrayList<>();
    for (int i = 50; i <= 55; i++) {
      Grape grape = new Grape();
      grape.setId(i);
      grape.setContent("数据添加测试");
      grape.setName("insert test");
      grape.setNum(new BigInteger("999" + i));
      list.add(grape);
    }
    return myService.insertList(list);
  }

  @RequestMapping("/deleteList")
  public int deleteList() {
    List<String> list = new ArrayList<>();
    for (int i = 50; i <= 55; i++) {
      list.add(String.valueOf(i));
    }
    return myService.deleteList(list);
  }
}
