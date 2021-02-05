package com.tw.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.ze.util.DataBese;

public class testdao {
	public static void main(String[] args) throws ParseException {
		String stime="2013/03/01 00:00:00";
		String etime="2013/03/02 00:00:00";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long a=sdf.parse(stime).getTime();
		long e=sdf.parse(etime).getTime();
//		System.out.println(sdf.format(sdf.parse(stime)));
		for(int i=0;i<92;i++){
			Date d=new Date(a+i*24*60*60*1000l);
			Date d1=new Date(e+i*24*60*60*1000l);
			String b=sdf.format(d);
			String c=sdf.format(d1);
			double aaa = find1(b,c);
			System.out.println(b+"  "+c+"  "+aaa);
		}
	}
	public static double find(String stime,String etime,String stime1,String etime1){
		int count=0;
		String sql="select sum((TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))/10)+(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))/10)) c from HZGPS_CITIZEN.TB_CITIZEN_2015@TAXILINK45 t" +
				" where ((shangche>to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') " +
				"and shangche<to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss'))" +
				" or (shangche>to_date('"+stime1+"','yyyy-mm-dd hh24:mi:ss')" +
				" and shangche<to_date('"+etime1+"','yyyy-mm-dd hh24:mi:ss')))";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static double find1(String stime,String etime){
		int count=0;
		String sql = "select count(distinct(vhic)) c from TB_CITIZEN_2013@TAXILINK89"
				+ " where shangche>=to_date('"+stime+"','yyyy/mm/dd hh24:mi:ss')"
				+ " and shangche<to_date('"+etime+"','yyyy/mm/dd hh24:mi:ss')";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
