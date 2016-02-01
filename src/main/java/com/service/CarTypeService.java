package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CarType;
import com.persistence.CarTypeMapper;

@Service
public class CarTypeService {

    @Autowired
    private CarTypeMapper carTypeMapper;

    public List<CarType> getCarTypes() {
        return carTypeMapper.getCarTypes();
    }

}
