package com.service.rights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.model.rights.User;
import com.persistence.rights.UserMapper;
import com.system.page.PageParam;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Page<User> queryByPage(User param, PageParam page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
        return (Page<User>) userMapper.selectList(param);
    }

    public int insert(User param) {
        return userMapper.insert(param);
    }

    /**
     * 检查用户是否存在
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    public User checkUser(User param) {
        return userMapper.checkUser(param);
    }

    /**
     * 更新用户
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
