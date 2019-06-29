package org.cent.ApiDemo.controller;

import org.cent.ApiDemo.model.CommonRequest;
import org.cent.ApiDemo.model.CommonResponse;
import org.cent.ApiDemo.model.POJO.TestVo;
import org.cent.ApiDemo.util.BeanValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

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
        commonResponse.putItem("postBody", commonRequest.getBody());
        return commonResponse;
    }

    @GetMapping(value = "/var/{id}")
    public CommonResponse getVar(@PathVariable int id) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setMessage("请求成功");
        commonResponse.putItem("id", id);
        return commonResponse;
    }

    @GetMapping(value = "/param")
    public CommonResponse getParam(@RequestParam String name, @RequestParam int age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setMessage("请求成功");
        commonResponse.putItem("param", map);
        return commonResponse;
    }

    @PostMapping(value = "/io")
    public CommonResponse ioValidate(@RequestBody @Validated CommonRequest commonRequest, BindingResult bindingResult) throws BindException {

        // 请求报文校验是否异常
        if (bindingResult.hasErrors()) {
            LOGGER.error("请求报文无效");
            throw new BindException(bindingResult);
        }

        TestVo testVo = commonRequest.getItem("testVo", TestVo.class);
        // 校验实例字段合法性
        bindingResult = BeanValidatorUtil.validate("testVo", testVo);
        if (bindingResult.hasErrors()) {
            LOGGER.error("接口校验无效");
            throw new BindException(bindingResult);
        }

        List<TestVo> testVos = commonRequest.getItems("testVos", TestVo.class);
        bindingResult = BeanValidatorUtil.validate("testVos", testVos);
        if (bindingResult.hasErrors()) {
            LOGGER.error("接口校验无效");
            throw new BindException(bindingResult);
        }

        LOGGER.info(testVo.toString());
        LOGGER.info(testVos.toString());

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setMessage("测试成功");
        commonResponse.putItem("single", testVo);
        commonResponse.putItem("multi", testVos);
        return commonResponse;
    }
}
