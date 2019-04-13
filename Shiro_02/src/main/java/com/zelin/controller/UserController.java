package com.zelin.controller;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
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
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 根据登录用户动态显示菜单
     * @param session
     * @return
     */
    @RequestMapping("/listmenu")
    public List<SysPermission>  listmenu( HttpSession session){
        try {
            //1.从session中得到当前登录的用户对象
            SysUser user = (SysUser) session.getAttribute("user");
            System.out.println("listmenu--->" + user);
            //2.从此用户身上得到其有的菜单
            if(user != null){
             List<SysPermission> menus = user.getMenus();
             return menus;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   // @RequestMapping("/touserlist")

}
