/**
 * 
 */
package com.zelin.service;

import com.zelin.pojo.SysRole;

import java.util.List;

/********************************
 * 公司:  深圳市泽林信息公司			<br>
 * 作者:  王峰						<br>
 * 类名:  RoleService			<br>
 * 日期:  2018年9月13日 下午2:59:30			<br>
 * 功能:  						
 ********************************/
public interface RoleService {

	/**
	 * 查询所有的角色
	 */
	List<SysRole> findRoles() throws Exception;

}
