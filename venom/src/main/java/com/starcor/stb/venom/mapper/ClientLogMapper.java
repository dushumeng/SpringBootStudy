package com.starcor.stb.venom.mapper;

import com.starcor.stb.venom.model.ClientLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ClientLogMapper {
    int insert(ClientLog record);

    int insertSelective(ClientLog record);

    ClientLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientLog record);

    int updateByPrimaryKey(ClientLog record);

    List<ClientLog> listAll();
}