package com.tw.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.RealtimeDao;
import com.tw.entity.Company;
import com.tw.entity.TreeNode;
import com.tw.entity.Vehicle;
import com.tw.entity.Vehijson;
import com.ze.util.GetStatus;

public class TreeAction implements Action{

	private String id;
	private String data;
	private String zdata;
	private String keyword;
	private RealtimeDao rtDao = new RealtimeDao();
	private JSONArray json;
	private List<TreeNode> list=new ArrayList<TreeNode>();  
	private GetStatus gs = new GetStatus();
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String cartree(){
		String comp=(String) ActionContext.getContext().getSession().get("data");
		list=new ArrayList<TreeNode>();  
		if(comp.split(",")[0].equals("所有公司")){
			List<Company> bas =rtDao.findbaname(comp);
//			int anum=0;
//			int bnum=0;
			for(int i=0;i<bas.size();i++){
				String a = bas.get(i).getId();
				TreeNode node1=null;
				//if(a.equals("10001")||a.equals("10002")||a.equals("10003")||a.equals("10004")||a.equals("10005")||a.equals("10006")||a.equals("10007")){
				//	anum++;
				//	node1=new TreeNode(Integer.parseInt(bas.get(i).getId()),0,bas.get(i).getName(),false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
				//}else{
				//	bnum++;
					node1=new TreeNode(Integer.parseInt(bas.get(i).getId()),-50,bas.get(i).getName(),false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
				//}
				list.add(node1);
			}
			
			List<Company> comps = rtDao.findcompname1(comp);
			for(int j=0;j<comps.size();j++){
				TreeNode node2=new TreeNode(Integer.parseInt(comps.get(j).getId()),Integer.parseInt(comps.get(j).getBaid()),comps.get(j).getName(),false,false,"css//zTreeStyle//img//diy//1_close.png",true);  
				list.add(node2);
			}
//			TreeNode no1=new TreeNode(0,-10,"区域出租("+anum+")",false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
//			TreeNode no2=new TreeNode(1,-10,"公司出租("+bnum+")",false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
//			list.add(no1);
//			list.add(no2);
			//工作组
			List<Company> groups =rtDao.findgroup(comp);
			for(int i=0;i<groups.size();i++){
				TreeNode node1=new TreeNode(Integer.parseInt(groups.get(i).getId()),-2,groups.get(i).getName(),false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
				list.add(node1);
			}
			TreeNode nog=new TreeNode(-2,-10,"工作组("+groups.size()+")",false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
			list.add(nog);
			
		}else{
			List<Company> bas =rtDao.findbaname(comp);
			for(int i=0;i<bas.size();i++){
				TreeNode node1=new TreeNode(Integer.parseInt(bas.get(i).getId()),0,bas.get(i).getName(),false,false,"css//zTreeStyle//img//diy//1_open.png",true);  
				list.add(node1);
			//	long s = new Date().getTime();
				List<Company> comps = rtDao.findcompname(comp,bas.get(i).getId());
			//	long e = new Date().getTime();
			//	System.out.println("haoshi "+(e-s));
				for(int j=0;j<comps.size();j++){
					TreeNode node2=new TreeNode(Integer.parseInt(comps.get(j).getId()),Integer.parseInt(bas.get(i).getId()),comps.get(j).getName(),false,false,"css//zTreeStyle//img//diy//1_close.png",true);  
					list.add(node2);
				}
			}
		}
		data = JSONArray.fromObject(list).toString();  
		return SUCCESS;
	}

	public String findcars(){
		String comp=(String) ActionContext.getContext().getSession().get("data");
		List<Vehijson> vjs = gs.getplace();
		 list=new ArrayList<TreeNode>();  
		String id = ServletActionContext.getRequest().getParameter("id");
		//keyword = ServletActionContext.getRequest().getParameter("keyword");
		keyword = "";
		List<Vehicle> vehiss = rtDao.findgjcl1(comp,id,keyword);
		List<Vehicle> groupvehis = rtDao.findgroupcar(comp,id,keyword);
		List<Vehicle> vehis = new ArrayList<Vehicle>();
		if(vehiss.size()==0){
			vehis.addAll(groupvehis);
		}else if(groupvehis.size()==0){
			vehis.addAll(vehiss);
		}
		for(int k=0;k<vehis.size();k++){
			Vehicle va = gs.checkvehi(vehis.get(k), vjs);
			String path="";
			if(va.getLati()==null||"".equals(va.getLati())){
				path = "img//car//d.png";
			}else{
				if(va.getOnoffstate().equals("1")){
					if(va.getCarStatus().equals("0")){
						path = "img//car//c.png";
					}else{
						path = "img//car//h.png";
					}
				}else{
					path = "img//car//d.png";
				}
			}
			TreeNode node3=new TreeNode(k,Integer.parseInt(id),vehis.get(k).getVehino(),false,false,path,false); 
			list.add(node3);
		}
		
		zdata = JSONArray.fromObject(list).toString();  
		return SUCCESS;
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

	public List<TreeNode> getList() {
		return list;
	}

	public void setList(List<TreeNode> list) {
		this.list = list;
	}

	public String getZdata() {
		return zdata;
	}

	public void setZdata(String zdata) {
		this.zdata = zdata;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}

