package cn.kcs.note.entity;

import java.util.List;

/**
 * @description: contact dto
 * @author: kcs
 * @create: 2019-01-02 16:55
 **/
public class ContactDto {
    private String userId;
    private String userName;
    private String currentUserId;
    private Integer noticeNumber;
    private String headPortrait;
    private List<String> msgsId;

    public List<String> getMsgsId() {
        return msgsId;
    }

    public void setMsgsId(List<String> msgsId) {
        this.msgsId = msgsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(Integer noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}
