package com.reservesituation.controller;

import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.naming.java.javaURLContextFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.acceptreserve.model.*;
import com.reservesituation.model.*;
public class ReserveSituationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String choose = req.getParameter("choose");
		String delPeriod = req.getParameter("delperiod");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				java.sql.Date reserveSituationDate = java.sql.Date.valueOf(req.getParameter("reservesituation").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				String str = req.getParameter("storeid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂位訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reservesituation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************2.開始查詢資料*****************************************/
				ReserveSituationService rsSvc = new ReserveSituationService();
				ReserveSituationVO rsVO = rsSvc.getOneReserveSituation(reserveSituationDate,str,periodId);
				if (rsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reservesituation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rsVO", rsVO); // 資料庫取出的ReserveSituationVO物件,存入req
				String url = "/front-customer-end/reservesituation/listOneReserveSituation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneReserveSituation.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/reservesituation/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insertro".equals(action)) {	//當有客戶訂位

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				java.sql.Date reserveSituationDate = java.sql.Date.valueOf(req.getParameter("reservetime").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid"));
				
				String storeId = req.getParameter("storeid");
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reservesituation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料****************************************/
				ReserveSituationService rsSvc = new ReserveSituationService();
				ReserveSituationVO rsVO = rsSvc.getOneReserveSituation(reserveSituationDate,storeId,periodId);
				
				Integer acceptGroups = rsVO.getAcceptGroups();
				Integer reservedGroups = rsVO.getReservedGroups();
				Integer reserveAdult = new Integer(req.getParameter("reserveadult"));
				acceptGroups = (acceptGroups*2 - reserveAdult)/2;
				reservedGroups += ((reserveAdult/2)+(reserveAdult%2));
				
				ReserveSituationVO qq = new ReserveSituationVO();
				qq.setStoreId(storeId);
				qq.setPeriodId(periodId);
				qq.setReserveSituationDate(reserveSituationDate);
				qq.setAcceptGroups(acceptGroups);
				qq.setReservedGroups(reservedGroups);
				qq = rsSvc.updateReserveSituation(reserveSituationDate,storeId,periodId,acceptGroups,reservedGroups);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("qq", qq);         // 資料庫取出的ReserveSituationVO物件,存入req
				String url = "/front-customer-end/reserveorder/listAllReserveOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ReserveSituation_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-store-end/reservesituation/listAllReserveSituation.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("getOne_For_Update".equals(action)) { //取消訂位

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				java.sql.Date reserveSituationDate = java.sql.Date.valueOf(req.getParameter("reservetime").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid"));
				
				String storeId = req.getParameter("storeid");
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/reservesituation/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料****************************************/
				ReserveSituationService rsSvc = new ReserveSituationService();
				ReserveSituationVO rsVO = rsSvc.getOneReserveSituation(reserveSituationDate,storeId,periodId);
				
				Integer acceptGroups = rsVO.getAcceptGroups();
				Integer reservedGroups = rsVO.getReservedGroups();
				Integer reserveAdult = new Integer(req.getParameter("reserveadult"));
				acceptGroups += ((reserveAdult/2) + (reserveAdult%2));
				reservedGroups -= ((reserveAdult/2) + (reserveAdult%2));
				
				ReserveSituationVO qq = new ReserveSituationVO();
				qq.setStoreId(storeId);
				qq.setPeriodId(periodId);
				qq.setReserveSituationDate(reserveSituationDate);
				qq.setAcceptGroups(acceptGroups);
				qq.setReservedGroups(reservedGroups);
				qq = rsSvc.updateReserveSituation(reserveSituationDate,storeId,periodId,acceptGroups,reservedGroups);
				
				/***************************3.修改完成,準備轉交(Send the Success view)************/
				req.setAttribute("qq", qq);         // 資料庫取出的ReserveSituationVO物件,存入req
				if ("updateforc".equals(choose)) {
					String url = "/front-customer-end/reserveorder/listAllReserveOrder.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ReserveSituation_input.jsp
					successView.forward(req, res);
				}
				if ("updatefors".equals(choose)) {
					String url = "/front-store-end/reserveorder/listAllReserveOrder.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ReserveSituation_input.jsp
					successView.forward(req, res);
				}
				

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-store-end/reservesituation/listAllReserveSituation.jsp");
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
				
				java.sql.Date reserveSituationDate = java.sql.Date.valueOf(req.getParameter("reservesituation").trim());
				
				String storeId = new String(req.getParameter("storeid").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				Integer acceptGroups = new Integer(req.getParameter("acceptgroups").trim());
				
				Integer reservedGroups = new Integer(req.getParameter("reservegroups").trim());

				ReserveSituationVO rsVO = new ReserveSituationVO();
				rsVO.setStoreId(storeId);
				rsVO.setPeriodId(periodId);
				rsVO.setReserveSituationDate(reserveSituationDate);
				rsVO.setAcceptGroups(acceptGroups);
				rsVO.setReservedGroups(reservedGroups);
				


				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("rsVO", rsVO); // 含有輸入格式錯誤的ReserveSituationVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-customer-end/reservesituation/update_reservesituation_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
				
				/***************************2.開始修改資料*****************************************/
				ReserveSituationService rsSvc = new ReserveSituationService();
				rsVO = rsSvc.updateReserveSituation(reserveSituationDate,storeId,periodId,acceptGroups,reservedGroups);
				
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rsVO", rsVO); // 資料庫update成功後,正確的的ReserveSituationVO物件,存入req
				
				String url = "/front-customer-end/reservesituation/listOneReserveSituation"
						+ ".jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneReserveSituation.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/reservesituation/update_reservesituation_input.jsp");
				failureView.forward(req, res);
			}
		}
        if ("insert".equals(action) || "open".equals(delPeriod)) { // 當時段被新增 或 被開啟
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				java.sql.Date reserveSituationDate = java.sql.Date.valueOf(req.getParameter("reservesituationdate").trim());
				
				String storeId = new String(req.getParameter("storeid").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				Integer acceptGroups = new Integer(req.getParameter("acceptgroups").trim());
				
				Integer reservedGroups = new Integer(req.getParameter("reservedgroups").trim());

				Integer acceptDays = new Integer(req.getParameter("acceptdays").trim());

				
				for (int i = 1; i < acceptDays+1; i++) {
				ReserveSituationVO rsVO = new ReserveSituationVO();
				rsVO.setStoreId(storeId);
				rsVO.setPeriodId(periodId);
				rsVO.setReserveSituationDate(reserveSituationDate);
				rsVO.setAcceptGroups(acceptGroups);
				rsVO.setReservedGroups(reservedGroups);
				
				
				
				/***************************2.開始新增資料***************************************/
				ReserveSituationService rsSvc = new ReserveSituationService();
				rsVO = rsSvc.addReserveSituation(reserveSituationDate,storeId,periodId,acceptGroups,reservedGroups);
				
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
				String nextDay = sdf.format(new Date(reserveSituationDate.getTime() + (24*60*60*1000L)));
				reserveSituationDate = java.sql.Date.valueOf(nextDay);
				}
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-store-end/acceptreserve/listAllAcceptReserve.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllReserveSituation.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/reserveorder/listAllReserveOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("close".equals(delPeriod)) { // 來自listAllReserveSituation.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				java.sql.Date reserveSituationDate = java.sql.Date.valueOf(req.getParameter("reservesituationdate").trim());
				
				String storeId = new String(req.getParameter("storeid").trim());
				
				Integer periodId = new Integer(req.getParameter("periodid").trim());
				
				/***************************2.開始刪除資料***************************************/
				ReserveSituationService rsSvc = new ReserveSituationService();
				rsSvc.deleteReserveSituation(reserveSituationDate,storeId, periodId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-store-end/acceptreserve/listAllAcceptReserve.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ReserveSituation/listAllReserveSituation.jsp");
				failureView.forward(req, res);
			}
		}
		if ("searchacg".equals(action)) {
			java.sql.Date dd = java.sql.Date.valueOf(req.getParameter("date"));
			Integer pickNum = Integer.parseInt( (req.getParameter("picknum")));
			ReserveSituationService rsSvc = new ReserveSituationService();
			AcceptReserveService arSvc = new AcceptReserveService();
			String ss = req.getParameter("storeid");
			List<ReserveSituationVO> list = rsSvc.getSearch(dd,ss);//該日期該店家 有哪些時段有位子
//			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			for (int i = 0; i < list.size(); i++) {//依有位子的時段數量跑迴圈
				
				int acg = list.get(i).acceptGroups;//acg=該時段的剩餘位子
				if (pickNum < acg * 2) {
					//periodId當KEY(反正同家店的ID不會重複) 記得要改店號
					DateFormat df = new SimpleDateFormat("HH:mm");
					try {
						obj.put("" + list.get(i).periodId, df.format(arSvc.getOneAcceptReserve(ss,list.get(i).periodId).startTime));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//先丟到list再包成JSONarray 再out.print拋給JSP 參考ClassDemo2 AjaxResponse.java
				//我沒有包成JSONArray直接JSON物件回去了
			}
//			array.put(obj);
			PrintWriter out = res.getWriter();
//			out.print(array.toString());//write是寫在頁面上  我們要get要用print
			out.print(obj);
			out.flush();
			out.close();
		}
	}
}
