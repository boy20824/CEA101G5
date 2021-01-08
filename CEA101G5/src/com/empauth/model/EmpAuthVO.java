package com.empauth.model;
import java.sql.*;
public class EmpAuthVO implements java.io.Serializable{
	private  String emp_id;
	private  String auth_no;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getAuth_no() {
		return auth_no;
	}
	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}
	
}
