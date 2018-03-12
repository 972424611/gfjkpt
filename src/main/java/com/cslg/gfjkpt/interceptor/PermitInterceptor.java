package com.cslg.gfjkpt.interceptor;

import com.cslg.gfjkpt.common.CookieSessionManage;
import com.cslg.gfjkpt.common.RequestHolder;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.model.User;
import com.cslg.gfjkpt.utils.JsonUtils;
import com.cslg.gfjkpt.utils.PropertyUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = RequestHolder.getCurrentUser();
        String str = CookieSessionManage.getCookie(httpServletRequest);
        if("admin".equals(user.getUsername())||"admin".equals(str)) {
            return true;
        }
        ResultJson resultJson = ResultJson.fail("需管理员才能访问");
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.getWriter().print(JsonUtils.objectToJson(resultJson));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
