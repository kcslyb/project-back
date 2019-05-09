package cn.kcs.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @description: Common Mq Listener
 * @author: kcs
 * @date: 2019-05-07 15:35
 **/
@Component
public class CommonMqListener {

    private static final Logger log = LoggerFactory.getLogger(CommonMqListener.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 监听消费
     *
     * @param message
     */
    @RabbitListener(queues = "msg_queue_name", containerFactory = "singleListenerContainer")
    public void consumeUserMsg(@Payload byte[] message) {
        try {
//            log.info("[--]监听到消息： {} ", objectMapper.writeValueAsString(message));
            log.info("[--]监听到 [msg] 消息： {} ", new String(message, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听消费
     *
     * @param message
     */
    @RabbitListener(queues = "accept_queue_name", containerFactory = "multiListenerContainer")
    public void consumeUserAccept(@Payload byte[] message) {
        try {
//            log.info("[--]监听到消息： {} ", objectMapper.writeValueAsString(message));
            log.info("[--]监听到 [accept] 消息： {} ", new String(message, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
