package com.tw.action;

import java.io.InputStream;  

import org.apache.struts2.ServletActionContext;  
  
import com.opensymphony.xwork2.ActionSupport;  
  
@SuppressWarnings("serial")  
public class DownloadAction extends ActionSupport {  
      
    public InputStream getDownloadFile() throws Exception {  
        return ServletActionContext.getServletContext().getResourceAsStream("/upload/gs.xls");  
    }//getDownloadFile()方法返回的必须是InputStream。getResourceAsStream()方法可以通过流的方式将资源输出  
  
    @Override  
    public String execute() throws Exception {  
        return SUCCESS;  
    }  
} 