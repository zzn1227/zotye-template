package com.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-2 下午4:46:22
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public String[]      allowUrls;
    public static String loginUrl = "/login";
    public static String homeUrl  = "/";

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

        if (null != allowUrls && allowUrls.length >= 1) {
            for (String url : allowUrls) {
                if (requestUrl.startsWith(url)) {
                    return true;
                }
            }
        }
        // 3、如果用户已经登录 放行
        if (request.getSession().getAttribute("userinfo") != null) {
            if (requestUrl.startsWith(loginUrl)) {
                // 登陆后再次访问登陆页面时重定向到首页
                response.sendRedirect(request.getContextPath() + homeUrl);
                return false;
            }
            return true;
        }

        if (requestUrl.startsWith(loginUrl)) {
            // 非登陆状态下允许访问首页
            return true;
        }

        // 4、非法请求 即这些请求需要登录后才能访问
        // 记录下重定向到登陆页面前的地址及请求参数串，只有请求参数中包含 callback 的才不需要记录
        if (request.getQueryString() == null || !request.getQueryString().contains("callback")) {
            request.getSession().setAttribute("returnUrl", request.getRequestURL());
            request.getSession().setAttribute("queryString", request.getQueryString());
        }
        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() + loginUrl);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
