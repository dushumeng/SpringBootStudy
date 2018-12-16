package com.starcor.stb.venom.mapper;

import com.starcor.stb.venom.api.ApiHeaderVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chongyang.gao on 2018/12/15.
 */
@Mapper
@Component
public interface ApiHeaderMapper {
    //插入数据
    @Insert("INSERT INTO journal_tb(" +
            "client_os,client_os_version,client_version,client_versionname,device_uuid,device_model,device_brand,client_market,device_mac,user_id,timestamp,journal_path) " +
            "VALUES(#{client_os},#{client_os_version},#{client_version}, #{client_versionname}, #{device_uuid}, #{device_model}, #{device_brand}, #{client_market}, #{device_mac}, #{user_id}, #{timestamp}, #{journal_path})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'journal_tb')", before = false, keyProperty = "journal_id", resultType = int.class)
    int insert(ApiHeaderVo apiHeaderVo);

    // 根据 ID 查询
    @Select("SELECT * FROM journal_tb WHERE journal_id=#{id}")
    ApiHeaderVo select(int id);

    // 查询全部
    @Select("SELECT * FROM journal_tb")
    List<ApiHeaderVo> selectAll();

    // 更新 value
    @Update("UPDATE journal_tb SET value=#{value} WHERE journal_id=#{id}")
    int updateValue(ApiHeaderVo apiHeaderVo);

    // 根据 ID 删除
    @Delete("DELETE FROM journal_tb WHERE journal_id=#{id}")
    int delete(Integer id);

}
