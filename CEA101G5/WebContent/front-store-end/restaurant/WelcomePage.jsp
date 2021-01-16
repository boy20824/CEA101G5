<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.restaurant.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
RestaurantService restSvc = new RestaurantService();
%>
<%@include file="../selectpage/selectstorepage.jsp" %>
    <html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <script src="https://kit.fontawesome.com/ec3da2c09a.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-store-end/restaurant/css/css.css" />
        <script type="text/javascript" src="js/js.js"></script>
    </head>

    <body>
      
        <div>
        	<img style="margin-left:675px; width:30%;" src="<%=request.getContextPath()%>/front-store-end/restaurant/img/welcomeLogo.png">
        </div>
        <div class="welcomePageBtn">
            <button type="submit" onclick="location.href='<%=request.getContextPath()%>/front-store-end/restaurant/addrestaurant.jsp'">餐廳資訊管理</button>
            <button type="submit" onclick="location.href='<%=request.getContextPath()%>/front-store-end/foodorder/orderlist.jsp'">外帶訂單管理</button>
            <button type="submit" onclick="location.href='<%=request.getContextPath()%>/front-store-end/queue/queueNo/storeWelcome.jsp'">取號管理</button>
            <button type="submit" onclick="location.href='<%=request.getContextPath()%>/front-store-end/acceptreserve/listAllAcceptReserve.jsp'">訂位管理</button>
            <button type="submit" onclick="location.href='<%=request.getContextPath()%>/front-store-end/cashflow/cashflow.jsp'">查看營業金流</button>
        </div>
    </body>
    </html>