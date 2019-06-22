package org.cent.ApiDemo.controller;

import org.cent.ApiDemo.entity.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Vincent
 * @version 1.0 2019/6/22
 */
@Controller
public class NotFoundController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundController.class);

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    @ResponseBody
    public CommonResponse error() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode("404");
        commonResponse.setMessage("404 - 请求路径不存在");
        return commonResponse;
    }
}
