package com.tw.action.find;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tw.dao.ConditionDao;
import com.tw.entity.Condition;
import com.tw.entity.Groups;

public class GetInfoAction extends ActionSupport{
	private Condition con = new Condition();
	private List<String >baList;
	private List<String>compList = new ArrayList<String>() ;
	private List<String>certList;
	private List<String>cardList;
	private List<Groups>groupList;
	private List<Groups>gcardList;
	private List sList;
	private Groups g = new Groups();
	private ConditionDao dao = new ConditionDao();
	//获取公司
	public String company(){		
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		String perm[]=new String[3];
		if( permission !=null){
			perm = permission.split(",");	
			if("所有公司".equals(perm[0])){
				compList =dao.getCompany();
			}else{
				String comp = perm[0];
				compList.add(comp);
			}
		
		}
		session.setAttribute("compList", compList);
		return SUCCESS;
	}
	//查分公司
	public String branch(){		
		baList = dao.getBranch(con.getCompany());
		return SUCCESS;
	}
	//查资格证号
	public String certNo(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		List<String> list = (List<String>) session.getAttribute("compList");
		
		if(permission != null && list !=null){
			if("--选择分公司--".equals(con.getBranch()) )
				con.setBranch(null);
			if( "--选择公司--".equals(con.getCompany()) )
				con.setCompany(null);
			if( list.size() == 1)	
				con.setCompany(list.get(0));			
			certList = dao.getCertNo(con);
	}	
		return SUCCESS;
	}
	//查车号
	public String cardNo(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		List<String> list = (List<String>) session.getAttribute("compList");
		
		if(permission != null && list !=null){
			if( "--选择分公司--".equals(con.getBranch()) )
				con.setBranch(null);
			if( "--选择公司--".equals(con.getCompany()) )
				con.setCompany(null);
			if( list.size() == 1)	
				con.setCompany(list.get(0));
			cardList = dao.getCardNo(con);	
		}
		return SUCCESS;
	}
	
	//获取群组
	public String groups(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		String perm[]=new String[3];
		if( permission !=null){
			perm = permission.split(",");	
			if("所有公司".equals(perm[0]))
				groupList =dao.getGroups();
		}
			return SUCCESS;
	}
	public String groupCard(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		//String perm[]=new String[3];
		if( permission !=null){
			gcardList = dao.getGroupVhic(g);
		}
		return SUCCESS;
	}
	public String searchNo(){
		System.out.println(sList);
		return SUCCESS;
	}
	public List<String> getBaList() {
		return baList;
	}
	
	public List<String> getCardList() {
		return cardList;
	}

	public void setCardList(List<String> cardList) {
		this.cardList = cardList;
	}

	public void setBaList(List<String> baList) {
		this.baList = baList;
	}


	public List<String> getCompList() {
		return compList;
	}

	public void setCompList(List<String> compList) {
		this.compList = compList;
	}
	public Condition getCon() {
		return con;
	}
	public void setCon(Condition con) {
		this.con = con;
	}
	
	public List<String> getCertList() {
		return certList;
	}
	public void setCertList(List<String> certList) {
		this.certList = certList;
	}
	
	public Groups getG() {
		return g;
	}
	public void setG(Groups g) {
		this.g = g;
	}
	public List<Groups> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Groups> groupList) {
		this.groupList = groupList;
	}
	public List<Groups> getGcardList() {
		return gcardList;
	}
	public void setGcardList(List<Groups> gcardList) {
		this.gcardList = gcardList;
	}
	public List getsList() {
		return sList;
	}
	public void setsList(List sList) {
		this.sList = sList;
	}


	
}
