package com.nankai.app.dao;

import java.util.List;

import com.nankai.app.domain.Chatroom;
import com.nankai.app.domain.Organization;

public interface ChatDao {
	//增加
	public void add(Chatroom chat);
	//修改
	public void update(Chatroom chat);
	//删除
	public void delete(Chatroom chat);
	//查询全部
	public List<Chatroom> findAll();
	//根据id去查询社团
	public int findTotalCount();
}
