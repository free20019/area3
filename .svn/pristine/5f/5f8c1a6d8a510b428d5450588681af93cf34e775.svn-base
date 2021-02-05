package com.tw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tw.entity.Area;
import com.tw.entity.Company;
import com.ze.util.DataBese;

public class PublicDao {

	public List<Company> findqxba(String a) {
		List<Company>list=new ArrayList<Company>();
		String sql="select * from HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v where 1=1 ";
		String ba=a.split(",")[0];
		if (!ba.equals("所有公司")) {
			sql+=" and ba_name = '"+ba+"'";
		}
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Company c=new Company();
				c.setBaid(rs.getString("ba_id"));
				c.setName(rs.getString("ba_name"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//查询所有区域
	public List<Area>findareaall(){
		List<Area>list=new ArrayList<Area>();
		String sql="select * from TB_AREA ts";
		try {
			Connection con=DataBese.getConnectionOfOracle();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Area a=new Area();
				a.setArea_id(rs.getString("area_id"));
				a.setArea_name(rs.getString("area_name"));
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
