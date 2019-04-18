package com.zelin.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description: 自定义表单过滤器完成验证码的验证
 * @Date: Create in 2019/4/16 15:27
 */
public class CustomFormFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1.将原始的请求与响应对象转换为基于http请求的对象
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //2.从session中取出原始的验证码
        HttpSession session = req.getSession();
        String validateCode = (String) session.getAttribute("validateCode");
        //3.得到用户输入的验证码
        String validcode = req.getParameter("validcode");
        //4.比较两次验证码是否一样,如果两次验证码不相等，就将错误信息放到request对象中
        if(StringUtils.isNotEmpty(validcode) && !validateCode.equals(validcode)){
            req.setAttribute("shiroLoginFailure","validCodeError");
            return true;      //代表验证未通过
        }
        return super.onAccessDenied(request, response);
    }
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request,response,getSuccessUrl(),null,true);
        //super.issueSuccessRedirect(request, response);
    }
}
