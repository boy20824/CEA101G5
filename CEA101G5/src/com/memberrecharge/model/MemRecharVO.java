package com.memberrecharge.model;

import java.sql.*;

public class MemRecharVO implements java.io.Serializable {
	private static final long serialVersionUID = 2L;

	private String memPhone;
	private String rechargeId;
	private Timestamp rechargeDate;
	private Integer rechargeChange;

	
	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}

	public Timestamp getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(Timestamp rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	public Integer getRechargeChange() {
		return rechargeChange;
	}

	public void setRechargeChange(Integer rechargeChange) {
		this.rechargeChange = rechargeChange;
	}

}
