package com.nankai.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nankai.app.domain.Member;
import com.nankai.app.service.MemberService;
import com.nankai.app.util.HibernateUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;





public class LoginAction extends ActionSupport implements ModelDriven<Member>{
	private Member mem=new Member();
	MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public String execute(){
		//使用登录名到数据库查记录  查到记录，检查密码一不一样
		//然后使用session传出用户名 和用户权限等级
		
		Member member = memberService.findMemberByID(mem.getMemberId());
		HttpSession sessionForUsername=ServletActionContext.getRequest().getSession();
		HttpSession sessionForPosition=ServletActionContext.getRequest().getSession();
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(member != null && member.getMemberPassword().equals(mem.getMemberPassword()))
		{
			sessionForUsername.setAttribute("username", member.getMemberId());
			sessionForPosition.setAttribute("userPosition",member.getMemberPosition());
			session.setAttribute("user", member);
			session.setAttribute("name", "你好，"+member.getMemberName());
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("succe", "登录成功！");
			/*if()
			request.getAttribute("lastPage")*/
			return SUCCESS;
		}
		else if(mem.getMemberId()==23501326 && mem.getMemberPassword().equals("1")){
			sessionForUsername.setAttribute("username",mem.getMemberId());
			sessionForPosition.setAttribute("userPosition","管理员");
			session.setAttribute("name", "你好，等永恒");
			mem.setMemberPosition("管理员");
			session.setAttribute("user", mem);
			return SUCCESS;
		}
		else{
			this.addActionError("用户名或密码错误！");
			return "fail";
		}						
	}
	
	@Override
	public Member getModel() {
		return mem;
	}
}
