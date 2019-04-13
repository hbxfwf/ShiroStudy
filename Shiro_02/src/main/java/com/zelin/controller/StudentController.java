package com.zelin.controller;

import com.zelin.pojo.PageBean;
import com.zelin.pojo.Student;
import com.zelin.service.ClassesService;
import com.zelin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description:
 * @Date: Create in 2019/4/13 09:34
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;
    private int pageSize = 5;
    @RequestMapping("/list")
    public List<Student> findAll(){
        try {
            return studentService.findStudents();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/search")
    public PageBean search(@RequestParam(defaultValue = "1") int page, Student student, Model model){
        try {
            PageBean pageBean = studentService.search(page,pageSize,student);
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
