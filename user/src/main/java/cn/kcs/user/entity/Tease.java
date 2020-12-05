package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Tease)实体类
 *
 * @author kcs
 * @since 2019-01-06 20:33:36
 */
public class Tease implements Serializable {
    private static final long serialVersionUID = 245952807605855541L;

    private String teaseId;

    private String teaseTitle;

    private String teaseCreatby;

    private Date teaseCreattime;

    private String teaseContent;

    private String teaseDto;


    public String getTeaseId() {
        return teaseId;
    }

    public void setTeaseId(String teaseId) {
        this.teaseId = teaseId;
    }

    public String getTeaseTitle() {
        return teaseTitle;
    }

    public void setTeaseTitle(String teaseTitle) {
        this.teaseTitle = teaseTitle;
    }

    public String getTeaseCreatby() {
        return teaseCreatby;
    }

    public void setTeaseCreatby(String teaseCreatby) {
        this.teaseCreatby = teaseCreatby;
    }

    public Date getTeaseCreattime() {
        return teaseCreattime;
    }

    public void setTeaseCreattime(Date teaseCreattime) {
        this.teaseCreattime = teaseCreattime;
    }

    public String getTeaseContent() {
        return teaseContent;
    }

    public void setTeaseContent(String teaseContent) {
        this.teaseContent = teaseContent;
    }

    public String getTeaseDto() {
        return teaseDto;
    }

    public void setTeaseDto(String teaseDto) {
        this.teaseDto = teaseDto;
    }

    @Override
    public String toString() {
        return "Tease{" +
                "teaseId='" + teaseId + '\'' +
                ", teaseTitle='" + teaseTitle + '\'' +
                ", teaseCreatby='" + teaseCreatby + '\'' +
                ", teaseCreattime=" + teaseCreattime +
                ", teaseContent='" + teaseContent + '\'' +
                ", teaseDto='" + teaseDto + '\'' +
                '}';
    }
}