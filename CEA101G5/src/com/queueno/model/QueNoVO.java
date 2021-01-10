package com.queueno.model;

import java.sql.*;

public class QueNoVO implements java.io.Serializable{
	private Integer queuenum;
	private String memphone;
	private Integer party;
	private String storeid;
	private Integer queueperiodid;
	private Integer queuelineno;
	private Integer queuetableid;
	private Timestamp queuenotime;
	
	public QueNoVO() {}
	
	public QueNoVO(Timestamp queuenotime, Integer queuenum, String memphone, Integer party, String storeid, Integer queueperiodid, Integer queuelineno, Integer queuetableid){
		this.queuenum = queuenum;
		this.memphone = memphone;
		this.party = party;
		this.storeid = storeid;
		this.queueperiodid = queueperiodid;
		this.queuelineno = queuelineno;
		this.queuetableid = queuetableid;
		this.queuenotime = queuenotime;
	}

	public Integer getQueuenum() {
		return queuenum;
	}

	public void setQueuenum(Integer queuenum) {
		this.queuenum = queuenum;
	}

	public String getMemphone() {
		return memphone;
	}

	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}

	public Integer getParty() {
		return party;
	}

	public void setParty(Integer party) {
		this.party = party;
	}


	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public Integer getQueueperiodid() {
		return queueperiodid;
	}

	public void setQueueperiodid(Integer queueperiodid) {
		this.queueperiodid = queueperiodid;
	}

	public Integer getQueuelineno() {
		return queuelineno;
	}

	public void setQueuelineno(Integer queuelineno) {
		this.queuelineno = queuelineno;
	}

	public Integer getQueuetableid() {
		return queuetableid;
	}

	public void setQueuetableid(Integer queuetableid) {
		this.queuetableid = queuetableid;
	}

	public Timestamp getQueuenotime() {
		return queuenotime;
	}

	public void setQueuenotime(Timestamp queuenotime) {
		this.queuenotime = queuenotime;
	}
}
	