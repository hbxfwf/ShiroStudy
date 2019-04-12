/**
 * 
 */
package com.zelin.service.impl;

import java.util.List;

import com.zelin.pojo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zelin.mapper.SysRoleMapper;
import com.zelin.service.RoleService;

/********************************
 * 公司:  深圳市泽林信息公司			<br>
 * 作者:  王峰						<br>
 * 类名:  RoleServiceImpl			<br>
 * 日期:  2018年9月13日 下午3:01:36			<br>
 * 功能:  						
 ********************************/
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private SysRoleMapper roelMapper;
	@Override
	public List<SysRole> findRoles() throws Exception {
		return roelMapper.selectAll();
	}

}
