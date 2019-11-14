package cn.kcs.rabbitmq;

import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.entity.TMsg;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.service.TMsgService;
import cn.kcs.websocket.WebSocketServer;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.encoder.org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * Common Mq Listener
 *
 * @author kcs
 * @date 2019-05-07 15:35
 **/
@Component
public class CommonMqListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonMqListener.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    public static CommonMqListener commonMqListener;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private TMsgService tMsgService;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserAccountDao userAccountDao;

    /**
     * 解决服务自动注入失败
     */
    @PostConstruct
    public void init() {
        commonMqListener = this;
        commonMqListener.tMsgService = this.tMsgService;
        commonMqListener.userAccountDao = this.userAccountDao;
    }

    /**
     * 监听消费
     *
     * @param message
     */
    @RabbitListener(queues = "msg_queue_name", containerFactory = "singleListenerContainer")
    public void consumeUserMsg(@Payload byte[] message) {
        try {
            String string = StringEscapeUtils.unescapeJavaScript(new String(message, StandardCharsets.UTF_8));
            String substring = string.substring(1, string.length() - 1);
            TMsg msgDto = objectMapper.readValue(substring, TMsg.class);
            TMsg msg = new TMsg(msgDto);
            UserAccount userAccount = commonMqListener.userAccountDao.queryById(msgDto.getMsgSender());
            msgDto.setMsgSender(userAccount.getUserName());
            if (WebSocketServer.isOnline(msgDto.getMsgReceiver())) {
                WebSocketServer.sendInfo(JSONObject.toJSONString(msgDto), msgDto.getMsgReceiver());
            }
            commonMqListener.tMsgService.insert(msg);
            LOGGER.info("队列[msg_queue_name]监听到[msg]消息： {} ", msgDto.toString());
        } catch (Exception e) {
            LOGGER.info("SOCKET推送信息异常:", e);
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
            LOGGER.info("队列[accept_queue_name]监听到 [accept] 消息： {} ", new String(message, StandardCharsets.UTF_8));
            WebSocketServer.sendInfo(new String(message, StandardCharsets.UTF_8), "info");
        } catch (Exception e) {
            LOGGER.info("SOCKET推送信息异常:", e);
        }
    }
}
