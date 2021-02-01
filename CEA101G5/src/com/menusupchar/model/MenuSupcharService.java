package com.menusupchar.model;

import java.util.Set;

import com.menusupchardetail.model.MenuSupcharDetailVO;

public class MenuSupcharService {
private MenuSupchar_interface dao;
	
	public MenuSupcharService() {
		dao = new MenuSupcharDAO();
	}
	
	public void addMenuSupcharVO(String menuSupcharName,Set<MenuSupcharDetailVO> set,MenuSupcharVO menuSupcharVO) {
		
		
		menuSupcharVO.setMenuSupcharName(menuSupcharName);
		menuSupcharVO.setMenuSupcharDetails(set);		
		
		dao.insert(menuSupcharVO);
		
	}
}
