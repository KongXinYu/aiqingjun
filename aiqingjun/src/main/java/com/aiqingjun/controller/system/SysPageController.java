package com.aiqingjun.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统模块页面
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@Controller
@RequestMapping("${base.path}")
public class SysPageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
