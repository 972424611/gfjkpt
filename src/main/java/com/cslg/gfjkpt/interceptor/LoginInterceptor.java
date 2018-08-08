package com.cslg.gfjkpt.interceptor;

import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.utils.PropertyUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 检测用户是否登陆
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) CookieSessionManage.getSession(httpServletRequest);
        if(user != null) {
            RequestHolder.add(user);
            RequestHolder.add(httpServletRequest);
            return true;
        }
        //重定向到登陆页面
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
