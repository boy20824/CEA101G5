package com.queueline.model;

public class QueLineVO {
	private Integer queuelineno;
	private Integer queuenocall;
	private String storeid;
	private Integer queuetableid;
	
	public QueLineVO() {
		
	}
	
	public QueLineVO(Integer queuelineno, Integer queuenocall, String storeid, Integer queuetableid) {
		this.queuelineno = queuelineno;
		this.queuenocall = queuenocall;
		this.storeid = storeid;
		this.queuetableid = queuetableid;
	}

	public Integer getQueuelineno() {
		return queuelineno;
	}

	public void setQueuelineno(Integer queuelineno) {
		this.queuelineno = queuelineno;
	}

	public Integer getQueuenocall() {
		return queuenocall;
	}

	public void setQueuenocall(Integer queuenocall) {
		this.queuenocall = queuenocall;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public Integer getQueuetableid() {
		return queuetableid;
	}

	public void setQueuetableid(Integer queuetableid) {
		this.queuetableid = queuetableid;
	}
}
