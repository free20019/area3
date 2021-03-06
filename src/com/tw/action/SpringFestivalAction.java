package com.tw.action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.tw.dao.SpringFestivalDao;
import com.tw.entity.SpringFestival;
public class SpringFestivalAction implements Action{
	private String time;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private List<SpringFestival>list=new ArrayList<SpringFestival>();
	private SpringFestivalDao s = new SpringFestivalDao();
	//杭州市出租车春运期间分布情况(每小时统计) 查询关键字日期
	public String findHourFB(){
		list=s.findHourFB(time);
		return SUCCESS;
	}
	//杭州市出租车春运期间分布情况(每日统计)
	public String findDayFB(){
		list=s.finddayfx();
		return SUCCESS;
	}
	//重点区域小时服务车次统计(每小时统计)
	public String findzdqyxstj(){
		list=s.findzdqyxstj(time);
		return SUCCESS;
	}
	public String findzdqyxskz(){
		list=s.findzdqyxskz(time);
		return SUCCESS;
	}
	//杭州出租车春运期间分布情况(每日统计)
	public String findzdqyrtj(){
		list=s.findzdqyrtj();
		return SUCCESS;
	}
	public String findzdqyrkz(){
		list=s.findzdqyrkz(time);
		return SUCCESS;
	}
	////重点区域服务车次日总数
	public String findzdqyzs(){
		list=s.findzdqyzs(time);
		return SUCCESS;
	}
	public String findzdqyzsexcle(){
		try {
			list=s.findzdqyzs(time);
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

				WritableSheet ws = wwb.createSheet("重点区域服务车次日总数统计(日总数)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"时间"); 
            	ws.addCell(label);
            	label = new Label(1,0,"火车东站"); 
            	ws.addCell(label);
            	label = new Label(2,0,"机场"); 
            	ws.addCell(label);
            	label = new Label(3,0,"城战"); 
            	ws.addCell(label);
            	label = new Label(4,0,"汽车客运中心站"); 
            	ws.addCell(label);
            	label = new Label(5,0,"汽车北站"); 
            	ws.addCell(label);
            	label = new Label(6,0,"汽车南站"); 
            	ws.addCell(label);
            	label = new Label(7,0,"汽车西站"); 
            	ws.addCell(label);
            	
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getTime()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getDongzhan()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getJichang()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getChengzhan());
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getKeyun()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getBeizhan()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getNanzhan()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getXizhan()); 
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
	public String findHourFBexcle(){
		try {
			list=s.findHourFB(time);
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

				WritableSheet ws = wwb.createSheet("杭州市出租车春运期间分布情况(每小时统计)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"时间"); 
            	ws.addCell(label);
            	label = new Label(1,0,"杭州市"); 
            	ws.addCell(label);
            	label = new Label(2,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(3,0,"大杭州"); 
            	ws.addCell(label);
            	label = new Label(4,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(5,0,"浙江省"); 
            	ws.addCell(label);
            	label = new Label(6,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(7,0,"河南省"); 
            	ws.addCell(label);
            	label = new Label(8,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(9,0,"安徽省"); 
            	ws.addCell(label);
            	label = new Label(10,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(11,0,"江西省"); 
            	ws.addCell(label);
            	label = new Label(12,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(13,0,"车辆总数"); 
            	ws.addCell(label);
            	DecimalFormat df = new DecimalFormat("#");
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getTime().substring(0, 19)); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,df.format(list.get(i).getHangzhou()/100*9612 )+""); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getHangzhou()+"%"); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,df.format(list.get(i).getDahangzhou()/100*9612 )+""); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getDahangzhou()+"%"); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,df.format(list.get(i).getZhejiang()/100*9612 )+""); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getZhejiang()+"%");
                    	ws.addCell(label);
                    	label = new Label(7,i+1,df.format(list.get(i).getHenan()/100*9612 )+""); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getHenan()+"%"); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,df.format(list.get(i).getAnhui()/100*9612 )+""); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getAnhui()+"%");
                    	ws.addCell(label);
                    	label = new Label(11,i+1,df.format(list.get(i).getJiangxi()/100*9612 )+"");
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getJiangxi()+"%"); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,"9612"); 
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
	public String findDayFBexcle(){
		try {
			list=s.finddayfx();
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

				WritableSheet ws = wwb.createSheet("杭州市出租车春运期间分布情况(每日统计)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
				label = new Label(0,0,"时间"); 
            	ws.addCell(label);
            	label = new Label(1,0,"杭州市"); 
            	ws.addCell(label);
            	label = new Label(2,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(3,0,"大杭州"); 
            	ws.addCell(label);
            	label = new Label(4,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(5,0,"浙江省"); 
            	ws.addCell(label);
            	label = new Label(6,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(7,0,"河南省"); 
            	ws.addCell(label);
            	label = new Label(8,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(9,0,"安徽省"); 
            	ws.addCell(label);
            	label = new Label(10,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(11,0,"江西省"); 
            	ws.addCell(label);
            	label = new Label(12,0,"占比"); 
            	ws.addCell(label);
            	label = new Label(13,0,"车辆总数"); 
            	ws.addCell(label);
            	DecimalFormat df = new DecimalFormat("#");
            	for (int i = 0; i < list.size(); i++) {
                	label = new Label(0,i+1,list.get(i).getTime().substring(0, 10)); 
                	ws.addCell(label);
                	label = new Label(1,i+1,df.format(list.get(i).getHangzhou()/100*9612 )+""); 
                	ws.addCell(label);
                	label = new Label(2,i+1,list.get(i).getHangzhou()+"%"); 
                	ws.addCell(label);
                	label = new Label(3,i+1,df.format(list.get(i).getDahangzhou()/100*9612 )+""); 
                	ws.addCell(label);
                	label = new Label(4,i+1,list.get(i).getDahangzhou()+"%"); 
                	ws.addCell(label);
                	label = new Label(5,i+1,df.format(list.get(i).getZhejiang()/100*9612 )+""); 
                	ws.addCell(label);
                	label = new Label(6,i+1,list.get(i).getZhejiang()+"%");
                	ws.addCell(label);
                	label = new Label(7,i+1,df.format(list.get(i).getHenan()/100*9612 )+""); 
                	ws.addCell(label);
                	label = new Label(8,i+1,list.get(i).getHenan()+"%"); 
                	ws.addCell(label);
                	label = new Label(9,i+1,df.format(list.get(i).getAnhui()/100*9612 )+""); 
                	ws.addCell(label);
                	label = new Label(10,i+1,list.get(i).getAnhui()+"%");
                	ws.addCell(label);
                	label = new Label(11,i+1,df.format(list.get(i).getJiangxi()/100*9612 )+"");
                	ws.addCell(label);
                	label = new Label(12,i+1,list.get(i).getJiangxi()+"%"); 
                	ws.addCell(label);
                	label = new Label(13,i+1,"9612"); 
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
	public String findzdqyxstjexcle(){
		try {
			list=s.findzdqyxstj(time);
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

				WritableSheet ws = wwb.createSheet("杭州出租车重点区域小时服务车次统计(每小时统计)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
				label = new Label(0,0,"区域"); 
            	ws.addCell(label);
            	label = new Label(1,0,"00:00"); 
            	ws.addCell(label);
            	label = new Label(2,0,"01:00"); 
            	ws.addCell(label);
            	label = new Label(3,0,"02:00"); 
            	ws.addCell(label);
            	label = new Label(4,0,"03:00"); 
            	ws.addCell(label);
            	label = new Label(5,0,"04:00"); 
            	ws.addCell(label);
            	label = new Label(6,0,"05:00"); 
            	ws.addCell(label);
            	label = new Label(7,0,"06:00"); 
            	ws.addCell(label);
            	label = new Label(8,0,"07:00"); 
            	ws.addCell(label);
            	label = new Label(9,0,"08:00"); 
            	ws.addCell(label);
            	label = new Label(10,0,"09:00"); 
            	ws.addCell(label);
            	label = new Label(11,0,"10:00"); 
            	ws.addCell(label);
            	label = new Label(12,0,"11:00"); 
            	ws.addCell(label);
            	label = new Label(13,0,"12:00"); 
            	ws.addCell(label);
            	label = new Label(14,0,"13:00"); 
            	ws.addCell(label);
            	label = new Label(15,0,"14:00"); 
            	ws.addCell(label);
            	label = new Label(16,0,"15:00"); 
            	ws.addCell(label);
            	label = new Label(17,0,"16:00"); 
            	ws.addCell(label);
            	label = new Label(18,0,"17:00"); 
            	ws.addCell(label);
            	label = new Label(19,0,"18:00"); 
            	ws.addCell(label);
            	label = new Label(20,0,"19:00"); 
            	ws.addCell(label);
            	label = new Label(21,0,"20:00"); 
            	ws.addCell(label);
            	label = new Label(22,0,"21:00"); 
            	ws.addCell(label);
            	label = new Label(23,0,"22:00"); 
            	ws.addCell(label);
            	label = new Label(24,0,"23:00"); 
            	ws.addCell(label);
            	
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getArea()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getA00().equals("&nbsp;")?"":list.get(i).getA00()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getA01().equals("&nbsp;")?"":list.get(i).getA01()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getA02().equals("&nbsp;")?"":list.get(i).getA02());
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getA03().equals("&nbsp;")?"":list.get(i).getA03()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getA04().equals("&nbsp;")?"":list.get(i).getA04()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getA05().equals("&nbsp;")?"":list.get(i).getA05()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getA06().equals("&nbsp;")?"":list.get(i).getA06()); 
                    	ws.addCell(label);
                    	label = new Label(8,i+1,list.get(i).getA07().equals("&nbsp;")?"":list.get(i).getA07()); 
                    	ws.addCell(label);
                    	label = new Label(9,i+1,list.get(i).getA08().equals("&nbsp;")?"":list.get(i).getA08()); 
                    	ws.addCell(label);
                    	label = new Label(10,i+1,list.get(i).getA09().equals("&nbsp;")?"":list.get(i).getA09()); 
                    	ws.addCell(label);
                    	label = new Label(11,i+1,list.get(i).getA10().equals("&nbsp;")?"":list.get(i).getA10()); 
                    	ws.addCell(label);
                    	label = new Label(12,i+1,list.get(i).getA11().equals("&nbsp;")?"":list.get(i).getA11()); 
                    	ws.addCell(label);
                    	label = new Label(13,i+1,list.get(i).getA12().equals("&nbsp;")?"":list.get(i).getA12());
                    	ws.addCell(label);
                    	label = new Label(14,i+1,list.get(i).getA13().equals("&nbsp;")?"":list.get(i).getA13()); 
                    	ws.addCell(label);
                    	label = new Label(15,i+1,list.get(i).getA14().equals("&nbsp;")?"":list.get(i).getA14()); 
                    	ws.addCell(label);
                    	label = new Label(16,i+1,list.get(i).getA15().equals("&nbsp;")?"":list.get(i).getA15()); 
                    	ws.addCell(label);
                    	label = new Label(17,i+1,list.get(i).getA16().equals("&nbsp;")?"":list.get(i).getA16()); 
                    	ws.addCell(label);
                    	label = new Label(18,i+1,list.get(i).getA17().equals("&nbsp;")?"":list.get(i).getA17()); 
                    	ws.addCell(label);
                    	label = new Label(19,i+1,list.get(i).getA18().equals("&nbsp;")?"":list.get(i).getA18()); 
                    	ws.addCell(label);
                    	label = new Label(20,i+1,list.get(i).getA19().equals("&nbsp;")?"":list.get(i).getA19()); 
                    	ws.addCell(label);
                    	label = new Label(21,i+1,list.get(i).getA20().equals("&nbsp;")?"":list.get(i).getA20()); 
                    	ws.addCell(label);
                    	label = new Label(22,i+1,list.get(i).getA21().equals("&nbsp;")?"":list.get(i).getA21()); 
                    	ws.addCell(label);
                    	label = new Label(23,i+1,list.get(i).getA22().equals("&nbsp;")?"":list.get(i).getA22());
                    	ws.addCell(label);
                    	label = new Label(24,i+1,list.get(i).getA23().equals("&nbsp;")?"":list.get(i).getA23()); 
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
	public String findzdqyrtjexcle(){
		try {
			list=s.findzdqyrtj();
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

				WritableSheet ws = wwb.createSheet("杭州出租车重点区域服务车次(每日统计)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"时间"); 
            	ws.addCell(label);
            	label = new Label(1,0,"火车东站"); 
            	ws.addCell(label);
            	label = new Label(2,0,"机场"); 
            	ws.addCell(label);
            	label = new Label(3,0,"城战"); 
            	ws.addCell(label);
            	label = new Label(4,0,"汽车客运中心站"); 
            	ws.addCell(label);
            	label = new Label(5,0,"汽车北站"); 
            	ws.addCell(label);
            	label = new Label(6,0,"汽车南站"); 
            	ws.addCell(label);
            	label = new Label(7,0,"汽车西站"); 
            	ws.addCell(label);
            	
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getTime()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getDongzhan()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getJichang()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getChengzhan());
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getKeyun()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getBeizhan()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getNanzhan()); 
                    	ws.addCell(label);
                    	label = new Label(7,i+1,list.get(i).getXizhan()); 
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
	
	public String findzdqyrkzexcle(){
		try {
			list=s.findzdqyrkz(time);
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
				//设置字体
				 WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				 WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				 cellFormat1.setWrap(true);//设置自动换行
				 cellFormat1.setAlignment(Alignment.CENTRE);  
			        //设置垂直居中;  
			        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);  
				WritableSheet ws = wwb.createSheet("杭州出租车重点区域服务车次(每日统计)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"时间",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(0, 0, 0, 1);
            	label = new Label(1,0,"火车东站",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(1, 0, 3, 0);
            	label = new Label(4,0,"机场",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(4, 0, 6, 0);
            	label = new Label(7,0,"城战",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(7, 0, 9, 0);
            	label = new Label(10,0,"汽车客运中心站",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(10, 0, 12, 0);
            	label = new Label(13,0,"汽车北站",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(13, 0, 15, 0);
            	label = new Label(16,0,"汽车南站",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(16, 0, 18, 0);
            	label = new Label(19,0,"汽车西站",cellFormat1); 
            	ws.addCell(label);
            	ws.mergeCells(19, 0, 21, 0);
            	label = new Label(1,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(2,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(3,1,"上客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(4,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(5,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(6,1,"上客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(7,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(8,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(9,1,"上客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(10,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(11,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(12,1,"上客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(13,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(14,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(15,1,"上客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(16,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(17,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(18,1,"上客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(19,1,"总数",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(20,1,"下客",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(21,1,"上客",cellFormat1); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                		if(list.get(i).getDongzhan()!=null&&list.get(i).getDongzhanz()!=null){
	                    	label = new Label(0,i+2,list.get(i).getTime()); 
	                    	ws.addCell(label);
	                    	label = new Label(1,i+2,(Integer.parseInt(list.get(i).getDongzhan())+Integer.parseInt(list.get(i).getDongzhanz()))+""); 
	                    	ws.addCell(label);
	                    	label = new Label(2,i+2,list.get(i).getDongzhan()); 
	                    	ws.addCell(label);
	                    	label = new Label(3,i+2,list.get(i).getDongzhanz()); 
	                    	ws.addCell(label);
	                    	label = new Label(4,i+2,(Integer.parseInt(list.get(i).getJichang())+Integer.parseInt(list.get(i).getJichangz()))+""); 
	                    	ws.addCell(label);
	                    	label = new Label(5,i+2,list.get(i).getJichang()); 
	                    	ws.addCell(label);
	                    	label = new Label(6,i+2,list.get(i).getJichangz()); 
	                    	ws.addCell(label);
	                    	label = new Label(7,i+2,(Integer.parseInt(list.get(i).getChengzhan())+Integer.parseInt(list.get(i).getChengzhanz()))+"");
	                    	ws.addCell(label);
	                    	label = new Label(8,i+2,list.get(i).getChengzhan());
	                    	ws.addCell(label);
	                    	label = new Label(9,i+2,list.get(i).getChengzhanz());
	                    	ws.addCell(label);
	                    	label = new Label(10,i+2,(Integer.parseInt(list.get(i).getKeyun())+Integer.parseInt(list.get(i).getKeyunz()))+""); 
	                    	ws.addCell(label);
	                    	label = new Label(11,i+2,list.get(i).getKeyun()); 
	                    	ws.addCell(label);
	                    	label = new Label(12,i+2,list.get(i).getKeyunz()); 
	                    	ws.addCell(label);
	                    	label = new Label(13,i+2,(Integer.parseInt(list.get(i).getBeizhan())+Integer.parseInt(list.get(i).getBeizhanz()))+""); 
	                    	ws.addCell(label);
	                    	label = new Label(14,i+2,list.get(i).getBeizhan()); 
	                    	ws.addCell(label);
	                    	label = new Label(15,i+2,list.get(i).getBeizhanz()); 
	                    	ws.addCell(label);
	                    	label = new Label(16,i+2,(Integer.parseInt(list.get(i).getNanzhan())+Integer.parseInt(list.get(i).getNanzhanz()))+""); 
	                    	ws.addCell(label);
	                    	label = new Label(17,i+2,list.get(i).getNanzhan()); 
	                    	ws.addCell(label);
	                    	label = new Label(18,i+2,list.get(i).getNanzhanz()); 
	                    	ws.addCell(label);
	                    	label = new Label(19,i+2,(Integer.parseInt(list.get(i).getXizhan())+Integer.parseInt(list.get(i).getXizhanz()))+""); 
	                    	ws.addCell(label);
	                    	label = new Label(20,i+2,list.get(i).getXizhan()); 
	                    	ws.addCell(label);
	                    	label = new Label(21,i+2,list.get(i).getXizhanz()); 
	                    	ws.addCell(label);
                		}
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
	public String findzdqyxskzexcle(){
		try {
			list=s.findzdqyxskz(time);
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
				//设置字体
				 WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
				 WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				 cellFormat1.setWrap(true);//设置自动换行
				 cellFormat1.setAlignment(Alignment.CENTRE);  
			        //设置垂直居中;  
			        cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);  
				WritableSheet ws = wwb.createSheet("杭州出租车重点区域小时服务车次统计(每小时统计)",0);//创建Excel工作表 指定名称和位置 
				Label label ;
				label = new Label(0,0,"区域",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(1,0,"类别",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(2,0,"00:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(3,0,"01:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(4,0,"02:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(5,0,"03:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(6,0,"04:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(7,0,"05:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(8,0,"06:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(9,0,"07:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(10,0,"08:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(11,0,"09:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(12,0,"10:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(13,0,"11:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(14,0,"12:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(15,0,"13:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(16,0,"14:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(17,0,"15:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(18,0,"16:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(19,0,"17:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(20,0,"18:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(21,0,"19:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(22,0,"20:00",cellFormat1);
            	ws.addCell(label);
            	label = new Label(23,0,"21:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(24,0,"22:00",cellFormat1); 
            	ws.addCell(label);
            	label = new Label(25,0,"23:00",cellFormat1); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i*3+1,list.get(i).getArea(),cellFormat1); 
                    	ws.addCell(label);
                    	ws.mergeCells(0, i*3+1, 0, i*3+3);
                    	label = new Label(1,i*3+1,"总数",cellFormat1); 
                    	ws.addCell(label);
                    	label = new Label(2,i*3+1,((int)Double.parseDouble(list.get(i).getA00())+(int)Double.parseDouble(list.get(i).getB00()))==0?"":((int)Double.parseDouble(list.get(i).getA00())+(int)Double.parseDouble(list.get(i).getB00()))+""); 
                    	ws.addCell(label);
                    	label = new Label(3,i*3+1,((int)Double.parseDouble(list.get(i).getA01())+(int)Double.parseDouble(list.get(i).getB01()))==0?"":((int)Double.parseDouble(list.get(i).getA01())+(int)Double.parseDouble(list.get(i).getB01()))+""); 
                    	ws.addCell(label);
                    	label = new Label(4,i*3+1,((int)Double.parseDouble(list.get(i).getA02())+(int)Double.parseDouble(list.get(i).getB02()))==0?"":((int)Double.parseDouble(list.get(i).getA02())+(int)Double.parseDouble(list.get(i).getB02()))+""); 
                    	ws.addCell(label);
                    	label = new Label(5,i*3+1,((int)Double.parseDouble(list.get(i).getA03())+(int)Double.parseDouble(list.get(i).getB03()))==0?"":((int)Double.parseDouble(list.get(i).getA03())+(int)Double.parseDouble(list.get(i).getB03()))+""); 
                    	ws.addCell(label);
                    	label = new Label(6,i*3+1,((int)Double.parseDouble(list.get(i).getA04())+(int)Double.parseDouble(list.get(i).getB04()))==0?"":((int)Double.parseDouble(list.get(i).getA04())+(int)Double.parseDouble(list.get(i).getB04()))+""); 
                    	ws.addCell(label);
                    	label = new Label(7,i*3+1,((int)Double.parseDouble(list.get(i).getA05())+(int)Double.parseDouble(list.get(i).getB05()))==0?"":((int)Double.parseDouble(list.get(i).getA05())+(int)Double.parseDouble(list.get(i).getB05()))+""); 
                    	ws.addCell(label);
                    	label = new Label(8,i*3+1,((int)Double.parseDouble(list.get(i).getA06())+(int)Double.parseDouble(list.get(i).getB06()))==0?"":((int)Double.parseDouble(list.get(i).getA06())+(int)Double.parseDouble(list.get(i).getB06()))+""); 
                    	ws.addCell(label);
                    	label = new Label(9,i*3+1,((int)Double.parseDouble(list.get(i).getA07())+(int)Double.parseDouble(list.get(i).getB07()))==0?"":((int)Double.parseDouble(list.get(i).getA07())+(int)Double.parseDouble(list.get(i).getB07()))+""); 
                    	ws.addCell(label);
                    	label = new Label(10,i*3+1,((int)Double.parseDouble(list.get(i).getA08())+(int)Double.parseDouble(list.get(i).getB08()))==0?"":((int)Double.parseDouble(list.get(i).getA08())+(int)Double.parseDouble(list.get(i).getB08()))+""); 
                    	ws.addCell(label);
                    	label = new Label(11,i*3+1,((int)Double.parseDouble(list.get(i).getA09())+(int)Double.parseDouble(list.get(i).getB09()))==0?"":((int)Double.parseDouble(list.get(i).getA09())+(int)Double.parseDouble(list.get(i).getB09()))+""); 
                    	ws.addCell(label);
                    	label = new Label(12,i*3+1,((int)Double.parseDouble(list.get(i).getA10())+(int)Double.parseDouble(list.get(i).getB10()))==0?"":((int)Double.parseDouble(list.get(i).getA10())+(int)Double.parseDouble(list.get(i).getB10()))+""); 
                    	ws.addCell(label);
                    	label = new Label(13,i*3+1,((int)Double.parseDouble(list.get(i).getA11())+(int)Double.parseDouble(list.get(i).getB11()))==0?"":((int)Double.parseDouble(list.get(i).getA11())+(int)Double.parseDouble(list.get(i).getB11()))+""); 
                    	ws.addCell(label);
                    	label = new Label(14,i*3+1,((int)Double.parseDouble(list.get(i).getA12())+(int)Double.parseDouble(list.get(i).getB12()))==0?"":((int)Double.parseDouble(list.get(i).getA12())+(int)Double.parseDouble(list.get(i).getB12()))+""); 
                    	ws.addCell(label);
                    	label = new Label(15,i*3+1,((int)Double.parseDouble(list.get(i).getA13())+(int)Double.parseDouble(list.get(i).getB13()))==0?"":((int)Double.parseDouble(list.get(i).getA13())+(int)Double.parseDouble(list.get(i).getB13()))+""); 
                    	ws.addCell(label);
                    	label = new Label(16,i*3+1,((int)Double.parseDouble(list.get(i).getA14())+(int)Double.parseDouble(list.get(i).getB14()))==0?"":((int)Double.parseDouble(list.get(i).getA14())+(int)Double.parseDouble(list.get(i).getB14()))+""); 
                    	ws.addCell(label);
                    	label = new Label(17,i*3+1,((int)Double.parseDouble(list.get(i).getA15())+(int)Double.parseDouble(list.get(i).getB15()))==0?"":((int)Double.parseDouble(list.get(i).getA15())+(int)Double.parseDouble(list.get(i).getB15()))+""); 
                    	ws.addCell(label);
                    	label = new Label(18,i*3+1,((int)Double.parseDouble(list.get(i).getA16())+(int)Double.parseDouble(list.get(i).getB16()))==0?"":((int)Double.parseDouble(list.get(i).getA16())+(int)Double.parseDouble(list.get(i).getB16()))+""); 
                    	ws.addCell(label);
                    	label = new Label(19,i*3+1,((int)Double.parseDouble(list.get(i).getA17())+(int)Double.parseDouble(list.get(i).getB17()))==0?"":((int)Double.parseDouble(list.get(i).getA17())+(int)Double.parseDouble(list.get(i).getB17()))+""); 
                    	ws.addCell(label);
                    	label = new Label(20,i*3+1,((int)Double.parseDouble(list.get(i).getA18())+(int)Double.parseDouble(list.get(i).getB18()))==0?"":((int)Double.parseDouble(list.get(i).getA18())+(int)Double.parseDouble(list.get(i).getB18()))+""); 
                    	ws.addCell(label);
                    	label = new Label(21,i*3+1,((int)Double.parseDouble(list.get(i).getA19())+(int)Double.parseDouble(list.get(i).getB19()))==0?"":((int)Double.parseDouble(list.get(i).getA19())+(int)Double.parseDouble(list.get(i).getB19()))+""); 
                    	ws.addCell(label);
                    	label = new Label(22,i*3+1,((int)Double.parseDouble(list.get(i).getA20())+(int)Double.parseDouble(list.get(i).getB20()))==0?"":((int)Double.parseDouble(list.get(i).getA20())+(int)Double.parseDouble(list.get(i).getB20()))+""); 
                    	ws.addCell(label);
                    	label = new Label(23,i*3+1,((int)Double.parseDouble(list.get(i).getA21())+(int)Double.parseDouble(list.get(i).getB21()))==0?"":((int)Double.parseDouble(list.get(i).getA21())+(int)Double.parseDouble(list.get(i).getB21()))+""); 
                    	ws.addCell(label);
                    	label = new Label(24,i*3+1,((int)Double.parseDouble(list.get(i).getA22())+(int)Double.parseDouble(list.get(i).getB22()))==0?"":((int)Double.parseDouble(list.get(i).getA22())+(int)Double.parseDouble(list.get(i).getB22()))+""); 
                    	ws.addCell(label);
                    	label = new Label(25,i*3+1,((int)Double.parseDouble(list.get(i).getA23())+(int)Double.parseDouble(list.get(i).getB23()))==0?"":((int)Double.parseDouble(list.get(i).getA23())+(int)Double.parseDouble(list.get(i).getB23()))+""); 
                    	ws.addCell(label);
                    	
                    	label = new Label(1,i*3+2,"下客",cellFormat1); 
                    	ws.addCell(label);
                    	label = new Label(2,i*3+2,(list.get(i).getA00().equals("0.01")?"":list.get(i).getA00())+""); 
                    	ws.addCell(label);
                    	label = new Label(3,i*3+2,(list.get(i).getA01().equals("0.01")?"":list.get(i).getA01())+""); 
                    	ws.addCell(label);
                    	label = new Label(4,i*3+2,(list.get(i).getA02().equals("0.01")?"":list.get(i).getA02())+""); 
                    	ws.addCell(label);
                    	label = new Label(5,i*3+2,(list.get(i).getA03().equals("0.01")?"":list.get(i).getA03())+""); 
                    	ws.addCell(label);
                    	label = new Label(6,i*3+2,(list.get(i).getA04().equals("0.01")?"":list.get(i).getA04())+""); 
                    	ws.addCell(label);
                    	label = new Label(7,i*3+2,(list.get(i).getA05().equals("0.01")?"":list.get(i).getA05())+""); 
                    	ws.addCell(label);
                    	label = new Label(8,i*3+2,(list.get(i).getA06().equals("0.01")?"":list.get(i).getA06())+""); 
                    	ws.addCell(label);
                    	label = new Label(9,i*3+2,(list.get(i).getA07().equals("0.01")?"":list.get(i).getA07())+""); 
                    	ws.addCell(label);
                    	label = new Label(10,i*3+2,(list.get(i).getA08().equals("0.01")?"":list.get(i).getA08())+""); 
                    	ws.addCell(label);
                    	label = new Label(11,i*3+2,(list.get(i).getA09().equals("0.01")?"":list.get(i).getA09())+""); 
                    	ws.addCell(label);
                    	label = new Label(12,i*3+2,(list.get(i).getA10().equals("0.01")?"":list.get(i).getA10())+""); 
                    	ws.addCell(label);
                    	label = new Label(13,i*3+2,(list.get(i).getA11().equals("0.01")?"":list.get(i).getA11())+""); 
                    	ws.addCell(label);
                    	label = new Label(14,i*3+2,(list.get(i).getA12().equals("0.01")?"":list.get(i).getA12())+""); 
                    	ws.addCell(label);
                    	label = new Label(15,i*3+2,(list.get(i).getA13().equals("0.01")?"":list.get(i).getA13())+""); 
                    	ws.addCell(label);
                    	label = new Label(16,i*3+2,(list.get(i).getA14().equals("0.01")?"":list.get(i).getA14())+""); 
                    	ws.addCell(label);
                    	label = new Label(17,i*3+2,(list.get(i).getA15().equals("0.01")?"":list.get(i).getA15())+""); 
                    	ws.addCell(label);
                    	label = new Label(18,i*3+2,(list.get(i).getA16().equals("0.01")?"":list.get(i).getA16())+""); 
                    	ws.addCell(label);
                    	label = new Label(19,i*3+2,(list.get(i).getA17().equals("0.01")?"":list.get(i).getA17())+""); 
                    	ws.addCell(label);
                    	label = new Label(20,i*3+2,(list.get(i).getA18().equals("0.01")?"":list.get(i).getA18())+""); 
                    	ws.addCell(label);
                    	label = new Label(21,i*3+2,(list.get(i).getA19().equals("0.01")?"":list.get(i).getA19())+""); 
                    	ws.addCell(label);
                    	label = new Label(22,i*3+2,(list.get(i).getA20().equals("0.01")?"":list.get(i).getA20())+""); 
                    	ws.addCell(label);
                    	label = new Label(23,i*3+2,(list.get(i).getA21().equals("0.01")?"":list.get(i).getA21())+""); 
                    	ws.addCell(label);
                    	label = new Label(24,i*3+2,(list.get(i).getA22().equals("0.01")?"":list.get(i).getA22())+""); 
                    	ws.addCell(label);
                    	label = new Label(26,i*3+2,(list.get(i).getA23().equals("0.01")?"":list.get(i).getA23())+""); 
                    	ws.addCell(label);
                    	
                    	
                    	label = new Label(1,i*3+3,"上客",cellFormat1); 
                    	ws.addCell(label);
                    	label = new Label(2,i*3+3,(list.get(i).getB00().equals("0.01")?"":list.get(i).getB00())+""); 
                    	ws.addCell(label);
                    	label = new Label(3,i*3+3,(list.get(i).getB01().equals("0.01")?"":list.get(i).getB01())+""); 
                    	ws.addCell(label);
                    	label = new Label(4,i*3+3,(list.get(i).getB02().equals("0.01")?"":list.get(i).getB02())+""); 
                    	ws.addCell(label);
                    	label = new Label(5,i*3+3,(list.get(i).getB03().equals("0.01")?"":list.get(i).getB03())+""); 
                    	ws.addCell(label);
                    	label = new Label(6,i*3+3,(list.get(i).getB04().equals("0.01")?"":list.get(i).getB04())+""); 
                    	ws.addCell(label);
                    	label = new Label(7,i*3+3,(list.get(i).getB05().equals("0.01")?"":list.get(i).getB05())+""); 
                    	ws.addCell(label);
                    	label = new Label(8,i*3+3,(list.get(i).getB06().equals("0.01")?"":list.get(i).getB06())+""); 
                    	ws.addCell(label);
                    	label = new Label(9,i*3+3,(list.get(i).getB07().equals("0.01")?"":list.get(i).getB07())+""); 
                    	ws.addCell(label);
                    	label = new Label(10,i*3+3,(list.get(i).getB08().equals("0.01")?"":list.get(i).getB08())+""); 
                    	ws.addCell(label);
                    	label = new Label(11,i*3+3,(list.get(i).getB09().equals("0.01")?"":list.get(i).getB09())+""); 
                    	ws.addCell(label);
                    	label = new Label(12,i*3+3,(list.get(i).getB10().equals("0.01")?"":list.get(i).getB10())+""); 
                    	ws.addCell(label);
                    	label = new Label(13,i*3+3,(list.get(i).getB11().equals("0.01")?"":list.get(i).getB11())+""); 
                    	ws.addCell(label);
                    	label = new Label(14,i*3+3,(list.get(i).getB12().equals("0.01")?"":list.get(i).getB12())+""); 
                    	ws.addCell(label);
                    	label = new Label(15,i*3+3,(list.get(i).getB13().equals("0.01")?"":list.get(i).getB13())+""); 
                    	ws.addCell(label);
                    	label = new Label(16,i*3+3,(list.get(i).getB14().equals("0.01")?"":list.get(i).getB14())+""); 
                    	ws.addCell(label);
                    	label = new Label(17,i*3+3,(list.get(i).getB15().equals("0.01")?"":list.get(i).getB15())+""); 
                    	ws.addCell(label);
                    	label = new Label(18,i*3+3,(list.get(i).getB16().equals("0.01")?"":list.get(i).getB16())+""); 
                    	ws.addCell(label);
                    	label = new Label(19,i*3+3,(list.get(i).getB17().equals("0.01")?"":list.get(i).getB17())+""); 
                    	ws.addCell(label);
                    	label = new Label(20,i*3+3,(list.get(i).getB18().equals("0.01")?"":list.get(i).getB18())+""); 
                    	ws.addCell(label);
                    	label = new Label(21,i*3+3,(list.get(i).getB19().equals("0.01")?"":list.get(i).getB19())+""); 
                    	ws.addCell(label);
                    	label = new Label(22,i*3+3,(list.get(i).getB20().equals("0.01")?"":list.get(i).getB20())+""); 
                    	ws.addCell(label);
                    	label = new Label(23,i*3+3,(list.get(i).getB21().equals("0.01")?"":list.get(i).getB21())+""); 
                    	ws.addCell(label);
                    	label = new Label(24,i*3+3,(list.get(i).getB22().equals("0.01")?"":list.get(i).getB22())+""); 
                    	ws.addCell(label);
                    	label = new Label(26,i*3+3,(list.get(i).getB23().equals("0.01")?"":list.get(i).getB23())+""); 
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
	public List<SpringFestival> getList() {
		return list;
	}
	public void setList(List<SpringFestival> list) {
		this.list = list;
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
	public String execute() throws Exception {
		return null;
	}
}
