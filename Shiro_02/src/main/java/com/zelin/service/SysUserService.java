/**
 * 
 */
package com.zelin.service;

import com.zelin.pojo.SysUser;

import java.util.List;

/**
 * 作者: 王峰
 * 文件: SysUserService.java
 * 类名: SysUserService
 * 时间: 2018年6月22日 下午2:35:34
 */
public interface SysUserService {

	/**
	 * 功能：根据用户名usercode及密码查询用户是否存在
	 * @param usercode
	 * @return
	 */
	SysUser findUserByUsercodeAndPassword(String usercode, String password) throws Exception;
	/**
	 * 查询出所有的用户
	 */
	List<SysUser> findUsers() throws Exception;

}
