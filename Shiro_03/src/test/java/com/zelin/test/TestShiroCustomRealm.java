package com.zelin.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description: 自定义Realm认证测试
 * @Date: Create in 2019/4/15 11:07
 */
public class TestShiroCustomRealm {

    //加盐测试
    @Test
    public void testAddSalt(){
        String salt = "rbtwy";				//加的盐值
        String oldPassword = "111111";		//原始密码
        int hashIterations = 1;				//代表加密次数
        //加密算法一：
        Md5Hash md5Hash = new Md5Hash(oldPassword,salt,hashIterations);
        System.out.println(md5Hash);
        //加密算法二：
        SimpleHash simpleHash = new SimpleHash("md5",oldPassword,salt,hashIterations);
        System.out.println(simpleHash.toString());
    }

    @Test
    public void test01(){
        //1.根据ini文件得到SecurityManagerFactory对象
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiroCustom.ini");
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

    }
}
