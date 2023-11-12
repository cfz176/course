package com.course.server.dto;

import java.util.List;

public class PageDto<V> {

    /**
     * 页码
     */
    protected int page;

    /**
     * 条数
     */
    protected int size;

    /**
     * 总条数
     */
    protected long total;

    protected List<V> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<V> getList() {
        return list;
    }

    public void setList(List<V> list) {
        this.list = list;
    }
}
