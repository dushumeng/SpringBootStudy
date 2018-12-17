package com.starcor.stb.venom.mapper;

import com.starcor.stb.venom.model.ClientLog;

public interface ClientLogMapper {
    int insert(ClientLog record);

    int insertSelective(ClientLog record);

    ClientLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClientLog record);

    int updateByPrimaryKey(ClientLog record);
}