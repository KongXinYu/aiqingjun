package com.aiqingjun.mapper.system;

import com.aiqingjun.model.system.SysUser;
import org.springframework.stereotype.Repository;

public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser getSysUserByLoginName(String loginName);
}