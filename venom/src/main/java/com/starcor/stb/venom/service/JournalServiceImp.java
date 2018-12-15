package com.starcor.stb.venom.service;

import com.starcor.stb.venom.api.ApiHeaderVo;
import com.starcor.stb.venom.mapper.ApiHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chongyang.gao on 2018/12/15.
 */
@Service("journalService")
public class JournalServiceImp implements JournalService {

    private ApiHeaderMapper apiHeaderMapper;

    @Autowired
    public JournalServiceImp(ApiHeaderMapper apiHeaderMapper){
        this.apiHeaderMapper = apiHeaderMapper;
    }

    @Override
    public boolean insertJournalInfo(ApiHeaderVo apiHeaderVo) {
        if (apiHeaderVo == null){
            return false;
        }
        int insert = apiHeaderMapper.insert(apiHeaderVo);
        if (insert >0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public ApiHeaderVo select(Integer id) {
        if (id == null){
            return null;
        }
        return apiHeaderMapper.select(id);
    }

    @Override
    public List<ApiHeaderVo> selectAll() {
        return apiHeaderMapper.selectAll();
    }

    @Override
    public boolean updateValue(ApiHeaderVo apiHeaderVo) {
        int i = apiHeaderMapper.updateValue(apiHeaderVo);
        return i > 0;
    }

    @Override
    public boolean delete(Integer id) {
        int i = apiHeaderMapper.delete(id);
        return i > 0;
    }


}
