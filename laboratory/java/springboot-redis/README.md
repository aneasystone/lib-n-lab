## 实战 Spring Boot 操作 Redis

这个例子展示了如何使用 Redis 存储和读取数据，程序对页面访问进行计数，页面每被访问一次，计数器自增一次，并将计数器保存在 Redis 里。

### 安装 Redis

```
docker run --name redis -p 6379:6379 -d redis:3.2 redis-server --appendonly yes
```

### 依赖

```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-redis</artifactId>
      <version>1.3.2.RELEASE</version>
    </dependency>
```

### 配置

```
spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.password=
```

### Redis 操作

通常使用 `RedisTemplate` 或 `StringRedisTemplate` 类来操作 Redis，针对不同的数据类型，`RedisTemplate` 提供了不同的方法来操作：

* ValueOperations
* ListOperations
* SetOperations
* ZSetOperations
* GeoOperations
* HyperLogLogOperations

譬如这里使用 `ValueOperations` 进行最简单的 string 类型的键值对的处理，基本操作是 `get` 和 `set`。

### `RedisTemplate` vs. `StringRedisTemplate`

两者之间的区别主要在于他们使用的序列化类：

* `RedisTemplate` 使用的是 `JdkSerializationRedisSerializer`
* `StringRedisTemplate` 使用的是 `StringRedisSerializer`

`RedisTemplate` 存入数据时会被序列化成字节流格式，类似下面这样：

```
127.0.0.1:6379> keys *
1) "\xac\xed\x00\x05t\x00\x0eaccess-counter"

127.0.0.1:6379> get "\xac\xed\x00\x05t\x00\x0eaccess-counter"
"\xac\xed\x00\x05t\x00\x0261"
```

`StringRedisTemplate` 存入的数据是字符串类型，可以直接查看：

```
127.0.0.1:6379> keys *
1) "access-counter-2"

127.0.0.1:6379> get "access-counter-2"
"4"
```