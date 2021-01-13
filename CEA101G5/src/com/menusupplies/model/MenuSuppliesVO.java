package com.menusupplies.model;

import java.io.Serializable;

public class MenuSuppliesVO implements Serializable{
	private String menuSupcharId;
	private String menuId;
	public String getMenuSupcharId() {
		return menuSupcharId;
	}
	public void setMenuSupcharId(String menuSupcharId) {
		this.menuSupcharId = menuSupcharId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
}
