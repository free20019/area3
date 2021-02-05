package com.ze.util;

import java.beans.SimpleBeanInfo;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tw.dao.PublicDao;
import com.tw.entity.Area;

public class test {
	public static void main(String[] args) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String time = "2016-09-01";
		Date d =null;
		try {
			d = dft.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long a = d.getTime()-1000*60*60*24;
		String zuotian = dft.format(a);
		System.out.println(zuotian);
	}
}
