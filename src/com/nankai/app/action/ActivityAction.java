
package com.nankai.app.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.nankai.app.domain.Activity;
import com.nankai.app.domain.Organization;
import com.nankai.app.service.ActService;
import com.nankai.app.vo.ActPage;
import com.nankai.app.vo.OrgPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ActivityAction extends ActionSupport implements ModelDriven<Activity>{
	private Activity act = new Activity();
	private ActService actService;
	//分页需要使用的参数
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
	//向数据库Activity表添加记录
	public String add()
	{
		actService.add(act);
		return SUCCESS;
	}
	//显示数据库中所有记录
	public String showAll()
	{
		ActPage actPage = actService.findAll(currentPage, pageSize);
		ServletActionContext.getRequest().getSession().setAttribute("actPage", actPage);
		return SUCCESS;
	}
	public String showAllForManager()
	{
		ActPage actPage = actService.findAll(currentPage, pageSize);
		ServletActionContext.getRequest().setAttribute("actPage", actPage);
		return "manager";
	}
	//删除数据库中记录
	public String delete(){
		Activity activity=actService.findMemberByID(act.getActivityId());
		actService.delete(activity);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("activity", "delete");
	    return "manager";
	}
	//更新数据库记录
	public String update(){
    	actService.update(act);
    	return "manager";
    }
	//根据ID查询，返回跳转到修改页面
	public String findActivityById()
	{
		Activity activity = actService.findMemberByID(act.getActivityId());
		ServletActionContext.getRequest().setAttribute("activity", activity);
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("Activity", "Act_update");
		return "update";
	}
	//根据ID查询，返回值使action调到活动详情页面
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

}
