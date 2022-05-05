package com.sijan.userservice.broadcast;

import com.sijan.userservice.config.RabbitMQProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class ChangeRecordBroadcaster {

    @Autowired
    @Qualifier("ampqTemplate")
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitMQProperty property;

    public void broadcast(BroadcastRecord record) {
        if (record == null)
            return;
        if (StringUtils.hasText(property.getExchange()) && StringUtils.hasText(property.getRoutingkey())) {
            log.info("broadcasting [{}] to exchange [{}] with routing-key [{}]", record, property.getExchange(), property.getRoutingkey());
            amqpTemplate.convertAndSend(property.getExchange(), property.getRoutingkey(), record);
        }
    }

}
