package com.nankai.app.service;

import java.util.List;

import com.nankai.app.domain.Chatroom;



public interface ChatService {
	//����
	public void add(Chatroom chat);
	//�޸�
	public void update(Chatroom chat);
	//ɾ��
	public void delete(Chatroom chat);
	//��ѯȫ��,��������
	public List<Chatroom> findAll();
	//ͨ��ID��ѯ
	/*public Activity findMemberByID(int aid);*/
}
