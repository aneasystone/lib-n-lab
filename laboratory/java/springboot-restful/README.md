## 实战 Spring Boot 打造 RESTful 应用

在 `springboot-mybatis-mysql` 和 `springboot-mybatis-xml` 两个例子中，我们实现了 person 实体的增删改查接口：

* 列表 GET /person/list
* 详情 GET /person/detail/{id}
* 新增 POST /person/insert
* 删除 POST /person/delete/{id}
* 更新 POST /person/update

这里使用了比较传统的接口命名方法，通过接口 URL 就可以知道该接口的行为，比如 insert、update、delete 等，这种接口设计方法以 **行为** 为核心，而 RESTful 接口设计方法是以 **资源** 为核心的，首先确定资源 person，然后使用不同的 HTTP 方法，代表该资源的不同操作：

* 列表 GET /person
* 详情 GET /person/{id}
* 新增 POST /person
* 删除 DELETE /person/{id}
* 更新 PUT /person/{id}

这种设计的一大好处是接口名统一，不会存在诸如：`/person/insert`、`/person/create`、`/create_person`、`/new_person` 等等各种格式的 URL。

我们基于 `springboot-mybatis-xml` 作如下修改：

    @RequestMapping(value = "/list", method = RequestMethod.GET)
=>  @RequestMapping(value = "", method = RequestMethod.GET)

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
=>  @RequestMapping(value = "/{id}", method = RequestMethod.GET)

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
=>  @RequestMapping(value = "", method = RequestMethod.POST)

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
=>  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)

    @RequestMapping(value = "/update", method = RequestMethod.POST)
=>  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)

### 什么是 RESTful

REST 原则是 Roy Thomas Fielding 在 2000 年提出的，它的全称是 **Representational State Transfer**，翻译成中文就是 **表现层状态转化**，如果一个架构符合 REST 原则，就称它为 RESTful 架构。那么什么是表现层状态转化呢？实际上这里省略了主语，这个表现层指的是 **资源** 的表现层。那么什么是资源呢？所谓资源就是网络上的一个实体，可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的实在。你可以用一个 URI（统一资源定位符）指向它，每种资源对应一个特定的 URI，要获取这个资源，访问它的 URI 就可以，因此 URI 就成了每一个资源的地址或独一无二的识别符。

但是，URI 只代表资源的实体，不代表它的形式，我们把资源具体呈现出来的形式，叫做它的表现层（Representation），一种资源可以有多种表现形式，比如文本可以用 txt 格式表现，也可以用 HTML 格式、XML 格式、JSON 格式表现。它的具体表现形式，应该在 HTTP 请求的头信息中用 Accept 和 Content-Type 字段指定。

访问一个网站，就代表了客户端和服务器的一个互动过程，在这个过程中，势必涉及到数据和状态的变化。客户端使用 HTTP 协议里面的四个表示操作方式的动词：GET、POST、PUT、DELETE，分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源（也可以用于更新资源），PUT 用来更新资源，DELETE 用来删除资源。

### 参考

http://www.ruanyifeng.com/blog/2011/09/restful.html
https://stackoverflow.com/questions/5020704/how-to-design-restful-search-filtering
https://mrbird.cc/Spring-Boot-Swagger2-RESTful-API.html
