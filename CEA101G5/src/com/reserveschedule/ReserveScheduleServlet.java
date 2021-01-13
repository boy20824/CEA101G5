package com.reserveschedule;
//package Test;
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.sql.*;
//import com.reservesituation.model.*;
//import java.util.*;
////每日刪除過去位子  要用的時候設定啟動時間
//public class ScheduleServlet extends HttpServlet {
//   
//    Timer timer;
//    int i=0;      
//   
//    public void init() throws ServletException {
//      TimerTask task = new TimerTask(){ 
//	        public void run() {
//	        	ReserveSituationService rsSvc = new ReserveSituationService();
//	        	java.util.Date today = new Date();
//	        	java.sql.Date sqlDate = new java.sql.Date(today.getTime());
//				rsSvc.deleteByDate(sqlDate);
//	        } 
//      };
//      timer = new Timer(); 
//      
//      //2020, Calendar.DECEMBER, 26, 22, 12, 0
//      Calendar cal = new GregorianCalendar();//改成空建構子也就是當下時間
//      timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000L);//1*60*60*1000
//      System.out.println("已建立排程!");       
//    }
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//                               throws ServletException, IOException {
//    }                           	
//
//    public void destroy() {
//        timer.cancel();
//        System.out.println("已移除排程!");
//    }
//    
//}