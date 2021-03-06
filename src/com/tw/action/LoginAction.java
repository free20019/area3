package com.tw.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

//import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.userDao;
import com.tw.entity.User;

public class LoginAction implements Action{
	private String username;
	private String password;
	private String message;
	//Logger logger  =  Logger.getLogger(LoginAction. class );
	private userDao userDao = new userDao();
	public String execute() throws Exception {
		return null;
	}
	public String login(){
		//logger.info("开始登陆");
		User user=userDao.getUser(username, password);
		if(user.getDate_view_type()==null){
			//logger.info("登陆失败");
			return ERROR;
		}else{
		HttpSession s=ServletActionContext.getRequest().getSession();
			s.setAttribute("date", user.getDate_view_type());
			ActionContext.getContext().getSession().put("data", user.getDate_view_type());
			ActionContext.getContext().getSession().put("uid", user.getId());
			ActionContext.getContext().getSession().put("station", user.getStation_juri());
			ActionContext.getContext().getSession().put("stationadmin", user.getStation_admin());
			ActionContext.getContext().getSession().put("realname", user.getReal_name());
			ActionContext.getContext().getSession().put("station_id", user.getStation_id());
			return SUCCESS;
		}
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public userDao getUserDao() {
		return userDao;
	}
	public void setUserDao(userDao userDao) {
		this.userDao = userDao;
	}
	
}
