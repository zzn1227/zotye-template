package com.service.rights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.rights.Authority;
import com.persistence.rights.AuthorityMapper;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-2 上午8:33:36
 */
@Service
public class AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     * 根据一批id查询权限
     * 
     * @author zhaozhineng
     * @date 2016-2-2
     */
    public List<Authority> getAuthorityByIds(String[] ids) {
        Authority param = new Authority();
        param.setIds(ids);
        return authorityMapper.selectList(param);
    }

}
