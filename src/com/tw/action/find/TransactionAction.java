package com.tw.action.find;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.TransactionDao;
import com.tw.entity.TB_Citizen_2014;
import com.tw.entity.TbVehicle;


public class TransactionAction implements Action{
	private String starttime;
	private String endtime;
	private String state;
	private String vhic;
	private String CUSTOMERS="";
	private int currentPage=1;
	private int pagesize=20;
	private int result=0;
	private int max=currentPage*pagesize;
	private int min=(currentPage-1)*pagesize+1;
	private int ye=0;
	private List<TB_Citizen_2014> list;
	private List<TB_Citizen_2014>businesslist=new ArrayList<TB_Citizen_2014>();
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String ba_name;
	private String comp_name;
	private List<TB_Citizen_2014>list1=new ArrayList<TB_Citizen_2014>();
	private List<TbVehicle>list2=new ArrayList<TbVehicle>();
	private TransactionDao t=new TransactionDao();
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String findoper(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		String uid=(String) ActionContext.getContext().getSession().get("u_id");
		try {
			result=t.findOpertotalpage(starttime, endtime, vhic,ba_name,comp_name,data,uid);
		} catch (Exception e1) {
			e1.printStackTrace();
			return"error";
		}
		if(result%pagesize==0){
			this.ye=result/pagesize;
			this.max=currentPage*pagesize;
			this.min=(currentPage-1)*pagesize+1;
			if(max>result){
				max=result;
			}
		}else{
			this.ye=result/pagesize+1;
			this.max=currentPage*pagesize;
			this.min=(currentPage-1)*pagesize+1;
			if(max>result){
				max=result;
			}
		}
		if(currentPage<=0){
			currentPage=1;
			this.max=currentPage*pagesize;
			this.min=(currentPage-1)*pagesize+1;
			if(max>result){
				max=result;
			}
		}
		if(currentPage>ye){
			currentPage=ye;
			this.max=currentPage*pagesize;
			this.min=(currentPage-1)*pagesize+1;
			if(max>result){
				max=result;
			}
		}
		if(min<0){
			min=0;
		}
		list=new ArrayList<TB_Citizen_2014>();
		try {
			list=t.findOperCash(starttime, endtime, vhic, ba_name, comp_name,data,uid, max, min);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return SUCCESS;
}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVhic() {
		return vhic;
	}
	public void setVhic(String vhic) {
		this.vhic = vhic;
	}
	public String getCUSTOMERS() {
		return CUSTOMERS;
	}
	public void setCUSTOMERS(String cUSTOMERS) {
		CUSTOMERS = cUSTOMERS;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getYe() {
		return ye;
	}
	public void setYe(int ye) {
		this.ye = ye;
	}
	public List<TB_Citizen_2014> getList() {
		return list;
	}
	public void setList(List<TB_Citizen_2014> list) {
		this.list = list;
	}
	public List<TB_Citizen_2014> getBusinesslist() {
		return businesslist;
	}
	public void setBusinesslist(List<TB_Citizen_2014> businesslist) {
		this.businesslist = businesslist;
	}
	public String getXlsfilename() {
		return xlsfilename;
	}
	public void setXlsfilename(String xlsfilename) {
		this.xlsfilename = xlsfilename;
	}
	public String getFanhuei() {
		return fanhuei;
	}
	public void setFanhuei(String fanhuei) {
		this.fanhuei = fanhuei;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBa_name() {
		return ba_name;
	}
	public void setBa_name(String baName) {
		ba_name = baName;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String compName) {
		comp_name = compName;
	}
	public TransactionDao getT() {
		return t;
	}
	public void setT(TransactionDao t) {
		this.t = t;
	}
	public List<TB_Citizen_2014> getList1() {
		return list1;
	}
	public void setList1(List<TB_Citizen_2014> list1) {
		this.list1 = list1;
	}
	public List<TbVehicle> getList2() {
		return list2;
	}
	public void setList2(List<TbVehicle> list2) {
		this.list2 = list2;
	}
	
}
