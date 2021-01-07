package com.menu.model;

public class MenuVO implements java.io.Serializable {
	private String menuId;
	private String storeId;
	private String menuName;
	private String menuDetail;
	private byte[] menuPic;
	private String menuChar;
	private Integer menuStatus;
	private Integer menuPrice;
	private Integer menuSellStatus;
//	為了購物車新增,在沒有使用框架的時候可以,如果使用框架就不能(因為有映射)
	private Integer quantity;
	public MenuVO() {
		
	}
	
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDetail() {
		return menuDetail;
	}
	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}
	public byte[] getMenuPic() {
		return menuPic;
	}
	public void setMenuPic(byte[] menuPic) {
		this.menuPic = menuPic;
	}
	public String getMenuChar() {
		return menuChar;
	}
	public void setMenuChar(String menuChar) {
		this.menuChar = menuChar;
	}
	public Integer getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}
	public Integer getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}
	public Integer getMenuSellStatus() {
		return menuSellStatus;
	}
	public void setMenuSellStatus(Integer menuSellStatus) {
		this.menuSellStatus = menuSellStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

//	為了使集合裡面的物件進行比較,會先比較hasCode方法在比較equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVO other = (MenuVO) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		return true;
	}
	
	
}
