package com.stonie.task;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;

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

        channel.queueDeclare("hello-queue", false, false, false, null);
        for (int i = 1; i <= 10; i++) {
            String message = "Hello World" + StringUtils.repeat(".", i);
            channel.basicPublish("", "hello-queue", null, message.getBytes());
            System.out.println("Message Sent: " + message);
        }

        channel.close();
        conn.close();
    }
}
