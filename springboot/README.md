# MySQL实现字段分割（一行转多行）

> ### 查询语句
```sql
SELECT a.id,
       a.name,
       a.num,
       SUBSTRING_INDEX(SUBSTRING_INDEX(a.content, ',', b.help_topic_id + 1), ',', - 1) AS content,
       a.value,
       a.link
FROM `grape` AS a
         JOIN grapefruit.help_topic AS b
              ON b.help_topic_id < (LENGTH(a.content) - LENGTH(REPLACE(a.content, ',', '')) + 1);
```

> ### 目标表
```sql
-- auto-generated definition
create table grape
(
    id      varchar(20)                   not null
        primary key,
    name    varchar(19) default 'orange'  null comment '名称',
    num     bigint unsigned               null,
    content varchar(255)                  null,
    value   varchar(20) default 'myValue' null comment '初始值',
    link    varchar(128)                  null
)
    comment '我到测试表';

create index i
    on grape (name);

```

> ### 辅助表

```sql
create table grapefruit.help_topic
(
    help_topic_id    int unsigned      not null
        primary key,
    name             char(64)          not null,
    help_category_id smallint unsigned not null,
    description      text              not null,
    example          text              not null,
    url              text              not null,
    constraint name
        unique (name)
)
    comment 'help topics' charset = utf8;
```

```sql
初始化语句

INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('0', 'orange', 1, '{"fruit":apple,"colour":"red"}', 'myValue', 'www.baidu.com');
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('1', 'meizhou', 2147483649, '{"fruit":apple,"colour":"red"}', 'myValue', 'www.baidu.com');
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('2', 'shenzhen', 2147483650, '{"fruit":peach,"colour":"pink"}', 'myValue', 'www.baidu.com2');
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('3', 'meizhou', 9223372036854775807, '{"fruit":apple,"colour":"red"}', 'myValue', 'www.baidu.com');
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('4', 'meizhou', 9223372036854775808, '{"fruit":apple,"colour":"red"}', 'myValue', 'www.baidu.com');
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('5', 'meizhou', 9223372036854775809, '{"fruit":apple,"colour":"red"}', 'myValue', 'www.baidu.com');
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('8', 'insert test', 9999, '数据添加测试', 'myValue', null);
INSERT INTO grapefruit.grape (id, name, num, content, value, link) VALUES ('9', 'insert test', 9999, '数据添加测试,2,3,4,5', 'myValue', null);

INSERT INTO grapefruit.help_topic (help_topic_id, name, help_category_id, description, example, url) VALUES (0, '0', 0, '0', '0', '0');
INSERT INTO grapefruit.help_topic (help_topic_id, name, help_category_id, description, example, url) VALUES (1, '1', 1, '1', '1', '1');
INSERT INTO grapefruit.help_topic (help_topic_id, name, help_category_id, description, example, url) VALUES (2, '2', 2, '2', '2', '2');
INSERT INTO grapefruit.help_topic (help_topic_id, name, help_category_id, description, example, url) VALUES (3, '3', 3, '3', '3', '3');
INSERT INTO grapefruit.help_topic (help_topic_id, name, help_category_id, description, example, url) VALUES (4, '4', 4, '4', '4', '4');
INSERT INTO grapefruit.help_topic (help_topic_id, name, help_category_id, description, example, url) VALUES (5, '5', 5, '5', '5', '5');
```


