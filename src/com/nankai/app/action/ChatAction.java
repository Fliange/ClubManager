package com.nankai.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.nankai.app.domain.Chatroom;
import com.nankai.app.service.ChatService;
import com.nankai.app.service.MemberService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ChatAction  extends ActionSupport implements ModelDriven<Chatroom>,ServletResponseAware,ServletRequestAware{
	private Chatroom chatroom;
	private MemberService memberService;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	//使用chatService对数据库中 聊天室这张表进行增删改查
	private ChatService chatService;
	
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	public String test()
	{
		
		
		//为了安卓，出一个request
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String content = request.getParameter("content");
		System.out.println(content+"------");
		if(content != null ){
			Chatroom newChat = new Chatroom();
			newChat.setMessageAuthor(1);
			newChat.setMessageContent(content);
			chatService.add(newChat);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("写入数据库成功喽！");
				out.println(content);
				out.flush();
				out.close();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return SUCCESS;
		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public Chatroom getModel() {
		return chatroom;
	}

}
