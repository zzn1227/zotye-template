package com.persistence;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.model.CarType;

public interface CarTypeMapper {

    public List<CarType> getCarTypes();
    
    public List<CarType> getCarTypeByPage(PageBounds pageBounds);
}
