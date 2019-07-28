## 实战 Spring 版 Hello World

在这个例子中，我们学习如何使用 Spring 注册并使用 Bean，Spring 提供了很多种不同的方法来注册 Bean，可以使用 XML 配置，也可以使用 Java 代码配置，可以手工配置，也可以定义自动扫描配置。所以大体可以分成下面四种方式：

* 手工 + Java 代码配置
* 手工 + XML 配置
* 自动扫描 + Java 代码配置
* 自动扫描 + XML 配置

### 依赖

```
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.0.6.RELEASE</version>
    </dependency>
```

### 使用 `@Configuration` + `@Bean` 手工配置

使用这种方式配置的 Bean 就是普通的 Java 类，不用做任何的修饰，这是因为在配置类中需要明确手工定义 Bean 的名字和实现。首先创建一个配置类 `HelloWorldConfig`，加上 `@Configuration` 注解，然后在这个类中定义 Bean，如下：

```
@Configuration
public class HelloWorldConfig {
    @Bean
    public HelloWorldService helloWorldService() {
        return new HelloWorldServiceImpl();
    }
}
```

Bean 配置好之后，有两种方法可以注册到 Spring 中去，第一种方法最简单，直接使用 `AnnotationConfigApplicationContext` 构造函数注册：

```
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
        HelloWorldService bean = (HelloWorldService) context.getBean("helloWorldService");
        bean.sayHello("World");
        context.close();
    }
```

或者使用 `AnnotationConfigApplicationContext` 的 `register()` 和 `refresh()` 方法注册：

```
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(HelloWorldConfig.class);
        context.refresh();
        HelloWorldService bean = (HelloWorldService) context.getBean("helloWorldService");
        bean.sayHello("World");
        context.close();
    }
```

### 使用 XML 手工配置

上面的 `HelloWorldConfig` 配置类，可以写成下面的 XML 配置文件，其效果一样：

```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">
    
    <bean id="helloWorldService" class="com.stonie.beans.impl.HelloWorldServiceImpl"/>
    
</beans>
```

不过要改成用 `ClassPathXmlApplicationContext` 来注册和使用时：

```
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("helloworld-config.xml");
        HelloWorldService bean = (HelloWorldService) context.getBean("helloWorldService");
        bean.sayHello("World");
        context.close();
    }
```

### 使用 `@ComponentScan` 自动配置

如果你的应用中 Bean 有很多，使用手工配置的方式会非常繁琐，配置类也会变得非常庞大。Spring 提供了一种方式可以自动扫描这些 Bean，如下所示：

```
@Configuration
@ComponentScan(basePackages = "com.stonie.beans")
public class AppConfig {
    // nothing
}
```

可以看出，配置类里不再定义具体的 Bean，而是在配置类上定义了一个 `@ComponentScan` 注解，并指定要扫描的包路径，Spring 会根据这个路径自动查找带有特定注解的类自动注册，下面是一些常见的作为 Bean 的注解：

* `@Repository` - 作为持久层的 DAO 组件
* `@Service` - 作为业务层的 Service 组件
* `@Controller` - 作为展现层的 Controller 组件
* `@Configuration` - Configuration 组件
* `@Component` - 通用注解, 可以作为以上注解的替代

和手工配置一样，使用 `AnnotationConfigApplicationContext` 构造函数注册：

```
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```

或使用 `register()` + `refresh()` 注册：

```
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.register(AppConfig.class);
context.refresh();
```

另外，还可以使用 `scan()` 方法自动注册，这样 `AppConfig` 配置类就可以省略掉：

```
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.scan("com.stonie.beans");
context.refresh();
```

### 使用 XML 自动配置

上面的 `AppConfig` 配置类，可以写成下面的 XML 配置文件，其效果一样：

```
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
 
    <context:component-scan base-package="com.stonie.beans" />
  
</beans>
```

相比于手工配置的 XML 文件，多了 `xmlns:context` 的声明。

### 参考

* [【译】Spring 4 Hello World例子](https://www.cnblogs.com/chenpi/p/6210097.html)
* [【译】Spring 4 自动装配、自动检测、组件扫描示例](https://www.cnblogs.com/chenpi/p/6211410.html)
* [使用 Java 配置进行 Spring bean 管理](https://www.ibm.com/developerworks/cn/webservices/ws-springjava/index.html)