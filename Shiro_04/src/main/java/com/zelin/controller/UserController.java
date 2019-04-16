package com.zelin.controller;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
import com.zelin.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/12 10:53
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService userService;

    /**
     * 根据登录用户动态显示菜单
     * @param model
     * @return
     */
    @RequestMapping("/listmenu")
    public String listmenu(Model model){
        try {
            //1.得到主体对象
            Subject subject = SecurityUtils.getSubject();
            //2.得到身份对象（当前登录的用户）
            SysUser user = (SysUser) subject.getPrincipal();
            //2.从此用户身上得到其有的菜单
            List<SysPermission> menus = user.getMenus();
            //3.将上面的用户菜单放到model中
            model.addAttribute("menus",menus);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/listmenu";
    }

    /**
     * 查询所有的用户
     * @return
     */
    @RequiresPermissions("user:userlistxxx")
    @RequestMapping("/userlist")
    public String  findAll(Model model){
        try {
             model.addAttribute("users",userService.findUsers());
        } catch (Exception e) {
            System.out.println("对不起，你无权访问此页面!");
        }
        return "user/list";
    }
}
