package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.DataExcelDao;
import com.tw.dao.FindDao;
import com.tw.dao.LoadOnlineDAO;
import com.tw.entity.Area;
import com.tw.entity.Condition;
import com.tw.entity.LOADONLINE;
import com.tw.entity.OperatingData;
import com.tw.entity.Vehicle;
import com.tw.entity.Vhic;
import com.ze.util.VhicComparator;


public class FindAction implements Action{
	private Condition con;
	private String time;
	private String quyu;
	private String day;
	private String speed;
	private String i;
	private String id;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String stime;
	private String etime;
	private String compname;
	private String compid;
	private String areaid;
	private List<Vhic>list=new ArrayList<Vhic>();
	private List<String>list1=new ArrayList<String>();
	private List<Vhic>list2=new ArrayList<Vhic>();
	private List<Vhic>list3=new ArrayList<Vhic>();
	private List<Vhic>list4=new ArrayList<Vhic>();
	private List<Vhic>listexcle=new ArrayList<Vhic>();
	private List<Vehicle>vehicle=new ArrayList<Vehicle>();
	private List<Area>area=new ArrayList<Area>();
	private List<OperatingData> data1=new ArrayList<OperatingData>() ;
	FindDao f=new FindDao();
	public String findoffline(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list=f.findvehino(stime,etime,data,areaid,compid);
		return SUCCESS;
	}
	public String findbreakdown(){
//		int a=0;
//		for (int i = 0; i < 48; i++) {
			String data=(String) ActionContext.getContext().getSession().get("data");
//			a=f.findmingxi(quyu, day, speed, data, i);
//			list1.add(i,a+"")  ;
//		}
		list1=f.findmingxi(quyu, day, speed, data);
		return SUCCESS;
	}
	public String findspecinfo(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list2=f.findspecinfo(quyu, time, speed, i,data);
		return SUCCESS;
	}
	public String findareavhic(){
		list3=f.findvhicinfo(time, id);
		return SUCCESS;
	}
	public String findnoline(){
		String data=(String) ActionContext.getContext().getSession().get("data");
		list4=f.findnoline(stime,etime,areaid,compid,data);
		return SUCCESS;
	}
	public String execute() throws Exception {
		HttpSession session= ServletActionContext.getRequest().getSession();
		session.setAttribute("time", time);
		session.setAttribute("id", id);
		return SUCCESS;
	}
	//时间段车辆数量分析
	public String findtimevehi(){
		list2=f.findtimevhic(stime, etime, quyu, compname);
		vehicle=f.findwsxvhic();
		area=f.areafind(quyu);
		return SUCCESS;
	}
	//查询所有公司
	public String findareaname(){
		list2=f.findtaxifenxi(time);
		return SUCCESS;
	}
	//输入3位以上车号查询车辆
	public String findcl3(){
		list2 = f.findcl3(quyu);
		return SUCCESS;
	}
	public String finddpjk(){
		vehicle = f.finddpjk(quyu);
		return SUCCESS;
	}
	public String findmingxiexcle(){
		try {
				String data=(String) ActionContext.getContext().getSession().get("data");
				list=f.findspecinfo(quyu, time, speed, i,data);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			try{
				Date date = new Date();
				String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
				String MV_VERSION="";
				MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
				MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
				MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
				MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
				MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
				MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
				logfile=logfile+"count\\";
				File file1 = new File(logfile);//创建文件夹
				if (!file1.exists()) {
					file1.mkdir();
					//System.err.println(file + " 文件夹已创建！");
				}
				DelAllFile d=new DelAllFile();
				d.delAllFile(logfile);
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				this.xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet("未营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"所在区域"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getBa_name()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getOwn_tel()); 
                    	ws.addCell(label);
					}
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.fanhuei="导出失败!";
			}
		
		return SUCCESS;
	}
	
	public String findofflineexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			list=f.findvehino(stime,etime,data,areaid,compid);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			try{
				Date date = new Date();
				String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
				String MV_VERSION="";
				MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
				MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
				MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
				MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
				MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
				MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
				logfile=logfile+"count\\";
				File file1 = new File(logfile);//创建文件夹
				if (!file1.exists()) {
					file1.mkdir();
					//System.err.println(file + " 文件夹已创建！");
				}
				DelAllFile d=new DelAllFile();
				d.delAllFile(logfile);
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				this.xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet("为营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"汇报时间"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getTime()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getOwn_tel()); 
                    	ws.addCell(label);
					}
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.fanhuei="导出失败!";
			}
		
		return SUCCESS;
	}
	public String findnolineexcle(){
		try {
			String data=(String) ActionContext.getContext().getSession().get("data");
			listexcle=f.findnoline(stime,etime,areaid,compid,data);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			try{
				Date date = new Date();
				String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
				String MV_VERSION="";
				MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
				MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
				MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
				MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
				MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
				MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
				logfile=logfile+"count\\";
				File file1 = new File(logfile);//创建文件夹
				if (!file1.exists()) {
					file1.mkdir();
					//System.err.println(file + " 文件夹已创建！");
				}
				DelAllFile d=new DelAllFile();
				d.delAllFile(logfile);
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				this.xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet("为营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"汇报时间"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < listexcle.size(); i++) {
                    	label = new Label(0,i+1,listexcle.get(i).getComp_id()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,listexcle.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,listexcle.get(i).getTime()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,listexcle.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,listexcle.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,listexcle.get(i).getOwn_tel()); 
                    	ws.addCell(label);
					}
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.fanhuei="导出失败!";
			}
		
		return SUCCESS;
	}
	public String findtimevehiexcle(){
		try {
				String data=(String) ActionContext.getContext().getSession().get("data");
				list2=f.findtimevhic(stime, etime, quyu, compname);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			try{
				Date date = new Date();
				String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
				String MV_VERSION="";
				MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
				MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
				MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
				MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
				MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
				MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
				logfile=logfile+"count\\";
				File file1 = new File(logfile);//创建文件夹
				if (!file1.exists()) {
					file1.mkdir();
					//System.err.println(file + " 文件夹已创建！");
				}
				DelAllFile d=new DelAllFile();
				d.delAllFile(logfile);
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				this.xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet("为营运车辆查询",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"区域"); 
            	ws.addCell(label);
            	label = new Label(2,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(4,0,"司机姓名"); 
            	ws.addCell(label);
            	label = new Label(5,0,"司机电话"); 
            	ws.addCell(label);
                	for (int i = 0; i < list2.size(); i++) {
                    	label = new Label(0,i+1,list2.get(i).getComp_name()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list2.get(i).getBa_name()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list2.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list2.get(i).getVehi_sim()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list2.get(i).getOwn_name()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list2.get(i).getOwn_tel()); 
                    	ws.addCell(label);
					}
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.fanhuei="导出失败!";
			}
		
		return SUCCESS;
	}
	public String mdtnoexcle(){
		DataExcelDao data=new DataExcelDao();
		try {
			data1=data.getmdtnoexcle(con);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			try{
				Date date = new Date();
				String logfile=ServletActionContext.getServletContext().getRealPath("/");
			String dateStr2 = new Timestamp(date.getTime()).toString();
				String MV_VERSION="";
				MV_VERSION=MV_VERSION+dateStr2.substring(0,4);
				MV_VERSION=MV_VERSION+dateStr2.substring(5,7);
				MV_VERSION=MV_VERSION+dateStr2.substring(8,10);
				MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
				MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
				MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
				logfile=logfile+"count\\";
				File file1 = new File(logfile);//创建文件夹
				if (!file1.exists()) {
					file1.mkdir();
					//System.err.println(file + " 文件夹已创建！");
				}
				DelAllFile d=new DelAllFile();
				d.delAllFile(logfile);
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				this.xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet("终端类型营运数据统计",0);//创建Excel工作表 指定名称和位置 
				Label label ;
	        	label = new Label(0,0,"所属公司"); 
	        	ws.addCell(label);
	        	label = new Label(1,0,"所属分公司"); 
	        	ws.addCell(label);
	        	label = new Label(2,0,"车号"); 
	        	ws.addCell(label);
	        	label = new Label(3,0,"金额(元)"); 
	        	ws.addCell(label);
	        	label = new Label(4,0,"次数(次)"); 
	        	ws.addCell(label);
	        	label = new Label(5,0,"计程(公里)"); 
	        	ws.addCell(label);
	        	label = new Label(6,0,"空驶(公里)"); 
	        	ws.addCell(label);
	        	label = new Label(7,0,"总里程(公里)"); 
	        	ws.addCell(label);
	        	label = new Label(8,0,"实载率"); 
	        	ws.addCell(label);
	        	label = new Label(9,0,"载客时间(小时)"); 
	        	ws.addCell(label);
	        	label = new Label(10,0,"载客等候时间(小时)"); 
	        	ws.addCell(label);
	            	for (int i = 0; i < data1.size(); i++) {
	            		double a=data1.get(i).getEmpty()+data1.get(i).getDistance();
	            		BigDecimal b = new BigDecimal(a);
	            		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	                	label = new Label(0,i+1,data1.get(i).getCompany()); 
	                	ws.addCell(label);
	                	label = new Label(1,i+1,data1.get(i).getBranch()); 
	                	ws.addCell(label);
	                	label = new Label(2,i+1,data1.get(i).getVhic()); 
	                	ws.addCell(label);
	                	label = new Label(3,i+1,data1.get(i).getMoney()+""); 
	                	ws.addCell(label);
	                	label = new Label(4,i+1,data1.get(i).getTimes()+""); 
	                	ws.addCell(label);
	                	label = new Label(5,i+1,data1.get(i).getDistance()+""); 
	                	ws.addCell(label);
	                	label = new Label(6,i+1,data1.get(i).getEmpty()+""); 
	                	ws.addCell(label);
	                	label = new Label(7,i+1,f1+""); 
	                	ws.addCell(label);
	                	label = new Label(8,i+1,(int)((data1.get(i).getDistance()/a)*100)+"%"); 
	                	ws.addCell(label);
	                	BigDecimal b1 = new BigDecimal((data1.get(i).getTimeOut()/3600*1.0));
	            		double f2 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	                	label = new Label(9,i+1,f2+""); 
	                	ws.addCell(label);
	                	BigDecimal b2 = new BigDecimal((data1.get(i).getWaitTime()/3600*1.0));
	            		double f3= b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	                	label = new Label(10,i+1,f3+""); 
	                	ws.addCell(label);
					}
                wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.fanhuei="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.fanhuei="导出失败!";
			}
		
		return SUCCESS;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<Vhic> getList() {
		return list;
	}
	public void setList(List<Vhic> list) {
		this.list = list;
	}
	public List<String> getList1() {
		return list1;
	}
	public void setList1(List<String> list1) {
		this.list1 = list1;
	}
	public FindDao getF() {
		return f;
	}
	public void setF(FindDao f) {
		this.f = f;
	}
	public String getQuyu() {
		return quyu;
	}
	public void setQuyu(String quyu) {
		this.quyu = quyu;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public List<Vhic> getList2() {
		return list2;
	}
	public void setList2(List<Vhic> list2) {
		this.list2 = list2;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Vhic> getList3() {
		return list3;
	}
	public void setList3(List<Vhic> list3) {
		this.list3 = list3;
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
	public List<Vhic> getListexcle() {
		return listexcle;
	}
	public void setListexcle(List<Vhic> listexcle) {
		this.listexcle = listexcle;
	}
	public List<Vhic> getList4() {
		return list4;
	}
	public void setList4(List<Vhic> list4) {
		this.list4 = list4;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public Condition getCon() {
		return con;
	}
	public void setCon(Condition con) {
		this.con = con;
	}
	public List<OperatingData> getData1() {
		return data1;
	}
	public void setData1(List<OperatingData> data1) {
		this.data1 = data1;
	}
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public List<Area> getArea() {
		return area;
	}
	public void setArea(List<Area> area) {
		this.area = area;
	}
}
