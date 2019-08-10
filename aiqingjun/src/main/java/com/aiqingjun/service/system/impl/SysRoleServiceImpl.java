package com.aiqingjun.service.system.impl;

import com.aiqingjun.mapper.system.SysRoleMapper;
import com.aiqingjun.model.system.SysRole;
import com.aiqingjun.service.system.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public List<SysRole> selectRolesByUserId(String userId) {
        return sysRoleMapper.selectRolesByUserId(userId);
    }

    public Set<String> selectRoleKeysByUserId(String userId) {
        List<SysRole> roles = sysRoleMapper.selectRolesByUserId(userId);
        return roles.stream()
                .filter(role -> StringUtils.isNotBlank(role.getRoleKey()))
                .map(role -> role.getRoleKey().trim())
                .collect(Collectors.toSet());
    }
}
