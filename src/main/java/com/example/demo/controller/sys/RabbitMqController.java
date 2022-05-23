package com.example.demo.controller.sys;

import com.example.demo.config.annotation.ResponseEntity;
import com.example.demo.config.mq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * rabbitmq测试
 *
 * @author luox
 * @date 2022/3/24
 */
@RestController
@RequestMapping("/rabbit")
@Slf4j
@ResponseEntity
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping("/sendMessage")
    public Boolean sendMessage(@RequestParam("message") String message){
        //手动确认消息接收
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallback);

        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_FANOUT,"",message,new CorrelationData("消息id1-asd"));
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC,"july.das.sms",message,new CorrelationData("消息id1-asd"));
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC,"email",message,new CorrelationData("消息id3-zxc"));
        //测试不存在的路由，调用return方法，仅对topic类似类型交换机有效
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC,"july",message,new CorrelationData("消息id2-qwe"));
        return true;
    }

    /**
     * 配置消息确认对象
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = (CorrelationData correlationData, boolean ack, String cause) -> {
        ///**
        // * @param correlationData 消息相关的数据，一般用于获取 唯一标识 id
        // * @param ack true 消息确认成功，false 失败
        // * @param cause 确认失败的原因
        // */
        assert correlationData != null;
        if (ack) {
            System.out.println("confirm 消息确认成功..." + correlationData.getId());
        } else {
            System.out.println("confirm 消息确认失败..." + correlationData.getId() + " cause: " + cause);
        }
    };

    /**
     * 配置 return 消息机制
     */
    private final RabbitTemplate.ReturnsCallback returnCallback = returnedMessage -> {
        ///**
        // *  return 的回调方法（找不到路由才会触发）
        // * @param message 消息的相关信息
        // * @param replyCode 错误状态码
        // * @param replyText 错误状态码对应的文本信息
        // * @param exchange 交换机的名字
        // * @param routingKey 路由的key
        // */
        System.out.println(returnedMessage.getMessage());
        System.out.println(new String(returnedMessage.getMessage().getBody()));
        System.out.println(returnedMessage.getReplyCode());
        System.out.println(returnedMessage.getReplyText());
        System.out.println(returnedMessage.getExchange());
        System.out.println(returnedMessage.getRoutingKey());

    };

    //@RabbitListener(bindings = @QueueBinding(
    //        value = @Queue(value = "queue_email", durable = "true"),
    //        exchange = @Exchange(
    //                value = RabbitConfig.EXCHANGE_FANOUT,
    //                ignoreDeclarationExceptions = "true",
    //                type = ExchangeTypes.FANOUT
    //        ),
    //        key = {"topic.#.email.#","email.*"}))
    @RabbitListener(queues = RabbitConfig.QUEUE_EMAIL)
    public void receive_email(String msg){
        System.out.println(" [邮件服务] received : " + msg + "!");
        //log.info(" [邮件服务] received : " + msg + "!");
    }

    //@RabbitListener(bindings = @QueueBinding(
    //        value = @Queue(value = "queue_sms", durable = "true"),
    //        exchange = @Exchange(
    //                value = RabbitConfig.EXCHANGE_FANOUT,
    //                ignoreDeclarationExceptions = "true",
    //                type = ExchangeTypes.FANOUT
    //        ),
    //        key = {"topic.#.sms.#"}))
    //@RabbitListener(queues = RabbitConfig.QUEUE_SMS)
    public void receive_sms(String msg){
        System.out.println(" [短信服务] 普通队列 received : " + msg + "!");
    }

    //@RabbitListener(queues = RabbitConfig.QUEUE_TTL)
    public void receive_ttl(String msg){
        System.out.println(" [短信服务] ttl队列 received : " + msg + "!");
    }

    //@RabbitListener(queues = RabbitConfig.QUEUE_DLX)
    public void receive_dlx(String msg) {
        System.out.println(" [短信服务] dlx队列 received : " + msg + "!");
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_DLX_RECEIVE)
    public void receive_dlx_receive(String msg){
        System.out.println(" [短信服务] receive DLX队列 received : " + msg + "!");
    }

    //注解绑定交换机队列
    //@RabbitListener(bindings = @QueueBinding(
    //        value = @Queue(value = RabbitConfig.QUEUE_SMS, durable = "true"),
    //        exchange = @Exchange(
    //                value = RabbitConfig.EXCHANGE_TOPIC,
    //                ignoreDeclarationExceptions = "true",
    //                type = ExchangeTypes.TOPIC
    //        ),
    //        key = {"#.sms.#"}))
    //@RabbitListener(queues = RabbitConfig.QUEUE_SMS)
    public void receive_sms2(String msg){
        System.out.println(" [短信服务2] 普通队列 received : " + msg + "!");
    }
}
