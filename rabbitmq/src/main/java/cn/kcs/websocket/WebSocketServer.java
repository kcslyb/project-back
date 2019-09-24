package cn.kcs.websocket;

import cn.kcs.rabbitmq.MsgSender;
import cn.kcs.user.dao.TMsgDao;
import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.entity.TMsg;
import cn.kcs.user.entity.UserAccount;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description: WebSocketServer
 * @author: kcs
 * @date: 2019-05-10 09:51
 **/
@Component
@ServerEndpoint("/socket/{sid}")
public class WebSocketServer {

    public static WebSocketServer webSocketServer;
    static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    @Autowired
    private MsgSender msgSender;
    @Autowired
    private TMsgDao tMsgDao;
    @Autowired
    private UserAccountDao userAccountDao;
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    // 接收sid
    private String sid = "";

    public static boolean isOnline(String sid) {
        for (WebSocketServer item : webSocketSet) {
            if (item.sid.equals(sid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        logger.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                // 这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                logger.info("推送消息失败:{}", e.getMessage());
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    /**
     * 解决msgSender自动注入失败
     */
    @PostConstruct
    public void init() {
        webSocketServer = this;
        webSocketServer.msgSender = this.msgSender;
        webSocketServer.tMsgDao = this.tMsgDao;
        webSocketServer.userAccountDao = this.userAccountDao;
    }

    /**
     * 连接建立成功调用的方法
     **/
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.sid = sid;
        this.session = session;
        // 加入set中
        webSocketSet.add(this);
        // 在线数加1
        addOnlineCount();
        logger.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        TMsg tMsg = new TMsg();
        tMsg.setMsgStatus("0");
        tMsg.setMsgReceiver(sid);
        List<TMsg> list = webSocketServer.tMsgDao.queryAllByMsg(tMsg);
        if (CollectionUtils.isNotEmpty(list)) {
            logger.info("sid" + "存在" + list.size() + "未读消息");
            for (TMsg msg : list) {
                UserAccount userAccount = webSocketServer.userAccountDao.queryById(msg.getMsgSender());
                msg.setMsgSender(userAccount.getUserName());
                try {
                    sendInfo(JSONObject.toJSONString(msg), sid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 从set中删除
        webSocketSet.remove(this);
        // 在线数减1
        subOnlineCount();
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口" + sid + "的信息: {}", message);
        try {
            webSocketServer.msgSender.sender("msg_exchange_name", "msg_routing_key_name", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误:{}", error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
