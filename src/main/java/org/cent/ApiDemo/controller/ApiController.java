package org.cent.ApiDemo.controller;

import org.cent.ApiDemo.model.CommonRequest;
import org.cent.ApiDemo.model.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(value = "/json")
    public CommonResponse getJson() {
        return new CommonResponse();
    }

    @PostMapping(value = "/json")
    public CommonResponse postJson(@RequestBody @Validated CommonRequest commonRequest, BindingResult bindingResult) throws BindException {

        // 请求报文校验是否异常
        if (bindingResult.hasErrors()) {
            LOGGER.error("请求报文无效");
            throw new BindException(bindingResult);
        }

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setMessage("请求成功");
        commonResponse.putItem("request", commonRequest);
        return commonResponse;
    }
}
