/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql.mapper;

import com.grapefruit.springboot.mysql.entity.Grape;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-05-28 10:38 下午
 */
@Mapper
public interface MyMapper {

    /**
     * 通过id获取实体
     *
     * @param id 主键id
     * @return 实体
     */
    //@Select("select * from grape where id = #{id};")
    Grape selectGrapeById(int id);

    /**
     * 更新实体
     *
     * @param grape grape
     * @return 数据库更新条数
     */
    int upGrapeById(Grape grape);

    /**
     * 添加实体
     *
     * @param grape grape
     * @return 数据库更新条数
     */
    int insert(Grape grape);

    /**
     * 添加批量实体
     *
     * @param list list
     * @return 数据库更新条数
     */
    @Insert({"<script>",
            "insert into grape (id,name,num,content) values" +
                    "<foreach item='item' collection='list'  separator=',' >" +
                    "(#{item.id},#{item.name},#{item.num},#{item.content})" +
                    "</foreach>" +
                    "</script>"})
    int insertList(List<Grape> list);

    /**
     * 依据id批量删除
     *
     * @param list list
     * @return 数据库影响行数
     */
    int deleteList(List<String> list);

    @Select("select * from grape ;")
    List<Grape> selectAll();
}
