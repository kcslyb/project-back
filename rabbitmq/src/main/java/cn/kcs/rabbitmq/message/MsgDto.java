package cn.kcs.rabbitmq.message;

import cn.kcs.user.entity.TMsg;

import java.io.Serializable;

/**
 * TMsg tMsg
 *
 * @author kcs
 * @date 2019-05-11 08:16
 **/
public class MsgDto implements Serializable {

    private String requestURI;

    private TMsg message;

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public TMsg getMessage() {
        return message;
    }

    public void setMessage(TMsg message) {
        this.message = message;
    }
}
