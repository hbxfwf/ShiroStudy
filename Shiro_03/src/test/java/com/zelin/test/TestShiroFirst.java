package com.zelin.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/15 10:38
 */
public class TestShiroFirst {
    @Test
    public void test01(){
        //1.根据ini文件得到SecurityManagerFactory对象
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiroFirst.ini");
        //2.根据上面的工厂对象得到SecurityManager对象
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //3.将当前的SecurityManager放到当前工作的上下文环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4.得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //5.构造用户的令牌对象（用户输入的信息）
        //5.1）如果用户名输入错误，出现:UnknownAccountException异常
        //5.2) 如果密码不正确，出现：IncorrectCredentialsException异常
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","11111");
        //6.通过suject进行认证
        subject.login(token);
        //7.判断认证是否通过
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + authenticated);
    }
}
