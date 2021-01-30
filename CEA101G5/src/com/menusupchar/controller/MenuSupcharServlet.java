package com.menusupchar.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menusupchar.model.MenuSupcharDAO;
import com.menusupchar.model.MenuSupcharService;
import com.menusupchar.model.MenuSupcharVO;
import com.menusupchardetail.model.MenuSupcharDetailVO;


@WebServlet("/menu/MenuSupchar.do")
public class MenuSupcharServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		String action = req.getParameter("action");
		
		
		if("insert".equals(action)) {
			String menuId = req.getParameter("menuId");
			String menuSupcharName =req.getParameter("menuSupcharName");
			String[] memuSupcharDetailName= req.getParameterValues("memuSupcharDetailName");
			
			Set<MenuSupcharDetailVO> set = new LinkedHashSet<MenuSupcharDetailVO>();
			MenuSupcharVO menuSupcharVO =new MenuSupcharVO();
			
			for(String name:memuSupcharDetailName) {
				MenuSupcharDetailVO menuSupcharDetailVO = new MenuSupcharDetailVO();
				menuSupcharDetailVO.setMenuSupcharDetailName(name);
				menuSupcharDetailVO.setMenuSupcharPrice("10");
				menuSupcharDetailVO.setMenuSupcharVO(menuSupcharVO);
				set.add(menuSupcharDetailVO);
				
			}
			MenuSupcharService menuSupcharService = new MenuSupcharService();
//			呼叫service要把VO傳過
			menuSupcharService.addMenuSupcharVO(menuSupcharName,set,menuSupcharVO);
			

			
			
		}
	}

}
