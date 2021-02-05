package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.tw.entity.SpringFestival;
import com.ze.util.DataBese;

public class SpringFestivalDao {
	//杭州市出租车春运期间分布情况(每小时统计) 查询关键字日期
	public List<SpringFestival> findHourFB(String time){
		List<SpringFestival>list=new ArrayList<SpringFestival>();
		String stime=time+" 00:00:00";
		String etime=time+" 23:59:59";
		String sql="select db_time time,sum(decode(areaid,'1',proportion,null)) henan," +
				" sum(decode(areaid,'2',proportion,null)) anhui," +
				" sum(decode(areaid,'3',proportion,null)) jiangxi," +
				" sum(decode(areaid,'4',proportion,null)) zhejiang," +
				" sum(decode(areaid,'5',proportion,null)) dahangzhou," +
				" sum(decode(areaid,'6',proportion,null)) hangzhou" +
				" from TB_TAXI_DISTRIBUTION  where" +
				" db_time >= to_date('"+stime+"', 'yyyy-MM-dd HH24:mi:ss')" +
				" and db_time <= to_date('"+etime+"', 'yyyy-MM-dd HH24:mi:ss')" +
				" group by db_time order by db_time desc";
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setAnhui(rs.getDouble("anhui"));
				s.setDahangzhou(rs.getDouble("dahangzhou"));
				s.setHangzhou(rs.getDouble("hangzhou"));
				s.setHenan(rs.getDouble("henan"));
				s.setJiangxi(rs.getDouble("jiangxi"));
				s.setTime(rs.getString("time").substring(0, 19));
				s.setZhejiang(rs.getDouble("zhejiang"));
				list.add(s);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//杭州市出租车春运期间分布情况(每日统计)
	public List<SpringFestival>finddayfx(){
		List<SpringFestival>list = new ArrayList<SpringFestival>();
		String sql="select statisticaldate time," +
				" sum(decode(areaid, '1', proportion, null)) henan," +
				" sum(decode(areaid, '2', proportion, null)) anhui," +
				" sum(decode(areaid, '3', proportion, null)) jiangxi," +
				" sum(decode(areaid, '4', proportion, null)) zhejiang," +
				" sum(decode(areaid, '5', proportion, null)) dahangzhou," +
				" sum(decode(areaid, '6', proportion, null)) hangzhou" +
				" from TB_TAXI_DISTRIBUTION_DAY where" +
				" (areaid = '1' or areaid = '2' or areaid = '3' or" +
				" areaid = '4' or areaid = '5' or areaid = '6')" +
				" group by statisticaldate order by time desc";
		try{
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setAnhui(rs.getDouble("anhui"));
				s.setDahangzhou(rs.getDouble("dahangzhou"));
				s.setHangzhou(rs.getDouble("hangzhou"));
				s.setHenan(rs.getDouble("henan"));
				s.setJiangxi(rs.getDouble("jiangxi"));
				s.setTime(rs.getString("time").substring(0, 10));
				s.setZhejiang(rs.getDouble("zhejiang"));
				list.add(s);
			}
			rs.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	//重点区域小时服务车次统计(每小时统计)
	public List<SpringFestival>findzdqyxstj(String time){
		String stime=time+" 00:00:00";
		String etime=time+" 23:59:59";
		List<SpringFestival>list=new ArrayList<SpringFestival>();
		String sql="select areaid," +
				" sum(decode(to_char(begindate,'hh24'), '00', taxinum, null)) a00," +
				" sum(decode(to_char(begindate,'hh24'), '01', taxinum, null)) a01," +
				" sum(decode(to_char(begindate,'hh24'), '02', taxinum, null)) a02," +
				" sum(decode(to_char(begindate,'hh24'), '03', taxinum, null)) a03," +
				" sum(decode(to_char(begindate,'hh24'), '04', taxinum, null)) a04," +
				" sum(decode(to_char(begindate,'hh24'), '05', taxinum, null)) a05," +
				" sum(decode(to_char(begindate,'hh24'), '06', taxinum, null)) a06," +
				" sum(decode(to_char(begindate,'hh24'), '07', taxinum, null)) a07," +
				" sum(decode(to_char(begindate,'hh24'), '08', taxinum, null)) a08, " +
				"sum(decode(to_char(begindate,'hh24'), '09', taxinum, null)) a09, " +
				"sum(decode(to_char(begindate,'hh24'), '10', taxinum, null)) a10," +
				"  sum(decode(to_char(begindate,'hh24'), '11', taxinum, null)) a11," +
				" sum(decode(to_char(begindate,'hh24'), '12', taxinum, null)) a12," +
				" sum(decode(to_char(begindate,'hh24'), '13', taxinum, null)) a13," +
				" sum(decode(to_char(begindate,'hh24'), '14', taxinum, null)) a14," +
				"  sum(decode(to_char(begindate,'hh24'), '15', taxinum, null)) a15," +
				" sum(decode(to_char(begindate,'hh24'), '16', taxinum, null)) a16," +
				" sum(decode(to_char(begindate,'hh24'), '17', taxinum, null)) a17," +
				" sum(decode(to_char(begindate,'hh24'), '18', taxinum, null)) a18," +
				"  sum(decode(to_char(begindate,'hh24'), '19', taxinum, null)) a19," +
				" sum(decode(to_char(begindate,'hh24'), '20', taxinum, null)) a20," +
				" sum(decode(to_char(begindate,'hh24'), '21', taxinum, null)) a21," +
				"  sum(decode(to_char(begindate,'hh24'), '22', taxinum, null)) a22," +
				" sum(decode(to_char(begindate,'hh24'), '23', taxinum, null)) a23" +
				" from TB_TAXI_IMPORTANT_AREA where" +
				" begindate >= to_date('"+stime+"', 'yyyy-MM-dd HH24:mi:ss')" +
				" and begindate <= to_date('"+etime+"', 'yyyy-MM-dd HH24:mi:ss')" +
				" group by areaid";
		System.out.println(sql);
		try{
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setArea(idzh(rs.getString("areaid")));
				s.setA00(rs.getString("a00")==null?"":rs.getString("a00"));
				s.setA01(rs.getString("a01")==null?"":rs.getString("a01"));
				s.setA02(rs.getString("a02")==null?"":rs.getString("a02"));
				s.setA03(rs.getString("a03")==null?"":rs.getString("a03"));
				s.setA04(rs.getString("a04")==null?"":rs.getString("a04"));
				s.setA05(rs.getString("a05")==null?"":rs.getString("a05"));
				s.setA06(rs.getString("a06")==null?"":rs.getString("a06"));
				s.setA07(rs.getString("a07")==null?"":rs.getString("a07"));
				s.setA08(rs.getString("a08")==null?"":rs.getString("a08"));
				s.setA09(rs.getString("a09")==null?"":rs.getString("a09"));
				s.setA10(rs.getString("a10")==null?"":rs.getString("a10"));
				s.setA11(rs.getString("a11")==null?"":rs.getString("a11"));
				s.setA12(rs.getString("a12")==null?"":rs.getString("a12"));
				s.setA13(rs.getString("a13")==null?"":rs.getString("a13"));
				s.setA14(rs.getString("a14")==null?"":rs.getString("a14"));
				s.setA15(rs.getString("a15")==null?"":rs.getString("a15"));
				s.setA16(rs.getString("a16")==null?"":rs.getString("a16"));
				s.setA17(rs.getString("a17")==null?"":rs.getString("a17"));
				s.setA18(rs.getString("a18")==null?"":rs.getString("a18"));
				s.setA19(rs.getString("a19")==null?"":rs.getString("a19"));
				s.setA20(rs.getString("a20")==null?"":rs.getString("a20"));
				s.setA21(rs.getString("a21")==null?"":rs.getString("a21"));
				s.setA22(rs.getString("a22")==null?"":rs.getString("a22"));
				s.setA23(rs.getString("a23")==null?"":rs.getString("a23"));
				list.add(s);
			}
			rs.close();
			ps.close();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//重点区域小时服务车次空重车转换(每小时统计)
	public List<SpringFestival>findzdqyxskz(String time){
		String stime=time+" 00:00:00";
		String etime=time+" 23:59:59";
		List<SpringFestival>list=new ArrayList<SpringFestival>();
		String sql="select areaid," +
				" sum(decode(to_char(begindate, 'hh24'), '00', EMPTYTAXINUM, null)) a00," +
				" sum(decode(to_char(begindate, 'hh24'), '01', EMPTYTAXINUM, null)) a01," +
				" sum(decode(to_char(begindate, 'hh24'), '02', EMPTYTAXINUM, null)) a02," +
				" sum(decode(to_char(begindate, 'hh24'), '03', EMPTYTAXINUM, null)) a03," +
				" sum(decode(to_char(begindate, 'hh24'), '04', EMPTYTAXINUM, null)) a04," +
				" sum(decode(to_char(begindate, 'hh24'), '05', EMPTYTAXINUM, null)) a05," +
				" sum(decode(to_char(begindate, 'hh24'), '06', EMPTYTAXINUM, null)) a06," +
				" sum(decode(to_char(begindate, 'hh24'), '07', EMPTYTAXINUM, null)) a07," +
				" sum(decode(to_char(begindate, 'hh24'), '08', EMPTYTAXINUM, null)) a08," +
				" sum(decode(to_char(begindate, 'hh24'), '09', EMPTYTAXINUM, null)) a09," +
				" sum(decode(to_char(begindate, 'hh24'), '10', EMPTYTAXINUM, null)) a10," +
				" sum(decode(to_char(begindate, 'hh24'), '11', EMPTYTAXINUM, null)) a11," +
				" sum(decode(to_char(begindate, 'hh24'), '12', EMPTYTAXINUM, null)) a12," +
				" sum(decode(to_char(begindate, 'hh24'), '13', EMPTYTAXINUM, null)) a13," +
				" sum(decode(to_char(begindate, 'hh24'), '14', EMPTYTAXINUM, null)) a14," +
				" sum(decode(to_char(begindate, 'hh24'), '15', EMPTYTAXINUM, null)) a15," +
				" sum(decode(to_char(begindate, 'hh24'), '16', EMPTYTAXINUM, null)) a16," +
				" sum(decode(to_char(begindate, 'hh24'), '17', EMPTYTAXINUM, null)) a17," +
				" sum(decode(to_char(begindate, 'hh24'), '18', EMPTYTAXINUM, null)) a18," +
				" sum(decode(to_char(begindate, 'hh24'), '19', EMPTYTAXINUM, null)) a19," +
				" sum(decode(to_char(begindate, 'hh24'), '20', EMPTYTAXINUM, null)) a20," +
				" sum(decode(to_char(begindate, 'hh24'), '21', EMPTYTAXINUM, null)) a21," +
				" sum(decode(to_char(begindate, 'hh24'), '22', EMPTYTAXINUM, null)) a22," +
				" sum(decode(to_char(begindate, 'hh24'), '23', EMPTYTAXINUM, null)) a23," +
				" sum(decode(to_char(begindate, 'hh24'), '00', LOADTAXINUM, null)) b00," +
				" sum(decode(to_char(begindate, 'hh24'), '01', LOADTAXINUM, null)) b01," +
				" sum(decode(to_char(begindate, 'hh24'), '02', LOADTAXINUM, null)) b02," +
				" sum(decode(to_char(begindate, 'hh24'), '03', LOADTAXINUM, null)) b03," +
				" sum(decode(to_char(begindate, 'hh24'), '04', LOADTAXINUM, null)) b04," +
				" sum(decode(to_char(begindate, 'hh24'), '05', LOADTAXINUM, null)) b05," +
				" sum(decode(to_char(begindate, 'hh24'), '06', LOADTAXINUM, null)) b06," +
				" sum(decode(to_char(begindate, 'hh24'), '07', LOADTAXINUM, null)) b07," +
				" sum(decode(to_char(begindate, 'hh24'), '08', LOADTAXINUM, null)) b08," +
				" sum(decode(to_char(begindate, 'hh24'), '09', LOADTAXINUM, null)) b09," +
				" sum(decode(to_char(begindate, 'hh24'), '10', LOADTAXINUM, null)) b10," +
				" sum(decode(to_char(begindate, 'hh24'), '11', LOADTAXINUM, null)) b11," +
				" sum(decode(to_char(begindate, 'hh24'), '12', LOADTAXINUM, null)) b12," +
				" sum(decode(to_char(begindate, 'hh24'), '13', LOADTAXINUM, null)) b13," +
				" sum(decode(to_char(begindate, 'hh24'), '14', LOADTAXINUM, null)) b14," +
				" sum(decode(to_char(begindate, 'hh24'), '15', LOADTAXINUM, null)) b15," +
				" sum(decode(to_char(begindate, 'hh24'), '16', LOADTAXINUM, null)) b16," +
				" sum(decode(to_char(begindate, 'hh24'), '17', LOADTAXINUM, null)) b17," +
				" sum(decode(to_char(begindate, 'hh24'), '18', LOADTAXINUM, null)) b18," +
				" sum(decode(to_char(begindate, 'hh24'), '19', LOADTAXINUM, null)) b19," +
				" sum(decode(to_char(begindate, 'hh24'), '20', LOADTAXINUM, null)) b20," +
				" sum(decode(to_char(begindate, 'hh24'), '21', LOADTAXINUM, null)) b21," +
				" sum(decode(to_char(begindate, 'hh24'), '22', LOADTAXINUM, null)) b22," +
				" sum(decode(to_char(begindate, 'hh24'), '23', LOADTAXINUM, null)) b23" +
				" from TB_TAXI_IMPORTANT_AREA where" +
				" begindate >= to_date('"+stime+"', 'yyyy-MM-dd HH24:mi:ss')" +
				" and begindate <= to_date('"+etime+"', 'yyyy-MM-dd HH24:mi:ss')" +
				" group by areaid";
		System.out.println(sql);
		try{
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setArea(idzh(rs.getString("areaid")));
				s.setA00(rs.getString("a00")==null?"0.01":rs.getString("a00"));
				s.setA01(rs.getString("a01")==null?"0.01":rs.getString("a01"));
				s.setA02(rs.getString("a02")==null?"0.01":rs.getString("a02"));
				s.setA03(rs.getString("a03")==null?"0.01":rs.getString("a03"));
				s.setA04(rs.getString("a04")==null?"0.01":rs.getString("a04"));
				s.setA05(rs.getString("a05")==null?"0.01":rs.getString("a05"));
				s.setA06(rs.getString("a06")==null?"0.01":rs.getString("a06"));
				s.setA07(rs.getString("a07")==null?"0.01":rs.getString("a07"));
				s.setA08(rs.getString("a08")==null?"0.01":rs.getString("a08"));
				s.setA09(rs.getString("a09")==null?"0.01":rs.getString("a09"));
				s.setA10(rs.getString("a10")==null?"0.01":rs.getString("a10"));
				s.setA11(rs.getString("a11")==null?"0.01":rs.getString("a11"));
				s.setA12(rs.getString("a12")==null?"0.01":rs.getString("a12"));
				s.setA13(rs.getString("a13")==null?"0.01":rs.getString("a13"));
				s.setA14(rs.getString("a14")==null?"0.01":rs.getString("a14"));
				s.setA15(rs.getString("a15")==null?"0.01":rs.getString("a15"));
				s.setA16(rs.getString("a16")==null?"0.01":rs.getString("a16"));
				s.setA17(rs.getString("a17")==null?"0.01":rs.getString("a17"));
				s.setA18(rs.getString("a18")==null?"0.01":rs.getString("a18"));
				s.setA19(rs.getString("a19")==null?"0.01":rs.getString("a19"));
				s.setA20(rs.getString("a20")==null?"0.01":rs.getString("a20"));
				s.setA21(rs.getString("a21")==null?"0.01":rs.getString("a21"));
				s.setA22(rs.getString("a22")==null?"0.01":rs.getString("a22"));
				s.setA23(rs.getString("a23")==null?"0.01":rs.getString("a23"));
				
				s.setB00(rs.getString("b00")==null?"0.01":rs.getString("b00"));
				s.setB01(rs.getString("b01")==null?"0.01":rs.getString("b01"));
				s.setB02(rs.getString("b02")==null?"0.01":rs.getString("b02"));
				s.setB03(rs.getString("b03")==null?"0.01":rs.getString("b03"));
				s.setB04(rs.getString("b04")==null?"0.01":rs.getString("b04"));
				s.setB05(rs.getString("b05")==null?"0.01":rs.getString("b05"));
				s.setB06(rs.getString("b06")==null?"0.01":rs.getString("b06"));
				s.setB07(rs.getString("b07")==null?"0.01":rs.getString("b07"));
				s.setB08(rs.getString("b08")==null?"0.01":rs.getString("b08"));
				s.setB09(rs.getString("b09")==null?"0.01":rs.getString("b09"));
				s.setB10(rs.getString("b10")==null?"0.01":rs.getString("b10"));
				s.setB11(rs.getString("b11")==null?"0.01":rs.getString("b11"));
				s.setB12(rs.getString("b12")==null?"0.01":rs.getString("b12"));
				s.setB13(rs.getString("b13")==null?"0.01":rs.getString("b13"));
				s.setB14(rs.getString("b14")==null?"0.01":rs.getString("b14"));
				s.setB15(rs.getString("b15")==null?"0.01":rs.getString("b15"));
				s.setB16(rs.getString("b16")==null?"0.01":rs.getString("b16"));
				s.setB17(rs.getString("b17")==null?"0.01":rs.getString("b17"));
				s.setB18(rs.getString("b18")==null?"0.01":rs.getString("b18"));
				s.setB19(rs.getString("b19")==null?"0.01":rs.getString("b19"));
				s.setB20(rs.getString("b20")==null?"0.01":rs.getString("b20"));
				s.setB21(rs.getString("b21")==null?"0.01":rs.getString("b21"));
				s.setB22(rs.getString("b22")==null?"0.01":rs.getString("b22"));
				s.setB23(rs.getString("b23")==null?"0.01":rs.getString("b23"));
				list.add(s);
			}
			rs.close();
			ps.close();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(((int)Integer.parseInt(list.get(0).getA22())+(int)Integer.parseInt(list.get(0).getB22()))+"   @@@");
		return list;
	}
	//杭州出租车春运期间分布情况(每日统计)
	public List<SpringFestival>findzdqyrtj(){
		List<SpringFestival>list=new ArrayList<SpringFestival>();
		String sql="select to_char(begindate,'yyyy-mm-dd') begindate," +
				"sum(dongzhan) dongzhan,sum(jichang) jichang," +
				"sum(chengzhan) chengzhan,sum(keyun) keyun," +
				"sum(beizhan) beizhan,sum(nanzhan) nanzhan,sum(xizhan) xizhan" +
				" from( select begindate," +
				" sum(decode(areaid, '7', taxinum, null)) dongzhan," +
				" sum(decode(areaid, '8', taxinum, null)) jichang," +
				" sum(decode(areaid, '9', taxinum, null)) chengzhan," +
				" sum(decode(areaid, '10', taxinum, null)) keyun," +
				" sum(decode(areaid, '11', taxinum, null)) beizhan," +
				" sum(decode(areaid, '12', taxinum, null)) nanzhan," +
				" sum(decode(areaid, '13', taxinum, null)) xizhan" +
				" from TB_TAXI_IMPORTANT_AREA group by begindate)" +
				" group by to_char(begindate,'yyyy-mm-dd') order by begindate desc";
		try{
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setTime(rs.getString("begindate"));
				s.setJichang(rs.getString("jichang"));
				s.setDongzhan(rs.getString("dongzhan"));
				s.setChengzhan(rs.getString("chengzhan"));
				s.setKeyun(rs.getString("keyun"));
				s.setBeizhan(rs.getString("beizhan"));
				s.setNanzhan(rs.getString("nanzhan"));
				s.setXizhan(rs.getString("xizhan"));
				list.add(s);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	//杭州出租车春运期间分布空重车转换(每日统计)
	public List<SpringFestival>findzdqyrkz(String time){
		List<SpringFestival>list=new ArrayList<SpringFestival>();
		String sql="select to_char(begindate, 'yyyy-mm-dd') begindate," +
				" sum(dongzhank) dongzhank, sum(dongzhanz) dongzhanz," +
				" sum(jichangk) jichangk, sum(jichangz) jichangz," +
				" sum(chengzhank) chengzhank, sum(chengzhanz) chengzhanz," +
				" sum(keyunk) keyunk, sum(keyunz) keyunz," +
				" sum(beizhank) beizhank, sum(beizhanz) beizhanz," +
				" sum(nanzhank) nanzhank, sum(nanzhanz) nanzhanz," +
				" sum(xizhank) xizhank, sum(xizhanz) xizhanz from" +
				" (select begindate, sum(decode(areaid, '7', EMPTYTAXINUM, null)) dongzhank," +
				" sum(decode(areaid, '7', LOADTAXINUM, null)) dongzhanz," +
				" sum(decode(areaid, '8', EMPTYTAXINUM, null)) jichangk," +
				" sum(decode(areaid, '8', LOADTAXINUM, null)) jichangz," +
				" sum(decode(areaid, '9', EMPTYTAXINUM, null)) chengzhank," +
				" sum(decode(areaid, '9', LOADTAXINUM, null)) chengzhanz," +
				" sum(decode(areaid, '10', EMPTYTAXINUM, null)) keyunk," +
				" sum(decode(areaid, '10', LOADTAXINUM, null)) keyunz," +
				" sum(decode(areaid, '11', EMPTYTAXINUM, null)) beizhank," +
				" sum(decode(areaid, '11', LOADTAXINUM, null)) beizhanz," +
				" sum(decode(areaid, '12', EMPTYTAXINUM, null)) nanzhank," +
				"  sum(decode(areaid, '12', LOADTAXINUM, null)) nanzhanz," +
				" sum(decode(areaid, '13', EMPTYTAXINUM, null)) xizhank," +
				" sum(decode(areaid, '13', LOADTAXINUM, null)) xizhanz" +
				" from TB_TAXI_IMPORTANT_AREA  where to_char(begindate,'yyyy-mm')='"+time+"'" +
				" group by begindate)" +
				" group by to_char(begindate, 'yyyy-mm-dd') order by begindate desc";
		System.out.println(sql);
		try{
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setTime(rs.getString("begindate"));
				s.setJichang(rs.getString("jichangk"));
				s.setDongzhan(rs.getString("dongzhank"));
				s.setChengzhan(rs.getString("chengzhank"));
				s.setKeyun(rs.getString("keyunk"));
				s.setBeizhan(rs.getString("beizhank"));
				s.setNanzhan(rs.getString("nanzhank"));
				s.setXizhan(rs.getString("xizhank"));
				s.setJichangz(rs.getString("jichangz"));
				s.setDongzhanz(rs.getString("dongzhanz"));
				s.setChengzhanz(rs.getString("chengzhanz"));
				s.setKeyunz(rs.getString("keyunz"));
				s.setBeizhanz(rs.getString("beizhanz"));
				s.setNanzhanz(rs.getString("nanzhanz"));
				s.setXizhanz(rs.getString("xizhanz"));
				list.add(s);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	//重点区域服务车次日总数
	public List<SpringFestival> findzdqyzs(String time){
		List<SpringFestival>list=new ArrayList<SpringFestival>();
		String sql="select recordtime time," +
				" sum(decode(area_id,7, times, null)) dongzhan," +
				" sum( decode(area_id,8, times, null)) jichang," +
				" sum( decode(area_id,9, times, null)) chengzhan," +
				" sum(  decode(area_id,10, times, null)) keyun," +
				" sum(decode(area_id,11, times, null)) beizhan," +
				" sum( decode(area_id,12, times, null)) nanzhan," +
				" sum( decode(area_id,13, times, null)) xizhan from " +
				" TB_TAXI_ENTER_AREA where to_char(recordtime,'yyyy-mm')='"+time+"'" +
				" group by recordtime order by recordtime desc";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				SpringFestival s=new SpringFestival();
				s.setDongzhan(rs.getString("dongzhan"));
				s.setJichang(rs.getString("jichang"));
				s.setChengzhan(rs.getString("chengzhan"));
				s.setKeyun(rs.getString("keyun"));
				s.setBeizhan(rs.getString("beizhan"));
				s.setNanzhan(rs.getString("nanzhan"));
				s.setXizhan(rs.getString("xizhan"));
				s.setTime(rs.getString("time").substring(0, 10));
				list.add(s);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public String idzh(String info){
		String xx="";
		if(info.equals("7")){
			xx="火车东站";
		}else if(info.equals("8")){
			xx="机场";
		}else if(info.equals("9")){
			xx="杭州城站";
		}else if(info.equals("10")){
			xx="杭州汽车客运中心站";
		}else if(info.equals("11")){
			xx="杭州汽车北站";
		}else if(info.equals("12")){
			xx="杭州汽车南站";
		}else if(info.equals("13")){
			xx="杭州汽车西站";
		}
		return xx;
	}
	
}
