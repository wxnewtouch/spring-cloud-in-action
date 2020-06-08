package com.wally.blogservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 * 广播模式
 */
@Configuration
public class RabbitConfig {

    public static final String queueName = "spring-boot";

    /**
     * 创建一个队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    /**
     * 创建一个exchange
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    /**
     * 将exchange与queue绑定
     *
     * @param queue
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
    }
}
