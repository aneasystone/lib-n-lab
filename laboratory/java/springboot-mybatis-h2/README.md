## 实战 Spring Boot 和 MyBatis

这个项目从 `srpingboot-helloworld-web` 进化而来，首先将 `springboot-helloworld-web` 复制一份，然后修改 pom.xml 文件将项目名称改为 `springboot-mybatis-h2`，由于这里我们使用 H2 作为我们的数据库，所以在 `pom.xml` 中添加如下依赖：

```
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.1.1</version>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
```

### 初始化数据

在开始之前，我们先准备点初始数据用来测试，Spring JDBC 有一个初始化 DataSource 的特性，如果启用了该特性，Spring Boot 在启动时会自动加载配置的 SQL 语句，当使用 H2 数据库时，这个特性是默认开启的，所以只要像下面这样配置下 SQL 语句即可：

```
spring.datasource.schema=classpath:import.sql
```

一般情况，有两个配置项用于指定 SQL 文件：`spring.datasource.schema` 包含 DML 语句，`spring.datasource.data` 包含 DDL 语句，这里为了简单，直接把 DML 和 DDL 都写在一个 `import.sql` 文件里了。

在 `import.sql` 文件中，我们创建了一个 `person` 表并插入了几条测试数据：

```
CREATE TABLE `person` (
  `id` int(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(33) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### MyBatis 的注解形式

有了初始数据，就可以使用 MyBatis 对数据进行增删改查了，首先定义一个实体类 `PersonEntity` 对应之前的 `person` 表，然后定义一个 `PersonMapper` 接口。MyBatis 提供了两种方式执行 SQL：注解和 XML，这里我们使用最简单的注解形式：

```
@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM person")
    List<PersonEntity> list();

    @Select("SELECT * FROM person WHERE id = #{id}")
    PersonEntity detail(@Param("id") Integer id);
}
```

这里一共用到了三个注解：`@Mapper`、`@Select` 和 `@Param`，其中 `@Mapper` 是 MyBatis 注解形式的核心，可以看到在使用 MyBatis 的时候，我们的接口都没有实现类，这是因为 MyBatis 会自动为带有 `@Mapper` 注解的接口生成代理类，需要注意的是，这个接口不支持重载，也就是说接口中不能有重名函数。

`@Select` 注解用于指定每个方法要执行的 SQL 语句，在 SQL 语句中可以通过占位符 `#{id}` 来代表参数 id，同时需要在参数 id 上加上 `@Param` 注解，如果不加 `@Param` 注解，在 SQL 语句中可以使用索引占位符 `#{0}` 表示第一个参数。

> TODO: MyBatis 的几种占位符用法

### 验证

最后我们创建一个控制器 `PersonController` 并增加两个新的 URL 映射：`/person/list` 和 `/person/detail/{id}`，启动应用后，使用 curl 验证我们的接口功能是否正常：

```
# curl http://127.0.0.1:8080/person/list
[
    {
        "id": 1,
        "name": "zhangsan",
        "age": 30
    },
    {
        "id": 2,
        "name": "lisi",
        "age": 31
    },
    {
        "id": 3,
        "name": "wanger",
        "age": 32
    },
    {
        "id": 4,
        "name": "mazi",
        "age": 33
    }
]
```

### 参考

* [spring boot集成h2指南](https://segmentfault.com/a/1190000007002140)
* [Spring Boot - 数据库初始化](https://www.hifreud.com/2017/07/11/spring-boot-21-database-init/)
* [Spring Boot - Database Initialization](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html)
