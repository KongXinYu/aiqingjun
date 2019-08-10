package com.aiqingjun.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Web异常枚举类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@AllArgsConstructor
@Getter
public enum WebExceptionEnum {

    /**----------- 未知异常 --------------*/
    UNKNOWN_EXCEPTION(000001, "未知异常"),

    /**----------- user exception ------------*/
    USER_NOT_EXISTS(1000001, "用户不存在"),
    USER_OR_PASSWORD_ERROR(1000002, "用户不存在或密码错误"),
    ;

    private int code;
    private String msg;

}
