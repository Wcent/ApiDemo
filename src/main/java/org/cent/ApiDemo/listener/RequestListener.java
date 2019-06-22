package org.cent.ApiDemo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求监听器
 * @author Vincent
 * @version 1.0 2019/6/22
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        LOGGER.info("监听到请求结束");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
        LOGGER.info("监听到新增请求：" + request.getRequestURI());
        if (request.getRequestURI() != null && request.getRequestURI().startsWith("/test")) {
            LOGGER.info("测试请求：" + request.getRequestURI());
        }
    }
}
