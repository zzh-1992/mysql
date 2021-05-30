/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql;

import com.grapefruit.springboot.mysql.entity.Grape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 请求接口
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-30 2:23 下午
 */
@Controller
@RequestMapping("sql")
public class SelectController {
    @Autowired
    private MysqlTest mysqlTest;

    @RequestMapping("/select")
    @ResponseBody
    public List<Grape> select(){
        return mysqlTest.execute();
    }
}
