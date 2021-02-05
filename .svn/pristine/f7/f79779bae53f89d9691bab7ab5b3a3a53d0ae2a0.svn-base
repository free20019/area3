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
import com.tw.dao.AreaDao;
import com.tw.entity.Vehicle;
import com.tw.entity.Area;
import com.tw.entity.Numb;

public class AreaAction  implements Action{

	private String id;
	private String name;
	private String time;
	private String stime;
	private String etime;
	private String xlsfilename;
	private String fanhuei;
	private String action;
	private String areaid;
	private String message;
	private String areaname;
	private String areabjs;
	private String areams;
	private String areazbs;
	private String areasize;
	private Area areaone = new Area();
	private List<Area> arealist = new ArrayList<Area>();
	private List<Vehicle> vehilist = new ArrayList<Vehicle>();
	private Numb num = new Numb();
	private AreaDao areaDao = new AreaDao();
	private List<Vehicle>list=new ArrayList<Vehicle>();
	
	private String id1;
	private String id2;
	private String oid1;
	private String oid2;
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String aaa(){
//		System.out.println(id+"  "+time);
		
		return SUCCESS;
	}
	
	public String addarea(){
		String id =(String) ActionContext.getContext().getSession().get("uid");
		int a = areaDao.addarea(areaname,areabjs,areams,areazbs,areasize,id);
		if(a==1){
			this.message="";
		}else{
			this.message="增加失败";
		}
		return SUCCESS;
	}
	
	public String addudarea(){
		String id =(String) ActionContext.getContext().getSession().get("uid");
		int a = areaDao.addudarea(areaname,areams,areazbs,areasize,id);
		if(a==1){
			this.message="";
		}else{
			this.message="增加失败";
		}
		return SUCCESS;
	}
	
	public String addvearea(){
		String id =(String) ActionContext.getContext().getSession().get("uid");
		int a = areaDao.addvearea(areaname,areams,areazbs,areasize,id);
		if(a==1){
			this.message="";
		}else{
			this.message="增加失败";
		}
		return SUCCESS;
	}
	
	public String queryarea(){
		String id =(String) ActionContext.getContext().getSession().get("uid");
		arealist = areaDao.finduserarea(id);
		return SUCCESS;
	}
	
	public String queryudarea(){
		String id =(String) ActionContext.getContext().getSession().get("uid");
		arealist = areaDao.findudarea(id);
		return SUCCESS;
	}
	public String queryvearea(){
		String id =(String) ActionContext.getContext().getSession().get("uid");
		arealist = areaDao.findvearea(id);
		return SUCCESS;
	}
	public String deletearea(){
		int a = areaDao.deletearea(id);
		if(a==1){
			this.message="";
		}else{
			this.message="删除失败";
		}
		return SUCCESS;
	}
	
	public String deleteudarea(){
		int a = areaDao.deleteudarea(id);
		if(a==1){
			this.message="";
		}else{
			this.message="删除失败";
		}
		return SUCCESS;
	}
	
	public String deletevearea(){
		int a = areaDao.deletevearea(id);
		if(a==1){
			this.message="";
		}else{
			this.message="删除失败";
		}
		return SUCCESS;
	}
	public String findonearea(){
		areaone = areaDao.findbyid(id);
		return SUCCESS;
	}
	
	public String findoneudarea(){
		areaone = areaDao.findudbyid(id);
		return SUCCESS;
	}
	public String findonevearea(){
		areaone = areaDao.findvebyid(id);
		return SUCCESS;
	}
	
	public String updatearea(){
		int a = areaDao.editarea(id,areaname,areabjs,areams,areazbs);
		if(a==1){
			this.message="";
		}else{
			this.message="修改失败";
		}
		return SUCCESS;
	}
	
	public String updateudarea(){
		int a = areaDao.editudarea(id,areaname,areams,areazbs);
		if(a==1){
			this.message="";
		}else{
			this.message="修改失败";
		}
		return SUCCESS;
	}
	
	public String updatevearea(){
		int a = areaDao.editvearea(id,areaname,areams,areazbs);
		if(a==1){
			this.message="";
		}else{
			this.message="修改失败";
		}
		return SUCCESS;
	}
	
	public String orderareaid(){
		int a = areaDao.paixu(id1,id2,oid1,oid2);
		if(a==1){
			this.message="";
		}else{
			this.message="修改失败";
		}
		return SUCCESS;
	}
	//上下客监测点区域查询
	public String findupdown() {
		arealist=areaDao.findupdown();
		return SUCCESS;
	}
	//上下客监测点数据查询
	public String findupdowndata(){
		list=areaDao.findupdownexcle(areaid, stime, etime);
		return SUCCESS;
	}
	//上下客监测点数据导出
	public String findupdownexcle(){
		try {
				list=areaDao.findupdownexcle(areaid,stime,etime);
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

				WritableSheet ws = wwb.createSheet("典型上下客监测点车辆信息",0);//创建Excel工作表 指定名称和位置 
				Label label ;
            	label = new Label(0,0,"公司"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车号"); 
            	ws.addCell(label);
            	label = new Label(2,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(3,0,"联系电话"); 
            	ws.addCell(label);
            	label = new Label(4,0,"区域名称"); 
            	ws.addCell(label);
            	label = new Label(5,0,"上/下客状态"); 
            	ws.addCell(label);
            	label = new Label(6,0,"上/下客时间"); 
            	ws.addCell(label);
                	for (int i = 0; i < list.size(); i++) {
                    	label = new Label(0,i+1,list.get(i).getCompname()); 
                    	ws.addCell(label);
                    	label = new Label(1,i+1,list.get(i).getVehino()); 
                    	ws.addCell(label);
                    	label = new Label(2,i+1,list.get(i).getVehisim()); 
                    	ws.addCell(label);
                    	label = new Label(3,i+1,list.get(i).getOwntel()); 
                    	ws.addCell(label);
                    	label = new Label(4,i+1,list.get(i).getSpeed()); 
                    	ws.addCell(label);
                    	label = new Label(5,i+1,list.get(i).getState()); 
                    	ws.addCell(label);
                    	label = new Label(6,i+1,list.get(i).getDateTime()); 
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
	public Area getAreaone() {
		return areaone;
	}

	public void setArea(Area areaone) {
		this.areaone = areaone;
	}

	public String getarea(){
		
		return SUCCESS;
	}

	public List<Area> getArealist() {
		return arealist;
	}

	public void setArealist(List<Area> arealist) {
		this.arealist = arealist;
	}

	public Numb getNum() {
		return num;
	}

	public void setNum(Numb num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Vehicle> getVehilist() {
		return vehilist;
	}

	public void setVehilist(List<Vehicle> vehilist) {
		this.vehilist = vehilist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getAreabjs() {
		return areabjs;
	}

	public void setAreabjs(String areabjs) {
		this.areabjs = areabjs;
	}

	public String getAreams() {
		return areams;
	}

	public void setAreams(String areams) {
		this.areams = areams;
	}

	public String getAreazbs() {
		return areazbs;
	}

	public void setAreazbs(String areazbs) {
		this.areazbs = areazbs;
	}

	public String getAreasize() {
		return areasize;
	}

	public void setAreasize(String areasize) {
		this.areasize = areasize;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getOid1() {
		return oid1;
	}

	public void setOid1(String oid1) {
		this.oid1 = oid1;
	}

	public String getOid2() {
		return oid2;
	}

	public void setOid2(String oid2) {
		this.oid2 = oid2;
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

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public void setAreaone(Area areaone) {
		this.areaone = areaone;
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

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public List<Vehicle> getList() {
		return list;
	}

	public void setList(List<Vehicle> list) {
		this.list = list;
	}
	
	
}
