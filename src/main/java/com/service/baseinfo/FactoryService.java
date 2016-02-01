package com.service.baseinfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.baseinfo.Factory;
import com.persistence.baseinfo.FactoryMapper;

@Service
public class FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;

    public List<Factory> getListByCondition(Factory o) {
        return factoryMapper.selectList(o);
    }

    public List<Factory> getListByCondition(Map<String, Object> map) {
        return factoryMapper.selectList(map);
    }
}
