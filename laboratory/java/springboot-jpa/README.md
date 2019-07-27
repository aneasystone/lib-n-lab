## 实战 Spring Data JPA 和 Hibernate

JPA 的全称为 Java Persistence API（Java 持久层 API），它是一种 ORM 规范，使得应用程序以统一的方式访问持久层，要注意的是，JPA 本身是一种规范，不包含任何实现，所以就有了 Hibernate，Hibernate 是 JPA 的一个具体实现，除此之外，TopLink、OpenJPA 也可以作为 JPA 的实现，这有点类似 JDBC 和 JDBC 驱动之间的关系。

但是不同的 ORM 框架实现 JPA 的方式不一样，导致人们在切换 ORM 框架时代码有一些差异，Spring Data JPA 在 JPA 规范的基础下提供了 Repository 层的实现，能够方便大家在不同的 ORM 框架之间进行切换而不用更改代码。

### 依赖

这个项目和 `springboot-mybatis-mysql` 类似，首先把 `mybatis-spring-boot-starter` 的依赖换成 `spring-boot-starter-data-jpa`:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### PersonEntity 和 PersonMapper

相比于 MyBatis，Hibernate 更符号 ORM 的标准，准确来说 MyBatis 并不能算 ORM 框架，它只实现了将数据库中的内容映射为实体，并没有实现将实体映射到数据库，而 Hibernate 支持双向映射，这通过 JPA 的几个注解来实现，首先我们在 `PersonEntity` 实体上加上 `@Entity` 和 `@Table` 注解，并在 `@Table` 注解上指定要映射的表名为 `person`：

```
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private Integer age;
}
```

另外，我们将 `id` 字段改成了 Long 型，并在上面加上了 `@Id` 和 `@GeneratedValue` 注解，表明这是一个自动生成的主键，其他字段没有加注解，表明和数据库中的字段名一致，如果不一致，可以使用 `@Column(name = "xxx")` 来定义映射。

然后我们删掉 `PersonMapper` 中的 `@Mapper` 注解，而是改为继承 `JpaRepository` 接口。

```
public interface PersonMapper extends JpaRepository<PersonEntity, Long> {
    // nothing to code
}
```

`PersonMapper` 接口中不用写任何 SQL 代码，就能自动实现数据库的增删改查，`JpaRepository` 提供了很多可用的方法：

* 增，改：save
* 删：deleteById
* 查：findAll、findById

除 `JpaRepository` 接口，Spring Data JPA 还有几个其他继承自 `Repository` 的接口可用：

`JpaRepository` -> `PagingAndSortingRepository` -> `CrudRepository` -> `Repository`

### 根据方法名自定义查询

Spring Data JPA 有一个很有趣的特性，会自动根据方法名来生成对应的查询语句，比如上面的 `findById(Long id)` 方法生成的 SQL 语句就是 `WHERE id = xxx` 的形式。如果我们要根据其他字段来查询，比如根据 name 字段，则可以定义一个 `findByName(String name)` 方法：

```
public interface PersonMapper extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByName(String name);
}
```

而且 Spring Data JPA 还支持多个属性的组合，以及不同的查询条件和排序条件，下面是一些具体的例子可以感受一下：

* And：findByUsernameAndPassword(String user, Striang pwd)
* Or：findByUsernameOrAddress(String user, String addr)
* Between：findBySalaryBetween(int max, int min)
* LessThan：findBySalaryLessThan(int max)
* GreaterThan：findBySalaryGreaterThan(int min)
* IsNull：findByUsernameIsNull()
* IsNotNull：findByUsernameIsNotNull()
* Like：findByUsernameLike(String user)
* NotLike：findByUsernameNotLike(String user)
* OrderBy：findByUsernameOrderBySalaryAsc(String user)
* Not：findByUsernameNot(String user)
* In：findByUsernameIn(Collection<String> userList)
* NotIn：findByUsernameNotIn(Collection<String> userList)

### 参考

* [Using MySQL in Spring Boot via Spring Data JPA and Hibernate](http://blog.netgloo.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/)
* [Getting started with Spring Data JPA](https://spring.io/blog/2011/02/10/getting-started-with-spring-data-jpa/)
* [使用 Spring Data JPA 简化 JPA 开发](https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/)
* [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)