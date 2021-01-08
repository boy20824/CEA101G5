//package com.emp.controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/login")
//public class login extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	@SuppressWarnings("unused")
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 這是不連資料庫,判斷賬號的存在登入進行簡單的登陸/計算
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String s1 = "admin";
//		String s2 = "123";
//		if (username.equals(s1) && password.equals(s2)) {
//			Cookie cookie = new Cookie("remeberName", username);
//			cookie.setMaxAge(60 * 60 * 24);
//			response.addCookie(cookie);
//			HttpSession session = request.getSession();
//			session.setAttribute("username", username);
//			response.sendRedirect("/MyWeb_04/successful.jsp");
//			request.getRequestDispatcher("/successful.jsp").forward(request, response);
//		} else {
//			request.setAttribute("error", "您的賬號和密碼不匹配，請重新輸入");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
//		}
//		// 通過連線資料庫的賬戶，判斷賬號的存在登入進行簡單的登陸/計算
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		// System.out.println(username + password);
//		// 建立物件UserDao userdao = new UserDao();
//		User user = UserDao.login(username, password);
//		if (user != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("username", username);
//			// response.sendRedirect("/MyWeb_04/successful.jsp");
//			request.getRequestDispatcher("/successful.jsp").forward(request, response);
//		} else {
//			request.setAttribute("error", "您的賬號和密碼不匹配，請重新輸入");
//			request.getRequestDispatcher("/login.jsp").forward(request, response);
//		}
//	}
//}
