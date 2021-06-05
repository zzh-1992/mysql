/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springboot.mysql;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-04 11:09 下午
 */
@Getter
@Setter
public class Req {
    @Max(value = 3,message = "id can not bigger than 3")
    private int id;

    @Length(min = 1,max = 3,message = "name length should between 1 and 3")
    @NotBlank(message = "用户名不能为空！")
    private String name;
}
