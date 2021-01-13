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
import com.member.model.*;

public class QueNoServlet extends HttpServlet {

	static int count = 1;
//	static Set<Integer> countSet = new TreeSet<Integer>();
//	static int custInsert;
//	static int storeInsert;
	Timer timer = new Timer();

	public void init() {
		// reset??��?��?�碼 ??��?��?��??
		Integer dayTime = 60 * 60 * 1000;
		// 設�?��?��?�年???(-1)?��??��?��??
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

		// ??��?��?��??
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
					periodCheck(quePeriodVO);//超過最後取號時間不得選取
					System.out.println(quePeriodVO);
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
						failureView.forward(req, res);
						return;// 程�?�中?��
					}
					HttpSession session = req.getSession();
//				session.setAttribute("pickupNo", ((TreeSet<Integer>) countSet).last());
					session.setAttribute("pickupNo", count);
					// SET �???�store??�list
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("storeid", storeid);
					count++;// 計數+1
//				req.setAttribute("pickupNo", count);
//				req.setAttribute("quePeriodVO", quePeriodVO);
//					res.sendRedirect((req.getContextPath() + "/front-store-end/queue/queueNo/customerPickupNo.jsp"));
					String url = "/front-store-end/queue/queueNo/customerPickupNo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("?��法�?��?��?��??:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
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
				periodCheck(quePeriodVO);
				
				QueTableService queTableSvc = new QueTableService();
				List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
				
				QueLineService queLineSvc = new QueLineService();
				List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);
				
				QueNoService queNoSvc = new QueNoService();
				List<QueNoVO> queNoVO = queNoSvc.getQueNoByStoreId(storeid);
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
					failureView.forward(req, res);
					return;// 程�?�中?��
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
				String url = "/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("?��法�?��?��?��??:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
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
				/*************************** 2.??��?�查詢�?��?? *****************************************/
				QueNoService queNoSvc = new QueNoService();

				List<QueNoVO> quenoVO = queNoSvc.getQueNoByStoreId(storeid);
				if (quenoVO == null) {
					errorMsgs.add("?��?��資�??");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/queue/queueNo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程�?�中?��
				}

				/*************************** 3.?��詢�?��??,準�?��?�交(Send the Success view) *************/
				req.setAttribute("queNoVO", quenoVO); // 資�?�庫??�出??�empVO?���?,存入req
				String url = "/front-store-end/queue/queueNo/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ?��他可?��??�錯誤�?��?? *************************************/
			} catch (Exception e) {
				errorMsgs.add("?��法�?��?��?��??:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/queue/queueNo/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp??��?��??

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��?? **********************/

				Integer queuenum = new Integer(req.getParameter("queuenum"));

				String memphone = req.getParameter("memphone");

				/*************************** 2.??��?�查詢�?��?? *****************************************/

				QueNoService queNoSvc = new QueNoService();
				QueNoVO quenoVO = null;
				if (!queuenum.equals(0)) {
					quenoVO = queNoSvc.getOneQueNo(queuenum, memphone);
				} else {
					quenoVO = queNoSvc.getQueNoByPhone(memphone);
				}
				if (quenoVO == null) {
					errorMsgs.add("?��?��資�??");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/queue/queueNo/listAllQueNo.jsp");
					failureView.forward(req, res);
					return;// 程�?�中?��
				}

				/*************************** 3.?��詢�?��??,準�?��?�交(Send the Success view) *************/
				req.setAttribute("queNoVO", quenoVO); // 資�?�庫??�出??�empVO?���?,存入req
				String url = "/front-store-end/queue/queueNo/listOneQueNo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ??��?��?�交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ?��他可?��??�錯誤�?��?? *************************************/
			} catch (Exception e) {
				errorMsgs.add("?��法�?��?��?��??:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/queue/queueNo/listAllQueNo.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp??��?��??

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.?��?��請�?��?�數 ****************************************/
				Integer queuenum = new Integer(req.getParameter("queueno"));
				String memphone = req.getParameter("memphone");

				/*************************** 2.??��?�查詢�?��?? ****************************************/
				QueNoService queNoSvc = new QueNoService();
				QueNoVO queNoVO = queNoSvc.getOneQueNo(queuenum, memphone);

				/*************************** 3.?��詢�?��??,準�?��?�交(Send the Success view) ************/
				req.setAttribute("queNoVO", queNoVO); // 資�?�庫??�出??�empVO?���?,存入req
				String url = "/queNo/numPickup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ??��?��?�交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ?��他可?��??�錯誤�?��?? **********************************/
			} catch (Exception e) {
				errorMsgs.add("?��法�?��?��?�修?��??��?��??:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/queNo/numPickup.jsp");
				failureView.forward(req, res);
			}
		}

		// ?��詢�?��??+店�??
		if ("getOne_By_Phone_And_Store".equals(action)) {

			String memphone = req.getParameter("memphone").trim();
			String storeid = req.getParameter("storeid").trim();

			QueNoService queNoSvc = new QueNoService();
			QueNoVO queNoVO = queNoSvc.getQueNoByPhoneAndStore(memphone, storeid);

			req.setAttribute("queNoVO", queNoVO);
			String url = "/front-store-end/queue/queueNo/showCustomerPickupNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("storeInsert".equals(action)) {
			System.out.println("startinsert");

//			try {
				/*********************** 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��?? *************************/
				String memName= req.getParameter("memName");
				Integer queuenum = new Integer(req.getParameter("queuenum"));
				String memphone = req.getParameter("memphone").trim();
				Integer party = new Integer(req.getParameter("party").trim());
				Timestamp queuenotime = strToTsp(req.getParameter("queuenotime"));
				String storeid = req.getParameter("storeid").trim();
				Integer queueperiodid = new Integer(req.getParameter("queueperiodid").trim());
				Integer queuelineno = new Integer(req.getParameter("queuelineno").trim());
				Integer queuetableid = new Integer(req.getParameter("queuetableid").trim());
				String psw = "Enak1234";
				
				// 新增用VO
				QueNoVO queNoVO1 = new QueNoVO();
				queNoVO1.setQueuenum(queuenum);
				queNoVO1.setMemphone(memphone);
				queNoVO1.setParty(party);
				queNoVO1.setQueuenotime(queuenotime);
				queNoVO1.setQueueperiodid(queueperiodid);
				queNoVO1.setQueuelineno(queuelineno);
				queNoVO1.setQueuetableid(queuetableid);
				queNoVO1.setStoreid(storeid);
				
//----------------------------------------------------------------------------
//String storeid = req.getParameter("storeid");
				
				QuePeriodService quePeriodSvc = new QuePeriodService();
				List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
				periodCheck(quePeriodVO);//超過最後取號時間不得選取
				
				QueTableService queTableSvc = new QueTableService();
				List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
				
				QueLineService queLineSvc = new QueLineService();
				List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);
				
				QueNoService queNoSvc = new QueNoService();
				List<QueNoVO> queNoVO = queNoSvc.getQueNoByStoreId(storeid);
				// 用來檢查是否有取過號
				List<String> noList = new ArrayList<String>();
				for(int i = 0; i<queNoVO.size();i++) {
					noList.add(queNoVO.get(i).getMemphone());
				}
				
				MemService memSvc = new MemService();
				List<MemVO> memVO = memSvc.getAll();
				// 用來檢查是否有註冊電話
				List<String> memList = new ArrayList<String>();
				for(int i = 0 ; i<memVO.size();i++) {
					memList.add(memVO.get(i).getMemPhone());
				}
				if(noList.contains(memphone)){
					// 已取過號
					req.setAttribute("check", "repeat");
				}else {
					if(memList.contains(memphone)) {
						// 已有會員該店尚未取過號-->>新增取號
						req.setAttribute("check", "addNo");
						queNoVO1 = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno, queuetableid);
						count++;
					}else {
						// 新增會員-->>新增取號
						memSvc.easyAddMem(memphone, psw, memName);
						queNoVO1 = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno, queuetableid);
						req.setAttribute("check", "addNo");
						count++;
					}
				}
//-------------------------------------------------------------------------------
//				System.out.print("??��?��?��??");

				/*************************** 2.??��?�新增�?��?? ***************************************/
				// ?��增至資�?�庫
//				QueNoService queNoSvc = new QueNoService();
				// 顯示桌種??��?��?�碼?��	
//				List<QueNoVO> list = queNoSvc.getQueNoByStoreIdAndTableId(storeid, queuetableid);
//				storeInsert = count;
				/*************************** 3.?��增�?��??,準�?��?�交(Send the Success view) ***********/
//				HttpSession session = req.getSession();
//				session.setAttribute("queNoVO", queNoVO);
				//----------------------------
//				req.setAttribute("queNoVO", queNoVO);
//				req.setAttribute("queNoVOList", list);
//				RequestDispatcher succesView = req
//						.getRequestDispatcher("/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
//				succesView.forward(req, res);
				//----------------------------
//				session.setAttribute("pickupNo", ((TreeSet<Integer>) countSet).last());
				HttpSession session = req.getSession();
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("queTableVO", queTableVO);
					session.setAttribute("queLineVO", queLineVO);
					session.setAttribute("queNoVO", queNoVO);
					session.setAttribute("storeid", storeid);
					session.setAttribute("pickupNo", count);
//					req.setAttribute("quePeriodVO", quePeriodVO);
//					req.setAttribute("queTableVO", queTableVO);
//					req.setAttribute("queLineVO", queLineVO);
//					req.setAttribute("queNoVO", queNoVO);
//					req.setAttribute("storeid", storeid);
//					req.setAttribute("pickupNo", count);
//				count++;
				String url = "/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ?��他可?��??�錯誤�?��?? **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
////				res.sendRedirect((req.getContextPath() + "/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp"));
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) {

			System.out.println("startinsert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��?? *************************/
				Integer queuenum = new Integer(req.getParameter("queuenum"));
				String memphone = req.getParameter("memphone").trim();
				String memberName = req.getParameter("memberName").trim();
				if (memphone == null || memphone.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if (!memphone.trim().matches(memphone)) { // 以�?�練習正???(�?)表示�?(regular-expression)
					errorMsgs.add("?��?��?��??��?��?�碼");
				}
				Integer party = null;
				try {
					party = new Integer(req.getParameter("party"));
				} catch (NumberFormatException e) {
					errorMsgs.add("??��?�到party");
				}

				Timestamp queuenotime = null;
				try {
					queuenotime = strToTsp(req.getParameter("queuenotime"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("?��??�到?��???!");
				}
				String storeid = req.getParameter("storeid").trim();
				Integer queueperiodid = new Integer(req.getParameter("queueperiodid").trim());
				Integer queuelineno = new Integer(req.getParameter("queuelineno").trim());
				Integer queuetableid = new Integer(req.getParameter("queuetableid").trim());
				System.out.println(req.getParameter("queuetableid"));
				
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
							.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
					failureView.forward(req, res);
					return;
				}
//				System.out.print("??��?��?��??");

				/*************************** 2.??��?�新增�?��?? ***************************************/
				// ?��增至資�?�庫
				QueNoService queNoSvc = new QueNoService();
				queNoVO = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
						queuetableid);
				
				// 顯示桌種??��?��?�碼?��
//				List<QueNoVO> list = queNoSvc2.getQueNoByStoreIdAndTableId(storeid, queuetableid);
				QueNoVO queNoVO2 = new QueNoVO();
				queNoVO2 = queNoSvc.getQueNoByPhoneAndStore(memphone, storeid);
				
				QuePeriodService quePeriodSvc = new QuePeriodService();
				List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
//				custInsert = count;
				/*************************** 3.?��增�?��??,準�?��?�交(Send the Success view) ***********/
				
//				HttpSession session = req.getSession();
//				session.setAttribute("memberName", memberName);
//				session.setAttribute("queNoVO", queNoVO);
//				session.setAttribute("queNoVO2", queNoVO2);
//				session.setAttribute("quePeriodVO", quePeriodVO);
				
//				req.setAttribute("queNoVO", queNoVO);
//				req.setAttribute("queNoVOList", list);

				res.sendRedirect((req.getContextPath() + "/front-store-end/queue/queueNo/showCustomerPickupNo.jsp"));
//				String url = "/front-store-end/queue/queueNo/showCustomerPickupNo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);

				/*************************** ?��他可?��??�錯誤�?��?? **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
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
//				/*********************** 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��?? *************************/
//				count++;
//				Integer queuenum = new Integer(req.getParameter("queuenum"));
//				String memphone = req.getParameter("memphone").trim();
//				if (memphone == null || memphone.trim().length() == 0) {
//					errorMsgs.add("請勿空白");
//				} else if (!memphone.trim().matches(memphone)) { // 以�?�練習正???(�?)表示�?(regular-expression)
//					errorMsgs.add("?��?��?��??��?��?�碼");
//				}
//				Integer party = null;
//				try {
//					party = new Integer(req.getParameter("party"));
//				} catch (NumberFormatException e) {
//					errorMsgs.add("??��?�到party");
//				}
//
//				Timestamp queuenotime = null;
//				try {
//					queuenotime = strToTsp(req.getParameter("queuenotime"));
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("?��??�到?��???!");
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
//							.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
//					failureView.forward(req, res);
//					return;
//				}
////				System.out.print("??��?��?��??");
//
//				/*************************** 2.??��?�新增�?��?? ***************************************/
//				// ?��增至資�?�庫
//				QueNoService queNoSvc = new QueNoService();
//				queNoVO = queNoSvc.addQueNo(queuenum, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
//						queuetableid);
//				// 顯示桌種??��?��?�碼?��
//				QueNoService queNoSvc2 = new QueNoService();
//				List<QueNoVO> list = queNoSvc2.getQueNoByStoreIdAndTableId(storeid, queuetableid);
//				/*************************** 3.?��增�?��??,準�?��?�交(Send the Success view) ***********/
//				req.setAttribute("queNoVO", queNoVO);
//				req.setAttribute("queNoVOList", list);
//
//				String url = "/front-store-end/queue/queueNo/showCustomerPickupNo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/*************************** ?��他可?��??�錯誤�?��?? **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
//				failureView.forward(req, res);
//			}
//		}

		if ("delete".equals(action)) {
			String memphone = req.getParameter("memphone");

			QueNoService queNoSvc = new QueNoService();
			queNoSvc.deleteQueNo(memphone);

			String url = "/front-store-end/queue/queueNo/listAllQueNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
	}
	
	public void periodCheck(List<QuePeriodVO> quePeriodVO) {
		for(int i = 0; i < quePeriodVO.size();i++) {
			Integer hr = new Date().getHours();
			Integer min = new Date().getMinutes();
			Integer checkhr = quePeriodVO.get(i).getQueueendtime().getHours();
			Integer checkmin = quePeriodVO.get(i).getQueueendtime().getMinutes();
			if(quePeriodVO.get(i).getQueuest()==1) {
			if(hr>checkhr) {
				quePeriodVO.remove(i);
				i--;
			}else if(hr==checkhr&&min>checkmin) {
				quePeriodVO.remove(i);
				i--;
			}}
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
