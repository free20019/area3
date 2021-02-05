package com.ze.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.ze.util.GetProperties;
import com.ze.util.Global;

public class GetProperties {

	private static Properties properties;
	
	
	static {	
		try{
		properties=new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("applicationConfig.properties");	
		properties.load(input);
		input.close();
		}catch(FileNotFoundException ex)
		   {ex.printStackTrace();}
		catch(IOException ex)
		   {ex.printStackTrace();}
	}
	
	public static String getProperty(String key)
	{	
		String prop=properties.getProperty(key);
		if(prop!=null&&prop!="")
			return parse(prop,"utf-8");
		else return null;
	}
	
	@Deprecated
	public static String parseToGBK(String str)
	{
		try { 
        	str=new String(str.getBytes("ISO-8859-1"), "GBK"); 
        	} catch (UnsupportedEncodingException e) { 
        		e.printStackTrace();
        	} catch (Exception e) { 
        		e.printStackTrace();
        	} 
        	return str;
	}
	
	public static String parse(String str,String encode)
	{
		try { 
        	str=new String(str.getBytes("ISO-8859-1"), encode); 
        	} catch (UnsupportedEncodingException e) { 
        		e.printStackTrace();
        	} catch (Exception e) { 
        		e.printStackTrace();
        	} 
        	return str;
	}
	
	public static boolean containKey(String key)
	{
		return properties.containsKey(key);
	}
	
	public static String getVIRTUAL_NAME(){
		String VIRTUAL_NAME="";
		if(GetProperties.containKey("VIRTUAL_NAME"))
     	  {
			VIRTUAL_NAME=GetProperties.getProperty("VIRTUAL_NAME");
     	  } 
		
		return VIRTUAL_NAME;
	}
	
	public static String getDriverClassName(){
		String DriverClassName="";
		if(GetProperties.containKey("jdbc.driverClassName"))
     	  {
			DriverClassName=GetProperties.getProperty("jdbc.driverClassName");
     	  } 
		
		return DriverClassName;
	}
	
	public static String getURL(){
		String URL="";
		if(GetProperties.containKey("jdbc.url"))
     	  {
			URL=GetProperties.getProperty("jdbc.url");
     	  } 
		
		return URL;
	}
	
	public static String getUSER(){
		String USER="";
		if(GetProperties.containKey("jdbc.username"))
     	  {
			USER=GetProperties.getProperty("jdbc.username");
			USER=Global.DenCrypt(USER);
     	  } 
		
		return USER;
	}
	
	public static String getPWD(){
		String PWD="";
		if(GetProperties.containKey("jdbc.password"))
     	  {
			PWD=GetProperties.getProperty("jdbc.password");
			PWD=Global.DenCrypt(PWD);
     	  } 
		
		return PWD;
	}
	
}
