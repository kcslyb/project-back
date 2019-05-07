package cn.kcs.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * @description: Receiver
 * @author: kcs
 * @create: 2019-05-05 16:23
 **/
@Component
//@RabbitListener(queues = "msg")
public class MsgReceiver {

    Logger logger = LoggerFactory.getLogger(MsgSender.class);

    @RabbitHandler
    public void process(String string) {
        logger.info("[::]Receiver  : " + string);
    }
}
