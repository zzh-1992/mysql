[Mybatis plus官网教程_快速开始](https://mp.baomidou.com/guide/quick-start.html)

# 快速开始

### 现有一张 User 表，其表结构如下：

id|    name|    age    |email
  ----  | ----  | ----  | ----  
1    |Jone    |18|    test1@baomidou.com
2    |Jack    |20    |test2@baomidou.com
3    |Tom    |28|    test3@baomidou.com
4    |Sandy    |21    |test4@baomidou.com
5    |Billie|    24    |test5@baomidou.com

###其对应的数据库 Schema 脚本如下：
```sql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);
```

###其对应的数据库 Data 脚本如下：
```sql
DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

#添加依赖
###引入 Spring Boot Starter 父工程：
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>spring-latest-version</version>
    <relativePath/>
</parent>
```

###引入 spring-boot-starter、spring-boot-starter-test、mybatis-plus-boot-starter、h2 依赖：
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>Latest Version</version>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

#配置
###在 application.yml 配置文件中添加 H2 数据库的相关配置：
```properties
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath:db/schema-h2.sql
    data: classpath:db/data-h2.sql
    url: jdbc:h2:mem:test
    username: root
    password: test
```
###在 Spring Boot 启动类中添加 @MapperScan 注解，扫描 Mapper 文件夹：
```java
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }

}
```
#编码
###编写实体类 User.java（此处使用了 Lombok (opens new window)简化代码）
```java
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

###编写Mapper类 UserMapper.java
```java
public interface UserMapper extends BaseMapper<User> {

}
```
#开始使用
###添加测试类，进行功能测试：
```java
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
```

