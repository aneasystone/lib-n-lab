## 实战 Spring Boot Web 版 Hello World

这个项目从 `srpingboot-helloworld-cli` 进化而来，首先将 `springboot-helloworld-cli` 复制一份，然后修改 pom.xml 文件将项目名称改为 `springboot-helloworld-web`，由于这里要创建一个 Spring Boot Web 应用，所以我们将 `spring-boot-starter` 改成 `spring-boot-starter-web`。

```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
```

### App.java

Spring Boot 程序的入口都是 App.java 的 main 方法，无论是命令行程序，还是 Web 应用，所以这个文件无需修改。

### 添加 RestController

然后修改 HelloWorld.java 文件，这里我们不用实现 `CommandLineRunner` 接口了，而是在 `HelloWorld` 类上加上了一个 `RestController` 注解，让其成为一个控制器类。控制器类就是 Spring MVC 中负责处理请求的类。

同时，我们为 `HelloWorld` 类添加一个请求映射，在 `index` 方法上加上 `RequestMapping` 注解：

```
@RestController
public class HelloWorld {
    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}
```

### 打包并运行

然后使用 `mvn package` 打包，并使用 `java -jar` 运行程序：

```
$ java -jar target/springboot-helloworld-web-1.0-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.2.RELEASE)

2019-06-23 22:12:40.743  INFO 5628 --- [           main] com.stonie.App                           : Starting App v1.0-SNAPSHOT on little-stone with PID 5628 (/home/aneasystone/codes/laboratory/java/springboot-helloworld-web/target/springboot-helloworld-web-1.0-SNAPSHOT.jar started by aneasystone in /home/aneasystone/codes/laboratory/java/springboot-helloworld-web)
2019-06-23 22:12:40.750  INFO 5628 --- [           main] com.stonie.App                           : No active profile set, falling back to default profiles: default
2019-06-23 22:12:40.825  INFO 5628 --- [           main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@3d99d22e: startup date [Sun Jun 23 22:12:40 CST 2019]; root of context hierarchy
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.springframework.cglib.core.ReflectUtils$1 (jar:file:/home/aneasystone/codes/laboratory/java/springboot-helloworld-web/target/springboot-helloworld-web-1.0-SNAPSHOT.jar!/BOOT-INF/lib/spring-core-5.0.6.RELEASE.jar!/) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of org.springframework.cglib.core.ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
2019-06-23 22:12:42.033  INFO 5628 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2019-06-23 22:12:42.073  INFO 5628 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2019-06-23 22:12:42.074  INFO 5628 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.31
2019-06-23 22:12:42.087  INFO 5628 --- [ost-startStop-1] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: [/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib]
2019-06-23 22:12:42.191  INFO 5628 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2019-06-23 22:12:42.192  INFO 5628 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1374 ms
2019-06-23 22:12:42.363  INFO 5628 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Servlet dispatcherServlet mapped to [/]
2019-06-23 22:12:42.370  INFO 5628 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2019-06-23 22:12:42.371  INFO 5628 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2019-06-23 22:12:42.371  INFO 5628 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2019-06-23 22:12:42.371  INFO 5628 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2019-06-23 22:12:42.563  INFO 5628 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2019-06-23 22:12:42.837  INFO 5628 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@3d99d22e: startup date [Sun Jun 23 22:12:40 CST 2019]; root of context hierarchy
2019-06-23 22:12:42.929  INFO 5628 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/]}" onto public java.lang.String com.stonie.HelloWorld.index()
2019-06-23 22:12:42.935  INFO 5628 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2019-06-23 22:12:42.936  INFO 5628 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2019-06-23 22:12:42.970  INFO 5628 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2019-06-23 22:12:42.971  INFO 5628 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2019-06-23 22:12:43.168  INFO 5628 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-06-23 22:12:43.223  INFO 5628 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-06-23 22:12:43.229  INFO 5628 --- [           main] com.stonie.App                           : Started App in 2.917 seconds (JVM running for 3.284)
```

和 `springboot-helloworld-web` 的启动日志不太一样，这里可以看到启动了一个 Tomcat 服务器，并监听在 8080 端口，而且还看到了 `Mapped "{[/]}" onto public java.lang.String com.stonie.HelloWorld.index()`，说明我们定义的 `index` 方法就对应 `/` 页面，我们直接在浏览器中打开 `http://localhost:8080/`，就可以看到 `Hello World` 了。
