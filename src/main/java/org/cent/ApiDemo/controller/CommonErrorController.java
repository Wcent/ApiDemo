package org.cent.ApiDemo.controller;

import org.cent.ApiDemo.constant.ExceptionEnum;
import org.cent.ApiDemo.exception.CommonException;
import org.cent.ApiDemo.model.CommonResponse;
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
        int status = response.getStatus();
        if (status == 404) {
            // 获取监听器设置的原始请求uri
            String uri = (String) request.getAttribute("RequestUri");
            LOGGER.error("请求路径不存在：" + uri);
            commonResponse.setStatus(CommonResponse.ERROR);
            commonResponse.setMessage(String.format(ExceptionEnum.REQ0008.toString(), uri));
        } else {
            LOGGER.error("未知异常，响应码：" + status);
            commonResponse.setStatus(CommonResponse.UNKNOWN);
            commonResponse.setMessage(String.format(ExceptionEnum.UNK0001.toString(), status));
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
            commonResponse.setMessage(commonException.getMessage());
        } else {
            commonResponse.setStatus((CommonResponse.UNKNOWN));
            commonResponse.setMessage(ExceptionEnum.UNK0002.toString());
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
