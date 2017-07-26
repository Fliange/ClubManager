package com.nankai.app.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nankai.app.domain.Register;
import com.nankai.app.service.RegisterService;
import com.nankai.app.vo.RegisterPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;



public class RegisterAction extends ActionSupport implements ModelDriven<Register>{
	private Register register = new Register();	
	//分页需要使用的参数
	private int currentPage=1;
	private int pageSize=6;
	
	//配置service
	private RegisterService registerService;
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	//新增报名记录
	public String add(){
		registerService.add(register);	
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("success", "报名成功！");
		return "addsuccess";	
	}
	//查询报名记录
	public String search(){
		RegisterPage registerPage1 = registerService.findAll(currentPage, pageSize);
		System.out.println("这里是search函数");
		ServletActionContext.getRequest().setAttribute("registersPage", registerPage1);
		return SUCCESS;
	}
	//真删
	public String delete(){
		Register register1=registerService.findRegisterByID(register.getRegisterId());
		registerService.delete(register1);
		return "register_search";
	}
	//假删
	public String falseDelete() {
		Register register1=registerService.findRegisterByID(register.getRegisterId());
		if(register1.getRegisterStatus()==0) {
			register1.setRegisterStatus(1);
		}
		else{if(register1.getRegisterStatus()==1){
			register1.setRegisterStatus(2);
			}
		}
		if(register1.getRegisterStatus()==2&&register1.getRegisterAdjust().equals("否")) {
			registerService.delete(register1);
			return "register_search";
		}
		registerService.update(register1);
		return "register_search";
	}
	//查找ID
	public String findByID(){
		Register register1=registerService.findRegisterByID(register.getRegisterId());
		ServletActionContext.getRequest().setAttribute("register", register1);
		return "findByID";
	}
	public String findByIDForDetail(){
		Register register1=registerService.findRegisterByID(register.getRegisterId());
		ServletActionContext.getRequest().setAttribute("register", register1);
		return "findByIDForDetail";
	}
	//进行刷新
	public String flush()
	{
		ServletActionContext.getRequest().removeAttribute("registersPage");
		return SUCCESS;
	}
	@Override
	public Register getModel() {
		// TODO Auto-generated method stub
		return register;
	}

}
