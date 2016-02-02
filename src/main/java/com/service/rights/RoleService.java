package com.service.rights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.rights.Role;
import com.persistence.rights.RoleMapper;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-1 下午4:52:14
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public Role getRole(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

}
