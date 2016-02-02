package com.model.rights;

/**
 * @Description:角色实体类
 * @author zhaozhineng
 * @date 2016-2-1 下午3:18:46
 */
public class Role {

    private Integer id;
    private String  name;
    private String  description;
    private String  menuIds;
    private String  authorityIds;
    private String  functionIds;
    private Integer roleType;
    private String  factoryNum;
    private String  factoryName;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public String getAuthorityIds() {
        return authorityIds;
    }

    public void setAuthorityIds(String authorityIds) {
        this.authorityIds = authorityIds;
    }

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

}
