package cn.kcs.common.util;

import java.io.Serializable;

/**
 * @author kcs
 * @date 2019-11-07 15:05
 **/
public class PageRequest implements Serializable {
    private Integer start;
    private Integer size;
    private String keyWord;
    private String orderBy;
    private String startTime;
    private String endTime;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public PageRequest(Integer start, Integer size) {
        this.start = start;
        this.size = size;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public PageRequest initStart(PageRequest pageRequest) {
        if (pageRequest.size == null || pageRequest.size <= 0) {
            pageRequest.size = 10;
        }
        if (pageRequest.start == null || pageRequest.start <= 1) {
            pageRequest.start = 0;
        } else {
            pageRequest.start = (pageRequest.start - 1) * pageRequest.size;
        }
        return pageRequest;
    }
}
