package com.aiqingjun.common.utils.security;

import com.aiqingjun.common.constants.Constants;
import com.aiqingjun.common.utils.bean.BeanUtils;
import com.aiqingjun.model.system.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


/**
 * shiro 工具类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getSysUser() {
        SysUser user = null;
        Object obj = getSubject().getPrincipal();
        if (obj != null) {
            user = new SysUser();
            BeanUtils.copyBeanProp(obj, user);
        }
        return user;
    }

    public static boolean isSuperAdmin(String userId) {
        return Constants.SUPEE_ADMIN_ID.equalsIgnoreCase(userId);
    }
}
