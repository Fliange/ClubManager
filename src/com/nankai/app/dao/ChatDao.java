package com.nankai.app.dao;

import java.util.List;

import com.nankai.app.domain.Chatroom;
import com.nankai.app.domain.Organization;

public interface ChatDao {
	//����
	public void add(Chatroom chat);
	//�޸�
	public void update(Chatroom chat);
	//ɾ��
	public void delete(Chatroom chat);
	//��ѯȫ��
	public List<Chatroom> findAll();
	//����idȥ��ѯ����
	public int findTotalCount();
}
