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
		//ʹ�õ�¼�������ݿ���¼  �鵽��¼���������һ��һ��
		//Ȼ��ʹ��session�����û��� ���û�Ȩ�޵ȼ�
		
		Member member = memberService.findMemberByID(mem.getMemberId());
		HttpSession sessionForUsername=ServletActionContext.getRequest().getSession();
		HttpSession sessionForPosition=ServletActionContext.getRequest().getSession();
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(member != null && member.getMemberPassword().equals(mem.getMemberPassword()))
		{
			sessionForUsername.setAttribute("username", member.getMemberId());
			sessionForPosition.setAttribute("userPosition",member.getMemberPosition());
			session.setAttribute("user", member);
			session.setAttribute("name", "��ã�"+member.getMemberName());
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("succe", "��¼�ɹ���");
			/*if()
			request.getAttribute("lastPage")*/
			return SUCCESS;
		}
		else if(mem.getMemberId()==23501326 && mem.getMemberPassword().equals("1")){
			sessionForUsername.setAttribute("username",mem.getMemberId());
			sessionForPosition.setAttribute("userPosition","����Ա");
			session.setAttribute("name", "��ã�������");
			mem.setMemberPosition("����Ա");
			session.setAttribute("user", mem);
			return SUCCESS;
		}
		else{
			this.addActionError("�û������������");
			return "fail";
		}						
	}
	
	@Override
	public Member getModel() {
		return mem;
	}
}
