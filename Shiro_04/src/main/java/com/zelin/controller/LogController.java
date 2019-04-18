package com.zelin.controller;

import com.zelin.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/16 09:39
 */
@Controller
public class LogController {
    @RequestMapping("/login")
    //默认情况下，shiro的用户认证工作由FormAuthenticationFilter(authc)过滤器完成，
    //当出现异常时，shiro会将异常对象的类名放到以shiroLoginFailure为key的request
    //对象中，我们只需要通过此key取出对应的异常类名就可以知道，用户是何种异常
    public String login(HttpServletRequest request) throws Exception {
        //1.得到异常的名称
        String exceptionName = (String) request.getAttribute("shiroLoginFailure");
        //2.根据异常的名称来判断执行的是何种异常，从而处理此异常
        //2.1)判断是否为null
        if(StringUtils.isNotEmpty(exceptionName)){
            if(UnknownAccountException.class.getName().equals(exceptionName)){
                throw new MyException("账户异常!");
            }else if(IncorrectCredentialsException.class.getName().equals(exceptionName)){
                throw new MyException("用户名或密码输入有误!");
            }else if(UnauthorizedException.class.getName().equals(exceptionName)){
                throw new MyException("无权限访问异常!");
            }else if("validCodeError".equals(exceptionName)){
                throw new MyException("验证码错误！");
            }else{
                throw new Exception();
            }
        }
        //此方法只有在FormAuthenticationFilter认证失败时工作，认证成功返回页面就是上一次的页面
        return "login";
    }
}
