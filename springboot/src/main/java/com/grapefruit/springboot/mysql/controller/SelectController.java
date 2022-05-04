/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.controller;

import com.grapefruit.springboot.mysql.Service.MyService;
import com.grapefruit.springboot.mysql.aspect.DB;
import com.grapefruit.springboot.mysql.aspect.DbType;
import com.grapefruit.springboot.mysql.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
@RequestMapping("/sql")
public class SelectController {
    @Autowired
    MyService myService;

    @DbType(DB.SLAVE)
    @RequestMapping("/selectAll")
    public List selectAll() {
        return myService.selectAll();

        /*ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.setViewName("list");
        return mv;*/
    }

    @DbType(DB.MASTER)
    @RequestMapping("/selectAllMaster")
    public List selectAllMaster() {
        return myService.selectAll();

        /*ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.setViewName("list");
        return mv;*/
    }

    @RequestMapping("/error")
    public ModelAndView error() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", LocalDateTime.now());
        mv.setViewName("error");
        return mv;
    }

    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    public Fruit selectById(@RequestBody @Valid Req req) {
        int id = req.getId();
        return myService.selectGrapeById(id);
    }

    @RequestMapping("/insert")
    public int insert() {
        Fruit fruit = new Fruit();
        fruit.setId(10);
        fruit.setContent("数据添加测试");
        fruit.setName("insert test");
        fruit.setNum(new BigInteger("9999"));
        return myService.insert(fruit);
    }

    @RequestMapping("/insertList")
    public int insertList() {
        List<Fruit> list = new ArrayList<>();
        for (int i = 50; i <= 55; i++) {
            Fruit fruit = new Fruit();
            fruit.setId(i);
            fruit.setContent("数据添加测试");
            fruit.setName("insert test");
            fruit.setNum(new BigInteger("999" + i));
            list.add(fruit);
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
