package com.tw.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
public static void main(String[] args) {
//	int []arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//	int [] p ={1,2,3,4,5,6,7,8,9,34};
//	int []s =new int[13];
//	for(int i=0;i<arr.length;i++){
//		boolean flag = true;
//		for(int j=0;j<p.length;j++){
//			if(arr[i] == p[j]){
//				flag = false;
//			}
//		}
//		if(flag ){
//			s[0]=arr[i];
//			System.out.println(s[0]);
//		}
//	}

	List<Object> params = new ArrayList<Object>();
	params.add("1");
	params.add("2");
	params.add("3");
	System.out.println(params.toString().substring(1,params.toString().length()-1));
}
}
