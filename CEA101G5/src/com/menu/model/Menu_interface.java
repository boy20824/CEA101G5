package com.menu.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Menu_interface {
	
	public void insert(MenuVO menuVO);
	public void update(MenuVO menuVO);
	public void delete(String menuId);
	public MenuVO findByPrimaryKey(String menuId);
	public JSONArray findOneByMenuChar(String menuChar,String storeId);
	public List<MenuVO> getAll();
	public List<MenuVO> getAllByStoreId(String storeId);
	public void updateSellStatus(MenuVO menuVO);
}
