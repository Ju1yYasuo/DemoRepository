package com.example.demo;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * java测试
 *
 * @author luox
 * @date 2021/7/16
 */
public class JavaTest {

    @Test
    public void test5(){

    }

    @Test
    public void testRabbitMqProducer() throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        String exchangeName = "luoxin.test.fanout";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true);
        String message = "testRabbitMqProducer message！！";
        channel.basicPublish(exchangeName, "", null, message.getBytes());

        //String exchangeName = "luoxin.test.topic";
        //channel.exchangeDeclare(exchangeName, "topic",true);
        //channel.basicPublish(exchangeName, "sms", null, message.getBytes());
        //channel.basicPublish(exchangeName, "email", null, message.getBytes());

        print(" [Producer] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    @Test
    public void testRabbitMqConsumer1() throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "luoxin.test.fanout";
        String queueName = "luoxin.test.fanout.sms";
        //队列 交换机 绑定关系应在配置类写好
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,"");

        DefaultConsumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                print("sms received ：" + new String(body));
                // 手动进行ACK
                /*
                 *  void basicAck(long deliveryTag, boolean multiple) throws IOException;
                 *  deliveryTag:用来标识消息的id
                 *  multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。
                 */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume(queueName,false,consumer);
    }

    @Test
    public void testRabbitMqConsumer2() throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "luoxin.test.fanout";
        String queueName = "luoxin.test.fanout.email";
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,"");
        //channel.queueBind(queueName,exchangeName,"sms");

        DefaultConsumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                print("email received ：" + new String(body));
            }
        };

        channel.basicConsume(queueName,true,consumer);
    }

    private Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.2.11");
        factory.setPort(5672);
        factory.setVirtualHost("luoxin_test");
        factory.setUsername("luoxin");
        factory.setPassword("luoxin");

        return factory.newConnection();
    }

    @Test
    public void test2(){
        int points = 10000;
        List<BigDecimal[]> bigDecimalList = new ArrayList<>(points);
        for(int i = 0;i < points;i++){
            BigDecimal[] bigDecimals = new BigDecimal[5];
            for(int j = 0;j< 5;j++){
                BigDecimal bigDecimal = new BigDecimal("0.001" + (int) (Math.random() * 9));
                bigDecimals[j] = bigDecimal;
            }
            bigDecimalList.add(bigDecimals);
        }

        BigDecimal standard = new BigDecimal("0.0002");
        for(int t = 0,size = bigDecimalList.size();t < size;t++) {
            BigDecimal[] bigDecimals = bigDecimalList.get(t);
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 5; j++) {
                    if(bigDecimals[i].subtract(bigDecimals[j]).abs().compareTo(standard) > 0){
                        print("t:" + t + ",i:" + i + ",j:" + j
                                + " v1:" + bigDecimals[i].floatValue() + ",v2:" + bigDecimals[j].floatValue());
                    }
                }
            }
        }

    }

    @Test
    public void test1(){
        print((int) (Math.random() * 9));
        print((int) (Math.random() * 10));
    }

    public static void main(String[] args) {
        String[] letter = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        String[] digital = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        Thread thread1 = new Thread(() -> {
            synchronized (JavaTest.class) {
                for (int i = 0,size = letter.length;i < size;i++) {
                    print(letter[i]);
                    JavaTest.class.notify();
                    if(i != size - 1){
                        try {
                            JavaTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "thread1");
        Thread thread2 = new Thread(() -> {
            synchronized (JavaTest.class) {
                for (int i = 0,size = digital.length;i < size;i++) {
                    print(digital[i]);
                    JavaTest.class.notify();
                    if(i != size - 1){
                        try {
                            JavaTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "thread2");
        thread1.start();
        thread2.start();
    }

    private static void print(Object obj){
        System.out.println(obj);
    }

}
