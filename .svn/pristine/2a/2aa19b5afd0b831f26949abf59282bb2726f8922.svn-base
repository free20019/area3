package com.tw.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.tw.dao.PublicDao;
import com.tw.entity.Company;

public class PublicAction implements Action{
	private List<Company>list=new ArrayList<Company>();
	private PublicDao p=new PublicDao();
	//根据权限查询公司
	public String findqxba(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String a=(String) session.getAttribute("data");
		list=p.findqxba(a);
		return SUCCESS;
	}
	public String execute() throws Exception {
		return null;
	}
	public List<Company> getList() {
		return list;
	}
	public void setList(List<Company> list) {
		this.list = list;
	}
}
