package com.aiqingjun.service.system.impl;

import com.aiqingjun.mapper.system.SysMenuMapper;
import com.aiqingjun.model.system.SysMenu;
import com.aiqingjun.service.system.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    public Set<String> selectPermsByUserId(String userId) {
        List<SysMenu> menus = sysMenuMapper.selectMenusByUserId(userId);
        return menus.stream()
                .filter(menu -> StringUtils.isNotBlank(menu.getPerms()))
                .map(menu -> menu.getPerms().trim())
                .collect(Collectors.toSet());
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuByUserId(String userId) {
        return sysMenuMapper.selectMenusByUserId(userId);
    }
}
