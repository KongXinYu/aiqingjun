package com.aiqingjun.service.system.impl;

import com.aiqingjun.mapper.system.SysUserMapper;
import com.aiqingjun.model.system.SysUser;
import com.aiqingjun.service.system.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getSysUserByLoginName(String loginName) {
        return sysUserMapper.getSysUserByLoginName(loginName);
    }
}
