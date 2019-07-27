## 实战 Spring Boot 版 Hello World

这个项目从 `java-helloworld-maven` 进化而来，首先将 `java-helloworld-maven` 复制一份，然后修改 pom.xml 文件将项目名称改为 `springboot-helloworld-cli`，然后添加 Spring Boot 相关的依赖。

### pom.xml

首先是增加 parent 节点，几乎所有的 Spring Boot 项目都需要加上这个：

```
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.2.RELEASE</version>
  </parent>
```

然后在 `dependencies` 中添加一个依赖：

```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
    </dependency>
```

### App.java

然后打开 App.java 文件，现在这个 App 只是一个普通的 Java 类，这里的 main 方法仍然是程序入口，只不过这个程序现在还无法使用 Spring 和 Spring Boot 的特性。

要让程序变成一个 Spring Boot 应用，要做下面两件事：

1. 给 `App` 类加上 `@SpringBootApplication` 注解
2. 在 `main` 方法中使用 `SpringApplication.run()` 启动 Spring 应用

这个类变成这个样子：

```
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
```

### 添加 CommandLineRunner

但是现在还没有开始写 Hello World 代码，之前的 `main` 方法被改成 `SpringApplication.run()` 了，Hello World 该写在哪里呢？

我们创建一个新的类 `HelloWorld`，然后加上 `@Component` 注解使它成为一个 Spring Bean，并实现 `CommandLineRunner` 接口，这个接口有一个 `run` 方法，我们的 Hello World 代码就可以写在这里:

```
@Component
public class HelloWorld implements CommandLineRunner {
    @Override
    public void run(String[] args) {
        System.out.println("Hello World.");
    }
}
```

### 使用 Maven 编译项目

在之前的项目中我们使用 `maven-shade-plugin` 插件来打包，但是在 Spring Boot 项目中，我们有一个专门的插件来打包 Spring Boot 项目：`spring-boot-maven-plugin`，我们将 `pom.xml` 文件中 `<plugin>` 部分修改成：

```
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
```

然后使用 `mvn package` 打包，并使用 `java -jar` 运行程序：

```
$ java -jar target/springboot-helloworld-cli-1.0-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.2.RELEASE)

2019-06-21 23:52:33.751  INFO 9391 --- [           main] com.stonie.App                           : Starting App v1.0-SNAPSHOT on little-stone with PID 9391 (/home/aneasystone/codes/laboratory/java/springboot-helloworld-cli/target/springboot-helloworld-cli-1.0-SNAPSHOT.jar started by aneasystone in /home/aneasystone/codes/laboratory/java/springboot-helloworld-cli)
2019-06-21 23:52:33.754  INFO 9391 --- [           main] com.stonie.App                           : No active profile set, falling back to default profiles: default
2019-06-21 23:52:33.823  INFO 9391 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@20398b7c: startup date [Fri Jun 21 23:52:33 CST 2019]; root of context hierarchy
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.springframework.cglib.core.ReflectUtils$1 (jar:file:/home/aneasystone/codes/laboratory/java/springboot-helloworld-cli/target/springboot-helloworld-cli-1.0-SNAPSHOT.jar!/BOOT-INF/lib/spring-core-5.0.6.RELEASE.jar!/) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of org.springframework.cglib.core.ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
2019-06-21 23:52:34.438  INFO 9391 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-06-21 23:52:34.455  INFO 9391 --- [           main] com.stonie.App                           : Started App in 1.028 seconds (JVM running for 1.357)
Hello World.
2019-06-21 23:52:34.458  INFO 9391 --- [       Thread-1] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@20398b7c: startup date [Fri Jun 21 23:52:33 CST 2019]; root of context hierarchy
2019-06-21 23:52:34.461  INFO 9391 --- [       Thread-1] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
```

仔细看，在大量的 Spring Boot 启动日志里就能找到我们输出的 `Hello World` 了，虽然这个小小的 `Hello World` 被日志给淹没了，但是终归，我们的 Spring Boot 算是启动起来了。