package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TMsg)实体类
 *
 * @author kcs
 * @since 2018-12-30 19:47:03
 */
public class TMsg implements Serializable {
    private static final long serialVersionUID = -93781891348912533L;

    private String msgId;

    private String msgSender;

    private String msgReceiver;

    private String msgContent;

    private Date msgCreattime;

    private String msgStatus;

    private String msgStandby;

    public TMsg() {
    }

    public TMsg(TMsg msg) {
        this.msgId = msg.msgId;
        this.msgSender = msg.msgSender;
        this.msgReceiver = msg.msgReceiver;
        this.msgContent = msg.msgContent;
        this.msgCreattime = msg.msgCreattime;
        this.msgStatus = msg.msgStatus;
        this.msgStandby = msg.msgStandby;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Date getMsgCreattime() {
        return msgCreattime;
    }

    public void setMsgCreattime(Date msgCreattime) {
        this.msgCreattime = msgCreattime;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsgStandby() {
        return msgStandby;
    }

    public void setMsgStandby(String msgStandby) {
        this.msgStandby = msgStandby;
    }

    @Override
    public String toString() {
        return "TMsg{" +
                "msgId='" + msgId + '\'' +
                ", msgSender='" + msgSender + '\'' +
                ", msgReceiver='" + msgReceiver + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgCreattime=" + msgCreattime +
                ", msgStatus='" + msgStatus + '\'' +
                ", msgStandby='" + msgStandby + '\'' +
                '}';
    }
}