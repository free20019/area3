package com.ze.util;


import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.tw.entity.GroupManage;
import com.tw.entity.OperatingData;
import com.tw.entity.RegularOffline;
/**
 * 
 * @author sven.zhang
 *	excel文件导出帮助类
 */
public class ExcelExport {
	private static DecimalFormat df =new DecimalFormat("0.00");
	public static String  exportVehicle(List<OperatingData> list,ByteArrayOutputStream out)  {
		String msg ="";
		try {
			
				WritableWorkbook book = Workbook.createWorkbook(out);
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Label label =new Label(0,0,"编号");
				sheet.addCell(label);
				label =new Label(1,0,"所属公司");
				sheet.addCell(label);
				label =new Label(2,0,"所属分公司");
				if(list.get(0).getVhic() != null){
					sheet.addCell(label);
					label =new Label(3,0,"车号");
				}
				if(list.get(0).getCertNo() != null){
					sheet.addCell(label);
					label =new Label(3,0,"资格证号");
				}
				sheet.addCell(label);
				label =new Label(4,0,"金额(元)");
				sheet.addCell(label);				
				label =new Label(5,0,"次数(次)");
				sheet.addCell(label);
				label =new Label(6,0,"计程(公里)");
				sheet.addCell(label);
				label =new Label(7,0,"空驶(公里)");
				sheet.addCell(label);
				label =new Label(8,0,"总里程(公里)");
				sheet.addCell(label);
				label =new Label(9,0,"实载率");
				sheet.addCell(label);
				label =new Label(10,0,"载客时间(小时)");
				sheet.addCell(label);
				label =new Label(11,0,"等候(小时)");
				sheet.addCell(label);	
				for( int i =0; i<list.size();i++){
					label =new Label(0,i+1,list.get(i).getNumber()+"");
					sheet.addCell(label);
					label =new Label(1,i+1,list.get(i).getCompany());
					sheet.addCell(label);
					label =new Label(2,i+1,list.get(i).getBranch());
					sheet.addCell(label);
					if(list.get(i).getVhic() != null){
						label =new Label(3,i+1,list.get(i).getVhic());
						sheet.addCell(label);
					}
					if(list.get(i).getCertNo() != null){
						label =new Label(3,i+1,list.get(i).getCertNo());
						sheet.addCell(label);
					}
					label =new Label(4,i+1,list.get(i).getMoney()+"");
					sheet.addCell(label);
					label =new Label(5,i+1,list.get(i).getTimes()+"");
					sheet.addCell(label);
					label =new Label(6,i+1,list.get(i).getDistance()+"");
					sheet.addCell(label);
					label =new Label(7,i+1,list.get(i).getEmpty()+"");
					sheet.addCell(label);
					label =new Label(8,i+1,list.get(i).getTotalDis()+"");
					sheet.addCell(label);
					label =new Label(9,i+1,list.get(i).getYpercent());
					sheet.addCell(label);
					label =new Label(10,i+1,df.format(list.get(i).getTimeOut()/3600*1.0));
					sheet.addCell(label);
					label =new Label(11,i+1,df.format(list.get(i).getWaitTime()/3600*1.0));
					sheet.addCell(label);
					
				}
				book.write();
				book.close();				
				out.flush();
				out.close();
		} catch (Exception e) {
			
			msg ="生成excel失败.xls";
		}
		return msg;
	}
	
	public static String  exportSelect(List<OperatingData> list,ByteArrayOutputStream out)  { 
		String msg ="";
		try {
		
				WritableWorkbook book = Workbook.createWorkbook(out);
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Label label =new Label(0,0,"编号");
				sheet.addCell(label);				
				label =new Label(1,0,"车号");
				sheet.addCell(label);
				label =new Label(2,0,"资格证号");
				sheet.addCell(label);
				label =new Label(3,0,"上车时间");
				sheet.addCell(label);
				label =new Label(4,0,"下车时间");
				sheet.addCell(label);
				label =new Label(5,0,"金额(元)");
				sheet.addCell(label);					
				label =new Label(6,0,"计程(公里)");
				sheet.addCell(label);
				label =new Label(7,0,"空驶(公里)");		
				sheet.addCell(label);
				label =new Label(8,0,"等候(秒)");
				sheet.addCell(label);	
				label =new Label(9,0,"交易类型");
				sheet.addCell(label);
				for( int i =0; i<list.size();i++){
					label =new Label(0,i+1,list.get(i).getNumber()+"");
					sheet.addCell(label);
					label =new Label(1,i+1,list.get(i).getVhic());
					sheet.addCell(label);
					label =new Label(2,i+1,list.get(i).getCertNo());
					sheet.addCell(label);
					label =new Label(3,i+1,list.get(i).getUpTaxi());
					sheet.addCell(label);
					label =new Label(4,i+1,list.get(i).getDownTaxi());
					sheet.addCell(label);
					label =new Label(5,i+1,list.get(i).getMoney()+"");
					sheet.addCell(label);
					label =new Label(6,i+1,list.get(i).getDistance()+"");
					sheet.addCell(label);
					label =new Label(7,i+1,list.get(i).getEmpty()+"");
					sheet.addCell(label);					
					label =new Label(8,i+1,list.get(i).getWaitTime()+"");
					sheet.addCell(label);
					label =new Label(9,i+1,list.get(i).getType()+"");
					sheet.addCell(label);
				}
				book.write();
				book.close();				
				out.flush();
				out.close();
		}catch (Exception e) {
			
			msg ="生成excel失败.xls";
		}
		return msg;
	}
	public static String  exportComp(List<OperatingData> list,ByteArrayOutputStream out)  { 
		String msg ="";
		try {
		
				WritableWorkbook book = Workbook.createWorkbook(out);
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Label label =new Label(0,0,"编号");
				sheet.addCell(label);
				label =new Label(1,0,"所属公司");
				sheet.addCell(label);
				label =new Label(2,0,"所属分公司");
				sheet.addCell(label);				
				label =new Label(3,0,"车辆总数");
				sheet.addCell(label);
				label =new Label(4,0,"营运数");
				sheet.addCell(label);
				label =new Label(5,0,"出车率");
				sheet.addCell(label);
				label =new Label(6,0,"金额(元)");
				sheet.addCell(label);				
				label =new Label(7,0,"次数(次)");
				sheet.addCell(label);
				label =new Label(8,0,"计程(公里)");
				sheet.addCell(label);
				label =new Label(9,0,"空驶(公里)");
				sheet.addCell(label);
				label =new Label(10,0,"总里程(公里)");
				sheet.addCell(label);
				label =new Label(11,0,"实载率");
				sheet.addCell(label);
				label =new Label(12,0,"载客时间(小时)");
				sheet.addCell(label);
				label =new Label(13,0,"载客等候时间(小时)");
				sheet.addCell(label);	
				for( int i =0; i<list.size();i++){
					label =new Label(0,i+1,list.get(i).getNumber()+"");
					sheet.addCell(label);
					label =new Label(1,i+1,list.get(i).getCompany());
					sheet.addCell(label);
					label =new Label(2,i+1,list.get(i).getBranch());
					sheet.addCell(label);
					label =new Label(3,i+1,list.get(i).getTotal()+"");
					sheet.addCell(label);
					label =new Label(4,i+1,list.get(i).getDriving()+"");
					sheet.addCell(label);	
					label =new Label(5,i+1,list.get(i).getYpercent());
					sheet.addCell(label);				
					label =new Label(6,i+1,list.get(i).getMoney()+"");
					sheet.addCell(label);
					label =new Label(7,i+1,list.get(i).getTimes()+"");
					sheet.addCell(label);
					label =new Label(8,i+1,list.get(i).getDistance()+"");
					sheet.addCell(label);
					label =new Label(9,i+1,list.get(i).getEmpty()+"");
					sheet.addCell(label);
					label =new Label(10,i+1,list.get(i).getTotalDis()+"");
					sheet.addCell(label);
					label =new Label(11,i+1,list.get(i).getYpercent());
					sheet.addCell(label);
					label =new Label(12,i+1,df.format(list.get(i).getTimeOut()/3600*1.0));
					sheet.addCell(label);
					label =new Label(13,i+1,df.format(list.get(i).getWaitTime()/3600*1.0));
					sheet.addCell(label);
					
				}
				book.write();
				book.close();				
				out.flush();
				out.close();
		}  catch (Exception e) {			
			msg ="生成excel失败.xls";
		}
		return msg;
	}
	public static String  exportGroup(List<OperatingData> list,ByteArrayOutputStream out)  { 
		String msg = "";
		try {
		
				WritableWorkbook book = Workbook.createWorkbook(out);
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Label label =new Label(0,0,"编号");
				sheet.addCell(label);
				label =new Label(1,0,"群组");
				sheet.addCell(label);				
				label =new Label(2,0,"车辆总数");
				sheet.addCell(label);
				label =new Label(3,0,"营运数");
				sheet.addCell(label);
				label =new Label(4,0,"出车率");
				sheet.addCell(label);
				label =new Label(5,0,"金额(元)");
				sheet.addCell(label);				
				label =new Label(6,0,"次数(次)");
				sheet.addCell(label);
				label =new Label(7,0,"计程(公里)");
				sheet.addCell(label);
				label =new Label(8,0,"空驶(公里)");
				sheet.addCell(label);
				label =new Label(9,0,"总里程(公里)");
				sheet.addCell(label);
				label =new Label(10,0,"实载率");
				sheet.addCell(label);
				label =new Label(11,0,"重车时间(小时)");
				sheet.addCell(label);
				label =new Label(12,0,"重车等候时间(小时)");
				sheet.addCell(label);	
				for( int i =0; i<list.size();i++){
					label =new Label(0,i+1,list.get(i).getNumber()+"");
					sheet.addCell(label);
					label =new Label(1,i+1,list.get(i).getGroup());
					sheet.addCell(label);
					label =new Label(2,i+1,list.get(i).getTotal()+"");
					sheet.addCell(label);
					label =new Label(3,i+1,list.get(i).getDriving()+"");
					sheet.addCell(label);	
					label =new Label(4,i+1,list.get(i).getYpercent());
					sheet.addCell(label);				
					label =new Label(5,i+1,list.get(i).getMoney()+"");
					sheet.addCell(label);
					label =new Label(6,i+1,list.get(i).getTimes()+"");
					sheet.addCell(label);
					label =new Label(7,i+1,list.get(i).getDistance()+"");
					sheet.addCell(label);
					label =new Label(8,i+1,list.get(i).getEmpty()+"");
					sheet.addCell(label);
					label =new Label(9,i+1,list.get(i).getTotalDis()+"");
					sheet.addCell(label);
					label =new Label(10,i+1,list.get(i).getYpercent());
					sheet.addCell(label);
					label =new Label(11,i+1,df.format(list.get(i).getTimeOut()/3600*1.0));
					sheet.addCell(label);
					label =new Label(12,i+1,df.format(list.get(i).getWaitTime()/3600*1.0));
					sheet.addCell(label);
					
				}
				book.write();
				book.close();				
				out.flush();
				out.close();
		} catch (Exception e) {			
			msg ="生成excel失败.xls";
		}
		return msg;
	}
	public static String  exportGs(List<GroupManage> list,ByteArrayOutputStream out)  { 
		String msg = "";
		try {
		
				WritableWorkbook book = Workbook.createWorkbook(out);
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Label label =new Label(0,0,"编号");
				sheet.addCell(label);
				label =new Label(1,0,"群组");
				sheet.addCell(label);
				label =new Label(2,0,"分公司");
				sheet.addCell(label);	
				label =new Label(3,0,"车号");
				sheet.addCell(label);	
				label =new Label(4,0,"车辆颜色");
				sheet.addCell(label);
				label =new Label(5,0,"终端类型");
				sheet.addCell(label);	
				label =new Label(6,0,"车辆类型");
				sheet.addCell(label);	
				for( int i =0; i<list.size();i++){
					label =new Label(0,i+1,(i+1)+"");
					sheet.addCell(label);
					label =new Label(1,i+1,list.get(i).getGroupName());
					sheet.addCell(label);
					label =new Label(2,i+1,list.get(i).getBranch());
					sheet.addCell(label);
					label =new Label(3,i+1,list.get(i).getVehicle());
					sheet.addCell(label);
					label =new Label(4,i+1,list.get(i).getColor());
					sheet.addCell(label);
					label =new Label(5,i+1,list.get(i).getTerminalType());
					sheet.addCell(label);
					label =new Label(6,i+1,list.get(i).getVehicleType());
					sheet.addCell(label);
					
				}
				book.write();
				book.close();				
				out.flush();
				out.close();
		} catch (Exception e) {			
			msg ="生成excel失败.xls";
		}
		return msg;
	}
	public static String  exportRegular(List<RegularOffline> list,ByteArrayOutputStream out)  { 
		String msg = "";
		try {
		
				WritableWorkbook book = Workbook.createWorkbook(out);
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Label label =new Label(0,0,"编号");
				sheet.addCell(label);
				label =new Label(1,0,"企业报停数");
				sheet.addCell(label);	
				label =new Label(2,0,"设备故障数量");
				sheet.addCell(label);
				label =new Label(3,0,"SIM卡故障数量");
				sheet.addCell(label);
				label =new Label(4,0,"其他数量");
				sheet.addCell(label);
				label =new Label(5,0,"合计");
				sheet.addCell(label);
				label =new Label(6,0,"登记时间");
				sheet.addCell(label);
				label =new Label(7,0,"登记人");
				sheet.addCell(label);
				for( int i =0; i<list.size();i++){
					label =new Label(0,i+1,(i+1)+"");
					sheet.addCell(label);
					label =new Label(1,i+1,list.get(i).getCpNum()+"");
					sheet.addCell(label);
					label =new Label(2,i+1,list.get(i).getEfNum()+"");
					sheet.addCell(label);
					label =new Label(3,i+1,list.get(i).getSimNum()+"");
					sheet.addCell(label);
					label =new Label(4,i+1,list.get(i).getOtherNum()+"");
					sheet.addCell(label);
					label =new Label(5,i+1,list.get(i).getTotal()+"");
					sheet.addCell(label);
					label =new Label(6,i+1,list.get(i).getOperatingTime());
					sheet.addCell(label);
					label =new Label(7,i+1,list.get(i).getOperatingUser());
					sheet.addCell(label);
				}
				book.write();
				book.close();				
				out.flush();
				out.close();
		} catch (Exception e) {			
			msg ="生成excel失败.xls";
		}
		return msg;
	}
}
