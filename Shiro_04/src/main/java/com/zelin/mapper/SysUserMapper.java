package com.zelin.mapper;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface SysUserMapper extends Mapper <SysUser>{
    List<SysPermission>  getMenusByUsercode(String usercode);
    List<SysPermission> getPermissionsByUsercode(String usercode);
}