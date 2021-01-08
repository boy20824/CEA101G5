package com.storechardetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemService;
import com.storechardetail.model.StoreCharDetailService;
import com.storechardetail.model.StoreCharDetailVO;


@WebServlet("/storechardetail/StoreCharDetailServlet")
public class StoreCharDetailServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String storeCharName = req.getParameter("storeCharName");
				String memPhoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (storeCharName == null || storeCharName.trim().length() == 0) {
					errorMsgs.add("餐廳分類不得空白");
				} else if (!storeCharName.trim().matches(memPhoneReg)) { // 餐廳分類
					errorMsgs.add("餐廳分類只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				// 判斷分類名稱是否有重複
				StoreCharDetailService storeCharDetailSvctest = new StoreCharDetailService();
				List<StoreCharDetailVO> listall = storeCharDetailSvctest.getAll();
				for (StoreCharDetailVO storeCharDetailVOList : listall) {
					if (storeCharDetailVOList.getStoreCharName().equals(storeCharName)) {
						errorMsgs.add("餐廳分類已重複，請重新輸入");
					}
				}
				
				StoreCharDetailVO storeCharDetailVO = new StoreCharDetailVO();
				storeCharDetailVO.setStoreCharName(storeCharName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeCharDetailVO", storeCharDetailVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/storeChar/storeChar.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				StoreCharDetailService storeCharDetailSvc = new StoreCharDetailService();
				storeCharDetailVO = storeCharDetailSvc.addStoreCharDetail(storeCharName);
				

				/*************************** 3.新增完成轉交成功畫面(Send the Success view) ***********/
				String url = "/back-end/storeChar/listAllStoreChar.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/storeChar/storeChar.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)){

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				
				 ***************************************/
				String storeChar = new String(req.getParameter("storeChar"));

				/***************************
				 
				 ***************************************/
				StoreCharDetailService storeCharDetailSvc = new StoreCharDetailService();
				storeCharDetailSvc.deleteStoreCharDetail(storeChar);

				/***************************
				*/
				String url = "/back-end/storeChar/listAllStoreChar.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);//
				successView.forward(req, res);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/storeChar/listAllStoreChar.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
