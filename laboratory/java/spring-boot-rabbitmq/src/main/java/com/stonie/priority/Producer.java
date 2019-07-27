package com.stonie.priority;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-max-priority", 3);
        channel.queueDeclare("priority-queue", false, false, false, arguments);
        for (int i = 1; i <= 5; i++) {
            AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder().priority(i-5);
            channel.basicPublish("", "priority-queue", properties.build(), ("Hello World" + i).getBytes());
        }

        channel.close();
        conn.close();
    }
}
