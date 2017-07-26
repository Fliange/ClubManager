package com.nankai.app.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nankai.app.domain.Material;
import com.nankai.app.service.MaterialService;
import com.nankai.app.service.impl.MaterialServiceImpl;
import com.nankai.app.util.HibernateUtil;
import com.nankai.app.vo.MaterialPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MaterialAction extends ActionSupport implements ModelDriven<Material>{
	private int currentPage=1;
	private int pageSize=5;
	private Material material = new Material();
	private MaterialService materialService;
	
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
	
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}
	public String add(){
		materialService.add(material);
		return "material_search";
	}
	public String update(){
		materialService.update(material);
		return "material_search";
	}
	public String findById(){
		Material material1 = materialService.findMaterialById(material.getMaterialId());
		ServletActionContext.getRequest().setAttribute("material", material1);
		return "findById";
	}
	public String delete(){
		//materialService
		materialService.delete(material.getMaterialId());
		return "material_search";
	}
	public String search(){
		
		MaterialPage materialPage = materialService.findAll(currentPage, pageSize);
		ServletActionContext.getRequest().setAttribute("materialPage", materialPage);
		return SUCCESS;
		
	}
	@Override
	public Material getModel() {
		// TODO Auto-generated method stub
		return material;
	}

}
