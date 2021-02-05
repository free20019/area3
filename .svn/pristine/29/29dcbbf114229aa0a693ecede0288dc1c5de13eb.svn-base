package com.tw.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.tw.dao.GroupDao;
import com.tw.entity.Vhic;

public class GroupAction implements Action{
	private String ba_id;
	private String comp_id;
	private List<Vhic>list=new ArrayList<Vhic>();
	private List<Vhic>list1=new ArrayList<Vhic>();
	private GroupDao g=new GroupDao();
	private String groupname;
	private String groupvhic;
	private String info;
	private String id;
	private String comp_name;
	private List<Vhic>fList=new ArrayList<Vhic>();
	private List<Vhic>bList=new ArrayList<Vhic>();
	private List<Vhic>cList=new ArrayList<Vhic>();
	private Vhic v=new Vhic();
	//查询分公司
	public String findcompname(){
		list=g.findcomp(ba_id);
		list1=g.findvhicgs(ba_id);
		return SUCCESS;
	}
	//查询车号
	public String findvehiname(){
		list=g.findvhicid(comp_id);
		return SUCCESS;
	}
	public String addvhicgroup(){
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();
		String dateStr2 = new Timestamp(d.getTime()).toString();
		String MV_VERSION="";
		MV_VERSION+=dateStr2.substring(3, 4);
		MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
		MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
		MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
		int a=g.vhicgroupnameadd(groupname, MV_VERSION);
		if (a!=0) {
			String[] b=groupvhic.split(",");
			info="增加失败";
			for (int i = 0; i < b.length; i++) {
				String[] c=b[i].split(":"); 
				if (c.length>1) {
						g.vhicgroupadd(c[0], MV_VERSION, c[1]);
						info="增加成功";
				}else {
					g.vhicgroupadd(c[0], MV_VERSION, "");
					info="增加成功";
				}
			}
		}else {
			info="车辆组名不为空";
		}
		return SUCCESS;
	}
	//查询车辆组
	public String findvhicgroup(){
		list=g.findvhicgroup();
		return SUCCESS;
	}
	//根据车辆组查询改组下的车号
	public String finggroupvhic(){		
		fList= g.findgroupvhic(id);
		return SUCCESS;
	}
	//车辆组删除
	public String delvhicgroup(){
		int a=g.delvhicgroup(id);
		info="删除失败";
		if(a!=0){
			g.delgroupvhic(id);
			info="删除成功";
		}else {
			info="删除失败";
		}
		return SUCCESS;
	}
	
	//根据id查询数据
	public String findvhicgroupid(){
		v=g.findvhicgroupid(id);
		list=g.findgroupvhicid(id);
		return SUCCESS;
	}
	//修改
	public String editvhicgroup(){
		g.delvhicgroup(id);
		g.delgroupvhic(id);
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();
		String dateStr2 = new Timestamp(d.getTime()).toString();
		String MV_VERSION="";
		MV_VERSION+=dateStr2.substring(3, 4);
		MV_VERSION=MV_VERSION+dateStr2.substring(11,13);
		MV_VERSION=MV_VERSION+dateStr2.substring(14,16);
		MV_VERSION=MV_VERSION+dateStr2.substring(17,19);
		int a=g.vhicgroupnameadd(groupname, MV_VERSION);
		if (a!=0) {
			String[] b=groupvhic.split(",");
			info="修改失败";
			for (int i = 0; i < b.length; i++) {
				String[] c=b[i].split(":"); 
				if (c.length>1) {
						g.vhicgroupadd(c[0], MV_VERSION, c[1]);
						info="修改成功";
				}else {
					g.vhicgroupadd(c[0], MV_VERSION, "");
					info="修改成功";
				}
			}
		}else {
			info="修改失败";
		}
		return SUCCESS;
	}
	
	/***
	*@author sven.zhang
	*/
	//查公司
	public String findBranch(){
		bList = g.getBranch(id);
		return SUCCESS;
	}

	public String getBa_id() {
		return ba_id;
	}

	public void setBa_id(String baId) {
		ba_id = baId;
	}

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String compId) {
		comp_id = compId;
	}


	public List<Vhic> getList() {
		return list;
	}
	public void setList(List<Vhic> list) {
		this.list = list;
	}
	public GroupDao getG() {
		return g;
	}

	public void setG(GroupDao g) {
		this.g = g;
	}

	public List<Vhic> getList1() {
		return list1;
	}
	public void setList1(List<Vhic> list1) {
		this.list1 = list1;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupvhic() {
		return groupvhic;
	}
	public void setGroupvhic(String groupvhic) {
		this.groupvhic = groupvhic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Vhic getV() {
		return v;
	}
	public void setV(Vhic v) {
		this.v = v;
	}
	public List<Vhic> getfList() {
		return fList;
	}
	public void setfList(List<Vhic> fList) {
		this.fList = fList;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public List<Vhic> getbList() {
		return bList;
	}
	public void setbList(List<Vhic> bList) {
		this.bList = bList;
	}
	public List<Vhic> getcList() {
		return cList;
	}
	public void setcList(List<Vhic> cList) {
		this.cList = cList;
	}
	
}
