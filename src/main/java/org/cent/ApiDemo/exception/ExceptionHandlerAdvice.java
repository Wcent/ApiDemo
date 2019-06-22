package org.cent.ApiDemo.exception;

import org.cent.ApiDemo.entity.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.UNKNOWN);
        commonResponse.setCode("ERR9999");
        commonResponse.setMessage("未知异常：" + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(CommonException.class)
    public CommonResponse handleCommonException(CommonException e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode(e.getCode());
        commonResponse.setMessage(e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public CommonResponse handleMissingPathVarException(MissingPathVariableException e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode("ERR9999");
        commonResponse.setMessage("路径变量缺少异常：" + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResponse handleMissingParmException(MissingServletRequestParameterException e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode("ERR9999");
        commonResponse.setMessage("请求参数缺失异常：" + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResponse handleArgumentMismatchException(MethodArgumentTypeMismatchException e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode("ERR9999");
        commonResponse.setMessage("请求参数不匹配异常：" + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode("ERR9999");
        commonResponse.setMessage("请求参数无效异常：" + e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public CommonResponse handleMethodNotAllowedExcepton(MethodNotAllowedException e) {
        LOGGER.info(e.toString());
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setCode("ERR9999");
        commonResponse.setMessage("请求方法不允许异常：" + e.getMessage());
        return commonResponse;
    }
}
