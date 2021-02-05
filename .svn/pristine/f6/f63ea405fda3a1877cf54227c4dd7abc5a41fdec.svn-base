package com.tw.action.find;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.tw.dao.OperatingDataDao;
import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.tw.entity.Vehicle;
import com.ze.util.PageHelper;

public class MdtnoAction implements Action{
	private String mdtno;
	private List<Condition>condition=new ArrayList<Condition>();
	private Condition con=new Condition();
	private List<Vehicle>vehicle=new ArrayList<Vehicle>();
	private List<OperatingData>list=new ArrayList<OperatingData>();
	private OperatingDataDao o=new OperatingDataDao();
	private PageHelper page;
	public String findmdtno(){
		condition=o.findmdtno();
		vehicle=o.findVehicle(mdtno);
		return SUCCESS;
	}
	public String findVehicle(){
		vehicle=o.findVehicle(mdtno);
		return SUCCESS;
	}
	//查询终端营运数据
	public String findmdtdata(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("conditionmdt", con);
		String a=(String) session.getAttribute("data");
		list=o.getmdtno(con, page,a);
		Integer t=o.mdtnoData(con,a);
		page.setPageCount(t);
		return SUCCESS;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMdtno() {
		return mdtno;
	}

	public void setMdtno(String mdtno) {
		this.mdtno = mdtno;
	}
	public List<Condition> getCondition() {
		return condition;
	}
	public void setCondition(List<Condition> condition) {
		this.condition = condition;
	}
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public List<OperatingData> getList() {
		return list;
	}
	public void setList(List<OperatingData> list) {
		this.list = list;
	}
	public PageHelper getPage() {
		return page;
	}
	public void setPage(PageHelper page) {
		this.page = page;
	}
	public Condition getCon() {
		return con;
	}
	public void setCon(Condition con) {
		this.con = con;
	}
	
}
