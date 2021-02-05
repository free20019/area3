package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tw.entity.Vhic;
import com.ze.util.DataBese;

public class GroupDao {
	//查询分公司
	public List<Vhic>findcomp(String ba_id){
		List<Vhic> list=new ArrayList<Vhic>();
		String sql="select c.comp_name,c.comp_id from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a,HZGPS_TAXI.TB_COMPANY@TAXILINK c where a.ba_id=c.ba_id ";
		if (ba_id.length()>=0&&ba_id!=null) {
			sql+=" and a.ba_id ='"+ba_id+"'";
		}
		sql+=" order by comp_name";
		System.out.println(sql);
		try {
			Connection  con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_name(rs.getString("comp_name"));
				v.setComp_id(rs.getString("comp_id"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Vhic>findvhicid(String comp_id){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from HZGPS_TAXI.TB_COMPANY@TAXILINK c,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where v.comp_id=c.comp_id  ";
		if (comp_id.length()>=0&&comp_id!=null) {
			sql+=" and c.comp_id ='"+comp_id+"'";
		}
		sql+=" order by vehi_no ";
		Connection con=null;
		System.out.println(sql);
		try {
			con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehi_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Vhic> findspdvhic(String ba_id,String comp_id,String info){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v where v.vehi_no like '%浙AT%'  ";
		if(ba_id.equals("0")){
			sql+="and v.vehi_no like '%"+info+"%'";
		}else if(!ba_id.equals("0")&&comp_id.equals("0")){
			sql+="and v.vehi_no like '%"+info+"%' and v.ba_id='"+ba_id+"'";
		}else if(!ba_id.equals("0")&&!comp_id.equals("0")){
			sql+="and v.vehi_no like '%"+info+"%' and v.comp_id='"+comp_id+"'";
		}
		sql+=" order by vehi_no ";
		Connection con=null;
	//	System.out.println(sql);
		try {
			con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehi_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Vhic>findvhicgs(String comp_id){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where a.ba_id=v.ba_id ";
		if (comp_id.length()>=0&&comp_id!=null) {
			sql+=" and a.ba_id ='"+comp_id+"'";
		}
		sql+=" order by vehi_no ";
		try {
			Connection  con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setVehi_no(rs.getString("vehi_no"));
				list.add(v);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//新增车辆组名
	public int vhicgroupnameadd(String groupname,String id){
		int count=0;
		String sql="insert into TB_TAXI_VEHICLE_GROUPNAME (group_name,group_id) values('"+groupname+"'," +
				"'"+id+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//新增车辆组车辆
	public int vhicgroupadd(String groupvhic,String groupid,String groupvhiccomp){
		int count=0;
		String sql="insert into TB_TAXI_VEHICLE_GROUP (group_vhic,group_id,comp_name) values('"+groupvhic+"'," +
				"'"+groupid+"','"+groupvhiccomp+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查询车辆组
	public List<Vhic>findvhicgroup(){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from TB_TAXI_VEHICLE_GROUPname";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setBa_id(rs.getString("id"));
				//v.setVehi_id(rs.getString("group_name"));
				v.setGroupid(rs.getString("group_id"));
				v.setGroupname(rs.getString("group_name"));
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
	//根据车辆组名查询该车辆组下的车号
	public List<Vhic>findgroupvhic(String groupid){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from TB_TAXI_VEHICLE_GROUPname n,TB_TAXI_VEHICLE_GROUP g" +
				" where n.group_id=rtrim(g.group_id) and g.group_id='"+groupid+"'";
		System.out.println(sql);
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setBa_id(rs.getString("id"));
				//v.setVehi_id(rs.getString("group_name"));
				v.setGroupid(rs.getString("group_id"));
				v.setGroupname(rs.getString("group_name"));
				v.setVehi_no(rs.getString("group_vhic"));
				v.setComp_name(rs.getString("comp_name"));
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
	//车辆组名删除
	public int delvhicgroup(String id){
		int count=0;
		String sql="delete from TB_TAXI_VEHICLE_GROUPname where group_id='"+id.trim()+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			count=ps.executeUpdate();
			ps.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//车辆组车辆删除
	public int delgroupvhic(String id){
		int count=0;
		String sql="delete from TB_TAXI_VEHICLE_GROUP where group_id='"+id.trim()+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			count=ps.executeUpdate();
			ps.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//根据id查询车辆组名
	public Vhic findvhicgroupid(String id){
		Vhic v=new Vhic();
		String sql="select * from TB_TAXI_VEHICLE_GROUPname where group_id='"+id.trim()+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				v.setBa_id(rs.getString("group_id"));
				v.setVehi_id(rs.getString("group_name"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	//根据id查询车辆组下的车号
	public List<Vhic> findgroupvhicid(String id){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from TB_TAXI_VEHICLE_GROUP where group_id='"+id.trim()+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setBa_id(rs.getString("id"));
				v.setVehi_no(rs.getString("group_vhic"));
				v.setComp_name(rs.getString("comp_name"));
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
	//修改车辆组名
	public int editvhicgroup(String id,String groupname){
		int count=0;
		String sql="update TB_TAXI_VEHICLE_GROUPname set group_name='"+groupname
		+"' where group_id='"+id.trim()+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//修改车辆组
	public int editgroupvhic(String id,String compname,String groupvhic){
		int count=0;
		String sql="update TB_TAXI_VEHICLE_GROUP set comp_name='"+compname
		+"',group_vhic='"+groupvhic+"' where group_id='"+id.trim()+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				count=0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	/***
	 * 
	 * @author sven.zhang
	 * @since 2015.4.27
	 * */
		public List<Vhic> getBranch(String id){
			List<Vhic>list=new ArrayList<Vhic>();
			String sql="select distinct(comp_name)from TB_TAXI_VEHICLE_GROUP where 1=1 ";
			if(!"0".equals(id)&& id!=null)
			sql+="and group_id='"+id.trim()+"'";
			sql+=" order by comp_name";
			Connection con=null;
			 
			try {
				con=DataBese.getConnectionOfOracle();
				PreparedStatement	ps = con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setComp_name(rs.getString("comp_name"));
					list.add(v);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return list;
		}
		//查询群组中的车号
		public List<Vhic>findcardNo(String groupid,String comp_name){
			List<Vhic>list=new ArrayList<Vhic>();
			String sql="select distinct(group_vhic) from TB_TAXI_VEHICLE_GROUP g  where 1=1 ";
			if(!"0".equals(groupid) && groupid !=null)
				sql+="and g.group_id='"+groupid+"'";
			if(!"0".equals(comp_name) && comp_name !=null)
				sql+="and g.comp_name='"+comp_name+"'";
			sql+=" order by group_vhic asc ";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setVehi_no(rs.getString("group_vhic"));					
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
		//查询车的信息
		public List<Vhic>findInfo(String groupid,String comp_name,String vhic){
			List<Vhic>list=new ArrayList<Vhic>();
			String sql="select * from TB_TAXI_VEHICLE_GROUP g  where 1=1 ";
			if(!"0".equals(groupid) && groupid !=null)
				sql+="and g.group_id='"+groupid+"'";
			if(!"0".equals(comp_name) && comp_name !=null)
				sql+="and g.comp_name='"+comp_name+"'";
			if(!"0".equals(vhic) && vhic!=null)
				sql+="and g.group_vhic='"+vhic+"'";
			sql+=" order by group_vhic asc ";
			try {
				Connection con=DataBese.getConnectionOfOracle();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					Vhic v=new Vhic();
					v.setVehi_no(rs.getString("group_vhic"));	
					v.setComp_name(rs.getString("comp_name"));
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
