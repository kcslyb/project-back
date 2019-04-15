package cn.kcs.service.inter.util;

import org.tinygroup.commons.namestrategy.impl.CamelCaseStrategy;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页dto
 * Created by kcs
 */
public class PageRequest implements Serializable {

    private Integer pageNo = 1; // 当前页码
    private Integer pageSize; // 页面大小

    private Integer start;//开始记录数

    private List<String> orderByList; // 排序字段

    private List<Boolean> orderAscList;//是否为升序

    public PageRequest() {
        this.pageSize = -1;
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     */
    public PageRequest(Integer pageNo, Integer pageSize) {
        this.setPageNo(pageNo);
        if (pageSize == null)
            return;
        this.pageSize = pageSize;
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码
     *
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        if (pageNo == null) return;
        this.pageNo = pageNo;
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面大小
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null) return;
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    public List<String> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<String> orderByList) {
        this.orderByList = orderByList;
    }

    public List<Boolean> getOrderAscList() {
        return orderAscList;
    }

    public void setOrderAscList(List<Boolean> orderAscList) {
        this.orderAscList = orderAscList;
    }

    public int getStart() {
        if (start == null) {
            start = (pageNo - 1) * pageSize;
        }
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        Integer from = getStart();
        int end = 0;
        if (from != null) {
            end = from + pageSize;
        }
        return end;
    }

    public void addOrder(String orderType, boolean asc) {
        if (orderAscList == null) {
            orderAscList = new ArrayList<Boolean>();
        }
        orderAscList.add(asc);

        if (orderByList == null) {
            orderByList = new ArrayList<String>();
        }
        orderByList.add(orderType);
    }

    public void setOrder(String order) {
        if (orderAscList == null) {
            orderAscList = new ArrayList<Boolean>();
        }
        if ("desc".equals(order)) {
            orderAscList.add(false);
        } else {
            orderAscList.add(true);
        }
    }

    public void setSort(String orderType) {
        if (orderByList == null) {
            orderByList = new ArrayList<String>();
        }
        orderByList.add(orderType);
    }

    /**
     * 获取排序字段,将Java属性字段转换成数据库字段
     *
     * @return
     */
    public List<String> getOrderNameList() {
        CamelCaseStrategy camelCaseStrategy = new CamelCaseStrategy();
        List<String> list = new ArrayList<String>();
        if (!CollectionUtil.isEmpty(orderByList)) {
            for (String s : orderByList) {
                if (!StringUtil.isBlank(s)) {
                    list.add(camelCaseStrategy.getFieldName(s));
                }
            }
        }
        return list;
    }

}
