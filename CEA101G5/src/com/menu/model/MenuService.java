package com.menu.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class MenuService {

	private Menu_interface dao;
	
	public MenuService() {
		dao = new MenuJNDIDAO();
	}
	
	public MenuVO addMenu(String menuId,String storeId,String menuName,String menuDetail,byte[] menuPic,String menuChar,Integer menuStatus,Integer menuPrice,Integer menuSellStatus) {
		
		MenuVO menuVO = new MenuVO();
		
		menuVO.setStoreId(storeId);
		menuVO.setMenuName(menuName);
		menuVO.setMenuDetail(menuDetail);
		menuVO.setMenuPic(menuPic);
		menuVO.setMenuChar(menuChar);
		menuVO.setMenuStatus(menuStatus);
		menuVO.setMenuPrice(menuPrice);
		menuVO.setMenuSellStatus(menuSellStatus);
		dao.insert(menuVO);
		
		return menuVO;
	}
	public MenuVO updateMenu(String menuId,String storeId,String menuName,String menuDetail,byte[] menuPic,String menuChar,Integer menuStatus,Integer menuPrice,Integer menuSellStatus) {
		MenuVO menuVO = new MenuVO();
		
		menuVO.setMenuName(menuName);
		menuVO.setMenuDetail(menuDetail);
		menuVO.setMenuPic(menuPic);
		menuVO.setMenuChar(menuChar);
		menuVO.setMenuStatus(menuStatus);
		menuVO.setMenuPrice(menuPrice);
		menuVO.setMenuSellStatus(menuSellStatus);
		menuVO.setMenuId(menuId);
		menuVO.setStoreId(storeId);
		dao.update(menuVO);
		
		return menuVO;
		
	}
	
	public void deleteMenu(String menuId) {
		dao.delete(menuId);
	}
	
	public MenuVO getOneMenu(String menuId) {
		return dao.findByPrimaryKey(menuId);
	}
	public List<MenuVO> getAll() {
		return dao.getAll();
	}
	public List<MenuVO> getAllByStoreId(String storeId){
		return dao.getAllByStoreId(storeId);
	}
	public MenuVO updateSellStatus(String menuId,Integer menuStatus ) {
		MenuVO menuVO = new MenuVO();
		menuVO.setMenuId(menuId);
		menuVO.setMenuStatus(menuStatus);
		dao.updateSellStatus(menuVO);
		
		return menuVO;
	}
	public JSONArray getOneByMenuChar(String menuChar,String storeId) {
		return dao.findOneByMenuChar(menuChar,storeId);
	}
	
}
