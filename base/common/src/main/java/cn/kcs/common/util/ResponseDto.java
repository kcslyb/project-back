package cn.kcs.common.util;

import java.util.Collection;

/**
 * @author kcs
 * @date 2019-11-03 09:14
 **/
public class ResponseDto<E> {
    private Collection<E> list;
    private Integer total;
    private Integer size;
    private Integer start;

    public ResponseDto(Collection<E> list, Integer total, Integer size, Integer start) {
        this.list = list;
        this.total = total;
        this.size = size;
        this.start = start;
    }

    public Collection<E> getList() {
        return list;
    }

    public void setList(Collection<E> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}
