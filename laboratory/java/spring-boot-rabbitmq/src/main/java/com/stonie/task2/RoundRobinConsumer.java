package com.stonie.task2;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by aneasystone on 2018/8/11.
 */
public class RoundRobinConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        Connection conn = factory.newConnection();
        final Channel channel = conn.createChannel();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.exchangeDeclare("hello-exchange", BuiltinExchangeType.DIRECT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "hello-exchange", "hello-key");
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println("Message Recv: " + message);
                    int c = message.lastIndexOf(".") - message.indexOf(".");
                    if (c % 2 == 0) {
                        Thread.sleep(1000 * 5);
                    } else {
                        Thread.sleep(1000 * 10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
