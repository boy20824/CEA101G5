package com.reserveschedule;
//package Test;
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.sql.*;
//import com.restaurant.model.*;
//import com.acceptreserve.model.*;
//import com.reservesituation.model.*;
//import java.util.*;
//public class ScheduleServlet2 extends HttpServlet {
//   
//    Timer timer;
//    int i=0;      
//   
//    public void init() throws ServletException {
//      TimerTask task = new TimerTask(){ 
//	        public void run() {
//	        	AcceptReserveService arSvc = new AcceptReserveService();
//	        	List<AcceptReserveVO> arList = arSvc.getSchedule2(1);//取得status=1的storeId & periodId
//	        	ReserveSituationService rsSvc = new ReserveSituationService();
//	        	RestaurantService rSvc = new RestaurantService();
//	        	for (int i = 0; i < arList.size(); i++) {//有幾個status=1的就做幾次
//	        		//以第i筆的storeId & periodId去查詢 位子 拿到最上面一筆的日期
//	        		List<ReserveSituationVO> rsList = rsSvc.schedule2(arList.get(i).getStoreId(),arList.get(i).getPeriodId());
//	        		//將拿到的日期+1天
//	        		java.sql.Date nextDay = new java.sql.Date(rsList.get(0).getReserveSituationDate().getTime() + 24*60*60*1000L);
//	        		//嘗試新增  還缺各餐廳的acceptGroups
//	        		try{
//	        			rsSvc.addReserveSituation(nextDay, arList.get(i).getStoreId(), arList.get(i).getPeriodId(), rSvc.getOneRestaurant(arList.get(i).getStoreId()).getAcceptGroups(), 0);
//	        		} catch(Exception e){}
//        		}
//	        } 
//      };
//      timer = new Timer(); 
//      
//      //2020, Calendar.DECEMBER, 26, 22, 12, 0
//      Calendar cal = new GregorianCalendar();//改成空建構子也就是當下時間
//      timer.scheduleAtFixedRate(task, cal.getTime(), 24*60*60*1000);//1*60*60*1000
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