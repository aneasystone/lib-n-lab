package com.stonie.direct;

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

        channel.exchangeDeclare("logs.direct", BuiltinExchangeType.DIRECT);
        channel.basicPublish("logs.direct", "info", null, "info log".getBytes());
        channel.basicPublish("logs.direct", "warn", null, "warn log".getBytes());
        channel.basicPublish("logs.direct", "error", null, "error log".getBytes());

        channel.close();
        conn.close();
    }
}
