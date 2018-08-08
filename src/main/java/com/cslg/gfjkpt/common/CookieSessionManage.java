package com.cslg.gfjkpt.common;

import com.cslg.gfjkpt.utils.PropertyUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author aekc
 */
public class CookieSessionManage {

    private static String SESSION_KEY;

    private static String COOKIE_NAME;

    private static Integer COOKIE_SAVE_TIME;

    static {
        SESSION_KEY = PropertyUtil.getProperty("SESSION_KEY");
        COOKIE_NAME = PropertyUtil.getProperty("COOKIE_NAME");
        COOKIE_SAVE_TIME = Integer.valueOf(PropertyUtil.getProperty("COOKIE_SAVE_TIME"));
    }

    public static void setCookie(HttpServletResponse response, String str) {
        Cookie cookie = new Cookie(COOKIE_NAME, str);
        cookie.setMaxAge(COOKIE_SAVE_TIME);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie c : cookies) {
                if(COOKIE_NAME.equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    public static void clearCookieAndSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            Object object = session.getAttribute(SESSION_KEY);
            if(object != null) {
                session.invalidate();
            }
        }
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        //设置为0为立即删除该Cookie
        cookie.setMaxAge(0);
        //删除指定路径的cookie,不设置该路径，默认为删除当前路径Cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void setSession(HttpServletRequest request, Object object) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, object);
    }

    public static Object getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return null;
        }
        return session.getAttribute(SESSION_KEY);
    }
}

