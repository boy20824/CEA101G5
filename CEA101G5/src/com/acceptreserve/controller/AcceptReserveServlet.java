package com.acceptreserve.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.naming.java.javaURLContextFactory;

import com.acceptreserve.model.*;
public class AcceptReserveServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				String str = req.getParameter("storeid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂位訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/acceptreserve/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String reserveId = null;
//				try {
//					reserveId = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-customer-end/reserveorder/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				/***************************2.開始查詢資料*****************************************/
				AcceptReserveService arSvc = new AcceptReserveService();
				AcceptReserveVO arVO = arSvc.getOneAcceptReserve(str,periodId);
				if (arVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/acceptreserve/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("arVO", arVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/acceptreserve/listOneAcceptReserve.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/acceptreserve/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("updateperiod".equals(action)) { // 來自listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				String storeId = req.getParameter("storeid");
				if (storeId == null || (storeId.trim()).length() == 0) {
					errorMsgs.add("請輸入訂位訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/acceptreserve/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料****************************************/
				AcceptReserveService arSvc = new AcceptReserveService();
				AcceptReserveVO arVO = arSvc.getOneAcceptReserve(storeId,periodId);
				
				Timestamp startTime = java.sql.Timestamp.valueOf(req.getParameter("starttime").trim());
				
				Timestamp endTime = java.sql.Timestamp.valueOf(req.getParameter("endtime").trim());
				
				Integer periodStatus = new Integer(req.getParameter("periodstatus").trim());

				AcceptReserveVO qq = new AcceptReserveVO();
				qq.setStoreId(storeId);
				qq.setPeriodId(periodId);
				qq.setStartTime(startTime);
				qq.setEndTime(endTime);
				qq.setPeriodStatus(periodStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("arVO", qq); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/acceptreserve/update_acceptreserve_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				qq = arSvc.updateAcceptReserve(periodId,storeId,startTime,endTime,periodStatus);
				
				
				/***************************3.準備轉交(Send the Success view)************/
				req.setAttribute("arVO", qq);         // 資料庫取出的empVO物件,存入req
				String url = "/reservesituation/reservesituation.do";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reserve/listAllAcceptReserve.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_reserveorder_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String storeId = new String(req.getParameter("storeid").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				Timestamp startTime = java.sql.Timestamp.valueOf(req.getParameter("starttime").trim());
				
				Timestamp endTime = java.sql.Timestamp.valueOf(req.getParameter("endtime").trim());
				
				Integer periodStatus = new Integer(req.getParameter("periodstatus").trim());

				AcceptReserveVO arVO = new AcceptReserveVO();
				arVO.setStoreId(storeId);
				arVO.setPeriodId(periodId);
				arVO.setStartTime(startTime);
				arVO.setEndTime(endTime);
				arVO.setPeriodStatus(periodStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("arVO", arVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/acceptreserve/update_acceptreserve_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AcceptReserveService arSvc = new AcceptReserveService();
				arVO = arSvc.updateAcceptReserve(periodId,storeId,startTime,endTime,periodStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("arVO", arVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-store-end/acceptreserve/listOneAcceptReserve"
						+ ".jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/acceptreserve/update_acceptreserve_input.jsp");
				failureView.forward(req, res);
			}
		}
//
        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer periodId = new Integer(req.getParameter("periodid"));
				if (periodId >= 11) {
					errorMsgs.add("時段最多10筆");
				}
				
				String storeId = new String(req.getParameter("storeid"));
				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				java.sql.Timestamp startTime = null;
				try{
					startTime = java.sql.Timestamp.valueOf(req.getParameter("starttime"));
				} catch (IllegalArgumentException e) {
//					startTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請勿修改格式");
				}
				
				java.sql.Timestamp endTime = null;
				try{
					endTime = java.sql.Timestamp.valueOf(req.getParameter("endtime"));
				} catch (IllegalArgumentException e) {
//					endTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請勿修改格式出錯");
				}
				int startTimec = startTime.getHours()*60 + startTime.getMinutes();
				int endTimec = endTime.getHours()*60 + endTime.getMinutes();
				if (endTimec < startTimec) {
					errorMsgs.add("結束時間必須在同一天開始時間之後!!");
				}
				int ins = startTime.getHours()*60 + startTime.getMinutes();
				int ine = endTime.getHours()*60 + endTime.getMinutes();
				
				
				Integer fori = new Integer(req.getParameter("fori"));
				//問題在FOR迴圈 因為i=1&2有值   i=3時 req.getParameter就錯了 所以i要動態給
				//不然timestamp會丟出if (s == null) throw new java.lang.IllegalArgumentException("null string");
				
				
				for (int i = 1; i < (fori+1); i++) {
					try {
						java.sql.Timestamp st = java.sql.Timestamp.valueOf(req.getParameter(i+""));
						int stc = st.getHours()*60 + st.getMinutes();
						
						java.sql.Timestamp et = java.sql.Timestamp.valueOf(req.getParameter((i*10)+""));
						int edc = et.getHours()*60 + et.getMinutes();
						
						if ((ins > stc && ins < edc) || (ine > stc && ine < edc) || (ins < stc && ine > stc)
								|| (ins == stc && ine == edc)) {
							errorMsgs.add("時段重複");
							break;
						}
						
					} catch (IllegalArgumentException e) {
						
					}
					
				}
				if ((ine-ins) < 60) {
					errorMsgs.add("時段最短1小時");
				}
				if ((ine-ins) > 120) {
					errorMsgs.add("時段最長2小時");
				}
				
				
				Integer periodStatus = new Integer(req.getParameter("periodstatus"));
				

				AcceptReserveVO arVO = new AcceptReserveVO();
				arVO.setPeriodId(periodId);
				arVO.setStoreId(storeId);
				arVO.setStartTime(startTime);
				arVO.setEndTime(endTime);
				arVO.setPeriodStatus(periodStatus);
				

				// Send the use back to the form, if there were errors 只要上面有任一錯誤
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("arVO", arVO); // 含有輸入格式錯誤的empVO物件,也存入req(這樣才不用全部重打, addEmp那邊的FORM會抓到你輸入的值)
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/acceptreserve/listAllAcceptReserve.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AcceptReserveService arSvc = new AcceptReserveService();
				arVO = arSvc.addAcceptReserve(periodId,storeId,startTime,endTime,periodStatus); 
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front-customer-end/acceptreserve/listAllAcceptReserve.jsp";
				String url = "/reservesituation/reservesituation.do";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/acceptreserve/listAllAcceptReserve.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer empno = new Integer(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
