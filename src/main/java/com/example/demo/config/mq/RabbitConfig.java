package com.example.demo.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMq配置
 *
 * @author luox
 * @date 2022/3/25
 */
@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_FANOUT = "luoxin.test.fanout";
    public static final String EXCHANGE_TOPIC = "luoxin.test.topic";

    public static final String QUEUE_SMS = "luoxin.test.fanout.sms";
    public static final String QUEUE_EMAIL = "luoxin.test.fanout.email";

    public static final String ROUTINGKEY_EMAIL = "#.email.#";
    public static final String ROUTINGKEY_SMS = "#.sms.#";

    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange(EXCHANGE_FANOUT).durable(true).build();
    }

    @Bean
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC).durable(true).build();
    }

    @Bean
    public Queue smsQueue(){
        return new Queue(QUEUE_SMS,true);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(QUEUE_EMAIL,true);
    }

    @Bean
    public Binding smsBindingFanoutExchange(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBindingFanoutExchange(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding smsBindingTopicExchange(){
        return BindingBuilder.bind(smsQueue()).to(topicExchange()).with(ROUTINGKEY_SMS);
    }

    @Bean
    public Binding emailBindingTopicExchange(){
        return BindingBuilder.bind(emailQueue()).to(topicExchange()).with(ROUTINGKEY_EMAIL);
    }


    /**
     * TTL队列，死信队列，接收死信队列
     */
    public static final String QUEUE_TTL = "luoxin.test.ttl";
    public static final String QUEUE_DLX = "luoxin.test.dlx";
    public static final String QUEUE_DLX_RECEIVE = "luoxin.test.dlx.receive.queue";
    public static final String EXCHANGE_DLX_RECEIVE = "luoxin.test.dlx.receive";
    /**
     * 绑定TTL，死信，接收死信队列
     */

    @Bean
    public FanoutExchange receiveDLXExchange(){
        return ExchangeBuilder.fanoutExchange(EXCHANGE_DLX_RECEIVE).durable(true).build();
    }

    @Bean
    public Queue ttlQueue(){
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000);//单位ms
        return new Queue(QUEUE_TTL,true,false,false,map);
    }

    @Bean
    public Queue dlxQueue(){
        Map<String, Object> map = new HashMap<>();
        // 5秒后，消息自动变为死信
        map.put("x-message-ttl", 5000);
        map.put("x-dead-letter-exchange", EXCHANGE_DLX_RECEIVE);
        map.put("x-dead-letter-routing-key", "");
        return new Queue(QUEUE_DLX,true,false,false,map);
    }

    @Bean
    public Queue receiveDLXQueue(){
        return new Queue(QUEUE_DLX_RECEIVE,true);
    }

    @Bean
    public Binding ttlBindingTopicExchange(){
        return BindingBuilder.bind(ttlQueue()).to(topicExchange()).with(ROUTINGKEY_SMS);
    }

    @Bean
    public Binding dlxBindingTopicExchange(){
        return BindingBuilder.bind(dlxQueue()).to(topicExchange()).with(ROUTINGKEY_SMS);
    }

    @Bean
    public Binding receiveDLXBindingTopicExchange(){
        return BindingBuilder.bind(receiveDLXQueue()).to(receiveDLXExchange());
    }
}
