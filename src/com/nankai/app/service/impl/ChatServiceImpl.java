package com.nankai.app.service.impl;

import java.util.List;

import com.nankai.app.dao.ChatDao;
import com.nankai.app.domain.Chatroom;
import com.nankai.app.service.ChatService;

public class ChatServiceImpl implements ChatService{

	private ChatDao chatDao;
	
	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}

	@Override
	public void add(Chatroom chat) {
		chatDao.add(chat);
	}

	@Override
	public void update(Chatroom chat) {
		chatDao.update(chat);
	}

	@Override
	public void delete(Chatroom chat) {
		chatDao.delete(chat);
	}

	@Override
	public List<Chatroom> findAll() {
		return chatDao.findAll();
	}
}
