package com.reserveschedule;
//package Test;
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//import com.reserveorder.model.*;
//import java.util.*;
////每日將訂單狀態0的改成1
//public class ScheduleServlet3 extends HttpServlet {
//   
//    Timer timer;
//    int i=0;      
//   
//    public void init() throws ServletException {
//      TimerTask task = new TimerTask(){ 
//	        public void run() {
//	        	ReserveOrderService roSvc = new ReserveOrderService();
//	        	java.util.Date today = new Date();
//	        	java.sql.Date sqlDate = new java.sql.Date(today.getTime());
//				roSvc.schedule3(sqlDate);
//	        } 
//      };
//      timer = new Timer(); 
//      
//      //2020, Calendar.DECEMBER, 26, 22, 12, 0
//      Calendar cal = new GregorianCalendar(2021, Calendar.JANUARY, 6, 23, 58, 0);//改成空建構子也就是當下時間
//      timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000L);//1*60*60*1000
//      System.out.println("已建立排程更新訂單狀態!");       
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