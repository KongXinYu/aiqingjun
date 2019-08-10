package com.aiqingjun.controller.test;

import com.aiqingjun.common.enums.WebExceptionEnum;
import com.aiqingjun.framework.exceptions.WebBaseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Object index() {
        return "Hello World";
    }

    @RequestMapping("/unknowException")
    public Object unknowException() {
        int a = 1 / 0;
        return "Hello World";
    }

    @RequestMapping("/exception")
    public Object exception() {
        throw new WebBaseException(WebExceptionEnum.USER_NOT_EXISTS);
    }
}
