package com.tw.action.find;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tw.dao.OperatingDataDao;
import com.tw.entity.Condition;
import com.tw.entity.OperatingData;
import com.ze.util.PageHelper;
/**
 * 
 * @author sven.zhang
 * 	分页查询
 *
 */
public class OperatingDataByPageAction extends ActionSupport {
	private static final long serialVersionUID = -2729160815301078521L;
	private List<OperatingData> list;
	private PageHelper page;
	private OperatingDataDao dao = new OperatingDataDao();
	// 按照车辆查询分页
	public String vehicle() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition bu = (Condition) session.getAttribute("condition");
		if (bu != null) {
			list = dao.getAll(bu, page);
		}
		return SUCCESS;
	}
	//按照公司查询分页
		public String company() {
			HttpSession session = ServletActionContext.getRequest().getSession();
			Condition bu = (Condition) session.getAttribute("condition1");
			if (bu != null) {
				list = dao.getDataByCompany(bu, page);
			}
			return SUCCESS;
		}
	//按照资格证号查询分页
	public String certNo() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition bu = (Condition) session.getAttribute("condition2");
		if (bu != null) {
			list = dao.getDataByCetNo(bu, page);
		}
		return SUCCESS;
	}
	
	//按照群组
	public String group() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition bu = (Condition) session.getAttribute("condition3");
		if (bu != null) {
			list = dao.getDataByGroup(bu, page);
		}
		return SUCCESS;
	}
	//营运交易查询
	public String business(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition bu =  (Condition) session.getAttribute("condition4");
		if (bu != null) {
			list = dao.getBusiness(bu, page);
		}
		return SUCCESS;
	}
	//群组交易查询
		public String groupsel() {
			HttpSession session = ServletActionContext.getRequest().getSession();
			Condition bu = (Condition) session.getAttribute("condition5");
			if (bu != null) {
				list = dao.getGroupsel(bu, page);
			}
			return SUCCESS;
		}
		//根据终端类型分页
		public String mdtpage(){
			HttpSession session = ServletActionContext.getRequest().getSession();
			Condition bu = (Condition) session.getAttribute("conditionmdt");
			String a=(String) session.getAttribute("data");
			if (bu != null) {
				list=dao.getmdtno(bu, page,a);
			}
			
			return SUCCESS;
		}
	public List<OperatingData> getList() {
		return list;
	}

	public void setList(List<OperatingData> list) {
		this.list = list;
	}

	public PageHelper getPage() {
		return page;
	}

	public void setPage(PageHelper page) {
		this.page = page;
	}

}
