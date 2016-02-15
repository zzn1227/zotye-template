package com.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.model.CarType;
import com.model.rights.User;
import com.persistence.CarTypeMapper;
import com.service.CarTypeService;
import com.service.rights.UserService;
import com.system.page.PageParam;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private CarTypeMapper       carTypeMapper;

    @Autowired
    private CarTypeService      carTypeService;

    @Autowired
    private UserService         userService;

    // @RequestMapping(method = RequestMethod.GET, value = "index")
    // public String index(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
    // model.put("name", "赵志能");
    // Collections.emptyMap();
    // return "index";
    // }

    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
        model.put("name", "赵志能");
        model.put("currentDate", new Date());
        model.put("message", "哈哈");
        log.info("哈啊哈");
        return "welcome";
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    @ResponseBody
    public String test(HttpServletRequest request) {
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCarTypes")
    public String getCarTypes(HttpServletRequest req, HttpServletResponse resp, Map<String, Object> model) {
        List<CarType> list = carTypeService.getCarTypes();
        model.put("carTypeList", list);
        return "carTypeList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/asyncGetCarTypes")
    @ResponseBody
    public List<CarType> asyncGetCarTypes() {
        List<CarType> list = carTypeService.getCarTypes();
        return list;
    }

    @RequestMapping(value = "/asyncGetUserByPage")
    @ResponseBody
    public List<User> asyncGetUserByPage(Map<String, Object> model) {
        User param = new User();
        PageParam page = new PageParam(1, 3, "name desc");
        Page<User> list = userService.queryByPage(param, page);
        return list;
    }

}
