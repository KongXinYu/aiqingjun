package com.aiqingjun.controller.system;

import com.aiqingjun.common.constants.Constants;
import com.aiqingjun.common.enums.WebExceptionEnum;
import com.aiqingjun.framework.exceptions.WebBaseException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录页
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@RestController("${base.path}")
public class LoginController {

    @PostMapping("/login")
    public Object login(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return Constants.SUCCESS_MSG;
        } catch (AuthenticationException e) {
            throw new WebBaseException(WebExceptionEnum.USER_OR_PASSWORD_ERROR);
        }
    }
}
