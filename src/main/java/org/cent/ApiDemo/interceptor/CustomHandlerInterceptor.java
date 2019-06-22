package org.cent.ApiDemo.interceptor;

import org.cent.ApiDemo.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定制拦截器
 * @author Vincent
 * @version 1.0 2019/6/23
 */
public class CustomHandlerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("拦截器请求前检查处理：" + request.getRequestURI());
        if ("/error/interceptor".equals(request.getRequestURI())) {
            LOGGER.info("拦截请求：" + request.getRequestURI());
            throw new CommonException("ERR0004", "拦截无效请求");
            // 返回false则中断请求
//            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("拦截器请求后处理");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("拦截器请求完成回调处理");
    }
}
