package cn.kcs.service.inter.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页dto
 * Created by kcs.
 */
public class PageResponse<T extends Serializable> implements Serializable {

    private int pageNo = 1; // 当前页码
    private int pageSize; // 页面

    private int count;// 总记录数
    private int start;//开始记录数

    private int first;// 首页索引
    private int last;// 尾页索引

    private boolean firstPage;//是否是第一页
    private boolean lastPage;//是否是最后一页

    private List<T> list = new ArrayList<T>();

    public PageResponse() {
        this.pageSize = -1;
    }


    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     */
    public PageResponse(int pageNo, int pageSize) {
        this(pageNo, pageSize, 0);
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     */
    public PageResponse(int pageNo, int pageSize, int count) {
        this(pageNo, pageSize, count, new ArrayList<T>());
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param count    数据条数
     * @param list     本页数据对象列表
     */
    public PageResponse(int pageNo, int pageSize, int count, List<T> list) {
        this.setCount(count);
        this.setPageNo(pageNo);
        this.pageSize = pageSize;
        setList(list);
    }

    /**
     * 初始化参数
     */
    public void initialize() {
        this.first = 1;

        this.last = count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1;

        if (this.count % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        if (this.pageNo <= 1) {
            this.pageNo = this.first;
            this.firstPage = true;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.lastPage = true;
        }


        if (this.pageNo < this.first) {// 如果当前页小于首页
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {// 如果当前页大于尾页
            this.pageNo = this.last;
        }

    }

    /**
     * 获取设置总数
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置数据总数
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
        if (pageSize >= count) {
            pageNo = 1;
        }
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
    public void setPageNo(int pageNo) {
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
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
    }

    /**
     * 首页索引
     *
     * @return
     */

    public int getFirst() {
        return first;
    }

    /**
     * 尾页索引
     *
     * @return
     */
    public int getLast() {
        return last;
    }

    /**
     * 获取页面总数
     *
     * @return getLast();
     */
    public int getTotalPage() {
        return getLast();
    }

    /**
     * 是否为第一页
     *
     * @return
     */
    public boolean isFirstPage() {
        return firstPage;
    }

    /**
     * 是否为最后一页
     *
     * @return
     */
    public boolean isLastPage() {
        return lastPage;
    }

    /**
     * 上一页索引值
     *
     * @return
     */
    public int getPrev() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    /**
     * 下一页索引值
     *
     * @return
     */
    public int getNext() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    /**
     * 获取本页数据对象列表
     *
     * @return List<T>
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置本页数据对象列表
     *
     * @param list
     */
    public PageResponse<T> setList(List<T> list) {
        this.list = list;
        initialize();
        return this;
    }

    /**
     * 分页是否有效
     *
     * @return this.pageSize==-1
     */
    public boolean isDisabled() {
        return this.pageSize == -1;
    }

    /**
     * 是否进行总数统计
     *
     * @return this.count==-1
     */
    public boolean isNotCount() {
        return this.count == -1;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}

