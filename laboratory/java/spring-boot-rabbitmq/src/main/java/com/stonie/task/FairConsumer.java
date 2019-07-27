package com.stonie.task;

import com.rabbitmq.client.AMQP;
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
public class FairConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        Connection conn = factory.newConnection();
        final Channel channel = conn.createChannel();

        // 每个 consumer 最多同时消费一个 message
        channel.basicQos(1);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.queueDeclare("hello-queue", false, false, false, null);
        channel.basicConsume("hello-queue", false, new DefaultConsumer(channel) {
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
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        });
    }
}
