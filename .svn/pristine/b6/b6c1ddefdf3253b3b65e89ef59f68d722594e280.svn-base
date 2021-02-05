package com.tw.action.home;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.tw.dao.AreaDao;
import com.tw.dao.home.HomeDao;
import com.tw.entity.Area;
import com.tw.entity.LOADONLINE;

public class HomeAction implements Action{
	HomeDao h=new HomeDao();
	private String shuju[]=null;
	private List<LOADONLINE>list=new ArrayList<LOADONLINE>();
	private List<Area>listarea=new ArrayList<Area>();
	private Area area=new Area();
	private List<Area>cs=new ArrayList<Area>();
	private LOADONLINE loadonline =new LOADONLINE();
	//上线率
	public String fingyingyun24(){
		List<String []>list1=new ArrayList<String[]>();
		for (int i = 0; i < 4; i++) {
			shuju=h.findyingyun24(i);
			list1.add(i, shuju);
		}
		loadonline.setYy1(list1);
		list=h.findaverageyingyun();
		return SUCCESS;
	}
	//重点监控区域上线数
	public String findarea(){
	AreaDao a=new AreaDao();
	List<List<Area>>hn=new ArrayList<List<Area>>();
	for (int i = 0; i < 4; i++) {
		listarea=h.findarea(i);
		hn.add(i, listarea);
	}
	area.setJg(hn);
	cs=h.findaveragezhongdain();
	return SUCCESS;
}
	
	//1小时未营运
	public String fingweiyingyun24(){
		List<String []>list1=new ArrayList<String[]>();
		for (int i = 0; i < 4; i++) {
			shuju=h.findweiyingyun24(i);
			list1.add(i, shuju);
		}
		loadonline.setYy1(list1);
		list=h.findaveragewyingyun();
		return SUCCESS;
	}
	//疑似停留车辆数
	public String fingystingyun(){
		List<String []>list1=new ArrayList<String[]>();
		for (int i = 0; i < 2; i++) {
			shuju=h.findystingyun(i);
			list1.add(i, shuju);
		}
		loadonline.setYy1(list1);
		return SUCCESS;
	}
	public HomeDao getH() {
		return h;
	}

	public void setH(HomeDao h) {
		this.h = h;
	}

	public String[] getShuju() {
		return shuju;
	}

	public void setShuju(String[] shuju) {
		this.shuju = shuju;
	}

	public LOADONLINE getLoadonline() {
		return loadonline;
	}

	public void setLoadonline(LOADONLINE loadonline) {
		this.loadonline = loadonline;
	}

	public String execute() throws Exception {
		return null;
	}

	public List<LOADONLINE> getList() {
		return list;
	}

	public void setList(List<LOADONLINE> list) {
		this.list = list;
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

}
