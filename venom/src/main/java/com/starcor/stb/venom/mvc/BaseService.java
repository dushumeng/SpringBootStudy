package com.starcor.stb.venom.mvc;

import com.starcor.stb.core.mybatis.MybatisService;
import com.starcor.stb.core.pager.Pager;
import com.starcor.stb.core.pager.PagerVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;

@Service
public abstract class BaseService<T> {

    @Autowired
    protected MybatisService mybatisService;

    private String namespace;

    public String statement(String statement) {
        if (StringUtils.isEmpty(namespace)) {
            namespace = namespace();
        }
        return namespace + "." + statement;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    protected abstract String namespace();

    @Transactional
    public int insert(T bean) {
        Assert.notNull(bean);
        return mybatisService.insert(statement("insertSelective"), bean);
    }

    @Transactional
    public int update(T bean) {
        Assert.notNull(bean);
        return mybatisService.update(statement("updateByPrimaryKeySelective"), bean);
    }

    @Transactional
    public int save(T bean) {
        Assert.notNull(bean);
        if (getPrimaryKey(bean, "id") == null) {

            return this.insert(bean);
        } else {
            return this.update(bean);
        }
    }

    @Transactional
    public int delete(Serializable id) {
        return mybatisService.delete(statement("deleteByPrimaryKey"), id);
    }

    protected String getPrimaryKey(Object bean, String primaryKeyName) {
        String primaryKey = null;
        try {
            primaryKey = BeanUtils.getProperty(bean, primaryKeyName);
        } catch (Exception e) {

        }
        return primaryKey;
    }

    public T findById(Serializable id) {
        return mybatisService.findOne(statement("selectByPrimaryKey"), id);
    }

    public Pager findPage(PagerVo vo) {
        return mybatisService.find(statement("findPage"), statement("countPage"), vo, vo.getPageNo(), vo.getPageSize());
    }
}
