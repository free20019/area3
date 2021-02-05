package com.tw.action;

import java.io.File;

public class DelAllFile {
	public static boolean delAllFile(String logfile) {
		boolean flag = false;
		File file = new File(logfile);
		if (!file.exists()) {
		   return flag;
		}
		if (!file.isDirectory()) {
		   return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
		   if (logfile.endsWith(File.separator)) {
		    temp = new File(logfile + tempList[i]);
		   } else {
		    temp = new File(logfile + File.separator + tempList[i]);
		   }
		   if (temp.isFile()) {
		    temp.delete();
		   }
		   if (temp.isDirectory()) {
		    delAllFile(logfile + "/" + tempList[i]);
		    flag = true;
		   }
		}
		return flag;
		}
}
