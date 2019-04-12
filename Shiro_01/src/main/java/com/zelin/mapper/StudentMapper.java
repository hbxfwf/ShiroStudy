package com.zelin.mapper;

import java.util.List;

import com.zelin.pojo.MyPage;
import com.zelin.pojo.Student;
import com.zelin.pojo.StudentCustom;
import com.zelin.pojo.StudentExample;
import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

public interface StudentMapper extends Mapper <Student>{

    //得到每页的学生记录集合
    List<Student> selectStudentsByPage(MyPage page);
    //分页带模糊查询学生记录信息
    List<Student> selectStudentsByPageAndWords(StudentCustom custom);
    //得到条件查询的记录数
    int selectCountByWords(Student student) ;

}