package com.tw.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tw.dao.RegularOfflineDao;
import com.tw.entity.Condition;
import com.tw.entity.RegularOffline;

public class RegularOfflineAction extends ActionSupport {

	private static final long serialVersionUID = 14524556325L;
	private RegularOffline re;
	private RegularOfflineDao dao = new RegularOfflineDao();
	private List<RegularOffline> list;
	private Condition con;
	//增加和载入数据
	public String addRegular(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String name = (String) session.getAttribute("realname");
		
		if(re != null){
			re.setOperatingUser(name);
			dao.addRegular(re);
		}
		list = dao.getRegular();
		return SUCCESS;
	}
	//查询数据
	public String regularSearch(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("conditionRegular", con);
		list = dao.getRegularByTime(con);
		return SUCCESS;
	}
	//删除
	public String delone(){
		if(re != null)		
			dao.delone(re);
		list = dao.getRegular();
		return SUCCESS;
	}
	//更新
	public String upone(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String name = (String) session.getAttribute("realname");
		if(re != null){				
			re.setOperatingUser(name);
			dao.updateOne(re);
		}
		list = dao.getRegular();
		return SUCCESS;
	}
	//查找一个
	public String selone(){
		if(re != null)		
			re = dao.selOne(re);
		return SUCCESS;
	}
	
	public RegularOffline getRe() {
		return re;
	}
	public void setRe(RegularOffline re) {
		this.re = re;
	}
	public List<RegularOffline> getList() {
		return list;
	}
	public void setList(List<RegularOffline> list) {
		this.list = list;
	}
	public Condition getCon() {
		return con;
	}
	public void setCon(Condition con) {
		this.con = con;
	}

	
}
