package com.nankai.app.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSON;
import com.nankai.app.domain.Activity;
import com.nankai.app.domain.Organization;
import com.nankai.app.service.ActService;
import com.nankai.app.vo.ActPage;
import com.nankai.app.vo.OrgPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ActivityAction extends ActionSupport implements ModelDriven<Activity>,ServletResponseAware,ServletRequestAware{
	private Activity act = new Activity();
	private ActService actService;
	//����׿�������õ���
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	//��ҳ��Ҫʹ�õĲ���
	private int currentPage=1;
	private int pageSize=6;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setActService(ActService actService) {
		this.actService = actService;
	}
	//�����ݿ�Activity����Ӽ�¼
	public String add()
	{
		actService.add(act);
		return SUCCESS;
	}
	//��ʾ���ݿ������м�¼---��ҳ
	public String showAll()
	{
		ActPage actPage = actService.findAll(currentPage, pageSize);
		ServletActionContext.getRequest().getSession().setAttribute("actPage", actPage);
		return SUCCESS;
	}
	//��ʾ���ݿ������м�¼---����ҳ
	public String showAllForList()
	{
		//�ȴ����ݿ�������л��¼������list
		List<Activity> actList = actService.findAllForList();

		//���ñ����ʽ
		response.setContentType("text/html; charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//������б�ת��json
		//����
		List<HashMap<String, Object>> map_List = new ArrayList<HashMap<String, Object>>();
        
        
        System.out.println("---"+actList.size());
        for(Activity act :actList)    
        {    
            System.out.println(act.getActivityName());
            HashMap<String,Object> map = new HashMap();

            map.put("ActivityId", act.getActivityId());
            map.put("ActivityName",act.getActivityName());
            map.put("ActivityIntroduction", act.getActivityIntroduction());
            map.put("ActivityLocatio", act.getActivityLocation());
            map.put("ActivityContent", act.getActivityContent());
            map.put("ActivityPicture", act.getActivityPicture());
            map.put("ActivityOrganization", act.getOrganization().getOrganizationName());
            map_List.add(map);
        }
        
   	 	String result = JSON.toJSONString(map_List);
   	 	System.out.println("JSON-----"+result);

		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String showAllForManager()
	{
		ActPage actPage = actService.findAll(currentPage, pageSize);
		ServletActionContext.getRequest().setAttribute("actPage", actPage);
		return "manager";
	}
	//ɾ�����ݿ��м�¼
	public String delete(){
		Activity activity=actService.findMemberByID(act.getActivityId());
		actService.delete(activity);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("activity", "delete");
	    return "manager";
	}
	//�������ݿ��¼
	public String update(){
    	actService.update(act);
    	return "manager";
    }
	//����ID��ѯ��������ת���޸�ҳ��
	public String findActivityById()
	{
		Activity activity = actService.findMemberByID(act.getActivityId());
		ServletActionContext.getRequest().setAttribute("activity", activity);
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("Activity", "Act_update");
		return "update";
	}
	//����ID��ѯ������ֵʹaction���������ҳ��
	public String findById()
	{
		Activity activity = actService.findMemberByID(act.getActivityId());
		ServletActionContext.getRequest().getSession().setAttribute("activity", activity);
		return "content";
	}
	@Override
	public Activity getModel() {
		return act;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
