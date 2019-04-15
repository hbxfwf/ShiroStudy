package com.zelin.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/15 11:34
 */
public class Md5CustomRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.得到身份信息
        Object principal = token.getPrincipal();
        //2.判断看是否为null
        if(null == principal){
            return null;
        }
        //3.模拟从数库中取得密码及加盐值
        String password = "ec1b86316c81b3f3440c07f65a74bf79";   //加密、加盐后的密码
        String salt = "rbtwy";                                  //盐值
        //4.返回SimpleAuthenticationInfo对象
        return new SimpleAuthenticationInfo(principal,password, ByteSource.Util.bytes(salt),"aaa");
    }
}
