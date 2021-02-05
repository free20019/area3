package com.tw.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tw.dao.ConditionDao;
import com.tw.dao.GroupManageDao;
import com.tw.entity.GroupManage;
import com.tw.entity.Vhic;
/**
 * 
 * @author sven.zhang
 *	群组管理
 */
public class GroupManageAction extends ActionSupport{

	private static final long serialVersionUID = 12536365256363L ;
	private GroupManage gm = new GroupManage();
	private GroupManageDao g=new GroupManageDao();
	private ConditionDao dao = new ConditionDao();
	private List<GroupManage>list = new ArrayList<GroupManage>();
	private List<Vhic> aList = new ArrayList<Vhic>();
	private List<String>cList;
	private String fileName;
	private boolean flag;
	 	public String findAllCompany(){
	 		cList = dao.getCompany();
	 		return SUCCESS;
	 	}
		//获取群组
		public String findGroup(){
			list = g.getGroup();
			return SUCCESS;
		}
		//查群组中的分公司
		public String findBranch(){
			list = g.getBranch(gm);
			return SUCCESS;
		}
		//查群组中的车号
		public String findVhic(){
			list = g.findcardNo(gm);
			return SUCCESS;
		}
		//查询
		public String searchs(){
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("gm", gm);
			list = g.findInfo(gm);
			return SUCCESS;
		}
		//增加
		public String addOne(){
			String []str = gm.getvList().split(",");
			aList = g.selectAll(str);
			groupIdUtil();
			 flag = g.addOne(gm,aList);
			return SUCCESS;
		}
		//修改
		public String updateOne(){
			String []str = gm.getvList().split(",");
			aList = g.selectAll(str);
			flag = g.updateOne(gm,aList);
			return SUCCESS;
		}
		//删除
		public String deleteOne(){
			flag = g.deleteOne(gm);
			return SUCCESS;
		}
		//获取固定格式的日期
		public void groupIdUtil(){
			SimpleDateFormat df = new SimpleDateFormat("yyMMddhhmmss");
			gm.setGroupId(df.format(new Date()));
		}
		public GroupManage getGm() {
			return gm;
		}
		public void setGm(GroupManage gm) {
			this.gm = gm;
		}
		public GroupManageDao getG() {
			return g;
		}
		public void setG(GroupManageDao g) {
			this.g = g;
		}
		public List<GroupManage> getList() {
			return list;
		}
		public void setList(List<GroupManage> list) {
			this.list = list;
		}
		public List<String> getcList() {
			return cList;
		}
		public void setcList(List<String> cList) {
			this.cList = cList;
		}
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
	
	

}
