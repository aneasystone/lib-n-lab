## 实战 Netty

### 传统的 IO 编程

服务端通过 `serverSocket.accept()` 以阻塞的方式获取新的连接，每个连接需要一个单独的线程来维护，同一时刻有大量的线程处于阻塞状态是非常严重的资源浪费，另外，数据读写是以字节流为单位，效率不高。

### NIO 编程

NIO 编程模型中，新来一个连接不再创建一个新的线程，而是把这条连接直接绑定到某个固定的线程，然后这条连接所有的读写都由这个线程来负责。这是通过 NIO 中的 `selector` 实现的。另外，NIO 通过以字节块为单位，读写效率高。但是使用 JDK 原生 NIO 的 API 非常复杂。

1. JDK 的 NIO 编程需要了解很多的概念，编程复杂，对 NIO 入门非常不友好，编程模型不友好，ByteBuffer 的 API 简直反人类
2. 对 NIO 编程来说，一个比较合适的线程模型能充分发挥它的优势，而 JDK 没有给你实现，你需要自己实现，就连简单的自定义协议拆包都要你自己实现
3. JDK 的 NIO 底层由 epoll 实现，该实现饱受诟病的空轮训 bug 会导致 cpu 飙升 100%
4. 项目庞大之后，自行实现的 NIO 很容易出现各类 bug，维护成本较高

### Netty 入门

* ServerBootstrap
* NioEventLoopGroup：boss、worker
* Channel：NioServerSocketChannel、NioSocketChannel

### 参考

* https://www.jianshu.com/p/a4e03835921a
