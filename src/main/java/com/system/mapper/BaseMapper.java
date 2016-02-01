package com.system.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-1-26 下午1:03:24
 */
public interface BaseMapper<T> {

    /**
     * 新增记录
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public int insert(T record);

    /**
     * 新增记录
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public int insertSelective(T record);

    /**
     * 根据主键删除
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public int deleteByPrimaryKey(T record);

    /**
     * 根据主键更新
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public int updateByPrimaryKey(T record);

    /**
     * 根据主键选择性更新
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键查询单条记录
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public T selectByPrimaryKey(Serializable id);

    /**
     * 查询所有记录
     * 
     * @author zhaozhineng
     * @date 2016-1-28
     */
    public List<T> selectList();

    /**
     * 查询列表
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public List<T> selectList(T o);

    /**
     * 查询列表
     * 
     * @author zhaozhineng
     * @date 2016-1-26
     */
    public List<T> selectList(Map<String, Object> param);

}
