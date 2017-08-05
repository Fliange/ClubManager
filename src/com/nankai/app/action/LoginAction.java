package com.nankai.app.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.nankai.app.domain.Member;
import com.nankai.app.service.MemberService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<Member>,ServletResponseAware,ServletRequestAware {
	private Member mem=new Member();
	MemberService memberService;
	private HttpServletResponse response;
	private HttpServletRequest request;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public String execute(){
		//使用登录名到数据库查记录  查到记录，检查密码一不一样
		//然后使用session传出用户名 和用户权限等级
		response.setCharacterEncoding("utf-8");
		String name = (String) request.getParameter("username");
		String pwd = (String) request.getParameter("pwd");
		if(name.equals("23501326") && pwd.equals("1")){
			try {
				PrintWriter out = response.getWriter();
				out.print("管理员");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}	
		Member member = memberService.findMemberByID(Integer.parseInt(name));
		if(member != null && member.getMemberPassword().equals(pwd))
		{
			try {
				PrintWriter out = response.getWriter();
				out.print(member.getMemberPosition());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else{
			try {
				PrintWriter out = response.getWriter();
				out.print("fail");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "fail";
		}
	}
	
	@Override
	public Member getModel() {
		return mem;
	}
}
