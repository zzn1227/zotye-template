package com.controller.rights;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.rights.Authority;
import com.model.rights.Role;
import com.model.rights.User;
import com.service.rights.AuthorityService;
import com.service.rights.MenuService;
import com.service.rights.RoleService;
import com.utils.Constants;
import com.utils.DataUtils;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-1 下午4:06:29
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private HttpServletRequest  request;

    @Autowired
    private RoleService         roleService;
    @Autowired
    private AuthorityService    authorityService;
    @Autowired
    private MenuService         menuService;

    @RequestMapping(value = "/getMainMenu")
    public String getMainMenu(Map<String, Object> model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userinfo");
        String roleIds = user.getRoleIds();
        String menuIds = "";
        String mainMenu = "";
        String functionIds = "";
        String authoryIds = "";
        String authorityParams = ""; // 权限参数值

        try {
            if (StringUtils.isNotBlank(roleIds)) {
                StringTokenizer s = new StringTokenizer(roleIds, ",");
                while (s.hasMoreElements()) {
                    String roleId = (String) s.nextElement();
                    Role role = roleService.getRole(Integer.valueOf(roleId));
                    if (role != null) {
                        if (StringUtils.isNotBlank(role.getMenuIds())) {
                            menuIds += role.getMenuIds() + ",";
                        }
                        if (StringUtils.isNotBlank(role.getFunctionIds())) {
                            functionIds += role.getFunctionIds() + ",";
                        }
                        if (StringUtils.isNotBlank(role.getAuthorityIds())) {
                            authoryIds = authoryIds + role.getAuthorityIds() + ",";
                        }
                    }
                }
                // 功能ID
                if (StringUtils.isNotBlank(functionIds)) {
                    // 去重
                    functionIds = functionIds.substring(0, functionIds.length() - 1);
                    String[] functionIdArray = DataUtils.arrayUnique(StringUtils.split(functionIds, ","));
                    session.setAttribute("functionIds", StringUtils.join(functionIdArray, ","));
                } else {
                    session.setAttribute("functionIds", functionIds);
                }

                if (StringUtils.isNotBlank(authoryIds)) {
                    authoryIds = authoryIds.substring(0, authoryIds.length() - 1);
                    // 去重
                    String[] ids = DataUtils.arrayUnique(StringUtils.split(authoryIds, ","));
                    session.setAttribute(Constants.USER_AUTHORYIDS, StringUtils.join(ids, ","));

                    List<Authority> AuthorityList = authorityService.getAuthorityByIds(ids);
                    for (Authority authority : AuthorityList) {
                        authorityParams += authority.getParams() + ",";
                    }
                    if (StringUtils.isNotBlank(authorityParams)) {
                        authorityParams = authorityParams.substring(0, authorityParams.length() - 1);
                        String[] authorityParamsArray = DataUtils.arrayUnique(StringUtils.split(authorityParams, ","));
                        authorityParams = StringUtils.join(authorityParamsArray, ",");
                    }
                    session.setAttribute(Constants.USER_AUTHORY_PARAMS, authorityParams);
                } else {
                    session.setAttribute(Constants.USER_AUTHORYIDS, authoryIds);
                    session.setAttribute(Constants.USER_AUTHORY_PARAMS, authorityParams);
                }
                // 前台菜单显示顺序按ID从小到大排序
                if (StringUtils.isNotBlank(menuIds)) {
                    mainMenu = menuService.getMenuTree(menuIds, request.getContextPath());
                    model.put("mainMenu", mainMenu);
                } else {
                    session.removeAttribute("userinfo");
                    model.put("error", "该用户还没分配菜单项!");
                    return "error";
                }
            } else {
                session.removeAttribute("userinfo");
                model.put("error", "该用户还没分配权限!");
                return "error";
            }
        } catch (Exception e) {
            log.error("请求首页失败!", e);
            session.removeAttribute("userinfo");
            model.put("error", "未知异常！");
            return "error";
        }
        return "index";
    }

    @RequestMapping(value = "/frameTransfer")
    public String frameTransfer(String navigateUrl, Map<String, Object> model) {
        model.put("navigateUrl", navigateUrl);
        return "frameTransfer";
    }

}
