/**
 * 
 */
package com.zelin.service;

import java.util.List;

import com.zelin.pojo.SysUserRole;
import com.zelin.pojo.SysUserRoleVo;

/********************************
 * 公司:  深圳市泽林信息公司			<br>
 * 作者:  王峰						<br>
 * 类名:  UserRoleService			<br>
 * 日期:  2018年9月13日 下午3:22:13			<br>
 * 功能:  						
 ********************************/
public interface UserRoleService {

	/**
	 * 根据userCode查询对应的角色信息（中间表Sys_user_role的信息）
	 */
	List<SysUserRole> findUserRole(String sysUserId);
	/**
	 * 为指定用户重新分配角色
	 */
	void update(SysUserRoleVo sysUserRoleVo);

}
