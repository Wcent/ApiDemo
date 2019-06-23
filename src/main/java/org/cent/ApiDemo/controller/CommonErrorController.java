package org.cent.ApiDemo.controller;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.cent.ApiDemo.entity.CommonResponse;
import org.cent.ApiDemo.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局统一404异常处理
 * @author Vincent
 * @version 1.0 2019/6/22
 */
@Controller
public class CommonErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonErrorController.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public CommonResponse error(HttpServletRequest request, HttpServletResponse response) {
        CommonResponse commonResponse = new CommonResponse();
        String status = String.valueOf(response.getStatus());
        if ("404".equals(status)) {
            // 获取监听器设置的原始请求uri
            String uri = (String) request.getAttribute("RequestUri");
            commonResponse.setStatus(CommonResponse.ERROR);
            commonResponse.setCode(status);
            commonResponse.setMessage(String.format("404 - 请求路径%s不存在", uri));
        } else {
            commonResponse.setStatus(CommonResponse.UNKNOWN);
            commonResponse.setCode(status);
            commonResponse.setMessage(String.format("%s - 未知异常", status));
        }
        return commonResponse;
    }

    /**
     * 过滤器异常跳转统一处理
     * @param request
     * @return
     */
    @RequestMapping(value = "/error/filter")
    @ResponseBody
    public CommonResponse filter(HttpServletRequest request) {
        // 获取过滤器设置异常信息
        CommonException commonException = (CommonException) request.getAttribute("CommonException");
        CommonResponse commonResponse = new CommonResponse();
        if (commonException != null) {
            commonResponse.setStatus((CommonResponse.ERROR));
            commonResponse.setCode(commonException.getCode());
            commonResponse.setMessage(commonException.getMessage());
        } else {
            commonResponse.setStatus((CommonResponse.UNKNOWN));
            commonResponse.setCode("ERR0003");
            commonResponse.setMessage("未知异常");
        }
        return commonResponse;
    }

    /**
     * 测试本uri被拦截器拦截处理
     * @return
     */
    @RequestMapping(value = "/error/interceptor")
    @ResponseBody
    public CommonResponse interceptor() {
        return new CommonResponse();
    }
}
