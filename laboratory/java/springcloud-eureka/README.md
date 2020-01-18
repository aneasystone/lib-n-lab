## 实战 Spring Cloud Eureka

这个例子从 springboot-helloworld-web 的基础上发展而来。

### 添加依赖

对于服务端首先添加 `spring-cloud-starter-netflix-eureka-server` 依赖，对于客户端添加 `spring-cloud-starter-netflix-eureka-client` 依赖：

```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
```

另外添加 `dependencyManagement`，在这里指定 Spring Cloud 的版本，由于我们这里 Spring Boot 版本是 2.0.2.RELEASE，所以 Spring Cloud 版本选择 Finchley。

```
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Finchley.SR4</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>
```

Spring Cloud 和 Spring Boot 版本一定要匹配，对应关系如下：

|Spring Boot|Spring Cloud|
|-----------|------------|
|1.5.x      |Dalston|
|1.5.x      |Edgware|
|2.0.x      |Finchley|
|2.1.x      |Greenwich|
|2.2.x      |Hoxton|

### EnableEurekaServer & EnableEurekaClient

服务端使用 `EnableEurekaServer` 注解开启 Eureka Server，客户端使用 `EnableEurekaClient` 注解开启 Eureka Client。

```
@SpringBootApplication
@EnableEurekaServer
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
```

### Eureka Server 配置

Eureka Server 可以单节点模式部署（Standalone Mode），也可以部署多节点实现高可用（Peer Awareness）。下面是单节点模式的配置：

```
eureka:
  client:
    registerWithEureka: false
    fetchRegistry: false
  server:
    waitTimeInMsWhenSyncEmpty: 0
```

### Eureka Client 配置

在 Eureka Client 的配置文件中指定 Eureka Server 的地址：

```
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

### 访问注册中心

依次启动 springcloud-eureka-server 和 springcloud-eureka-client，然后访问 http://localhost:8761 ，可以看到客户端已成功注册到注册中心。

### 参考

* https://cloud.spring.io/spring-cloud-netflix/reference/html/
* https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html
* https://spring.io/projects/spring-cloud