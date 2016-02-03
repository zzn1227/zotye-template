package com.controller.rights;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.rights.User;
import com.service.rights.UserService;
import com.system.base.ReturnModel;
import com.utils.Constants;
import com.utils.MD5;
import com.utils.PasswordUtils;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-1-30 下午3:44:37
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    /**
     * 登陆
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    @RequestMapping(method = RequestMethod.POST, value = "/doLogin")
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

    /**
     * 显示修改密码页面
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    @RequestMapping(method = RequestMethod.GET, value = "/changePassword")
    public String changePassword(Map<String, Object> model) {
        return "rights/changePassword";
    }

    /**
     * 修改密码
     * 
     * @author zhaozhineng
     * @date 2016-1-30
     */
    @RequestMapping(value = "/doChangePassword")
    @ResponseBody
    public ReturnModel doChangePassword(User user, String passwordOld, HttpServletRequest request, Map<String, Object> model) {
        ReturnModel result = new ReturnModel();
        User sessionUser = (User) request.getSession().getAttribute("userinfo");
        user.setId(sessionUser.getId());

        if (MD5.encode(passwordOld).equals(sessionUser.getPassword())) {
            if (PasswordUtils.isWeakPassword(user.getPassword())) {
                result.setErrorMsg("新密码为弱口令,请重新修改！");
                return result;
            }
            // 对新密码加密
            user.setPassword(MD5.encode(user.getPassword()));
            userService.updateUser(user);
            sessionUser.setPassword(user.getPassword());
            request.getSession().setAttribute("userinfo", sessionUser);
        } else {
            result.setErrorMsg("原密码错误，修改失败！");
        }
        return result;
    }

}
