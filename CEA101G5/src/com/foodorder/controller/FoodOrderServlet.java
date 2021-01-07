package com.foodorder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.foodorder.model.FoodOrderService;
import com.foodorder.model.FoodOrderVO;
import com.foodorderdetail.model.FoodOrderDetailVO;
import com.menu.model.MenuService;
import com.menu.model.MenuVO;


@WebServlet("/foodorder/FoodOrderServlet.do")
public class FoodOrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		String action = req.getParameter("action");
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
				String[] menuNum = req.getParameterValues("menuNum"); //多值
				String[] menuPrice = req.getParameterValues("menuPrice");//多值
				String[] menuId = req.getParameterValues("menuId");//多值
				String storeId = req.getParameter("storeId");
				Integer foodOrderTotalPrice =Integer.parseInt(req.getParameter("foodOrderTotalPrice"));
				
				

				/*************************** 2.開始新增資料 ***************************************/			

//				先呼叫訂單新增
				FoodOrderService foodOrderSvc =new FoodOrderService();
				List<FoodOrderDetailVO> foodOrderDetailList = new ArrayList<FoodOrderDetailVO>();//拿來放訂單明細
				FoodOrderVO foodOrderVO =foodOrderSvc.addFoodOrder("0921842852",storeId,foodOrderTotalPrice,"食物備註");
				for(int i=0;i<menuNum.length;i++) {
					FoodOrderDetailVO FoodOrderDetail =new FoodOrderDetailVO(); 
					//產生多筆訂單明細
					FoodOrderDetail.setMenuId(menuId[i]);
					FoodOrderDetail.setMenuNum(Integer.parseInt(menuNum[i]));
					FoodOrderDetail.setMenuPrice(Integer.parseInt(menuPrice[i]));
					
					foodOrderDetailList.add(FoodOrderDetail);
					
				}
//				先呼叫Service再去執行DAO
				foodOrderSvc.insertWithFoodOrderDetail(foodOrderVO, foodOrderDetailList);
//				刪除Session裡面的購物車集合
				HttpSession session = req.getSession();
				session.removeAttribute("memuList");
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = req.getContextPath()+"/front-customer-end/foodorder/orderok/orderok.jsp";
				res.sendRedirect(url);
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/foodorder/foodorde.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {
			
//			接收請求參數
			String status = req.getParameter("status");
			String foodOrderId = req.getParameter("foodOrderId");
			Timestamp nowTime = new Timestamp(System.currentTimeMillis());

			
//			開始新增資料
			FoodOrderService foodOrderSvc =new FoodOrderService();
			foodOrderSvc.updateFoodOrder(foodOrderId, nowTime, Integer.parseInt(status));
			
			
//			轉交
			String url = req.getContextPath()+"/front-store-end/foodorder/orderlist.jsp";
			res.sendRedirect(url);
			
		}
		
		
	}

}
