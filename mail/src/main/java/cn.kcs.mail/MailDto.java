package cn.kcs.mail;

import org.springframework.lang.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author kcs
 * @date 2019-09-25 16:25
 **/
public class MailDto implements Serializable {
    @Nullable
    private String replyTo;
    @Nullable
    private String[] to;
    @Nullable
    private String[] cc;
    @Nullable
    private String[] bcc;
    @Nullable
    private Date sentDate;
    @Nullable
    private String subject;
    @Nullable
    private String title;
    @Nullable
    private String content;
    List<File> fileList;

    public MailDto(@Nullable String[] to, @Nullable String title, @Nullable String content) {
        this.to = to;
        this.title = title;
        this.content = content;
    }

    @Nullable
    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(@Nullable String replyTo) {
        this.replyTo = replyTo;
    }

    @Nullable
    public String[] getTo() {
        return to;
    }

    public void setTo(@Nullable String[] to) {
        this.to = to;
    }

    @Nullable
    public String[] getCc() {
        return cc;
    }

    public void setCc(@Nullable String[] cc) {
        this.cc = cc;
    }

    @Nullable
    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(@Nullable String[] bcc) {
        this.bcc = bcc;
    }

    @Nullable
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(@Nullable Date sentDate) {
        this.sentDate = sentDate;
    }

    @Nullable
    public String getSubject() {
        return subject;
    }

    public void setSubject(@Nullable String subject) {
        this.subject = subject;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}
