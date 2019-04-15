package com.zelin.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description: 自定义授权realm
 * @Date: Create in 2019/4/15 12:08
 */
public class CustomPermissionRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.得到身份信息
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        //2.模拟从数据库中得到权限列表
        List<String> permissions = new ArrayList<>();
        permissions.add("student:create");
        permissions.add("student:delete");
        permissions.add("student:update");
        //3.构造AuthorizationInfo对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //4.将权限列表对象与SimpleAuthorizationInfo对象进行绑定
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.得到认证身份对象
        Object principal = token.getPrincipal();
        if(principal == null) return null;
        //2.从数据库中得到密码
        String password = "111111";
        return new SimpleAuthenticationInfo(principal,password,"aaa");
    }
}
