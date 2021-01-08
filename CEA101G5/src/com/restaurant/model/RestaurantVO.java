package com.restaurant.model;

import java.sql.*;

public class RestaurantVO implements java.io.Serializable{
	private String storeId;
	private String memPhone;
	private String storeChar;
	private String storeInfo;
	private String storeName;
	private String storePhone;
	private String storeAddress;
	private Integer storeStatus;
	private Integer storeFinalReservDate;
	private Integer storeOrderCondition;
	private Integer storeReservCondition;
	private Integer storeQueueCondition;
	private Integer storeOrderWaitTime;
	private Timestamp storeOpenTime;
	private Timestamp storeCloseTime;
	private Timestamp storeStartOrderDate;
	private Timestamp storeEndOrderDate;
	private Integer acceptGroups;
	private Integer numOfGroups;
	private Integer storePeopleTotal;
	private Integer storeRatingTotal;
	
	public RestaurantVO() {
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

	public String getStoreChar() {
		return storeChar;
	}

	public void setStoreChar(String storeChar) {
		this.storeChar = storeChar;
	}

	public String getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(String storeInfo) {
		this.storeInfo = storeInfo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Integer getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(Integer storeStatus) {
		this.storeStatus = storeStatus;
	}

	public Integer getStoreFinalReservDate() {
		return storeFinalReservDate;
	}

	public void setStoreFinalReservDate(Integer storeFinalReservDate) {
		this.storeFinalReservDate = storeFinalReservDate;
	}

	public Integer getStoreOrderCondition() {
		return storeOrderCondition;
	}

	public void setStoreOrderCondition(Integer storeOrderCondition) {
		this.storeOrderCondition = storeOrderCondition;
	}

	public Integer getStoreReservCondition() {
		return storeReservCondition;
	}

	public void setStoreReservCondition(Integer storeReservCondition) {
		this.storeReservCondition = storeReservCondition;
	}

	public Integer getStoreQueueCondition() {
		return storeQueueCondition;
	}

	public void setStoreQueueCondition(Integer storeQueueCondition) {
		this.storeQueueCondition = storeQueueCondition;
	}

	public Integer getStoreOrderWaitTime() {
		return storeOrderWaitTime;
	}

	public void setStoreOrderWaitTime(Integer storeOrderWaitTime) {
		this.storeOrderWaitTime = storeOrderWaitTime;
	}

	public Timestamp getStoreOpenTime() {
		return storeOpenTime;
	}

	public void setStoreOpenTime(Timestamp storeOpenTime) {
		this.storeOpenTime = storeOpenTime;
	}

	public Timestamp getStoreCloseTime() {
		return storeCloseTime;
	}

	public void setStoreCloseTime(Timestamp storeCloseTime) {
		this.storeCloseTime = storeCloseTime;
	}

	public Timestamp getStoreStartOrderDate() {
		return storeStartOrderDate;
	}

	public void setStoreStartOrderDate(Timestamp storeStartOrderDate) {
		this.storeStartOrderDate = storeStartOrderDate;
	}

	public Timestamp getStoreEndOrderDate() {
		return storeEndOrderDate;
	}

	public void setStoreEndOrderDate(Timestamp storeEndOrderDate) {
		this.storeEndOrderDate = storeEndOrderDate;
	}

	public Integer getAcceptGroups() {
		return acceptGroups;
	}

	public void setAcceptGroups(Integer acceptGroups) {
		this.acceptGroups = acceptGroups;
	}

	public Integer getNumOfGroups() {
		return numOfGroups;
	}

	public void setNumOfGroups(Integer numOfGroups) {
		this.numOfGroups = numOfGroups;
	}

	public Integer getStorePeopleTotal() {
		return storePeopleTotal;
	}

	public void setStorePeopleTotal(Integer storePeopleTotal) {
		this.storePeopleTotal = storePeopleTotal;
	}

	public Integer getStoreRatingTotal() {
		return storeRatingTotal;
	}

	public void setStoreRatingTotal(Integer storeRatingTotal) {
		this.storeRatingTotal = storeRatingTotal;
	}
	
	
}
