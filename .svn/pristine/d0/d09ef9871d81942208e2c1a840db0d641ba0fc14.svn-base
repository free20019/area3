package com.tw.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.tw.dao.FindDao;
import com.tw.dao.JurisdictionDao;
import com.tw.entity.Company;
import com.tw.entity.User;
import com.tw.entity.Vhic;
import com.tw.entity.station;

public class JurisdictionAction implements Action{
	private String compname;
	private List<User>list=new ArrayList<User>();
	private JurisdictionDao j=new JurisdictionDao();
	private String u_id;
	private String username;
	private String password;
	private String date_view_type;
	private String visit_view_type;
	private String comp_name;
	private String info;
	private String id;
	private User user=new User();
	private String ba_idadd;
	private String comp_idadd;
	private String group;
	private int num=0;
	private String group_id;
	private String action_id;
	private String groupname;
	private String stationname;
	private String stationjuri;
	private String stationadmin;
	private String station_id;
	private String realname;
	private String stationid;
	private station s=new station();
	private List<Vhic>list1=new ArrayList<Vhic>();
	private List<Vhic>list2=new ArrayList<Vhic>();
	private List<Vhic>list3=new ArrayList<Vhic>();
	private List<station>station=new ArrayList<station>();
	private List<Company>company=new ArrayList<Company>();
	public String execute(){
		return SUCCESS;
	}
	public String finduser(){
		FindDao f=new FindDao();
		String name=(String) ActionContext.getContext().getSession().get("date");
		list=j.finduser(name);
		return SUCCESS;
	}
	public String adduser(){
		int a=j.useradd(username,password,date_view_type, station_id, realname);
		if (a!=0) {
			info="添加成功";
		}else {
			info="添加失败";
		}
		return SUCCESS;
	}
	//删除
	public String deluser(){
		if(!"".equals(this.id)&&this.id!=null){
			String ids[] =this.id.split(" ");
			for(int i=0;i<ids.length;i++){
				j.userdel(ids[i]);
				this.info="删除成功!";
			}
		}
		return SUCCESS;
	}
	//根据id查询
	public String iduser(){
		user=j.userid(id);
		return SUCCESS;
	}
	public String edituser(){
		int a=j.edituser(realname, username, password, date_view_type, stationid, id);
		if (a!=0) {
			info="修改成功";
		}else {
			info="修改失败";
		}
		return SUCCESS;
	}
	//下拉列表框
	public String findba(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String permission = (String) session.getAttribute("date");
		String perm[]=new String[3];
		if (permission!=null) {
			perm=permission.split(",");
			if ("所有公司".equals(perm[0])) {
				list1=j.findba();
			}else {
				Vhic v=new Vhic();
				String comp=perm[0];
				v.setBa_name(comp);
				list1.add(v);
			}
		}
		return SUCCESS;
	}
	public String findcomp(){
		if (ba_idadd==null) {
			list2=j.findcomp(ba_idadd);
			list3=j.findvhicgs(ba_idadd);
		}else {
			String a[]=ba_idadd.split(",");
			for (int i = 0; i < a.length; i++) {
				if (!a[i].equals("所有公司")) {
					list2=j.findcomp(a[i]);
					list3=j.findvhicgs(a[i]);
				}
			}
		}
		return SUCCESS;
	}
	public String findvehi(){
		if(comp_idadd==null){
			list3=j.findvhicid(comp_idadd);
		}else {
			String a[]=comp_idadd.split(",");
			for (int i = 0; i < a.length; i++) {
				if (!a[i].equals("所有分公司")) {
					list3=j.findvhicid(a[i]);
				}
			}
		}
		return SUCCESS;
	}
	//新增岗位
	public String addstation(){
		int a=j.addstation(stationname, stationjuri,stationadmin);
		if(a!=0){
			info="添加成功";
		}else {
			info="添加失败";
		}
		return SUCCESS;
	}
	//查询岗位
	public String findstation(){
		station=j.findstation();
		return SUCCESS;
	}
	//根据id查询岗位
	public String stationid(){
		s=j.stationid(action_id.trim());
		return SUCCESS;
	}
	//根据id删除岗位
	public String stationdel(){
		int a=j.stationdel(action_id.trim());
		if (a!=0) {
			info="删除成功";
		}else {
			info="删除失败";
		}
		return SUCCESS;
	}
	//根据id修改岗位
	public String stationedit(){
		int a=j.editstation(action_id, stationname, stationadmin, stationjuri);
		if (a!=0) {
			info="修改成功";
		}else {
			info="修改失败";
		}
		return SUCCESS;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public JurisdictionDao getJ() {
		return j;
	}
	public void setJ(JurisdictionDao j) {
		this.j = j;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String uId) {
		u_id = uId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDate_view_type() {
		return date_view_type;
	}
	public void setDate_view_type(String dateViewType) {
		date_view_type = dateViewType;
	}
	public String getVisit_view_type() {
		return visit_view_type;
	}
	public void setVisit_view_type(String visitViewType) {
		visit_view_type = visitViewType;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String compName) {
		comp_name = compName;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Vhic> getList1() {
		return list1;
	}
	public void setList1(List<Vhic> list1) {
		this.list1 = list1;
	}
	public List<Vhic> getList2() {
		return list2;
	}
	public void setList2(List<Vhic> list2) {
		this.list2 = list2;
	}
	public List<Vhic> getList3() {
		return list3;
	}
	public void setList3(List<Vhic> list3) {
		this.list3 = list3;
	}
	public List<Company> getCompany() {
		return company;
	}
	public void setCompany(List<Company> company) {
		this.company = company;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String groupId) {
		group_id = groupId;
	}
	public String getAction_id() {
		return action_id;
	}
	public void setAction_id(String actionId) {
		action_id = actionId;
	}
	public String getStationname() {
		return stationname;
	}
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	public String getStationjuri() {
		return stationjuri;
	}
	public void setStationjuri(String stationjuri) {
		this.stationjuri = stationjuri;
	}
	public List<station> getStation() {
		return station;
	}
	public void setStation(List<station> station) {
		this.station = station;
	}
	public String getStationadmin() {
		return stationadmin;
	}
	public void setStationadmin(String stationadmin) {
		this.stationadmin = stationadmin;
	}
	public station getS() {
		return s;
	}
	public void setS(station s) {
		this.s = s;
	}
	public String getBa_idadd() {
		return ba_idadd;
	}
	public void setBa_idadd(String baIdadd) {
		ba_idadd = baIdadd;
	}
	public String getComp_idadd() {
		return comp_idadd;
	}
	public void setComp_idadd(String compIdadd) {
		comp_idadd = compIdadd;
	}
	public String getStation_id() {
		return station_id;
	}
	public void setStation_id(String stationId) {
		station_id = stationId;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getStationid() {
		return stationid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
	}
}
