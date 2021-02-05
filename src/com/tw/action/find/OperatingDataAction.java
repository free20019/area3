package com.tw.action.find;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tw.action.DelAllFile;
import com.tw.dao.DataExcelDao;
import com.tw.dao.OperatingDataDao;
import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.ze.util.PageHelper;
/**
 * 
 * @author sven.zhang
 *	营运交易查询
 *	群组交易查询
 *	根据车号获取统计数据
 *	根据公司获取统计数据
 *	根据资格证号获取统计数据
 *	根据群组获取统计数据
 */

public class OperatingDataAction extends ActionSupport {
	private static final long serialVersionUID = 71442418916410272L;
	private Condition con;
	private List<OperatingData> list ;
	private PageHelper page;
	private List<Integer>numList =new ArrayList<Integer>();
	private OperatingDataDao dao =new OperatingDataDao();
	private DataExcelDao pao = new DataExcelDao();
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private DataExcelDao data=new DataExcelDao();
	//营运交易查询
	public String business(){	
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition4", con);
		Integer t= dao.getNumbyBusiness(con);
		page.setPageCount(t);
		list = dao.getBusiness(con, page);
		return SUCCESS;
	}
	//群组交易查询
	public String groupsel(){	
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition5", con);
		List<OperatingData> list1 = pao.getGroupsel(con);
		page.setPageCount(list1.size());
		list = dao.getGroupsel(con, page);
		return SUCCESS;
	}
	//根据车号获取统计数据
	public String vehicle(){		
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition", con);
		Integer t= dao.geData(con);
		page.setPageCount(t);
		list =dao.getAll(con, page);	
		return SUCCESS;
	}
	//根据公司获取统计数据
	public String company(){	
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition1", con);
		Integer t= dao.getNumByCompany(con);
		page.setPageCount(t);
		list =dao.getDataByCompany(con, page);	
		return SUCCESS;
	}
	//根据资格证号获取统计数据
	public String certNo(){		
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition2", con);
		Integer t= dao.getNumByCertNo(con);
		page.setPageCount(t);
		list =dao.getDataByCetNo(con, page);	
		return SUCCESS;
	}
	//根据群组获取统计数据
	public String group(){		
		getUtil();	
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("condition3", con);
		list = dao.getDataByGroup(con, page);
		Integer t = dao.getNumByGroup(con);
		page.setPageCount(t);
		return SUCCESS;
	}
	//过滤前端传来的条件
	public void getUtil(){
		if("--选择分公司--".equals(con.getBranch()))
			con.setBranch(null);		
		if("--选择公司--".equals(con.getCompany()))
			con.setCompany(null);
		if("0".equals(con.getCardNo()))
			con.setCardNo(null);
		if("0".equals(con.getCertNo())||con.getCertNo() =="")
			con.setCertNo(null);
		if("0".equals(con.getGroup()))
			con.setGroup(null);
	}
	public void excel(){
		
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
public List<Integer> getNumList() {
	return numList;
}
public void setNumList(List<Integer> numList) {
	this.numList = numList;
}
public OperatingDataDao getDao() {
	return dao;
}
public void setDao(OperatingDataDao dao) {
	this.dao = dao;
}
public DataExcelDao getPao() {
	return pao;
}
public void setPao(DataExcelDao pao) {
	this.pao = pao;
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
public static long getSerialversionuid() {
	return serialVersionUID;
}


}
