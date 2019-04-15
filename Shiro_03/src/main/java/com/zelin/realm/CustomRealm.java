package com.zelin.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.Test;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/15 10:54
 */
public class CustomRealm extends AuthorizingRealm {
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


    //授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    //认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.得到身份信息
        Object principal = token.getPrincipal();
        //2.如果是null就返回null,其实就是此值返回给自定义realm的老大ModularRealmAuthticator这个对象
        //此时ModularRealmAuthticator这个账户就会抛出：unknownAccoutException
        if(principal == null){
            return null;
        }
        //3.先模拟从数据库中取得密码
        String password = "111111";
        //4.返回AuthenticationInfo对象
        return new SimpleAuthenticationInfo(principal,password,"aaaa");
    }
}
