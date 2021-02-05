package com.ze.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tw.entity.Vehicle;
import com.tw.entity.Vehijson;

import net.sf.json.JSONObject;

public class GetStatus {
	private GetGdzb gg = new GetGdzb();
	public List<Vehijson> getplace(){
	        List<String> li = new ArrayList<String>();
	        List<Vehijson> vehis = new ArrayList<Vehijson>();
	        String path=getpath();
	        File f = new File(path);
	        InputStream in = null;
	        try {
	            in = new FileInputStream(f);
	            int ab = in.available();
	            byte b[] = new byte[ab];
	            while (in.read(b, 0, ab) > -1) {
//	                System.out.println(new String(b));
	                li.add(new String(b));
	            }
	    		String data = li.get(0).replaceAll("}", "};");
	    		String[] jsa = data.split(";");
	    		for(int i=0;i<jsa.length-1;i++){
	    			JSONObject  obj = JSONObject.fromObject(jsa[i]);
	    			Vehijson vjson = (Vehijson) JSONObject.toBean(obj, Vehijson.class);
	    			vehis.add(vjson);
	    		}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Sort sort = new Sort();
	        sort.sort(vehis, "getMdt_no", "");  
	        return vehis;
	    }
	
	
	public Vehicle checkvehi(Vehicle vehi ,List<Vehijson> vehis){
		 if(vehi.getSimka()==null||vehis.size()==0){
	        	//System.out.println("没有车辆");
	        }else{
	        	 
	        	int startPos = 0; 
	            int endPos = vehis.size()-1;
	            int m = (startPos + endPos) / 2;
	            while(startPos <= endPos){
	            	//System.out.println(vehi.getSimka()+"    "+vehis.get(m));
	              if(vehi.getSimka().compareTo(vehis.get(m).getMdt_no())==0){ 
	            	    vehi.setDateTime(vehis.get(m).getDateTime());
	            	    vehi.setCarStatus(vehis.get(m).getCarStatus());
	            	    vehi.setHeading(vehis.get(m).getHeading());
	            	    vehi.setGpsStatus(vehis.get(m).getGpsStatus());
	            	    vehi.setLati(Double.parseDouble(vehis.get(m).getLati()));
						vehi.setLongi(Double.parseDouble(vehis.get(m).getLongti()));
						vehi.setSpeed(vehis.get(m).getSpeed());
						if(jisuan(vehis.get(m).getDateTime())){
							vehi.setOnoffstate("1");
							if(vehis.get(m).getCarStatus().equals("0")){
								vehi.setCarStatus("0");
							}else{
								vehi.setCarStatus("1");
							}
						}else{
							vehi.setOnoffstate("0");
						}
	            	  break;
	        	  }else if(vehi.getSimka().compareTo(vehis.get(m).getMdt_no())>0) {
	               startPos = m + 1;
	              }else if(vehi.getSimka().compareTo(vehis.get(m).getMdt_no())<0) {
	               endPos = m -1;
	              }
	              m = (startPos + endPos) / 2;
	            }
	           
	       }
		 return vehi;
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

	 
	 public List<Vehicle> checklist(List<Vehicle> vehilist ,List<Vehijson> vehis){
		 List<Vehicle> result = new ArrayList<Vehicle>();
		 
		         Map<String, Vehijson> map = new HashMap<String, Vehijson>();
		         for(int i=0;i<vehis.size();i++){
		        	 Vehijson person = vehis.get(i);
		              String id = person.getMdt_no();
		              map.put(id,person);
		         }
		         List<Vehicle> resultList = new ArrayList<Vehicle>();
		         for (Vehicle str : vehilist) {
		        	 Vehijson pList = map.get(str.getSimka());
		        	 if(pList!=null){
			        	 str.setDateTime(pList.getDateTime());
			        	 str.setCarStatus(pList.getCarStatus());
			        	 str.setHeading(pList.getHeading());
			        	 str.setGpsStatus(pList.getGpsStatus());
						 str.setSpeed(pList.getSpeed());
						 if(jisuan(pList.getDateTime())){
							str.setOnoffstate("1");
							if(pList.getCarStatus().equals("0")){
								str.setCarStatus("0");
							}else{
								str.setCarStatus("1");
							}
						 }else{
							str.setOnoffstate("0");
						 }
		        	 }
					 resultList.add(str);
		         }

		 return result;
	}
	 
	 public static String getpath(){
		 String filepath = "E:\\123\\";
		 File file = new File(filepath);
		 List<String> txts = new ArrayList<String>();
         if (!file.isDirectory()) {
                 System.out.println("文件");
                 System.out.println("path=" + file.getPath());
                 System.out.println("absolutepath=" + file.getAbsolutePath());
                 System.out.println("name=" + file.getName());

         } else if (file.isDirectory()) {
//                 System.out.println("文件夹");
                 String[] filelist = file.list();
                 for (int i = 0; i < filelist.length; i++) {
                         File readfile = new File(filepath + "\\" + filelist[i]);
                         if (!readfile.isDirectory()&&readfile.getName().indexOf("txt")>=0) {
                        	 
                        	 txts.add(readfile.getAbsolutePath());
                         } else if (readfile.isDirectory()) {
                                // readfile(filepath + "\\" + filelist[i]);
                         }
                 }
         }
         return txts.get(txts.size()-1);
	 }
}
