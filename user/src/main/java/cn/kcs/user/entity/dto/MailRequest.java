package cn.kcs.user.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author kcs
 * @date 2019-11-07 14:05
 **/
public class MailRequest implements Serializable {
    private String receive;
    private List<String> receiveList;

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public List<String> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(List<String> receiveList) {
        this.receiveList = receiveList;
    }
}
