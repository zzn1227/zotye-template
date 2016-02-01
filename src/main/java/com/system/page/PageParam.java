package com.system.page;

public class PageParam {

    /**
     * 页码
     */
    private int    pageNum;
    /**
     * 页面大小
     */
    private int    pageSize;

    /**
     * 排序
     */
    private String orderBy;

    public PageParam() {
        super();
    }

    public PageParam(int pageNum, int pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageParam(int pageNum, int pageSize, String orderBy) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

}
