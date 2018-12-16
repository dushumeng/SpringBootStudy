package com.starcor.stb.core.pager;

public class PagerVo {

    private static final long serialVersionUID = 1L;
    private int pageNo = 1;
    private int pageSize = 10;
    private boolean returns;
    private Integer startAt; //从多少条开始查询，用法参见shopMapper.searchShopOrGoods

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isReturns() {
        return returns;
    }

    public void setReturns(boolean returns) {
        this.returns = returns;
    }

}
