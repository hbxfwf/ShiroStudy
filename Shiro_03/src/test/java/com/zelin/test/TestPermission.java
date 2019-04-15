package com.zelin.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/15 11:58
 */
public class TestPermission {
    @Test
    public void test01(){
        //第一部分：进行认证操作
        //1.根据ini文件得到SecurityManagerFactory对象
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiroPermission.ini");
        //2.根据上面的managerFactory得到SecurityManager对象
        SecurityManager securityManager = managerFactory.getInstance();
        //3.将此对象设置到上下文工作环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4.得到主体对象
        Subject subject = SecurityUtils.getSubject();
        //5.构造一个令牌对象
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","111111");
        //6.进行认证
        subject.login(token);
        //7.检查认证是否通过
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过：" + authenticated);

        //第二部分：进行授权操作（下面第二部分必须在上面第一部分认证难过之后才能执行）
        //2.1)进行角色的授权操作
        //2.1.1)单个角色的授权操作
        boolean role1 = subject.hasRole("role1");
        System.out.println("是否有role1角色：" + role1);
        //2.1.2)多个角色的授权操作(没有角色，也不会抛出异常，只要有一个角色没有，就返回false)
        boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role3"));
        System.out.println("是否有role1,role3两个角色：" + hasAllRoles);

        //2.2)进行权限的授权操作
        //2.2.1)判断单个权限
        boolean permitted = subject.isPermitted("student:create");
        System.out.println("是否有单个权限：" + permitted);
        //2.2.2)判断多个权限(如果没有指定的权限，也不会抛出异常)
        boolean permittedAll = subject.isPermittedAll("student:create", "student:update", "student:query");
        System.out.println("是否有多个权限：" + permittedAll);
    }
}
