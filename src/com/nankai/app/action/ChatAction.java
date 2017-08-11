package com.nankai.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSON;
import com.nankai.app.domain.Activity;
import com.nankai.app.domain.Chatroom;
import com.nankai.app.domain.Member;
import com.nankai.app.service.ChatService;
import com.nankai.app.service.MemberService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ChatAction  extends ActionSupport implements ModelDriven<Chatroom>,ServletResponseAware,ServletRequestAware{
	private Chatroom chatroom;
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	private HttpServletResponse response;
	private HttpServletRequest request;
	
	//ʹ��chatService�����ݿ��� ���������ű������ɾ�Ĳ�
	private ChatService chatService;
	
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	public String test()
	{
		//Ϊ�˰�׿����һ��request
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String messageTime = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		System.out.println(content+"------"+userId+"---------"+messageTime);
		if(content != null ){
			Chatroom newChat = new Chatroom();
			newChat.setMessageAuthor(Integer.parseInt(userId));
			newChat.setMessageContent(content);
			newChat.setMessageTime(messageTime);
			chatService.add(newChat);
			System.out.println("�������ݿ�ɹ���");
			//�ѳɹ���Ϣд��ȥ	
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("chatSuccess");
				out.flush();
				out.close();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return SUCCESS;
		
	}
	
	//��ѯ���й���
	public String findAll() {
		//Ϊ�˰�׿����һ��request
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//�����ݿ��ѯ���еĹ���
		List<Chatroom> list = chatService.findAll();
		//������б�ת��json
		//����
		List<HashMap<String, Object>> map_List = new ArrayList<HashMap<String, Object>>();
             
        System.out.println("��������---"+list.size());
        for(Chatroom chat :list)    
        {    
            HashMap<String,Object> map = new HashMap();
            Member mem = memberService.findMemberByID(chat.getMessageAuthor());
            if(mem == null)
            	continue;
            String authorName = mem.getMemberName();
            map.put("MessageAuthor", authorName);
            map.put("MessageContent",chat.getMessageContent());
            map.put("MessageTime", chat.getMessageTime());
            map_List.add(map);
        }
        
   	 	String result = JSON.toJSONString(map_List);
   	 	System.out.println("����JSON-----"+result);

		//��jsonд��ȥ	
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
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
