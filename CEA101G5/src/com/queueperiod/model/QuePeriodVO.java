package com.queueperiod.model;

import java.sql.*;

public class QuePeriodVO implements java.io.Serializable{
	private Integer queueperiodid;
	private	String storeid;
	private Integer queuest;
	private Timestamp queuestarttime;
	private Timestamp queueendtime;
	private Integer queuenocurrent;
	
	public QuePeriodVO() {
		
	}
	public QuePeriodVO(Integer queueperiodid,String storeid,Integer queuest, Timestamp queuestarttime, Timestamp queueendtime, Integer queuenocurrent) {
		this.queueperiodid = queueperiodid;
		this.storeid = storeid;
		this.queuest = queuest;
		this.queuestarttime = queuestarttime;
		this.queueendtime = queueendtime;
		this.queuenocurrent = queuenocurrent;
	}
	

	public Timestamp getQueuestarttime() {
		return queuestarttime;
	}
	public void setQueuestarttime(Timestamp queuestarttime) {
		this.queuestarttime = queuestarttime;
	}
	public Timestamp getQueueendtime() {
		return queueendtime;
	}
	public void setQueueendtime(Timestamp queueendtime) {
		this.queueendtime = queueendtime;
	}

	public Integer getQueueperiodid() {
		return queueperiodid;
	}

	public void setQueueperiodid(Integer queueperiodid) {
		this.queueperiodid = queueperiodid;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public Integer getQueuest() {
		return queuest;
	}

	public void setQueuest(Integer queuest) {
		this.queuest = queuest;
	}



	public Integer getQueuenocurrent() {
		return queuenocurrent;
	}

	public void setQueuenocurrent(Integer queuenocurrent) {
		this.queuenocurrent = queuenocurrent;
	}
	
	
	
}
