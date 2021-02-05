package com.tw.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tw.entity.Company;
import com.tw.entity.TbVehicleGps;
import com.tw.entity.Vehicle;
import com.ze.util.DataBese;

public class RealtimeDao {

	public List<Vehicle> findbykeyword(String keyword, String compid) {
		List<Vehicle> results = new ArrayList<Vehicle>();
		String sql="select * from (select t.* from HZGPS_TAXI.VW_VEHICLE@TAXILINK t ";
		String tj="";
		String [] s = compid.split(",");
		if("所有公司".equals(s[0])){
			tj=" where 1=1";
		}else{
			if("所有分公司".equals(s[1])){
				tj = " where t.ba_name='"+s[0]+"'";
			}else{
				if("所有车辆".equals(s[2])){
					tj = " where t.comp_name='"+s[1]+"'";
				}else{
					tj = " where t.vehi_no='"+s[2]+"'";
				}
			}
		}
		tj+=" and t.vehi_no like '%"+keyword+"%' and t.vehi_no like '%浙AT%' order by t.vehi_no)a left join TB_TAXI_STATUS_NEW s on a.sim_num=s.mdt_no";
		sql+=tj;
	//System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vehicle vehi = new Vehicle();
				vehi.setCompname(rs.getString("COMP_NAME"));
				vehi.setVehino(rs.getString("VEHI_NO"));
				vehi.setSimka(rs.getString("SIM_NUM"));
				vehi.setCartype(rs.getString("VT_NAME"));
				vehi.setVehisim(rs.getString("VEHI_SIM"));
				vehi.setMdtno(rs.getString("MT_NAME"));
				vehi.setOwnname(rs.getString("OWN_NAME"));
				vehi.setOwntel(rs.getString("OWN_TEL"));
				vehi.setColor(rs.getString("VC_NAME"));
				
				
				 vehi.setDateTime(rs.getString("STIME")==null?"":rs.getString("STIME").substring(0, 19));
         	    vehi.setCarStatus(rs.getString("STATUS"));
         	    vehi.setHeading(rs.getString("HEADING"));
         	    vehi.setGpsStatus(rs.getString("GPS_STATUS"));
         	    vehi.setLati(rs.getDouble("PY"));
					vehi.setLongi(rs.getDouble("PX"));
					vehi.setSpeed(rs.getString("SPEED"));
					if(vehi.getDateTime()!=""&&jisuan(vehi.getDateTime())){
						vehi.setOnoffstate("1");
					}else{
						vehi.setOnoffstate("0");
					}
				results.add(vehi);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	public static boolean jisuan(String date1){ 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        double result=0;
		try {
			Date start = sdf.parse(date1);
			Date end = new Date();
			long cha = end.getTime() - start.getTime(); 
			result = cha * 1.0 / (1000 * 60); 
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(result<=30){ 
			return true; 
		}else{ 
			return false; 
		} 
    } 
	public List<TbVehicleGps> findhistory(String vehiid, String stime,String etime) {
		List<TbVehicleGps> results = new ArrayList<TbVehicleGps>();
		String db = stime.substring(2, 4)+stime.substring(5, 7);
		int s=0;
		String sql = "select * from hzgps_taxi.tb_gps_"+db+"@TAXILINK t where t.carstate='0' and t.speed>="+s+" and t.vehicle_num='"+vehiid+"' and t.speed_time>=to_date('"+stime+"','yyyy-mm-dd hh24-mi-ss') and t.speed_time<=to_date('"+etime+"','yyyy-mm-dd hh24-mi-ss') order by t.speed_time";
//		System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count=0;
			while(rs.next()){
				count++;
				TbVehicleGps gps = new TbVehicleGps();
				gps.setId(count+"");
				gps.setMessageid(rs.getString("MESSAGE_ID"));
				gps.setVehiid(rs.getString("VEHICLE_ID"));
				gps.setVehinum(rs.getString("VEHICLE_NUM"));
//				gps.setLongi(((rs.getDouble("LONGI")+0.0045)+"").length()>10?((rs.getDouble("LONGI")+0.0045)+"").substring(0,10):((rs.getDouble("LONGI")+0.0045)+""));
//				gps.setLati(((rs.getDouble("LATI")-0.0025)+"").length()>9?((rs.getDouble("LATI")-0.0025)+"").substring(0,9):((rs.getDouble("LATI")-0.0025)+""));
				gps.setLongi(rs.getString("PX"));
				gps.setLati(rs.getString("PY"));
				gps.setSpeed(rs.getString("SPEED"));
				gps.setDirection(rs.getInt("DIRECTION"));
				//System.out.println(rs.getInt("DIRECTION"));
				//gps.setCarstate(rs.getString("CARSTATE"));
				if("0".equals(rs.getString("STATE"))||rs.getString("STATE")==null){
					gps.setCarstate("空车");
				}else{
					gps.setCarstate("重车");
				}
				gps.setSpeedtime(rs.getString("SPEED_TIME").substring(0, 19));
				gps.setDbtime(rs.getString("DB_TIME"));
				gps.setNote(rs.getString("NOTE"));
				gps.setAddress("");
//				gps.setAddress(getplace(rs.getDouble("PY")+"", rs.getDouble("PX")+""));
		//		gps.setAddress("");
				//System.out.println(rs.getDouble("PY")+"  "+ rs.getDouble("PX")+"   "+getplace(rs.getDouble("PY")+"", rs.getDouble("PX")+""));
				results.add(gps);
			}
			
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	public static void main(String[] args) {
		RealtimeDao r = new RealtimeDao();
		System.out.println(r.getplace("116.397428","39.90923"));
	}
	public String getplace(String lati, String longti){
		//String path="http://gc.ditu.aliyun.com/regeocoding?l="+lati+","+longti+"&type=100";  
		String path="http://192.168.0.105:9090/taximonitor/addresss.action?lati="+lati+"&longi="+longti; 
		String place="";
        //参数直接加载url后面
		try {
			URL url= new URL(path);
		
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();  
        conn.setRequestMethod("GET");  
        conn.setConnectTimeout(5000);  
        StringBuffer buffer = new StringBuffer();  
        if(conn.getResponseCode()==200){                //200表示请求成功  
            InputStream is=conn.getInputStream();    //以输入流的形式返回  
            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");  
            //将输入流转换成字符串  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
      	  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            is.close();  
            is = null;  
            conn.disconnect();  
//            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
            place = buffer.toString();
        }  
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return place;
	}

	public Vehicle findbyno(String string) {
		String sql="select * from (select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.VEHI_NO='"+string+"') tt left join TB_COMPANY_PHONE c on tt.COMP_NAME=c.COMPNAME)a left join TB_TAXI_STATUS_NEW s on a.sim_num=s.mdt_no";
		//	System.out.println(sql);
		Vehicle vehi = new Vehicle();
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				vehi.setCompname(rs.getString("COMP_NAME"));
				vehi.setVehino(rs.getString("VEHI_NO"));
				vehi.setSimka(rs.getString("SIM_NUM"));
				vehi.setCartype(rs.getString("VT_NAME"));
				vehi.setVehisim(rs.getString("VEHI_SIM"));
				vehi.setMdtno(rs.getString("MT_NAME"));
				vehi.setOwnname(rs.getString("OWN_NAME"));
				vehi.setOwntel(rs.getString("OWN_TEL"));
				vehi.setColor(rs.getString("VC_NAME"));
				vehi.setCompphone(rs.getString("COMPPHONE")==null?"":rs.getString("COMPPHONE"));
				
				vehi.setDateTime(rs.getString("STIME")==null?"":rs.getString("STIME").substring(0, 19));
         	    vehi.setCarStatus(rs.getString("STATUS"));
         	    vehi.setHeading(rs.getString("HEADING"));
         	    vehi.setGpsStatus(rs.getString("GPS_STATUS"));
         	    vehi.setLati(rs.getDouble("PY"));
				vehi.setLongi(rs.getDouble("PX"));
				vehi.setAddress(getplace(rs.getString("PY"),rs.getString("PX")));
				vehi.setSpeed(rs.getString("SPEED"));
				if(vehi.getDateTime()!=""&&jisuan(vehi.getDateTime())){
					vehi.setOnoffstate("1");
				}else{
					vehi.setOnoffstate("0");
				}
				vehi.setAddress(getplace(rs.getString("PY"), rs.getString("PX")));
					
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehi;
	}
	public List<Vehicle> findbynos(String string) {
		//String sql="select * from (select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.VEHI_NO='"+string+"') tt left join TB_COMPANY_PHONE c on tt.COMP_NAME=c.COMPNAME)a left join TB_TAXI_STATUS_NEW s on a.sim_num=s.mdt_no";
		String cars = string.substring(0, string.length()-1);
		String sql="select * from (select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.VEHI_NO in ("+cars+")) tt left join TB_COMPANY_PHONE c on tt.COMP_NAME=c.COMPNAME)a left join TB_TAXI_STATUS_NEW s on a.sim_num=s.mdt_no";
		//	System.out.println(sql);
		List<Vehicle> vehis = new ArrayList<Vehicle>();
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vehicle vehi = new Vehicle();
				
				vehi.setCompname(rs.getString("COMP_NAME"));
				vehi.setVehino(rs.getString("VEHI_NO"));
				vehi.setSimka(rs.getString("SIM_NUM"));
				vehi.setCartype(rs.getString("VT_NAME"));
				vehi.setVehisim(rs.getString("VEHI_SIM"));
				vehi.setMdtno(rs.getString("MT_NAME"));
				vehi.setOwnname(rs.getString("OWN_NAME"));
				vehi.setOwntel(rs.getString("OWN_TEL"));
				vehi.setColor(rs.getString("VC_NAME"));
				vehi.setCompphone(rs.getString("COMPPHONE")==null?"":rs.getString("COMPPHONE"));
				
				vehi.setDateTime(rs.getString("STIME")==null?"":rs.getString("STIME").substring(0, 19));
         	    vehi.setCarStatus(rs.getString("STATUS"));
         	    vehi.setHeading(rs.getString("HEADING"));
         	    vehi.setGpsStatus(rs.getString("GPS_STATUS"));
         	    vehi.setLati(rs.getDouble("PY"));
					vehi.setLongi(rs.getDouble("PX"));
					vehi.setAddress(getplace(rs.getString("PY"),rs.getString("PX")));
					vehi.setSpeed(rs.getString("SPEED"));
					if(vehi.getDateTime()!=""&&jisuan(vehi.getDateTime())){
						vehi.setOnoffstate("1");
					}else{
						vehi.setOnoffstate("0");
					}
					vehis.add(vehi);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehis;
	}
	public List<Company> findbaname(String compid) {
		List<Company> results = new ArrayList<Company>();
		String sql="";
		String [] s = compid.split(",");
		if("所有公司".equals(s[0])){
			sql="select distinct ba_name,ba_id from HZGPS_TAXI.VW_VEHICLE@TAXILINK t";
//			sql="select * from TB_BUSI_AREA t";
		}else{
			sql="select distinct ba_name,ba_id from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.ba_name='"+s[0]+"'";
		}
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company c  = new Company();
				c.setId(rs.getString("BA_ID"));
				c.setName(rs.getString("BA_NAME"));
				results.add(c);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public List<Company> findgroup(String compid) {
		List<Company> results = new ArrayList<Company>();
		String sql="select * from TB_TAXI_VEHICLE_GROUPNAME t";
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company c  = new Company();
				c.setId(rs.getString("GROUP_ID"));
				c.setName(rs.getString("GROUP_NAME"));
				results.add(c);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public List<Company> findcompname(String compid, String baid) {
		List<Company> results = new ArrayList<Company>();
		String sql="";
		String [] s = compid.split(",");
		if("所有分公司".equals(s[1])){
			sql="select distinct comp_name,comp_id from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.ba_id='"+baid+"'";
		}else{
			sql="select distinct comp_name,comp_id from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.comp_name='"+s[1]+"'";
		}
//	System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company c  = new Company();
				c.setId(rs.getString("COMP_ID"));
				c.setName(rs.getString("COMP_NAME"));
				results.add(c);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public List<Company> findcompname1(String compid) {
		List<Company> results = new ArrayList<Company>();
		String sql="";
		String [] s = compid.split(",");
		sql="select t.* from HZGPS_TAXI.TB_COMPANY@TAXILINK t";
//	System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company c  = new Company();
				c.setId(rs.getString("COMP_ID"));
				c.setBaid(rs.getString("BA_ID"));
				c.setName(rs.getString("COMP_NAME"));
				results.add(c);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public List<Vehicle> findgjcl(String compid, String keyword) {
		List<Vehicle> results = new ArrayList<Vehicle>();
		String sql="";
		String [] s = compid.split(",");
		if("所有车辆".equals(s[2])){
			sql="select t.* from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.COMP_ID='"+keyword+"' and t.vehi_no like '%浙AT%'";
		}else{
			sql="select t.* from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where  t.vehi_no = '"+s[2]+"' and t.vehi_no like '%浙AT%'";
		}
//	System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vehicle vehi = new Vehicle();
				vehi.setCompname(rs.getString("COMP_NAME"));
				vehi.setVehino(rs.getString("VEHI_NO"));
				vehi.setSimka(rs.getString("SIM_NUM"));
				vehi.setCartype(rs.getString("VT_NAME"));
				vehi.setVehisim(rs.getString("VEHI_SIM"));
				vehi.setMdtno(rs.getString("MT_NAME"));
				vehi.setOwnname(rs.getString("OWN_NAME"));
				vehi.setOwntel(rs.getString("OWN_TEL"));
				vehi.setColor(rs.getString("VC_NAME"));
				results.add(vehi);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	public List<Vehicle> findgscl(String compid) {
		List<Vehicle> results = new ArrayList<Vehicle>();
		String sql="select t.* from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.ba_ID='"+compid+"' and t.vehi_no like '%浙AT%'";
	System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vehicle vehi = new Vehicle();
				vehi.setCompname(rs.getString("COMP_NAME"));
				vehi.setVehino(rs.getString("VEHI_NO"));
				vehi.setSimka(rs.getString("SIM_NUM"));
				vehi.setCartype(rs.getString("VT_NAME"));
				vehi.setVehisim(rs.getString("VEHI_SIM"));
				vehi.setMdtno(rs.getString("MT_NAME"));
				vehi.setOwnname(rs.getString("OWN_NAME"));
				vehi.setOwntel(rs.getString("OWN_TEL"));
				vehi.setColor(rs.getString("VC_NAME"));
				results.add(vehi);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public List<Vehicle> findgjcl1(String compid, String keyword,String k) {
		List<Vehicle> results = new ArrayList<Vehicle>();
		String sql="";
		String [] s = compid.split(",");
		if("所有车辆".equals(s[2])){
			sql="select t.* from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.COMP_ID='"+keyword+"' and t.vehi_no like '%浙AT%' and t.vehi_no like '%"+k+"%'";
		}else{
			sql="select t.* from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where  t.vehi_no = '"+s[2]+"' and t.vehi_no like '%浙AT%' and t.vehi_no like '%"+k+"%'";
		}
//	System.out.println(sql);
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Vehicle vehi = new Vehicle();
				vehi.setCompname(rs.getString("COMP_NAME"));
				vehi.setVehino(rs.getString("VEHI_NO"));
				vehi.setSimka(rs.getString("SIM_NUM"));
				vehi.setCartype(rs.getString("VT_NAME"));
				vehi.setVehisim(rs.getString("VEHI_SIM"));
				vehi.setMdtno(rs.getString("MT_NAME"));
				vehi.setOwnname(rs.getString("OWN_NAME"));
				vehi.setOwntel(rs.getString("OWN_TEL"));
				vehi.setColor(rs.getString("VC_NAME"));
				results.add(vehi);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

public List<Vehicle> findgroupcar(String compid, String keyword,String k) {
	List<Vehicle> results = new ArrayList<Vehicle>();
	String sql="select * from TB_TAXI_VEHICLE_GROUP t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.GROUP_ID='"+keyword+"' and t.group_vhic=v.vehi_no";
//System.out.println(sql);
	try {
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Connection con=DataBese.getConnectionOfOracle();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			Vehicle vehi = new Vehicle();
			vehi.setCompname(rs.getString("COMP_NAME"));
			vehi.setVehino(rs.getString("VEHI_NO"));
			vehi.setSimka(rs.getString("SIM_NUM"));
			vehi.setCartype(rs.getString("VT_NAME"));
			vehi.setVehisim(rs.getString("VEHI_SIM"));
			vehi.setMdtno(rs.getString("MT_NAME"));
			vehi.setOwnname(rs.getString("OWN_NAME"));
			vehi.setOwntel(rs.getString("OWN_TEL"));
			vehi.setColor(rs.getString("VC_NAME"));
			results.add(vehi);
		}
		rs.close();
		pstmt.close();
		con.commit();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return results;
}

public List<Vehicle> findstopcars() {
	List<Vehicle> results = new ArrayList<Vehicle>();
	String sql="select * from TB_STOPVEHI t where t.speed_time<=sysdate-1/24/60*30";
//System.out.println(sql);
	try {
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Connection con=DataBese.getConnectionOfOracle();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			Vehicle vehi = new Vehicle();
			vehi.setVehino(rs.getString("VEHI_NO"));
			vehi.setLongi(rs.getDouble("PX"));
			vehi.setLati(rs.getDouble("PY"));
			vehi.setSpeed(rs.getString("SPEED"));
			vehi.setSimka(rs.getString("SIM_NUM"));
			vehi.setCompname(rs.getString("COMP_NAME"));
			vehi.setDateTime(rs.getString("SPEED_TIME")==null?"":rs.getString("SPEED_TIME").substring(0, 19));
			//vehi.setAddress(getplace(vehi.getLati(), vehi.getLongi()));
			
			if(vehi.getDateTime()!=""&&jisuan(vehi.getDateTime())){
				vehi.setOnoffstate("1");
			}else{
				vehi.setOnoffstate("0");
			}
			results.add(vehi);
		}
		rs.close();
		pstmt.close();
		con.commit();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return results;
}
}