package com.model.rights;

/**
 * @Description:菜单实体类
 * @author zhaozhineng
 * @date 2016-2-1 下午3:47:11
 */
public class Menu {

    private Integer id;
    private String  name;
    private String  url;
    private Integer parentId;
    private String  orderNum;  // 顺序
    private Integer isFunction; // 0为菜单 1为功能

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsFunction() {
        return isFunction;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setIsFunction(Integer isFunction) {
        this.isFunction = isFunction;
    }

}
