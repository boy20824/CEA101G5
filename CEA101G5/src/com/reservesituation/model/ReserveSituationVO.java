package com.reservesituation.model;
import java.sql.*;

public class ReserveSituationVO implements java.io.Serializable{
	public Date reserveSituationDate;
	public String storeId;
	public Integer periodId;
	public Integer acceptGroups;
	public Integer reservedGroups;
	public Date getReserveSituationDate() {
		return reserveSituationDate;
	}
	public void setReserveSituationDate(Date reserveSituationDate) {
		this.reserveSituationDate = reserveSituationDate;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Integer getPeriodId() {
		return periodId;
	}
	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}
	public Integer getAcceptGroups() {
		return acceptGroups;
	}
	public void setAcceptGroups(Integer acceptGroups) {
		this.acceptGroups = acceptGroups;
	}
	public Integer getReservedGroups() {
		return reservedGroups;
	}
	public void setReservedGroups(Integer reservedGroups) {
		this.reservedGroups = reservedGroups;
	}

}
