package org.cent.ApiDemo.exception;

import org.cent.ApiDemo.constant.ExceptionEnum;
import org.cent.ApiDemo.model.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 全局统一异常处理
 * @author cent
 * @version 1.0 2019年6月22日
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        LOGGER.error("未知异常", e);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.UNKNOWN);
        commonResponse.setMessage(String.format(ExceptionEnum.UNK0000.toString(), e.getMessage()));
        return commonResponse;
    }

    @ExceptionHandler(BindException.class)
    public CommonResponse handleBindException(BindException e) {
        LOGGER.error("请求报文绑定异常", e);

        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuffer sb = new StringBuffer();
        for (FieldError fieldError : fieldErrors) {
            sb.append("[").append(fieldError.getField()).append("=").append(fieldError.getRejectedValue()).append("]，")
                    .append(fieldError.getDefaultMessage()).append(" && ");
        }
        sb.delete(sb.length()-4, sb.length());
        
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setMessage(String.format(ExceptionEnum.REQ0005.toString(), sb.toString()));
        return commonResponse;
    }

    @ExceptionHandler(CommonException.class)
    public CommonResponse handleCommonException(CommonException e) {
        LOGGER.error("服务异常", e);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setMessage(e.getMessage());
        return commonResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResponse handleMsgNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error("请求报文缺失", e);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setMessage(ExceptionEnum.REQ0006.toString());
        return commonResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        LOGGER.error("请求参数类型不匹配", e);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setMessage(String.format(ExceptionEnum.REQ0001.toString(), e.getMessage()));
        return commonResponse;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResponse handleMissingParmException(MissingServletRequestParameterException e) {
        LOGGER.error("请求参数缺失", e);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setMessage(String.format(ExceptionEnum.REQ0002.toString(), e.getMessage()));
        return commonResponse;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResponse handleMethodNotSupportedExcepton(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("请求方法不支持", e);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(CommonResponse.ERROR);
        commonResponse.setMessage(String.format(ExceptionEnum.OTH0000.toString(), e.getMessage()));
        return commonResponse;
    }
}
