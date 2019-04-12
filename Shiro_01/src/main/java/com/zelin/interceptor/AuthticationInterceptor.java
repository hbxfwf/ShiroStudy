package com.zelin.interceptor;

import com.zelin.pojo.SysPermission;
import com.zelin.pojo.SysUser;
import com.zelin.utils.CommUtils;
import com.zelin.utils.ResourcesUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:用户认证拦截器
 * @Date: Create in 2019/4/12 11:13
 */
@Component
public class AuthticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.情况一：如果是匿名用户，就放行
        //1.1) 得到当前登录的用户的url地址
        String uri = request.getRequestURI();
        //1.2) 对uri地址作处理
        uri = CommUtils.getPath(uri);
        //1.3) 得到匿名用户的地址列表
        List<String> anonymousURL = ResourcesUtil.gekeyList("anonymousURL");
        //1.4) 判断当前登录的url地址是否在上面的地址集合中,如果在就放行
        if (anonymousURL.contains(uri)) return true;

        //情况二：看用户是否登录，如果登录就看其访问地址是否在公用的url地址列表中，
        //2.1) 得到session，并且取得当前登录的用户对象user
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        //2.2) 判断是否存在此用户
        if (null != user) {    //代表用户登录过
            //2.3）读取公用的用户列表(只有登录成功的用户才能访问此列表)
            List<String> commonURL = ResourcesUtil.gekeyList("commonURL");
            //2.4) 判断当前登录用户的访问url地址是否在commonURL中，是就放行
            if (commonURL.contains(uri)) return true;

            //情况三：根据当前用户，得到其访问资源的权限列表
            //3.1) 得到当前登录成功的用户的所有权限列表
            List<SysPermission> permissions = user.getPermissions();
            //3.2) 根据此权限列表遍历出每个权限，再看当前用户的访问url地址是否在其中
            for (SysPermission permission : permissions) {
                if (permission.getUrl().contains(uri)) {
                    return true;
                }
            }
        }
        //其它情况：如果上面的情况都不成立，就跳转到无权访问页面。
        request.getRequestDispatcher("/WEB-INF/jsp/resure.jsp").forward(request, response);
        return false;
    }
}
