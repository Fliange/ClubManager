package com.nankai.app.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nankai.app.dao.MemberDao;
import com.nankai.app.domain.Department;
import com.nankai.app.domain.Member;
import com.nankai.app.domain.Organization;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MemberDaoImpl extends HibernateDaoSupport implements MemberDao{

	@Override
	public void update(Member member) {
		this.getHibernateTemplate().update(member);	
	}

	@Override
	public void add(Member member) {
		this.getHibernateTemplate().save(member);
	}

	@Override
	public void delete(Member member) {
		this.getHibernateTemplate().delete(member);
	}

	@Override
	public List<Member> findAll(final int currentPage, final int pageSize) {
		List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				Query query  = session.createQuery("from Member");
				query.setFirstResult((currentPage-1)*pageSize);
				query.setMaxResults(pageSize);
				List<Member> list = query.list();
				return list;
			}
		});
		return list;
	}

	@Override
	public Member findMemberByID(int memberId) {
		return this.getHibernateTemplate().get(Member.class,memberId);
	}

	@Override
	public int findTotalCount() {
		Long count=(Long) this.getHibernateTemplate().find("select count(*)from Member").iterator().next();
		return count.intValue();
	}

	@Override
	public List<Member> findMemberByDid(int did) {
		List<Member> list = this.getHibernateTemplate().find("from Member where department.departmentId=?",did);
		return list;
	}

	@Override
	public List<Member> findMemberByName(String name) {
		List<Member> list = this.getHibernateTemplate().find("from Member where memberName=?",name);
		return list;
	}

	@Override
	public List<Member> findMemberByGender(String gender) {
		List<Member> list = this.getHibernateTemplate().find("from Member where memberGender=?",gender);
		return list;
	}

	@Override
	public List<Member> findMemberByMajor(String major) {
		List<Member> list = this.getHibernateTemplate().find("from Member where memberMajor=?",major);
		return list;
	}

	@Override
	public List<Member> findMemberByHometown(String hometown) {
		List<Member> list = this.getHibernateTemplate().find("from Member where memberHometown=?",hometown);
		return list;
	}

	@Override
	public List<Member> findMemberByPosition(String position) {
		List<Member> list = this.getHibernateTemplate().find("from Member where memberPosition=?",position);
		return list;
	}
	@Override
	public List<Member> findAllForManager(int currentPage, int pageSize) {
		//���ݵ�¼����ݣ��г��������ŵ�������
		//String position = (String) ServletActionContext.getRequest().getAttribute("userPosition");
		//Integer username = (Integer)ServletActionContext.getRequest().getAttribute("username");
		//1.���Ȼ�õ�¼��member����
		Member user = (Member) ServletActionContext.getRequest().getSession().getAttribute("user");
		String position = user.getMemberPosition();
		if(position.equals("�߲�"))
		{
			//2.����member���󣬻�ȡ����department����
			Department dpt = user.getDepartment();
			//3.����department���󣬻�ȡ����Organization����
			Organization org = dpt.getOrganization();
			//4.����Organization���󣬻�ȡ������Ͻ���ż���
			Set<Department> dptSet = org.getDepartments();
			//5.�������ż��ϣ���ȡÿ�����ŵĳ�Ա����
			Set<Member> memSet = new HashSet();
			for (Department department : dptSet) {  
				Set<Member> temp = department.getMembers();
				for(Member mem:temp)
				{
					memSet.add(mem);
				}
			} 
			//List<Member> list = this.getHibernateTemplate().find("from Member where department.departmentId=?",did);
			//ת��һ��
			List<Member> list = new ArrayList(memSet);
			return list;
		}
		else
		{
			if(position.equals("����Ա"))
			{
				return findAll(currentPage, pageSize);
			}
			else
			{
				if(position.equals("�в�"))
				{
					Department dpt = user.getDepartment();
					Set<Member> mems = dpt.getMembers();
					Set<Member> memSet = new HashSet();
					for(Member mem:mems)
					{
						memSet.add(mem);
					}
					//ת��һ��
					List<Member> list = new ArrayList(memSet);
					return list;
				}
				else
				{
					return null;
				}
			}
		}
		
	}

}
