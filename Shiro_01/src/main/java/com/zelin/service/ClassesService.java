package com.zelin.service;

import java.util.List;

import com.zelin.pojo.Classes;

public interface ClassesService {

	//查询所有的班级信息
	public  List<Classes> findAll() throws Exception;
}
