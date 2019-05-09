package cn.kcs.note.entity.dto;

import cn.kcs.note.entity.TMsg;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2019-01-02 15:39
 **/
public class MsgDto implements Serializable {
    private Integer infoNumber;
    private Integer start;
    private Integer limit;
    private String userName;
    private String msgSender;
    private String msgReceiver;
    private String msgContent;
    private String standby;
    private List<TMsg> msgs;

    public Integer getInfoNumber() {
        return infoNumber;
    }

    public void setInfoNumber(Integer infoNumber) {
        this.infoNumber = infoNumber;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStandby() {
        return standby;
    }

    public void setStandby(String standby) {
        this.standby = standby;
    }

    public List<TMsg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<TMsg> msgs) {
        this.msgs = msgs;
    }

    @Override
    public String toString() {
        return "MsgDto{" +
                "infoNumber=" + infoNumber +
                ", start=" + start +
                ", limit=" + limit +
                ", userName='" + userName + '\'' +
                ", msgSender='" + msgSender + '\'' +
                ", msgReceiver='" + msgReceiver + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", standby='" + standby + '\'' +
                ", msgs=" + msgs +
                '}';
    }
}
