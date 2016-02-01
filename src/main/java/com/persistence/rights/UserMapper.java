package com.persistence.rights;

import com.model.rights.User;
import com.system.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 检查用户是否存在
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    public User checkUser(User user);
}
