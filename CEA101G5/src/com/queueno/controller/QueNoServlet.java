package com.queueno.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.queueline.model.*;
import com.queueno.model.*;
import com.queueperiod.model.*;
import com.queuetable.model.*;

public class QueNoServlet extends HttpServlet {

	static int count = 1;
//	static Set<Integer> countSet = new TreeSet<Integer>();
//	static int custInsert;
//	static int storeInsert;
	Timer timer = new Timer();

	public void init() {
		// reset取號號碼 時間間隔
		Integer dayTime = 60 * 60 * 1000;
		// 設定開始年月(-1)日時分秒
		Date date = new Date(120, 11, 23, 18, 31, 2);
		System.out.println(date);
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				count = 1;
//				countSet.clear();
			}
		};
//		timer.schedule(timerTask,  10*1000);
//		timer.scheduleAtFixedRate(timerTask, new Date(), dayTime);
		timer.scheduleAtFixedRate(timerTask, date, dayTime);
	}

	public void destroy() {
		timer.cancel();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		System.out.println(action);

		// 開始取號
			if ("getQueNo".equals(action)) {
//			countSet.add(count);
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				try {

					String storeid = req.getParameter("storeid");
					QuePeriodService quePeriodSvc = new QuePeriodService();
					List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/queue/queueNo/customerPickupNo.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					HttpSession session = req.getSession();
//				session.setAttribute("pickupNo", ((TreeSet<Integer>) countSet).last());
					session.setAttribute("pickupNo", count);
					// SET 一間store的list
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("storeid", storeid);
					count++;// 計數+1
//				req.setAttribute("pickupNo", count);
//				req.setAttribute("quePeriodVO", quePeriodVO);
					String url = "/front-end/queue/queueNo/customerPickupNo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/queue/queueNo/customerPickupNo.jsp");
					failureView.forward(req, res);
				}
			
		} else if ("storeGetQueNo".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String storeid = req.getParameter("storeid");
				
				QuePeriodService quePeriodSvc = new QuePeriodService();
				List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
				
				QueTableService queTableSvc = new QueTableService();
				List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
				
				QueLineService queLineSvc = new QueLineService();
				List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);
				
				QueNoService queNoSvc = new QueNoService();
				List<QueNoVO> queNoVO = queNoSvc.getQueNoByStoreId(storeid);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/queue/queueNo/storePickupNoAndNoCall.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				HttpSession session = req.getSession();
//				session.setAttribute("pickupNo", ((TreeSet<Integer>) countSet).last());
					session.setAttribute("pickupNo", count);
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("queTableVO", queTableVO);
					session.setAttribute("queLineVO", queLineVO);
					session.setAttribute("queNoVO", queNoVO);
					session.setAttribute("storeid", storeid);
					count++;// 計數+1
//				req.setAttribute("pickupNo", count);
//				req.setAttribute("quePeriodVO", quePeriodVO);
//				count++;
				String url = "/front-end/queue/queueNo/storePickupNoAndNoCall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/queue/queueNo/storePickupNoAndNoCall.jsp");
				failureView.forward(req, res);
			}
		}

		if ("list_One_Store".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String storeid = req.getParameter("storeid");
				/*************************** 2.開始查詢資料 *****************************************/
				QueNoService queNoSvc = new QueNoService();

				List<QueNoVO> quenoVO = queNoSvc.getQueNoByStoreId(storeid);
				if (quenoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/queue/queueNo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("queNoVO", quenoVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/queue/queueNo/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/queue/queueNo/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer queuenum = new Integer(req.getParameter("queuenum"));

				String memphone = req.getParameter("memphone");

				/*************************** 2.開始查詢資料 *****************************************/

				QueNoService queNoSvc = new QueNoService();
				QueNoVO quenoVO = null;
				if (!queuenum.equals(0)) {
					quenoVO = queNoSvc.getOneQueNo(queuenum, memphone);
				} else {
					quenoVO = queNoSvc.getQueNoByPhone(memphone);
				}
				if (quenoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/queue/queueNo/listAllQueNo.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("queNoVO", quenoVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/queue/queueNo/listOneQueNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/queue/queueNo/listAllQueNo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer queuenum = new Integer(req.getParameter("queueno"));
				String memphone = req.getParameter("memphone");

				/*************************** 2.開始查詢資料 ****************************************/
				QueNoService queNoSvc = new QueNoService();
				QueNoVO queNoVO = queNoSvc.getOneQueNo(queuenum, memphone);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("queNoVO", queNoVO); // 資料庫取出的empVO物件,存入req
				String url = "/queNo/numPickup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/queNo/numPickup.jsp");
				failureView.forward(req, res);
			}
		}

		// 查詢手機+店號
		if ("getOne_By_Phone_And_Store".equals(action)) {

			String memphone = req.getParameter("memphone").trim();
			String storeid = req.getParameter("storeid").trim();

			QueNoService queNoSvc = new QueNoService();
			QueNoVO queNoVO = queNoSvc.getQueNoByPhoneAndStore(memphone, storeid);

			req.setAttribute("queNoVO", queNoVO);
			String url = "/front-end/queue/queueNo/showCustomerPickupNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("storeInsert".equals(action)) {
			System.out.println("startinsert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer queuenum = new Integer(req.getParameter("queuenum"));
				String memphone = req.getParameter("memphone").trim();
				if (memphone == null || memphone.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if (!memphone.trim().matches(memphone)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("只能是手機號碼");
				}
				Integer party = null;
				try {
					party = new Integer(req.getParameter("party"));
				} catch (NumberFormatException e) {
					errorMsgs.add("抓不到party");
				}
				System.out.println(req.getParameter("queuenotime"));
				Timestamp queuenotime = null;
				try {
					queuenotime = strToTsp(req.getParameter("queuenotime"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("未抓到日期!");
				}
				String storeid = req.getParameter("storeid").trim();
				Integer queueperiodid = new Integer(req.getParameter("queueperiodid").trim());
				Integer queuelineno = new Integer(req.getParameter("queuelineno").trim());
				Integer queuetableid = new Integer(req.getParameter("queuetableid").trim());
				QueNoVO queNoVO = new QueNoVO();
				queNoVO.setQueuenum(queuenum);
				queNoVO.setMemphone(memphone);
				queNoVO.setParty(party);
				queNoVO.setQueuenotime(queuenotime);
				queNoVO.setQueueperiodid(queueperiodid);

				queNoVO.setQueuelineno(queuelineno);

				queNoVO.setQueuetableid(queuetableid);

				queNoVO.setStoreid(storeid);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("queNoVO", queNoVO);
					RequestDispatcher failureView = req.getRequestDispatcher(
							req.getContextPath() + "/front-end/queue/queueNo/storePickupNoAndNoCall.jsp");
					failureView.forward(req, res);

					return;
				}
//				System.out.print("有問題？");

				/*************************** 2.開始新增資料 ***************************************/
				// 新增至資料庫
				QueNoService queNoSvc = new QueNoService();
				queNoVO = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
						queuetableid);
				// 顯示桌種預備號碼用
				List<QueNoVO> list = queNoSvc.getQueNoByStoreIdAndTableId(storeid, queuetableid);
//				storeInsert = count;
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				HttpSession session = req.getSession();
//				session.setAttribute("queNoVO", queNoVO);
				req.setAttribute("queNoVO", queNoVO);
				req.setAttribute("queNoVOList", list);
				RequestDispatcher succesView = req
						.getRequestDispatcher("/front-end/queue/queueNo/storePickupNoAndNoCall.jsp");
				succesView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
//				res.sendRedirect((req.getContextPath() + "/front-end/queue/queueNo/storePickupNoAndNoCall.jsp"));
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/queue/queueNo/storePickupNoAndNoCall.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			System.out.println("startinsert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer queuenum = new Integer(req.getParameter("queuenum"));
				String memphone = req.getParameter("memphone").trim();
				if (memphone == null || memphone.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if (!memphone.trim().matches(memphone)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("只能是手機號碼");
				}
				Integer party = null;
				try {
					party = new Integer(req.getParameter("party"));
				} catch (NumberFormatException e) {
					errorMsgs.add("抓不到party");
				}

				Timestamp queuenotime = null;
				try {
					queuenotime = strToTsp(req.getParameter("queuenotime"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("未抓到日期!");
				}
				String storeid = req.getParameter("storeid").trim();
				Integer queueperiodid = new Integer(req.getParameter("queueperiodid").trim());
				Integer queuelineno = new Integer(req.getParameter("queuelineno").trim());
				Integer queuetableid = new Integer(req.getParameter("queuetableid").trim());
				
				QueNoVO queNoVO = new QueNoVO();
				queNoVO.setQueuenum(queuenum);
				queNoVO.setMemphone(memphone);
				queNoVO.setParty(party);
				queNoVO.setQueuenotime(queuenotime);
				queNoVO.setQueueperiodid(queueperiodid);
				queNoVO.setQueuelineno(queuelineno);
				queNoVO.setQueuetableid(queuetableid);
				queNoVO.setStoreid(storeid);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("queNoVO", queNoVO);

					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/queue/queueNo/customerPickupNo.jsp");
					failureView.forward(req, res);
					return;
				}
//				System.out.print("有問題？");

				/*************************** 2.開始新增資料 ***************************************/
				// 新增至資料庫
				QueNoService queNoSvc = new QueNoService();
				queNoVO = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
						queuetableid);
				
				// 顯示桌種預備號碼用
//				List<QueNoVO> list = queNoSvc2.getQueNoByStoreIdAndTableId(storeid, queuetableid);
				QueNoVO queNoVO2 = new QueNoVO();
				queNoVO2 = queNoSvc.getQueNoByPhoneAndStore(memphone, storeid);
				
				QuePeriodService quePeriodSvc = new QuePeriodService();
				List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
//				custInsert = count;
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				HttpSession session = req.getSession();
				session.setAttribute("queNoVO", queNoVO);
				session.setAttribute("queNoVO2", queNoVO2);
				session.setAttribute("quePeriodVO", quePeriodVO);
//				req.setAttribute("queNoVO", queNoVO);
//				req.setAttribute("queNoVOList", list);

				String url = "/front-end/queue/queueNo/showCustomerPickupNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/queue/queueNo/customerPickupNo.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("insert".equals(action)) {
//
//			System.out.println("startinsert");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				count++;
//				Integer queuenum = new Integer(req.getParameter("queuenum"));
//				String memphone = req.getParameter("memphone").trim();
//				if (memphone == null || memphone.trim().length() == 0) {
//					errorMsgs.add("請勿空白");
//				} else if (!memphone.trim().matches(memphone)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("只能是手機號碼");
//				}
//				Integer party = null;
//				try {
//					party = new Integer(req.getParameter("party"));
//				} catch (NumberFormatException e) {
//					errorMsgs.add("抓不到party");
//				}
//
//				Timestamp queuenotime = null;
//				try {
//					queuenotime = strToTsp(req.getParameter("queuenotime"));
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("未抓到日期!");
//				}
//				String storeid = req.getParameter("storeid").trim();
//				Integer queueperiodid = new Integer(req.getParameter("queueperiodid").trim());
//				Integer queuelineno = new Integer(req.getParameter("queuelineno").trim());
//				Integer queuetableid = new Integer(req.getParameter("queuetableid").trim());
//				QueNoVO queNoVO = new QueNoVO();
//				queNoVO.setQueuenum(queuenum);
//				queNoVO.setMemphone(memphone);
//				queNoVO.setParty(party);
//				queNoVO.setQueuenotime(queuenotime);
//				queNoVO.setQueueperiodid(queueperiodid);
//				;
//				queNoVO.setQueuelineno(queuelineno);
//				;
//				queNoVO.setQueuetableid(queuetableid);
//				;
//				queNoVO.setStoreid(storeid);
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("queNoVO", queNoVO);
//
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/queue/queueNo/customerPickupNo.jsp");
//					failureView.forward(req, res);
//					return;
//				}
////				System.out.print("有問題？");
//
//				/*************************** 2.開始新增資料 ***************************************/
//				// 新增至資料庫
//				QueNoService queNoSvc = new QueNoService();
//				queNoVO = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
//						queuetableid);
//				// 顯示桌種預備號碼用
//				QueNoService queNoSvc2 = new QueNoService();
//				List<QueNoVO> list = queNoSvc2.getQueNoByStoreIdAndTableId(storeid, queuetableid);
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				req.setAttribute("queNoVO", queNoVO);
//				req.setAttribute("queNoVOList", list);
//
//				String url = "/front-end/queue/queueNo/showCustomerPickupNo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/queue/queueNo/customerPickupNo.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("delete".equals(action)) {
			String memphone = req.getParameter("memphone");

			QueNoService queNoSvc = new QueNoService();
			queNoSvc.deleteQueNo(memphone);

			String url = "/front-end/queue/queueNo/listAllQueNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	}

	public Timestamp addToTime(Integer num) {	
		Timestamp time2 = new Timestamp(num-8*60*60*1000);
		return time2;
	}
	
	public Timestamp strToTsp(String str) {
		System.out.println("transforming");
		@SuppressWarnings("deprecation")
		Date udate = new Date(str);
		System.out.print(udate);
		long long1 = udate.getTime();
		Timestamp time1 = new Timestamp(long1);
		return time1;
	}

	public int TimeToReset(int hr, int min, int sec) {
		Date date = new Date();
		Integer hrCheck = date.getHours();
		Integer minCheck = date.getMinutes();
		Integer secCheck = date.getSeconds();

		System.out.println(hrCheck);
		System.out.println(minCheck);
		System.out.println(secCheck);
		if (hrCheck.equals(hr) && minCheck.equals(min) && secCheck.equals(sec)) {
			count = 0;
		}
		return count;
	}
}
