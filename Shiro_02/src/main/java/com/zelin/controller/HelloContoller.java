package com.zelin.controller;

import com.zelin.pojo.Student;
import com.zelin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/13 09:03
 */
@RestController
public class HelloContoller {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/hello")
    public List<Student> hello(){
        try {
            return studentService.findStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
