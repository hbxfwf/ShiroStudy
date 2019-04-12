/**
 * 
 */
package com.zelin.service.impl;

import java.util.List;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zelin.mapper.SysUserMapper;
import com.zelin.service.SysUserService;
import com.zelin.utils.MD5;

/**
 * 作者: 王峰
 * 文件: SysUserServiceImpl.java
 * 类名: SysUserServiceImpl
 * 时间: 2018年6月22日 下午2:37:40
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserMapper userMapper;
	@Override
	public SysUser findUserByUsercodeAndPassword(String usercode, String password)  throws Exception{
		//1.根据用户名查询到用户对象
		SysUser user = new SysUser();
		user.setUsercode(usercode);
		user.setPassword(password);
		System.out.println("usercode:" + usercode);
		System.out.println("password:" + password);
		SysUser sysUser = userMapper.selectByPrimaryKey(usercode);
		System.out.println("user:" + sysUser);
		if(sysUser != null ){
			System.out.println(sysUser);
			//1.1）得到当前用户下的所有菜单 集合
			List<SysPermission> menus = userMapper.getMenusByUsercode(usercode);
			//1.2）得到当前用户下的所有权限 集合
			List<SysPermission> permissions = userMapper.getPermissionsByUsercode(usercode);
			//1.3) 为当前用户指定上面两个集合
			sysUser.setMenus(menus);
			sysUser.setPermissions(permissions);
			//2.判断用户是否为空，如果不为空，再判断密码
			//将用户输入的密码经过md5加密后，再与数据库中的密码进行比对
			MD5 md5 = new MD5();
			password = md5.getMD5ofStr(password); //对用户输入的密码进行md5加密
			System.out.println("password:" + password);
			if(sysUser.getPassword().equals(password)){
				return sysUser;
			}
		}
		return null;
	}
	@Override
	public List<SysUser> findUsers() throws Exception {
		return userMapper.selectByExample(null);
	}

}
