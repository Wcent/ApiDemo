package org.cent.ApiDemo.filter;

import org.cent.ApiDemo.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 定制过滤器
 * @author Vincent
 * @version 1.0 2019/6/22
 */
@WebFilter(filterName = "customFilter", urlPatterns = "/api/*")
public class CustomFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("启用api过滤器，非application/json请求报文或报文编码非UTF-8统一报错处理");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("请求过滤处理");
        LOGGER.info("请求类型：" + request.getContentType());
        LOGGER.info("报文编码：" + request.getCharacterEncoding());
        if (!"application/json".equals(request.getContentType())) {
            CommonException commonException = new CommonException("ERR0001", "报文类型无效，仅支持application/json");
            request.setAttribute("CommonException", commonException);
            request.getRequestDispatcher("/error/filter").forward(request, response);
            return;
        }

        if (!"UTF-8".equals(request.getCharacterEncoding())) {
            CommonException commonException = new CommonException("ERR0002", "报文编码无效，仅支持UTF-8");
            request.setAttribute("CommonException", commonException);
            request.getRequestDispatcher("/error/filter").forward(request, response);
            return;
        }

        // 正常继续请求处理
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("关闭过滤器");
    }
}
