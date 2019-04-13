package com.zelin.controller;

import com.zelin.pojo.SysUser;
import com.zelin.service.SysUserService;
import com.zelin.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/13 10:31
 */
@RestController
public class LoginController {
    @Autowired
    private SysUserService userService;
    @RequestMapping("/login")
    public AjaxResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try {
            //1.根据用户名在数据库中查询是否存在此用户
            SysUser user = userService.findUserByUsercodeAndPassword(username, password);
            //2.判断用户是否存在
            //2.1)如果此用户存在，就将其放到session中
            if(user != null){
                request.getSession().setAttribute("user",user);
                return new AjaxResult("登录成功",true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult("登录失败",false);
    }
}
