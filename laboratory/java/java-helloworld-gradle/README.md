## 实战 Gradle 版 Hello World

Gradle 是一款类似 Ant 或 Maven 的构建工具，支持 Java、Groovy、Kotlin 和 Scala 等不同的编程语言，它不依赖传统的 XML 配置文件，而是通过 Groovy 语法来写配置文件。

### 安装 Gradle

https://gradle.org/install/

### 配置文件说明

我们使用之前创建的 `java-helloworld-maven` 示例项目，将 Maven 的配置文件 `pom.xml` 删掉，新建一个 Gradle 的配置文件 `build.gradle`：

```
apply plugin: 'java'

repositories {
    mavenCentral()
}

dependencies {
    testCompile "junit:junit:3.8.1"
}

jar {
    baseName = 'java-helloworld-gradle'
    version =  '1.0-SNAPSHOT'
    manifest {
        attributes 'Main-Class': 'com.stonie.App'
    }
}
```

这里使用了 Gradle 内置的 `java` 插件，`java` 插件支持很多 Task，比如最常用的 `jar`，在这里需要指定 baseName 和 version，这有点类似 `pom.xml` 里的 artifactId 和 version，这个就是最终打包的文件名。使用 Maven 打包时我们使用了 `maven-shade-plugin` 插件，通过 `org.apache.maven.plugins.shade.resource.ManifestResourceTransformer` 来指定 mainClass，这里我们也要使用 `manifest` 指定 mainClass。

Gradle 的仓库直接依赖现有的仓库，并没有重复造轮子，并且它支持多种仓库可以切换：

* Ivy 仓库：http://ivy.petrikainulainen.net/repo
* Maven 仓库：http://maven.petrikainulainen.net/repo
* Flat Directory 仓库

其中 Maven 仓库有几种别名：

* mavenCentral()：从 Central Maven 2 仓库中获取
* jcenter()：从 Bintary’s JCenter Maven 仓库中获取
* mavenLocal()：从本地的 Maven 仓库中获取

Gradle 的依赖管理和 Maven 类似，由 dependencies 定义，它支持几种不同的依赖配置项：

* compile 依赖是必须的
* runtime 依赖在运行时是必须的
* testCompile 依赖在编译项目的测试代码时是必须的
* testRuntime 依赖在运行测试代码时是必须的

### 编译打包

使用 `gradle tasks` 可以查看支持的所有 tasks：

```
$ gradle tasks

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project
------------------------------------------------------------

Build tasks
-----------
assemble - Assembles the outputs of this project.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.

Help tasks
----------
buildEnvironment - Displays all buildscript dependencies declared in root project 'java-helloworld-gradle'.
components - Displays the components produced by root project 'java-helloworld-gradle'. [incubating]
dependencies - Displays all dependencies declared in root project 'java-helloworld-gradle'.
dependencyInsight - Displays the insight into a specific dependency in root project 'java-helloworld-gradle'.
dependentComponents - Displays the dependent components of components in root project 'java-helloworld-gradle'. [incubating]
help - Displays a help message.
model - Displays the configuration model of root project 'java-helloworld-gradle'. [incubating]
projects - Displays the sub-projects of root project 'java-helloworld-gradle'.
properties - Displays the properties of root project 'java-helloworld-gradle'.
tasks - Displays the tasks runnable from root project 'java-helloworld-gradle'.

Verification tasks
------------------
check - Runs all checks.
test - Runs the unit tests.

Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.
Pattern: upload<ConfigurationName>: Assembles and uploads the artifacts belonging to a configuration.

To see all tasks and more detail, run gradle tasks --all

To see more detail about a task, run gradle help --task <task>

BUILD SUCCESSFUL in 1s
1 actionable task: 1 executed
```

这里我们要编译并打包 Java 程序，可以看到 `assemble`、`build`、`jar` 都可以，我们使用 `gradle build`：

```
$ gradle build

BUILD SUCCESSFUL in 2s
4 actionable tasks: 2 executed, 2 up-to-date
```

编译完成之后，在 `build/libs` 目录下会生成 jar 包：

```
$ java -jar build/libs/java-helloworld-gradle-1.0-SNAPSHOT.jar
Hello World!
```

### 参考

* [Gradle学习系列](https://www.cnblogs.com/davenkin/p/gradle-learning-1.html)
* [Building Java Projects with Gradle](https://spring.io/guides/gs/gradle/)
* [Gradle 官方手册](https://docs.gradle.org/current/userguide/userguide.html)
