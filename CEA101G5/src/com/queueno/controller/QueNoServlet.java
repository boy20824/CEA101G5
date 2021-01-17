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
import com.restaurant.model.RestaurantService;
import com.restaurant.model.RestaurantVO;
import com.member.model.*;

public class QueNoServlet extends HttpServlet {
	
	
//	Timer timer = new Timer();
//	public void init() {
//		deleteAllNum();
//		
//		Integer dayTime =  15 * 60 * 1000;
//		Date date = new Date(121, 0, 14, 15, 44, 1);
//		TimerTask timerTask = new TimerTask() {
//			
//			@Override
//			public void run() {
//				deleteAllNum();
//				System.out.println("排程開始");
//			}
//		};
//		System.out.println(date);
////		timer.schedule(timerTask,  10*1000);
////		timer.scheduleAtFixedRate(timerTask, new Date(), dayTime);
//		timer.scheduleAtFixedRate(timerTask, date, dayTime);
//	}
//	public void destroy() {
//		timer.cancel();
//	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public synchronized void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		

		// ??��?��?��??
			if ("getQueNo".equals(action)) {
					int num = 0;
//			countSet.add(count);
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					String storeid = req.getParameter("storeid");
					
					QueNoService queNoSvc = new QueNoService();
					List<QueNoVO> queNoVO = queNoSvc.getQueNoByStoreId(storeid);
					if(queNoVO.size()!=0) {
						for(int i = 0; i < queNoVO.size(); i++) {
							if(num <= queNoVO.get(i).getQueuenum())
								num = queNoVO.get(i).getQueuenum()+1;
						}
					}else {
						num = 1;
					}
					
					QueTableService queTableSvc = new QueTableService();
					List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
					QuePeriodService quePeriodSvc = new QuePeriodService();
					List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
					periodCheck(quePeriodVO);//超過最後取號時間不得選取
					// 判斷商家有無新增時段
					if(quePeriodVO.isEmpty()) {
						req.setAttribute("check", "alert");
					}
					
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-store-end/queue/queueNo/customerPickupNo.jsp");
						failureView.forward(req, res);
						return;// 程�?�中?��
					}
					
					HttpSession session = req.getSession();
//				session.setAttribute("pickupNo", ((TreeSet<Integer>) countSet).last());
					session.setAttribute("pickupNo", num);
					// SET �???�store??�list
					session.setAttribute("queTableVO", queTableVO);
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("storeid", storeid);
//					count++;// 計數+1
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
			String storeid = req.getParameter("storeid");
			Integer reset = null;
			try {
				reset = new Integer(req.getParameter("reset"));
			}catch(Exception e){
				reset = null;
			}
			try{
				
			if(reset==1) {
				
				QueTableService queTableSvc = new QueTableService();
				List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
				for(int i = 0; i<queTableVO.size();i++) {
					Integer queuetableusable = queTableVO.get(i).getQueuetablettl();
					Integer queuetableid = queTableVO.get(i).getQueuetableid();
					String queuetabletype = queTableVO.get(i).getQueuetabletype();
					Integer queuetablettl = queTableVO.get(i).getQueuetablettl();
					queTableSvc.updateTable(queuetableid, queuetabletype, storeid, queuetablettl, queuetableusable, 0);
				}
				QueLineService queLineSvc = new QueLineService();
				List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);
				for(int i = 0; i<queLineVO.size(); i++) {
					Integer queuetableid = queTableVO.get(i).getQueuetableid();
					queLineSvc.updateQueLine(queuetableid, 0, storeid, queuetableid);
				}
				QuePeriodService quePeriodSvc = new QuePeriodService();
				List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
				for(int i = 0; i<quePeriodVO.size(); i++) {
					Integer queueperiodid = quePeriodVO.get(i).getQueueperiodid();
					Integer queuest = quePeriodVO.get(i).getQueuest();
					Timestamp queuestarttime = quePeriodVO.get(i).getQueuestarttime();
					Timestamp queueendtime = quePeriodVO.get(i).getQueueendtime();
					quePeriodSvc.updateQuePeriod(queueperiodid, storeid, queuest, queuestarttime, queueendtime, 0);
				}
			}}catch(Exception e){}
			
			int num =0;
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				QuePeriodService quePeriodSvc = new QuePeriodService();
				List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
				periodCheck(quePeriodVO);
				
				QueTableService queTableSvc = new QueTableService();
				List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
				
				QueLineService queLineSvc = new QueLineService();
				List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);
				
				QueNoService queNoSvc = new QueNoService();
				List<QueNoVO> queNoVO = queNoSvc.getQueNoByStoreId(storeid);
//				List<QueNoVO> queNoVO1 = queNoSvc.getQueNoByStoreIdAndTableId(storeid, 1);
//				List<QueNoVO> queNoVO2 = queNoSvc.getQueNoByStoreIdAndTableId(storeid, 2);
//				List<QueNoVO> queNoVO3 = queNoSvc.getQueNoByStoreIdAndTableId(storeid, 3);
//				List<QueNoVO> queNoVO4 = queNoSvc.getQueNoByStoreIdAndTableId(storeid, 4);
				
				
				if(queNoVO.size()!=0) {
					for(int i = 0; i < queNoVO.size(); i++) {
						if(num <= queNoVO.get(i).getQueuenum())
							num = queNoVO.get(i).getQueuenum()+1;
					}
				}else {
					num = 1;
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
					failureView.forward(req, res);
					return;// 程�?�中?��
				}
				HttpSession session = req.getSession();
//				session.setAttribute("pickupNo", ((TreeSet<Integer>) countSet).last());
					session.setAttribute("pickupNo", num);
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("queTableVO", queTableVO);
					session.setAttribute("queLineVO", queLineVO);
					session.setAttribute("queNoVO", queNoVO);
//					session.setAttribute("queNoVO1", queNoVO1);
//					session.setAttribute("queNoVO2", queNoVO2);
//					session.setAttribute("queNoVO3", queNoVO3);
//					session.setAttribute("queNoVO4", queNoVO4);
					session.setAttribute("storeid", storeid);
//					count++;// 計數+1
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
			
			if ("storeInsert".equals(action)) {
				int num = 0;
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
				
				if(queNoVO.size()!=0) {
					for(int i = 0; i < queNoVO.size(); i++) {
						if(num <= queNoVO.get(i).getQueuenum())
							num = queNoVO.get(i).getQueuenum()+1;
					}
				}else {
					num = 1;
				}
				// 用來檢查是否有取過號
				List<String> noList = new ArrayList<String>();
				for(int i = 0; i<queNoVO.size();i++) {
					noList.add(queNoVO.get(i).getMemphone());
				}
				
				MemService memSvc = new MemService();
				List<MemVO> memVO = memSvc.getAll();
				// 用來檢查是否有註冊電話 sweetalert
				List<String> memList = new ArrayList<String>();
				if(queueperiodid!=999) {
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
							queNoVO1 = queNoSvc.addQueNo(num, memphone, party, queuenotime, storeid, queueperiodid, queuelineno, queuetableid);
							System.out.println("新增成功");
						}else {
							// 新增會員-->>新增取號
							memSvc.easyAddMem(memphone, psw, memName);
							queNoVO1 = queNoSvc.addQueNo(num, memphone, party, queuenotime, storeid, queueperiodid, queuelineno, queuetableid);
							req.setAttribute("check", "addNo");
							System.out.println("新增成功");
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
					session.setAttribute("pickupNo", num);
//					req.setAttribute("quePeriodVO", quePeriodVO);
//					req.setAttribute("queTableVO", queTableVO);
//					req.setAttribute("queLineVO", queLineVO);
//					req.setAttribute("queNoVO", queNoVO);
//					req.setAttribute("storeid", storeid);
//					req.setAttribute("pickupNo", count);
//				count++;
//					res.sendRedirect(req.getContextPath()+"/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
					
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
				}else { // 沒新增
					req.setAttribute("check", "stop");
					HttpSession session = req.getSession();
					session.setAttribute("quePeriodVO", quePeriodVO);
					session.setAttribute("queTableVO", queTableVO);
					session.setAttribute("queLineVO", queLineVO);
					session.setAttribute("queNoVO", queNoVO);
					session.setAttribute("storeid", storeid);
					session.setAttribute("pickupNo", num);
//					res.sendRedirect(req.getContextPath()+"/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp");
					String url = "/front-store-end/queue/queueNo/storePickupNoAndNoCall.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
			}
			
			if ("insert".equals(action)) {
				int num =0;
				System.out.println("startinsert");
				/*********************** 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��?? *************************/
				Integer queuenum = new Integer(req.getParameter("queuenum"));
				String memphone = req.getParameter("memphone").trim();
				String memberName = req.getParameter("memberName").trim();
				System.out.println(memberName);
				Integer party = new Integer(req.getParameter("party"));
				Timestamp queuenotime = strToTsp(req.getParameter("queuenotime"));
				String storeid = req.getParameter("storeid").trim();
				Integer queueperiodid = new Integer(req.getParameter("queueperiodid").trim());
				Integer queuelineno = new Integer(req.getParameter("queuelineno").trim());
				Integer queuetableid = new Integer(req.getParameter("queuetableid").trim());
				String psw = "Enak1234";
				QueNoService queNoSvc = new QueNoService();
				// 設定取號號碼
				List<QueNoVO> callNum = queNoSvc.getQueNoByStoreId(storeid);
				if(callNum.size()!=0) {
					for(int i = 0; i < callNum.size(); i++) {
						if(num <= callNum.get(i).getQueuenum())
							num = callNum.get(i).getQueuenum()+1;
					}
				}else {
					num = 1;
				}
				QueNoVO queNoVO = new QueNoVO();
				queNoVO.setQueuenum(queuenum);
				queNoVO.setMemphone(memphone);
				queNoVO.setParty(party);
				queNoVO.setQueuenotime(queuenotime);
				queNoVO.setQueueperiodid(queueperiodid);
				queNoVO.setQueuelineno(queuelineno);
				queNoVO.setQueuetableid(queuetableid);
				queNoVO.setStoreid(storeid);
				/*************************** 2.??��?�新增�?��?? ***************************************/
				// ?��增至資�?�庫
				
				// 顯示桌種??��?��?�碼?��
//				List<QueNoVO> list = queNoSvc2.getQueNoByStoreIdAndTableId(storeid, queuetableid);
//				QueNoVO queNoVO2 = new QueNoVO();
//				queNoVO2 = queNoSvc.getQueNoByPhoneAndStore(memphone, storeid);
				
				QuePeriodService quePeriodSvc = new QuePeriodService();
				QuePeriodVO quePeriodVO = quePeriodSvc.getOneQuePeriod(queueperiodid, storeid);
//				
				// 用來檢查是否有取過號
				List<QueNoVO> numCheck = queNoSvc.getQueNoByStoreId(storeid);
				List<String> noList = new ArrayList<String>();
				for(int i = 0; i<numCheck.size();i++) {
					noList.add(numCheck.get(i).getMemphone());
				}
				
				MemService memSvc = new MemService();
				List<MemVO> memVO = memSvc.getAll();
				// 用來檢查是否有註冊電話
				List<String> memList = new ArrayList<String>();
				for(int i = 0 ; i<memVO.size();i++) {
					memList.add(memVO.get(i).getMemPhone());
				}
				
				// 檢查開始
				if(!noList.contains(memphone)){
					if(memList.contains(memphone)) {
						// 已有會員該店尚未取過號-->>新增取號
						System.out.println("新增成功");
						req.setAttribute("check", "addNo");
						queNoVO = queNoSvc.addQueNo(num, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
								queuetableid);
//						count++;
					}else {
						// 新增會員-->>新增取號
						System.out.println("新增成功");
						memSvc.easyAddMem(memphone, psw, memberName);
						queNoVO = queNoSvc.addQueNo(num, memphone, party, queuenotime, storeid, queueperiodid, queuelineno,
								queuetableid);
						req.setAttribute("check", "addNo");
//						count++;
					}
				/*************************** 3.?��增�?��??,準�?��?�交(Send the Success view) ***********/
				// 預期時間+組數
				Timestamp time1 = new Timestamp(System.currentTimeMillis());
				int queNoAmount = queNoSvc.getQueNoByStoreIdAndTableId(storeid, queuetableid).size();
				Long long2 =(time1).getTime()+ queNoAmount*5*60*1000;
				Timestamp expectTime = new Timestamp(long2);
				
				
				HttpSession session = req.getSession();
				session.setAttribute("memberName", memberName);
				session.setAttribute("queNoVO", queNoVO);
//				session.setAttribute("queNoVO2", queNoVO2);
				session.setAttribute("expectTime", expectTime);
				session.setAttribute("storeid", storeid);
				
//				req.setAttribute("queNoVO", queNoVO);
//				req.setAttribute("queNoVOList", list);
				
				res.sendRedirect((req.getContextPath() + "/front-store-end/queue/queueNo/showCustomerPickupNo.jsp"));
//				String url = "/front-store-end/queue/queueNo/showCustomerPickupNo.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				
				// 取過號碼
				}else {
					HttpSession session = req.getSession();
					session.setAttribute("memberName", memberName);
					session.setAttribute("queNoVO", queNoVO);
//					session.setAttribute("queNoVO2", queNoVO2);
					session.setAttribute("storeid", storeid);
					// 已取過號，沒新增
					req.setAttribute("check", "repeat");
					System.out.println("set");
					String url = "/front-store-end/queue/queueNo/customerPickupNo.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				
				/*************************** ?��他可?��??�錯誤�?��?? **********************************/
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



		if ("delete".equals(action)) {
			String memphone = req.getParameter("memphone");

			QueNoService queNoSvc = new QueNoService();
			queNoSvc.deleteQueNo(memphone);

			String url = "/front-store-end/queue/queueNo/listAllQueNo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("reset".equals(action)) {
			String storeid = req.getParameter("storeid");
			
			QueTableService queTableSvc = new QueTableService();
			List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
			for(int i = 0; i<queTableVO.size();i++) {
				Integer queuetableusable = queTableVO.get(i).getQueuetablettl();
				Integer queuetableid = queTableVO.get(i).getQueuetableid();
				String queuetabletype = queTableVO.get(i).getQueuetabletype();
				Integer queuetablettl = queTableVO.get(i).getQueuetablettl();
				queTableSvc.updateTable(queuetableid, queuetabletype, storeid, queuetablettl, queuetableusable, 0);
			}
			QueLineService queLineSvc = new QueLineService();
			List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);
			for(int i = 0; i<queLineVO.size(); i++) {
				Integer queuelineno = queLineVO.get(i).getQueuelineno();
				Integer queuetableid = queTableVO.get(i).getQueuetableid();
				queLineSvc.updateQueLine(queuelineno, 0, storeid, queuetableid);
			}
			QuePeriodService quePeriodSvc = new QuePeriodService();
			List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
			for(int i = 0; i<quePeriodVO.size(); i++) {
				Integer queueperiodid = quePeriodVO.get(i).getQueueperiodid();
				Integer queuest = quePeriodVO.get(i).getQueuest();
				Timestamp queuestarttime = quePeriodVO.get(i).getQueuestarttime();
				Timestamp queueendtime = quePeriodVO.get(i).getQueueendtime();
				quePeriodSvc.updateQuePeriod(queueperiodid, storeid, queuest, queuestarttime, queueendtime, 0);
			}
//			res.sendRedirect(req.getContextPath()+"/front-store-end/restaurant/WelcomePage.jsp");
			String url = "/front-store-end/queue/queueNo/queueNo.do?&action=storeGetQueNo&storeid="+storeid;
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
				}
			}else {
				quePeriodVO.remove(i);
				i--;
			}
		}
		
	}
	
	// 預期時間+組數
//	public Timestamp addToTime(Long num) {
//		Timestamp time2 = new Timestamp(num+10*60*1000);
//		return time2;
//	}
	
	public void deleteAllNum() {
		QueNoService queNoSvc = new QueNoService();
		List<QueNoVO> queNoVO = queNoSvc.getAll();
		for(int i = 0; i<queNoVO.size(); i++) {
			queNoSvc.deleteQueNo(queNoVO.get(i).getMemphone());
		}
	}
	
	public Timestamp strToTsp(String str) {
		System.out.println("transforming");
		@SuppressWarnings("deprecation")
		Date udate = new Date(str);
		long long1 = udate.getTime();
		Timestamp time1 = new Timestamp(long1);
		System.out.println(time1);
		return time1;
	}

//	public int TimeToReset(int hr, int min, int sec) {
//		Date date = new Date();
//		Integer hrCheck = date.getHours();
//		Integer minCheck = date.getMinutes();
//		Integer secCheck = date.getSeconds();
//
//		System.out.println(hrCheck);
//		System.out.println(minCheck);
//		System.out.println(secCheck);
//		if (hrCheck.equals(hr) && minCheck.equals(min) && secCheck.equals(sec)) {
//			count = 0;
//		}
//		return count;
//	}
}
