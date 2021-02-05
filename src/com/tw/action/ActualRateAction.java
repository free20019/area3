//package com.tw.action;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.opensymphony.xwork2.Action;
//import com.tw.dao.ActualRateDao;
//import com.tw.entity.LOADONLINE;
//
//public class ActualRateAction implements Action{
//	private List<LOADONLINE>list=new ArrayList<LOADONLINE>();
//	private ActualRateDao a=new ActualRateDao();
//	public String findactualrate(){
//		for (int i = 0; i < 6; i++) {
//			list=a.findactualrate(i);
//		}
//		return SUCCESS;
//	}
//	public String execute() throws Exception {
//		return null;
//	}
//	public List<LOADONLINE> getList() {
//		return list;
//	}
//	public void setList(List<LOADONLINE> list) {
//		this.list = list;
//	}
//	
//}
