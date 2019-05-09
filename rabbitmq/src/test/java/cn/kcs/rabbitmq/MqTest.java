package cn.kcs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: kcs
 * @create: 2019-05-05 16:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private Environment environment;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void msg() throws Exception {
        rabbitTemplate.setExchange(environment.getProperty("mq_msg_exchange_name"));
        rabbitTemplate.setRoutingKey(environment.getProperty("mq_msg_routing_key_name"));
        Message message = MessageBuilder
                .withBody(objectMapper.writeValueAsBytes("msg msg"))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        message.getMessageProperties()
                .setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                        MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(message);
    }

    @Test
    public void accept() throws Exception {
        rabbitTemplate.setExchange(environment.getProperty("mq_accept_exchange_name"));
        rabbitTemplate.setRoutingKey(environment.getProperty("mq_accept_routing_key_name"));
        Message message = MessageBuilder
                .withBody(objectMapper.writeValueAsBytes("accept msg"))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        message.getMessageProperties()
                .setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,
                        MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(message);
    }
}
