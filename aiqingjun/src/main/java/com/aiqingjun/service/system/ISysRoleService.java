package com.aiqingjun.service.system;

import com.aiqingjun.model.system.SysRole;

import java.util.List;
import java.util.Set;

public interface ISysRoleService {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return role_key集合
     */
    List<SysRole> selectRolesByUserId(String userId);

    /**
     * 根据用户ID查询role_key集合
     *
     * @param userId 用户ID
     * @return role_key集合
     */
    Set<String> selectRoleKeysByUserId(String userId);
}
