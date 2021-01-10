package com.queueperiod.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;
import com.queueperiod.model.*;

public class QuePeriodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		System.out.println(action);

		/*************************** 1.?��?��請�?��?�數 - 輸入?��式�?�錯誤�?��?? **********************/
		if ("getOne_For_Display".equals(action)) {
			
			Integer queueperiodid = new Integer(req.getParameter("queueperiodid"));
			String storeid = req.getParameter("storeid");

			/*************************** 2.??��?�查詢�?��?? *****************************************/
			QuePeriodService quePeriodSvc = new QuePeriodService();
			QuePeriodVO quePeriodVO = quePeriodSvc.getOneQuePeriod(queueperiodid, storeid);
			if (quePeriodVO == null) {
			}
			/*************************** 3.?��詢�?��??,準�?��?�交(Send the Success view) *************/
			req.setAttribute("quePeriodVO", quePeriodVO); // 資�?�庫??�出??�empVO?���?,存入req
			String url = "/quePeriod/listOneQuePeriod.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		if("getOne_Store".equals(action)) {
			String storeid = req.getParameter("storeid");
			System.out.println(req.getParameter("storeid"));
			/*************************** 2.??��?�查詢�?��?? *****************************************/
			QuePeriodService quePeriodSvc = new QuePeriodService();
			List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
			/*************************** 3.?��詢�?��??,準�?��?�交(Send the Success view) *************/
			req.setAttribute("storeid", storeid);
			req.setAttribute("quePeriodVO", quePeriodVO); 
			String url = "/front-store-end/queue/queuePeriod/editQuePeriod.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		//UPDATE
		if ("getOne_For_Update".equals(action)) {
			
			/*************************** 1.?��?��請�?��?�數 ****************************************/
			
			
			Integer queueperiodid = new Integer(req.getParameter("queueperiodid"));
			String storeid = req.getParameter("storeid");
			

			/*************************** 2.??��?�查詢�?��?? ****************************************/
			QuePeriodService quePeriodSvc = new QuePeriodService();
			QuePeriodVO quePeriodVO = quePeriodSvc.getOneQuePeriod(queueperiodid, storeid);
			/*************************** 3.?��詢�?��??,準�?��?�交(Send the Success view) ************/
			
			req.setAttribute("quePeriodVO", quePeriodVO); // 資�?�庫??�出??�empVO?���?,存入req
			String url = "/quePeriod/update_quePeriod_input.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // ??��?��?�交 listOneEmp.jsp
			
			successView.forward(req, res);
		}

		if ("update".equals(action)) {
			List<Integer> timeArrFromJsp = new ArrayList<Integer>();// +毫�?�數?��
			int dayBegin = 0; // 0毫�?��?��?天�?��??
			for(int i = 0; i <48; i++) {
				timeArrFromJsp.add(dayBegin);
				dayBegin += 30*60*1000;
			}
			// ??��?�代�?+毫�?�數?��?���?
			Integer questarttime = new Integer(req.getParameter("queuestarttime"));
			Integer queendtime = new Integer(req.getParameter("queueendtime"));	
			
			Integer queueperiodid = new Integer(req.getParameter("queueperiodid"));
			String storeid = req.getParameter("storeid");
			Integer queuest = new Integer(req.getParameter("queuest"));
			Timestamp queuestarttime = addToTime(timeArrFromJsp.get(questarttime));	
			Timestamp queueendtime = addToTime(timeArrFromJsp.get(queendtime));		
			System.out.println(queuestarttime);
			Integer queuenocurrent = 0;
			
			QuePeriodVO queperiodVO = new QuePeriodVO();
			queperiodVO.setQueueperiodid(queueperiodid);
			queperiodVO.setStoreid(storeid);
			queperiodVO.setQueuest(queuest);
			queperiodVO.setQueuestarttime(queuestarttime);
			queperiodVO.setQueueendtime(queueendtime);
			queperiodVO.setQueuenocurrent(queuenocurrent);


			/*************************** 2.??��?�修?��資�?? *****************************************/
			QuePeriodService quePeriodSvc = new QuePeriodService();
			queperiodVO = quePeriodSvc.updateQuePeriod(queueperiodid, storeid, queuest, queuestarttime, queueendtime,
					queuenocurrent);
			// 轉交?��period編輯??�面
						List<QuePeriodVO> quePeriodVO1 = quePeriodSvc.getOneQuePeriod(storeid);
						
						/*************************** 3.?��增�?��??,準�?��?�交(Send the Success view) ***********/
						req.setAttribute("quePeriodVO", quePeriodVO1);
			/*************************** 3.修改完�??,準�?��?�交(Send the Success view) *************/
			String url = "/front-store-end/queue/queuePeriod/editQuePeriod.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ?��他可?��??�錯誤�?��?? *************************************/

		}

		
		if ("insert".equals(action)) {
			List<Integer> timeArrFromJsp = new ArrayList<Integer>();// +毫�?�數?��
			int dayBegin = 0; // 0毫�?��?��?天�?��??
			for(int i = 0; i <48; i++) {
				timeArrFromJsp.add(dayBegin);
				dayBegin += 30*60*1000;
			}
			// ??��?�代�?+毫�?�數?��?���?
			Integer questarttime = new Integer(req.getParameter("queuestarttime"));
			Integer queendtime = new Integer(req.getParameter("queueendtime"));	
			
			Integer queueperiodid = new Integer(req.getParameter("queueperiodid"));
			String storeid = req.getParameter("storeid");
			Integer queuest = new Integer(req.getParameter("queuest"));
										// ??��?��?                      ??��?�段
			Timestamp queuestarttime = addToTime(timeArrFromJsp.get(questarttime));	
			Timestamp queueendtime = addToTime(timeArrFromJsp.get(queendtime));		
			System.out.println(queuestarttime);
			Integer queuenocurrent = 0;

			
			QuePeriodVO queperiodVO = new QuePeriodVO();
			queperiodVO.setQueueperiodid(queueperiodid);
			queperiodVO.setStoreid(storeid);
			queperiodVO.setQueuest(queuest);
			queperiodVO.setQueuestarttime(queuestarttime);
			queperiodVO.setQueueendtime(queueendtime);
			queperiodVO.setQueuenocurrent(queuenocurrent);
			/*************************** 2.??��?�新增�?��?? ***************************************/

			QuePeriodService quePeriodSvc = new QuePeriodService();
			queperiodVO = quePeriodSvc.addQuePeriod(queueperiodid, storeid, queuest, queuestarttime, queueendtime,
					queuenocurrent);
			// 轉交?��period編輯??�面
			List<QuePeriodVO> quePeriodVO1 = quePeriodSvc.getOneQuePeriod(storeid);
			
			/*************************** 3.?��增�?��??,準�?��?�交(Send the Success view) ***********/
			req.setAttribute("quePeriodVO", quePeriodVO1);
			String url = "/front-store-end/queue/queuePeriod/editQuePeriod.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if("delete".equals(action)) {
			Integer queueperiodid = new Integer(req.getParameter("queueperiodid"));
			String storeid = req.getParameter("storeid");
			
			QuePeriodService quePeriodSvc = new QuePeriodService();
			quePeriodSvc.deleteQuePeriod(queueperiodid, storeid);
			List<QuePeriodVO> quePeriodVO = quePeriodSvc.getOneQuePeriod(storeid);
			req.setAttribute("storeid", storeid);
			req.setAttribute("quePeriodVO", quePeriodVO); 
			
			String url = "/front-store-end/queue/queuePeriod/editQuePeriod.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			
		}
	
	}
	public Timestamp strToTsp2(String str) {
		@SuppressWarnings("deprecation")
		Date udate = new Date(str);
		System.out.print(udate);
		long long1 = udate.getTime();
		Timestamp time1 = new Timestamp(long1);
		return time1;
	}
	
	public Timestamp addToTime(Integer num) {	
		Timestamp time2 = new Timestamp(num-8*60*60*1000);
		return time2;
	}
	
//	public Timestamp strToTsp(String str) {
//		java.sql.Timestamp timestamp = null;
//		try{
//			String strTime = str.trim()+":00";
//			timestamp = java.sql.Timestamp.valueOf(strTime);
//			System.out.println("timestamp"+timestamp);
//		}catch(IllegalArgumentException e){
//			e.printStackTrace();
//		}
//		return timestamp;
//	}
	
}
