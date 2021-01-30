package com.menusupchar.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.menusupchardetail.model.MenuSupcharDetailVO;

public class MenuSupcharVO implements Serializable{
	private Long  menuSupcharId;
	private String menuSupcharName ;
	private Set<MenuSupcharDetailVO> menuSupcharDetails = new HashSet<MenuSupcharDetailVO>();
	
	public MenuSupcharVO() {};
	
	public Set<MenuSupcharDetailVO> getMenuSupcharDetails() {
		return menuSupcharDetails;
	}

	public void setMenuSupcharDetails(Set<MenuSupcharDetailVO> menuSupcharDetails) {
		this.menuSupcharDetails = menuSupcharDetails;
	}

	public Long  getMenuSupcharId() {
		return menuSupcharId;
	}
	public void setMenuSupcharId(Long  menuSupcharId) {
		this.menuSupcharId = menuSupcharId;
	}
	public String getMenuSupcharName() {
		return menuSupcharName;
	}
	public void setMenuSupcharName(String menuSupcharName) {
		this.menuSupcharName = menuSupcharName;
	}
}
