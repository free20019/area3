package com.tw.dao;

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

import javax.print.attribute.Size2DSyntax;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.tw.entity.LOADONLINE;
import com.tw.entity.RECORD;
import com.tw.entity.Area;
import com.tw.entity.Vehicle;
import com.ze.util.DataBese;

public class AreaDao {
	public List<Area>findAreas(){
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_AREA t";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setArea_name(rs.getString("area_name"));
				a.setArea_id(rs.getString("area_id"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询区域内数据
	public List<Area> findarea(String area ,String time ,int i){
		//System.out.println(time);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian=null,zuotian=null,qiantian=null,shangzhou=null,shangyue=null,shangnian=null;
		try {
		Date beginDate = dft.parse(time);
		jintian=time;
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
		zuotian=dft.format(endDate);
		Calendar date7 = Calendar.getInstance();
		date7.setTime(beginDate);
		date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
		Date date8=dft.parse(dft.format(date7.getTime()));
		qiantian=dft.format(date8);
		Calendar date3 = Calendar.getInstance();
		date3.setTime(beginDate);
		date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
		Date date4=dft.parse(dft.format(date3.getTime()));
		shangzhou=dft.format(date4);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int riqi=cal.get(Calendar.DAY_OF_MONTH);
	    Calendar date1 = Calendar.getInstance();
		date1.setTime(beginDate);
		date1.set(Calendar.DATE, date1.get(Calendar.DATE) - riqi);
		Date date2=dft.parse(dft.format(date1.getTime()));
		shangyue=dft.format(date2);
		Calendar date5 = Calendar.getInstance();
		date5.setTime(beginDate);
		date5.set(Calendar.DATE, date5.get(Calendar.DATE) - 365);
		Date date6=dft.parse(dft.format(date5.getTime()));
		shangnian=dft.format(date6);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String d=null;
		if (i==0) {
			d=shangnian;
		}else if (i==1) {
			d=shangyue;
		}else if (i==2) {
			d=shangzhou;
		}else if(i==3){
			d=qiantian;
		}else if (i==4) {
			d=zuotian;
		}else if (i==5) {
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
			if(!area.equals("0")){
				sql+=" and area_id ='"+area+"'";
			}else {
				sql+=" and ("+areasql+")";
			}
			sql+=" order by db_time";
//			System.out.println(sql);
			try {
				String dbtime=null;
				int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0,a13=0,a14=0,a15=0,a16=0,a17=0,a18=0,a19=0,a20=0,a21=0,a22=0,a23=0,a24=0,a25=0,a26=0,a27=0,a28=0,a29=0,a30=0,a31=0,a32=0,a33=0,a34=0,a35=0,a36=0,a37=0,a38=0,a39=0,a40=0,a41=0,a42=0,a43=0,a44=0,a45=0,a46=0,a47=0,a48=0;
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				Area a=new Area();
				while (rs.next()) {
					dbtime=rs.getString("db_time");
					if (dbtime.length()==10||dbtime.substring(11	, 16).equals("00:00")) {
						a1+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("00:30")) {
						a2+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("01:00")) {
						a3+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("01:30")) {
						a4+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("02:00")) {
						a5+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("02:30")) {
						a6+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("03:00")) {
						a7+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("03:30")) {
						a8+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("04:00")) {
						a9+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("04:30")) {
						a10+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("05:00")) {
						a11+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("05:30")) {
						a12+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("06:00")) {
						a13+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("06:30")) {
						a14+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("07:00")) {
						a15+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("07:30")) {
						a16+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("08:00")) {
						a17+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("08:30")) {
						a18+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("09:00")) {
						a19+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("09:30")) {
						a20+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("10:00")) {
						a21+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("10:30")) {
						a22+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("11:00")) {
						a23+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("11:30")) {
						a24+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("12:00")) {
						a25+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("12:30")) {
						a26+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("13:00")) {
						a27+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("13:30")) {
						a28+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("14:00")) {
						a29+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("14:30")) {
						a30+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("15:00")) {
						a31+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("15:30")) {
						a32+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("16:00")) {
						a33+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("16:30")) {
						a34+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("17:00")) {
						a35+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("17:30")) {
						a36+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("18:00")) {
						a37+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("18:30")) {
						a38+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("19:00")) {
						a39+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("19:30")) {
						a40+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("20:00")) {
						a41+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("20:30")) {
						a42+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("21:00")) {
						a43+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("21:30")) {
						a44+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("22:00")) {
						a45+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("22:30")) {
						a46+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("23:00")) {
						a47+=Integer.parseInt(rs.getString("taxi_quantity"));
					}else if (dbtime.substring(11	, 16).equals("23:30")) {
						a48+=Integer.parseInt(rs.getString("taxi_quantity"));
					}
				}
				list1.add(a1+"");list1.add(a2+"");list1.add(a3+"");list1.add(a4+"");list1.add(a5+"");list1.add(a6+"");list1.add(a7+"");list1.add(a8+"");list1.add(a9+"");list1.add(a10+"");
				list1.add(a11+"");list1.add(a12+"");list1.add(a13+"");list1.add(a14+"");list1.add(a15+"");list1.add(a16+"");list1.add(a17+"");list1.add(a18+"");list1.add(a19+"");list1.add(a20+"");
				list1.add(a21+"");list1.add(a22+"");list1.add(a23+"");list1.add(a24+"");list1.add(a25+"");list1.add(a26+"");list1.add(a27+"");list1.add(a28+"");list1.add(a29+"");list1.add(a30+"");
				list1.add(a31+"");list1.add(a32+"");list1.add(a33+"");list1.add(a34+"");list1.add(a35+"");list1.add(a36+"");list1.add(a37+"");list1.add(a38+"");list1.add(a39+"");list1.add(a40+"");
				list1.add(a41+"");list1.add(a42+"");list1.add(a43+"");list1.add(a44+"");list1.add(a45+"");list1.add(a46+"");list1.add(a47+"");list1.add(a48+"");
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
	//pingjun
	public List<Area>findaverage(String quyu,String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
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
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss')";
		if(!quyu.equals("0")){
			sql+=" and area_id ='"+quyu+"'";
		}else {
			sql+=" and ("+areasql+")";
		}
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
		Area zhuanhuan=zhuanh(list);
		List<Area>list1=new ArrayList<Area>();
		list1.add(zhuanhuan);
		return list1;
	}
	public Area zhuanh(List<Area>list){
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
		List<String>area25=new ArrayList<String>();
		List<String>area26=new ArrayList<String>();
		List<String>area27=new ArrayList<String>();
		List<String>area28=new ArrayList<String>();
		List<String>area29=new ArrayList<String>();
		List<String>area30=new ArrayList<String>();
		List<String>area31=new ArrayList<String>();
		List<String>area32=new ArrayList<String>();
		List<String>area33=new ArrayList<String>();
		List<String>area34=new ArrayList<String>();
		List<String>area35=new ArrayList<String>();
		List<String>area36=new ArrayList<String>();
		List<String>area37=new ArrayList<String>();
		List<String>area38=new ArrayList<String>();
		List<String>area39=new ArrayList<String>();
		List<String>area40=new ArrayList<String>();
		List<String>area41=new ArrayList<String>();
		List<String>area42=new ArrayList<String>();
		List<String>area43=new ArrayList<String>();
		List<String>area44=new ArrayList<String>();
		List<String>area45=new ArrayList<String>();
		List<String>area46=new ArrayList<String>();
		List<String>area47=new ArrayList<String>();
		List<String>area48=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals("00:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area1.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("00:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area2.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area3.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("01:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area4.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area5.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("02:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area6.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area7.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("03:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area8.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area9.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("04:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area10.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area11.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("05:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area12.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area13.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("06:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area14.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area15.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("07:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area16.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area17.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("08:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area18.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area19.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("09:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area20.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area21.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("10:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area22.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area23.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("11:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area24.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area25.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("12:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area26.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area27.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("13:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area28.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area29.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("14:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area30.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area31.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("15:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area32.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area33.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("16:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area34.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area35.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("17:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area36.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area37.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("18:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area38.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area39.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("19:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area40.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area41.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("20:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area42.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area43.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("21:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area44.add(list.get(i).getTaxi_quantity());
				}
				
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area45.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("22:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area46.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:00")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area47.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals("23:30")) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area48.add(list.get(i).getTaxi_quantity());
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
		b=0.0;
		if (area25.size()>0) {
		for (int j = 0; j < area25.size(); j++) {
				b+=Double.parseDouble(area25.get(j));
			
		}
		pingjunarea.add(24, (int)(b/7)+"");
		}else {
			pingjunarea.add(24, 0+"");
		}
		b=0.0;
		if (area26.size()>0) {
		for (int j = 0; j < area26.size(); j++) {
				b+=Double.parseDouble(area26.get(j));
			
		}
		pingjunarea.add(25, (int)(b/7)+"");
	}else {
		pingjunarea.add(25, 0+"");
	}
		b=0.0;
		if (area27.size()>0) {
		for (int j = 0; j < area27.size(); j++) {
				b+=Double.parseDouble(area27.get(j));
			
		}
		pingjunarea.add(26, (int)(b/7)+"");
		}else {
			pingjunarea.add(26, 0+"");
		}
		b=0.0;
		if (area28.size()>0) {
		for (int j = 0; j < area28.size(); j++) {
				b+=Double.parseDouble(area28.get(j));
			
		}
		pingjunarea.add(27, (int)(b/7)+"");
	}else {
		pingjunarea.add(27, 0+"");
	}
		b=0.0;
		if (area29.size()>0) {
		for (int j = 0; j < area29.size(); j++) {
				b+=Double.parseDouble(area29.get(j));
			
		}
		pingjunarea.add(28, (int)(b/7)+"");
		}else {
			pingjunarea.add(28, 0+"");
		}
		b=0.0;
		if (area30.size()>0) {
		for (int j = 0; j < area30.size(); j++) {
				b+=Double.parseDouble(area30.get(j));
			
		}
		pingjunarea.add(29, (int)(b/7)+"");
	}else {
		pingjunarea.add(29, 0+"");
	}
		b=0.0;
		if (area31.size()>0) {
		for (int j = 0; j < area31.size(); j++) {
				b+=Double.parseDouble(area31.get(j));
			
		}
		pingjunarea.add(30, (int)(b/7)+"");
		}else {
			pingjunarea.add(30, 0+"");
		}
		b=0.0;
		if (area32.size()>0) {
		for (int j = 0; j < area32.size(); j++) {
				b+=Double.parseDouble(area32.get(j));
			
		}
		pingjunarea.add(31, (int)(b/7)+"");
	}else {
		pingjunarea.add(31, 0+"");
	}
		b=0.0;
		if (area33.size()>0) {
		for (int j = 0; j < area33.size(); j++) {
				b+=Double.parseDouble(area33.get(j));
			
		}
		pingjunarea.add(32, (int)(b/7)+"");
		}else {
			pingjunarea.add(32, 0+"");
		}
		b=0.0;
		if (area34.size()>0) {
		for (int j = 0; j < area34.size(); j++) {
				b+=Double.parseDouble(area34.get(j));
			
		}
		pingjunarea.add(33, (int)(b/7)+"");
	}else {
		pingjunarea.add(33, 0+"");
	}
		b=0.0;
		if (area35.size()>0) {
		for (int j = 0; j < area35.size(); j++) {
				b+=Double.parseDouble(area35.get(j));
			
		}
		pingjunarea.add(34, (int)(b/7)+"");
		}else {
			pingjunarea.add(34, 0+"");
		}
		b=0.0;
		if (area36.size()>0) {
		for (int j = 0; j < area36.size(); j++) {
				b+=Double.parseDouble(area36.get(j));
			
		}
		pingjunarea.add(35, (int)(b/7)+"");
	}else {
		pingjunarea.add(35, 0+"");
	}
		b=0.0;
		if (area37.size()>0) {
		for (int j = 0; j < area37.size(); j++) {
				b+=Double.parseDouble(area37.get(j));
			
		}
		pingjunarea.add(36, (int)(b/7)+"");
		}else {
			pingjunarea.add(36, 0+"");
		}
		b=0.0;
		if (area38.size()>0) {
		for (int j = 0; j < area38.size(); j++) {
				b+=Double.parseDouble(area38.get(j));
			
		}
		pingjunarea.add(37, (int)(b/7)+"");
	}else {
		pingjunarea.add(37, 0+"");
	}
		b=0.0;
		if (area39.size()>0) {
		for (int j = 0; j < area39.size(); j++) {
				b+=Double.parseDouble(area39.get(j));
			
		}
		pingjunarea.add(38, (int)(b/7)+"");
		}else {
			pingjunarea.add(38, 0+"");
		}
		b=0.0;
		if (area40.size()>0) {
		for (int j = 0; j < area40.size(); j++) {
				b+=Double.parseDouble(area40.get(j));
			
		}
		pingjunarea.add(39, (int)(b/7)+"");
	}else {
		pingjunarea.add(39, 0+"");
	}
		b=0.0;
		if (area41.size()>0) {
		for (int j = 0; j < area41.size(); j++) {
				b+=Double.parseDouble(area41.get(j));
			
		}
		pingjunarea.add(40, (int)(b/7)+"");
		}else {
			pingjunarea.add(40, 0+"");
		}
		b=0.0;
		if (area42.size()>0) {
		for (int j = 0; j < area42.size(); j++) {
				b+=Double.parseDouble(area42.get(j));
			
		}
		pingjunarea.add(41, (int)(b/7)+"");
	}else {
		pingjunarea.add(41, 0+"");
	}
		b=0.0;
		if (area43.size()>0) {
		for (int j = 0; j < area43.size(); j++) {
				b+=Double.parseDouble(area43.get(j));
			
		}
		pingjunarea.add(42, (int)(b/7)+"");
		}else {
			pingjunarea.add(42, 0+"");
		}
		b=0.0;
		if (area44.size()>0) {
		for (int j = 0; j < area44.size(); j++) {
				b+=Double.parseDouble(area44.get(j));
			
		}
		pingjunarea.add(43, (int)(b/7)+"");
	}else {
		pingjunarea.add(43, 0+"");
	}
		b=0.0;
		if (area45.size()>0) {
		for (int j = 0; j < area45.size(); j++) {
				b+=Double.parseDouble(area45.get(j));
			
		}
		pingjunarea.add(44, (int)(b/7)+"");
		}else {
			pingjunarea.add(44, 0+"");
		}
		b=0.0;
		if (area46.size()>0) {
		for (int j = 0; j < area46.size(); j++) {
				b+=Double.parseDouble(area46.get(j));
			
		}
		pingjunarea.add(45, (int)(b/7)+"");
	}else {
		pingjunarea.add(45, 0+"");
	}
		b=0.0;
		if (area47.size()>0) {
		for (int j = 0; j < area47.size(); j++) {
				b+=Double.parseDouble(area47.get(j));
			
		}
		pingjunarea.add(46, (int)(b/7)+"");
		}else {
			pingjunarea.add(46, 0+"");
		}
		b=0.0;
		if (area48.size()>0) {
			for (int j = 0; j < area48.size(); j++) {
					b+=Double.parseDouble(area48.get(j));
				
			}
			pingjunarea.add(47, (int)(b/7)+"");
		}else {
			pingjunarea.add(47, 0+"");
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
	public List<Area> getarea() {
		List<Area> results = new ArrayList<Area>();
		String sql = "select * from TB_TAXI_AREA t";
		//System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Area a = new Area();
				a.setId(rs.getString("AREA_ID"));
				a.setName(rs.getString("AREA_NAME"));
				a.setLati1(rs.getString("LATITUDE_MIN"));
				a.setLongi1(rs.getString("LONGITUDE_MIN"));
				a.setLati2(rs.getString("LATITUDE_MIN"));
				a.setLongi2(rs.getString("LONGITUDE_MAX"));
				a.setLati3(rs.getString("LATITUDE_MAX"));
				a.setLongi3(rs.getString("LONGITUDE_MIN"));
				a.setLati4(rs.getString("LATITUDE_MAX"));
				a.setLongi4(rs.getString("LONGITUDE_MAX"));
				a.setBjcars(rs.getString("ALARMNUM"));
				results.add(a);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public int findcars(String id) {
		String sql = "select count(*) c from TB_TAXI_STATUS_NEW t,TB_TAXI_AREA a where (t.LATI<=a.LATITUDE_MAX and t.LATI>=a.LATITUDE_MIN) and (t.LONGI <=a.LONGITUDE_MAX and t.LONGI>=LONGITUDE_MIN) and a.AREA_ID='"+id+"' and t.stime>=sysdate-1/24/60*10 ";
		int count=0;
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count  = rs.getInt("C");
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public int getnum() {
		String sql = "select count(*) c from TB_TAXI_STATUS_NEW t";
		int count=0;
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count  = rs.getInt("C");
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int getonnum() {
		String sql = "select count(*) c from TB_TAXI_STATUS_NEW t where t.stime>=sysdate-1/24/60*15";
		int count=0;
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count  = rs.getInt("C");
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public int getoffnum() {
		String sql = "select count(*) c from TB_TAXI_STATUS_NEW t where t.stime<sysdate-1/24/60*15 or t.db_time is null";
		int count=0;
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count  = rs.getInt("C");
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int gethnum() {
		String sql = "select count(*) c from TB_TAXI_STATUS_NEW t where t.status <> 0 and t.stime>=sysdate-1/24/60*15";
		int count=0;
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count  = rs.getInt("C");
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getnnum() {
		String sql = "select count(*) c from TB_TAXI_STATUS_NEW t where t.status = '0' and t.stime>=sysdate-1/24/60*15";
		int count=0;
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				count  = rs.getInt("C");
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<Vehicle> findvehi() {
		String sql = "select * from TB_TAXI_STATUS_NEW t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.VEHI_NO=v.VEHI_NO";
		List<Vehicle> vehilist = new ArrayList<Vehicle>();
//		System.out.println(sql);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date b = new Date();
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vehicle v = new Vehicle();
				v.setVehino(rs.getString("VEHI_NO"));
				
				if(rs.getString("DB_TIME")!=null){
					String a=rs.getString("DB_TIME").substring(0, 19);
					Long c = b.getTime()-sf.parse(a).getTime();
					long d = c/1000/60;
					//System.out.println(d);
					if(d<=10&&!"0".equals(rs.getString("STATUS"))){
						v.setState("1");//重车
					}else if(d<=10&&"0".equals(rs.getString("STATUS"))){
						v.setState("0");//空车
					}else{
						v.setState("2");
					}
				}else{
					v.setState("2");//
				}
				v.setSimka(rs.getString("VEHI_SIM"));
				v.setCartype(rs.getString("VT_NAME"));
				v.setCompname(rs.getString("COMP_NAME"));
				v.setOwnname(rs.getString("OWN_NAME"));
				v.setOwntel(rs.getString("OWN_TEL"));
				v.setColor(rs.getString("VC_NAME"));
				
				if(rs.getString("LATI")!=null&&rs.getString("LONGI")!=null){
					v.setLati(rs.getDouble("LATI"));
					v.setLongi(rs.getDouble("LONGI"));
					vehilist.add(v);
				}
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(vehilist.size());
		return vehilist;
	}
	public int addarea(String areaname, String areabjs, String areams,String areazbs,String areasize,String uid) {
		String sql = "insert into TB_AREA t (AREA_NAME,AREA_DESCRIBE,AREA_COORDINATES,USER_ID,ALARMNUM,AREA_SIZE) values ('"+areaname+"','"+areams+"','"+areazbs+"','"+uid+"','"+areabjs+"','"+areasize+"')";
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public int addudarea(String areaname, String areams,String areazbs,String areasize,String uid) {
		String sql = "insert into TB_UPDOWNAREA t (AREA_NAME,AREA_DESCRIBE,AREA_COORDINATES,USER_ID,AREA_SIZE) values ('"+areaname+"','"+areams+"','"+areazbs+"','"+uid+"','"+areasize+"')";
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int addvearea(String areaname, String areams,String areazbs,String areasize,String uid) {
		String sql = "insert into TB_VEHIAREA t (AREA_NAME,AREA_DESCRIBE,AREA_COORDINATES,USER_ID,AREA_SIZE) values ('"+areaname+"','"+areams+"','"+areazbs+"','"+uid+"','"+areasize+"')";
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public List<Area> finduserarea(String id) {
		List<Area>list=new ArrayList<Area>();
//		String sql="select * from TB_AREA t order by t.orderid";
		String sql="select * from TB_AREA t where t.user_id='"+id+"' order by t.orderid";// where t.user_id="+id;// where t.user_id="+id;
//		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setId(rs.getString("area_id"));
				a.setAreaname(rs.getString("area_name"));
				a.setAreasize(rs.getString("AREA_SIZE"));
				a.setAreams(rs.getString("AREA_DESCRIBE"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
				a.setOrderid(rs.getString("ORDERID"));
				a.setAll(new ArrayList<String>());
				String nums = rs.getString("ALARMNUM");
				
				for(int i=0;i<nums.split(";").length;i++){
					a.setAreabjs(nums.split(";")[getybjnum()]+"");
				}
				
				//a.setAreabjs(rs.getString("ALARMNUM"));
				
				
				list.add(a);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Area> findudarea(String id) {
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_UPDOWNAREA t order by t.orderid";// where t.user_id="+id;
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setId(rs.getString("area_id"));
				a.setAreaname(rs.getString("area_name"));
				a.setAreasize(rs.getString("AREA_SIZE"));
				a.setAreams(rs.getString("AREA_DESCRIBE"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
				a.setOrderid(rs.getString("ORDERID"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Area> findvearea(String id) {
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_VEHIAREA t order by t.orderid";// where t.user_id="+id;
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setId(rs.getString("area_id"));
				a.setAreaname(rs.getString("area_name"));
				a.setAreasize(rs.getString("AREA_SIZE"));
				a.setAreams(rs.getString("AREA_DESCRIBE"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
				a.setOrderid(rs.getString("ORDERID"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public int deletearea(String id) {
		String sql = "delete from TB_AREA t where t.area_id="+id;
	//	System.out.println(sql);
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int deleteudarea(String id) {
		String sql = "delete from TB_UPDOWNAREA t where t.area_id="+id;
	//	System.out.println(sql);
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int deletevearea(String id) {
		String sql = "delete from TB_VEHIAREA t where t.area_id="+id;
	//	System.out.println(sql);
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Area findbyid(String id) {
		String sql="select * from TB_AREA t where t.area_id="+id;
//		System.out.println(sql);
		Area a=new Area();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				a.setId(rs.getString("area_id"));
				a.setAreaname(rs.getString("area_name"));
				a.setAreasize(rs.getString("AREA_SIZE"));
				a.setAreams(rs.getString("AREA_DESCRIBE"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
				a.setAreabjs(rs.getString("ALARMNUM"));
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
	
	public Area findudbyid(String id) {
		String sql="select * from TB_UPDOWNAREA t where t.area_id="+id;
//		System.out.println(sql);
		Area a=new Area();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				a.setId(rs.getString("area_id"));
				a.setAreaname(rs.getString("area_name"));
				a.setAreasize(rs.getString("AREA_SIZE"));
				a.setAreams(rs.getString("AREA_DESCRIBE"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
				//a.setAreabjs(rs.getString("ALARMNUM"));
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
	public Area findvebyid(String id) {
		String sql="select * from TB_VEHIAREA t where t.area_id="+id;
//		System.out.println(sql);
		Area a=new Area();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				a.setId(rs.getString("area_id"));
				a.setAreaname(rs.getString("area_name"));
				a.setAreasize(rs.getString("AREA_SIZE"));
				a.setAreams(rs.getString("AREA_DESCRIBE"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
				//a.setAreabjs(rs.getString("ALARMNUM"));
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
	public int editarea(String id, String areaname, String areabjs,String areams, String areazbs) {
		String sql = "update TB_AREA t set t.AREA_NAME='"+areaname+"',t.AREA_DESCRIBE='"+areams+"',t.ALARMNUM='"+areabjs+"',t.AREA_COORDINATES='"+areazbs+"' where t.area_id="+id;
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int editudarea(String id, String areaname,String areams, String areazbs) {
		String sql = "update TB_UPDOWNAREA t set t.AREA_NAME='"+areaname+"',t.AREA_DESCRIBE='"+areams+"',t.AREA_COORDINATES='"+areazbs+"' where t.area_id="+id;
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int editvearea(String id, String areaname,String areams, String areazbs) {
		String sql = "update TB_VEHIAREA t set t.AREA_NAME='"+areaname+"',t.AREA_DESCRIBE='"+areams+"',t.AREA_COORDINATES='"+areazbs+"' where t.area_id="+id;
		int count=0;
		try {
			PreparedStatement pstmt =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int getybjnum(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		//System.out.println(sdf.format(new Date()));
		String t = sdf.format(new Date());
		int a = timenum(t);
		if(a==timenum("23:58")||a==timenum("23:59")||a==timenum("00:02")||a==timenum("00:01")){
			return 0;
		}else if(a>=timenum("00:03")&&a<=timenum("00:57")){
			return 1;
		}else if(a>=timenum("00:58")&&a<=timenum("01:02")){
			return 2;
		}else if(a>=timenum("01:03")&&a<=timenum("01:57")){
			return 3;
		}else if(a>=timenum("01:58")&&a<=timenum("02:02")){
			return 4;
		}else if(a>=timenum("02:03")&&a<=timenum("02:57")){
			return 5;
		}else if(a>=timenum("02:58")&&a<=timenum("03:02")){
			return 6;
		}else if(a>=timenum("03:03")&&a<=timenum("03:57")){
			return 7;
		}else if(a>=timenum("03:58")&&a<=timenum("04:02")){
			return 8;
		}else if(a>=timenum("04:03")&&a<=timenum("04:57")){
			return 9;
		}else if(a>=timenum("04:58")&&a<=timenum("05:02")){
			return 10;
		}else if(a>=timenum("05:03")&&a<=timenum("05:57")){
			return 11;
		}else if(a>=timenum("05:58")&&a<=timenum("06:02")){
			return 12;
		}else if(a>=timenum("06:03")&&a<=timenum("06:57")){
			return 13;
		}else if(a>=timenum("06:58")&&a<=timenum("07:02")){
			return 14;
		}else if(a>=timenum("07:03")&&a<=timenum("07:57")){
			return 15;
		}else if(a>=timenum("07:58")&&a<=timenum("08:02")){
			return 16;
		}else if(a>=timenum("08:03")&&a<=timenum("08:57")){
			return 17;
		}else if(a>=timenum("08:58")&&a<=timenum("09:02")){
			return 18;
		}else if(a>=timenum("09:03")&&a<=timenum("09:57")){
			return 19;
		}else if(a>=timenum("09:58")&&a<=timenum("10:02")){
			return 20;
		}else if(a>=timenum("10:03")&&a<=timenum("10:57")){
			return 21;
		}else if(a>=timenum("10:58")&&a<=timenum("11:02")){
			return 22;
		}else if(a>=timenum("11:03")&&a<=timenum("11:57")){
			return 23;
		}else if(a>=timenum("11:58")&&a<=timenum("12:02")){
			return 24;
		}else if(a>=timenum("12:03")&&a<=timenum("12:57")){
			return 25;
		}else if(a>=timenum("12:58")&&a<=timenum("13:02")){
			return 26;
		}else if(a>=timenum("13:03")&&a<=timenum("13:57")){
			return 27;
		}else if(a>=timenum("13:58")&&a<=timenum("14:02")){
			return 28;
		}else if(a>=timenum("14:03")&&a<=timenum("14:57")){
			return 29;
		}else if(a>=timenum("14:58")&&a<=timenum("15:02")){
			return 30;
		}else if(a>=timenum("15:03")&&a<=timenum("15:57")){
			return 31;
		}else if(a>=timenum("15:58")&&a<=timenum("16:02")){
			return 32;
		}else if(a>=timenum("16:03")&&a<=timenum("16:57")){
			return 33;
		}else if(a>=timenum("16:58")&&a<=timenum("17:02")){
			return 34;
		}else if(a>=timenum("17:03")&&a<=timenum("17:57")){
			return 35;
		}else if(a>=timenum("17:58")&&a<=timenum("18:02")){
			return 36;
		}else if(a>=timenum("18:03")&&a<=timenum("18:57")){
			return 37;
		}else if(a>=timenum("18:58")&&a<=timenum("19:02")){
			return 38;
		}else if(a>=timenum("19:03")&&a<=timenum("19:57")){
			return 39;
		}else if(a>=timenum("19:58")&&a<=timenum("20:02")){
			return 40;
		}else if(a>=timenum("20:03")&&a<=timenum("20:57")){
			return 41;
		}else if(a>=timenum("20:58")&&a<=timenum("21:02")){
			return 42;
		}else if(a>=timenum("21:03")&&a<=timenum("21:57")){
			return 43;
		}else if(a>=timenum("21:58")&&a<=timenum("22:02")){
			return 44;
		}else if(a>=timenum("22:03")&&a<=timenum("22:57")){
			return 45;
		}else if(a>=timenum("22:58")&&a<=timenum("23:02")){
			return 46;
		}else if(a>=timenum("23:03")&&a<=timenum("23:57")){
			return 47;
		}else{
			return 0;
		}
	}
	public int timenum(String arg){
		return Integer.parseInt(arg.split(":")[0])*60+Integer.parseInt(arg.split(":")[1]);
	}
	
	
	
	public int paixu(String id1, String id2,String oid1,String oid2) {
		String sql1 = "update TB_AREA t set t.ORDERID='"+oid2+"' where t.area_id="+id1;
		String sql2 = "update TB_AREA t set t.ORDERID='"+oid1+"' where t.area_id="+id2;
		int count=0;
		Connection con=DataBese.getConnectionOfOracle();
		try {
			con.setAutoCommit(false);
			count = con.prepareStatement(sql1).executeUpdate();
			count = con.prepareStatement(sql2).executeUpdate();
			con.commit();
		} catch (Exception e) {
			count=0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	//查询上下客监测点区域
	public List<Area>findupdown(){
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_UPDOWNAREA t";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setArea_id(rs.getString("area_id"));
				a.setArea_name(rs.getString("area_name"));
				list.add(a);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//上下客监测点数据导出
	public List<Vehicle>findupdownexcle(String areaid,String stime,String etime){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from TB_AREA_TAXI_COUNTS_EX t, HZGPS_TAXI.VW_VEHICLE@TAXILINK v ,TB_UPDOWNAREA tb,TB_COMPANY_PHONE p " +
				" where t.taxi_vehi=v.vehi_no  and t.area_id=tb.area_id and v.comp_name=p.compname" ;
		if (!areaid.equals("0")) {
			sql+=" and t.area_id='"+areaid+"'";
		}
		if (stime!=null&&stime.length()>0&&etime!=null&&etime.length()>0) {
			sql+=" and speed_time > to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and" +
			"  speed_time < to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+="order by ba_name,vehi_no ";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setCompname(rs.getString("comp_name")==null?"":rs.getString("comp_name"));
				v.setVehino(rs.getString("vehi_no")==null?"":rs.getString("vehi_no"));
				v.setVehisim(rs.getString("vehi_sim")==null?"":rs.getString("vehi_sim"));
				if (rs.getString("compphone")==null) {
						v.setOwntel(rs.getString("own_tel")==null?"":rs.getString("own_tel"));
				}else {
					v.setOwntel(rs.getString("compphone"));
				}
				v.setOwntel(rs.getString("compphone")==null?rs.getString("own_tel"):rs.getString("compphone"));
				v.setDateTime(rs.getString("speed_time").substring(0, 19));
				v.setSpeed(rs.getString("area_name")==null?"":rs.getString("area_name"));
				if (rs.getString("taxi_status").equals("0")) {
					v.setState("重车至空车");
				}else {
					v.setState("空车至重车");
				}
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
