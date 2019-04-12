package com.zelin.service.impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zelin.mapper.ClassesMapper;
import com.zelin.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zelin.mapper.StudentMapper;
import com.zelin.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ClassesMapper classesMapper;
	public List<Student> findStudents() throws Exception {
		List<Student> students = studentMapper.selectByExample(null);
		for (Student student : students) {
			Classes classes = classesMapper.selectByPrimaryKey(student.getCid());
			student.setClasses(classes);
		}
		return students;
	}

	/**
	 * 查询所有学生（带有分页功能）
	 * @param page
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean findAllStudents(int page,int pagesize) throws Exception {
		//1.开始分页
		PageHelper.startPage(page,pagesize);
		//2.查询得到当前分页的Page对象
		StudentExample example = new StudentExample();
		//3.查询结果
		Page<Student> students = (Page<Student>) studentMapper.selectByExample(example);
		//4）将当前学生结果集与班级对象相关联
		for (Student student : students.getResult()) {
			Classes classes = classesMapper.selectByPrimaryKey(student.getCid());
			student.setClasses(classes);
		}
		return new PageBean((int)students.getTotal(), students.getResult(),students.getPages());
	}
	@Override
	public PageBean findAllStudents(StudentCustom studentCustom) throws Exception {
		//int total = this.findStudentsByWords(studentCustom.getStudent()).size();
		int total = this.getCountByWords(studentCustom.getStudent());
		//2.得到每一页的记录数
		List<Student> rows = studentMapper.selectStudentsByPageAndWords(studentCustom);
		return new PageBean(total, rows);
	}

	public List<Student> findStudentsByWords(Student student) throws Exception {
		StudentExample example = null;
		if(!(StringUtils.isEmpty(student.getSname()) &&
				StringUtils.isEmpty(student.getAddr()) &&
				student.getClasses().getCid() == 0)){
			example = new StudentExample();
			//创建条件
			StudentExample.Criteria criteria = example.createCriteria();
			criteria.andSnameLike("%"+student.getSname()+"%");
			criteria.andAddrLike("%" + student.getAddr()+"%");
			if(student.getClasses().getCid() != 0){
				criteria.andCidEqualTo(student.getClasses().getCid());
			}
		}
		return studentMapper.selectByExample(example);
	}
	public int getCountByWords(Student student) throws Exception {
		return studentMapper.selectCountByWords(student);
	}

	@Override
	public void add(Student student) throws Exception{
		studentMapper.insertSelective(student);
	}
	@Override
	public void update(Student student) throws Exception {
		studentMapper.updateByPrimaryKey(student);
	}
	@Override
	public void delete(int sid)  throws Exception {
		studentMapper.deleteByPrimaryKey(sid);
	}

	/**
	 * 条件查询带分页功能
	 * @param page
	 * @param pageSize
	 * @param student
	 * @return
	 */
	@Override
	public PageBean search(int page, int pageSize, Student student) {
		//1.开始分页
		PageHelper.startPage(page,pageSize);
		//2.定义查询实例
		StudentExample studentExample = new StudentExample();
		//3.添加查询条件到查询实例中
		StudentExample.Criteria criteria = studentExample.createCriteria();
		//4.构造查询条件
		if(student != null){
			if(StringUtils.isNotEmpty(student.getSname())){
				criteria.andSnameLike("%" + student.getSname() + "%");
			}
			if(StringUtils.isNotEmpty(student.getAddr())){
				criteria.andAddrLike("%" + student.getAddr() + "%");
			}
			if(student.getCid() != null && student.getCid().intValue() != 0){
				criteria.andCidEqualTo(student.getCid()) ;
			}
		}
		//5.进行条件查询(注意：如果没有在applicationContext.xml文件中的sqlSessionFactoryBean中定义plugins的话，就会报:类型转换失败)
		Page<Student> pageData = (Page<Student>) studentMapper.selectByExample(studentExample);
		//6.将学生与其班级对象进行绑定并关联
		for (Student stud : pageData.getResult()) {
			stud.setClasses(classesMapper.selectByPrimaryKey(stud.getCid()));
		}
		//7.将得到的分页对象返回
		return new PageBean((int)pageData.getTotal(),pageData.getResult(),pageData.getPages());
	}

}
