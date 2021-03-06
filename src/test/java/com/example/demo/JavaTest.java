package com.example.demo;

import com.example.demo.config.es.entity.Products;
import com.example.demo.entity.BiProducts;
import com.example.demo.util.json.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.alibaba.fastjson.*;
import com.rabbitmq.client.*;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * java测试
 *
 * @author luox
 * @date 2021/7/16
 */
public class JavaTest {

    @Test
    public void test8(){

        List<Cost> costList = new ArrayList<>();

        costList.add(Cost.builder().cost(new BigDecimal("34.6")).orgId(5001).timeType(0).totalMonth("2022-06").type(0).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.9")).orgId(5001).timeType(1).totalMonth("2022-06").type(0).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.3")).orgId(5001).timeType(2).totalMonth("2022-06").type(0).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.7")).orgId(5001).timeType(0).totalMonth("2022-06").type(1).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.1")).orgId(5001).timeType(1).totalMonth("2022-06").type(1).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.4")).orgId(5001).timeType(2).totalMonth("2022-06").type(1).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.2")).orgId(5001).timeType(1).totalMonth("2022-06").type(2).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.8")).orgId(5001).timeType(0).totalMonth("2022-06").type(2).build());
        costList.add(Cost.builder().cost(new BigDecimal("34.5")).orgId(5001).timeType(2).totalMonth("2022-06").type(2).build());

        costList.add(Cost.builder().cost(new BigDecimal("24.6")).orgId(5002).timeType(0).totalMonth("2022-06").type(0).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.7")).orgId(5002).timeType(0).totalMonth("2022-06").type(1).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.8")).orgId(5002).timeType(0).totalMonth("2022-06").type(2).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.9")).orgId(5002).timeType(1).totalMonth("2022-06").type(0).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.1")).orgId(5002).timeType(1).totalMonth("2022-06").type(1).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.2")).orgId(5002).timeType(1).totalMonth("2022-06").type(2).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.3")).orgId(5002).timeType(2).totalMonth("2022-06").type(0).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.4")).orgId(5002).timeType(2).totalMonth("2022-06").type(1).build());
        costList.add(Cost.builder().cost(new BigDecimal("24.5")).orgId(5002).timeType(2).totalMonth("2022-06").type(2).build());

        /*

        print(costList.stream().collect(Collectors.groupingBy(Cost::getType,Collectors.groupingBy(Cost::getTimeType,
                //会缺失精度
                Collectors.summingDouble(c -> c.getCost().doubleValue())))));

        print(costList.stream().collect(Collectors.groupingBy(Cost::getType,Collectors.groupingBy(Cost::getTimeType)))
                .values().stream().map(tMap ->
                        tMap.values().stream().map(m ->
                                        //reduce第一个参数是初始值，第二个参数是累计值，第三个参数是Next Element
                                        m.stream().map(Cost::getCost).reduce(BigDecimal.ZERO,BigDecimal::add))
                                .collect(Collectors.toList()))
                .collect(Collectors.toList()));

        print(costList.stream().collect(Collectors.groupingBy(Cost::getType,Collectors.groupingBy(Cost::getTimeType)))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                        entry1 -> getAsd(entry1.getValue())
                ))
        );

        print(costList.stream().collect(Collectors.groupingBy(Cost::getType,Collectors.groupingBy(Cost::getTimeType)))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                                entry -> entry.getValue().stream().map(Cost::getCost).reduce(BigDecimal.ZERO,BigDecimal::add)))
                ))
        );
        */

        List<CostVo> costVoList = new ArrayList<>();
        costList.stream().collect(Collectors.groupingBy(Cost::getType, Collectors.groupingBy(Cost::getTimeType)))
                .forEach((key, value) -> {
                    CostVo costVo = new CostVo();
                    costVo.setType(key);

                    List<CostVo.TimeTypeVo> timeTypeVoList = new ArrayList<>();
                    value.forEach((key1 , value2) -> {
                        CostVo.TimeTypeVo timeTypeVo = new CostVo.TimeTypeVo();
                        timeTypeVo.setTimeType(key1);
                        timeTypeVo.setTotal(value2.stream().map(Cost::getCost).reduce(BigDecimal.ZERO,BigDecimal::add));
                        timeTypeVoList.add(timeTypeVo);

                    });
                    costVo.setTimeTypeVoList(timeTypeVoList);
                    costVoList.add(costVo);
                });
        print(JsonUtil.toJsonString(costVoList));


        List<CostVo> costVoList1 = new ArrayList<>();
        costList.stream().collect(Collectors.groupingBy(Cost::getType,
                Collectors.groupingBy(Cost::getOrgId)))
                .forEach((key,value) -> {
                    CostVo costVo = new CostVo();
                    costVo.setType(key);

                    List<CostVo.OrgVo> orgVoList = new ArrayList<>();
                    value.forEach((key1,value1) -> {
                        CostVo.OrgVo orgVo = new CostVo.OrgVo();
                        orgVo.setOrgId(key1);

                        List<CostVo.OrgVo.orgTimeTypeVo> orgTimeTypeVoList = new ArrayList<>();
                        value1.forEach(cost -> {
                            CostVo.OrgVo.orgTimeTypeVo orgTimeTypeVo = new CostVo.OrgVo.orgTimeTypeVo();
                            orgTimeTypeVo.setTimeType(cost.getTimeType());
                            orgTimeTypeVo.setCost(cost.getCost());
                            orgTimeTypeVoList.add(orgTimeTypeVo);
                        });
                        orgVo.setOrgTimeTypeVoList(orgTimeTypeVoList);
                        orgVoList.add(orgVo);
                    });
                    costVo.setOrgVoList(orgVoList);
                    costVoList1.add(costVo);
                });

        print(JsonUtil.toJsonString(costVoList1));
        costVoList.forEach(costVo -> {
            costVoList1.forEach(costVo1 -> {
                if(costVo.getType().equals(costVo1.getType())){
                    costVo.setOrgVoList(costVo1.getOrgVoList());
                }
            });
        });
        print(JsonUtil.toJsonString(costVoList));
    }

    @Data
    public static class CostVo{
        private Integer type;
        private List<TimeTypeVo> timeTypeVoList;
        private List<OrgVo> orgVoList;
        @Data
        public static class TimeTypeVo{
            private Integer timeType;
            private BigDecimal total;
        }
        @Data
        public static class OrgVo{
            private Integer orgId;
            private List<orgTimeTypeVo> orgTimeTypeVoList;
            @Data
            public static class orgTimeTypeVo{
                private Integer timeType;
                private BigDecimal cost;
            }
        }

    }

    private Map<Integer,BigDecimal> getAsd(Map<Integer,List<Cost>> costMap){
        Map<Integer,BigDecimal> valMap2 = new HashMap<>();
        for(Map.Entry<Integer,List<Cost>> entry2 : costMap.entrySet()){
            valMap2.put(entry2.getKey(),
                    entry2.getValue().stream().map(Cost::getCost).reduce(BigDecimal.ZERO,BigDecimal::add));
        }
        //在流中修改外部变量无效
        //costMap.entrySet().stream().map(entry2 -> valMap2.put(entry2.getKey(),
        //        entry2.getValue().stream().map(Cost::getCost).reduce(BigDecimal.ZERO,BigDecimal::add)));

        return valMap2;
    }

    @Test
    public void test7() throws UnsupportedEncodingException {
        //请求头Authorization鉴权
        //String encoding = DatatypeConverter.printBase64Binary("elastic:julyyasuo".getBytes(StandardCharsets.UTF_8));
        //Basic后面有空格
        //http.setHeader("Authorization", "Basic " + encoding);
        //print(encoding);
    }

    @Test
    public void test6(){
        Products products = new Products();
        products.setPrice(2.33d);
        print(JsonUtil.parse(JsonUtil.toJsonString(products),BiProducts.class));

        BiProducts biProducts = new BiProducts();
        biProducts.setPrice(new BigDecimal("5.32"));
        print(JsonUtil.parse(JsonUtil.toJsonString(biProducts),Products.class));
        //BiProducts biProducts = new BiProducts();
        //biProducts.setSuggest("土豆,番茄");
        //Products products = new Products();
        //BeanUtils.copyProperties(biProducts,products);
        //products.setSuggest(new Completion(biProducts.getSuggest().split(",")));
        //print(JsonUtil.toJsonString(products));
    }

    @Test
    public void test5() throws JsonProcessingException {
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //print(objectMapper.writeValueAsString(products));
        BiProducts biProducts = new BiProducts();
        biProducts.setId(2L);
        Products products = new Products();
        BeanUtils.copyProperties(biProducts,products);
        print(products);
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
