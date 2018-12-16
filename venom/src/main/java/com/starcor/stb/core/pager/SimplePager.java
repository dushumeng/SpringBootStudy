package com.starcor.stb.core.pager;

public class SimplePager<T> implements PagerIface, java.io.Serializable {
    public static final int DEF_COUNT = 20;
    private static final long serialVersionUID = 1L;
    protected int totalCount = 0;
    protected int pageSize = 20;
    protected int pageNo = 1;
    protected int totalPage = 0;

    public SimplePager() {
    }

    public SimplePager(int pageNo, int pageSize, int totalCount) {
        if (totalCount <= 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }
        if (pageSize <= 0) {
            this.pageSize = DEF_COUNT;
        } else {
            this.pageSize = pageSize;
        }
        if (pageNo <= 0) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
        if ((this.pageNo - 1) * this.pageSize >= totalCount) {
            this.pageNo = totalCount / pageSize;
        }
    }

    /**
     * 调整分页参数，使合理化
     */
    public void adjustPage() {
        if (totalCount <= 0) {
            totalCount = 0;
        }
        if (pageSize <= 0) {
            pageSize = DEF_COUNT;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        if ((pageNo - 1) * pageSize >= totalCount) {
            pageNo = totalCount / pageSize;
        }
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0) {
            totalPage++;
        }
        if (totalCount == 0) {
            totalPage = 0;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }
}
