package com.queuetable.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.queuetable.model.*;
import com.queueline.model.*;

public class QueTableServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		System.out.println(action);
		
		if("list_One_Store".equals(action)) {
			String storeid = req.getParameter("storeid");
			QueTableService queTableSvc = new QueTableService();
			List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
			
			req.setAttribute("queTableVO", queTableVO);
			req.setAttribute("storeid", storeid);
			String url = "/front-store-end/queue/queueTable/editQueTable.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("getOne_For_Display".equals(action)) {
			Integer quetableid = new Integer(req.getParameter("queuetableid"));
			String storeid = req.getParameter("storeid");
			QueTableService queTableSvc = new QueTableService();
			QueTableVO queTableVO = queTableSvc.getOneQueTable(quetableid, storeid);
			
			req.setAttribute("queTableVO",queTableVO);
			String url = "/front-store-end/queue/queueTable/listOneQueTable.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			Integer queuetableid = new Integer(req.getParameter("queuetableid"));
			String queuetabletype = null;
			// queuetableid?��???1, 2, 3, 4
			switch(queuetableid) {
			case 1:
				queuetabletype = "2P";
				break;
			case 2:
				queuetabletype = "4P";
				break;
			case 3:
				queuetabletype = "8P";
				break;
			case 4:
				queuetabletype = "10P";
				break;
				default:
					break;
				
			}
//			String queuetabletype = req.getParameter("queuetabletype");
			String storeid = req.getParameter("storeid");
			Integer queuetablettl = new Integer(req.getParameter("queuetablettl"));
			Integer queuetableusable = new Integer(req.getParameter("queuetableusable"));
			Integer queuetableocc = queuetablettl - queuetableusable;
			
			// ?��增新桌�??
			QueTableVO queTableVO = new QueTableVO();
			queTableVO.setQueuetableid(queuetableid);
			queTableVO.setQueuetabletype(queuetabletype);
			queTableVO.setStoreid(storeid);
			queTableVO.setQueuetablettl(queuetablettl);
			queTableVO.setQueuetableusable(queuetableusable);
			queTableVO.setQueuetableocc(queuetableocc);
			//??��?�新增�?��?��?��??
			QueLineVO queLineVO = new QueLineVO();
			queLineVO.setStoreid(storeid);
			queLineVO.setQueuelineno(queuetableid);
			queLineVO.setQueuetableid(queuetableid);
			queLineVO.setQueuenocall(0);
			
			QueTableService queTableSvc = new QueTableService();
			queTableVO = queTableSvc.addQueTable(queuetableid, queuetabletype, storeid, queuetablettl, queuetableusable, queuetableocc);
			//轉交?��table編輯??�面
			List<QueTableVO> queTableVO1 = queTableSvc.getStoreQueTable(storeid);
			
			QueLineService queLineSvc = new QueLineService();
			queLineVO = queLineSvc.addQueLine(queuetableid, 0, storeid, queuetableid);
			
			req.setAttribute("queTableVO", queTableVO1);
			String url = "/front-store-end/queue/queueTable/editQueTable.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
//			String url = "/front-store-end/queue/queueTable/select_page.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("重複新增");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueTable/editQueTable.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			Integer quetableid = new Integer(req.getParameter("queuetableid"));
			String storeid = req.getParameter("storeid");
			Integer quelineno = new Integer(req.getParameter("queuetableid"));
			
			QueLineService queLineSvc = new QueLineService();
			queLineSvc.deleteQueLine(quelineno, storeid, quetableid);
			QueTableService queTableSvc = new QueTableService();
			queTableSvc.deleteQueTable(quetableid, storeid);
			
			List<QueTableVO> queTableVO = queTableSvc.getStoreQueTable(storeid);
			req.setAttribute("storeid", storeid);
			req.setAttribute("queTableVO", queTableVO);
			String url = "/front-store-end/queue/queueTable/editQueTable.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("?��?��資�?�失???:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueTable/editQueTable.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
			Integer queuetableid = new Integer(req.getParameter("queuetableid"));
			String storeid = req.getParameter("storeid");
			
			QueTableService queTableSvc = new QueTableService();
			QueTableVO queTableVO = queTableSvc.getOneQueTable(queuetableid, storeid);
			
			req.setAttribute("queTableVO", queTableVO);
			String url = "/front-store-end/queue/queueTable/editQueTable.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
			}catch(Exception e) {
				errorMsgs.add("?��法�?��?��?�修?��??��?��??:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/queue/queueTable/editQueTable.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("updateTableAmount".equals(action)) {
			Integer queuetableid = new Integer(req.getParameter("queuetableid"));
			String queuetabletype = null;
			// queuetableid?��???1, 2, 3, 4
			switch(queuetableid) {
			case 1:
				queuetabletype = "2P";
				break;
			case 2:
				queuetabletype = "4P";
				break;
			case 3:
				queuetabletype = "8P";
				break;
			case 4:
				queuetabletype = "10P";
				break;
				default:
					break;
				
			}
			String storeid = req.getParameter("storeid");
			Integer queuetablettl = new Integer(req.getParameter("queuetablettl"));
			Integer queuetableusable = new Integer(req.getParameter("queuetableusable"));
			Integer queuetableocc = queuetablettl - queuetableusable;
			
			QueTableVO queTableVO = new QueTableVO();
			queTableVO.setQueuetableid(queuetableid);
			queTableVO.setQueuetabletype(queuetabletype);
			queTableVO.setStoreid(storeid);
			queTableVO.setQueuetablettl(queuetablettl);
			queTableVO.setQueuetableusable(queuetableusable);
			queTableVO.setQueuetableocc(queuetableocc);
			
			QueTableService queTableSvc = new QueTableService();
			queTableVO = queTableSvc.updateTable(queuetableid, queuetabletype, storeid, queuetablettl, queuetableusable, queuetableocc);
List<QueTableVO> queTableVO1 = queTableSvc.getStoreQueTable(storeid);
			
			req.setAttribute("queTableVO", queTableVO1);
			String url = "/front-store-end/queue/queueTable/editQueTable.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
//			String url = "/front-store-end/queue/queueTable/select_page.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		
		if("update".equals(action)) {
			Integer queuetableid = new Integer(req.getParameter("queuetableid"));
			String queuetabletype = req.getParameter("queuetabletype");
			String storeid = req.getParameter("storeid");
			Integer queuetablettl = new Integer(req.getParameter("queuetablettl"));
			Integer queuetableusable = new Integer(req.getParameter("queuetableusable"));
			Integer queuetableocc = new Integer(req.getParameter("queuetableocc"));
			
			QueTableVO queTableVO = new QueTableVO();
			queTableVO.setQueuetableid(queuetableid);
			queTableVO.setQueuetabletype(queuetabletype);
			queTableVO.setStoreid(storeid);
			queTableVO.setQueuetablettl(queuetablettl);
			queTableVO.setQueuetableusable(queuetableusable);
			queTableVO.setQueuetableocc(queuetableocc);
			
			QueTableService queTableSvc = new QueTableService();
			queTableVO = queTableSvc.updateTable(queuetableid, queuetabletype, storeid, queuetablettl, queuetableusable, queuetableocc);
			
			String url = "/front-store-end/queue/queueTable/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
