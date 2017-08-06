package com.nankai.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nankai.app.domain.Member;
import com.nankai.app.service.MemberService;
import com.nankai.app.util.HibernateUtil;
import com.nankai.app.vo.RegisterPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MemberAction extends ActionSupport implements ModelDriven<Member>,ServletResponseAware,ServletRequestAware{
	private Member member=new Member();
	private int currentPage=1;
	private int pageSize=100;
	
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
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public String updatePassword(){
		Member mem = memberService.findMemberByID(member.getMemberId());
		if(mem.getMemberPassword().equals(member.getOldmemberPassword()))
		{
			if(member.getNewmemberPassword().equals(member.getRememberPassword())) {
				mem.setMemberPassword(member.getNewmemberPassword());
				memberService.update(mem);
				return SUCCESS;
			}else {
				this.addActionError("两次密码不一致！");
				return "fail";
			}
			
		}
		
		else 
		{
			this.addActionError("用户名或密码错误！");
			return "fail";
		}	
	}
	public String updatePasswordForAndroid(){
		response.setCharacterEncoding("utf-8");
		String name = (String) request.getParameter("username");
		String oldPwd = (String) request.getParameter("oldPwd");
		String newPwd = (String) request.getParameter("newPwd");
		String confirmPwd = (String)request.getParameter("confirmPwd");
		Member mem = memberService.findMemberByID(Integer.parseInt(name));
		if(mem!=null&&mem.getMemberPassword().equals(oldPwd))
		{
			if(newPwd.equals(confirmPwd))
			{
				mem.setMemberPassword(newPwd);
				memberService.update(mem);
				try {
					PrintWriter out = response.getWriter();
					out.print("success");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return SUCCESS;
			}else {
				try {
					PrintWriter out = response.getWriter();
					out.print("difference");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "fail";
			}
			
		}
		else 
		{
			try {
				PrintWriter out = response.getWriter();
				out.print("none");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "fail";
		}	
	}
	public String add(){
    	memberService.add(member);
    	return "member_search";
    }
    public String delete(){
    	Member member1=memberService.findMemberByID(member.getMemberId());
    	memberService.delete(member1);
    	return "member_search";
    }
    public String searchBeforeUpdate(){
        Member member1=memberService.findMemberByID(member.getMemberId());
        ServletActionContext.getRequest().setAttribute("member",member1);
        return "update";
    }
    public String search(){
    	RegisterPage memberPage=memberService.findAll(currentPage, pageSize);
    	ServletActionContext.getRequest().setAttribute("membersPage", memberPage);
    	ServletActionContext.getRequest().setAttribute("members", memberPage.getMemberList());
    	return "search_success";
    }
    public String update(){
    	memberService.update(member);
    	return "member_search";
    }
    public String findByID(){
    	//按ID查找，返回的是单个对象，为了统一，也存到链表里面
    	Member member1= memberService.findMemberByID(member.getMemberId());
    	List<Member> list = new ArrayList();
    	list.add(member1);
    	ServletActionContext.getRequest().setAttribute("members", list);
    	return "findMemberBySomething";
    }
    public String findMemberByDid(){
    	List<Member> list1=memberService.findMemberByDid(member.getDepartment().getDepartmentId());
    	ServletActionContext.getRequest().setAttribute("members", list1);
    	return "findMemberBySomething";
    }
    public String findMemberByName(){
    	List<Member> list1=memberService.findMemberByName(member.getMemberName());
    	ServletActionContext.getRequest().setAttribute("members", list1);
    	return "findMemberBySomething";
    }
    public String findMemberByGender(){
    	List<Member> list1=memberService.findMemberByGender(member.getMemberGender());
    	ServletActionContext.getRequest().setAttribute("members", list1);
    	return "findMemberBySomething";
    }
    public String findMemberByHometown(){
    	List<Member> list1=memberService.findMemberByHometown(member.getMemberHometown());
    	ServletActionContext.getRequest().setAttribute("members", list1);
    	return "findMemberBySomething";
    }
    public String findMemberByMajor(){
    	List<Member> list1=memberService.findMemberByMajor(member.getMemberMajor());
    	ServletActionContext.getRequest().setAttribute("members", list1);
    	return "findMemberBySomething";
    }
    public String findMemberByPosition(){
    	List<Member> list1=memberService.findMemberByPosition(member.getMemberPosition());
    	ServletActionContext.getRequest().setAttribute("members", list1);
    	return "findMemberBySomething";
    }
    public String findMemberBySomething(){
    	
    	if(member.getMemberId()!=null){
    		return "member_findByID";
    	}
    	if(!member.getMemberName().isEmpty()){
    		System.out.println(member.getMemberName()+"2hjsahd");
    		return "member_findMemberByName";
    	}
    	if(!member.getMemberGender().isEmpty()){
    		System.out.println("findByGender");
    		return "member_findMemberByGender";
    	}
    	if(!member.getMemberPosition().isEmpty()){
    		System.out.println("findByPosition");
    		return "member_findMemberByPosition";
    	}
    	if(!member.getMemberMajor().isEmpty()){
    		return "member_findMemberByMajor";
    	}
    	if(!member.getMemberHometown().isEmpty()){
    		return "member_findMemberByHometown";
    	}
    	if(member.getDepartment().getDepartmentId()!=null){
    		return "member_findMemberByDid";
    	}
    	else{
    		return "member_search";
    	}
    }
    //根据登录权限查询
    public String managerSearch(){
    	RegisterPage memberPage=memberService.findAllForManager(currentPage, pageSize);
    	ServletActionContext.getRequest().setAttribute("managerMembers", memberPage.getMemberList());
    	ServletActionContext.getRequest().setAttribute("membersPage", memberPage);
    	return "managerSearch_success";
    }
	@Override
	public Member getModel() {
		// TODO Auto-generated method stub
		return member;
	}

}
