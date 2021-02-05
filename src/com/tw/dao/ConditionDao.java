package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tw.entity.Condition;
import com.tw.entity.Groups;
import com.ze.util.DataBese;
/**
 * 
 * @author sven.zhang
 * @since 2015.4.13
 *	获取公司
 *	获取分公司
 *	获取车号
 *	获取群组
 */
public class ConditionDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
//获取群组
	public List<Groups> getGroups(){
		List<Groups> list = new ArrayList<Groups>();
		String sql = "select * from tb_taxi_vehicle_groupname order by group_name";
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Groups g = new Groups();
				g.setId(rs.getString("group_id"));
				g.setGroupName(rs.getString("group_name"));
				list.add(g);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return list;
	}
	//群组车号查询
	public List<Groups> getGroupVhic(Groups g){
		List<Groups> list = new ArrayList<Groups>();
		String sql = "select * from tb_taxi_vehicle_group where 1= 1 ";
		if(g.getId() != null)
		sql +=" and group_id ='"+g.getId()+"'";
		sql += "order by group_vhic";
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Groups gs = new Groups();
				gs.setGroupVhic(rs.getString("group_vhic"));
				list.add(gs);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return list;
	}
	// 分公司查询
	public List<String> getBranch(String company) {
		List<String> list = new ArrayList<String>();
		if ("杭州之江汽车出租有限公司(上泗)".equals(company)) {
			list.add("杭州之江汽车出租有限公司(上泗)");
		} else if ("杭州之江汽车出租有限公司".equals(company)) {
			list.add("杭州之江汽车出租有限公司");
		} else {
			String sql = "select * from HZGPS_TAXI.TB_COMPANY@TAXILINK t ,HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v where v.ba_id =t.ba_id and ba_name ='"
					+ company + "' order by comp_name";
			con = DataBese.getConnectionOfOracle();
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(rs.getString("comp_name"));
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

	// 公司查询
	public List<String> getCompany() {
		List<String> list = new ArrayList<String>();
		String sql = "select * from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK order by ba_name";
		
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt("owner_id") == 27) {
					list.add("杭州之江汽车出租有限公司(上泗)");
				} else {
					if(!rs.getString("owner_id").equals("21") && !rs.getString("owner_id").equals("12"))
					list.add(rs.getString("ba_name"));
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
	//资格证号查询
	public List<String> getCertNo(Condition co){
		List<String> list = new ArrayList<String>();
		String sj=co.getStartTime().substring(0,4);
		StringBuffer sql = new StringBuffer(
				"select distinct yingyun from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t ,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where shangche>=to_date(");
		sql.append("'"+co.getStartTime()+"','yyyy-MM-dd hh24:mi:ss') and shangche<=to_date(");
		sql.append("'"+co.getEndTime()+"','yyyy-MM-dd hh24:mi:ss') ");
		if (co.getCompany() != null){
			if(co.getCompany().equals("杭州之江汽车出租有限公司(上泗)")){
				sql = sql.append(" and v.ba_id = '" + 27001 + "'");
			}else{
				sql = sql.append(" and v.ba_name = '" + co.getCompany() + "'");
				if (co.getBranch() != null)
					sql = sql.append(" and v.comp_name = '" + co.getBranch() + "'");
			}
		}	
		sql.append(" and '浙'||t.vhic = v.vehi_no order by yingyun");
		System.out.println("zigez  "+sql);
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("yingyun"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}
	// 车号查询
	public List<String> getCardNo(Condition co) {
			List<String> list = new ArrayList<String>();
			StringBuffer sql = new StringBuffer(
					"select vehi_no from HZGPS_TAXI.VW_VEHICLE@TAXILINK v,HZGPS_TAXI.TB_COMPANY@TAXILINK t  where 1=1 and v.comp_id = t.comp_id  ");
			if (co.getCompany() != null){
				if(co.getCompany().equals("杭州之江汽车出租有限公司(上泗)")){
					sql = sql.append("and v.ba_id = '" + 27001 + "'");
				}else{
					sql = sql.append("and v.ba_name = '" + co.getCompany() + "'");
					if (co.getBranch() != null && co.getBranch() == "")
						sql = sql.append(" and v.comp_name = '" + co.getBranch() + "'");
				}				
			}	
			sql.append("and owner_id !='21' and owner_id !='12' order by vehi_no");	
			con = DataBese.getConnectionOfOracle();
			try {
				ps = con.prepareStatement(sql.toString());
				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(rs.getString("vehi_no"));
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
