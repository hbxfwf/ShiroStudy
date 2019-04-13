package com.zelin.controller;

import com.zelin.pojo.SysRole;
import com.zelin.pojo.SysUser;
import com.zelin.pojo.SysUserRole;
import com.zelin.pojo.SysUserRoleVo;
import com.zelin.service.RoleService;
import com.zelin.service.UserRoleService;
import com.zelin.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/13 14:58
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 查询所有的角色
     * @return
     */
    @RequestMapping("/list")
    public List<SysRole> findAll(){
        try {
            return roleService.findRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 得到用户对象
     * @param session
     * @return
     */
    @RequestMapping("/getName")
    public SysUser getName(HttpSession session){
        Object object = session.getAttribute("user");
        if(object != null){
            SysUser user = (SysUser) object;
            return user;
        }
        return null;
    }

    /**
     * 根据当前登录用户查询出其关联的角色
     * @param session
     * @return
     */
    @RequestMapping("/findRolesByUser")
    public List<SysUserRole> findRolesByUser(HttpSession session){
        Object object = session.getAttribute("user");
        if(object != null){
            SysUser user = (SysUser) object;
            List<SysUserRole> userRoles = userRoleService.findUserRole(user.getUsercode());
            return userRoles;
        }
        return null;

    }

    /**
     * 修改权限
     * @param sysUserRoleVo
     * @param session
     * @return
     */
    @RequestMapping("/changeRole")
    public AjaxResult changeRole(SysUserRoleVo sysUserRoleVo,HttpSession session){
        try {
            SysUser user = (SysUser) session.getAttribute("user");
            if(user != null){
                sysUserRoleVo.setSysUserId(user.getId());
            }
            userRoleService.update(sysUserRoleVo);
            return new AjaxResult("修改权限成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("修改权限失败",false);
        }
    }
}
