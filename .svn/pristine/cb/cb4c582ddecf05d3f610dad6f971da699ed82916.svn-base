package com.ze.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;




import oracle.sql.BLOB;



public class oracleBolb {
	
	public String getId(Connection con,String tbfilename){//查询存储过程里的id
	       String id="";
	       try {
		   String procedure = "{call PRC_GET_"+tbfilename+"_ID(?) }";
		   CallableStatement cstmt;
		   cstmt = con.prepareCall(procedure);
		   cstmt.registerOutParameter(1, Types.VARCHAR);
		   cstmt.execute();
		   id = cstmt.getString(1);
		   
	      } catch (SQLException e) {
		      e.printStackTrace();	
	      }
	      return id;
	}
	
	
	public boolean insert(Connection con,String TYPE,String DEVICE_CODE,String CMENU_NAME,String EXTNAME,String tbfilename,String OBJ_CODE,File FILES){//上传文件
		try {   					  
			BLOB blob = null;   
			byte[] PIC;
			String id="ID";
			String sql ="insert into "+tbfilename+"("+id+",TYPE,DEVICE_CODE,CMENU_NAME,EXTNAME,OBJ_CODE,FILES) values(?,?,?,?,?,?,empty_blob())";
			PreparedStatement pstmt = con.prepareStatement(sql);   
			String PK_ID=this.getId(con, tbfilename);
			pstmt.setString(1,PK_ID);
			pstmt.setString(2,TYPE);
			pstmt.setString(3,DEVICE_CODE);  
			pstmt.setString(4,CMENU_NAME);  
			pstmt.setString(5,EXTNAME);
			pstmt.setString(6,OBJ_CODE);
			pstmt.executeUpdate();   
			pstmt.close();   
			  
			
			pstmt = con.prepareStatement("select FILES from "+tbfilename+" where "+id+"= ? for update");   
			pstmt.setString(1,PK_ID);   
			ResultSet rset = pstmt.executeQuery();   
			if (rset.next()) blob = (BLOB) rset.getBlob(1);   
			
			OutputStream out = blob.getBinaryOutputStream(); 
			 /*InputStream fin = new FileInputStream(PH_PIC); 
			 byte[] b = new byte[blob.getBufferSize()]; 
		        int len = 0; 
		        while ( (len = fin.read(b)) != -1) { 
		        	out.write(b, 0, len); 
		        } */
			PIC=this.getBytesFromFile(FILES);
			out.write(PIC);	
			
			pstmt.close();	
			out.close();   
			con.commit();
			//con.close();
			return true;
		} catch (SQLException e) {   
			System.err.println(e.getMessage());   
			e.printStackTrace();   
			return false;
		} catch (IOException e) {   
			System.err.println(e.getMessage());   
			return false;
		}  
	}
	
	
	public boolean update(Connection con,File FILES ,String tbfilename,String PK_ID,String EXTNAME,String id,String table){
		try {   					  
			BLOB blob = null;   
			byte[] PIC;
			//String id=this.getId(con, tbfilename); 
			//String id="PK_"+tbfilename.substring(3, tbfilename.length())+"_ID";
			
			String sql = "update "+tbfilename+" set EXTNAME = '"+EXTNAME+"' where "+id+"= '"+PK_ID+"' and cmenu_name='"+table+"'";
			  Statement pstmt1 = con.createStatement();
			  pstmt1.executeUpdate(sql);
 			
				
			PreparedStatement pstmt = con.prepareStatement("select FILES,EXTNAME from "+tbfilename+" where "+id+"= ? and cmenu_name=? for update");   
			pstmt.setString(1,PK_ID);  
			pstmt.setString(2,table);  
			
			ResultSet rset = pstmt.executeQuery();   
			if (rset.next()) blob = (BLOB) rset.getBlob(1);   
			
			OutputStream out = blob.getBinaryOutputStream();   	
			PIC=this.getBytesFromFile(FILES);
			out.write(PIC);		
			
			pstmt1.close();
			pstmt.close();
			rset.close();
			out.close();   
			//con.close();
			return true;
		} catch (SQLException e) {   
			System.err.println(e.getMessage());   
			e.printStackTrace();   
			return false;
		} catch (IOException e) {   
			System.err.println(e.getMessage());   
			return false;
		}  
	}
	
	public boolean select(Blob PH_PIC,String path){//读取文件
		try {
			InputStream is = PH_PIC.getBinaryStream();
			FileOutputStream fos = new FileOutputStream(path);// 读取到e:\\ss.doc
			byte[] buf1 = new byte[10240];
			int len1;
			while ((len1 = is.read(buf1)) != -1) {
				fos.write(buf1, 0, len1);
			}
			
			fos.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public byte[] getBytesFromFile(File f) {//File 转换�? byte
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
	
	public ResultSet getResultSet(Connection con,String sql) {//
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//rs.close();
			//pstmt.close();
			//con.commit();
			//con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return rs;
    }
}