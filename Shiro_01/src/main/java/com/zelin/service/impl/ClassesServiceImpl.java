package com.zelin.service.impl;

import java.util.List;

import com.zelin.pojo.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelin.mapper.ClassesMapper;
import com.zelin.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	private ClassesMapper classesMapper;
	@Override
	public List<Classes> findAll() throws Exception {
		return classesMapper.selectByExample(null);
	}

}
