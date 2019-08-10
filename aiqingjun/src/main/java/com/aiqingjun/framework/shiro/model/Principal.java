package com.aiqingjun.framework.shiro.model;

import com.aiqingjun.model.system.SysUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 授权用户信息
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Principal implements Serializable {

    private String userId; // 编号

    private String loginName; // 登录名

    private String userName; // 姓名

    public Principal(SysUser user) {
        this.userId = user.getUserId();
        this.loginName = user.getLoginName();
        this.userName = user.getUserName();
    }
}
