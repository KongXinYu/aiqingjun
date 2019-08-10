package com.aiqingjun.service.system;

import com.aiqingjun.model.system.SysUser;

public interface ISysUserService {

    SysUser getSysUserByLoginName(String loginName);
}
