package com.nankai.app.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.nankai.app.domain.Department;
import com.nankai.app.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	Department dpt = new Department();
	//springøÚº‹œ¬Ã·π©Service
	private DepartmentService departmentService;
	
	public void setDpt(Department dpt) {
		this.dpt = dpt;
	}
	public Department getDpt() {
		return dpt;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public String findAll()
	{
		List<Department> dptList = departmentService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("DepartmentList", dptList);
		return SUCCESS;
	}
	public String findAllForActivityUpdate()
	{
		List<Department> dptList = departmentService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("DepartmentList", dptList);
		return "activity";
	}
	public String findAllForRegister()
	{
		List<Department> dptList = departmentService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("DepartmentList", dptList);
		return "registerjsp";
	}
	public String findAllForUpdateMember()
	{
		List<Department> dptList = departmentService.findAll();
		ServletActionContext.getRequest().getSession().setAttribute("DepartmentList", dptList);
		return "updateMember";
	}
	
	@Override
	public Department getModel() {
		return dpt;
	}
}
