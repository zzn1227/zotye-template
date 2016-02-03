package com.controller.rights;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.rights.AuthorityService;
import com.service.rights.MenuService;
import com.service.rights.RoleService;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-1 下午4:06:29
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    // private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleService        roleService;
    @Autowired
    private AuthorityService   authorityService;
    @Autowired
    private MenuService        menuService;

}
