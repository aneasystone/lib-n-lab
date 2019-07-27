## 实战 Maven 版 Hello World

### 使用 `mvn archetype:generate` 生成代码框架

```
$ mvn archetype:generate
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:3.1.1:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:3.1.1:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO] 
[INFO] 
[INFO] --- maven-archetype-plugin:3.1.1:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
```

通过 `mvn archetype:generate` 命令，可以以交互的形式快速生成一个 Maven 项目模板，不过在实际使用中会卡在上一步提示处，要等好久，这是因为 Maven 在获取 `archetype-catalog.xml` 文件导致的，可以加上 `-DarchetypeCatalog=internal` 或 `-DarchetypeCatalog=local` 参数。

```
$ mvn archetype:generate -DarchetypeCatalog=internal
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:3.1.1:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:3.1.1:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO] 
[INFO] 
[INFO] --- maven-archetype-plugin:3.1.1:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: internal -> org.apache.maven.archetypes:maven-archetype-archetype (An archetype which contains a sample archetype.)
2: internal -> org.apache.maven.archetypes:maven-archetype-j2ee-simple (An archetype which contains a simplifed sample J2EE application.)
3: internal -> org.apache.maven.archetypes:maven-archetype-plugin (An archetype which contains a sample Maven plugin.)
4: internal -> org.apache.maven.archetypes:maven-archetype-plugin-site (An archetype which contains a sample Maven plugin site.
      This archetype can be layered upon an existing Maven plugin project.)
5: internal -> org.apache.maven.archetypes:maven-archetype-portlet (An archetype which contains a sample JSR-268 Portlet.)
6: internal -> org.apache.maven.archetypes:maven-archetype-profiles ()
7: internal -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
8: internal -> org.apache.maven.archetypes:maven-archetype-site (An archetype which contains a sample Maven site which demonstrates
      some of the supported document types like APT, XDoc, and FML and demonstrates how
      to i18n your site. This archetype can be layered upon an existing Maven project.)
9: internal -> org.apache.maven.archetypes:maven-archetype-site-simple (An archetype which contains a sample Maven site.)
10: internal -> org.apache.maven.archetypes:maven-archetype-webapp (An archetype which contains a sample Maven Webapp project.)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 7: 
```

默认会选择第 7 个，也就是 `maven-archetype-quickstart` 这个 archetype，确定之后会让你填写 `groupId`、`artifactId`、`version` 和 `package`：

```
Define value for property 'groupId': com.stonie
Define value for property 'artifactId': java-helloworld-maven
Define value for property 'version' 1.0-SNAPSHOT: : 
Define value for property 'package' com.stonie: : 
Confirm properties configuration:
groupId: com.stonie
artifactId: java-helloworld-maven
version: 1.0-SNAPSHOT
package: com.stonie
 Y: : Y
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Old (1.x) Archetype: maven-archetype-quickstart:1.1
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: basedir, Value: /home/aneasystone/codes/laboratory/java
[INFO] Parameter: package, Value: com.stonie
[INFO] Parameter: groupId, Value: com.stonie
[INFO] Parameter: artifactId, Value: java-helloworld-maven
[INFO] Parameter: packageName, Value: com.stonie
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] project created from Old (1.x) Archetype in dir: /home/aneasystone/codes/laboratory/java/java-helloworld-maven
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  35.925 s
[INFO] Finished at: 2019-06-20T22:53:26+08:00
[INFO] ------------------------------------------------------------------------
```

最后输入 `Y` 确认后，就可以生成项目模板了。 

### 项目目录结构

```
$ tree
.
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── stonie
    │               └── App.java
    └── test
        └── java
            └── com
                └── stonie
                    └── AppTest.java

9 directories, 3 files
```

这是 Maven 项目典型的目录结构，根目录的 `pom.xml` 文件包含了一些项目基本信息和依赖信息，`src/main/java` 目录包含项目的主代码，`src/test/java` 目录包含项目的测试代码。

### 使用 Maven 编译项目

```
$ mvn clean
$ mvn compile
$ mvn test
$ mvn package
```

要注意的是，使用 `mvn package` 命令会在 target 目录生成 jar 文件，但是直接运行会报错:

```
$ java -jar target/java-helloworld-maven-1.0-SNAPSHOT.jar 
target/java-helloworld-maven-1.0-SNAPSHOT.jar中没有主清单属性
```

这是因为没有在 `pom.xml` 文件中指定程序的入口，我们在 `<project>` 节点下增加一个 `<build>` 节点：

```
  <build>
      <plugins>
            <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-shade-plugin</artifactId>
                  <version>1.2.1</version>
                  <executions>
                        <execution>
                              <phase>package</phase>
                              <goals>
                                    <goal>shade</goal>
                              </goals>
                              <configuration>
                                    <transformers>
                                          <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                                <mainClass>com.stonie.App</mainClass>
                                          </transformer>
                                    </transformers>
                              </configuration>
                        </execution>
                  </executions>
            </plugin>
      </plugins>
</build>
```

重新打包并运行：

```
$ java -jar target/java-helloworld-maven-1.0-SNAPSHOT.jar 
Hello World!
```

### `mvn archetype:generate` 的非交互模式

使用非交互模式直接一个命令就可以生成项目框架：

```
$ mvn archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.1 \
    -DgroupId=com.stonie \
    -DartifactId=java-helloworld-maven-2 \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.stonie \
    -DinteractiveMode=false \
    -DarchetypeCatalog=internal
```

### 参考

* [Maven - 工程模板 - Maven 教程 - 极客学院Wiki](http://wiki.jikexueyuan.com/project/maven/project-templates.html)
* [maven一波流(3)——使用eclipse构建第一个maven工程_慕课手记](https://www.imooc.com/article/76193?block_id=tuijian_wz)
