package com.zelin.realm;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
import com.zelin.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/16 09:21
 */

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService userService;
    //用户授权
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            //1.取得主身份信息
            SysUser user = (SysUser) principals.getPrimaryPrincipal();
            List<SysPermission> permissions = null;
            if(null != user){
                System.out.println("开始查询数据库...");
                //2.取得此用户的权限列表
                permissions = userService.findPermissionsByUserCode(user.getUsercode());
                if(permissions != null && permissions.size() > 0){
                    user.setPermissions(permissions);
                }
            }
        //3.定义授权对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //4.将上面的用户的权限列表字符串赋值给当前的授权对象
        List<String> permissionList = new ArrayList<>();
        //5.转换上面的List<SysPermission>对象为List<String>对象
        for (SysPermission permission : permissions) {
            permissionList.add(permission.getPercode());
        }
        //6.将权限码列表与当前授权对象关联
        authorizationInfo.addStringPermissions(permissionList);
        //7.返回授权对象
        return authorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.得到主身份信息
        String usercode = (String) token.getPrincipal();
        //2.如果用户名存在
        if(usercode != null){
            SysUser user = userService.findUserByUserCode(usercode);
            if(user == null) return null;
            //2.1)如果不为null，就查询其对应的菜单列表
            List<SysPermission> menus = userService.findMenusByUserCode(usercode);
            user.setMenus(menus);
            //2.2)取出数据库中的密码及加盐值
            String password = user.getPassword();
            String salt = user.getSalt();
            return new SimpleAuthenticationInfo(user,password, ByteSource.Util.bytes(salt),"aaa");
        }
       return null;
    }
}
