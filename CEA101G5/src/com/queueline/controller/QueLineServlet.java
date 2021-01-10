package com.queueline.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.queueline.model.QueLineService;
import com.queueline.model.QueLineVO;

public class QueLineServlet extends HttpServlet {

	// ?��??��?��??
	int count = 0;
	Timer timer = new Timer();

	public void init() {
		Integer dayTime = 24 * 60 * 60 * 1000;
		Date date = new Date(2020, 11, 12 / 22, 10, 20, 1);
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				count = 0;
			}
		};
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

		if ("insert".equals(action)) {
			Integer queuelineno = new Integer(req.getParameter("quelineno"));
			Integer queuenocall = new Integer(req.getParameter("quenocall"));
			String storeid = req.getParameter("storeid");
			Integer queuetableid = new Integer(req.getParameter("quetableid"));

			QueLineVO queLineVO = new QueLineVO();
			queLineVO.setQueuelineno(queuelineno);
			queLineVO.setQueuenocall(queuenocall);
			queLineVO.setStoreid(storeid);
			queLineVO.setQueuetableid(queuetableid);

			QueLineService queLineSvc = new QueLineService();
			queLineVO = queLineSvc.addQueLine(queuelineno, queuenocall, storeid, queuetableid);

			String url = "/front-store-end/queue/queueLine/listAllQueLine.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer quelineno = new Integer(req.getParameter("queuelineno"));
				String storeid = req.getParameter("storeid");
				Integer quetableid = new Integer(req.getParameter("queuetableid"));

				QueLineService QueLineSvc = new QueLineService();
				QueLineVO queLineVO = QueLineSvc.getQueNoCall(quelineno, storeid, quetableid);
				// ?��???
				++count;
				queLineVO.setQueuenocall(count);
				req.setAttribute("queLineVO", queLineVO);
				String url = "/front-store-end/queue/queueLine/update_queLine_input.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("?��法�?��?��?�修?��??��?��??:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueLine/listAllQueLine.jsp");
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

				QueLineService queLineSvc = new QueLineService();
				List<QueLineVO> queLineVO = queLineSvc.getStoreQueNo(storeid);

				req.setAttribute("queLineVO", queLineVO);
				String url = "/front-store-end/queue/queueLine/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("?��法�?��?��?�修?��??��?��??:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueLine/listAllQueLine.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			Integer queuelineno = new Integer(req.getParameter("queuelineno"));
			String storeid = req.getParameter("storeid");
			Integer queuetableid = new Integer(req.getParameter("queuetableid"));

			QueLineService QueLineSvc = new QueLineService();
			QueLineSvc.deleteQueLine(queuelineno, storeid, queuetableid);
			
			
			String url = "/front-store-end/queue/queueLine/listAllQueLine.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("update".equals(action)) {
			Integer queuelineno = new Integer(req.getParameter("queuelineno"));
			String storeid = req.getParameter("storeid");
			Integer queuetableid = new Integer(req.getParameter("queuetableid"));
			Integer queuenocall = new Integer(req.getParameter("queuenocall"));
		
			QueLineService queLineSvc = new QueLineService();
			QueLineVO queLineVO = queLineSvc.updateQueLine(queuelineno, queuenocall, storeid, queuetableid);
			
			req.setAttribute("queLineVO", queLineVO);
			String url = "/front-store-end/queue/queueLine/listAllQueLine.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		
		}

	}

}
