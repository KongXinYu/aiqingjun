package com.aiqingjun.service.system;

import com.aiqingjun.model.system.SysMenu;

import java.util.List;
import java.util.Set;

public interface ISysMenuService {
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    Set<String> selectPermsByUserId(String userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuByUserId(String userId);
}
