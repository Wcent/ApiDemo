package org.cent.ApiDemo.filter;

import org.cent.ApiDemo.constant.ExceptionEnum;
import org.cent.ApiDemo.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 定制过滤器
 * @author Vincent
 * @version 1.0 2019/6/22
 */
@WebFilter(filterName = "customFilter", urlPatterns = "/*")
public class CustomFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("#######################启用过滤器#######################");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("#######################请求过滤处理#######################");

        String method = ((HttpServletRequest) request).getMethod();
        String contentType = request.getContentType();
        String encoding = request.getCharacterEncoding();
        LOGGER.info("请求方法：" + method);
        LOGGER.info("请求类型：" + contentType);
        LOGGER.info("报文编码：" + encoding);

        if ("POST".equalsIgnoreCase(method) && !"application/json".equals(contentType)) {
            LOGGER.error("POST请求报文非application/json请求报文报错处理");
            CommonException commonException = new CommonException(ExceptionEnum.OTH0001.toString());
            request.setAttribute("CommonException", commonException);
            request.getRequestDispatcher("/error/filter").forward(request, response);
            return;
        }

        if (!"UTF-8".equalsIgnoreCase(encoding)) {
            LOGGER.error("报文编码非UTF-8报错处理");
            CommonException commonException = new CommonException(ExceptionEnum.OTH0002.toString());
            request.setAttribute("CommonException", commonException);
            request.getRequestDispatcher("/error/filter").forward(request, response);
            return;
        }

        // 正常继续请求处理
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("#######################关闭过滤器#######################");
    }
}
