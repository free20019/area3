package com.tw.dao.home;

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

import com.tw.entity.Area;
import com.tw.entity.Form;
import com.tw.entity.LOADONLINE;
import com.tw.entity.RegularOffline;
import com.ze.util.DataBese;

public class HomeChartDao {
	// 最近一个小时参加营运车辆
	public int fingoneyingyun() {
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stime = df.format(calendar.getTime());
		Date d = new Date();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		etime = df1.format(d);
		int count = 0;
		String sj=stime.substring(0, 4);
		String sql = "select count(distinct vhic) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and shangche<to_date('"
				+ etime + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 上周一小时营运车辆
	public int findszyingyun() {
		int count = 0;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String shangzhou = null;
		try {
			Date beginDate = dft.parse(dft.format(d));
			Calendar date3 = Calendar.getInstance();
			date3.setTime(beginDate);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		stime = shangzhou + " " + df.format(calendar.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		etime = shangzhou + " " + df1.format(d);
		String sj=stime.substring(0,4);
		String sql = "select count(distinct vhic) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and shangche<to_date('"
				+ etime + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 上周平均参加营运车辆
	public String findszpjyingyun() {
		String count = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH"); // 设置时间格式
		List<String> szlist = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		String dq = sdf1.format(d);
		try {
			calendar.setTime(sdf.parse(sdf.format(d)));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime);
		String et = sdf.format(eTime);
		try {
			Date dateOne = sdf.parse(st);
			Date dateTwo = sdf.parse(et);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dateOne);

			while (calendar1.getTime().before(dateTwo)) {
				szlist.add(sdf.format(calendar1.getTime()) + " " + dq
						+ ":00:00");
				calendar1.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_TAXI_RUN_INFO_RECORD_TEST t where 1=1 and ( ";
		for (int i = 0; i < szlist.size(); i++) {
			if (i == szlist.size() - 1) {
				sql += "db_time=to_date('" + szlist.get(i)
						+ "','yyyy-MM-dd HH24:mi:ss') ";
			} else {
				sql += "db_time=to_date('" + szlist.get(i)
						+ "','yyyy-MM-dd HH24:mi:ss') or ";
			}
		}
		sql += ")";
		double shangzhoupj = 0, i = 0, j = 0, szzhouzhuan = 0, szjine = 0;
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j++;
				if (rs.getInt("run_taxis") != 0) {
					i++;
					shangzhoupj += Double
							.parseDouble(rs.getString("run_taxis"));
				}
				szzhouzhuan += Double.parseDouble(rs.getString("run_times"));
				szjine += Double.parseDouble(rs.getString("run_profit"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		count = (shangzhoupj / i) + "," + (szzhouzhuan / j) + ","
				+ (szjine / j);
		return count;
	}

	// 单车周转次数
	public int findonezhouzhuan() {
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stime = df.format(calendar.getTime());
		Date d = new Date();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		etime = df1.format(d);
		int count = 0;
		String sj=stime.substring(0, 4);
		String sql = "select count(*) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and shangche<to_date('"
				+ etime + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 上周一小时周转次数
	public int findszzhouzhuan() {
		int count = 0;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String shangzhou = null;
		try {
			Date beginDate = dft.parse(dft.format(d));
			Calendar date3 = Calendar.getInstance();
			date3.setTime(beginDate);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		stime = shangzhou + " " + df.format(calendar.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		etime = shangzhou + " " + df1.format(d);
		String sj=stime.substring(0,4);
		String sql = "select count(*) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and shangche<to_date('"
				+ etime + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 单车营收金额
	public int findonejine() {
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stime = df.format(calendar.getTime());
		Date d = new Date();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		etime = df1.format(d);
		int count = 0;
		String sj=stime.substring(0, 4);
		String sql = "select sum(jine)/100 c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and shangche<to_date('"
				+ etime + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 上周一小时营收金额
	public int findszjine() {
		int count = 0;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String shangzhou = null;
		try {
			Date beginDate = dft.parse(dft.format(d));
			Calendar date3 = Calendar.getInstance();
			date3.setTime(beginDate);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		stime = shangzhou + " " + df.format(calendar.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		etime = shangzhou + " " + df1.format(d);
		String sj=stime.substring(0,4);
		String sql = "select sum(jine)/100 c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and shangche<to_date('"
				+ etime + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 上线率查询
	public LOADONLINE findonlindone() {
		LOADONLINE l = new LOADONLINE();
		String sql = "select * from (select * from TB_TAXI_LOAD_ONLINE_RATE t"
				+ " order by db_time desc) where rownum=1";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				l.setOnline_rate(rs.getString("online_rate"));
				l.setArea_online_rate(findszonlineone());
				l.setLoad_rate(findpjonlineone() + "%");
				System.out.println(rs.getString("online_rate")+"  "+findszonlineone()+"   "+findpjonlineone() + "%");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	// 上周同一时间的上线率查询
	public String findszonlineone() {
		String count = "";
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String shangzhou = null;
		try {
			Date beginDate = dft.parse(dft.format(d));
			Calendar date3 = Calendar.getInstance();
			date3.setTime(beginDate);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String stime = null, etime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		stime = shangzhou + " " + df.format(calendar.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		etime = shangzhou + " " + df1.format(d);
		String sql = "select * from (select * from TB_TAXI_LOAD_ONLINE_RATE t where"
				+ " db_time>to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ "  db_time<to_date('"
				+ etime
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " order by db_time desc) where rownum=1";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getString("online_rate");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 上周平均上线率查询
	public int findpjonlineone() {
		int count = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH"); // 设置时间格式
		List<String> szlist = new ArrayList<String>();
		List<String> szlist1 = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		calendar.add(Calendar.MINUTE, -10);// 当前系统时间的 前一小时
		String dq = sdf.format(calendar.getTime());
		calendar.add(Calendar.MINUTE, 10);// 当前系统时间的 前一小时
		String dq1 = sdf.format(calendar.getTime());
		try {
			calendar.setTime(sdf.parse(sdf.format(d)));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime);
		String et = sdf.format(eTime);
		try {
			Date dateOne = sdf.parse(st);
			Date dateTwo = sdf.parse(et);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dateOne);

			while (calendar1.getTime().before(dateTwo)) {
				szlist.add(sdf.format(calendar1.getTime()).substring(0, 11)
						+ dq.subSequence(11, 19));
				szlist1.add(sdf.format(calendar1.getTime()).substring(0, 11)
						+ dq1.subSequence(11, 19));
				calendar1.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_TAXI_LOAD_ONLINE_RATE t where 1=1 and ( ";
		for (int i = 0; i < szlist.size(); i++) {
			if (i == szlist.size() - 1) {
				sql += "db_time>=to_date('" + szlist.get(i)
						+ "','yyyy-MM-dd HH24:mi:ss') "
						+ "and db_time<=to_date('" + szlist1.get(i)
						+ "','yyyy-MM-dd HH24:mi:ss') ";
			} else {
				sql += "(db_time>=to_date('" + szlist.get(i)
						+ "','yyyy-MM-dd HH24:mi:ss') "
						+ "and db_time<=to_date('" + szlist1.get(i)
						+ "','yyyy-MM-dd HH24:mi:ss') ) or ";
			}
		}
		sql += ")";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0, a = 0;
			while (rs.next()) {
				if (rs.getString("online_rate").length() > 2) {
					i++;
					String rate = rs.getString("online_rate").substring(0, 2);
					a += Integer.parseInt(rate);
				}
			}
			if (i==0) {
				i=1;
			}
			count = a / i;
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 查询故障车辆
	public RegularOffline findRegular() {
		RegularOffline r = new RegularOffline();
		String sql = "select * from (select * from TB_TAXI_REGULAR_OFFLINE t "
				+ " order by operating_time desc) where rownum=1";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r.setCpNum(rs.getInt("cp_num"));// 企业报停数量
				r.setEfNum(rs.getInt("ef_num"));// 设备故障数量
				r.setOtherNum(rs.getInt("other_num"));// 其他故障
				r.setSimNum(rs.getInt("sim_num"));// sim卡故障
				r.setTotal(rs.getInt("total"));// 合计故障
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// 重点监控区域时段车辆停留数量
	public int findzdclsl(int i) {
		int count = 0;
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -48);
		String time48 = fd.format(calendar.getTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.HOUR, -24);
		String time24 = fd.format(calendar1.getTime());
		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.HOUR, -12);
		String time12 = fd.format(calendar2.getTime());
		Calendar calendar3 = Calendar.getInstance();
		calendar3.add(Calendar.HOUR, -6);
		String time6 = fd.format(calendar3.getTime());
		Calendar calendar4 = Calendar.getInstance();
		calendar4.add(Calendar.HOUR, -3);
		String time3 = fd.format(calendar4.getTime());
		Calendar calendar5 = Calendar.getInstance();
		calendar5.add(Calendar.HOUR, -2);
		String time2 = fd.format(calendar5.getTime());
		Calendar calendar6 = Calendar.getInstance();
		calendar6.add(Calendar.HOUR, -1);
		String time1 = fd.format(calendar6.getTime());
		String time = "";
		if (i == 0) {
			time = time48;
		} else if (i == 1) {
			time = time24;
		} else if (i == 2) {
			time = time12;
		} else if (i == 3) {
			time = time6;
		} else if (i == 4) {
			time = time3;
		} else if (i == 5) {
			time = time2;
		} else if (i == 6) {
			time = time1;
		}
		String sql = "select count(distinct vehi_no) c from TB_TAXI_AREA_INFO_RECORD t"
				+ " where record_time>=to_date('"
				+ time
				+ "','yyyy-MM-dd HH24:mi:ss')" + " and area_name!='非监控区域'";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 最近12小时在线营运率
	public int[] findyingyun12(int i) {
		int[] shuju = new int[12];
		for (int j = 0; j < shuju.length; j++) {
			shuju[j] = 0;
		}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian = null, zuotian = null, qiantian = null, shangzhou = null;
		String time = null;
		try {
			Date d = new Date();
			time = dft.format(d);
			jintian = time;
			Calendar date = Calendar.getInstance();
			date.setTime(d);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			Calendar date7 = Calendar.getInstance();
			date7.setTime(d);
			date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
			Date date8 = dft.parse(dft.format(date7.getTime()));
			qiantian = dft.format(date8);
			Calendar date3 = Calendar.getInstance();
			date3.setTime(d);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd HH");
		String hnow = df.format(new Date()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h1 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h2 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h3 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h4 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h5 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h6 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h7 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h8 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h9 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h10 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h11 = df.format(calendar.getTime()) + ":00";
		String d = null,d1=null;
		if (i == 0) {
			d = shangzhou;
		} else if (i == 1) {
			d = qiantian;
		} else if (i == 2) {
			d = zuotian;
		} else if (i == 3) {
			d = jintian;
		}
		Date beginDate = null;
		if (Integer.parseInt(jintian.substring(8, 10))>Integer.parseInt(h11.substring(0, 2))) {
			try {
				beginDate = dft.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			d1 = dft.format(date.getTime());
		}else {
			d1=d;
		}
		String sql = "select * from TB_TAXI_RUN_RATE t"
				+ " where db_time>=to_date('" + d1 + " " + h11.substring(3, 8)
				+ "','yyyy-MM-dd HH24:mi:ss')" + " and db_time<to_date('" + d
				+ " " + hnow.substring(3, 8) + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String dbtime = null;
			while (rs.next()) {
				LOADONLINE l = new LOADONLINE();
				dbtime = rs.getString("db_time").substring(11, 16);
				if(rs.getString("run_rate").length()>2){
				if (dbtime.equals(h11.substring(3, 8))) {
					shuju[0] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h10.substring(3, 8))) {
					shuju[1] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h9.substring(3, 8))) {
					shuju[2] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h8.substring(3, 8))) {
					shuju[3] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h7.substring(3, 8))) {
					shuju[4] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h6.substring(3, 8))) {
					shuju[5] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h5.substring(3, 8))) {
					shuju[6] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h4.substring(3, 8))) {
					shuju[7] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h3.substring(3, 8))) {
					shuju[8] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h2.substring(3, 8))) {
					shuju[9] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(h1.substring(3, 8))) {
					shuju[10] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				} else if (dbtime.equals(hnow.substring(3, 8))) {
					shuju[11] = Integer.parseInt(rs.getString("run_rate")
							.substring(0, 2));
				}
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String stime = null;
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY,
				calendar1.get(Calendar.HOUR_OF_DAY) - 6);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		int w = Integer.parseInt(stime.substring(11, 13));
		int[] a = new int[6];
		if (i == 3 && time.equals(stime.substring(0, 10))) {
			for (int y = 0; y < 6; y++) {
				double b = findmeiyou(y);
				a[y] = (int) b;
			}
			for (int j = 0; j < a.length; j++) {
				shuju[6 + j] = a[j];
			}
			for (int j = 0; j < shuju.length; j++) {
				if (j>0) {
					if (shuju[j] == 0) {
						shuju[j] = shuju[j - 1];
					}
				}
			}
		}
		for (int j = 0; j < shuju.length; j++) {
			if (j>0) {
				
				if (shuju[j] == 0) {
					shuju[j] = shuju[j - 1];
				}
			}
		}
		return shuju;
	}

	// 营运率最近6小时
	public int findmeiyou(int i) {
		String stime = "", etime = "";
		if (i == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 6);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 5);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 1) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 5);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 4);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 2) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 4);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 3);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 3) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 3);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 2);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 4) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 1);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 5) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 1);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Date d = new Date();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(d).substring(0, 14) + "00:00";
		}
		String sj=stime.substring(0, 4);
		String sql = "select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ stime
				+ "','yyyy-MM-dd hh24:mi:ss')"
				+ " and  shangche<to_date('"
				+ etime + "','yyyy-MM-dd hh24:mi:ss')";
		double totalpage = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalpage = rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double a = totalpage / 9612 * 100;
		return (int) a;
	}

	// 最近12小时平均值
	public List<LOADONLINE> findyypj12() {
		List<LOADONLINE> list = new ArrayList<LOADONLINE>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		String time = sdf.format(d);
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		String sql = "select * from TB_TAXI_RUN_RATE t where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss')"
				+ " and db_time<=to_date('"
				+ et + "','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LOADONLINE l = new LOADONLINE();
				l.setDb_time(rs.getString("db_time"));
				l.setRun_rate(rs.getString("run_rate"));
				list.add(l);
			}
			rs.close();
			ps.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOADONLINE zhuanhuan = zhuanhuanyy(list);
		List<LOADONLINE> list1 = new ArrayList<LOADONLINE>();
		list1.add(zhuanhuan);
		return list1;
	}

	public LOADONLINE zhuanhuanyy(List<LOADONLINE> list) {
		LOADONLINE l = new LOADONLINE();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH");
		String hnow = df.format(new Date()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h1 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h2 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h3 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h4 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h5 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h6 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h7 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h8 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h9 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h10 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h11 = df.format(calendar.getTime()) + ":00";
		List<String> yingyun1 = new ArrayList<String>();
		List<String> yingyun2 = new ArrayList<String>();
		List<String> yingyun3 = new ArrayList<String>();
		List<String> yingyun4 = new ArrayList<String>();
		List<String> yingyun5 = new ArrayList<String>();
		List<String> yingyun6 = new ArrayList<String>();
		List<String> yingyun7 = new ArrayList<String>();
		List<String> yingyun8 = new ArrayList<String>();
		List<String> yingyun9 = new ArrayList<String>();
		List<String> yingyun10 = new ArrayList<String>();
		List<String> yingyun11 = new ArrayList<String>();
		List<String> yingyun12 = new ArrayList<String>();
		String [] shijian=new String [12];
		shijian[11]=hnow;
		shijian[10]=h1;
		shijian[9]=h2;
		shijian[8]=h3;
		shijian[7]=h4;
		shijian[6]=h5;
		shijian[5]=h6;
		shijian[4]=h7;
		shijian[3]=h8;
		shijian[2]=h9;
		shijian[1]=h10;
		shijian[0]=h11;
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).getDb_time().length() == 10|| list.get(i).getDb_time().substring(11, 16).equals(h11)) {
				if (list.get(i).getRun_rate() != null&& list.get(i).getRun_rate().length() > 0&& list.get(i).getRun_rate() != "0") {
					yingyun1.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h10)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun2.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h9)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun3.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h8)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun4.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h7)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun5.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h6)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun6.add(list.get(i).getRun_rate());
				}
			}else if (list.get(i).getDb_time().substring(11, 16).equals(h5)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun7.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(h4)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun8.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h3)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun9.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h2)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun10.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h1)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun11.add(list.get(i).getRun_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					hnow)) {
				if (list.get(i).getRun_rate() != null
						&& list.get(i).getRun_rate().length() > 0
						&& list.get(i).getRun_rate() != "0") {
					yingyun12.add(list.get(i).getRun_rate());
				}
			} 
		}
		List<String> pingjunyingyun = new ArrayList<String>();
		double b = 0.0;
		b = 0;
		if (yingyun1.size() > 0) {
			for (int j = 0; j < yingyun1.size(); j++) {
				if (yingyun1.get(j).length() == 3) {
					b += Double.parseDouble(yingyun1.get(j).substring(0, 2)) / 100;
				}
			}
			pingjunyingyun.add(0, (int) (b / yingyun1.size() * 100) + "%");
		} else {
			pingjunyingyun.add(0, 0 + "");
		}
		b = 0.0;
		if (yingyun2.size() > 0) {

			for (int j = 0; j < yingyun2.size(); j++) {
				if (yingyun2.get(j).length() == 3) {
					b += Double.parseDouble(yingyun2.get(j).substring(0, 2)) / 100;
				}
			}
			pingjunyingyun.add(1, (int) (b / yingyun2.size() * 100) + "%");
		} else {
			pingjunyingyun.add(1, 0 + "");
		}
		b = 0.0;
		if (yingyun3.size() > 0) {

			for (int j = 0; j < yingyun3.size(); j++) {
				if (yingyun3.get(j).length() == 3) {
					b += Double.parseDouble(yingyun3.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(2, (int) (b / yingyun3.size() * 100) + "%");
		} else {
			pingjunyingyun.add(2, 0 + "");
		}
		b = 0.0;
		if (yingyun4.size() > 0) {

			for (int j = 0; j < yingyun4.size(); j++) {
				if (yingyun4.get(j).length() == 3) {
					b += Double.parseDouble(yingyun4.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(3, (int) (b / yingyun4.size() * 100) + "%");
		} else {
			pingjunyingyun.add(3, 0 + "");
		}
		b = 0.0;
		if (yingyun5.size() > 0) {
			for (int j = 0; j < yingyun5.size(); j++) {
				if (yingyun5.get(j).length() == 3) {
					b += Double.parseDouble(yingyun5.get(j).substring(0, 2)) / 100;
				}
			}
			pingjunyingyun.add(4, (int) (b / yingyun5.size() * 100) + "%");
		} else {
			pingjunyingyun.add(4, 0 + "");
		}
		b = 0.0;
		if (yingyun6.size() > 0) {

			for (int j = 0; j < yingyun6.size(); j++) {
				if (yingyun6.get(j).length() == 3) {
					b += Double.parseDouble(yingyun6.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(5, (int) (b / yingyun6.size() * 100) + "%");
		} else {
			pingjunyingyun.add(5, 0 + "");
		}

		b = 0.0;
		if (yingyun7.size() > 0) {

			for (int j = 0; j < yingyun7.size(); j++) {
				if (yingyun7.get(j).length() == 3) {
					b += Double.parseDouble(yingyun7.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(6, (int) (b / yingyun7.size() * 100) + "%");
		} else {
			pingjunyingyun.add(6, 0 + "");
		}
		b = 0.0;
		if (yingyun8.size() > 0) {

			for (int j = 0; j < yingyun8.size(); j++) {
				if (yingyun8.get(j).length() == 3) {
					b += Double.parseDouble(yingyun8.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(7, (int) (b / yingyun8.size() * 100) + "%");
		} else {
			pingjunyingyun.add(7, 0 + "");
		}
		b = 0.0;
		if (yingyun9.size() > 0) {

			for (int j = 0; j < yingyun9.size(); j++) {
				if (yingyun9.get(j).length() == 3) {
					b += Double.parseDouble(yingyun9.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(8, (int) (b / yingyun9.size() * 100) + "%");
		} else {
			pingjunyingyun.add(8, 0 + "");
		}
		b = 0.0;
		if (yingyun10.size() > 0) {

			for (int j = 0; j < yingyun10.size(); j++) {
				if (yingyun10.get(j).length() == 3) {
					b += Double.parseDouble(yingyun10.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(9, (int) (b / yingyun10.size() * 100) + "%");
		} else {
			pingjunyingyun.add(9, 0 + "");
		}
		b = 0.0;
		if (yingyun11.size() > 0) {

			for (int j = 0; j < yingyun11.size(); j++) {
				if (yingyun11.get(j).length() == 3) {
					b += Double.parseDouble(yingyun11.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(10, (int) (b / yingyun11.size() * 100) + "%");
		} else {
			pingjunyingyun.add(10, 0 + "");

		}
		b = 0.0;
		if (yingyun12.size() > 0) {

			for (int j = 0; j < yingyun12.size(); j++) {
				if (yingyun12.get(j).length() == 3) {
					b += Double.parseDouble(yingyun12.get(j).substring(0, 2)) / 100;
				}

			}
			pingjunyingyun.add(11, (int) (b / yingyun12.size() * 100) + "%");
		} else {
			pingjunyingyun.add(11, 0 + "");
		}
		l.setPjyingyun(pingjunyingyun);
		l.setShijian(shijian);
		return l;
	}
	
	
	
	
	// 最近12小时中1小時未營運車輛
	public int[] findwyingyun12(int i) {
		int[] shuju = new int[12];
		for (int j = 0; j < shuju.length; j++) {
			shuju[j] = 0;
		}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian = null, zuotian = null, qiantian = null, shangzhou = null;
		String time = null;
		try {
			Date d = new Date();
			time = dft.format(d);
			jintian = time;
			Calendar date = Calendar.getInstance();
			date.setTime(d);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			Calendar date7 = Calendar.getInstance();
			date7.setTime(d);
			date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
			Date date8 = dft.parse(dft.format(date7.getTime()));
			qiantian = dft.format(date8);
			Calendar date3 = Calendar.getInstance();
			date3.setTime(d);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd HH");
		String hnow = df.format(new Date()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h1 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h2 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h3 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h4 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h5 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h6 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h7 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h8 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h9 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h10 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h11 = df.format(calendar.getTime()) + ":00";
		String d = null,d1=null;
		if (i == 0) {
			d = shangzhou;
		} else if (i == 1) {
			d = qiantian;
		} else if (i == 2) {
			d = zuotian;
		} else if (i == 3) {
			d = jintian;
		}
		Date beginDate = null;
		if (Integer.parseInt(jintian.substring(8, 10))>Integer.parseInt(h11.substring(0, 2))) {
			try {
				beginDate = dft.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			d1 = dft.format(date.getTime());
		}else {
			d1=d;
		}
		String sql="select * from TB_TAXI_RUN_INFO_RECORD_TEST t where" +
		" db_time>=to_date('" + d1 + " " + h11.substring(3, 8)+"','yyyy-MM-dd HH24:mi:ss') and" +
		" db_time<=to_date('" + d+ " " + hnow.substring(3, 8) +"','yyyy-MM-dd HH24:mi:ss') order by db_time";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String dbtime = null;
			while (rs.next()) {
				LOADONLINE l = new LOADONLINE();
				dbtime = rs.getString("db_time").substring(11, 16);
				if (dbtime.equals(h11.substring(3, 8))) {
					shuju[0] = (9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h10.substring(3, 8))) {
					shuju[1] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h9.substring(3, 8))) {
					shuju[2] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h8.substring(3, 8))) {
					shuju[3] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h7.substring(3, 8))) {
					shuju[4] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h6.substring(3, 8))) {
					shuju[5] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h5.substring(3, 8))) {
					shuju[6] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h4.substring(3, 8))) {
					shuju[7] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h3.substring(3, 8))) {
					shuju[8] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h2.substring(3, 8))) {
					shuju[9] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(h1.substring(3, 8))) {
					shuju[10] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				} else if (dbtime.equals(hnow.substring(3, 8))) {
					shuju[11] =(9612-Integer.parseInt(rs.getString("run_taxis")));
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String stime = null;
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY,
				calendar1.get(Calendar.HOUR_OF_DAY) - 6);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		stime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		int w = Integer.parseInt(stime.substring(11, 13));
		int[] a = new int[6];
		if (i == 3 && time.equals(stime.substring(0, 10))) {
			for (int y = 0; y < 6; y++) {
				double b = findwyymeiyou(y);
				a[y] = (int) b;
			}
			for (int j = 0; j < a.length; j++) {
				shuju[6 + j] = a[j];
			}
			for (int j = 0; j < shuju.length; j++) {
				if (j>0) {
					if (shuju[j] == 0) {
						shuju[j] = shuju[j - 1];
					}
				}
			}
		}
		for (int j = 0; j < shuju.length; j++) {
			if (j>0) {
				if (shuju[j] == 0) {
					shuju[j] = shuju[j - 1];
				}
			}
		}
		return shuju;
	}

	// 营运率最近6小时
	public int findwyymeiyou(int i) {
		String stime = "", etime = "";
		if (i == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 6);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 5);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 1) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 5);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 4);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 2) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 4);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 3);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 3) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 3);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 2);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 4) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, calendar1
					.get(Calendar.HOUR_OF_DAY) - 1);
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(calendar1.getTime()).substring(0, 14) + "00:00";
		} else if (i == 5) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, calendar
					.get(Calendar.HOUR_OF_DAY) - 1);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stime = df.format(calendar.getTime()).substring(0, 14) + "00:00";
			Date d = new Date();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			etime = df1.format(d).substring(0, 14) + "00:00";
		}
		String sj=stime.substring(0, 4);
		String sql="select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t" +
		" where shangche>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss')" +
		" and  shangche<to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')";
		System.out.println(sql);
		double totalpage = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				totalpage = 9612-rs.getInt("c");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int)totalpage;
	}
	
	// 最近12小时中1小時未營運車輛平均值
	public List<LOADONLINE> findwyypj12() {
		List<LOADONLINE> list = new ArrayList<LOADONLINE>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		String time = sdf.format(d);
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		String sql="select * from TB_TAXI_RUN_INFO_RECORD_TEST t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
		" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss')";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		LOADONLINE zhuanhuan = zhuanhuanwyy(list);
		List<LOADONLINE> list1 = new ArrayList<LOADONLINE>();
		list1.add(zhuanhuan);
		return list1;
	}

	public LOADONLINE zhuanhuanwyy(List<LOADONLINE> list) {
		LOADONLINE l = new LOADONLINE();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH");
		String hnow = df.format(new Date()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h1 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h2 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h3 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h4 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h5 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h6 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h7 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h8 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h9 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h10 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h11 = df.format(calendar.getTime()) + ":00";
		List<String> yingyun1 = new ArrayList<String>();
		List<String> yingyun2 = new ArrayList<String>();
		List<String> yingyun3 = new ArrayList<String>();
		List<String> yingyun4 = new ArrayList<String>();
		List<String> yingyun5 = new ArrayList<String>();
		List<String> yingyun6 = new ArrayList<String>();
		List<String> yingyun7 = new ArrayList<String>();
		List<String> yingyun8 = new ArrayList<String>();
		List<String> yingyun9 = new ArrayList<String>();
		List<String> yingyun10 = new ArrayList<String>();
		List<String> yingyun11 = new ArrayList<String>();
		List<String> yingyun12 = new ArrayList<String>();
		String [] shijian=new String [12];
		shijian[11]=hnow;
		shijian[10]=h1;
		shijian[9]=h2;
		shijian[8]=h3;
		shijian[7]=h4;
		shijian[6]=h5;
		shijian[5]=h6;
		shijian[4]=h7;
		shijian[3]=h8;
		shijian[2]=h9;
		shijian[1]=h10;
		shijian[0]=h11;
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).getDb_time().length() == 10|| list.get(i).getDb_time().substring(11, 16).equals(h11)) {
				if (list.get(i).getOnline_rate() != null&& list.get(i).getOnline_rate().length() > 0&& list.get(i).getOnline_rate() != "0") {
					yingyun1.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h10)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun2.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h9)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun3.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h8)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun4.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h7)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun5.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h6)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun6.add(list.get(i).getOnline_rate());
				}
			}else if (list.get(i).getDb_time().substring(11, 16).equals(h5)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun7.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(h4)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun8.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h3)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun9.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h2)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun10.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					h1)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun11.add(list.get(i).getOnline_rate());
				}
			} else if (list.get(i).getDb_time().substring(11, 16).equals(
					hnow)) {
				if (list.get(i).getOnline_rate() != null
						&& list.get(i).getOnline_rate().length() > 0
						&& list.get(i).getOnline_rate() != "0") {
					yingyun12.add(list.get(i).getOnline_rate());
				}
			} 
		}
		List<String> pingjunyingyun = new ArrayList<String>();
		double b = 0.0;
		b = 0;
		if (yingyun1.size() > 0) {
			for (int j = 0; j < yingyun1.size(); j++) {
					b += Double.parseDouble(yingyun1.get(j)) ;
			}
			pingjunyingyun.add(0, (int) (b / yingyun1.size()) + "");
		} else {
			pingjunyingyun.add(0, 0 + "");
		}
		b = 0.0;
		if (yingyun2.size() > 0) {

			for (int j = 0; j < yingyun2.size(); j++) {
					b += Double.parseDouble(yingyun2.get(j));
			}
			pingjunyingyun.add(1, (int) (b / yingyun2.size() ) + "");
		} else {
			pingjunyingyun.add(1, 0 + "");
		}
		b = 0.0;
		if (yingyun3.size() > 0) {

			for (int j = 0; j < yingyun3.size(); j++) {
					b += Double.parseDouble(yingyun3.get(j));
			}
			pingjunyingyun.add(2, (int) (b / yingyun3.size() ) + "");
		} else {
			pingjunyingyun.add(2, 0 + "");
		}
		b = 0.0;
		if (yingyun4.size() > 0) {

			for (int j = 0; j < yingyun4.size(); j++) {
					b += Double.parseDouble(yingyun4.get(j));
			}
			pingjunyingyun.add(3, (int) (b / yingyun4.size() ) + "");
		} else {
			pingjunyingyun.add(3, 0 + "");
		}
		b = 0.0;
		if (yingyun5.size() > 0) {
			for (int j = 0; j < yingyun5.size(); j++) {
					b += Double.parseDouble(yingyun5.get(j));
			}
			pingjunyingyun.add(4, (int) (b / yingyun5.size() ) + "");
		} else {
			pingjunyingyun.add(4, 0 + "");
		}
		b = 0.0;
		if (yingyun6.size() > 0) {

			for (int j = 0; j < yingyun6.size(); j++) {
					b += Double.parseDouble(yingyun6.get(j));
			}
			pingjunyingyun.add(5, (int) (b / yingyun6.size() ) + "");
		} else {
			pingjunyingyun.add(5, 0 + "");
		}

		b = 0.0;
		if (yingyun7.size() > 0) {

			for (int j = 0; j < yingyun7.size(); j++) {
					b += Double.parseDouble(yingyun7.get(j));
			}
			pingjunyingyun.add(6, (int) (b / yingyun7.size() ) + "");
		} else {
			pingjunyingyun.add(6, 0 + "");
		}
		b = 0.0;
		if (yingyun8.size() > 0) {

			for (int j = 0; j < yingyun8.size(); j++) {
					b += Double.parseDouble(yingyun8.get(j));
			}
			pingjunyingyun.add(7, (int) (b / yingyun8.size() ) + "");
		} else {
			pingjunyingyun.add(7, 0 + "");
		}
		b = 0.0;
		if (yingyun9.size() > 0) {

			for (int j = 0; j < yingyun9.size(); j++) {
					b += Double.parseDouble(yingyun9.get(j));
			}
			pingjunyingyun.add(8, (int) (b / yingyun9.size() ) + "");
		} else {
			pingjunyingyun.add(8, 0 + "");
		}
		b = 0.0;
		if (yingyun10.size() > 0) {

			for (int j = 0; j < yingyun10.size(); j++) {
					b += Double.parseDouble(yingyun10.get(j));
			}
			pingjunyingyun.add(9, (int) (b / yingyun10.size() ) + "");
		} else {
			pingjunyingyun.add(9, 0 + "");
		}
		b = 0.0;
		if (yingyun11.size() > 0) {

			for (int j = 0; j < yingyun11.size(); j++) {
					b += Double.parseDouble(yingyun11.get(j));
			}
			pingjunyingyun.add(10, (int) (b / yingyun11.size() ) + "");
		} else {
			pingjunyingyun.add(10, 0 + "");

		}
		b = 0.0;
		if (yingyun12.size() > 0) {

			for (int j = 0; j < yingyun12.size(); j++) {
					b += Double.parseDouble(yingyun12.get(j));
			}
			pingjunyingyun.add(11, (int) (b / yingyun12.size() ) + "");
		} else {
			pingjunyingyun.add(11, 0 + "");
		}
		l.setPjyingyun(pingjunyingyun);
		l.setShijian(shijian);
		return l;
	}
	
	
	
	
	// 最近12小时重點監控區域車輛數量
	public int[] findjksl12(int i) {
		int[] shuju = new int[12];
		for (int j = 0; j < shuju.length; j++) {
			shuju[j] = 0;
		}
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String jintian = null, zuotian = null, qiantian = null, shangzhou = null;
		String time = null;
		try {
			Date d = new Date();
			time = dft.format(d);
			jintian = time;
			Calendar date = Calendar.getInstance();
			date.setTime(d);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			Calendar date7 = Calendar.getInstance();
			date7.setTime(d);
			date7.set(Calendar.DATE, date7.get(Calendar.DATE) - 2);
			Date date8 = dft.parse(dft.format(date7.getTime()));
			qiantian = dft.format(date8);
			Calendar date3 = Calendar.getInstance();
			date3.setTime(d);
			date3.set(Calendar.DATE, date3.get(Calendar.DATE) - 7);
			Date date4 = dft.parse(dft.format(date3.getTime()));
			shangzhou = dft.format(date4);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd HH");
		String hnow = df.format(new Date()) + ":00";
		String hnow1 = df.format(new Date()) + ":01";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h1 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h2 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h3 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h4 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h5 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h6 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h7 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h8 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h9 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h10 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h11 = df.format(calendar.getTime()) + ":00";
		String d = null,d1=null;
		if (i == 0) {
			d = shangzhou;
		} else if (i == 1) {
			d = qiantian;
		} else if (i == 2) {
			d = zuotian;
		} else if (i == 3) {
			d = jintian;
		}
		Date beginDate = null;
		if (Integer.parseInt(jintian.substring(8, 10))>Integer.parseInt(h11.substring(0, 2))) {
			try {
				beginDate = dft.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			d1 = dft.format(date.getTime());
		}else {
			d1=d;
		}
		String sql="select * from TB_TAXI_AREA_CONFIGURATION t where " +
		" db_time>=to_date('" + d1 + " " + h11.substring(3, 8)+"','yyyy-mm-dd hh24:mi:ss')" +
		" and db_time<=to_date('" + d+ " " + hnow1.substring(3, 8) +"','yyyy-mm-dd hh24:mi:ss')";
		sql+=" order by db_time";
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String dbtime = null;
			int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0;
			while (rs.next()) {
				LOADONLINE l = new LOADONLINE();
				dbtime = rs.getString("db_time").substring(11, 16);
				if (dbtime.equals(h11.substring(3, 8))) {
					a1+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h10.substring(3, 8))) {
					a2+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h9.substring(3, 8))) {
					a3+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h8.substring(3, 8))) {
					a4+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h7.substring(3, 8))) {
					a5+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h6.substring(3, 8))) {
					a6+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h5.substring(3, 8))) {
					a7+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h4.substring(3, 8))) {
					a8+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h3.substring(3, 8))) {
					a9+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h2.substring(3, 8))) {
					a10+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(h1.substring(3, 8))) {
					a11+=Integer.parseInt(rs.getString("taxi_quantity"));
				} else if (dbtime.equals(hnow.substring(3, 8))) {
					a12+=Integer.parseInt(rs.getString("taxi_quantity"));
				}
			}
			shuju[0]=a1;
			shuju[1]=a2;
			shuju[2]=a3;
			shuju[3]=a4;
			shuju[4]=a5;
			shuju[5]=a6;
			shuju[6]=a7;
			shuju[7]=a8;
			shuju[8]=a9;
			shuju[9]=a10;
			shuju[10]=a11;
			shuju[11]=a12;
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shuju;
	}
	
	
	//计算重点车辆数量上周平均
	public List<Area>findzhongdainpj(){
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
    
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_TAXI_AREA_CONFIGURATION t where db_time>=to_date('"+st+"','yyyy-MM-dd HH24:mi:ss')" +
				" and db_time<=to_date('"+et+"','yyyy-MM-dd HH24:mi:ss')";
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
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH");
		String hnow = df.format(new Date()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h1 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h2 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h3 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h4 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h5 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h6 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h7 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h8 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h9 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h10 = df.format(calendar.getTime()) + ":00";
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 1);
		String h11 = df.format(calendar.getTime()) + ":00";
		List<String> area1 = new ArrayList<String>();
		List<String> area2 = new ArrayList<String>();
		List<String> area3 = new ArrayList<String>();
		List<String> area4 = new ArrayList<String>();
		List<String> area5 = new ArrayList<String>();
		List<String> area6 = new ArrayList<String>();
		List<String> area7 = new ArrayList<String>();
		List<String> area8 = new ArrayList<String>();
		List<String> area9 = new ArrayList<String>();
		List<String> area10 = new ArrayList<String>();
		List<String> area11 = new ArrayList<String>();
		List<String> area12 = new ArrayList<String>();
		String [] shijian=new String [12];
		shijian[11]=hnow;
		shijian[10]=h1;
		shijian[9]=h2;
		shijian[8]=h3;
		shijian[7]=h4;
		shijian[6]=h5;
		shijian[5]=h6;
		shijian[4]=h7;
		shijian[3]=h8;
		shijian[2]=h9;
		shijian[1]=h10;
		shijian[0]=h11;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDb_time().length()==10||list.get(i).getDb_time().substring(11,16).equals(h11)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area1.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h10)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area2.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h9)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area3.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h8)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area4.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h7)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area5.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h6)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area6.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h5)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area7.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h4)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area8.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h3)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area9.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h2)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area10.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(h1)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area11.add(list.get(i).getTaxi_quantity());
				}
			}else if (list.get(i).getDb_time().substring(11,16).equals(hnow)) {
				if (list.get(i).getTaxi_quantity()!=null&&list.get(i).getTaxi_quantity().length()>0&&list.get(i).getTaxi_quantity()!="0") {
					area12.add(list.get(i).getTaxi_quantity());
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
		area.setSj(pingjunarea);
		area.setShijian(shijian);
		return area;
	}
	
	
	
	
	//下線車輛分析
	public int[] findoffonline(){
		int [] off=new int[3];
		String sql="select stime from TB_TAXI_STATUS_NEW t,HZGPS_TAXI.VW_VEHICLE@taxilink v where t.mdt_no = v.sim_num and v.vehi_no like '%浙AT%'";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date bxs=calendar.getTime();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.DAY_OF_MONTH, calendar1.get(Calendar.DAY_OF_MONTH) - 1);
		Date oneday=calendar1.getTime();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.DAY_OF_MONTH, calendar2.get(Calendar.DAY_OF_MONTH) - 3);
		Date threeday=calendar2.getTime();
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int a=0,b=0,c=0;
			String time="";
			Date d = null;
			while(rs.next()){
				time=rs.getString("stime");
				try {
					d=df.parse(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (d.getTime()<bxs.getTime()
						&&d.getTime()>=oneday.getTime()) {
					a++;
				}else if (d.getTime()<oneday.getTime()
						&&d.getTime()>=threeday.getTime()) {
					b++;
				}else if (d.getTime()<threeday.getTime()) {
					c++;
				}
			}
			off[0]=a;
			off[1]=b;
			off[2]=c;
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return off;
	}
	
	
	//上线未营运车辆查询
	public int []findonlinewyy(){
		int[] a=new int[2];
		String sql="select * from (select * from TB_TAXI_OFFLINE t" +
				" order by db_time desc) where rownum=1";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				a[0]=rs.getInt("OFFLINE_ONE_THREE");
				a[1]=rs.getInt("OFFLINE_MORE_THREE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	//查询车辆总数
	public int[] findcls(){
		int[] a = {};
		int zs=0,cls=0;
		String sql = "select vehi_no from HZGPS_TAXI.TB_VEHICLE@TAXILINK t";
		System.out.println(sql);
		try {
			Connection con = DataBese.getConnectionOfOracle();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				zs++;
				if(rs.getString("vehi_no").indexOf("浙AT")>=0){
					cls++;
				}
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(zs+"  "+cls);
		a[0]=zs;
		a[1]=cls;
		a[2]=zs-cls;
		return a;
	}
}
