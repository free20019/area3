package com.ze.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.ze.util.GetProperties;
import com.ze.util.DataBese;



public class DataBese {
	private  static DataBese factory=null;
	
	private DataBese(){

	}
	
	public static DataBese getInstance(){
		
		if(factory == null){
			factory = new DataBese();
		}
		return factory;
	}
	
	public static Connection getConnectionOfOracle(){
		
		Connection con = null;
		String driver = GetProperties.getDriverClassName();
		String url = GetProperties.getURL();
		String user = GetProperties.getUSER();
		String pwd = GetProperties.getPWD();
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		System.out.println(getConnectionOfOracle());
	}


}
