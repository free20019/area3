package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tw.entity.Condition;
import com.tw.entity.RegularOffline;
import com.ze.util.DataBese;

public class RegularOfflineDao {
	private Connection con;
	private PreparedStatement ps;
	private Statement st ;
	private ResultSet rs;
	private SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void addRegular(RegularOffline re){
		String sql = "insert into TB_TAXI_REGULAR_OFFLINE(cp_num,ef_num,sim_num,total,operating_user,operating_time,other_num)values(?,?,?,?,?,?,?)";
		con = DataBese.getConnectionOfOracle();
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, re.getCpNum());
			ps.setInt(2, re.getEfNum());
			ps.setInt(3, re.getSimNum());
			ps.setInt(4, re.getTotal());
			ps.setString(5, re.getOperatingUser());
			ps.setString(6, sf.format(new Date()));
			ps.setInt(7, re.getOtherNum());
			ps.addBatch();
			ps.executeBatch();
			con.commit();
			ps.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	public List<RegularOffline> getRegular(){
		List<RegularOffline> list = new ArrayList<RegularOffline>();
		String sql = "select*from TB_TAXI_REGULAR_OFFLINE order by operating_time desc";
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()){
				RegularOffline re = new RegularOffline();
				re.setRegularId(rs.getString("regular_id"));
				re.setCpNum(rs.getInt("cp_num"));
				re.setEfNum(rs.getInt("ef_num"));
				re.setSimNum(rs.getInt("sim_num"));
				re.setOtherNum(rs.getInt("other_num"));
				re.setTotal(rs.getInt("total"));
				re.setOperatingTime(rs.getString("operating_time")+"");
				re.setOperatingUser(rs.getString("operating_user"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public List<RegularOffline> getRegularByTime(Condition condition){
		List<RegularOffline> list = new ArrayList<RegularOffline>();
		String sql ="";
		if(condition !=null)
			sql = "select*from TB_TAXI_REGULAR_OFFLINE where operating_time>='"+condition.getStartTime()+"' and operating_time<='"+condition.getEndTime()+"' order by operating_time desc";
		else{
			sql = "select*from TB_TAXI_REGULAR_OFFLINE  order by operating_time desc";
		}
		con = DataBese.getConnectionOfOracle();
		try {
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()){
				RegularOffline re = new RegularOffline();
				re.setRegularId(rs.getString("regular_id"));
				re.setCpNum(rs.getInt("cp_num"));
				re.setEfNum(rs.getInt("ef_num"));
				re.setSimNum(rs.getInt("sim_num"));
				re.setOtherNum(rs.getInt("other_num"));
				re.setTotal(rs.getInt("total"));
				re.setOperatingTime(rs.getString("operating_time")+"");
				re.setOperatingUser(rs.getString("operating_user"));
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	//更新
	public void updateOne(RegularOffline re){
		String sql = "update TB_TAXI_REGULAR_OFFLINE set cp_num=?,ef_num=?,sim_num=?,total=?,operating_user=?,operating_time=?,other_num=?" 
				+" where regular_id='"+re.getRegularId()+"'";
		con = DataBese.getConnectionOfOracle();
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, re.getCpNum());
			ps.setInt(2, re.getEfNum());
			ps.setInt(3, re.getSimNum());
			ps.setInt(4, re.getTotal());
			ps.setString(5, re.getOperatingUser());
			ps.setString(6, sf.format(new Date()));
			ps.setInt(7, re.getOtherNum());
			ps.addBatch();
			ps.executeBatch();
			con.commit();
			ps.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	//删除
	public void delone(RegularOffline re){
		String sql = "delete from TB_TAXI_REGULAR_OFFLINE where regular_id='"+re.getRegularId()+"'";
		con = DataBese.getConnectionOfOracle();
		try {
			st =con.createStatement();
			st.executeUpdate(sql);
			con.commit();
			st.close();
			con.close();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	public RegularOffline selOne(RegularOffline re){
		RegularOffline regular = null;
		String sql = "select * from TB_TAXI_REGULAR_OFFLINE where regular_id='"+re.getRegularId()+"'";
		con = DataBese.getConnectionOfOracle();
		try {
		ps= con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			regular =new RegularOffline();
			regular.setRegularId(rs.getString("regular_id"));
			regular.setCpNum(rs.getInt("cp_num"));
			regular.setEfNum(rs.getInt("ef_num"));
			regular.setSimNum(rs.getInt("sim_num"));
			regular.setOtherNum(rs.getInt("other_num"));
			regular.setTotal(rs.getInt("total"));
			regular.setOperatingTime(rs.getString("operating_time")+"");
			regular.setOperatingUser(rs.getString("operating_user"));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regular;
	}
}
