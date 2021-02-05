package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tw.entity.TB_Citizen_2014;
import com.tw.entity.TbVehicle;
import com.tw.entity.Vhic;
import com.ze.util.DataBese;


public class TransactionDao {
	public List<TB_Citizen_2014> findOperCash(String starttime, String endtime,String vhic,String ba_name,String comp_name,String data,String uid, int max, int min){
		String riqi=starttime.substring(0,4);
		List<TB_Citizen_2014>list=new ArrayList<TB_Citizen_2014>();
		String sql="";
		if(max==0&&min==0){
			sql="select ta.*,rownum rn from( select a.* from ( select distinct *" +
					" from hzgps_citizen.tb_citizen_"+riqi+"@TAXILINK45 t where" +
					" t.shangche > to_date('"+starttime+"','yyyy-mm-dd hh24:mi:ss') and" +
					"  t.shangche < to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss')) a," +
					" ( select * from tb_vehicle t where 1=1";
			if (vhic!=null&&vhic.length()>0&&!vhic.equals("0")) {
				sql=sql+" and vehi_no like  '%"+vhic+"%'";
			}
				if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("所有公司")&&!ba_name.equals("0")) {
					sql+=" and ba_name like '%"+ba_name+"%' ";
				}
				if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("所有分公司")&&!comp_name.equals("0")&&!comp_name.equals("null")) {
					sql+=" and comp_name like '%"+comp_name+"%' ";
				}
			sql+=") b  where '浙'||a.vhic=b.vehi_no) ta";
		}else {
			sql="select taa.* from (select ta.*,rownum rn from( select a.* from ( select distinct *" +
			" from hzgps_citizen.tb_citizen_"+riqi+"@TAXILINK45 t where" +
			" t.shangche > to_date('"+starttime+"','yyyy-mm-dd hh24:mi:ss') and" +
			"  t.shangche < to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss')) a," +
			" ( select * from tb_vehicle t where 1=1";
			if (vhic!=null&&vhic.length()>0&&!vhic.equals("0")) {
				sql=sql+" and vehi_no like  '%"+vhic+"%'";
			}
				if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("所有公司")&&!ba_name.equals("0")) {
					sql+=" and ba_name like '%"+ba_name+"%' ";
				}
				if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("null")&&!comp_name.equals("所有分公司")&&!comp_name.equals("0")) {
					sql+=" and comp_name like '%"+comp_name+"%' ";
				}
	sql+=") b  where '浙'||a.vhic=b.vehi_no) ta)taa  where taa.rn>"+(min-1)+" and taa.rn<="+max;
		}	
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				TB_Citizen_2014 tb=createTbCitizen_2014(rs);
				list.add(tb);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//车辆营运交易时段总页数查询
	public int findOpertotalpage(String starttime, String endtime,
			String vhic,String ba_name,String comp_name,String data,String uid)  {
		String riqi=starttime.substring(0,4);
		String sql="select count(*) c from ( select distinct * from hzgps_citizen.tb_citizen_"+riqi+"@TAXILINK45 t" +
				" where t.shangche > to_date('"+starttime+"','yyyy-mm-dd hh24:mi:ss') and" +
				"  t.shangche < to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss')) a," +
				" ( select * from tb_vehicle t where 1=1 ";
		if (vhic!=null&&vhic.length()>0&&!vhic.equals("0")) {
			sql=sql+" and vehi_no like  '%"+vhic+"%'";
		}
			if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("所有公司")&&!ba_name.equals("0")) {
				sql+=" and ba_name like '%"+ba_name+"%' ";
			}
			if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("null")&&!comp_name.equals("所有分公司")&&!comp_name.equals("0")) {
				sql+=" and comp_name like '%"+comp_name+"%' ";
			}
		sql+=") b where '浙'||a.vhic=b.vehi_no";
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalpage;
	}
	//TB
	private TB_Citizen_2014 createTbCitizen_2014(ResultSet rs) throws SQLException{
		TB_Citizen_2014 tb=new TB_Citizen_2014();
		try {
		tb.setCbid(rs.getString("cbid"));
		tb.setJiaoyitype(rs.getString("jiaoyitype"));
		tb.setSim(rs.getString("sim"));
		tb.setYingyun(rs.getString("yingyun"));
		tb.setVhic(rs.getString("vhic"));
		tb.setShangchedate(rs.getString("shangchedate"));
		tb.setShangchetime(rs.getString("shangchetime"));
		tb.setShangche(rs.getString("shangche").subSequence(0, 19)+"");
		tb.setXiachedate(rs.getString("xiachedate"));
		tb.setXiache(rs.getString("xiache").subSequence(0, 19)+"");
		String jicheng=rs.getString(11);
		int ji=Integer.parseInt(jicheng);
		double jic=((double)ji)/10;
		String jichengString=jic+"0";
		tb.setJicheng(jichengString);
		String denghou=rs.getString(12);
		int deng=Integer.parseInt(denghou);
		String denghouString=deng+"";
		tb.setDenghou(denghouString);
		String jine=rs.getString(13);
		int jin=Integer.parseInt(jine);
		double e=((double)jin)/100;
		String jineString=e+"";
		tb.setJine(jineString);
		String kongshi=rs.getString(14);
		int kong=Integer.parseInt(kongshi);
		double k=((double)kong)/10;
		String kongshiString=k+"";
		tb.setKongshi(kongshiString);
		tb.setYuane(rs.getString("yuane"));
		tb.setCity(rs.getString("city"));
		tb.setXiaofei(rs.getString("xiaofei"));
		tb.setKahao(rs.getString("kahao"));
		tb.setBaoliu(rs.getString("baoliu"));
		tb.setZhongduanno(rs.getString("zhongduanno"));
		tb.setJiaoyi(rs.getString("jiaoyi"));
		tb.setKatype(rs.getString("katype"));
		tb.setQianbao(rs.getString("qianbao"));
		tb.setQiyong(rs.getString("qiyong"));
		tb.setJiakuan(rs.getString("jiakuan"));
		tb.setTac(rs.getString("tac"));
		tb.setPos(rs.getString("pos"));
		tb.setQiye(rs.getString("qiye"));
		tb.setZhongduantime(rs.getString("zhongduantime"));
		tb.setZhongduan(rs.getString("zhongduan"));
		tb.setZhongxintime(rs.getString("zhongxintime"));
		tb.setZhongxin(rs.getString("zhongxin").subSequence(0, 19)+"");
		tb.setYanshi(rs.getString("yanshi"));
		tb.setWanshenyanshi(rs.getString("wanshenyanshi"));
		tb.setMaxcount(rs.getString("maxcount"));
		tb.setAdditionalfee(rs.getString("additionalfee"));
		tb.setVehicleturn(rs.getInt("vehicleturn"));
		tb.setState(rs.getString("state"));
		tb.setR(rs.getString("rn"));
		} catch (Exception e) {
		}
		return tb;
	}
	//营运交易最新时间查询
	public List<TB_Citizen_2014> findYYLatestTime(String starttime,
			String endtime,  String vhic,String ba_name,String comp_name,String data,String uid, int max, int min) {
		String riqi=starttime.substring(0,4);
		List<TB_Citizen_2014>list=new ArrayList<TB_Citizen_2014>();
		String sql="";
		if(max==0&&min==0){
			sql="select ta.*,rownum rn from (select vhic,max(xiache) xiache,zhongxintime,shangche,cbid,jicheng,denghou,jine,kongshi,jiaoyitype from" +
					" hzgps_citizen.tb_citizen_"+riqi+"@TAXILINK45 t ,tb_vehicle c where '浙'||t.vhic=c.vehi_no" +
					" and t.shangche > to_date('"+starttime+"','yyyy-mm-dd hh24:mi:ss') and " +
					" t.shangche < to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss') ";
			if (vhic!=null&&vhic.length()>0&&!vhic.equals("0")) {
				sql=sql+" and vehi_no like  '%"+vhic+"%'";
			}
				if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("所有公司")&&!ba_name.equals("0")) {
					sql+=" and ba_name like '%"+ba_name+"%' ";
				}
				if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("所有分公司")&&!comp_name.equals("0")) {
					sql+=" and comp_name like '%"+comp_name+"%' ";
				}
			sql+="  group by vhic ,zhongxintime,shangche,cbid,jicheng,denghou,jine,kongshi,jiaoyitype) ta";
		}else {
			sql="select taa.* from (select ta.*,rownum rn from (select vhic,max(xiache) xiache,zhongxintime" +
					" ,shangche,cbid,jicheng,denghou,jine,kongshi,jiaoyitype" +
					" from hzgps_citizen.tb_citizen_"+riqi+"@TAXILINK45 t ,tb_vehicle c where '浙'||t.vhic=c.vehi_no" +
					" and t.shangche > to_date('"+starttime+"','yyyy-mm-dd hh24:mi:ss') and" +
					"  t.shangche < to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss') " ;
			if (vhic!=null&&vhic.length()>0&&!vhic.equals("0")) {
				sql=sql+" and vehi_no like  '%"+vhic+"%'";
			}
				if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("所有公司")&&!ba_name.equals("0")) {
					sql+=" and ba_name like '%"+ba_name+"%' ";
				}
				if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("所有分公司")&&!comp_name.equals("0")) {
					sql+=" and comp_name like '%"+comp_name+"%' ";
				}
			sql+=" group by vhic ,zhongxintime,shangche,cbid,jicheng,denghou,jine,kongshi,jiaoyitype) ta)taa  where taa.rn>"+(min-1)+" and taa.rn<="+max;
		}	
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				TB_Citizen_2014 tb=new TB_Citizen_2014();
				tb.setR(rs.getString("rn"));
				tb.setXiache(rs.getString("xiache").substring(0, 19));
				tb.setZhongxin(rs.getString("zhongxintime"));
				tb.setVhic(rs.getString("vhic"));
				tb.setKongshi(rs.getString("kongshi"));
				tb.setJicheng(rs.getString("jicheng"));
				tb.setJine(rs.getString("jine"));
				tb.setShangche(rs.getString("shangche").substring(0, 19));
				tb.setDenghou(rs.getString("denghou"));
				tb.setCbid(rs.getString("cbid"));
				tb.setJiaoyitype(rs.getString("jiaoyitype"));
				list.add(tb);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//营运交易最新时间总页数查询
	public int findYYtotalpage(String starttime, String endtime,
			String vhic,String ba_name,String comp_name,String data,String uid) {
		String riqi=starttime.substring(0,4);
		String sql="select count(*) c from (select max(xiache) ,vhic from" +
				" hzgps_citizen.tb_citizen_"+riqi+"@TAXILINK45 t ,tb_vehicle c where '浙'||t.vhic=c.vehi_no" +
				" and t.shangche > to_date('"+starttime+"','yyyy-mm-dd hh24:mi:ss') and " +
				" t.shangche < to_date('"+endtime+"','yyyy-mm-dd hh24:mi:ss') ";
		if (vhic!=null&&vhic.length()>0&&!vhic.equals("0")) {
			sql=sql+" and vehi_no like  '%"+vhic+"%'";
		}
			if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("所有公司")&&!ba_name.equals("0")) {
				sql+=" and ba_name like '%"+ba_name+"%' ";
			}
			if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("所有分公司")&&!comp_name.equals("0")) {
				sql+=" and comp_name like '%"+comp_name+"%' ";
			}
		sql+="group by vhic)";
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalpage;
	}
}
