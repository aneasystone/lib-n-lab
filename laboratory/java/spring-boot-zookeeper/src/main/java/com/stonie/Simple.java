package com.stonie;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by aneasystone on 2018/8/4.
 */
public class Simple implements Watcher {

    private CountDownLatch connectedSignal = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    public void createNode() throws Exception {
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 1000, this);
        connectedSignal.await();
        zookeeper.create("/data", "Hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zookeeper.close();
    }

    public static void main(String[] args) throws Exception {
        Simple simple = new Simple();
        simple.createNode();
    }
}
