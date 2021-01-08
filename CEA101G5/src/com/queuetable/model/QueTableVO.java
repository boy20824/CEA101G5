package com.queuetable.model;

public class QueTableVO {
	private Integer queuetableid;
	private String queuetabletype;
	private String storeid;
	private Integer queuetablettl;
	private Integer queuetableusable;
	private Integer queuetableocc;
	
	public QueTableVO() {	
	
	}
	
	public QueTableVO(Integer queuetableid,String queuetabletype,String storeid,Integer queuetablettl,Integer queuetableusable,Integer queuetableocc) {
		this.queuetableid = queuetableid;
		this.queuetabletype = queuetabletype;
		this.storeid = storeid;
		this.queuetablettl = queuetablettl;
		this.queuetableusable = queuetableusable;
		this.queuetableocc = queuetableocc;
	}

	public Integer getQueuetableid() {
		return queuetableid;
	}

	public void setQueuetableid(Integer queuetableid) {
		this.queuetableid = queuetableid;
	}

	public String getQueuetabletype() {
		return queuetabletype;
	}

	public void setQueuetabletype(String queuetabletype) {
		this.queuetabletype = queuetabletype;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public Integer getQueuetablettl() {
		return queuetablettl;
	}

	public void setQueuetablettl(Integer queuetablettl) {
		this.queuetablettl = queuetablettl;
	}

	public Integer getQueuetableusable() {
		return queuetableusable;
	}

	public void setQueuetableusable(Integer queuetableusable) {
		this.queuetableusable = queuetableusable;
	}

	public Integer getQueuetableocc() {
		return queuetableocc;
	}

	public void setQueuetableocc(Integer queuetableocc) {
		this.queuetableocc = queuetableocc;
	}
}