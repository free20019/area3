package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;

import com.tw.entity.Company;
import com.tw.entity.User;
import com.tw.entity.Vehicle;
import com.tw.entity.Vhic;
import com.tw.entity.station;
import com.ze.util.DataBese;

public class JurisdictionDao {
//查询数据
	public List<User>finduser(String name){
		List<User>list=new ArrayList<User>();
		String sql="select * from tb_user where 1=1";
		String a[]=name.split(",");
		if (!a[0].equals("所有公司")) {
			sql+=" and date_view_type like '%"+a[0]+"%'";
		}
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setId(rs.getString("id"));
				u.setDate_view_type(rs.getString("date_view_type")==null?"&nbsp;":rs.getString("date_view_type"));
				u.setUsername(rs.getString("username")==null?"&nbsp;":rs.getString("username"));
				u.setPassword(rs.getString("password")==null?"&nbsp;":rs.getString("password"));
				u.setReal_name(rs.getString("real_name")==null?"&nbsp;":rs.getString("real_name"));
				u.setStation_id(rs.getString("station_id")==null?"&nbsp;":rs.getString("station_id"));
				list.add(u);
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
	//新增
	public int useradd(String username,String password,String date_view_type,
			String station_id,String realname){
		int count=0;
		String sql="insert into tb_user(username,password,date_view_type,station_id,real_name)" +
				" values('"+username+"','"+password+"','"+date_view_type+"','"+station_id+"'" +
						" ,'"+realname+"')";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			count=0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//删除
	public int userdel(String id){
		int count=0;
		String sql="delete from tb_user t where ID='"+id.trim()+"'";
		PreparedStatement pstmt =null;
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			count = pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//根据id查询
	public User userid(String id){
		User u=new User();
		String sql="select * from tb_user where id='"+id.trim()+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				u.setId(rs.getString("id"));
				u.setDate_view_type(rs.getString("date_view_type"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setReal_name(rs.getString("real_name"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	//修改
	public int edituser(String realname,String username,String password,String date_view_type,String  station,String id){
		int count=0;
		String sql="update tb_user set real_name='"+realname+"', username='"+username+"', password='"+password+"'" +
				", date_view_type='"+date_view_type+"', station_id='"+station+"'" +
						" where id='"+id+"'";
		Connection con=null;
		try {
			con=DataBese.getConnectionOfOracle();
			Statement st=con.createStatement();
			count=st.executeUpdate(sql);
			st.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查询总公司
	public List<Vhic>findba(){
		List<Vhic>list=new ArrayList<Vhic>();
		String sql="select * from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK where owner_id ='11' or owner_id='27' order by ba_name";
		try {
			Connection  con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setBa_id(rs.getString("ba_id"));
				v.setBa_name(rs.getString("ba_name"));
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
	//查询分公司
	public List<Vhic>findcomp(String ba_id){
		List<Vhic> list=new ArrayList<Vhic>();
		String sql="select * from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK a,HZGPS_TAXI.TB_COMPANY@TAXILINK c where a.ba_id=c.ba_id ";
		if (ba_id.length()>=0&&ba_id!=null) {
			sql+=" and ba_name ='"+ba_id+"'";
		}
		sql+=" order by comp_name";
		try {
			Connection  con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Vhic v=new Vhic();
				v.setComp_name(rs.getString("comp_name"));
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
			sql+=" and c.comp_name ='"+comp_id+"'";
		}
		sql+=" order by vehi_no ";
		Connection con=null;
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
			sql+=" and a.ba_name ='"+comp_id+"'";
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
	//新增岗位
	public int addstation(String stationname,String stationjuri,String stationadmin){
		int count=0;
		String sql="insert into TB_AREA_STATION (STATION_NAME,STATION_JURI,station_admin) values('"+stationname+"','"+stationjuri+"','"+stationadmin+"')";
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
			count=0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
	//查看岗位
	public List<station>findstation(){
		List<station>list=new ArrayList<station>();
		String sql="select * from TB_AREA_STATION t ";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				station s=new station();
				s.setStation_juri(rs.getString("station_juri")==null?"&nbsp;":rs.getString("station_juri"));
				s.setStation_name(rs.getString("station_name")==null?"&nbsp;":rs.getString("station_name"));
				s.setStation_admin(rs.getString("station_admin")==null?"&nbsp;":rs.getString("station_admin"));
				s.setId(rs.getString("id"));
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
	//根据id查询岗位
	public station stationid(String id){
		station s=new station();
		String sql="select * from TB_AREA_STATION t where id='"+id+"'";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				s.setStation_admin(rs.getString("station_admin")==null?"&nbsp;":rs.getString("station_admin"));
				s.setStation_juri(rs.getString("station_juri")==null?"&nbsp;":rs.getString("station_juri"));
				s.setStation_name(rs.getString("station_name")==null?"&nbsp;":rs.getString("station_name"));
				s.setId(rs.getString("id")==null?"&nbsp":rs.getString("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	//根据id删除岗位
	public int stationdel(String id){
		int count=0;
		String sql="delete from TB_AREA_STATION where id='"+id+"'";
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
	//根据id修改岗位
	public int editstation(String id,String stationname,String stationadmin,String stationjuri){
		int count=0;
		String sql="update TB_AREA_STATION set station_name='"+stationname+"',station_admin='"
		+stationadmin+"',station_juri='"+stationjuri+"' where id='"+id+"'";
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
				count=0;
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return count;
	}
}
