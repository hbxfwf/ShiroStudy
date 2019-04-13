package com.zelin.web.controller;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
import com.zelin.service.StudentService;
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
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 根据登录用户动态显示菜单
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/listmenu")
    public String listmenu(Model model, HttpSession session){
        try {
            //1.从session中得到当前登录的用户对象
            SysUser user = (SysUser) session.getAttribute("user");
            //2.从此用户身上得到其有的菜单
            List<SysPermission> menus = user.getMenus();
            //3.将上面的用户菜单放到model中
            model.addAttribute("menus",menus);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/listmenu";
    }
}
