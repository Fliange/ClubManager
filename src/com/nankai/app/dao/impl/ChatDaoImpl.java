package com.nankai.app.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.nankai.app.dao.ChatDao;
import com.nankai.app.domain.Chatroom;

public class ChatDaoImpl extends HibernateDaoSupport implements ChatDao {

	@Override
	public void add(Chatroom chat) {
		this.getHibernateTemplate().save(chat);
	}

	@Override
	public void update(Chatroom chat) {
		this.getHibernateTemplate().update(chat);
	}

	@Override
	public void delete(Chatroom chat) {
		this.getHibernateTemplate().delete(chat);
	}

	@Override
	public List<Chatroom> findAll(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
