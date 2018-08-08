package com.cslg.gfjkpt.common;

import com.cslg.gfjkpt.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 高并发下可以处理各自的数据, 优化
 */
public class RequestHolder {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(User user) {
        userHolder.set(user);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static User getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
