package com.tw.action;

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
import com.tw.dao.FormDao;
import com.tw.entity.Form;
import com.tw.entity.TaxiInfo;

public class FormAction implements Action{
	private String time;
	private List<Form>list=new ArrayList<Form>();
	private FormDao f=new FormDao();
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String comp_name;
	private String vehi_no;
	private List<TaxiInfo>taxiinfo=new ArrayList<TaxiInfo>();
	public String findmonthform(){
		list=f.findmonthfrom(time);
		return SUCCESS;
	}
	public String findyearform(){
		list=f.findyearfrom(time);
		return SUCCESS;
	}
	public String findseasonsform(){
		list=f.findseasonsfrom(time);
		return SUCCESS;
	}
	public String findyearsform(){
		list=f.findyearsfrom();
		return SUCCESS;
	}
	//日报
	public String finddayform(){
		list=f.finddayform(time);
		return SUCCESS;
	}
	//疑似模子出租信息统计
	public String findtaxiinfo(){
		taxiinfo=f.findTaxiInfo(time, comp_name, vehi_no);
		return SUCCESS;
	}
	public String findtaxiinfoexcle(){
		try {
			taxiinfo=f.findTaxiInfo(time, comp_name, vehi_no);
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

				WritableSheet ws = wwb.createSheet("疑似模子出租信息统计",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"开始时间"); 
            	ws.addCell(label);
            	label = new Label(3,0,"结束时间"); 
            	ws.addCell(label);
            	label = new Label(4,0,"营运次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"地址"); 
            	ws.addCell(label);
            	
                	for (int i = 0; i < taxiinfo.size(); i++) {
                    	label = new Label(0,i+1,taxiinfo.get(i).getComp_name()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,taxiinfo.get(i).getVehi_no()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,taxiinfo.get(i).getBegintime()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,taxiinfo.get(i).getEndtime()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,taxiinfo.get(i).getOperation_num()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,taxiinfo.get(i).getAddress()); 
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
	public String finddayformexcle(){
		try {
			list=f.finddayform(time);
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

				WritableSheet ws = wwb.createSheet("日报表",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"时间"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"参与营运车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"平均周转次数"); 
            	ws.addCell(label);
            	label = new Label(4,0,"平均营收金额"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均实载率"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均重车时间(分)"); 
            	ws.addCell(label);
            	label = new Label(7,0,"平均重车等候时间(分)"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(9,0,"平均空驶里程(公里)"); 
            	ws.addCell(label);
            	
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,"9612"); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_vhicno()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_amount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_actual_loading()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getReproe_revenue_time()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getRepore_wait_time()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getRepore_empty_mileage()); 
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
	public String findmonthformexcle(){
		try {
			list=f.findmonthfrom(time);
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

				WritableSheet ws = wwb.createSheet("月报表",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"日期"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"参与营运车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"周转总次数"); 
            	ws.addCell(label);
            	label = new Label(4,0,"平均周转次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均营运率"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均营收金额"); 
            	ws.addCell(label);
            	label = new Label(7,0,"平均实载率"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均出车时间(时)"); 
            	ws.addCell(label);
            	label = new Label(9,0,"平均重车时间(时)"); 
            	ws.addCell(label);
            	label = new Label(10,0,"平均等候时间(时)"); 
            	ws.addCell(label);
            	label = new Label(11,0,"平均总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(12,0,"平均实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(13,0,"平均空驶里程(公里)"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getRepore_vhicno()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_vhic()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_turnover()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_rade()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getRepore_amount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getRepore_actual_loading()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_car_time()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getReproe_revenue_time()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getRepore_wait_time()); 
                    	ws.addCell(label);
                    	label = new Label(11,i+1,list.get(i).getRepore_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,list.get(i).getRepore_empty_mileage()); 
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
	public String findyearformexcle(){
		try {
			list=f.findyearfrom(time);
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

				WritableSheet ws = wwb.createSheet("年报表(月)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"月份"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"参与营运平均日车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"总周转次数"); 
            	ws.addCell(label);
            	label = new Label(4,0,"平均单车月周转次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均单车日周转次数"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均上路率"); 
            	ws.addCell(label);
            	label = new Label(7,0,"总出车时间(天)"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均出车时间(天)"); 
            	ws.addCell(label);
            	label = new Label(9,0,"营收金额"); 
            	ws.addCell(label);
            	label = new Label(10,0,"平均单车月营收金额"); 
            	ws.addCell(label);
            	label = new Label(11,0,"平均单车日营收金额"); 
            	ws.addCell(label);
            	label = new Label(12,0,"总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(13,0,"平均单车月总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(14,0,"平均单车日总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(15,0,"实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(16,0,"平均单车月实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(17,0,"平均单车日实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(18,0,"空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(19,0,"平均月空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(20,0,"平均日空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(21,0,"平均实载率"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,"9612"); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_pjvhic()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_ypjno()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_pjno()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getRepore_rade()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getRepore_car_time()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_pjcar_time()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getRepore_amount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getRepore_ypjamount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(11,i+1,list.get(i).getRepore_pjamount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getRepore_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,list.get(i).getRepore_ypjmileage()); 
                    	ws.addCell(label);
                    	label = new Label(14,i+1,list.get(i).getRepore_pjmileage()); 
                    	ws.addCell(label);
                    	label = new Label(15,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(16,i+1,list.get(i).getRepore_ypjactual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(17,i+1,list.get(i).getRepore_pjactual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(18,i+1,list.get(i).getRepore_empty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(19,i+1,list.get(i).getRepore_ypjempty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(20,i+1,list.get(i).getRepore_pjempty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(21,i+1,list.get(i).getRepore_actual_loading()); 
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
	public String findseasonsformexcle(){
		try {
			list=f.findseasonsfrom(time);
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

				WritableSheet ws = wwb.createSheet("年报表(季)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"季度"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"参与营运平均日车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"总周转次数"); 
            	ws.addCell(label);
            	label = new Label(4,0,"平均单车季周转次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均单车日周转次数"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均上路率"); 
            	ws.addCell(label);
            	label = new Label(7,0,"总营收金额"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均单车季营收金额"); 
            	ws.addCell(label);
            	label = new Label(9,0,"平均单车日营收金额"); 
            	ws.addCell(label);
            	label = new Label(10,0,"平均实载率"); 
            	ws.addCell(label);
            	label = new Label(11,0,"总出车时间(天)"); 
            	ws.addCell(label);
            	label = new Label(12,0,"平均单车日出车时间(天)"); 
            	ws.addCell(label);
            	label = new Label(13,0,"平均总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(14,0,"平均单车季总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(15,0,"平均单车日总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(16,0,"平均实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(17,0,"平均单车季实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(18,0,"平均单车日实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(19,0,"平均空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(20,0,"平均单车季空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(21,0,"平均单车日空驶里程(公里)"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,"9612"); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_ypjvhic()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_ypjno()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_jpjno()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getRepore_rade()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getRepore_amount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_ypjamount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getRepore_jpjamount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getRepore_actual_loading()); 
                    	ws.addCell(label);
                    	label = new Label(11,i+1,list.get(i).getRepore_car_time()); 
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getRepore_jpjcar_time()); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,list.get(i).getRepore_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(14,i+1,list.get(i).getRepore_ypjmileage()); 
                    	ws.addCell(label);
                    	label = new Label(15,i+1,list.get(i).getRepore_jpjmileage()); 
                    	ws.addCell(label);
                    	label = new Label(16,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(17,i+1,list.get(i).getRepore_ypjactual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(18,i+1,list.get(i).getRepore_jpjactual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(19,i+1,list.get(i).getRepore_empty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(20,i+1,list.get(i).getRepore_ypjempty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(21,i+1,list.get(i).getRepore_jpjempty_mileage()); 
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
	public String findyearsformexcle(){
		try {
			list=f.findyearsfrom();
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

				WritableSheet ws = wwb.createSheet("逐年报表",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"年份"); 
            	ws.addCell(label);
            	label = new Label(1,0,"总车辆数"); 
            	ws.addCell(label);
            	label = new Label(2,0,"参与营运平均日车辆数"); 
            	ws.addCell(label);
            	label = new Label(3,0,"总周转次数"); 
            	ws.addCell(label);
            	label = new Label(4,0,"平均单车月总周转次数"); 
            	ws.addCell(label);
            	label = new Label(5,0,"平均单车日周转次数次数"); 
            	ws.addCell(label);
            	label = new Label(6,0,"平均上路率"); 
            	ws.addCell(label);
            	label = new Label(7,0,"平均营收金额"); 
            	ws.addCell(label);
            	label = new Label(8,0,"平均单车月营收金额"); 
            	ws.addCell(label);
            	label = new Label(9,0,"平均单车日营收金额"); 
            	ws.addCell(label);
            	label = new Label(10,0,"平均实载率"); 
            	ws.addCell(label);
            	label = new Label(11,0,"平均出车时间(天)"); 
            	ws.addCell(label);
            	label = new Label(12,0,"平均单车月出车时间(天)"); 
            	ws.addCell(label);
            	label = new Label(13,0,"平均总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(14,0,"平均单车月总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(15,0,"平均单车日总里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(16,0,"平均实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(17,0,"平均单车月实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(18,0,"平均单车日实载里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(19,0,"平均空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(20,0,"平均单车月空驶里程(公里)"); 
            	ws.addCell(label);
            	label = new Label(21,0,"平均单车日空驶里程(公里)"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getReport_time()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,"9612"); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getRepore_npjvhic()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getRepore_no()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getRepore_ypjno()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getRepore_npjno()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getRepore_rade()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getRepore_amount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getRepore_ypjamount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getRepore_npjamount_revenue()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getRepore_actual_loading()); 
                    	ws.addCell(label);
                    	label = new Label(11,i+1,list.get(i).getRepore_car_time()); 
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getRepore_ypjcar_time()); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,list.get(i).getRepore_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(14,i+1,list.get(i).getRepore_ypjmileage()); 
                    	ws.addCell(label);
                    	label = new Label(15,i+1,list.get(i).getRepore_npjmileage()); 
                    	ws.addCell(label);
                    	label = new Label(16,i+1,list.get(i).getRepore_actual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(17,i+1,list.get(i).getRepore_ypjactual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(18,i+1,list.get(i).getRepore_npjactual_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(19,i+1,list.get(i).getRepore_empty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(20,i+1,list.get(i).getRepore_ypjempty_mileage()); 
                    	ws.addCell(label);
                    	label = new Label(21,i+1,list.get(i).getRepore_npjempty_mileage()); 
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

	public List<Form> getList() {
		return list;
	}

	public void setList(List<Form> list) {
		this.list = list;
	}

	public FormDao getF() {
		return f;
	}

	public void setF(FormDao f) {
		this.f = f;
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
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String compName) {
		comp_name = compName;
	}
	public String getVehi_no() {
		return vehi_no;
	}
	public void setVehi_no(String vehiNo) {
		vehi_no = vehiNo;
	}
	public List<TaxiInfo> getTaxiinfo() {
		return taxiinfo;
	}
	public void setTaxiinfo(List<TaxiInfo> taxiinfo) {
		this.taxiinfo = taxiinfo;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
