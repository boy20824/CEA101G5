package com.menusupchardetail.model;

import java.io.Serializable;

import com.menusupchar.model.MenuSupcharVO;

public class MenuSupcharDetailVO implements Serializable{
	
	private Long  menuSupcharDetailId;
	private String menuSupcharDetailName;
	private String menuSupcharPrice;
	private MenuSupcharVO menuSupcharVO;
	
	public MenuSupcharVO getMenuSupcharVO() {
		return menuSupcharVO;
	}

	public void setMenuSupcharVO(MenuSupcharVO menuSupcharVO) {
		this.menuSupcharVO = menuSupcharVO;
	}

	public MenuSupcharDetailVO() {}

	public Long  getMenuSupcharDetailId() {
		return menuSupcharDetailId;
	}

	public void setMenuSupcharDetailId(Long  menuSupcharDetailId) {
		this.menuSupcharDetailId = menuSupcharDetailId;
	}

	public String getMenuSupcharDetailName() {
		return menuSupcharDetailName;
	}

	public void setMenuSupcharDetailName(String menuSupcharDetailName) {
		this.menuSupcharDetailName = menuSupcharDetailName;
	}

	public String getMenuSupcharPrice() {
		return menuSupcharPrice;
	}

	public void setMenuSupcharPrice(String menuSupcharPrice) {
		this.menuSupcharPrice = menuSupcharPrice;
	};
	
}
