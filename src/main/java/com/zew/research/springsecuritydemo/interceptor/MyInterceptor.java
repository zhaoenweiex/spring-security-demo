package com.zew.research.springsecuritydemo.interceptor;

import com.zew.research.springsecuritydemo.util.FakeRedis;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String obj = (String) request.getSession().getAttribute("cur_user");
        if (obj == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        String remoteAddress = request.getRemoteAddr();
        String lastAddress = FakeRedis.getInstance().getKey(obj + "_current_ip");
        if (lastAddress.equalsIgnoreCase(remoteAddress)) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}