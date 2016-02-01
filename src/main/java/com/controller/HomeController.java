package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.baseinfo.Factory;
import com.model.rights.User;
import com.service.baseinfo.FactoryService;
import com.service.rights.UserService;
import com.system.base.ReturnModel;
import com.utils.Constants;
import com.utils.MD5;
import com.utils.PasswordUtils;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-1-30 下午3:44:47
 */
@Controller
public class HomeController {

    // private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private UserService    userService;

    /**
     * 显示登陆页面
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    @RequestMapping(method = RequestMethod.GET, value = "index")
    public String index(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
        List<Factory> factoryList = factoryService.getListByCondition(new Factory());
        model.put("factoryList", factoryList);

        return "login";
    }

    /**
     * 登陆
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    @RequestMapping(method = RequestMethod.POST, value = "doLogin")
    @ResponseBody
    public ReturnModel doLogin(HttpServletRequest req, User userParam, Map<String, Object> model) {
        ReturnModel result = new ReturnModel();
        User userinfo = userService.checkUser(userParam);
        if (userinfo == null) {
            result.setErrorMsg("用户名不存在！");
        } else {
            if (MD5.verify(userParam.getPassword(), userinfo.getPassword())) {
                if (Constants.USER_ENABLE == userinfo.getStatus()) {
                    req.getSession().removeAttribute("userinfo");
                    req.getSession().setAttribute("userinfo", userinfo);
                    req.getSession().setMaxInactiveInterval(-1);

                    if (PasswordUtils.isWeakPassword(userParam.getPassword())) {
                        // 如果密码是弱口令密码 则必须先去修改密码
                        result.setErrorMsg("weakPassword");
                    }
                } else {
                    result.setErrorMsg("该账户已被冻结，请联系系统管理员！");
                }
            } else {
                result.setErrorMsg("密码错误！");
            }
        }
        return result;
    }
}
