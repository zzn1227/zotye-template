package com.controller.rights;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.rights.Menu;
import com.service.rights.MenuService;
import com.system.base.ReturnModel;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-3 上午10:15:05
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    private static final Logger log = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private HttpServletRequest  request;

    @Autowired
    private MenuService         menuService;

    /**
     * 显示菜单管理页面
     * 
     * @author zhaozhineng
     * @date 2016-2-3
     */
    @RequestMapping(value = "")
    public String management() {
        return "rights/menu";
    }

    /**
     * 显示菜单管理树
     * 
     * @author zhaozhineng
     * @date 2016-2-3
     */
    @RequestMapping(value = "/menuTreeList")
    @ResponseBody
    public String menuTreeList(Menu menu, @RequestParam(defaultValue = "0")
    Integer node) {
        menu.setParentId(node);
        return menuService.assemblyConfigMenuTree(menu);
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ReturnModel save(Menu menu) {
        ReturnModel result = new ReturnModel();
        int count = 0;
        try {
            count = menuService.insertMenu(menu);
        } catch (Exception e) {
            log.error("保存菜单失败！", e);
        }
        if (count <= 0) {
            result.setErrorMsg("保存菜单失败！");
        }
        return result;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public ReturnModel update(Menu menu) {
        ReturnModel result = new ReturnModel();
        int count = 0;
        try {
            count = menuService.updateByPrimaryKeySelective(menu);
        } catch (Exception e) {
            log.error("更新菜单失败！", e);
        }
        if (count <= 0) {
            result.setErrorMsg("更新菜单失败！");
        }
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnModel delete(Menu menu) {
        ReturnModel result = new ReturnModel();
        int count = 0;
        try {
            count = menuService.deleteMenu(menu);
        } catch (Exception e) {
            log.error("删除菜单失败！", e);
        }
        if (count <= 0) {
            result.setErrorMsg("删除菜单失败！");
        }
        return result;
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public ReturnModel get(Menu param) {
        ReturnModel result = new ReturnModel();
        Menu menu = menuService.getMenu(param.getId());
        result.setData(menu);
        return result;
    }

}
