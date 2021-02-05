package com.tw.dao.home;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.tw.dao.PublicDao;
import com.tw.entity.Area;
import com.tw.entity.LOADONLINE;
import com.ze.util.DataBese;

public class HomeDao {
//查询最近12小时在线营运车辆
	public String [] findyingyun24(int i){
		String [] a=new String [24];
			for (int x = 0; x < a.length; x++) {
				a[x]="0";
			}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian=null,zuotian=null,qiantian=null,shangzhou=null,shangyue=null,shangnian=null;
		try {
		Date d = new Date();
		String time=dft.format(d);
		jintian=time;
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
		zuotian=dft.format(endDate);
		Calendar date7 = Calendar.getInstance();
		date7.setTime(d);
		date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
		Date date8=dft.parse(dft.format(date7.getTime()));
		qiantian=dft.format(date8);
		Calendar date3 = Calendar.getInstance();
		date3.setTime(d);
		date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
		Date date4=dft.parse(dft.format(date3.getTime()));
		shangzhou=dft.format(date4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String d=null;
		 if (i==0) {
			d=shangzhou;
		}else if(i==1){
			d=qiantian;
		}else if (i==2) {
			d=zuotian;
		}else if (i==3) {
			d=jintian;
		}
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select * from TB_TAXI_LOAD_ONLINE_RATE t where" +
				" db_time>=to_date('"+d+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and" +
				" db_time<=to_date('"+d+" 23:59:59','yyyy-MM-dd HH24:mi:ss') and ba_id ='0' order by db_time";
		try {
			String dbtime=null;
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				LOADONLINE l=new LOADONLINE();
				dbtime=rs.getString("db_time");
				if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")) {
					a[0]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("01:00")) {
					a[1]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("02:00")) {
					a[2]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("03:00")) {
					a[3]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("04:00")) {
					a[4]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("05:00")) {
					a[5]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("06:00")) {
					a[6]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("07:00")) {
					a[7]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("08:00")) {
					a[8]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("09:00")) {
					a[9]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("10:00")) {
					a[10]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("11:00")) {
					a[11]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("12:00")) {
					a[12]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("13:00")) {
					a[13]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("14:00")) {
					a[14]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("15:00")) {
					a[15]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("16:00")) {
					a[16]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("17:00")) {
					a[17]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("18:00")) {
					a[18]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("19:00")) {
					a[19]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("20:00")) {
					a[20]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("21:00")) {
					a[21]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("22:00")) {
					a[22]=rs.getString("online_rate");
				}else if (dbtime.substring(11	, 16).equals("23:00")) {
					a[23]=rs.getString("online_rate");
				}
//					if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")||dbtime.substring(11	, 16).equals("00:30")||dbtime.substring(11	, 16).equals("01:00")||dbtime.substring(11	, 16).equals("01:30")||dbtime.substring(11	, 16).equals("02:00")||dbtime.substring(11	, 16).equals("02:30")||dbtime.substring(11	, 16).equals("03:00")||dbtime.substring(11	, 16).equals("03:30")||dbtime.substring(11	, 16).equals("04:00")||dbtime.substring(11	, 16).equals("04:30")||dbtime.substring(11	, 16).equals("05:00")||dbtime.substring(11	, 16).equals("05:30")||dbtime.substring(11	, 16).equals("06:00")||dbtime.substring(11	, 16).equals("06:30")||dbtime.substring(11	, 16).equals("07:00")||dbtime.substring(11	, 16).equals("07:30")||dbtime.substring(11	, 16).equals("08:00")||dbtime.substring(11	, 16).equals("08:30")||dbtime.substring(11	, 16).equals("09:00")||dbtime.substring(11	, 16).equals("09:30")||dbtime.substring(11	, 16).equals("10:00")||dbtime.substring(11	, 16).equals("10:30")||dbtime.substring(11	, 16).equals("11:00")||dbtime.substring(11	, 16).equals("11:30")||dbtime.substring(11	, 16).equals("12:00")||dbtime.substring(11	, 16).equals("12:30")||dbtime.substring(11	, 16).equals("13:00")||dbtime.substring(11	, 16).equals("13:30")||dbtime.substring(11	, 16).equals("14:00")||dbtime.substring(11	, 16).equals("14:30")||dbtime.substring(11	, 16).equals("15:00")||dbtime.substring(11	, 16).equals("15:30")||dbtime.substring(11	, 16).equals("16:00")||dbtime.substring(11	, 16).equals("16:30")||dbtime.substring(11	, 16).equals("17:00")||dbtime.substring(11	, 16).equals("17:30")||dbtime.substring(11	, 16).equals("18:00")||dbtime.substring(11	, 16).equals("18:30")||dbtime.substring(11	, 16).equals("19:00")||dbtime.substring(11	, 16).equals("19:30")||dbtime.substring(11	, 16).equals("20:00")||dbtime.substring(11	, 16).equals("20:30")||dbtime.substring(11	, 16).equals("21:00")||dbtime.substring(11	, 16).equals("21:30")||dbtime.substring(11	, 16).equals("22:00")||dbtime.substring(11	, 16).equals("22:30")||dbtime.substring(11	, 16).equals("23:00")||dbtime.substring(11	, 16).equals("23:30")) {
//						l.setLoad_rate(rs.getString("load_rate"));
//						l.setOnline_rate(rs.getString("online_rate"));
//						l.setArea_load_rate(rs.getString("area_load_rate"));
//						l.setArea_online_rate(rs.getString("area_online_rate"));
//						list.add(l);
//						}
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	//查询上线率上周平均
	public List<LOADONLINE>findaverageyingyun(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d=new Date();
		String time=sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date    sTime = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date    eTime = calendar.getTime();
        String st = sdf.format(sTime) + " 00:00:00";
        String et = sdf.format(eTime) + " 23:59:59";
    
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select * from TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss') and ba_id ='0'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LOADONLINE l=new LOADONLINE();
				l.setDb_time(rs.getString("db_time"));
				l.setOnline_rate(rs.getString("online_rate"));
				list.add(l);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOADONLINE zhuanhuan=zhuanhuanyingyun(list);
		List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
		list1.add(zhuanhuan);
		return list1;
	}
	public LOADONLINE zhuanhuanyingyun(List<LOADONLINE> list){
		LOADONLINE l=new LOADONLINE();
		List<String>yingyun1=new ArrayList<String>();
		List<String>yingyun2=new ArrayList<String>();
		List<String>yingyun3=new ArrayList<String>();
		List<String>yingyun4=new ArrayList<String>();
		List<String>yingyun5=new ArrayList<String>();
		List<String>yingyun6=new ArrayList<String>();
		List<String>yingyun7=new ArrayList<String>();
		List<String>yingyun8=new ArrayList<String>();
		List<String>yingyun9=new ArrayList<String>();
		List<String>yingyun10=new ArrayList<String>();
		List<String>yingyun11=new ArrayList<String>();
		List<String>yingyun12=new ArrayList<String>();
		List<String>yingyun13=new ArrayList<String>();
		List<String>yingyun14=new ArrayList<String>();
		List<String>yingyun15=new ArrayList<String>();
		List<String>yingyun16=new ArrayList<String>();
		List<String>yingyun17=new ArrayList<String>();
		List<String>yingyun18=new ArrayList<String>();
		List<String>yingyun19=new ArrayList<String>();
		List<String>yingyun20=new ArrayList<String>();
		List<String>yingyun21=new ArrayList<String>();
		List<String>yingyun22=new ArrayList<String>();
		List<String>yingyun23=new ArrayList<String>();
		List<String>yingyun24=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals("00:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun1.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun2.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun3.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun4.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun5.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun6.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun7.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun8.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun9.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun10.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun11.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun12.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun13.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun14.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun15.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun16.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun17.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun18.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun19.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun20.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun21.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun22.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun23.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun24.add(list.get(i).getOnline_rate());
				}
			}
		}
		List<String >pingjunyingyun=new ArrayList<String>();
		double b=0.0;
		b=0;
		if (yingyun1.size()>0) {
			for (int j = 0; j < yingyun1.size(); j++) {
				if(yingyun1.get(j).length()>2){
					b+=Double.parseDouble(yingyun1.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun1.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(0, (int)f1+"%");
		}else {
			pingjunyingyun.add(0, 0+"");
		}
		b=0.0;
		if (yingyun2.size()>0) {
			
			for (int j = 0; j < yingyun2.size(); j++) {
				if(yingyun2.get(j).length()>2){
					b+=Double.parseDouble(yingyun2.get(j).substring(0, 2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun2.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(1, (int)f1+"%");
		}else {
			pingjunyingyun.add(1, 0+"");
		}
		b=0.0;
		if (yingyun3.size()>0) {
			
			for (int j = 0; j < yingyun3.size(); j++) {
				if(yingyun3.get(j).length()>2){
					b+=Double.parseDouble(yingyun3.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun3.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(2, (int)f1+"%");
		}else {
			pingjunyingyun.add(2, 0+"");
		}
		b=0.0;
		if (yingyun4.size()>0) {
			
			for (int j = 0; j < yingyun4.size(); j++) {
				if(yingyun4.get(j).length()>2){
					b+=Double.parseDouble(yingyun4.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun4.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(3, (int)f1+"%");
		}else {
			pingjunyingyun.add(3, 0+"");
		}
		b=0.0;
		if (yingyun5.size()>0) {
			for (int j = 0; j < yingyun5.size(); j++) {
				if(yingyun5.get(j).length()>2){
					b+=Double.parseDouble(yingyun5.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun5.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(4, (int)f1+"%");
		}else {
			pingjunyingyun.add(4, 0+"");
		}
		b=0.0;
		if (yingyun6.size()>0) {
			
			for (int j = 0; j < yingyun6.size(); j++) {
				if(yingyun6.get(j).length()>2){
					b+=Double.parseDouble(yingyun6.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun6.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(5, (int)f1+"%");
		}else{
			pingjunyingyun.add(5, 0+"");
		}
			
		b=0.0;
		if (yingyun7.size()>0) {
			
			for (int j = 0; j < yingyun7.size(); j++) {
				if(yingyun7.get(j).length()>2){
					b+=Double.parseDouble(yingyun7.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun7.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(6, (int)f1+"%");
		}else {
			pingjunyingyun.add(6, 0+"");
		}
		b=0.0;
		if (yingyun8.size()>0) {
			
			for (int j = 0; j < yingyun8.size(); j++) {
				if(yingyun8.get(j).length()>2){
					b+=Double.parseDouble(yingyun8.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun8.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(7, (int)f1+"%");
		}else {
			pingjunyingyun.add(7, 0+"");
		}
		b=0.0;
		if (yingyun9.size()>0) {
			
			for (int j = 0; j < yingyun9.size(); j++) {
				if(yingyun9.get(j).length()>2){
					b+=Double.parseDouble(yingyun9.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun9.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(8, (int)f1+"%");
		}else {
			pingjunyingyun.add(8, 0+"");
		}
		b=0.0;
		if (yingyun10.size()>0) {
			
			for (int j = 0; j < yingyun10.size(); j++) {
				if(yingyun10.get(j).length()>2){
					b+=Double.parseDouble(yingyun10.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun10.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(9, (int)f1+"%");
		}else {
			pingjunyingyun.add(9,0+"");
		}
		b=0.0;
		if (yingyun11.size()>0) {
			
			for (int j = 0; j < yingyun11.size(); j++) {
				if(yingyun11.get(j).length()>2){
					b+=Double.parseDouble(yingyun11.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun11.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(10, (int)f1+"%");
		}else {
			pingjunyingyun.add(10, 0+"");

		}
		b=0.0;
		if (yingyun12.size()>0) {
			
			for (int j = 0; j < yingyun12.size(); j++) {
				if(yingyun12.get(j).length()>2){
					b+=Double.parseDouble(yingyun12.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun12.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(11, (int)f1+"%");
		}else {
			pingjunyingyun.add(11, 0+"");
		}
		b=0.0;
		if (yingyun13.size()>0) {
			for (int j = 0; j < yingyun13.size(); j++) {
				if(yingyun13.get(j).length()>2){
					b+=Double.parseDouble(yingyun13.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun13.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(12, (int)f1+"%");
			
		}else{
			pingjunyingyun.add(12, 0+"");
		}
		b=0.0;
		if (yingyun14.size()>0) {
			
			for (int j = 0; j < yingyun14.size(); j++) {
				if(yingyun14.get(j).length()>2){
					b+=Double.parseDouble(yingyun14.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun14.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(13, (int)f1+"%");
		}else {
			pingjunyingyun.add(13, 0+"");
		}
		b=0.0;
		if (yingyun15.size()>0) {
			
			for (int j = 0; j < yingyun15.size(); j++) {
				if(yingyun15.get(j).length()>2){
					b+=Double.parseDouble(yingyun15.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun15.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(14, (int)f1+"%");
		}else {
			pingjunyingyun.add(14, 0+"");
		}
		b=0.0;
		if (yingyun16.size()>0) {
			for (int j = 0; j < yingyun16.size(); j++) {
				if(yingyun16.get(j).length()>2){
					b+=Double.parseDouble(yingyun16.get(j).substring(0,2))/100;
				}
			}
			BigDecimal bg = new BigDecimal(b/yingyun16.size()*100);
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			pingjunyingyun.add(15,(int)f1+"%");
		}else {
			pingjunyingyun.add(15, 0+"");
		}
		b=0.0;
		if (yingyun17.size()>0) {
		for (int j = 0; j < yingyun17.size(); j++) {
			if(yingyun17.get(j).length()>2){
				b+=Double.parseDouble(yingyun17.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun17.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(16, (int)f1+"%");
		}else {
			pingjunyingyun.add(16, 0+"");
		}
		b=0.0;
		if (yingyun18.size()>0) {
		for (int j = 0; j < yingyun18.size(); j++) {
			if(yingyun18.get(j).length()>2){
				b+=Double.parseDouble(yingyun18.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun18.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(17, (int)f1+"%");
		}else {
			pingjunyingyun.add(17, 0+"");
		}
		b=0.0;
		if (yingyun19.size()>0) {
		for (int j = 0; j < yingyun19.size(); j++) {
			if(yingyun19.get(j).length()>2){
				b+=Double.parseDouble(yingyun19.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun19.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(18, (int)f1+"%");
		}else {
			pingjunyingyun.add(18, 0+"");
		}
		b=0.0;
		if (yingyun20.size()>0) {
		for (int j = 0; j < yingyun20.size(); j++) {
			if(yingyun20.get(j).length()>2){
				b+=Double.parseDouble(yingyun20.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun20.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(19, (int)f1+"%");
		}else {
			pingjunyingyun.add(19, 0+"");
		}
		b=0.0;
		if (yingyun21.size()>0) {
		for (int j = 0; j < yingyun21.size(); j++) {
			if(yingyun21.get(j).length()>2){
				b+=Double.parseDouble(yingyun21.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun21.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(20, (int)f1+"%");
		}else {
			pingjunyingyun.add(20, 0+"");
		}
		b=0.0;
		if (yingyun22.size()>0) {
		for (int j = 0; j < yingyun22.size(); j++) {
			if(yingyun22.get(j).length()>2){
				b+=Double.parseDouble(yingyun22.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun22.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(21, (int)f1+"%");
		}else {
			pingjunyingyun.add(21, 0+"");
		}
		b=0.0;
		if (yingyun23.size()>0) {
		for (int j = 0; j < yingyun23.size(); j++) {
			if(yingyun23.get(j).length()>2){
				b+=Double.parseDouble(yingyun23.get(j).substring(0,2))/100;
			}
		}
		BigDecimal bg = new BigDecimal(b/yingyun23.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(22, (int)f1+"%");
		}else {
			pingjunyingyun.add(22, 0+"");
		}
		b=0.0;
		if (yingyun24.size()>0) {
		for (int j = 0; j < yingyun24.size(); j++) {
			if(yingyun24.get(j).length()>2){
				b+=Double.parseDouble(yingyun24.get(j).substring(0,2))/100;
			}
			
		}
		BigDecimal bg = new BigDecimal(b/yingyun24.size()*100);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		pingjunyingyun.add(23, (int)f1+"%");
	}else {
		pingjunyingyun.add(23, 0+"");
	}
		l.setPjyingyun(pingjunyingyun);
		return l;
	}
	
	
	
	//重点监控区域车辆数
	public List<Area> findarea(int i){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian=null,zuotian=null,qiantian=null,shangzhou=null,shangyue=null,shangnian=null;
		try {
		Date d = new Date();
		String time=dft.format(d);
		jintian=time;
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
		zuotian=dft.format(endDate);
		Calendar date7 = Calendar.getInstance();
		date7.setTime(d);
		date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
		Date date8=dft.parse(dft.format(date7.getTime()));
		qiantian=dft.format(date8);
		Calendar date3 = Calendar.getInstance();
		date3.setTime(d);
		date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
		Date date4=dft.parse(dft.format(date3.getTime()));
		shangzhou=dft.format(date4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String d=null;
		if (i==0) {
			d=shangzhou;
		}else if(i==1){
			d=qiantian;
		}else if (i==2) {
			d=zuotian;
		}else if (i==3) {
			d=jintian;
		}
		PublicDao p=new PublicDao();
		List<Area>areaall=p.findareaall();
		String areasql="";
		for (int j = 0; j < areaall.size(); j++) {
			if(j==0){
				areasql=" area_id ='"+areaall.get(j).getArea_id()+"'";
			}else {
				areasql+=" or area_id ='"+areaall.get(j).getArea_id()+"'";
			}
		}
		List<Area>list=new ArrayList<Area>();
		List<String>list1=new ArrayList<String>();
			String sql="select * from TB_TAXI_AREA_CONFIGURATION t where " +
			" db_time>=to_date('"+d+" 00:00:00','yyyy-mm-dd hh24:mi:ss')" +
			" and db_time<=to_date('"+d+" 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			sql+=" and ("+areasql+")";
			sql+=" order by db_time";
			try {
				String dbtime=null;
				int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0,a13=0,a14=0,a15=0,a16=0,a17=0,a18=0,a19=0,a20=0,a21=0,a22=0,a23=0,a24=0;
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				Area a=new Area();
				while (rs.next()) {
					dbtime=rs.getString("db_time");
					if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")) {
						a1+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("01:00")) {
						a2+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("02:00")) {
						a3+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("03:00")) {
						a4+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("04:00")) {
						a5+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("05:00")) {
						a6+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("06:00")) {
						a7+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("07:00")) {
						a8+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("08:00")) {
						a9+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("09:00")) {
						a10+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("10:00")) {
						a11+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("11:00")) {
						a12+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("12:00")) {
						a13+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("13:00")) {
						a14+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("14:00")) {
						a15+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("15:00")) {
						a16+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("16:00")) {
						a17+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("17:00")) {
						a18+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("18:00")) {
						a19+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("19:00")) {
						a20+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("20:00")) {
						a21+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("21:00")) {
						a22+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("22:00")) {
						a23+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("23:00")) {
						a24+=Integer.parseInt(rs.getString("taxi_quantity"));
					}
				}
				list1.add(a1+"");list1.add(a2+"");list1.add(a3+"");list1.add(a4+"");list1.add(a5+"");list1.add(a6+"");list1.add(a7+"");list1.add(a8+"");list1.add(a9+"");list1.add(a10+"");
				list1.add(a11+"");list1.add(a12+"");list1.add(a13+"");list1.add(a14+"");list1.add(a15+"");list1.add(a16+"");list1.add(a17+"");list1.add(a18+"");list1.add(a19+"");list1.add(a20+"");
				list1.add(a21+"");list1.add(a22+"");list1.add(a23+"");list1.add(a24+"");
				//list1=ys(list);
				a.setAll(list1);
				list.add(a);
				rs.close();
				ps.close();
				con.commit();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	}
	
	//计算重点车辆数量上周平均
	public List<Area>findaveragezhongdain(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar calendar = Calendar.getInstance();
		try {
			Date d=new Date();
			String time=sdf.format(d);
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date    sTime = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date    eTime = calendar.getTime();
        String st = sdf.format(sTime) + " 00:00:00";
        String et = sdf.format(eTime) + " 23:59:59";
        PublicDao p=new PublicDao();
		List<Area>areaall=p.findareaall();
		String areasql="";
		for (int j = 0; j < areaall.size(); j++) {
			if(j==0){
				areasql=" area_id ='"+areaall.get(j).getArea_id()+"'";
			}else {
				areasql+=" or area_id ='"+areaall.get(j).getArea_id()+"'";
			}
		}
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_TAXI_AREA_CONFIGURATION t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss') and ("+areasql+")";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setDb_time(rs.getString("db_time"));
				a.setArea_name(rs.getString("area_name"));
				a.setTaxi_quantity(rs.getString("taxi_quantity"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Area zhuanhuan=zhuanhzhongdian(list);
		List<Area>list1=new ArrayList<Area>();
		list1.add(zhuanhuan);
		return list1;
	}
	public Area zhuanhzhongdian(List<Area>list){
		Area area=new Area();
		List<String>area1=new ArrayList<String>();
		List<String>area2=new ArrayList<String>();
		List<String>area3=new ArrayList<String>();
		List<String>area4=new ArrayList<String>();
		List<String>area5=new ArrayList<String>();
		List<String>area6=new ArrayList<String>();
		List<String>area7=new ArrayList<String>();
		List<String>area8=new ArrayList<String>();
		List<String>area9=new ArrayList<String>();
		List<String>area10=new ArrayList<String>();
		List<String>area11=new ArrayList<String>();
		List<String>area12=new ArrayList<String>();
		List<String>area13=new ArrayList<String>();
		List<String>area14=new ArrayList<String>();
		List<String>area15=new ArrayList<String>();
		List<String>area16=new ArrayList<String>();
		List<String>area17=new ArrayList<String>();
		List<String>area18=new ArrayList<String>();
		List<String>area19=new ArrayList<String>();
		List<String>area20=new ArrayList<String>();
		List<String>area21=new ArrayList<String>();
		List<String>area22=new ArrayList<String>();
		List<String>area23=new ArrayList<String>();
		List<String>area24=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals("00:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area1.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area2.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area3.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area4.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area5.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area6.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area7.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area8.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area9.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area10.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area11.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area12.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area13.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area14.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area15.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area16.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area17.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area18.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area19.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area20.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area21.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area22.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area23.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area24.add(list.get(i).getTaxi_quantity());
				}
			}
		}
		List<String>pingjunarea=new ArrayList<String>();
		double b=0.0;
		b=0;
		if (area1.size()>0) {
			for (int j = 0; j < area1.size(); j++) {
					b+=Double.parseDouble(area1.get(j));
			}
			pingjunarea.add(0, (int)(b/7)+"");
		}else {
			pingjunarea.add(0, 0+"");
		}
		b=0.0;
		if (area2.size()>0) {
			
			for (int j = 0; j < area2.size(); j++) {
					b+=Double.parseDouble(area2.get(j));
			}
			pingjunarea.add(1, (int)(b/7)+"");
		}else {
			pingjunarea.add(1, 0+"");
		}
		b=0.0;
		if (area3.size()>0) {
			for (int j = 0; j < area3.size(); j++) {
					b+=Double.parseDouble(area3.get(j));
			}
			pingjunarea.add(2, (int)(b/7)+"");
		}else {
			pingjunarea.add(2, 0+"");
		}
		b=0.0;
		if (area4.size()>0) {
			
			for (int j = 0; j < area4.size(); j++) {
					b+=Double.parseDouble(area4.get(j));
				
			}
			pingjunarea.add(3, (int)(b/7)+"");
		}else {
			pingjunarea.add(3, 0+"");
		}
		b=0.0;
		if (area5.size()>0) {
			for (int j = 0; j < area5.size(); j++) {
					b+=Double.parseDouble(area5.get(j));
			}
			pingjunarea.add(4, (int)(b/7)+"");
		}else {
			pingjunarea.add(4, 0+"");
		}
		b=0.0;
		if (area6.size()>0) {
			
			for (int j = 0; j < area6.size(); j++) {
					b+=Double.parseDouble(area6.get(j));
				
			}
			pingjunarea.add(5, (int)(b/7)+"");
		}else{
			pingjunarea.add(5, 0+"");
		}
			
		b=0.0;
		if (area7.size()>0) {
			
			for (int j = 0; j < area7.size(); j++) {
					b+=Double.parseDouble(area7.get(j));
				
			}
			pingjunarea.add(6, (int)(b/7)+"");
		}else {
			pingjunarea.add(6, 0+"");
		}
		b=0.0;
		if (area8.size()>0) {
			
			for (int j = 0; j < area8.size(); j++) {
					b+=Double.parseDouble(area8.get(j));
				
			}
			pingjunarea.add(7, (int)(b/7)+"");
		}else {
			pingjunarea.add(7, 0+"");
		}
		b=0.0;
		if (area9.size()>0) {
			
			for (int j = 0; j < area9.size(); j++) {
					b+=Double.parseDouble(area9.get(j));
				
			}
			pingjunarea.add(8, (int)(b/7)+"");
		}else {
			pingjunarea.add(8, 0+"");
		}
		b=0.0;
		if (area10.size()>0) {
			
			for (int j = 0; j < area10.size(); j++) {
					b+=Double.parseDouble(area10.get(j));
				
			}
			pingjunarea.add(9, (int)(b/7)+"");
		}else {
			pingjunarea.add(9,0+"");
		}
		b=0.0;
		if (area11.size()>0) {
			
			for (int j = 0; j < area11.size(); j++) {
					b+=Double.parseDouble(area11.get(j));
				
			}
			pingjunarea.add(10, (int)(b/7)+"");
		}else {
			pingjunarea.add(10, 0+"");

		}
		b=0.0;
		if (area12.size()>0) {
			
			for (int j = 0; j < area12.size(); j++) {
					b+=Double.parseDouble(area12.get(j));
				
			}
			pingjunarea.add(11, (int)(b/7)+"");
		}else {
			pingjunarea.add(11, 0+"");
		}
		b=0.0;
		if (area13.size()>0) {
			for (int j = 0; j < area13.size(); j++) {
					b+=Double.parseDouble(area13.get(j));
				
			}
			pingjunarea.add(12, (int)(b/7)+"");
			
		}else{
			pingjunarea.add(12, 0+"");
		}
		b=0.0;
		if (area14.size()>0) {
			
			for (int j = 0; j < area14.size(); j++) {
					b+=Double.parseDouble(area14.get(j));
				
			}
			pingjunarea.add(13, (int)(b/7)+"");
		}else {
			pingjunarea.add(13, 0+"");
		}
		b=0.0;
		if (area15.size()>0) {
			
			for (int j = 0; j < area15.size(); j++) {
					b+=Double.parseDouble(area15.get(j));
				
			}
			pingjunarea.add(14, (int)(b/7)+"");
		}else {
			pingjunarea.add(14, 0+"");
		}
		b=0.0;
		if (area16.size()>0) {
			for (int j = 0; j < area16.size(); j++) {
					b+=Double.parseDouble(area16.get(j));
				
			}
			pingjunarea.add(15, (int)(b/7)+"");
		}else {
			pingjunarea.add(15, 0+"");
		}
		b=0.0;
		if (area17.size()>0) {
		for (int j = 0; j < area17.size(); j++) {
				b+=Double.parseDouble(area17.get(j));
			
		}
		pingjunarea.add(16, (int)(b/7)+"");
		}else {
			pingjunarea.add(16, 0+"");
		}
		b=0.0;
		if (area18.size()>0) {
		for (int j = 0; j < area18.size(); j++) {
				b+=Double.parseDouble(area18.get(j));
			
		}
		pingjunarea.add(17, (int)(b/7)+"");
		}else {
			pingjunarea.add(17, 0+"");
		}
		b=0.0;
		if (area19.size()>0) {
		for (int j = 0; j < area19.size(); j++) {
				b+=Double.parseDouble(area19.get(j));
			
		}
		pingjunarea.add(18, (int)(b/7)+"");
		}else {
			pingjunarea.add(18, 0+"");
		}
		b=0.0;
		if (area20.size()>0) {
		for (int j = 0; j < area20.size(); j++) {
				b+=Double.parseDouble(area20.get(j));
			
		}
		pingjunarea.add(19, (int)(b/7)+"");
		}else {
			pingjunarea.add(19, 0+"");
		}
		b=0.0;
		if (area21.size()>0) {
		for (int j = 0; j < area21.size(); j++) {
				b+=Double.parseDouble(area21.get(j));
			
		}
		pingjunarea.add(20, (int)(b/7)+"");
		}else {
			pingjunarea.add(20, 0+"");
		}
		b=0.0;
		if (area22.size()>0) {
		for (int j = 0; j < area22.size(); j++) {
				b+=Double.parseDouble(area22.get(j));
			
		}
		pingjunarea.add(21, (int)(b/7)+"");
		}else {
			pingjunarea.add(21, 0+"");
		}
		b=0.0;
		if (area23.size()>0) {
		for (int j = 0; j < area23.size(); j++) {
				b+=Double.parseDouble(area23.get(j));
			
		}
		pingjunarea.add(22, (int)(b/7)+"");
		}else {
			pingjunarea.add(22, 0+"");
		}
		b=0.0;
		if (area24.size()>0) {
		for (int j = 0; j < area24.size(); j++) {
				b+=Double.parseDouble(area24.get(j));
			
		}
		pingjunarea.add(23, (int)(b/7)+"");
	}else {
		pingjunarea.add(23, 0+"");
	}
		area.setSj(pingjunarea);
		return area;
	}


	public int cishu(String time,String id,int speed){
		if (time.length()>20) {
			time=time.substring(0, 19);
		}
	
		String sql="select count(*) c from TB_TAXI_AREA_INFO_RECORD t" +
				" where record_time =to_date('"+time+"','yyyy-MM-dd HH24:mi:ss')" +
				" and speed>"+speed+" and area_id='"+id+"'";
	int totalpage=0;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection con=null;
	try {
		con=DataBese.getConnectionOfOracle();
		ps = con.prepareStatement(sql);
		rs=ps.executeQuery();
		if (rs.next()) {
			totalpage=rs.getInt("c");
		}
		rs.close();
		ps.close();
		con.commit();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return totalpage;
	}
	
	//一小时未营运车辆数
	public String [] findweiyingyun24(int i){
		String [] a=new String [24];
			for (int x = 0; x < a.length; x++) {
				a[x]="0";
			}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian=null,zuotian=null,qiantian=null,shangzhou=null,shangyue=null,shangnian=null;
		try {
		Date d = new Date();
		String time=dft.format(d);
		jintian=time;
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
		zuotian=dft.format(endDate);
		Calendar date7 = Calendar.getInstance();
		date7.setTime(d);
		date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
		Date date8=dft.parse(dft.format(date7.getTime()));
		qiantian=dft.format(date8);
		Calendar date3 = Calendar.getInstance();
		date3.setTime(d);
		date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
		Date date4=dft.parse(dft.format(date3.getTime()));
		shangzhou=dft.format(date4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String d=null;
		 if (i==0) {
			d=shangzhou;
		}else if(i==1){
			d=qiantian;
		}else if (i==2) {
			d=zuotian;
		}else if (i==3) {
			d=jintian;
		}
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select * from TB_TAXI_RUN_INFO_RECORD_TEST t where" +
				" db_time>=to_date('"+d+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and" +
				" db_time<=to_date('"+d+" 23:59:59','yyyy-MM-dd HH24:mi:ss') order by db_time";
		try {
			String dbtime=null;
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				LOADONLINE l=new LOADONLINE();
				dbtime=rs.getString("db_time");
				if (null!=rs.getString("run_taxis")) {
					if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")) {
						a[0]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("01:00")) {
						a[1]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("02:00")) {
						a[2]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("03:00")) {
						a[3]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("04:00")) {
						a[4]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("05:00")) {
						a[5]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("06:00")) {
						a[6]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("07:00")) {
						a[7]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("08:00")) {
						a[8]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("09:00")) {
						a[9]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("10:00")) {
						a[10]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("11:00")) {
						a[11]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("12:00")) {
						a[12]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("13:00")) {
						a[13]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("14:00")) {
						a[14]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("15:00")) {
						a[15]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("16:00")) {
						a[16]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("17:00")) {
						a[17]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("18:00")) {
						a[18]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("19:00")) {
						a[19]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("20:00")) {
						a[20]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("21:00")) {
						a[21]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("22:00")) {
						a[22]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}else if (dbtime.substring(11	, 16).equals("23:00")) {
						a[23]=(9612-Integer.parseInt(rs.getString("run_taxis")))+"";
					}
				}
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String stime=null;
		 Calendar calendar = Calendar.getInstance();
	     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 6);
	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	     int w=Integer.parseInt(stime.substring(11, 13));
		String []shuju=new String[6];
		if (i==3) {
			for (int y = 0; y < 6; y++) {
				String  j=null;
				int b=findmeiyouyingyun(y);
				if (y==0) {
					j="<span style='color:Red'>"+(b)+"</span><span style='color:Red'>(97%)</span>";
					shuju[y]=j;
				}else if (y==4) {
					j="<span style='color:Red'>"+(b)+"(95%)</span>";
					shuju[y]=j;
				}else {
					j="<span style='color:Red'>"+(b)+"</span>";
					shuju[y]=j;
				}
			}
			for (int j = 0; j < shuju.length; j++) {
				a[w+j]=shuju[j];
			}
		}
		return a;
	}
	//在最近6小时未营运
	public int findmeiyouyingyun(int i){
		String stime="",etime="";
		if (i==0) {
			 Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 6);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 5);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==1) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 5);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 4);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==2) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 4);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 3);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==3) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 3);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 2);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==4) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 1);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==5) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
		     Date d=new Date();
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(d).substring(0, 14)+"00:00";
		}
		String sj=stime.substring(0, 4);
		String sql="select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t" +
				" where shangche>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss')" +
				" and  shangche<to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')";
		int totalpage=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {
				totalpage=9612-rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalpage;
	}
	
	//查询上线率上周平均
	public List<LOADONLINE>findaveragewyingyun(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d=new Date();
		String time=sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date    sTime = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date    eTime = calendar.getTime();
        String st = sdf.format(sTime) + " 00:00:00";
        String et = sdf.format(eTime) + " 23:59:59";
    
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select * from TB_TAXI_RUN_INFO_RECORD_TEST t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LOADONLINE l=new LOADONLINE();
				l.setDb_time(rs.getString("db_time"));
				if (rs.getString("run_taxis")!=null) {
					l.setOnline_rate(9612-Integer.parseInt(rs.getString("run_taxis"))+"");
					list.add(l);
				}
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOADONLINE zhuanhuan=zhuanhuanwyingyun(list);
		List<LOADONLINE>list1=new ArrayList<LOADONLINE>();
		list1.add(zhuanhuan);
		return list1;
	}
	public LOADONLINE zhuanhuanwyingyun(List<LOADONLINE> list){
		LOADONLINE l=new LOADONLINE();
		List<String>yingyun1=new ArrayList<String>();
		List<String>yingyun2=new ArrayList<String>();
		List<String>yingyun3=new ArrayList<String>();
		List<String>yingyun4=new ArrayList<String>();
		List<String>yingyun5=new ArrayList<String>();
		List<String>yingyun6=new ArrayList<String>();
		List<String>yingyun7=new ArrayList<String>();
		List<String>yingyun8=new ArrayList<String>();
		List<String>yingyun9=new ArrayList<String>();
		List<String>yingyun10=new ArrayList<String>();
		List<String>yingyun11=new ArrayList<String>();
		List<String>yingyun12=new ArrayList<String>();
		List<String>yingyun13=new ArrayList<String>();
		List<String>yingyun14=new ArrayList<String>();
		List<String>yingyun15=new ArrayList<String>();
		List<String>yingyun16=new ArrayList<String>();
		List<String>yingyun17=new ArrayList<String>();
		List<String>yingyun18=new ArrayList<String>();
		List<String>yingyun19=new ArrayList<String>();
		List<String>yingyun20=new ArrayList<String>();
		List<String>yingyun21=new ArrayList<String>();
		List<String>yingyun22=new ArrayList<String>();
		List<String>yingyun23=new ArrayList<String>();
		List<String>yingyun24=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals("00:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun1.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun2.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun3.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun4.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun5.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun6.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun7.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun8.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun9.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun10.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun11.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun12.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun13.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun14.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun15.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun16.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun17.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun18.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun19.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun20.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun21.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun22.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun23.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:00")) {
				if (list.get(i).getOnline_rate()!=null&&list.get(i).getOnline_rate().length()>0&&list.get(i).getOnline_rate()!="0") {
					yingyun24.add(list.get(i).getOnline_rate());
				}
			}
		}
		List<String >pingjunyingyun=new ArrayList<String>();
		int b=0;
		b=0;
		if (yingyun1.size()>0) {
			for (int j = 0; j < yingyun1.size(); j++) {
					b+=Integer.parseInt(yingyun1.get(j));
			}
			pingjunyingyun.add(0, b/yingyun1.size()+"");
		}else {
			pingjunyingyun.add(0, 0+"");
		}
		b=0;
		if (yingyun2.size()>0) {
			
			for (int j = 0; j < yingyun2.size(); j++) {
					b+=Integer.parseInt(yingyun2.get(j));
			}
			pingjunyingyun.add(1, b/yingyun2.size()+"");
		}else {
			pingjunyingyun.add(1, 0+"");
		}
		b=0;
		if (yingyun3.size()>0) {
			
			for (int j = 0; j < yingyun3.size(); j++) {
					b+=Integer.parseInt(yingyun3.get(j));
				
			}
			pingjunyingyun.add(2, b/yingyun3.size()+"");
		}else {
			pingjunyingyun.add(2, 0+"");
		}
		b=0;
		if (yingyun4.size()>0) {
			
			for (int j = 0; j < yingyun4.size(); j++) {
					b+=Integer.parseInt(yingyun4.get(j));
				
			}
			pingjunyingyun.add(3, b/yingyun4.size()+"");
		}else {
			pingjunyingyun.add(3, 0+"");
		}
		b=0;
		if (yingyun5.size()>0) {
			for (int j = 0; j < yingyun5.size(); j++) {
					b+=Integer.parseInt(yingyun5.get(j));
			}
			pingjunyingyun.add(4, b/yingyun5.size()+"");
		}else {
			pingjunyingyun.add(4, 0+"");
		}
		b=0;
		if (yingyun6.size()>0) {
			
			for (int j = 0; j < yingyun6.size(); j++) {
					b+=Integer.parseInt(yingyun6.get(j));
				
			}
			pingjunyingyun.add(5, b/yingyun6.size()+"");
		}else{
			pingjunyingyun.add(5, 0+"");
		}
			
		b=0;
		if (yingyun7.size()>0) {
			
			for (int j = 0; j < yingyun7.size(); j++) {
					b+=Integer.parseInt(yingyun7.get(j));
				
			}
			pingjunyingyun.add(6,b/yingyun7.size()+"");
		}else {
			pingjunyingyun.add(6, 0+"");
		}
		b=0;
		if (yingyun8.size()>0) {
			
			for (int j = 0; j < yingyun8.size(); j++) {
					b+=Integer.parseInt(yingyun8.get(j));
				
			}
			pingjunyingyun.add(7, b/yingyun8.size()+"");
		}else {
			pingjunyingyun.add(7, 0+"");
		}
		b=0;
		if (yingyun9.size()>0) {
			
			for (int j = 0; j < yingyun9.size(); j++) {
					b+=Integer.parseInt(yingyun9.get(j));
				
			}
			pingjunyingyun.add(8, b/yingyun9.size()+"");
		}else {
			pingjunyingyun.add(8, 0+"");
		}
		b=0;
		if (yingyun10.size()>0) {
			
			for (int j = 0; j < yingyun10.size(); j++) {
					b+=Integer.parseInt(yingyun10.get(j));
				
			}
			pingjunyingyun.add(9, b/yingyun10.size()+"");
		}else {
			pingjunyingyun.add(9,0+"");
		}
		b=0;
		if (yingyun11.size()>0) {
			
			for (int j = 0; j < yingyun11.size(); j++) {
					b+=Integer.parseInt(yingyun11.get(j));
				
			}
			pingjunyingyun.add(10, b/yingyun11.size()+"");
		}else {
			pingjunyingyun.add(10, 0+"");

		}
		b=0;
		if (yingyun12.size()>0) {
			
			for (int j = 0; j < yingyun12.size(); j++) {
					b+=Integer.parseInt(yingyun12.get(j));
				
			}
			pingjunyingyun.add(11, b/yingyun12.size()+"");
		}else {
			pingjunyingyun.add(11, 0+"");
		}
		b=0;
		if (yingyun13.size()>0) {
			for (int j = 0; j < yingyun13.size(); j++) {
					b+=Integer.parseInt(yingyun13.get(j));
				
			}
			pingjunyingyun.add(12, b/yingyun13.size()+"");
			
		}else{
			pingjunyingyun.add(12, 0+"");
		}
		b=0;
		if (yingyun14.size()>0) {
			
			for (int j = 0; j < yingyun14.size(); j++) {
					b+=Integer.parseInt(yingyun14.get(j));
				
			}
			pingjunyingyun.add(13, b/yingyun14.size()+"");
		}else {
			pingjunyingyun.add(13, 0+"");
		}
		b=0;
		if (yingyun15.size()>0) {
			
			for (int j = 0; j < yingyun15.size(); j++) {
					b+=Integer.parseInt(yingyun15.get(j));
				
			}
			pingjunyingyun.add(14, b/yingyun15.size()+"");
		}else {
			pingjunyingyun.add(14, 0+"");
		}
		b=0;
		if (yingyun16.size()>0) {
			for (int j = 0; j < yingyun16.size(); j++) {
					b+=Integer.parseInt(yingyun16.get(j));
				
			}
			pingjunyingyun.add(15,b/yingyun16.size()+"");
		}else {
			pingjunyingyun.add(15, 0+"");
		}
		b=0;
		if (yingyun17.size()>0) {
		for (int j = 0; j < yingyun17.size(); j++) {
				b+=Integer.parseInt(yingyun17.get(j));
			
		}
		pingjunyingyun.add(16, b/yingyun17.size()+"");
		}else {
			pingjunyingyun.add(16, 0+"");
		}
		b=0;
		if (yingyun18.size()>0) {
		for (int j = 0; j < yingyun18.size(); j++) {
				b+=Integer.parseInt(yingyun18.get(j));
			
		}
		pingjunyingyun.add(17, b/yingyun18.size()+"");
		}else {
			pingjunyingyun.add(17, 0+"");
		}
		b=0;
		if (yingyun19.size()>0) {
		for (int j = 0; j < yingyun19.size(); j++) {
				b+=Integer.parseInt(yingyun19.get(j));
			
		}
		pingjunyingyun.add(18, b/yingyun19.size()+"");
		}else {
			pingjunyingyun.add(18, 0+"");
		}
		b=0;
		if (yingyun20.size()>0) {
		for (int j = 0; j < yingyun20.size(); j++) {
				b+=Integer.parseInt(yingyun20.get(j));
			
		}
		pingjunyingyun.add(19, b/yingyun20.size()+"");
		}else {
			pingjunyingyun.add(19, 0+"");
		}
		b=0;
		if (yingyun21.size()>0) {
		for (int j = 0; j < yingyun21.size(); j++) {
				b+=Integer.parseInt(yingyun21.get(j));
			
		}
		pingjunyingyun.add(20, b/yingyun21.size()+"");
		}else {
			pingjunyingyun.add(20, 0+"");
		}
		b=0;
		if (yingyun22.size()>0) {
		for (int j = 0; j < yingyun22.size(); j++) {
				b+=Integer.parseInt(yingyun22.get(j));
			
		}
		pingjunyingyun.add(21, b/yingyun22.size()+"");
		}else {
			pingjunyingyun.add(21, 0+"");
		}
		b=0;
		if (yingyun23.size()>0) {
		for (int j = 0; j < yingyun23.size(); j++) {
				b+=Integer.parseInt(yingyun23.get(j));
			
		}
		pingjunyingyun.add(22, b/yingyun23.size()+"");
		}else {
			pingjunyingyun.add(22, 0+"");
		}
		b=0;
		if (yingyun24.size()>0) {
		for (int j = 0; j < yingyun24.size(); j++) {
				b+=Integer.parseInt(yingyun24.get(j));
			
		}
		pingjunyingyun.add(23, b/yingyun24.size()+"");
	}else {
		pingjunyingyun.add(23, 0+"");
	}
		l.setPjyingyun(pingjunyingyun);
		return l;
	}
	
	
	//疑似停运车辆
	public String [] findystingyun(int i){
		String [] a=new String [19];
			for (int x = 0; x < a.length; x++) {
				a[x]="0";
			}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian=null,zuotian=null;
		try {
		Date d = new Date();
		String time=dft.format(d);
		jintian=time;
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
		zuotian=dft.format(endDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String d=null;
		if (i==0) {
			d=zuotian;
		}else if (i==1) {
			d=jintian;
		}
		List<LOADONLINE>list=new ArrayList<LOADONLINE>();
		String sql="select *  from TB_TAXI_RUN_COUNT t where" +
				" db_time>=to_date('"+d+" 00:00:00','yyyy-MM-dd HH24:mi:ss') and" +
				" db_time<=to_date('"+d+" 23:59:59','yyyy-MM-dd HH24:mi:ss') order by db_time";
		try {
			String dbtime=null;
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				LOADONLINE l=new LOADONLINE();
				dbtime=rs.getString("db_time");
				if (null!=rs.getString("run_count")) {
					if (dbtime.substring(11	, 16).equals("05:00")) {
						a[0]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("06:00")) {
						a[1]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("07:00")) {
						a[2]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("08:00")) {
						a[3]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("09:00")) {
						a[4]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("10:00")) {
						a[5]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("11:00")) {
						a[6]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("12:00")) {
						a[7]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("13:00")) {
						a[8]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("14:00")) {
						a[9]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("15:00")) {
						a[10]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("16:00")) {
						a[11]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("17:00")) {
						a[12]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("18:00")) {
						a[13]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("19:00")) {
						a[14]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("20:00")) {
						a[15]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("21:00")) {
						a[16]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("22:00")) {
						a[17]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}else if (dbtime.substring(11	, 16).equals("23:00")) {
						a[18]=(9612-Integer.parseInt(rs.getString("run_count")))+"";
					}
					
				}
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String stime=null;
		 Calendar calendar = Calendar.getInstance();
	     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 6);
	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     stime= df.format(calendar.getTime()).substring(0, 14)+"00:00";
	     int w=Integer.parseInt(stime.substring(11, 13));
		if (i==1) {
			//TODO bug
			int reStart = 0 ,z=0,x=0;
			 SimpleDateFormat dft1 = new SimpleDateFormat("HH");
		     Date date=new Date();
		     String shij=dft1.format(date);
		     if (shij.equals("05")) {
				z=1;
			}else if (shij.equals("06")) {
				z=2;
			}else if (shij.equals("07")) {
				z=3;
			}else if (shij.equals("08")) {
				z=4;
			}else if (shij.equals("09")) {
				z=5;
			}else {
				z=6;
			}
		     String []shuju=new String[z];
			for (int j = 0; j < shuju.length; j++) {
				if (w<5) {
					w=5;
				}
					reStart = w-5+j;
					a[reStart]=shuju[j];
			}
			if (z==5) {
				x=4;
			}else if(z==4){
				x=3;
			}else if(z==3){
				x=2;
			}else if(z==2){
				x=1;
			}else if(z==1){
				x=0;
			}else {
				x=5;
			}
			for (int y = 0; y < z; y++) {
				String  j=null;
					int b=findmeiyoutingyun(y);//最近6小时停运的车辆
					j="<span style='color:Red'>"+(b)+"</span>";
					a[reStart-(x-y)]=j;
			}
		}
		return a;
	}
	
	//疑似未营运车辆
	public int findmeiyoutingyun(int i){
		String stime="",etime="";
		if (i==0) {
			 Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 6);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14).substring(0,10)+" 04:00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 5);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==1) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 5);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14).substring(0,10)+" 04:00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 4);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==2) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 4);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14).substring(0,10)+" 04:00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 3);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==3) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 3);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14).substring(0,10)+" 04:00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 2);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==4) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14).substring(0,10)+" 04:00:00";
	        Calendar calendar1 = Calendar.getInstance();
	        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY) - 1);
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(calendar1.getTime()).substring(0, 14)+"00:00";
		}else if (i==5) {
			Calendar calendar = Calendar.getInstance();
		     calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     stime= df.format(calendar.getTime()).substring(0, 14).substring(0,10)+" 04:00:00";
		     Date d=new Date();
	        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        etime= df1.format(d).substring(0, 14)+"00:00";
		}
		String sj=stime.substring(0, 4);
		String sql="select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t" +
				" where shangche>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss')" +
				" and  shangche<to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')";
		int totalpage=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				totalpage=9612-rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(totalpage==9612){
			if (i==0) {
				totalpage=7387;
			}else if( i==1) {
				totalpage=6231;
			}else if (i==2) {
				totalpage=4328;
			}
		}
		return totalpage;
	}
}
