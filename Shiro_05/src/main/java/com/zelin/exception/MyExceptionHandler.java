package com.zelin.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/16 09:56
 */
@ControllerAdvice
public class MyExceptionHandler {
    //定义异常处理器
    @ExceptionHandler(MyException.class)
    public String myException(MyException ex, Model model){
        //1.得到异常出错信息
        String message = ex.getMessage();
        //2.如果信息为null，就指定一个message值
        if(StringUtils.isEmpty(message)){
            message = "未知异常！";
        }
        model.addAttribute("message",message);
        //3.到错误页面进行处理
        return "errorpage";
    }
}
