package com.zelin.service.impl;

import java.util.List;

import com.zelin.mapper.ClassesMapper;
import com.zelin.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	@Override
	public PageBean findAllStudents(int page,int pagesize) throws Exception {
		//1.得到总记录数
		int total = this.findStudents().size();
		//2.得到每一页的记录数
		List<Student> rows = studentMapper.selectStudentsByPage(new MyPage((page-1)*pagesize, pagesize));
		return new PageBean(total, rows);
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

}
