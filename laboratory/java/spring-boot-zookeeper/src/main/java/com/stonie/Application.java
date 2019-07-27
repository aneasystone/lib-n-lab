package com.stonie;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Watcher watcher = event -> System.out.println("receive event：" + event);
        // sessionTimeout 设置的很长，是为了避免出现：KeeperErrorCode = ConnectionLoss for node 错误
        final ZooKeeper zookeeper = new ZooKeeper("192.168.0.107:2181", 99999999, watcher);
        String dataPath = "/data";
        if (zookeeper.exists(dataPath, false) == null) {
            zookeeper.create(dataPath, "Hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        String value = new String(zookeeper.getData(dataPath, watcher, null));
        zookeeper.close();
        System.out.println(value);
    }
}
