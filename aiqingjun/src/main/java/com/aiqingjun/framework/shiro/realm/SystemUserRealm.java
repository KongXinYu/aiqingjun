package com.aiqingjun.framework.shiro.realm;

import com.aiqingjun.common.constants.Constants;
import com.aiqingjun.common.utils.security.ShiroUtils;
import com.aiqingjun.model.system.SysUser;
import com.aiqingjun.service.system.ISysMenuService;
import com.aiqingjun.service.system.ISysRoleService;
import com.aiqingjun.service.system.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义realm类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
public class SystemUserRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        SysUser user = sysUserService.getSysUserByLoginName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    getName()
                    );
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = ShiroUtils.getSysUser();
        Set<String> roles = null;
        Set<String> perms = null;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 将用户拥有的角色和权限添加到AuthorizationInfo认证对象中
        if (!user.isSuperAdmin(user.getUserId()) ) {
            info.addRole(Constants.SUPEE_ADMIN);
            info.addStringPermission("*:*:*");
        } else {
            roles = sysRoleService.selectRoleKeysByUserId(user.getUserId());
            perms = sysMenuService.selectPermsByUserId(user.getUserId());
            info.addRoles(roles);
            info.addStringPermissions(perms);
        }
        return info;
    }

    /**
     * 对注解RequiresPermissions 的校验
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        return isSuperAdmin(principals) || super.isPermitted(principals, permission);
    }

    /**
     * 对注解RequiresRoles的校验
     * @param principals
     * @param roleIdentifier
     * @return
     */
    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        return isSuperAdmin(principals) || super.hasRole(principals, roleIdentifier);
    }

    /**
     * 是否为超级管理员
     * @param principals
     * @return
     */
    private boolean isSuperAdmin(PrincipalCollection principals) {
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        if (user != null && user.isSuperAdmin()) {
            return true;
        }
        return false;
    }
}
