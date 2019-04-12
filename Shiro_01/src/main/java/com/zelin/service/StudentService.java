package com.zelin.service;

import java.util.List;

import com.zelin.pojo.PageBean;
import com.zelin.pojo.Student;
import com.zelin.pojo.StudentCustom;

public interface StudentService {

	//查询所有学生
	public List<Student> findStudents() throws Exception;
	//查询所有学生（分页）
	public PageBean findAllStudents(int page, int pagesize) throws Exception;
	//分页带模糊查询
	public PageBean findAllStudents(StudentCustom studentCustom) throws Exception;
	//根据条件查询出学生信息（查询得到的是满足条件的所有记录）
	public List<Student> findStudentsByWords(Student student) throws Exception;
	//根据查询条件得到记录数
	public int getCountByWords(Student student) throws Exception;
	//添加学生
	public void add(Student student) throws Exception;
	//修改学生
	public void update(Student student) throws Exception;
	//根据主键删除学生
	public void delete(int sid) throws Exception;
	
}
