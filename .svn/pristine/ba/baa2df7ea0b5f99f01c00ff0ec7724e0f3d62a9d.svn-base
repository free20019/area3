package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tw.entity.GroupManage;
import com.tw.entity.Vhic;
import com.ze.util.DataBese;
/***
 * 
 * @author sven.zhang
 * @since 2015.4.27
 * */
public class GroupManageDao {
	private Connection con;
	private PreparedStatement ps;
	private PreparedStatement pr;
	private ResultSet rs;
	private Statement st;
	public List<GroupManage> getGroup(){
		List<GroupManage> list = new ArrayList<GroupManage>();
		String sql="select * from TB_TAXI_VEHICLE_GROUPNAME";
		try {
			con=DataBese.getConnectionOfOracle();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				GroupManage g = new GroupManage();
				g.setGroupId(rs.getString("group_id"));
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
	public List<GroupManage> getBranch(GroupManage gm) {
		List<GroupManage> list = new ArrayList<GroupManage>();
		String sql = "select distinct(comp_name)from TB_TAXI_VEHICLE_GROUP where 1=1 ";
		if (gm.getGroupId() !=null && !"0".equals(gm.getGroupId()))
			sql += "and group_id='" + gm.getGroupId() + "'";
		sql += " order by comp_name";
		try {
			con = DataBese.getConnectionOfOracle();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GroupManage g = new GroupManage();
				g.setBranch(rs.getString("comp_name"));
				list.add(g);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	// 查询群组中的车号
	public List<GroupManage> findcardNo(GroupManage gm) {
		List<GroupManage> list = new ArrayList<GroupManage>();
		String sql = "select distinct(group_vhic) from TB_TAXI_VEHICLE_GROUP g  where 1=1 ";
		if ( gm.getGroupId() !=null && !"0".equals(gm.getGroupId()))
			sql += "and g.group_id='" + gm.getGroupId() + "'";
		if ( gm.getBranch() !=null && !"0".equals(gm.getBranch()))
			sql += "and g.comp_name='" + gm.getBranch() + "'";
		sql += " order by group_vhic asc ";
		try {
			 con = DataBese.getConnectionOfOracle();
			 ps = con.prepareStatement(sql);
			 rs = ps.executeQuery();
			while (rs.next()) {
				GroupManage g = new GroupManage();
				g.setVehicle(rs.getString("group_vhic"));
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

	// 查询车的信息
	public List<GroupManage> findInfo(GroupManage gm) {
		List<GroupManage> list = new ArrayList<GroupManage>();
		String sql = "select * from TB_TAXI_VEHICLE_GROUP g, HZGPS_TAXI.VW_VEHICLE@TAXILINK v, " +
				"tb_taxi_vehicle_groupname t where g.group_vhic=v.vehi_no and t.group_ID=G.GROUP_ID";
		if (gm !=null && gm.getGroupId() !=null && !"0".equals(gm.getGroupId()))
			sql += " and g.group_id='" + gm.getGroupId() + "'";
		if (gm !=null && gm.getBranch() !=null && !"0".equals(gm.getBranch()))
			sql += " and g.comp_name='" + gm.getBranch() + "'";
		if (gm !=null && gm.getVehicle() != null && !"0".equals(gm.getVehicle()) )
			sql += " and g.group_vhic='" + gm.getVehicle() + "'";
		sql += " order by group_vhic asc ";
		try {
			 con = DataBese.getConnectionOfOracle();
			 ps = con.prepareStatement(sql);
			 rs = ps.executeQuery();
			while (rs.next()) {
				GroupManage g = new GroupManage();
				g.setVehicle(rs.getString("group_vhic"));
				g.setBranch(rs.getString("comp_name"));
				g.setGroupName(rs.getString("group_name"));
				g.setColor(rs.getString("vc_name"));
				g.setTerminalType(rs.getString("mt_name"));
				g.setVehicleType(rs.getString("vt_name"));
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
	public boolean addOne(GroupManage gm,List<Vhic>list){
		String sql  = "insert into TB_TAXI_VEHICLE_GROUPNAME(group_id,group_name)values(?,?)";
		
		String hql ="insert into TB_TAXI_VEHICLE_GROUP(group_id,group_vhic,ba_id,ba_name,comp_id,comp_name)values(?,?,?,?,?,?)";
		con =DataBese.getConnectionOfOracle();
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, gm.getGroupId());
			ps.setString(2, gm.getGroupName());
			ps.executeUpdate();
			pr = con.prepareStatement(hql);
			for(Vhic v:list){
				pr.setString(1, gm.getGroupId());
				pr.setString(2, v.getVehi_no());
				pr.setString(3, v.getBa_id());
				pr.setString(4, v.getBa_name());
				pr.setString(5, v.getComp_id());
				pr.setString(6, v.getComp_name());
				pr.addBatch();

			}
			pr.executeBatch();
			con.commit();
			
		} catch (SQLException e) {
			
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return true;
	}
	public void addGroup(GroupManage gm){
		
	}
	public List<Vhic> selectAll(String[] str) {
		List <Vhic> list = new ArrayList<Vhic>();
		String sql = "select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK where vehi_no=? ";
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql);
			for(int i = 0;i<str.length;i++){
			ps.setString(1, str[i]);
			rs = ps.executeQuery();
			while(rs.next()){
				Vhic v =new Vhic();
				v.setBa_id(rs.getString("ba_id"));
				v.setBa_name(rs.getString("ba_name"));
				v.setComp_id(rs.getString("comp_id"));
				v.setComp_name(rs.getString("comp_name"));
				v.setVehi_no(rs.getString("vehi_no"));
				list.add(v);
			}
			}
	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return list;
	}
	public boolean updateOne(GroupManage gm, List<Vhic> list) {
		String sql = "update TB_TAXI_VEHICLE_GROUPNAME set group_name='"+gm.getGroupName()+"'  where group_id='"+gm.getGroupId()+"'";
		String sql2 = "delete from TB_TAXI_VEHICLE_GROUP where group_id='"+gm.getGroupId()+"'";
		String hql ="insert into TB_TAXI_VEHICLE_GROUP(group_id,group_vhic,ba_id,ba_name,comp_id,comp_name)values(?,?,?,?,?,?)";
		con =DataBese.getConnectionOfOracle();
		try {
			con.setAutoCommit(false);
			st =con.createStatement();
			st.executeUpdate(sql);
			ps = con.prepareStatement(sql2);
			ps.executeUpdate();
			pr = con.prepareStatement(hql);
			for(Vhic v:list){
				pr.setString(1, gm.getGroupId());
				pr.setString(2, v.getVehi_no());
				pr.setString(3, v.getBa_id());
				pr.setString(4, v.getBa_name());
				pr.setString(5, v.getComp_id());
				pr.setString(6, v.getComp_name());
				pr.addBatch();

			}
			pr.executeBatch();
			con.commit();
			
		} catch (SQLException e) {
			
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return true;
	}
	public boolean deleteOne(GroupManage gm){
		String sql = "delete from TB_TAXI_VEHICLE_GROUP where group_id='"+gm.getGroupId()+"'";
		String hql = "delete from TB_TAXI_VEHICLE_GROUPNAME where group_id='"+gm.getGroupId()+"'";
		con = DataBese.getConnectionOfOracle();
		try {
			con.setAutoCommit(false);
			st =con.createStatement();
			st.executeUpdate(sql);
			ps = con.prepareStatement(hql);
			ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	return true;	
	}
}


