package com.reserveorder.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.reserveorder.model.*;
public class ReserveOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String choose = req.getParameter("choose");
		String lateorcancel = req.getParameter("lateorcancel");
		String phoneSearch = req.getParameter("phonesearch");
		if ("qq".equals(action)) {
			System.out.println(phoneSearch);
			req.setAttribute("phoneSearch", phoneSearch);
//			res.sendRedirect("/front-store-end/reserveorder/listOneReserveOrder.jsp" + phoneSearch);
			String url = "/front-store-end/reserveorder/listOneReserveOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("ww".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("phonesearch");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員電話");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reserveorder/select_page.jsp");
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
				ReserveOrderService reserveOrderSvc = new ReserveOrderService();
				List<ReserveOrderVO> list = reserveOrderSvc.getOneReserveOrder(str);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/reserveorder/listAllReserveOrder.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/reserveorder/listOneReserveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/reserveorder/listAllReserveOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String reserveId = new String(req.getParameter("reserveid"));
				String storeId = new String(req.getParameter("storeid").trim());
				String memPhone = new String(req.getParameter("memphone").trim());
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				java.sql.Date reserveTime = java.sql.Date.valueOf(req.getParameter("reservetime").trim());
				Integer reserveAdult = new Integer(req.getParameter("reserveadult").trim());
				Integer reserveChild = new Integer(req.getParameter("reservechild").trim());
				Integer reserveStatus = new Integer(req.getParameter("reservestatus"));
				String reserveNote = new String(req.getParameter("reservenote").trim());
				Timestamp reserveCreateTime = java.sql.Timestamp.valueOf(req.getParameter("reservecreatetime").trim());
				
				/***************************2.開始查詢資料****************************************/
				ReserveOrderService reserveOrderSvc = new ReserveOrderService();
				ReserveOrderVO roVO = new ReserveOrderVO();
								
				/***************************3.修改訂單************/
				//如果是customer就把訂單狀態改成2
				if ("updateforc".equals(choose)) {
					reserveStatus = 2;
					
				}
				//如果是店家就看要改3還是4
				if ("updatefors".equals(choose)) {
					if ("late".equals(lateorcancel)) {
						reserveStatus = 3;
					}
					if ("cancel".equals(lateorcancel)) {
						reserveStatus = 4;
					}
				}
				roVO.setStoreId(storeId);
				roVO.setMemPhone(memPhone);
				roVO.setPeriodId(periodId);
				roVO.setReserveTime(reserveTime);
				roVO.setReserveAdult(reserveAdult);
				roVO.setReserveChild(reserveChild);
				roVO.setReserveStatus(reserveStatus);
				roVO.setReserveNote(reserveNote);
				roVO.setReserveId(reserveId);
				roVO.setReserveCreateTime(reserveCreateTime);
				req.setAttribute("roVO", roVO);
				
				roVO = reserveOrderSvc.updateReserveOrder(reserveId,storeId, memPhone, periodId, reserveTime, reserveAdult, reserveChild, reserveStatus, reserveNote, reserveCreateTime);
				
				String url = "/reservesituation/reservesituation.do";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				//改好訂單狀態之後轉交 位子 的控制器, 去改人數
				
//				if ("updateforc".equals(choose)) {
//					
//				
//				String url = "/front-customer-end/reserveorder/update_reserveorder_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//				}
//				else {
//					String url = "/front-store-end/reserveorder/update_reserveorder_input.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//					successView.forward(req, res);
//				}
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/reserve/listAllReserveOrder.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update".equals(action)) { // 來自update_reserveorder_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String reserveId = new String(req.getParameter("reserveid").trim());
				
				String storeId = new String(req.getParameter("storeid").trim());
				
				String memPhone = new String(req.getParameter("memphone").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				java.sql.Date reserveTime = java.sql.Date.valueOf(req.getParameter("reservetime").trim());
						
				
				Integer reserveAdult = new Integer(req.getParameter("reserveadult").trim());
				
				
				Integer reserveChild = new Integer(req.getParameter("reservechild").trim());
				
				Integer reserveStatus = new Integer(req.getParameter("reservestatus"));
				
				String reserveNote = new String(req.getParameter("reservenote").trim());
				
				
				Timestamp reserveCreateTime = java.sql.Timestamp.valueOf(req.getParameter("reservecreatetime").trim());
				

//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				ReserveOrderVO roVO = new ReserveOrderVO();
				roVO.setStoreId(storeId);
				roVO.setMemPhone(memPhone);
				roVO.setPeriodId(periodId);
				roVO.setReserveTime(reserveTime);
				roVO.setReserveAdult(reserveAdult);
				roVO.setReserveChild(reserveChild);
				roVO.setReserveStatus(reserveStatus);
				roVO.setReserveNote(reserveNote);
				roVO.setReserveId(reserveId);
				roVO.setReserveCreateTime(reserveCreateTime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roVO", roVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reserveorder/update_reserveorder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReserveOrderService reserveOrderSvc = new ReserveOrderService();
				roVO = reserveOrderSvc.updateReserveOrder(reserveId,storeId, memPhone, periodId, reserveTime, reserveAdult, reserveChild, reserveStatus, reserveNote, reserveCreateTime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("roVO", roVO); // 資料庫update成功後,正確的empVO物件,存入req
				String url = "/front-customer-end/reserveorder/listOneReserveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/reserveorder/update_reserveorder_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insertro".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String storeId = req.getParameter("storeid").trim();
			    //Java版Reg ^起頭$結尾 中文範圍 英文範圍數字 長度2~10
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (storeId == null || storeId.trim().length() == 0) {
					errorMsgs.add("請選擇人數未滿之時段");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memPhone = req.getParameter("memphone").trim();
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("請選擇人數未滿之時段");
				}
				

				
				Integer periodId = null;
				try {
					periodId = new Integer(req.getParameter("periodid").trim());
				} catch (NumberFormatException e) {
					periodId = 1;
					errorMsgs.add("時段編號請填數字");
				}
				
				java.sql.Date reserveTime = null;
				reserveTime = java.sql.Date.valueOf(req.getParameter("reservetime").trim());
				
				
				Integer reserveAdult = null;
				try {
					reserveAdult = new Integer(req.getParameter("reserveadult").trim());
				} catch (NumberFormatException e) {
					reserveAdult = 2;
					errorMsgs.add("人數請填數字");
				}
				
				Integer reserveChild = null;
				try {
					reserveChild = new Integer(req.getParameter("reservechild").trim());
				} catch (NumberFormatException e) {
					reserveChild = 0;
					errorMsgs.add("小孩人數請填數字");
				}
				
				Integer reserveStatus = null;
				try {
					reserveStatus = new Integer(req.getParameter("reservestatus").trim());
				} catch (NumberFormatException e) {
					reserveStatus = 0;
					errorMsgs.add("獎金請填數字.");
				}
				
				String reserveNote = null;
				try {
					reserveNote = new String(req.getParameter("reservenote").trim());
				} catch (NumberFormatException e) {
					reserveNote = "";
					errorMsgs.add("備註");
				}
				
				
				
//				Integer reserveChild = new Integer(req.getParameter("reservechild").trim());

				ReserveOrderVO roVO = new ReserveOrderVO();
				roVO.setStoreId(storeId);
				roVO.setMemPhone(memPhone);
				roVO.setPeriodId(periodId);
				roVO.setReserveTime(reserveTime);
				roVO.setReserveAdult(reserveAdult);
				roVO.setReserveChild(reserveChild);
				roVO.setReserveStatus(reserveStatus);
				roVO.setReserveNote(reserveNote);
				

				// Send the use back to the form, if there were errors 只要上面有任一錯誤
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("roVO", roVO); // 含有輸入格式錯誤的empVO物件,也存入req(這樣才不用全部重打, addEmp那邊的FORM會抓到你輸入的值)
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reserveorder/addReserveOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ReserveOrderService reserveOrderSvc = new ReserveOrderService();
				roVO = reserveOrderSvc.addReserveOrder(storeId, memPhone, periodId, reserveTime, reserveAdult, reserveChild, reserveStatus, reserveNote);
				//我是不是在這邊用date storeid period(這邊已經取到了) 還是我直接轉交rs查詢 去查詢AcceptG 跟 reserG 然後req.setattribue?
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/reservesituation/reservesituation.do";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/reserveorder/addReserveOrder.jsp");
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
