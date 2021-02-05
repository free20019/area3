package com.tw.action.find;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.action.DelAllFile;
import com.tw.dao.TransactionDao;
import com.tw.entity.TB_Citizen_2014;

public class FindYYAction implements Action {
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
	private String ba_name;
	private String comp_name;
	private List<TB_Citizen_2014>list;
	private List<TB_Citizen_2014>businesslist=new ArrayList<TB_Citizen_2014>();
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private TransactionDao t=new TransactionDao();
	public String execute(){
		return "success";
	}
	public String findyy(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		String uid=(String) ActionContext.getContext().getSession().get("u_id");
				try {
					result=t.findYYtotalpage(starttime, endtime, vhic, ba_name, comp_name,data,uid);
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
					list=t.findYYLatestTime(starttime, endtime, vhic, ba_name, comp_name, data,uid,max, min);
				} catch (Exception e) {
					e.printStackTrace();
				}
			return SUCCESS;
	}
//	public String business_select_excel() {
//		try {
//			businesslist=t.findYYLatestTimeexcle(starttime, endtime, state, vhic);
//		} catch (DaoException e1) {
//			e1.printStackTrace();
//		}
//		if(businesslist.size()>0){
//			try{
//				Date date = new Date();
//				String logfile=ServletActionContext.getServletContext().getRealPath("/");
//			String dateStr2 = new Timestamp(date.getTime()).toString();
//				String MV_VERSION="";
//				MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
//				MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
//				MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
//				MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
//				MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
//				MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
//				logfile=logfile+"count\\";
//				File file1 = new File(logfile);//创建文件夹
//				if (!file1.exists()) {
//					file1.mkdir();
//					//System.err.println(file + " 文件夹已创建！");
//				}
//				DelAllFile d=new DelAllFile();
//				d.delAllFile(logfile);
//				File file= new File(logfile+""+MV_VERSION+".xls"); 
//				this.xlsfilename=""+MV_VERSION+".xls";
//				//this.xlsfilepath="//evaluation//"+xlsfilename;
//				file.createNewFile(); //建立要输出的文件 
//
//				OutputStream os = new FileOutputStream(file);//使用文件缓冲 
//
//				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 
//
//				WritableSheet ws = wwb.createSheet("营运交易最新时间查询",0);//创建Excel工作表 指定名称和位置 
//				Label label ;
//            	label = new Label(0,0,"车牌号"); 
//            	ws.addCell(label);
//            	label = new Label(1,0,"最新交易时间"); 
//            	ws.addCell(label);
//            	label = new Label(2,0,"接受时间"); 
//            	ws.addCell(label);
//            	
//                for(int ii1=0;ii1<businesslist.size();ii1++){
//                	TB_Citizen_2014 tbBusiness2=new TB_Citizen_2014();
//                	tbBusiness2=businesslist.get(ii1);
//                	label = new Label(0,ii1+1,tbBusiness2.getVhic()); 
//                	ws.addCell(label);
//                	label = new Label(1,ii1+1,tbBusiness2.getXiache().toString()); 
//                	ws.addCell(label);
//                	label = new Label(2,ii1+1,tbBusiness2.getZhongxin().toString()); 
//                	ws.addCell(label);
//                
//                }
//      
//                wwb.write();//写入文件 
//				wwb.close();
//				os.close();
//				this.fanhuei="成功导成Excel!";
//				this.action="filedownload.action?inputPath="+this.xlsfilename;
//			}catch(Exception e){
//				this.fanhuei="导出失败!";
//			}
//		}else{
//			this.fanhuei="导出失败!";
//		}
//		
//		return SUCCESS;
//	}
	
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
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public List<TB_Citizen_2014> getList() {
		return list;
	}
	public void setList(List<TB_Citizen_2014> list) {
		this.list = list;
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
	
	
}
