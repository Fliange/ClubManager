package com.nankai.app.service.impl;

import java.util.List;

import com.nankai.app.dao.DepartmentDao;
import com.nankai.app.domain.Department;
import com.nankai.app.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{

	DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public List<Department> findAll() {
		return departmentDao.findAll();
	}
	
}
