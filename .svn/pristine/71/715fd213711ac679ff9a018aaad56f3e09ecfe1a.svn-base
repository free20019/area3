package com.tw.action.find;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tw.dao.DataExcelDao;
import com.tw.dao.GroupManageDao;
import com.tw.dao.RegularOfflineDao;
import com.tw.entity.Condition;
import com.tw.entity.GroupManage;
import com.tw.entity.OperatingData;
import com.tw.entity.RegularOffline;
import com.ze.util.ExcelExport;
/**
 * 
 * @author sven.zhang
 * @category excel文件下载
 *
 */
public class DataExcelAction extends ActionSupport  {

	private static final long serialVersionUID = 112456782344587L;
	private String filename;
	private InputStream  inputPath;
	private DataExcelDao dao = new DataExcelDao();
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	private GroupManageDao pao = new GroupManageDao();
	
	private RegularOfflineDao rdao = new RegularOfflineDao();
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private String msg;
	//按照车辆查询导出excel
	public String excelVehicle(){
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition");
		if( condition != null){
			List<OperatingData> list = dao.getVehicle(condition);
			msg =ExcelExport.exportVehicle(list, out);
		}
		byte[] ba = out.toByteArray();  
	    inputPath = new ByteArrayInputStream(ba); 	    
	    filename= "车辆运营数据统计"+df.format(new Date())+".xls";	    
		filename=translate(filename);
		if(msg !=""){
			filename=msg;
		}
	
	    return SUCCESS;
	}
	//按业户查询统计数据excel
	public String excelCompany(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition1");
		if( condition != null){
			List<OperatingData> list = dao.getDataByCompany(condition);
			msg = ExcelExport.exportComp(list, out);
		}
		byte[] ba = out.toByteArray();  
        inputPath = new ByteArrayInputStream(ba); 
        filename= "业户运营数据统计"+df.format(new Date())+".xls";
        filename=translate(filename);
        if(msg !=""){
			filename=msg;
		}
		
		return SUCCESS;
	}
	//按从业人员查询统计数据excel
	public String excelCertNo(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition2");
		if( condition != null){
			List<OperatingData> list = dao.getDataByCetNo(condition);
			msg = ExcelExport.exportVehicle(list, out);
		}
		byte[] ba = out.toByteArray();  
	    inputPath = new ByteArrayInputStream(ba); 
	    filename= "从业人员运营数据统计"+df.format(new Date())+".xls";
	    filename=translate(filename);
	    if(msg !=""){
			filename=msg;
		}
	    
		return SUCCESS;
	}
	//群组excel
	public String excelGroup(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition3");
		if( condition != null){
			List<OperatingData> list = dao.getDataByGroup(condition);
			msg = ExcelExport.exportGroup(list, out);
		}
	   byte[] ba = out.toByteArray();  
       inputPath = new ByteArrayInputStream(ba); 
       filename= "群组运营数据统计"+df.format(new Date())+".xls";	
       filename=translate(filename);
       if(msg !=""){
			filename=msg;
		}
		return SUCCESS;
	}
	//营运交易查询excel
	public String excelBus(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("condition4");
		if( condition != null){
			List<OperatingData> list = dao.getBusiness(condition);
			msg = ExcelExport.exportSelect(list, out);
		}
		byte[] ba = out.toByteArray();  
		inputPath = new ByteArrayInputStream(ba); 
		filename= "营运交易查询"+df.format(new Date())+".xls";
		filename=translate(filename);
		 if(msg !=""){
				filename=msg;
			}
		return SUCCESS;
	}

	//群组营运交易查询excel
		public String excelGroupsel(){
			HttpSession session = ServletActionContext.getRequest().getSession();
			Condition condition = (Condition) session.getAttribute("condition5");			
			if( condition != null){
				List<OperatingData> list = dao.getGroupsel(condition);
				msg = ExcelExport.exportSelect(list, out);
			}
			byte[] ba = out.toByteArray();  
			inputPath = new ByteArrayInputStream(ba); 
			filename= "群组营运交易查询"+df.format(new Date())+".xls";
			filename=translate(filename);
			 if(msg !=""){
					filename=msg;
				}
			return SUCCESS;
		}
		//常规下线情况
	public String regular(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Condition condition = (Condition) session.getAttribute("conditionRegular");					
			List<RegularOffline> list = rdao.getRegularByTime(condition);
			msg = ExcelExport.exportRegular(list, out);
		byte[] ba = out.toByteArray();  
		inputPath = new ByteArrayInputStream(ba); 
		filename= "常规下线情况"+df.format(new Date())+".xls";
		filename=translate(filename);
		 if(msg !=""){
				filename=msg;
			}
		return SUCCESS;
	}
	//车辆管理查询excel
	public String excelGroups(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		GroupManage gm = (GroupManage) session.getAttribute("gm");
		
			List<GroupManage> list = pao.findInfo(gm);
			msg = ExcelExport.exportGs(list, out);
			byte[] ba = out.toByteArray();  
			inputPath = new ByteArrayInputStream(ba); 
			filename= "车辆管理"+df.format(new Date())+".xls";
			filename=translate(filename);
			if(msg !=""){
				filename=msg;
			}
		
		return SUCCESS;
	}
	public InputStream getInputPath() {
		return inputPath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setInputPath(InputStream inputPath) {
		this.inputPath = inputPath;
	}
	/**
	 * 返回文件名编码设置
	 */
	public String translate(String name){
		try {
			name = new String(name.getBytes("utf-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;
	}
}
