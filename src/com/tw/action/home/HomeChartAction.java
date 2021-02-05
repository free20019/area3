package com.tw.action.home;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.tw.dao.AreaDao;
import com.tw.dao.YingYunDao;
import com.tw.dao.home.HomeChartDao;
import com.tw.dao.home.HomeDao;
import com.tw.entity.Area;
import com.tw.entity.LOADONLINE;
import com.tw.entity.RegularOffline;

public class HomeChartAction implements Action{
	private String sz=null;
	private int yingyun=0;
	private int szyingyun=0;
	private int szpjyingyun=0;
	private int zhouzhuan=0;
	private int szzhouzhuan=0;
	private double szpjzhouzhuan=0;
	private int jine=0;
	private int szjine=0;
	private double szpjjine=0;
	private LOADONLINE l=new LOADONLINE();
	private int[] shuju=null;
	private String shuju1[]=null;
	private String time="";
	private HomeChartDao h=new HomeChartDao();
	private HomeDao h1=new HomeDao();
	private YingYunDao y=new YingYunDao();
	private List<LOADONLINE>list=new ArrayList<LOADONLINE>();
	private LOADONLINE loadonline=new LOADONLINE();
	private List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
	private List<LOADONLINE>list2=new ArrayList<LOADONLINE>();
	private List<Area>listarea=new ArrayList<Area>();
	private Area area=new Area();
	private List<Area>cs=new ArrayList<Area>();
	private RegularOffline r=new RegularOffline();
	private List<Integer>areanum=new ArrayList<Integer>();
	private int[] cls=new int[3];
	public String find(){
		sz=h.findszpjyingyun();
		String [] a=sz.split(",");
		yingyun=h.fingoneyingyun();//最近1小时营运
		szyingyun=h.findszyingyun();//上周同今天当前时间营运
			szpjyingyun=(int) Double.parseDouble(a[0]);//上周平均营运
			BigDecimal bg = new BigDecimal(a[1]);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			szpjzhouzhuan=f1;//上周平均周转次数
			BigDecimal bg1 = new BigDecimal(a[2]);
			double f11 = bg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			szpjjine=f11;//上周平均金额
		zhouzhuan=h.findonezhouzhuan();//最近1小时周转次数
		szzhouzhuan=h.findszzhouzhuan();//上周同今天当前时间周转次数
		jine=h.findonejine();
		szjine=h.findszjine();
		l=h.findonlindone();
		cls=h.findcls();
		return SUCCESS;
	}
	//在线营运率
	public String yingyun12(){
		List<int []>list=new ArrayList<int[]>();
		for (int i = 0; i < 4; i++) {
			shuju=h.findyingyun12(i);
			list.add(i, shuju);
		}
		loadonline.setYy12(list);
		list1=h.findyypj12();
		return SUCCESS;
	}
	//最近1小時未營運車輛
	public String wyyyingyun12(){
		List<int []>list=new ArrayList<int[]>();
		for (int i = 0; i < 4; i++) {
			shuju=h.findwyingyun12(i);
			list.add(i, shuju);
		}
		loadonline.setYy12(list);
		list1=h.findwyypj12();
		return SUCCESS;
	}
	//重點監控區域車輛數量12小時
	public String clsl12(){
		List<int []>list=new ArrayList<int[]>();
		for (int i = 0; i < 4; i++) {
			shuju=h.findjksl12(i);
			list.add(i, shuju);
		}
		loadonline.setYy12(list);
		cs=h.findzhongdainpj();
		return SUCCESS;
	}
	//下線車輛和故障車輛
	public String threetb(){
		//查询故障车辆
		r=h.findRegular();
		//下線車輛
		cls=h.findoffonline();
		//下线未营运车辆
		shuju=h.findonlinewyy();
		return SUCCESS;
	}
	//重点区域时段停留车辆数量
	public String findareanum(){
		for (int i = 0; i < 7; i++) {
			areanum.add(h.findzdclsl(i));
		}
		return SUCCESS;
	}
	public int getYingyun() {
		return yingyun;
	}

	public void setYingyun(int yingyun) {
		this.yingyun = yingyun;
	}

	public int getSzyingyun() {
		return szyingyun;
	}
	public void setSzyingyun(int szyingyun) {
		this.szyingyun = szyingyun;
	}
	public HomeChartDao getH() {
		return h;
	}
	public void setH(HomeChartDao h) {
		this.h = h;
	}
	public int getSzpjyingyun() {
		return szpjyingyun;
	}
	public void setSzpjyingyun(int szpjyingyun) {
		this.szpjyingyun = szpjyingyun;
	}
	public int getZhouzhuan() {
		return zhouzhuan;
	}
	public void setZhouzhuan(int zhouzhuan) {
		this.zhouzhuan = zhouzhuan;
	}
	public int getSzzhouzhuan() {
		return szzhouzhuan;
	}
	public void setSzzhouzhuan(int szzhouzhuan) {
		this.szzhouzhuan = szzhouzhuan;
	}
	public int getJine() {
		return jine;
	}
	public void setJine(int jine) {
		this.jine = jine;
	}
	public int getSzjine() {
		return szjine;
	}
	public void setSzjine(int szjine) {
		this.szjine = szjine;
	}
	public String getSz() {
		return sz;
	}
	public void setSz(String sz) {
		this.sz = sz;
	}
	public double getSzpjzhouzhuan() {
		return szpjzhouzhuan;
	}
	public void setSzpjzhouzhuan(double szpjzhouzhuan) {
		this.szpjzhouzhuan = szpjzhouzhuan;
	}
	public double getSzpjjine() {
		return szpjjine;
	}
	public void setSzpjjine(double szpjjine) {
		this.szpjjine = szpjjine;
	}
	
	public int[] getShuju() {
		return shuju;
	}
	public void setShuju(int[] shuju) {
		this.shuju = shuju;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public YingYunDao getY() {
		return y;
	}
	public void setY(YingYunDao y) {
		this.y = y;
	}
	public List<LOADONLINE> getList() {
		return list;
	}
	public void setList(List<LOADONLINE> list) {
		this.list = list;
	}
	public LOADONLINE getLoadonline() {
		return loadonline;
	}
	public void setLoadonline(LOADONLINE loadonline) {
		this.loadonline = loadonline;
	}
	public List<LOADONLINE> getList1() {
		return list1;
	}
	public void setList1(List<LOADONLINE> list1) {
		this.list1 = list1;
	}
	public String[] getShuju1() {
		return shuju1;
	}
	public void setShuju1(String[] shuju1) {
		this.shuju1 = shuju1;
	}
	public HomeDao getH1() {
		return h1;
	}
	public void setH1(HomeDao h1) {
		this.h1 = h1;
	}
	public List<LOADONLINE> getList2() {
		return list2;
	}
	public void setList2(List<LOADONLINE> list2) {
		this.list2 = list2;
	}
	public String execute() throws Exception {
		return null;
	}
	public List<Area> getListarea() {
		return listarea;
	}
	public void setListarea(List<Area> listarea) {
		this.listarea = listarea;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<Area> getCs() {
		return cs;
	}
	public void setCs(List<Area> cs) {
		this.cs = cs;
	}
	public RegularOffline getR() {
		return r;
	}
	public void setR(RegularOffline r) {
		this.r = r;
	}
	public LOADONLINE getL() {
		return l;
	}
	public void setL(LOADONLINE l) {
		this.l = l;
	}
	public List<Integer> getAreanum() {
		return areanum;
	}
	public void setAreanum(List<Integer> areanum) {
		this.areanum = areanum;
	}
	public int[] getCls() {
		return cls;
	}
	public void setCls(int[] cls) {
		this.cls = cls;
	}
	
}
