package com.github.rluisb.voting.config;

import com.rabbitmq.client.Channel;
import org.apache.log4j.Logger;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Bean
    public SpringAMQPMessageSource complaintEventsMethod(Serializer serializer) {
        return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

            @RabbitListener(queues = "${axon.amqp.exchange}")
            @Override
            public void onMessage(Message message, Channel channel) {
                LOGGER.debug(String.format("Event Received: %s", message.getBody().toString()));
                super.onMessage(message, channel);
            }
        };
    }
}
