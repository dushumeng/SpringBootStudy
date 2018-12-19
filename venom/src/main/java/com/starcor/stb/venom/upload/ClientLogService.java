package com.starcor.stb.venom.upload;

import com.starcor.stb.venom.model.ClientLog;
import com.starcor.stb.venom.mvc.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLogService extends BaseService<ClientLog> {


    @Override
    protected String namespace() {
        return "com.starcor.stb.venom.mapper.ClientLogMapper";
    }

    public List<ClientLog> listAll() {
        return mybatisService.findList(statement("listAll"), null);
    }
}
