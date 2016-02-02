package com.service.rights;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.model.rights.Menu;
import com.persistence.rights.MenuMapper;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-2 上午11:22:15
 */
@Service
public class MenuService {

    private static final int rootId = 1; // 根节点菜单id

    @Autowired
    private MenuMapper       menuMapper;

    /**
     * 获取菜单树
     * 
     * @author zhaozhineng
     * @date 2016-2-2
     */
    public String getMenuTree(String menuIds, String contextPath) {
        StringBuilder menuBuf = new StringBuilder();
        String mainMenu = "";
        Set<String> set = new HashSet<String>();
        for (String menuId : StringUtils.split(menuIds, ",")) {
            if (!StringUtils.isBlank(menuId)) {
                // 所有授权给用户的菜单
                set.add(menuId);
            }
        }
        // 根节点
        Menu root = menuMapper.selectByPrimaryKey(rootId);

        Menu param = new Menu();
        param.setParentId(rootId);
        param.setIsFunction(0);
        PageHelper.orderBy("orderNum");
        List<Menu> childMenuListTmp = menuMapper.selectList(param);
        List<Menu> childMenuList = new ArrayList<Menu>();
        // 获取用户权限菜单中系统父节点的第一子节点
        for (Menu childMenu : childMenuListTmp) {
            if (set.contains(String.valueOf(childMenu.getId()))) {
                childMenuList.add(childMenu);
            }
        }

        menuBuf.append("<script type=\"text/javascript\">Docs.classData ={\"id\":\"" + root.getId() + "\",\"text\":\"" + root.getName()
                       + "\",\"iconCls\":\"icon-pkg\",\"singleClickExpand\":true,\"children\":[");

        // 组装子菜单json
        for (Menu childMenu : childMenuList) {
            if (childMenu != null && childMenu.getId() != 1) {
                getChildMenuJson(childMenu, set, contextPath, menuBuf);
            }
        }
        menuBuf = menuBuf.deleteCharAt(menuBuf.length() - 1);
        menuBuf.append("]};Docs.icons = {};</script>");
        mainMenu = menuBuf.toString();
        return mainMenu;
    }

    /**
     * 组装子菜单json
     * 
     * @author zhaozhineng
     * @date 2016-2-2
     */
    private void getChildMenuJson(Menu menu, Set<String> set, String contextPath, StringBuilder menuBuf) {
        Menu param = new Menu();
        param.setParentId(menu.getId());
        param.setIsFunction(0);
        PageHelper.orderBy("orderNum");
        List<Menu> childMenuList = menuMapper.selectList(param);
        if (CollectionUtils.isEmpty(childMenuList)) {
            // 如果没有子菜单
            if (!StringUtils.isBlank(menu.getUrl())) {
                // 如果有链接的话作为叶子节点
                menuBuf.append("{\"id\":\"" + menu.getId() + "\",\"text\":\"" + menu.getName() + "\",\"iconCls\":\"icon-cls\",\"cls\":\"cls\",\"href\":\""
                               + contextPath + menu.getUrl() + "\",\"isClass\":true,\"leaf\":true},");
            }
        } else {
            // 有子菜单,组装当前菜单json
            menuBuf.append("{\"id\":\"" + menu.getId() + "\",\"text\":\"" + menu.getName()
                           + "\",\"iconCls\":\"icon-pkg\",\"cls\":\"package\",\"singleClickExpand\":true, \"children\":[");
            // 组装子菜单列表json
            for (Menu childMenu : childMenuList) {
                if (childMenu != null && childMenu.getId() != 1) {
                    getChildMenuJson(childMenu, set, contextPath, menuBuf);
                }
            }
            menuBuf = menuBuf.deleteCharAt(menuBuf.length() - 1);
            menuBuf.append("]},");
        }
    }

}
