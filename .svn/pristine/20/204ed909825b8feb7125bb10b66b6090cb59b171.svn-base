package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tw.entity.HalfMonth;
import com.ze.util.DataBese;

public class HalfMonthDao {
	//重点监控区域车辆上月最高值和最低值
	public HalfMonth findmaxmin(String id,String time){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian=null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian=dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HalfMonth h=new HalfMonth();
		String sql="select * from TB_AREA_HALF_MONTH t where" +
				" enddate=to_date('"+zuotian+"','yyyy-MM-dd') and area_id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				h.setAreavhicmax(rs.getString("taxicounts_max").split(";"));
				h.setAreavhicmin(rs.getString("taxicounts_min").split(";"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
	//重点监控区域上线率重车率前半月最高值和最低值
	public HalfMonth findonlineloadmaxmin(String time,String baid){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian=null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian=dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HalfMonth h=new HalfMonth();
		String sql="select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where" +
		" enddate=to_date('"+zuotian+"','yyyy-MM-dd') and ba_id='"+baid+"'";
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				h.setArealoadmax(rs.getString("load_rate_max").split(";"));
				h.setArealoadmin(rs.getString("load_rate_min").split(";"));
				h.setAreaonlinemax(rs.getString("online_rate_max").split(";"));
				h.setAreaonlinemin(rs.getString("online_rate_min").split(";"));
				h.setAllonlinemax(rs.getString("ALL_ONLINE_RATE_MAX").split(";"));
				h.setAllonlinemin(rs.getString("ALL_ONLINE_RATE_MIN").split(";"));
				h.setAllloadmax(rs.getString("ALL_LOAD_RATE_MAX").split(";"));
				h.setAllloadmin(rs.getString("ALL_LOAD_RATE_MIN").split(";"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
	//在线营运率和实载率前半月最高值和最低值
	public HalfMonth findyingyunmaxmin(String time){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian=null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian=dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HalfMonth h=new HalfMonth();
		String sql="select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where" +
		" enddate=to_date('"+zuotian+"','yyyy-MM-dd') ";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				h.setYingyunmax(rs.getString("online_run_rate_max").split(";"));//营运率
				h.setYingyunmin(rs.getString("online_run_rate_min").split(";"));
				h.setActualmax(rs.getString("actual_load_rate_max").split(";"));//实载率
				h.setActualmin(rs.getString("actual_load_rate_min").split(";"));
				h.setProfitmax(rs.getString("RUN_PROFIT_MAX").split(";"));//营运收益
				h.setProfitmin(rs.getString("RUN_PROFIT_MIN").split(";"));
				h.setActualloadmax(rs.getString("ACTUAL_LOAD_MILEAGE_MAX").split(";"));//实载里程
				h.setActualloadmin(rs.getString("ACTUAL_LOAD_MILEAGE_MIN").split(";"));
				h.setNoloadmax(rs.getString("NO_LOAD_MILEAGE_MAX").split(";"));//空驶里程
				h.setNoloadmin(rs.getString("NO_LOAD_MILEAGE_MIN").split(";"));
				h.setTotalloadmax(rs.getString("TOTAL_MILEAGE_MAX").split(";"));//总里程
				h.setTotalloadmin(rs.getString("TOTAL_MILEAGE_MIN").split(";"));
				h.setTimesmax(rs.getString("RUN_TIMES_MAX").split(";"));//营运次数
				h.setTimesmin(rs.getString("RUN_TIMES_MIN").split(";"));
				h.setTimemax(rs.getString("RUN_TIME_MAX").split(";"));//载客时间
				h.setTimemin(rs.getString("RUN_TIME_MIN").split(";"));
				h.setWaittingtimemax(rs.getString("WAITTING_TIME_MAX").split(";"));//载客空驶时间
				h.setWaittingtimemin(rs.getString("WAITTING_TIME_MIN").split(";"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
}
