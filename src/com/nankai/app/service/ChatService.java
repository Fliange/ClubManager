package com.nankai.app.service;

import com.nankai.app.domain.Chatroom;



public interface ChatService {
	//����
	public void add(Chatroom chat);
	//�޸�
	public void update(Chatroom chat);
	//ɾ��
	public void delete(Chatroom chat);
	/*//��ѯȫ��,���ط�ҳ���������
	public ActPage findAll(int currentPage,int pageSize);
	//ͨ��ID��ѯ
	public Activity findMemberByID(int aid);*/
}
