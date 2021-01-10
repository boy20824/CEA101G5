package com.acceptreserve.model;
import java.sql.*;

public class AcceptReserveVO implements java.io.Serializable{
	public Integer periodId;
	public String storeId;
	public Timestamp startTime;
	public Timestamp endTime;
	public Integer periodStatus;
	
	public AcceptReserveVO() {
		
	}
	
	public Integer getPeriodId() {
		return periodId;
	}
	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Integer getPeriodStatus() {
		return periodStatus;
	}
	public void setPeriodStatus(Integer periodStatus) {
		this.periodStatus = periodStatus;
	}
	
}
