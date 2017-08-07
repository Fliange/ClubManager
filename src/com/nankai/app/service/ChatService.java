package com.nankai.app.service;

import com.nankai.app.domain.Chatroom;



public interface ChatService {
	//增加
	public void add(Chatroom chat);
	//修改
	public void update(Chatroom chat);
	//删除
	public void delete(Chatroom chat);
	/*//查询全部,返回分页所需的数据
	public ActPage findAll(int currentPage,int pageSize);
	//通过ID查询
	public Activity findMemberByID(int aid);*/
}
