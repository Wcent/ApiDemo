package org.cent.ApiDemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.cent.ApiDemo.entity.CommonRequest;
import org.cent.ApiDemo.entity.CommonResponse;
import org.cent.ApiDemo.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/json")
    public CommonResponse testJson() {
        return new CommonResponse();
    }

    @GetMapping(value = "/pathVar/{id}")
    public CommonResponse getPathVar(@PathVariable int id) {
        try {
            int a = 1/id;
        } catch (Exception e) {
            throw new CommonException("999", "除0", e);
        }
        if (id ==1) {
            throw new CommonException("888", "不要传1");
        }
        CommonResponse commonResponse = new CommonResponse();
        JSONObject body = new JSONObject();
        body.put("id", id);
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setCode("SUC0000");
        commonResponse.setMessage("请求成功");
        commonResponse.setBody(body);
        return commonResponse;
    }

    @PostMapping(value = "/postJson")
    public CommonResponse postJson(@RequestBody CommonRequest commonRequest) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setCode("SUC0000");
        commonResponse.setMessage("请求成功");
        commonResponse.setBody(JSON.parseObject(JSON.toJSONString(commonRequest), Map.class));
        return commonResponse;
    }
}
