package com.emp.model;

import java.sql.*;
public class EmpVO implements java.io.Serializable{
	private String emp_id;
	private String emp_name;
	private String emp_account;
	private String emp_password;
	private byte[] emp_image;
	private Date emp_date;
	private Integer emp_status;
	
	public byte[] getEmp_image() {
		return emp_image;
	}
	public void setEmp_image(byte[] emp_image) {
		this.emp_image = emp_image;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_account() {
		return emp_account;
	}
	public void setEmp_account(String emp_account) {
		this.emp_account = emp_account;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
//	public Blob getEmp_image() {
//		return emp_image;
//	}
//	public void setEmp_image(Blob emp_image) {
//		this.emp_image = emp_image;
//	}
	public Date getEmp_date() {
		return emp_date;
	}
	public void setEmp_date(Date emp_date) {
		this.emp_date = emp_date;
	}
	public Integer getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}
	
}
