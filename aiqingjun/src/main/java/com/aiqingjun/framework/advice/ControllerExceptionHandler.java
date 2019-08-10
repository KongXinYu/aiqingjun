package com.aiqingjun.framework.advice;

import com.aiqingjun.common.enums.WebExceptionEnum;
import com.aiqingjun.framework.exceptions.WebBaseException;
import com.aiqingjun.framework.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * controller层异常处理类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 异常处理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("exception url : {}", request.getRequestURI());
        log.error(e.getMessage(), e);
        WebBaseException baseException = null;
        if (e instanceof WebBaseException) {
            baseException = (WebBaseException) e;
        } else {
            baseException = new WebBaseException(WebExceptionEnum.UNKNOWN_EXCEPTION);
        }

        return new Result(baseException.getCode(), baseException.getMsg(), null);
    }

}
