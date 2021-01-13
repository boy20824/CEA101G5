package com.restaurantcmt.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodorder.model.FoodOrderService;
import com.menu.model.MenuService;
import com.menu.model.MenuVO;
import com.restaurantcmt.model.RestaurantCmtService;
import com.restaurantcmt.model.RestaurantCmtVO;


@WebServlet("/restaurantcmt/RestaurantCmt.do")
public class RestaurantCmtServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8"); // 處理中文
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");
		
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String storeId =req.getParameter("storeId");
			String memPhone =req.getParameter("memPhone");
			String storeCmtContent = req.getParameter("cmt");
			Integer storeRating=0;
			String foodOrderId = req.getParameter("foodOrderId");
//			取得餐廳評分
			Enumeration<String> enu= req.getParameterNames();
			int i =0;
			while(enu.hasMoreElements()) {
				i++;
				String name=(String) enu.nextElement();
				if(i%5==0) {
					storeRating =Integer.parseInt(req.getParameter(name));
				}
			}
			if(storeCmtContent==null) {
				storeCmtContent=" ";
			}else {
				storeCmtContent=" ";
			}
				/*************************** 2.開始查詢資料 *****************************************/
				RestaurantCmtService cmtSvc = new RestaurantCmtService();
				FoodOrderService foodSvc = new FoodOrderService();
				System.out.println(foodOrderId);
				foodSvc.updateOneByFoodOrderId(foodOrderId);
				 cmtSvc.addRestaurantCmt(storeId,memPhone,storeCmtContent,storeRating,new Integer(0));
			

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/front-customer-end/customerorder/customerorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
