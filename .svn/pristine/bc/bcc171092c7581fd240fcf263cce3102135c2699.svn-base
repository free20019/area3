package com.ze.util;

import java.util.Comparator;

import com.tw.entity.Vhic;
//对list进行排序
public class VhicComparator implements Comparator<Vhic>{
	public int compare(Vhic o1, Vhic o2) {
	  if(o1.getA()>o2.getA()){
	   return -1;
	  }
	  else if(o1.getBa_name().equals(o2.getBa_name())){
		   if(Integer.parseInt(o1.getBusy_taxi_chuche_rate())>Integer.parseInt(o2.getBusy_taxi_chuche_rate()))
		    return -1;
		   else
		    return 1;
	 } else
		   return 1;
	 }

}
