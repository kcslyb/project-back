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

    public void initStart(PageRequest pageRequest) {
        if (pageRequest.size == null || pageRequest.size <= 0) {
            pageRequest.size = 10;
        }
        if (pageRequest.start == null || pageRequest.start <= 1) {
            pageRequest.start = 1;
        } else {
            pageRequest.start = (pageRequest.start - 1) * pageRequest.size + 1;
        }
    }
}
