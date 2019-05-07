package cn.kcs.common;

import java.util.Collection;

/**
 * @description: result data
 * @author: kcs
 * @create: 2019-04-17 20:41
 **/
public class ResultDate {

    private String total;
    private Object object;
    private Collection<Object> list;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Collection<Object> getList() {
        return list;
    }

    public void setList(Collection<Object> list) {
        this.list = list;
    }
}
