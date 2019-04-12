package com.zelin.web.controller;

import com.zelin.pojo.SysUser;
import com.zelin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/12 10:30
 */
@Controller
public class LoginController  {
    @Autowired
    private SysUserService userService;
    /**
     * 登录业务处理
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try {
            //1、根据用户名及密码查询用户（经过md5加密）
            SysUser user = userService.findUserByUsercodeAndPassword(username, password);
            //2.将上面得到的用户放到session中
            if(user != null){
                request.getSession().setAttribute("user",user);
                request.getRequestDispatcher(request.getContextPath() + "/user/listmenu.do").forward(request,response);
            }else{
                request.setAttribute("message","对不起，用户名或密码输入有误！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
