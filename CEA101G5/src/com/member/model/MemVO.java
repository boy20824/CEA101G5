package com.member.model;

import java.sql.*;


public class MemVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	public MemVO(){
		
	}
	
	private String memPhone;
	private String memPwd;
	private String memName;
	private String memAddress;
	private Integer memSex;
	private String memEmail;
	private String memIdentity;
	private Date memBirth;
	private String memNick;
	private Integer memLice;
	private Integer memCondition;
	private String memAuth;
	private Integer memTotalRechar;
	private byte[] memPhoto;
	private String memCardNumber;
	private String memCardHolder;
	private String memCardExpirationDate;
	private String memCardCCV;
	
	
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public Integer getMemSex() {
		return memSex;
	}
	public void setMemSex(Integer memSex) {
		this.memSex = memSex;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemIdentity() {
		return memIdentity;
	}
	public void setMemIdentity(String memIdentity) {
		this.memIdentity = memIdentity;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public Integer getMemLice() {
		return memLice;
	}
	public void setMemLice(Integer memLice) {
		this.memLice = memLice;
	}
	public Integer getMemCondition() {
		return memCondition;
	}
	public void setMemCondition(Integer memCondition) {
		this.memCondition = memCondition;
	}
	public String getMemAuth() {
		return memAuth;
	}
	public void setMemAuth(String memAuth) {
		this.memAuth = memAuth;
	}
	public Integer getMemTotalRechar() {
		return memTotalRechar;
	}
	public void setMemTotalRechar(Integer memTotalRechar) {
		this.memTotalRechar = memTotalRechar;
	}
	public byte[] getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(byte[] memPhoto) {
		this.memPhoto = memPhoto;
	}
	public String getMemCardNumber() {
		return memCardNumber;
	}
	public void setMemCardNumber(String memCardNumber) {
		this.memCardNumber = memCardNumber;
	}
	public String getMemCardHolder() {
		return memCardHolder;
	}
	public void setMemCardHolder(String memCardHolder) {
		this.memCardHolder = memCardHolder;
	}
	public String getMemCardExpirationDate() {
		return memCardExpirationDate;
	}
	public void setMemCardExpirationDate(String memCardExpirationDate) {
		this.memCardExpirationDate = memCardExpirationDate;
	}
	public String getMemCardCCV() {
		return memCardCCV;
	}
	public void setMemCardCCV(String memCardCCV) {
		this.memCardCCV = memCardCCV;
	}
		
}
