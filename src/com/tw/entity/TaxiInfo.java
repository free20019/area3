package com.tw.entity;

public class TaxiInfo {
	private String comp_name;
	private String vehi_no;
	private String begintime;
	private String endtime;
	private String px;
	private String py;
	private String address;
	private String operation_num;
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String compName) {
		comp_name = compName;
	}
	public String getVehi_no() {
		return vehi_no;
	}
	public void setVehi_no(String vehiNo) {
		vehi_no = vehiNo;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOperation_num() {
		return operation_num;
	}
	public void setOperation_num(String operationNum) {
		operation_num = operationNum;
	}
	
}
