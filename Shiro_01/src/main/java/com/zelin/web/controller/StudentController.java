package com.zelin.web.controller;

import com.zelin.pojo.Student;
import com.zelin.service.ClassesService;
import com.zelin.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/8 10:50
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentSerivce;
    @Autowired
    private ClassesService classesService;
    int pageSize = 5;       //代表每页的大小
    //查询所有的学生
    @RequestMapping("/tolist")
    public String findAll(Model model){
        try {
            //1.查询所有的学生集合
            List<Student> students = studentSerivce.findStudents();
            System.out.println("----->" + students);
            //2.将上面的集合放到model中
            model.addAttribute("students",students);
            model.addAttribute("classes",classesService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4.返回逻辑视图
        return "student/list";
    }

}

