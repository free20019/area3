package com.tw.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.tw.dao.HalfMonthDao;
import com.tw.dao.LoadOnlineDAO;
import com.tw.dao.YingYunDao;
import com.tw.entity.HalfMonth;
import com.tw.entity.LOADONLINE;

public class YingYunAction implements Action{
	private String stime;
	private String etime;
	private List<LOADONLINE>list=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>list2=new ArrayList<LOADONLINE>();
	private YingYunDao y=new YingYunDao();
	private HalfMonthDao h=new HalfMonthDao();
	private HalfMonth halfMonth=new HalfMonth();
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String time;
	private String shuju[]=null;
	private LOADONLINE loadonline=new LOADONLINE();
	private List<LOADONLINE>listexcle=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>list1excle=new ArrayList<LOADONLINE>();
	public String findyingyun(){
		List<String []>list=new ArrayList<String[]>();
		for (int i = 0; i < 6; i++) {
			if (null == time ||  time.equals("")) {
				Date d=new Date();
				SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
				time=s.format(d);
			}
			shuju=y.findyingyun(time,i);
			list.add(i, shuju);
		}
		loadonline.setYy1(list);
		list1=y.findaverage(time);
		halfMonth=h.findyingyunmaxmin(time);
		return SUCCESS;
	}
	
	public String findyingyunexcle(){
		YingYunDao y=new YingYunDao();
		try {
			List<String []>list=new ArrayList<String[]>();
			for (int i = 0; i < 6; i++) {
				if (null == time ||  time.equals("")) {
					Date d=new Date();
					SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
					time=s.format(d);
				}
				shuju=y.findyingyun(time,i);
				list.add(i, shuju);
			}
			loadonline.setYy1(list);
			list1=y.findaverage(time);
			halfMonth=h.findyingyunmaxmin(time);
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

				WritableSheet ws = wwb.createSheet("在线营运率分析",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"在线营运率"); 
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
            	label = new Label(0,1,"今天"); 
            	ws.addCell(label);
            	label = new Label(0,2,"昨天"); 
            	ws.addCell(label);
            	label = new Label(0,3,"前天"); 
            	ws.addCell(label);
            	label = new Label(0,4,"上周"); 
            	ws.addCell(label);
            	label = new Label(0,5,"上周平均"); 
            	ws.addCell(label);
            	label = new Label(0,6,"前半月最大"); 
            	ws.addCell(label);
            	label = new Label(0,7,"前半月最小"); 
            	ws.addCell(label);
            	label = new Label(0,8,"上月"); 
            	ws.addCell(label);
            	label = new Label(0,9,"上年"); 
            	ws.addCell(label);
                	String []shangnian=loadonline.getYy1().get(0);
                	for (int i = 0; i < shangnian.length; i++) {
                    	label = new Label(i+1,9,shangnian[i]); 
                    	ws.addCell(label);
					}
                	String []shangyue=loadonline.getYy1().get(1);
                	for (int i = 0; i < shangyue.length; i++) {
                		
                    	label = new Label(i+1,8,shangyue[i]); 
                    	ws.addCell(label);
					}
                	String [] max=halfMonth.getYingyunmax();
                		for (int i = 0; i < max.length; i++) {
                    	label = new Label(i+1,6,max[i]); 
                    	ws.addCell(label);
					}
                		String [] min=halfMonth.getYingyunmin();
                		for (int i = 0; i < min.length; i++) {
                    	label = new Label(i+1,7,min[i]); 
                    	ws.addCell(label);
					}
                	String []shangzhou=loadonline.getYy1().get(2);
                	for (int i = 0; i < shangzhou.length; i++) {
                		
                    	label = new Label(i+1,4,shangzhou[i]); 
                    	ws.addCell(label);
					}
                	String []qiantian=loadonline.getYy1().get(3);
                	for (int i = 0; i < qiantian.length; i++) {
                		
                    	label = new Label(i+1,3,qiantian[i]); 
                    	ws.addCell(label);
					}
                	String []zuotian=loadonline.getYy1().get(4);
                	for (int i = 0; i < zuotian.length; i++) {
                		
                    	label = new Label(i+1,2,zuotian[i]); 
                    	ws.addCell(label);
					}
                	String []jintian=loadonline.getYy1().get(5);
                	for (int i = 0; i < jintian.length; i++) {
                		String a="";
                		if (jintian[i].length()>10) {
							a=jintian[i].substring(24, 27);
						}else {
							a=jintian[i];
						}
                    	label = new Label(i+1,1,a); 
                    	ws.addCell(label);
					}
                	List<String>pingjun=list1.get(0).getPjyingyun();
                	for (int i = 0; i < pingjun.size(); i++) {
                		
                    	label = new Label(i+1,5,pingjun.get(i)); 
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
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public List<LOADONLINE> getList() {
		return list;
	}
	public void setList(List<LOADONLINE> list) {
		this.list = list;
	}
	public List<LOADONLINE> getList1() {
		return list1;
	}
	public void setList1(List<LOADONLINE> list1) {
		this.list1 = list1;
	}
	public YingYunDao getY() {
		return y;
	}
	public void setY(YingYunDao y) {
		this.y = y;
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
	public List<LOADONLINE> getListexcle() {
		return listexcle;
	}
	public void setListexcle(List<LOADONLINE> listexcle) {
		this.listexcle = listexcle;
	}
	public List<LOADONLINE> getList1excle() {
		return list1excle;
	}
	public void setList1excle(List<LOADONLINE> list1excle) {
		this.list1excle = list1excle;
	}

	public LOADONLINE getLoadonline() {
		return loadonline;
	}

	public void setLoadonline(LOADONLINE loadonline) {
		this.loadonline = loadonline;
	}

	public List<LOADONLINE> getList2() {
		return list2;
	}

	public void setList2(List<LOADONLINE> list2) {
		this.list2 = list2;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String[] getShuju() {
		return shuju;
	}

	public void setShuju(String[] shuju) {
		this.shuju = shuju;
	}

	public HalfMonthDao getH() {
		return h;
	}

	public void setH(HalfMonthDao h) {
		this.h = h;
	}

	public HalfMonth getHalfMonth() {
		return halfMonth;
	}

	public void setHalfMonth(HalfMonth halfMonth) {
		this.halfMonth = halfMonth;
	}
	
}
