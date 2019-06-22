package org.cent.ApiDemo.controller;

import com.alibaba.fastjson.JSON;
import org.cent.ApiDemo.entity.CommonRequest;
import org.cent.ApiDemo.entity.CommonResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/json")
    public CommonResponse getJson() {
        return new CommonResponse();
    }

    @PostMapping(value = "/json")
    public CommonResponse postJson(@RequestBody CommonRequest commonRequest) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.SUCCESS);
        commonResponse.setCode("SUC0000");
        commonResponse.setMessage("请求成功");
        commonResponse.setBody(JSON.parseObject(JSON.toJSONString(commonRequest), Map.class));
        return commonResponse;
    }
}
