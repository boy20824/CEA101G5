package com.reserveorder.model;
import java.sql.*;

public class ReserveOrderVO implements java.io.Serializable{
	public String reserveId;
	public String storeId;
	public String memPhone;
	public Integer periodId;
	public Date reserveTime;
	public Integer reserveAdult;
	public Integer reserveChild;
	public Integer reserveStatus;
	public String reserveNote;
	public Timestamp reserveCreateTime;
	
	public ReserveOrderVO() {
		
	}
	
	public String getReserveId() {
		return reserveId;
	}
	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public Integer getPeriodId() {
		return periodId;
	}
	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}
	
	public Date getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}
	public Integer getReserveAdult() {
		return reserveAdult;
	}
	public void setReserveAdult(Integer reserveAdult) {
		this.reserveAdult = reserveAdult;
	}
	public Integer getReserveChild() {
		return reserveChild;
	}
	public void setReserveChild(Integer reserveChild) {
		this.reserveChild = reserveChild;
	}
	public Integer getReserveStatus() {
		return reserveStatus;
	}
	public void setReserveStatus(Integer reserveStatus) {
		this.reserveStatus = reserveStatus;
	}
	public String getReserveNote() {
		return reserveNote;
	}
	public void setReserveNote(String reserveNote) {
		this.reserveNote = reserveNote;
	}
	public Timestamp getReserveCreateTime() {
		return reserveCreateTime;
	}
	public void setReserveCreateTime(Timestamp reserveCreateTime) {
		this.reserveCreateTime = reserveCreateTime;
	}
}
