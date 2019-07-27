## 实战 Spring Boot 和 MyBatis

在 `springboot-mybatis-mysql` 例子中，我们使用 MyBatis 对数据库进行了简单的增删改查，这里用的是 MyBatis 的注解形式，直接将 SQL 语句写在 `PersonMapper` 接口的各个方法的注解上。这在 SQL 语句很简单时没有太大的问题，代码看起来也很紧凑，但是如果涉及到的 SQL 比较复杂，将 SQL 写在注解里可能会导致代码非常臃肿，这时，推荐使用 MyBatis 的 XML 配置方式。

首先我们在 resources 下创建一个 mapper 目录，这个目录用于存放我们的 XML 配置，MyBatis XML 文件的格式大抵如下：

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.stonie.person.PersonMapper">
    <insert ...>
    <delete ...>
    <update ...>
    <select ...>
</mapper>
```

然后在 `application.properties` 文件中添加如下配置，用于指定 MyBatis 的 mapper 文件位置：

```
mybatis.mapperLocations=classpath:mapper/*.xml
```

最后打开之前创建的 `PersonMapper` 接口，将所有方法上的注解全部删除，编译并运行项目，效果和 `springboot-mybatis-mysql` 例子完全一样。

### MyBatis XML 配置文件

* insert、delete、update、select
* resultMap
* parameterType

参考官方手册：http://www.mybatis.org/mybatis-3/sqlmap-xml.html

### `@MapperScan` 注解

在上面的例子中，我们保留了 `PersonMapper` 接口上的 `@Mapper` 注解，实际上，如果我们的 Mapper 接口非常多，且放在某个固定的包下，可以直接使用 `@MapperScan` 注解自动扫描所有的 Mapper 接口，无需定义 `@Mapper` 注解。

比如在入口类 `App` 上添加如下代码：

```
@SpringBootApplication
@MapperScan("com.stonie")
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
```

其中的包路径支持通配符，比如：`@MapperScan("com.stonie.*.dao")`。