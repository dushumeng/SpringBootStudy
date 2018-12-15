package com.starcor.stb.venom.service;

import com.starcor.stb.venom.api.ApiHeader;
import com.starcor.stb.venom.api.ApiHeaderVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chongyang.gao on 2018/12/15.
 */
public interface JournalService {

    boolean insertJournalInfo(ApiHeaderVo apiHeaderVo);

    ApiHeaderVo select(Integer id);

    List<ApiHeaderVo> selectAll();

    boolean updateValue(ApiHeaderVo model);

    boolean delete(Integer id);

}
