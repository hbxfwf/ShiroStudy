/**
 * 
 */
package com.zelin.pojo;

import java.util.ArrayList;
import java.util.List;

/********************************
 * 公司:  深圳市泽林信息公司			<br>
 * 作者:  王峰						<br>
 * 类名:  SysUserRoleVo			<br>
 * 日期:  2018年9月13日 下午3:42:42			<br>
 * 功能:  						
 ********************************/
public class SysUserRoleVo extends SysUserRole {

	private List<String> sysRoleIds = new ArrayList<>();

	public List<String> getSysRoleIds() {
		return sysRoleIds;
	}

	public void setSysRoleIds(List<String> sysRoleIds) {
		this.sysRoleIds = sysRoleIds;
	}

	
	
	
}
