package com.starcor.stb.core.pager;

import com.starcor.stb.core.util.ArrayUtils;

import java.util.List;

public class Pager<T> extends SimplePager{

    // 是否有下一页
    protected boolean more;
    /**
     * 当前页的数据
     */
    @SuppressWarnings("unchecked")
    private List<T> list;

    public Pager() {
    }

    public Pager(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    @SuppressWarnings("unchecked")
    public Pager(int pageNo, int pageSize, int totalCount, List list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }

    @SuppressWarnings("unchecked")
    public List<T> getList() {
        return list;
    }

    @SuppressWarnings("unchecked")
    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public int getRows() {
        if (ArrayUtils.isEmpty(list)) {
            return 0;
        }
        return list.size();
    }

}
