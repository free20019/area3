//package com.ze.util;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.tw.entity.Tb_Alert_GPS;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
////import com.ze.entity.TbGps;
//
//public class DataUtil {
//	//根据经纬度转换为地址
//	public String getplace(String lati, String longti){
//		String path="http://gc.ditu.aliyun.com/regeocoding?l="+lati+","+longti+"&type=100";  
//		String place="";
//        //参数直接加载url后面  
//		try {
//			URL url= new URL(path);
//		
//        HttpURLConnection conn=(HttpURLConnection) url.openConnection();  
//        conn.setRequestMethod("GET");  
//        conn.setConnectTimeout(5000);  
//        StringBuffer buffer = new StringBuffer();  
//        if(conn.getResponseCode()==200){                //200表示请求成功  
//            InputStream is=conn.getInputStream();    //以输入流的形式返回  
//            InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");  
//            //将输入流转换成字符串  
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
//      	  
//            String str = null;  
//            while ((str = bufferedReader.readLine()) != null) {  
//                buffer.append(str);  
//            }  
//            bufferedReader.close();  
//            inputStreamReader.close();  
//            // 释放资源  
//            is.close();  
//            is = null;  
//            conn.disconnect();  
//            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());  
//            //得出整个坐标反馈信息  
////            System.out.println(buffer.toString());
//              
//            //转换成json数据处理  
//            //{"queryLocation":[39.938133,116.395739],"addrList":[{"type":"doorPlate","status":1,"name":"地安门外大街万年胡同1号","admCode":"110102","admName":"北京市,北京市,西城区,","addr":"","nearestPoint":[116.39546,39.93850],"distance":45.804}]}  
//              
//            String addrList =  jsonObject.getString("addrList");  
////            System.out.println(addrList+" 1111111");  //地址信息  
//              
//            JSONArray jsonarry = JSONArray.fromObject(addrList);  
//            for(int i = 0;i<jsonarry.size();i++){  
//                JSONObject jsonObject2 = jsonarry.getJSONObject(i);  
//                String roadName =  jsonObject2.getString("name"); //路名（这才是我最终想要的）
//                String quName= jsonObject2.getString("admName");
//                String qu[]=quName.split(",");
//                String name1="";
//                if (qu.length>1) {
//                	 name1=qu[qu.length-2]+qu[qu.length-1];
//				}else {
//					name1=qu[qu.length-1];
//				}
//                String name2=name1+roadName;
//                place = name2;  
////                System.out.println(place);
//            }  
//        }  
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
//		return place;
//	}
//	//时间过滤
//	public List<Tb_Alert_GPS> getlist(String stime,String etime,List<Tb_Alert_GPS>gpss){
//	List<Tb_Alert_GPS> gpslist = new ArrayList<Tb_Alert_GPS>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date = stime;
//		String date2=etime;
//		int a = Integer.parseInt(date.substring(11, 13));
//		int b = Integer.parseInt(date.substring(14, 16));
//		int i=0;
//		for(i=0;i<60;i+=10){
//			if((i-b)>=0&&b<=49){
//				break;
//			}else if(b>49){
//				i=0;
//				a=a+1;
//				break;
//			}
//		}
//		String aa = "";
//		String ai = "";
//		if(a<10){
//			aa = "0"+a;
//		}else{
//			aa=a+"";
//		}
//		if(i<10){
//			ai = "0"+i;
//		}else{
//			ai=i+"";
//		}
//		String timelist = "";
//		if(i==0){
//			timelist = date.substring(0, 11)+aa+":00:00";
//		}else{
//			timelist = date.substring(0, 14)+ai+":00";
//		}
////		System.out.println(timelist);
//		
//		long s = 0;
//		long e = 0;
//		try {
//			s = sdf.parse(timelist).getTime();
//			e = sdf.parse(date2).getTime();
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		long c = e-s;
////		System.out.println(c/600000+"   c");
//		int n=0;
//		int ng=0;
//		int temp=0;
//		for(int m=0;m<c/5400000;m++){
//			if(m!=0){
//				s = s+5400000;
//			}
////			System.out.println(sdf.format(new Date(s)));
//			for(n=ng;n<gpss.size();n++){
//				try {
//					if(sdf.parse(gpss.get(n).getSpeed_time()).getTime()-s>=0&&sdf.parse(gpss.get(n).getSpeed_time()).getTime()-s<=600000){
//						gpslist.add(gpss.get(n));
//						temp=n;
//						break;
//					}
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//			}
//			ng=temp;
//		}
//		/*for(int m=0;m<gpslist.size();m++){
//			System.out.println(gpslist.get(m).getSpeed_time()+"   "+gpslist.get(m).getLati()+","+gpslist.get(m).getLongti());
//		}*/
//		return gpslist;
//	}
//
//	public List<List<Tb_Alert_GPS>> chgpslist(List<Tb_Alert_GPS> a) {
////		System.out.println(a.size()+"    a.size()");
//		List<List<Tb_Alert_GPS>> list = new ArrayList<List<Tb_Alert_GPS>>(); 
//		int b=a.size();
//		int c=a.size()-1;
//		while(c<b){
//			b=c;
//			for(int i=0;i<a.size()-2;i++){
//				if(i!=0&&((120.141000<Double.parseDouble(a.get(i+1).getLongti())&&Double.parseDouble(a.get(i+1).getLongti())<120.141500)&&(30.266000<Double.parseDouble(a.get(i+1).getLati())&&Double.parseDouble(a.get(i+1).getLati())<30.267500))&&(120.141000<Double.parseDouble(a.get(i).getLongti())&&Double.parseDouble(a.get(i).getLongti())<120.141500&&30.266000<Double.parseDouble(a.get(i).getLati())&&Double.parseDouble(a.get(i).getLati())<30.267500)&&(120.141000<Double.parseDouble(a.get(i+2).getLongti())&&Double.parseDouble(a.get(i+2).getLongti())<120.141500&&30.266000<Double.parseDouble(a.get(i+2).getLati())&&Double.parseDouble(a.get(i+2).getLati())<30.267500)){
////					System.out.println(a.get(i).getSpeed_time()+"  "+a.get(i+1).getLati()+"  "+a.get(i+1).getLongti()+"         asd1");
//					a.remove(i+1);
//				}
//				if(i==0&&((120.141000<Double.parseDouble(a.get(i+1).getLongti())&&Double.parseDouble(a.get(i+1).getLongti())<120.141500)&&(30.266000<Double.parseDouble(a.get(i+1).getLati())&&Double.parseDouble(a.get(i+1).getLati())<30.267500))&&(120.141000<Double.parseDouble(a.get(i).getLongti())&&Double.parseDouble(a.get(i).getLongti())<120.141500&&30.266000<Double.parseDouble(a.get(i).getLati())&&Double.parseDouble(a.get(i).getLati())<30.267500)){
////					System.out.println(a.get(i).getSpeed_time()+"  "+a.get(i).getLati()+"  "+a.get(i).getLongti()+"         asd2");
//					a.remove(i);
//				}
//			}
//			c=a.size();
//			
//		}
////		for(int i=0;i<a.size();i++){
////			System.out.println(a.get(i).getSpeed_time()+"  "+a.get(i).getLati()+" "+a.get(i).getLongti());
////		}
////		System.out.println(a.size());
//		List<Integer> m = new ArrayList<Integer>();
//		for(int i=0;i<a.size();i++){
//			if((120.141000<Double.parseDouble(a.get(i).getLongti())&&Double.parseDouble(a.get(i).getLongti())<120.141500&&30.266000<Double.parseDouble(a.get(i).getLati())&&Double.parseDouble(a.get(i).getLati())<30.267500)){
////				System.out.println("i="+i);	
//				m.add(i);
//			}
//		}
//		for(int i=0;i<m.size()-1;i=i+2){
////			System.out.println(m.get(i)+"  "+m.get(i+1));
//			List<Tb_Alert_GPS> a1 = a.subList(m.get(i),m.get(i+1)+1);
//			list.add(a1);
//		}
////		for(int i=0;i<list.get(0).size();i++){
////			System.out.println(list.get(0).get(i).getSpeed_time()+"  "+list.get(0).get(i).getLati()+" "+list.get(0).get(i).getLongti());
////		}
//		return list;
//	}
//	
//	public String chgpslist1(String stime,String etime,List<Tb_Alert_GPS> agpss) {
//		String asdString="";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		long nd = 1000*24*60*60;
//		long day=0;
//		try {
//			long diff = sdf.parse(etime).getTime() - sdf.parse(stime).getTime();
//			day=diff/nd;
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		int d = (int) day;
//		int a = Integer.parseInt(stime.substring(8, 10));
//		List<Tb_Alert_GPS> gpss = new ArrayList<Tb_Alert_GPS>();
//		if(agpss.size()!=0){
//			gpss = gpsguolv(agpss);
//		}
//		for(int i=0;i<d+1;i++){
//			String aa = (a+i)+"";
//			if(a+i<10){
//				aa="0"+aa;
//			}
//			
//			for(int j=0;j<gpss.size();j++){
//				try {
//					if(sdf.parse(gpss.get(j).getSpeed_time()).getTime()<=sdf.parse(stime.substring(0, 8)+aa+" 23:59:59").getTime()&&sdf.parse(gpss.get(j).getSpeed_time()).getTime()>=sdf.parse(stime.substring(0, 8)+aa+" 00:00:00").getTime()){
//						gpss.get(j).setAddress(getplace(gpss.get(j).getLati(),gpss.get(j).getLongti()));
//						if (j==0) {
//							asdString +=gpss.get(j).getSpeed_time().substring(11, 19)+"("+gpss.get(j).getAddress()+")﹣";
//						}else if (j==1) {
//							asdString +=gpss.get(j).getSpeed_time().substring(11, 16)+"("+gpss.get(j).getAddress()+")";
//						}else if(j==gpss.size()-1){
//							asdString +="﹣"+gpss.get(j).getSpeed_time().substring(11, 19)+"("+gpss.get(j).getAddress()+")";
//						}else {
//							asdString +="﹣"+gpss.get(j).getSpeed_time().substring(11, 16)+"("+gpss.get(j).getAddress()+")";
//						}
//					}
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
////		for(int j=0;j<list.size();j++){
////			List<TbGps> b = list.get(j);
////			for(int i=0;i<b.size();i++){
////				
////			}
////		}
//		return asdString;
//	}
//public List<Tb_Alert_GPS> gpsguolv(List<Tb_Alert_GPS> gps){
//		
//		int index=0;
//		int indexmax=0;
//		for(int j=0;j<gps.size();j++){
//			if(gps.get(j).getSpeed()!=0){
//				index=j;
////				System.out.println(index+"  111  "+j);
//				break;
//			}
//		}
//		for(int j=0;j<gps.size();j++){
//			if(gps.get(j).getSpeed()!=0){
//				indexmax=j;
//			}
//		}
//		if(indexmax!=0){
////			if(index-1>=0&&indexmax+2<=gps.size()){
////				gps = gps.subList(index-1, indexmax+2);
////			}else if(index-1<0&&indexmax+2>gps.size()){
////				gps = gps.subList(index, indexmax+1);
////			}else if(index-1>=0&&indexmax+2>gps.size()){
////				gps = gps.subList(index-1, indexmax+1);
////			}else if(index-1<0&&indexmax+2<=gps.size()){
////				gps = gps.subList(index, indexmax+2);
////			}
////		System.out.println(gps.get(0).getSpeed_time() +" "+gps.get(gps.size()-1).getSpeed_time());
//		gps = getmin(gps.get(0).getSpeed_time(), gps.get(gps.size()-1).getSpeed_time(), gps);
//		int b=gps.size();
//		int c=gps.size()-1;
//		while(c<b){
//			b=c;
//			for(int j=0;j<gps.size()-1;j++){
//				if(gps.get(j).getSpeed_time().equals(gps.get(j+1).getSpeed_time())){
////					System.out.println(a.get(i).getSpeed_time()+"  "+a.get(i+1).getLati()+"  "+a.get(i+1).getLongti()+"         asd1");
//					gps.remove(j+1);
//				}
//			}
//			c=gps.size();
//		}
//		return gps;
//		}else{
//			List<Tb_Alert_GPS> gpslist = new ArrayList<Tb_Alert_GPS>();
//			gpslist.add(gps.get(0));
//			gpslist.add(gps.get(gps.size()-1));
////			System.out.println(gpslist+" @@@@");
//			return gpslist;
//		}
//	}
//
//	public List<Tb_Alert_GPS> getmin(String stime,String etime,List<Tb_Alert_GPS> gpss){
////		System.out.println(gpss.size()+" begin_size");
//		List<Tb_Alert_GPS> gpslist = new ArrayList<Tb_Alert_GPS>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////		for(int i=0;i<gpss.size();i++){
////			System.out.println(gpss.get(i).getSpeed()+"  "+gpss.get(i).getSpeed_time()+"  "+gpss.get(i).getLati()+"  "+gpss.get(i).getLongti());
////		}
//		long s = 0;
//		long e = 0;
//		try {
//			s = sdf.parse(stime).getTime();
//			e = sdf.parse(etime).getTime();
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		long c = e-s;
//		int n=0;
//		int ng=0;
//		int temp=0;
////		System.out.println(c/600000);
//		for(int m=0;m<c/1800000+1;m++){
//			if(m!=0){
//				s = s+1800000;
//			}
////			System.out.println(s);
//			for(n=ng;n<gpss.size();n++){
//				try {
//					if(sdf.parse(gpss.get(n).getSpeed_time()).getTime()-s>=0&&sdf.parse(gpss.get(n).getSpeed_time()).getTime()-s<=600000){
//						gpslist.add(gpss.get(n));
//						temp=n;
//						break;
//					}
//					
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//			}
//			ng=temp;
//		}
//		try {
//			if(sdf.parse(gpss.get(gpss.size()-1).getSpeed_time()).getTime()-s>=0){
//				gpslist.add(gpss.get(gpss.size()-1));
//			}
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
////		System.out.println(gpss.size()+" end_size");
//		return gpslist;
//	}
//}
