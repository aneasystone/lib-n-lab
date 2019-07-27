## 实战 Spring Boot 和 MyBatis

这个项目从 `springboot-mybatis-h2` 进化而来，首先将 `springboot-mybatis-h2` 复制一份，然后修改 pom.xml 文件将项目名称改为 `springboot-mybatis-mysql`，由于这里我们使用 MySQL 作为我们的数据库，所以在 `pom.xml` 中将 H2 改为 mysql：

```
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.1.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

### 初始化数据

在使用 H2 数据库时，由于 Spring Boot 默认启用了初始化 DataSource 的特性，所以只需要用 `spring.datasource.schema` 和 `spring.datasource.data` 配置好初始化的 SQL 即可，但是使用 MySQL 时，这个特性没有开启，所以需要开启一下，将 `spring.datasource.initialization-mode` 设置为 `always`：

```
spring.datasource.initialization-mode=always
spring.datasource.schema=classpath:import.sql
```

### 数据源配置

和 H2 数据库另一个不同的地方在于，Spring Boot 在使用内嵌数据库时（如：H2、HSQL、Derby 等），无需配置数据源 URL，只需要在 `pom.xml` 文件中添加相应的依赖即可，这在开发时非常方便。

但在使用生产环境数据库时（如：MySQL 等），必须要配置数据源，如下所示：

```
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

### MySQL 数据库

在开发环境使用 MySQL 数据库最简单的方式是使用 Docker，只用一个命令就可以把 MySQL 安装好：

```
# docker run -d --name db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7
```

安装好 MySQL 后，我们创建一个 `test` 数据库：

```
CREATE DATABASE `test` CHARACTER SET utf8 COLLATE utf8_general_ci;
```

### 验证

剩下的步骤和 `springboot-mybatis-h2` 完全一样，我们在这个基础上加上增删改的接口。

* GET /person/list
* GET /person/detail/{id}
* POST /person/insert
* POST /person/delete/{id}
* POST /person/update

在 `PersonMapper` 中我们新引入了 `@Insert`、`@Delete` 和 `@Update` 注解，另外在 `PersonController` 中我们还使用了 `@RequestBody` 注解。`@RequestBody` 注解通常用来处理 content-type 为 application/json 的请求，而不是默认的 application/x-www-form-urlcoded，这样可以很方便的将请求中的 JSON 对象直接绑定到参数 bean 上。

> TODO: content-type 为 application/x-www-form-urlcoded 的用法

```
# curl -H Content-Type:application/json -X POST --data '{"name":"test", "age":100}' http://127.0.0.1:8080/person/insert
# curl -H Content-Type:application/json -X POST --data '{"id":5, "name":"test", "age":200}' http://127.0.0.1:8080/person/update
# curl -H Content-Type:application/json -X POST http://127.0.0.1:8080/person/delete/5
```

### 参考

* [Spring Boot - Working with SQL Databases](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)
