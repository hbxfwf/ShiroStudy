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
import java.util.ArrayList;
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
     * 根据当前选择用户查询出其关联的角色(得到sysRoleIds这个数组是在前台进行)
     * @return
     */
//    @RequestMapping("/findRolesByUser")
//    public List<SysUserRole> findRolesByUser(String sysUserId){
//       List<SysUserRole> userRoles = userRoleService.findUserRole(sysUserId);
//       return userRoles;
//    }


    /**
     * 根据当前选择用户查询出其关联的角色(得到sysRoleIds这个数组在后台这里进行),
     * 注意：这里得到的是某个用户的roleId集合
     * @return
     */
    @RequestMapping("/findRolesByUser")
    public List<String> findRolesByUser(String sysUserId){
        List<String>  sysRoleIds = new ArrayList<>();
        try {
            //1.根据当前选择的用户得到对应的用户角色集合
            List<SysUserRole> userRoles = userRoleService.findUserRole(sysUserId);
            //2.查询所有的角色集合，同时遍历userRoles，如果二者的roleId相等，就放此roleid到sysRoleIds，否则，放0
            for (int i=0;i< roleService.findRoles().size();i++) {
                SysRole role = roleService.findRoles().get(i);
                sysRoleIds.add("0");
                for (int j = 0;j < userRoles.size();j++) {
                    SysUserRole userRole = userRoles.get(j);
                    if(userRole.getSysRoleId().equals(role.getId())){
                        sysRoleIds.set(i,role.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysRoleIds;
    }
    /**
     * 修改角色
     * @param sysUserRoleVo
     * @return
     */
    @RequestMapping("/changeRole")
    public AjaxResult changeRole(SysUserRoleVo sysUserRoleVo){
        try {
            userRoleService.update(sysUserRoleVo);
            return new AjaxResult("修改权限成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("修改权限失败",false);
        }
    }
}
