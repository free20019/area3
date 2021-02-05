package com.tw.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tw.entity.TbVehicle;
import com.tw.entity.TbVehicleGps;
import com.ze.util.DataBese;

public class StatReportDao {
	public List<TbVehicle> findbycompid(String data,String keyword) {
		List<TbVehicle> results = new ArrayList<TbVehicle>();
		if(keyword==null){
			keyword="";
		}
		String [] ids = data.split(",");
		String comps = "";
		if (ids.length==1&&!ids[0].equals("所有公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"')";
		}else if (ids.length==2&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"' and t.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&ids[1].equals("所有分公司")) {
			comps +="and ( t.ba_name='"+ids[0]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&ids[2].equals("所有车辆")) {
			comps +="and ( t.ba_name='"+ids[0]+"' and t.comp_name='"+ids[1]+"')";
		}else if (ids.length==3&&!ids[0].equals("所有公司")&&!ids[1].equals("所有分公司")&&!ids[2].equals("所有车辆")) {
			comps +="and ( t.ba_name='"+ids[0]+"' and t.comp_name='"+ids[1]+"' and t.vehi_no='"+ids[2]+"')";
		}
		String sql = "select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where vehi_no like '%浙AT%'and  vehi_no like '%"+keyword+"%'"+comps;
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count=0;
			while(rs.next()){
				TbVehicle vehi = new TbVehicle();
				vehi.setId(count+"");
				vehi.setVehinum(rs.getString("VEHI_NO"));
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

	public TbVehicle findlc(String vehinum, String stime, String etime,String ba_name,String comp_name) {
		String riqi=stime.substring(0, 4);
		TbVehicle tb=new TbVehicle();
		String sql="select distinct  * from HZGPS_CITIZEN.TB_CITIZEN_"+riqi+"@TAXILINK45 t," +
				"HZGPS_TAXI.VW_VEHICLE@TAXILINK v where '浙'||t.vhic=v.vehi_no " +
				" and shangche >=to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss')" +
				" and shangche <=to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss')";
		if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("选择公司")) {
			sql+=" and ba_id='"+ba_name+"'";
		}
		if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("选择分公司")) {
			sql+=" and comp_id='"+comp_name+"'";
		}
		if (vehinum!=null&&vehinum.length()>0&&!vehinum.equals("选择车辆")) {
			sql+=" and vehi_no='"+vehinum+"'";
		}
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int count=0;
			String vhic="";
			while(rs.next()){
				vhic=rs.getString("vhic");
				count+=Integer.parseInt(rs.getString("jicheng"))+Integer.parseInt(rs.getString("kongshi"));
			}
			tb.setVehinum(vehinum);
			tb.setJicheng(count/10+"");
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tb;
	}
	public List<TbVehicleGps>findgps(String vehinum, String stime, String etime,String speed,String ba_name,String comp_name){
		String riqi=stime.substring(2, 4)+stime.substring(5, 7);
		List<TbVehicleGps>list=new ArrayList<TbVehicleGps>();
		String sql="select  * from HZGPS_TAXI.TB_GPS_"+riqi+"@TAXILINK t," +
				"HZGPS_TAXI.VW_VEHICLE@TAXILINK v where  t.vehicle_num=v.vehi_no " +
				" and speed_time >=to_date('"+stime+"','yyyy-mm-dd hh24:mi:ss') and" +
				" speed_time <=to_date('"+etime+"','yyyy-mm-dd hh24:mi:ss') ";
		if (speed!=null&&speed.length()>0) {
			sql+=" and speed >"+speed;
		}
		if (ba_name!=null&&ba_name.length()>0&&!ba_name.equals("0")) {
			sql+=" and ba_id='"+ba_name+"'";
		}
		if (comp_name!=null&&comp_name.length()>0&&!comp_name.equals("0")) {
			sql+=" and comp_id='"+comp_name+"'";
		}
		if (vehinum!=null&&vehinum.length()>0&&!vehinum.equals("0")) {
			sql+=" and vehi_no='"+vehinum+"'";
		}
		try {
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			Connection con=DataBese.getConnectionOfOracle();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TbVehicleGps t=new TbVehicleGps();
				t.setVehinum(rs.getString("vehicle_num"));
				t.setLati(rs.getString("py"));
				t.setLongi(rs.getString("px"));
				t.setSpeed(rs.getString("speed"));
				t.setSpeedtime(rs.getString("speed_time").substring(0, 19));
				t.setAddress(getplace(rs.getString("py"), rs.getString("px")));
				int fx=rs.getInt("direction");
				String fx1=null;
				if(fx==0||fx==360){
					fx1 ="正北";
				}else if(fx==90){
					fx1 ="正东";
				}else if(fx==180){
					fx1 ="正南";
				}else if(fx==270){
					fx1 ="正西";
				}else if(fx>0&&fx<90){
					fx1 ="东北";
				}else if(fx>90&&fx<180){
					fx1 ="东南";
				}else if(fx>180&&fx<270){
					fx1 ="西南";
				}else if(fx>270&&fx<360){
					fx1 ="西北";
				}
				t.setDirection1(fx1);
				list.add(t);
			}
			rs.close();
			pstmt.close();
			con.commit();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
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
	
}
