package com.starcor.stb.core.mybatis;

import com.starcor.stb.core.pager.Pager;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MyBatis通用操作类
 *
 * @author fengwx
 */
@Service
public class MybatisService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 执行删除语句
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @return 删除记录数
     */
    public int delete(String statement, Object parameter) {
        return sqlSessionTemplate.delete(statement, parameter);
    }

    /**
     * 执行插入语句
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @return 插入记录数
     */
    public int insert(String statement, Object parameter) {
        return sqlSessionTemplate.insert(statement, parameter);
    }

    /**
     * 执行更新语句
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @return 更新记录数
     */
    public int update(String statement, Object parameter) {
        return sqlSessionTemplate.update(statement, parameter);
    }

    /**
     * 查询单条记录，返回该记录的对象
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @return 对象
     */
    public <T> T findOne(String statement, Object parameter) {
        return sqlSessionTemplate.selectOne(statement, parameter);
    }

    /**
     * 查询多条记录，使用ResultHandler处理每行记录，一般用于大数据量的处理
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @param handler   每条记录的处理对象
     */
    public void findWithHandler(String statement, Object parameter, ResultHandler handler) {
        sqlSessionTemplate.select(statement, parameter, handler);
    }

    /**
     * 分页查询多条记录，使用ResultHandler处理每行记录，一般用于大数据量的处理
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @param pageNo    当前页码
     * @param pageSize  每页数量
     * @param handler   每条记录的处理对象
     */
    public void findWithHandler(String statement, Object parameter, int pageNo, int pageSize, ResultHandler handler) {
        sqlSessionTemplate.select(statement, parameter, new RowBounds((pageNo - 1) * pageSize, pageSize), handler);
    }

    /**
     * 查询多条记录，把list转换成map返回
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @param mapKey    map的key
     * @return Map
     */
    public Map findMap(String statement, Object parameter, String mapKey) {
        return sqlSessionTemplate.selectMap(statement, parameter, mapKey);
    }

    /**
     * 查询多条记录，把list转换成set返回
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @return Map
     */
    public Set findSet(String statement, Object parameter) {
        List list = findList(statement, parameter);
        return new LinkedHashSet(list);
    }

    /**
     * 查询多条记录，返回list
     *
     * @param statement sql语句
     * @return List
     */
    public <T> List<T> findList(String statement) {
        return sqlSessionTemplate.selectList(statement);
    }

    /**
     * 查询多条记录，返回list
     *
     * @param statement sql语句
     * @param parameter 参数，一般为Map
     * @return List
     */
    public <T> List<T> findList(String statement, Object parameter) {
        return sqlSessionTemplate.selectList(statement, parameter);
    }

    /**
     * 分页查询多条记录，返回list
     *
     * @param statement sql语句
     * @param parameter 参数
     * @param pageNo    当前页码
     * @param pageSize  每页数量
     * @return List
     */
    public <T> List<T> findList(String statement, Object parameter, int pageNo, int pageSize) {
        return sqlSessionTemplate.selectList(statement, parameter, new RowBounds((pageNo - 1) * pageSize, pageSize));
    }

    /**
     * 分页查询多条记录，同时统计记录总数，返回分页对象
     *
     * @param selectStatement 查询sql语句
     * @param countStatement  统计sql语句
     * @param parameter       参数，一般为Map
     * @param pageNo          当前页码
     * @param pageSize        每页数量
     * @return Pagination
     */
    public <T> Pager<T> find(String selectStatement, String countStatement, Object parameter, int pageNo, int pageSize) {
        List<T> content = sqlSessionTemplate.selectList(selectStatement, parameter, new RowBounds((pageNo - 1) * pageSize, pageSize));
        Integer total = sqlSessionTemplate.selectOne(countStatement, parameter);
        if (null == total) {
            total = 0;
        }
        Pager<T> pagination = new Pager<T>(pageNo, pageSize, total, content);
        return pagination;
    }

    /**
     * 获取批量提交对象，默认最大提交数量为1000
     *
     * @param statement 插入或更新的sql语句
     * @return MybatisBatcher
     */
    public MybatisBatcher getBatcher(String statement) {
        MybatisBatcher batcher = new MybatisBatcher(statement);
        batcher.setService(this);
        return batcher;
    }

    /**
     * 获取批量提交对象，指定最大提交数量，当缓存数据量达到batchSize时提交给数据库
     *
     * @param statement 插入或更新的sql语句
     * @param batchSize 最大提交数量
     * @return MybatisBatcher
     */
    public MybatisBatcher getBatcher(String statement, int batchSize) {
        MybatisBatcher batcher = new MybatisBatcher(statement, batchSize);
        batcher.setService(this);
        return batcher;
    }

    /**
     * 查询统计数据
     *
     * @param statement 插入或更新的sql语句
     * @param parameter 参数
     * @return 统计结果
     */
    public int count(String statement, Object parameter) {
        Integer count = sqlSessionTemplate.selectOne(statement, parameter);
        if (count == null) {
            return 0;
        }
        return count.intValue();
    }

    /**
     * 查询统计数据的double
     *
     * @param statement 插入或更新的sql语句
     * @param parameter 参数
     * @return 统计结果
     */
    public Double countDouble(String statement, Object parameter) {
        Double d = sqlSessionTemplate.selectOne(statement, parameter);
        return null == d ? 0d : d;
    }
}
