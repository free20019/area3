package com.tw.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.cache.DataItem;
import com.tw.cache.GisData;
import com.tw.dao.AreaDao;
import com.tw.dao.RealtimeDao;
import com.tw.entity.Area;
import com.tw.entity.Company;
import com.tw.entity.Numb;
import com.tw.entity.TbVehicleGps;
import com.tw.entity.Vehicle;
import com.tw.entity.Vehijson;
import com.ze.util.Converter;
import com.ze.util.GetGdzb;
import com.ze.util.GetStatus;

public class RealtimeAction implements Action{

	private String keyword;
	private String compid;
	
	private String vehiid;
	private String iscomp;
	private String baid;
	private String stime;
	private String etime;
	private Numb num = new Numb();
	private List<Company> baname = new ArrayList<Company>();
	private List<Company> compname = new ArrayList<Company>();
	private List<Area> arealist = new ArrayList<Area>();
	private AreaDao areaDao = new AreaDao();
	private List<Vehicle> vehilist = new ArrayList<Vehicle>();
	private Vehicle mdt = new Vehicle();
	private List<TbVehicleGps> vehigps = new ArrayList<TbVehicleGps>();
	private RealtimeDao rtDao = new RealtimeDao();
	private GetStatus gs = new GetStatus();
	private GetGdzb gg = new GetGdzb();
	private List<TbVehicleGps> exportvs = new ArrayList<TbVehicleGps>();
	private List<TbVehicleGps> exportjk = new ArrayList<TbVehicleGps>();
	private List<Vehicle> l2 = new ArrayList<Vehicle>();
	private List<Vehicle> l3 = new ArrayList<Vehicle>();
	private List<Vehicle> l4 = new ArrayList<Vehicle>();
	private List<Vehicle> l5 = new ArrayList<Vehicle>();
	private List<Vehicle> l6 = new ArrayList<Vehicle>();
	private List<Vehicle> l7 = new ArrayList<Vehicle>();
	private Converter converter = new Converter();
	
	
	private String xlsfilename;
	private String xlsfilepath;
	private String message;
	private String action;
	
	
	public List<Vehicle> getL2() {
		return l2;
	}
	public void setL2(List<Vehicle> l2) {
		this.l2 = l2;
	}
	public List<Vehicle> getL3() {
		return l3;
	}
	public void setL3(List<Vehicle> l3) {
		this.l3 = l3;
	}
	public List<Vehicle> getL4() {
		return l4;
	}
	public void setL4(List<Vehicle> l4) {
		this.l4 = l4;
	}
	
	public List<Vehicle> getL5() {
		return l5;
	}
	public void setL5(List<Vehicle> l5) {
		this.l5 = l5;
	}
	public List<Vehicle> getL6() {
		return l6;
	}
	public void setL6(List<Vehicle> l6) {
		this.l6 = l6;
	}
	public List<Vehicle> getL7() {
		return l7;
	}
	public void setL7(List<Vehicle> l7) {
		this.l7 = l7;
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCompid() {
		return compid;
	}
	public void setCompid(String compid) {
		this.compid = compid;
	}
	
	public List<Vehicle> getVehilist() {
		return vehilist;
	}
	public void setVehilist(List<Vehicle> vehilist) {
		this.vehilist = vehilist;
	}
	public List<TbVehicleGps> getExportvs() {
		return exportvs;
	}
	public void setExportvs(List<TbVehicleGps> exportvs) {
		this.exportvs = exportvs;
	}
	public List<Company> getBaname() {
		return baname;
	}
	public void setBaname(List<Company> baname) {
		this.baname = baname;
	}
	public List<Company> getCompname() {
		return compname;
	}
	public void setCompname(List<Company> compname) {
		this.compname = compname;
	}
	public String getVehiid() {
		return vehiid;
	}
	public void setVehiid(String vehiid) {
		this.vehiid = vehiid;
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
	public Vehicle getMdt() {
		return mdt;
	}
	public void setMdt(Vehicle mdt) {
		this.mdt = mdt;
	}
	public String getIscomp() {
		return iscomp;
	}
	public void setIscomp(String iscomp) {
		this.iscomp = iscomp;
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
	public List<TbVehicleGps> getVehigps() {
		return vehigps;
	}
	public void setVehigps(List<TbVehicleGps> vehigps) {
		this.vehigps = vehigps;
	}
	
	
	public String getXlsfilename() {
		return xlsfilename;
	}
	public void setXlsfilename(String xlsfilename) {
		this.xlsfilename = xlsfilename;
	}
	public String getXlsfilepath() {
		return xlsfilepath;
	}
	public void setXlsfilepath(String xlsfilepath) {
		this.xlsfilepath = xlsfilepath;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBaid() {
		return baid;
	}
	public void setBaid(String baid) {
		this.baid = baid;
	}
	
	public List<TbVehicleGps> getExportjk() {
		return exportjk;
	}
	public void setExportjk(List<TbVehicleGps> exportjk) {
		this.exportjk = exportjk;
	}
	public String chaxunvhic(){
		this.compid=(String) ActionContext.getContext().getSession().get("data");
		vehilist = rtDao.findbykeyword(this.keyword,this.compid);
		return SUCCESS;
	}
	//轨迹
	public String chaxunvhichis(){
		this.compid=(String) ActionContext.getContext().getSession().get("data");
		vehilist = rtDao.findbykeyword(this.keyword,this.compid);
		baname=rtDao.findbaname(this.compid);
		//vehilist = rtDao.findgjcl(this.compid,"");
		//compname = rtDao.findcompname(this.compid);
		return SUCCESS;
	}
	
	public String findfgs(){
		this.compid=(String) ActionContext.getContext().getSession().get("data");
		compname = rtDao.findcompname(this.compid,this.baid);
		vehilist = rtDao.findgscl(this.baid);
		return SUCCESS;
	}
	public String findgjcl(){
		this.compid=(String) ActionContext.getContext().getSession().get("data");
		vehilist = rtDao.findgjcl(this.compid,this.keyword);
		return SUCCESS;
	}
	
	public String queryvhic(){
		
		
		this.compid=(String) ActionContext.getContext().getSession().get("data");
		String id=(String) ActionContext.getContext().getSession().get("uid");
		if(null == compid || null == id){
			return SUCCESS;
		}
		if(this.compid.contains("所有公司")){
			queryvhicAdmin();
			return SUCCESS;
		}
		int onnum =0;
		int offnum = 0;
		int hnum = 0;
		int nnum = 0;
		vehilist = rtDao.findbykeyword(this.keyword,this.compid);
		for(int i=0;i<vehilist.size();i++){
			Vehicle va = vehilist.get(i);
			if(va.getLati()==null||"".equals(va.getLati())||"0.0".equals(va.getLati())||"".equals(va.getLongi())||"0.0".equals(va.getLongi())){
				offnum++;
			}else{
				
				if(va.getOnoffstate().equals("1")){
					onnum++;
					if(va.getCarStatus().equals("0")){
						nnum++;
					}else{
						hnum++;
					}
				}else{
					offnum++;
				}
			}
		}
		int total = vehilist.size();
		num.setTotal(total+"");
		num.setOnnum(onnum+"");
		num.setOffnum(offnum+"");
		num.setHnum(hnum+"");
		num.setNnum(nnum+"");
		
		arealist = areaDao.finduserarea(id);
		return SUCCESS;
	}
	
	public String queryvhicAdmin(){
		DataItem data = (DataItem) GisData.map.get("data");
		if(null != data){
			this.arealist = data.getArealist();
		//	System.out.println("#####data.getArealist()"+data.getArealist().size());
			this.num = data.getNum();
			this.vehilist = data.getVehilist();
			this.l2 = data.getL2();
			this.l3 = data.getL3();
			this.l4 = data.getL4();
			this.l5 = data.getL5();
			this.l6 = data.getL6();
			this.l7 = data.getL7();
			//System.out.println(vehilist.size());
		}
		return SUCCESS;
	}
	
	//历史轨迹
	public String history(){
		vehigps =  rtDao.findhistory(this.vehiid,this.stime,this.etime);
		return SUCCESS;
	}
	
	 public static boolean jisuan(String date1){ 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        double result=0;
		try {
			Date start = sdf.parse(date1);
			Date end = new Date();
			long cha = end.getTime() - start.getTime(); 
			result = cha * 1.0 / (1000 * 60); 
			
		} catch (ParseException e) {
			System.out.println("日期转换错误！");
		}
		if(result<=30){ 
			//System.out.println("可用");   
			return true; 
		}else{  
			//System.out.println("已过期");  
			return false; 
		} 
    } 
	 
	 
	 
	 //定位
	 public String finddwvhic(){
			if(!"".equals(this.vehiid)){
				//查数据库；
					vehilist = rtDao.findbynos(this.vehiid);
				//查缓存；
//				DataItem data = (DataItem) GisData.map.get("data");
//				List<Vehicle> vlist = data.getVehilist();
				
			}
		 return SUCCESS;
	 }
	 
	 public String queryvhic30(){
		 vehilist = rtDao.findstopcars();
		 return SUCCESS;
	 }
	 
	 public String monitor(){
	 	//mdt = rtDao.findbyno(this.vehiid);
	 	
	 	DataItem data = (DataItem) GisData.map.get("data");
		List<Vehicle> vlist = data.getVehilist();
	 	
		for(int i=0;i<vlist.size();i++){
			if(this.vehiid.equals(vlist.get(i).getVehino())){
				
				mdt = vlist.get(i);
				mdt.setAddress(rtDao.getplace(mdt.getLati()+"", mdt.getLongi()+""));
				break;
			}
		}
		
		
		return SUCCESS;
	 }
	 
	 public String exporthistory(){
		 Date date = new Date();
		String dateStr2 = new Timestamp(date.getTime()).toString();
		this.xlsfilename="";
		this.xlsfilepath="";
		String logfile=ServletActionContext.getServletContext().getRealPath("/");
		if(this.exportvs.size()>0){
			try{
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
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet(exportvs.get(0).getVehinum()+"历史轨迹报表",0);//创建Excel工作表 指定名称和位置 
				Label label ;
				label = new Label(0,0,"序号"); 
            	ws.addCell(label);
            	label = new Label(1,0,"上报时间"); 
            	ws.addCell(label);
            	label = new Label(2,0,"车辆状态"); 
            	ws.addCell(label);
            	label = new Label(3,0,"经度"); 
            	ws.addCell(label);
            	label = new Label(4,0,"纬度"); 
            	ws.addCell(label);
            	label = new Label(5,0,"方向"); 
            	ws.addCell(label);
            	label = new Label(6,0,"GPS速度(km/h)"); 
            	ws.addCell(label);
            	label = new Label(7,0,"里程(km)"); 
            	ws.addCell(label);
            	label = new Label(8,0,"位置描述"); 
            	ws.addCell(label);
				
                for(int ii1=0;ii1<exportvs.size();ii1++){
                	TbVehicleGps tbBusiness2=exportvs.get(ii1);
                	label = new Label(0,ii1+1,tbBusiness2.getMessageid()); 
                	ws.addCell(label);
                	label = new Label(1,ii1+1,tbBusiness2.getSpeedtime()); 
                	ws.addCell(label);
                	label = new Label(2,ii1+1,tbBusiness2.getCarstate()); 
                	ws.addCell(label);
                	label = new Label(3,ii1+1,tbBusiness2.getLongi()); 
                	ws.addCell(label);
                	label = new Label(4,ii1+1,tbBusiness2.getLati()); 
                	ws.addCell(label);
                	label = new Label(5,ii1+1,tbBusiness2.getMdttype()); 
                	ws.addCell(label);
                	label = new Label(6,ii1+1,tbBusiness2.getSpeed()); 
                	ws.addCell(label);
                	label = new Label(7,ii1+1,tbBusiness2.getColor()); 
                	ws.addCell(label);
                	label = new Label(8,ii1+1,tbBusiness2.getAddress()); 
                	ws.addCell(label);
                }
      
				wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.message="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.message="导出失败!";
			}
		}else{
			this.message="导出失败!";
		}
				return SUCCESS;
	}
	
	 public String exportmonitor(){
		 Date date = new Date();
		String dateStr2 = new Timestamp(date.getTime()).toString();
		this.xlsfilename="";
		this.xlsfilepath="";
		String logfile=ServletActionContext.getServletContext().getRealPath("/");
		if(this.exportjk.size()>0){
			try{
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
				File file= new File(logfile+""+MV_VERSION+".xls"); 
				xlsfilename=""+MV_VERSION+".xls";
				//this.xlsfilepath="//evaluation//"+xlsfilename;
				file.createNewFile(); //建立要输出的文件 

				OutputStream os = new FileOutputStream(file);//使用文件缓冲 

				WritableWorkbook wwb = Workbook.createWorkbook(os);//取EXCEL操作实例 

				WritableSheet ws = wwb.createSheet("监控车辆记录",0);//创建Excel工作表 指定名称和位置 
				Label label ;
				label = new Label(0,0,"序号"); 
            	ws.addCell(label);
            	label = new Label(1,0,"车牌"); 
            	ws.addCell(label);
            	label = new Label(2,0,"车辆颜色"); 
            	ws.addCell(label);
            	label = new Label(3,0,"分公司"); 
            	ws.addCell(label);
            	label = new Label(4,0,"公司电话"); 
            	ws.addCell(label);
            	label = new Label(5,0,"SIM卡号"); 
            	ws.addCell(label);
            	label = new Label(6,0,"车速km/h"); 
            	ws.addCell(label);
            	label = new Label(7,0,"状态"); 
            	ws.addCell(label);
            	label = new Label(8,0,"汇报时间"); 
            	ws.addCell(label);
            	label = new Label(9,0,"当前定位"); 
            	ws.addCell(label);
            	label = new Label(10,0,"终端类型"); 
            	ws.addCell(label);
            	label = new Label(11,0,"车辆类型"); 
            	ws.addCell(label);
				
                for(int ii1=0;ii1<exportjk.size();ii1++){
                	TbVehicleGps tbBusiness2=exportjk.get(ii1);
                	label = new Label(0,ii1+1,tbBusiness2.getId()); 
                	ws.addCell(label);
                	label = new Label(1,ii1+1,tbBusiness2.getVehinum()); 
                	ws.addCell(label);
                	label = new Label(2,ii1+1,tbBusiness2.getColor()); 
                	ws.addCell(label);
                	label = new Label(3,ii1+1,tbBusiness2.getCompid()); 
                	ws.addCell(label);
                	label = new Label(4,ii1+1,tbBusiness2.getCompphone()); 
                	ws.addCell(label);
                	label = new Label(5,ii1+1,tbBusiness2.getSimnum()); 
                	ws.addCell(label);
                	label = new Label(6,ii1+1,tbBusiness2.getSpeed()); 
                	ws.addCell(label);
                	label = new Label(7,ii1+1,tbBusiness2.getState()); 
                	ws.addCell(label);
                	label = new Label(8,ii1+1,tbBusiness2.getSpeedtime()); 
                	ws.addCell(label);
                	label = new Label(9,ii1+1,tbBusiness2.getAddress()); 
                	ws.addCell(label);
                	label = new Label(10,ii1+1,tbBusiness2.getMdtno()); 
                	ws.addCell(label);
                	label = new Label(11,ii1+1,tbBusiness2.getVttype()); 
                	ws.addCell(label);
                }
      
				wwb.write();//写入文件 
				wwb.close();
				os.close();
				this.message="成功导成Excel!";
				this.action="filedownload.action?inputPath="+this.xlsfilename;
			}catch(Exception e){
				this.message="导出失败!";
			}
		}else{
			this.message="导出失败!";
		}
				return SUCCESS;
	}
	 
}


  