package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tw.entity.Area;
import com.tw.entity.Vehicle;
import com.tw.entity.Vhic;
import com.ze.util.DataBese;
import com.ze.util.VhicComparator;


public class FindDao {
	//查询总车辆
	public List<Vhic>findvehino(String stime,String etime,String data,String areaid,String compid){
		List<Vhic>list=new ArrayList<Vhic>();
		String [] ids = data.split(",");
		String comps = "";
		String sj=stime.substring(0, 4);
		if (ids.length==1&&!ids[0].equals("所有公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"' and t.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
			comps +="and ( t.ba_name='"+ids[0]+"' and t.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
			comps +="and ( t.ba_name='"+ids[0]+"' and t.comp_name='"+ids[1]+"' and t.vehi_no='"+ids[2]+"')";
		}
		String sql="select vehi_no,comp_name,own_name,own_tel,vehi_sim from" +
				" HZGPS_TAXI.VW_VEHICLE@TAXILINK t where vehi_no like '%浙AT%' " ;
		if (!ids[0].equals("所有公司")) {
			sql+=comps;
		}else {
			if (!areaid.equals("0")&&areaid.length()>1) {
				sql+="and ba_id ='"+areaid+"'";
				if (!compid.equals("0")) {
					sql+="and comp_id='"+compid+"'";
				}
			}
		}
				sql+=" minus " +
				" select vehi_no,comp_name,own_name,own_tel,vehi_sim from (select * from" +
				" (select distinct (vhic) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t" +
				" where xiache>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss') and" +
				" xiache<=to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')) a," +
				" HZGPS_TAXI.VW_VEHICLE@TAXILINK t where '浙'||a.c=t.vehi_no " ;
				if (!ids[0].equals("所有公司")) {
					sql+=comps;
				}else {
					if (!areaid.equals("0")&&areaid.length()>1) {
						sql+="and ba_id ='"+areaid+"'";
						if (!compid.equals("0")) {
							sql+="and comp_id='"+compid+"'";
						}
					}
				}
				sql+=")";
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con=DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//监控点车辆明细查询
	public List<String >findmingxi(String quyu,String time,String speed,String data){
		List<String >list=new ArrayList<String>();
		if (quyu.equals("所有区域")) {
			String sql="select * from TB_AREA_VEHINO t where time=to_date('"+time+"','yyyy-mm-dd')";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				String sj="";
				while(rs.next()){
					sj=rs.getString("no");
				}
				String[] s=sj.split(",");
				for (int i = 0; i < s.length; i++) {
					list.add(i, s[i]);
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
		String stime=time+" 00:00:00";
		String etime=time+" 23:59:59";
		PublicDao p=new PublicDao();
		List<Area>areaall=p.findareaall();
		String areasql="";
		for (int x = 0; x < areaall.size(); x++) {
			if(x==0){
				areasql=" area_id ='"+areaall.get(x).getArea_id()+"'";
			}else {
				areasql+=" or area_id ='"+areaall.get(x).getArea_id()+"'";
			}
		}
		String riq=time.substring(0, 4)+time.substring(5, 7);
		String sql="select * from TB_TAXI_AREA_INFO_RECORD"+riq+" t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
			"  where t.vehi_no=v.vehi_no and record_time>=" +
			"to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss') and" +
			" record_time<=to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss') ";
			if (quyu.equals("所有区域")) {
				sql+=" and ("+areasql+")";
			}else {
				sql+=" and  area_name ='"+quyu+"'";
			}
			if (speed!=null&&speed.length()>0) {
				sql+=" and speed >"+speed;
			}
			String [] ids = data.split(",");
			String comps = "";
			if (ids.length==1&&!ids[0].equals("所有公司")) {
				comps +="and ( v.ba_name='"+ids[0]+"')";
			}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
				comps +="and ( v.ba_name='"+ids[0]+"')";
			}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
				comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
			}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
				comps +="and ( v.ba_name='"+ids[0]+"')";
			}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
				comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
			}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
				comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"' and v.vehi_no='"+ids[2]+"')";
			}
			sql+=comps;
			System.out.println(sql);
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				String b=null;
				String tt [] = {"00:00","00:30",
						"01:00","01:30",
						"02:00","02:30",
						"03:00","03:30",
						"04:00","04:30",
						"05:00","05:30",
						"06:00","06:30",
						"07:00","07:30",
						"08:00","08:30",
						"09:00","09:30",
						"10:00","10:30",
						"11:00","11:30",
						"12:00","12:30",
						"13:00","13:30",
						"14:00","14:30",
						"15:00","15:30",
						"16:00","16:30",
						"17:00","17:30",
						"18:00","18:30",
						"19:00","19:30",
						"20:00","20:30",
						"21:00","21:30",
						"22:00","22:30",
						"23:00","23:30",
						};
				int [] aa = new int[48];
				while(rs.next()){
					b=rs.getString("record_time").substring(11, 16);
					for(int i=0;i<tt.length;i++){
						if(b.equals(tt[i])){
							aa[i]++;
						}
					}
				}
				for(int i=0;i<aa.length;i++){
					list.add(i,aa[i]+"");
				}
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//查询车辆具体信息
	public List<Vhic>findspecinfo(String quyu,String time,String speed,String i,String data){
		System.out.println(time);
		String stime=null;
		String etime=null;
		if (i.equals("0")) {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date d =null;
			try {
				d = dft.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long a = d.getTime()-1000*60*60*24;
			String zuotian = dft.format(a);
			stime=zuotian+" 23:58:00";
			etime=time+" 00:02:00";
		}else if (i.equals("1")) {
			stime=time+" 00:28:00";
			etime=time+" 00:32:00";
		}else if (i.equals("2")) {
			stime=time+" 00:58:00";
			etime=time+" 01:02:00";
		}else if (i.equals("3")) {
			stime=time+" 01:28:00";
			etime=time+" 01:32:00";
		}else if (i.equals("4")) {
			stime=time+" 01:58:00";
			etime=time+" 02:02:00";
		}
		else if (i.equals("5")) {
			stime=time+" 02:28:00";
			etime=time+" 02:32:00";
		}else if (i.equals("6")) {
			stime=time+" 02:58:00";
			etime=time+" 03:02:00";
		}else if (i.equals("7")) {
			stime=time+" 03:28:00";
			etime=time+" 03:32:00";
		}else if (i.equals("8")) {
			stime=time+" 03:58:00";
			etime=time+" 04:02:00";
		}else if (i.equals("9")) {
			stime=time+" 04:28:00";
			etime=time+" 04:32:00";
		}else if (i.equals("10")) {
			stime=time+" 04:58:00";
			etime=time+" 05:02:00";
		}else if (i.equals("11")) {
			stime=time+" 05:28:00";
			etime=time+" 05:32:00";
		}else if (i.equals("12")) {
			stime=time+" 05:58:00";
			etime=time+" 06:02:00";
		}else if (i.equals("13")) {
			stime=time+" 06:28:00";
			etime=time+" 06:32:00";
		}else if (i.equals("14")) {
			stime=time+" 06:58:00";
			etime=time+" 07:02:00";
		}else if (i.equals("15")) {
			stime=time+" 07:28:00";
			etime=time+" 07:32:00";
		}else if (i.equals("16")) {
			stime=time+" 07:58:00";
			etime=time+" 08:02:00";
		}else if (i.equals("17")) {
			stime=time+" 08:28:00";
			etime=time+" 08:32:00";
		}else if (i.equals("18")) {
			stime=time+" 08:58:00";
			etime=time+" 09:02:00";
		}else if (i.equals("19")) {
			stime=time+" 09:28:00";
			etime=time+" 09:32:00";
		}else if (i.equals("20")) {
			stime=time+" 09:58:00";
			etime=time+" 10:02:00";
		}else if (i.equals("21")) {
			stime=time+" 10:28:00";
			etime=time+" 10:32:00";
		}else if (i.equals("22")) {
			stime=time+" 10:58:00";
			etime=time+" 11:02:00";
		}else if (i.equals("23")) {
			stime=time+" 11:28:00";
			etime=time+" 11:32:00";
		}else if (i.equals("24")) {
			stime=time+" 11:58:00";
			etime=time+" 12:02:00";
		}else if (i.equals("25")) {
			stime=time+" 12:28:00";
			etime=time+" 12:32:00";
		}else if (i.equals("26")) {
			stime=time+" 12:58:00";
			etime=time+" 13:02:00";
		}else if (i.equals("27")) {
			stime=time+" 13:28:00";
			etime=time+" 13:32:00";
		}else if (i.equals("28")) {
			stime=time+" 13:58:00";
			etime=time+" 14:02:00";
		}else if (i.equals("29")) {
			stime=time+" 14:28:00";
			etime=time+" 14:32:00";
		}else if (i.equals("30")) {
			stime=time+" 14:58:00";
			etime=time+" 15:02:00";
		}else if (i.equals("31")) {
			stime=time+" 15:28:00";
			etime=time+" 15:32:00";
		}else if (i.equals("32")) {
			stime=time+" 15:58:00";
			etime=time+" 16:02:00";
		}else if (i.equals("33")) {
			stime=time+" 16:28:00";
			etime=time+" 16:32:00";
		}else if (i.equals("34")) {
			stime=time+" 16:58:00";
			etime=time+" 17:02:00";
		}else if (i.equals("35")) {
			stime=time+" 17:28:00";
			etime=time+" 17:32:00";
		}else if (i.equals("36")) {
			stime=time+" 17:58:00";
			etime=time+" 18:02:00";
		}else if (i.equals("37")) {
			stime=time+" 18:28:00";
			etime=time+" 18:32:00";
		}else if (i.equals("38")) {
			stime=time+" 18:58:00";
			etime=time+" 19:02:00";
		}else if (i.equals("39")) {
			stime=time+" 19:28:00";
			etime=time+" 19:32:00";
		}else if (i.equals("40")) {
			stime=time+" 19:58:00";
			etime=time+" 20:02:00";
		}else if (i.equals("41")) {
			stime=time+" 20:28:00";
			etime=time+" 20:32:00";
		}else if (i.equals("42")) {
			stime=time+" 20:58:00";
			etime=time+" 21:02:00";
		}else if (i.equals("43")) {
			stime=time+" 21:28:00";
			etime=time+" 21:32:00";
		}else if (i.equals("44")) {
			stime=time+" 21:58:00";
			etime=time+" 22:02:00";
		}else if (i.equals("45")) {
			stime=time+" 22:28:00";
			etime=time+" 22:32:00";
		}else if (i.equals("46")) {
			stime=time+" 22:58:00";
			etime=time+" 23:02:00";
		}else if (i.equals("47")) {
			stime=time+" 23:28:00";
			etime=time+" 23:32:00";
		}
		PublicDao p=new PublicDao();
		List<Area>areaall=p.findareaall();
		String areasql="";
		for (int x = 0; x < areaall.size(); x++) {
			if(x==0){
				areasql=" area_id ='"+areaall.get(x).getArea_id()+"'";
			}else {
				areasql+=" or area_id ='"+areaall.get(x).getArea_id()+"'";
			}
		}
		String riq=time.substring(0, 4)+time.substring(5, 7);
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from TB_TAXI_AREA_INFO_RECORD"+riq+" t, HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				" where t.vehi_no=v.vehi_no " +
				" and record_time>=to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss')" +
				" and record_time<=to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss')";
		
		if (quyu.equals("所有区域")) {
			sql+=" and ("+areasql+")";
		}else {
			sql+=" and  area_name ='"+quyu+"'";
		}
		if (speed.length()>0) {
			sql+=" and t.speed>"+speed;
		}
		String [] ids = data.split(",");
		String comps = "";
		if (ids.length==1&&!ids[0].equals("所有公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"' and v.vehi_no='"+ids[2]+"')";
		}
		sql+=comps;
		sql+=" order by v.comp_name,v.vehi_no,area_name";
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				v.setBa_name(rs.getString("area_name"));
				list.add(v);
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
	//查询区域车辆具体信息
	public List<Vhic>findvhicinfo(String time,String id){
		String day=time.substring(0, 10)+" "+time.subSequence(10, 18);
		List<Vhic>list=new ArrayList<Vhic>();
		String sql=" select *  from TB_TAXI_STATUS_NEW t,TB_TAXI_AREA a, HZGPS_TAXI.VW_VEHICLE@TAXILINK v" +
				" where t.vehi_no=v.vehi_no and (t.LATI<=a.LATITUDE_MAX  and t.LATI>=a.LATITUDE_MIN)" +
				" and (t.LONGI <=a.LONGITUDE_MAX and t.LONGI>=LONGITUDE_MIN)" +
				" and a.AREA_ID='"+id+"' and t.db_time>=to_date('"+day+"','yyyy-mm-dd hh24:mi:ss')-1/24/60*10 ";
		sql+=" order by v.comp_name,v.vehi_no";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				list.add(v);
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
	//未上线车辆查询
	public List<Vhic>findnoline(String stime,String etime,String areaid,String compid,String data){
		String riq=stime.substring(0, 4)+stime.substring(5, 7);
		List<Vhic>list=new ArrayList<Vhic>();
		String [] ids = data.split(",");
		String comps = "";
		if (ids.length==1&&!ids[0].equals("所有公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( v.ba_name='"+ids[0]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
			comps +="and ( v.ba_name='"+ids[0]+"' and v.comp_name='"+ids[1]+"' and v.vehi_no='"+ids[2]+"')";
		}
		String sql="select * from(   select vehi_no,comp_name,own_name,own_tel,vehi_sim from" +
				" HZGPS_TAXI.VW_VEHICLE@TAXILINK v where vehi_no like '%浙AT%'"; 
		if (!ids[0].equals("所有公司")) {
			sql+=comps;
		}else {
			if (!areaid.equals("0")&&areaid.length()>1) {
				sql+="and ba_id ='"+areaid+"'";
				if (!compid.equals("0")) {
					sql+="and comp_id='"+compid+"'";
				}
			}
		}
				sql+=" minus" +
				" select a.vehi_no,v.comp_name,own_name,own_tel,vehi_sim from(" +
				" select distinct vehi_no from TB_TAXI_AREA_INFO_RECORD"+riq+" v where" +
				" source_db_time>to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss')" +
				" and source_db_time<to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss') " ;
				
						sql+=") a," +
				"HZGPS_TAXI.VW_VEHICLE@TAXILINK v where a.vehi_no=v.vehi_no ";
						if (!ids[0].equals("所有公司")) {
							sql+=comps;
						}else {
							if (!areaid.equals("0")&&areaid.length()>1) {
								sql+="and ba_id ='"+areaid+"'";
								if (!compid.equals("0")) {
									sql+="and comp_id='"+compid+"'";
								}
							}
						}
						sql+=") x," +
				" (select max(source_db_time) source_db_time,vehi_no from TB_TAXI_AREA_INFO_RECORD"+riq+"" +
				" where record_time>to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss')" +
				" and record_time<to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss') " ;
						sql+=" group by vehi_no) y where x.vehi_no=y.vehi_no";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_id(rs.getString("comp_name")==null?"&nbsp;":rs.getString("comp_name"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				v.setTime(rs.getString("source_db_time").substring(0, 19)==null?"&nbsp;":rs.getString("source_db_time").substring(0, 19));
				list.add(v);
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
	//时间段车辆数量分析
//	public List<Vhic>findtimevhic(String stime,String etime,String quyu,String compname){
//		List<Vhic>list=new ArrayList<Vhic>();
//		String sql="select distinct(t.vehi_no),vehi_sim,own_name,own_tel,area_name,v.ba_name from TB_TAXI_AREA_INFO_RECORD t" +
//				",HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.vehi_no=v.vehi_no and " +
//				" t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss')" +
//				" and t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') ";
//		System.out.println(sql);
//		if (compname!=null&&compname.length()>0&&!compname.equals("0")) {
//			sql+=" and v.ba_name ='"+compname+"'";
//		}
//		if (!quyu.equals("0")) {
//			sql+=" and area_name='"+quyu+"'";
//		}else {
//			sql+="and area_name!='非监控区域'";
//		}
//		sql+=" order by ba_name";
//		Connection con=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		try {
//			con=DataBese.getConnectionOfOracle();
//			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
//			int a=0;
//			while(rs.next()){
//				Vhic v=new Vhic();
//				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
//				a=findtimevhicno(stime, etime, quyu, rs.getString("vehi_no"));
//				v.setTime(a+"");
//				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
//				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
//				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
//				v.setBa_name(rs.getString("area_name"));
//				v.setComp_name(rs.getString("ba_name"));
//				list.add(v);
//			}
//			rs.close();
//			ps.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d1=null;
//		Date d2=null;
//		try {
//			d1 = sd.parse(stime);
//			d2 = sd.parse(etime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//        //毫秒ms
//        long diff = d2.getTime() - d1.getTime();
//
//        long diffMinutes = diff / (60 * 1000) % 60;
//        long diffHours = diff / (60 * 60 * 1000) % 24;
//        long diffDays = diff / (24 * 60 * 60 * 1000);
//        if (diffDays!=0) {
//			diffMinutes=diffDays*24*60+diffMinutes;
//			if (diffHours!=0) {
//	 			diffMinutes=diffHours*60+diffMinutes;
//	 		}
//		}
//        if (diffHours!=0) {
//			diffMinutes=diffHours*60+diffMinutes;
//		}
//        long a=diffMinutes/10;
//        if (a<10) {
//			a=1;
//		}
//		List<Vhic>l=new ArrayList<Vhic>();
//		for (int i = 0; i < list.size(); i++) {
//			Vhic v1=new Vhic();
//			if (Integer.parseInt(list.get(i).getTime())>=a) {
//				v1.setVehi_no(list.get(i).getVehi_no());
//				v1.setTime(list.get(i).getTime());
//				v1.setVehi_sim(list.get(i).getVehi_sim());
//				v1.setOwn_name(list.get(i).getOwn_name());
//				v1.setOwn_tel(list.get(i).getOwn_tel());
//				v1.setBa_name(list.get(i).getBa_name());
//				v1.setComp_name(list.get(i).getComp_name());
//				l.add(v1);
//			}
//		}
//		return l;
//	}
//	//时段车辆数量查询
//	public int findtimevhicno(String stime,String etime,String quyu,String vehi){
//		int count=0;
//		String sql="select count(*) c from TB_TAXI_AREA_INFO_RECORD t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v " +
//				" where t.vehi_no=v.vehi_no and  t.vehi_no='"+vehi+"' and" +
//				" t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and" +
//				" t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') ";
//		if (quyu!=null&&quyu.length()>0&&!quyu.equals("0")) {
//			sql+=" and area_name='"+quyu+"'";
//		}
//		System.out.println(sql);
//		Connection con=null;
//		PreparedStatement ps=null;
//		ResultSet rs=null;
//		try {
//			con=DataBese.getConnectionOfOracle();
//			ps=con.prepareStatement(sql);
//			rs=ps.executeQuery();
//			while(rs.next()){
//				count=rs.getInt("c");
//			}
//			rs.close();
//			ps.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return count;
//	}
	
	//时间段车辆数量分析
	public List<Vhic>findtimevhic(String stime,String etime,String quyu,String compname){
		List<Vhic>list=new ArrayList<Vhic>();
		String riq=stime.substring(0, 4)+stime.substring(5, 7);
		PublicDao p=new PublicDao();
		List<Area>areaall=p.findareaall();
		String areasql="";
		for (int x = 0; x < areaall.size(); x++) {
			if(x==0){
				areasql=" area_id ='"+areaall.get(x).getArea_id()+"'";
			}else {
				areasql+=" or area_id ='"+areaall.get(x).getArea_id()+"'";
			}
		}
		String sql="select * from(select v.vehi_no,v.vehi_sim,v.own_name,v.own_tel,ve.area_name,v.ba_name" +
				"  from (select distinct(t.vehi_no),area_name from TB_TAXI_AREA_INFO_RECORD"+riq+" t," +
				"HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.vehi_no=v.vehi_no and " +
				" t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and" +
				"  t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')";
				if (compname!=null&&compname.length()>0&&!compname.equals("0")) {
					sql+=" and v.ba_name ='"+compname+"'";
				}
				if (!quyu.equals("0")) {
					sql+=" and area_name='"+quyu+"'";
				}else {
					sql+=" and ("+areasql+")";
				}
				sql+=") ve, HZGPS_TAXI.VW_VEHICLE@TAXILINK v where ve.vehi_no=v.vehi_no ) a, " +
				" (select count(t.vehi_no) v,t.vehi_no from TB_TAXI_AREA_INFO_RECORD"+riq+" t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v " +
				" where t.vehi_no=v.vehi_no and t.source_db_time>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') " +
				"and t.source_db_time<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')  " ;
				if (compname!=null&&compname.length()>0&&!compname.equals("0")) {
					sql+=" and v.ba_name ='"+compname+"'";
				}
				if (!quyu.equals("0")) {
					sql+=" and area_name='"+quyu+"'";
				}else {
					sql+=" and ("+areasql+")";
				}
				sql+=" group by t.vehi_no) b where a.vehi_no=b.vehi_no ";
				sql+=" order by ba_name";
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int a=0;
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1=null;
			Date d2=null;
			try {
				d1 = sd.parse(stime);
				d2 = sd.parse(etime);
			} catch (ParseException e) {
				e.printStackTrace();
			}

	        //毫秒ms
	        long diff = d2.getTime() - d1.getTime();
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000) % 24;
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	        if (diffDays!=0) {
				diffMinutes=diffDays*24*60+diffMinutes;
				if (diffHours!=0) {
		 			diffMinutes=diffHours*60+diffMinutes;
		 		}
			}
	        if (diffHours!=0) {
				diffMinutes=diffHours*60+diffMinutes;
			}
	        long a1=diffMinutes/10;
	        if (a1<10) {
				a1=1;
			}
	        a1=(int)((double)a1*0.8);
			while(rs.next()){
				Vhic v=new Vhic();
				if(rs.getLong("v")>=a1){
				v.setVehi_no(rs.getString("vehi_no")==null?"&nbsp;":rs.getString("vehi_no"));
				v.setVehi_sim(rs.getString("vehi_sim")==null?"&nbsp;":rs.getString("vehi_sim"));
				v.setOwn_name(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwn_tel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setBa_name(rs.getString("area_name"));
				v.setComp_name(rs.getString("ba_name"));
				list.add(v);
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//重点监控区域时段停留车辆查询查询区域经纬度
	public List<Area>areafind(String area){
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_AREA t where 1=1 ";
		if (!area.equals("0")) {
			sql+=" and area_name='"+area+"'";
		}
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setArea_name(rs.getString("area_name"));
				a.setAreazbs(rs.getString("AREA_COORDINATES"));
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
	//重点监控区域时段停留车辆查询 
	//查询未上线车辆
	public List<Vehicle>findwsxvhic(){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select * from TB_TAXI_STATUS_NEW t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.mdt_no=v.sim_num and (sysdate-stime)*24*60>10 and v.vehi_no like '%浙AT%'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vehicle v=new Vehicle();
				v.setLongi(rs.getDouble("px"));
				v.setLati(rs.getDouble("py"));
				v.setCompname(rs.getString("ba_name"));
				v.setSimka(rs.getString("vehi_sim"));
				v.setVehino(rs.getString("vehi_no"));
				v.setOwnname(rs.getString("own_name"));
				v.setOwntel(rs.getString("own_tel"));
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
	//公司出租汽车营运率分析
	public List<Vhic>findtaxifenxi(String time){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select *  from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a, TB_TAXI_CHUCHE_INFO_RECORD t" +
				" where a.ba_id=t.ba_id and db_time=to_Date('"+time+"','yyyy-MM-dd')";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<Vhic>list1=findareacls(time);
			int a=0;
			while(rs.next()){
				Vhic v=new Vhic();
				if (!rs.getString("ba_name").equals("测试")) {
					for (int i = 0; i < list1.size(); i++) {
						if (rs.getString("ba_id").equals(list1.get(i).getBa_id())) {
							a++;
						}
					}
					v.setA(a);
					v.setBa_name(rs.getString("ba_name"));
					v.setAverage_run_times(rs.getString("average_run_times"));
					v.setBusy_taxi_chuche_rate(rs.getString("busy_taxi_chuche_rate"));
					v.setTaxi_chuche_rate(rs.getString("taxi_chuche_rate"));
					v.setRun_times(rs.getString("run_times"));
					a=0;
					list.add(v);
				}
			}
			VhicComparator comp=new VhicComparator();
			//调用排序方法
			Collections.sort(list,comp);
			 
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询公司车辆数
	public List<Vhic> findareacls(String time){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select *  from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where a.ba_id=v.BA_id";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setBa_name(rs.getString("Ba_name"));
				v.setBa_id(rs.getString("ba_id"));
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
	//查询车辆
	public List<Vhic> findcl3(String vehi_no){
		List<Vhic> list = new ArrayList<Vhic>();
		String sql = "select vehi_no from HZGPS_TAXI.VW_VEHICLE@TAXILINK where vehi_no like '%"+vehi_no+"%' order by vehi_no";
		System.out.println(sql);
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Vhic v = new Vhic();
				v.setVehi_id(rs.getString("vehi_no"));
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
	//多屏监控
	public List<Vehicle> finddpjk(String quyu){
		List<Vehicle>list=new ArrayList<Vehicle>();
		String sql="select *  from TB_TAXI_STATUS_NEW@db113 t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.mdt_no=v.sim_num"
				+ " and vehi_no = '"+quyu+"'";
			System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				/**
				 * 当前时间-stime<30分钟上线车辆
				 * speed>0为运动状态车辆
				 * speed=0为不运动状态
				 * 在下面设置为status=0为上线=1为下线
				 * 再上线的基础上gpsstatus=1为运动状态
				 * =0为静止状态
				 * */
				Vehicle v=new Vehicle();
				v.setVehino(rs.getString("vehi_no")==null?"&nbsp":rs.getString("vehi_no"));
				v.setCompname(rs.getString("comp_name")==null?"&nbsp":rs.getString("comp_name"));
				v.setColor(rs.getString("vc_name")==null?"&nbsp":rs.getString("vc_name"));
				v.setPx(rs.getString("px")==null?"0":rs.getString("px"));
				v.setPy(rs.getString("py")==null?"0":rs.getString("py"));
				v.setOwnname(rs.getString("own_name")==null?"&nbsp;":rs.getString("own_name"));
				v.setOwntel(rs.getString("own_tel")==null?"&nbsp;":rs.getString("own_tel"));
				v.setSimka(rs.getString("vehi_sim")==null?"&nbsp":rs.getString("vehi_sim"));
				v.setSpeed(rs.getString("speed")==null?"&nbsp":rs.getString("vehi_no"));
				v.setHeading(rs.getString("heading")==null?"&nbsp":rs.getString("heading"));
				if (rs.getString("stime")!=null) {
					v.setDateTime(rs.getString("stime").substring(0, 19));
					long time=rs.getTimestamp("stime").getTime();
					Date date=new Date();
					long time1=date.getTime();
					if (time1-time<=1800000) {
						v.setOnoffstate("0");
					}
				}else {
					v.setOnoffstate("1");
				}
				v.setState(rs.getString("status")==null?"&nbsp":rs.getString("status"));
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
	//计算车辆是否上线
		public static boolean jisuan(String date1){ 
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        double result=0;
			try {
				Date start = sdf.parse(date1);
				Date end = new Date();
				long cha = end.getTime() - start.getTime(); 
				result = cha * 1.0 / (1000 * 60); 
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(result<=30){ 
				return true; 
			}else{ 
				return false; 
			} 
	    } 
}
