package com.aiqingjun.framework.exceptions;

import com.aiqingjun.common.enums.WebExceptionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Web异常基类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@Getter
@Setter
public class WebBaseException extends RuntimeException {

    protected int code;
    protected String msg;

    public WebBaseException(WebExceptionEnum wee) {
        this.code = wee.getCode();
        this.msg = wee.getMsg();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
