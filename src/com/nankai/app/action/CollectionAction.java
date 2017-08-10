package com.nankai.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.nankai.app.domain.Chatroom;
import com.nankai.app.domain.Collection;
import com.nankai.app.service.ChatService;
import com.nankai.app.service.CollectionService;
import com.nankai.app.service.MemberService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CollectionAction  extends ActionSupport implements ModelDriven<Collection>,ServletResponseAware,ServletRequestAware{
	private Collection collection;
	private MemberService memberService;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	//ʹ��chatService�����ݿ��� ���������ű�������ɾ�Ĳ�
	private CollectionService collectionService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public String test()
	{
		System.out.println("collection test ����");	
		//Ϊ�˰�׿����һ��request
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String activityId = request.getParameter("activityId");
		String userId = request.getParameter("userId");
		String state = request.getParameter("state");
		System.out.println("activityId------------"+activityId);
		System.out.println("userId------------"+userId);
		System.out.println("state------------"+state);
		
		//�����ݿ��ҳ���ǰuserId��activityId��Ӧ���ղؼ�¼
		//Collection mCollection = collectionService.findCollectionByUserAndActivity(Integer.parseInt(userId), Integer.parseInt(activityId));
		int actID = Integer.parseInt(activityId);
		int userID = Integer.parseInt(userId);
		Collection mCollection = new Collection();
		mCollection.setActivityId(actID);
		mCollection.setUserId(userID);
		collectionService.add(mCollection);
		/*if(mCollection != null)
		{
			collectionService.add(mCollection);
		}*/
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("д�����ݿ�ɹ�ඣ�");
			out.flush();
			out.close();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*String content = request.getParameter("content");
		System.out.println(content+"------");
		if(content != null ){
			Chatroom newChat = new Chatroom();
			newChat.setMessageAuthor(1);
			newChat.setMessageContent(content);
			collectionService.add(newChat);
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("д�����ݿ�ɹ�ඣ�");
				out.println(content);
				out.flush();
				out.close();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return SUCCESS;
		
	}
	
	public String findCollectionByUserAndActivity() {
		
		//Ϊ�˰�׿����һ��request
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String activityId = request.getParameter("activityId");
		String userId = request.getParameter("userId");
		//ȥ���ݿ���¼
		Collection mCollection = collectionService.findCollectionByUserAndActivity(Integer.parseInt(userId), Integer.parseInt(activityId));
		
			PrintWriter out;
			try {
				out = response.getWriter();
				if(mCollection != null)
				{
					out.println("writesuccess");
				}
				else{
					out.println("writesufail");
				}
				out.flush();
				out.close();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
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
	public Collection getModel() {
		return collection;
	}

}