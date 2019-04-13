package com.zelin.service;

import com.zelin.pojo.Classes;

import java.util.List;

public interface ClassesService {

	//查询所有的班级信息
	public  List<Classes> findAll() throws Exception;
}
