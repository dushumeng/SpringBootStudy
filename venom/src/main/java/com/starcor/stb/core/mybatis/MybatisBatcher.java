/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-5-21
 */
package com.starcor.stb.core.mybatis;

import java.util.ArrayList;
import java.util.List;


/**
 * mybatis批处理类
 *
 * @author fengwx
 */
public class MybatisBatcher {

    /**
     * 默认提交数量
     */
    public static final int DEFAULT_COUNT = 1000;


    private List list; // 数据缓存
    private long size = 0; // 共提交次数
    private int execCount = 0; // 每次提交数量
    private String statement; // 执行的语句名称

    private MybatisService mybatisService;

    public MybatisBatcher(String statementName) {
        this(statementName, DEFAULT_COUNT);
    }

    public MybatisBatcher(String statement, int execCount) {
        this.statement = statement;
        this.execCount = execCount;
        list = new ArrayList(execCount);
    }

    /**
     * 批量提交对象。对象会保存在缓存里，待数量等于提交数量时才提交。
     *
     * @param obj
     */
    public void update(Object obj) {
        size++;
        list.add(obj);
        if (list.size() >= execCount) {
            mybatisService.update(statement, list);
            reset();
        }
    }

    /**
     * 提交剩余未处理的对象，并清空缓存
     */
    public void update() {
        if (list.size() > 0) {
            mybatisService.update(statement, list);
            reset();
        }
    }

    /**
     * 清空缓存
     */
    public void reset() {
        list.clear();
    }

    /**
     * 每次提交数量
     */
    public int getExecCount() {
        return execCount;
    }

    /**
     * 总共插入的记录数量
     *
     * @return
     */
    public long getSize() {
        return size;
    }

    public String getStatementName() {
        return statement;
    }

    public void setService(MybatisService mybatisService) {
        this.mybatisService = mybatisService;
    }


}

