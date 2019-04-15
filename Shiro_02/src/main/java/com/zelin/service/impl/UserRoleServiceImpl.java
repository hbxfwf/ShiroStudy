package com.zelin.service.impl;

import com.zelin.mapper.SysUserRoleMapper;
import com.zelin.pojo.SysUserRole;
import com.zelin.pojo.SysUserRoleExample;
import com.zelin.pojo.SysUserRoleVo;
import com.zelin.service.UserRoleService;
import com.zelin.utils.CommUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/********************************
 * 公司:  深圳市泽林信息公司			<br>
 * 作者:  王峰						<br>
 * 类名:  UserRoleServiceImpl			<br>
 * 日期:  2018年9月13日 下午3:23:53			<br>
 * 功能:  						
 ********************************/
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Override
	public List<SysUserRole> findUserRole(String sysUserId) {
		//1.生成查询实例
		SysUserRoleExample example = new SysUserRoleExample();
		//1.1)定义查询条件实列
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		//1.2)添加查询条件
		criteria.andSysUserIdEqualTo(sysUserId);
		//2.查询用户角色信息并返回
		return userRoleMapper.selectByExample(example);
	}
	//修改角色
	@Override
	public void update(SysUserRoleVo sysUserRoleVo) {
		//1.根据sys_user_id删除sys_user_role这张表中的记录
		//1.1)构建查询实例
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		//1.2)在查询实例下添加条件
		criteria.andSysUserIdEqualTo(sysUserRoleVo.getSysUserId());
		//1.3)根据条件删除用户
		userRoleMapper.deleteByExample(example);
		
		//2.在sys_user_id表中添加记录
		List<String> sysRoleIds = sysUserRoleVo.getSysRoleIds();
		for (String roleId : sysRoleIds) {
			if(!roleId.equals("0")){
				SysUserRole userRole = new SysUserRole();
				userRole.setId(CommUtils.createUUID());
				userRole.setSysRoleId(roleId);
				userRole.setSysUserId(sysUserRoleVo.getSysUserId());
				userRoleMapper.insert(userRole );
			}
		}
	}

}
