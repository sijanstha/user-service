package com.sijan.userservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class RabbitMQProperty {
    @Value("${userservice.rabbitmq.queue}")
    private String queueName;

    @Value("${userservice.rabbitmq.exchange}")
    private String exchange;

    @Value("${userservice.rabbitmq.routingkey}")
    private String routingkey;
}
