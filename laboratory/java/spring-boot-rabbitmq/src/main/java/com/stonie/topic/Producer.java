package com.stonie.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by aneasystone on 2018/8/11.
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("logs.topic", BuiltinExchangeType.TOPIC);
        channel.basicPublish("logs.topic", "cron.info", null, "cron info log".getBytes());
        channel.basicPublish("logs.topic", "cron.warn", null, "cron warn log".getBytes());
        channel.basicPublish("logs.topic", "cron.error", null, "cron error log".getBytes());
        channel.basicPublish("logs.topic", "kernel.info", null, "kernel info log".getBytes());
        channel.basicPublish("logs.topic", "kernel.warn", null, "kernel warn log".getBytes());
        channel.basicPublish("logs.topic", "kernel.error", null, "kernel error log".getBytes());

        channel.close();
        conn.close();
    }
}
