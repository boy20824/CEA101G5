<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reservesituation.model.*"%>

<%
    ReserveSituationService rsSvc = new ReserveSituationService();
    List<ReserveSituationVO> list = rsSvc.fors(storeLogin.storeId);
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>

<html>
<head>
<title>訂位狀況資料</title>

<style>
  table#table-1 {
	background-color: #f3853d;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
 	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
     border: 1px solid #404040; 
     border-spacing: 0; 
     background-color: #F6F6F6;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .button{
	  background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
  }
  body{
  	magin:0;
  	padding:0;
  	text-align:center;
/*   	background-image: url("images/r1.jpg"); */
  	background-size: cover;
  	background-attachment: fixed;
  	background-position: center;
  	background-repeat: no-repeat;
  }
  .info{
  	text-align: center;
  	margin: 50px auto;
  }
  .tableborder{
  background-color: white;
  margin:20px;
  }
  #openp{
  background-color: #228b22;
  }

</style>

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="tableborder">
<table class="info">
	<tr>
		<th>日期</th>
		<th>餐廳編號</th>
		<th>用餐時間</th>
		<th>可訂桌數</th>
		<th>已訂桌數</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ReserveSituationVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ReserveSituationVO.reserveSituationDate}</td>
			<td>${ReserveSituationVO.storeId}</td><!-- 缺餐廳model -->
			<td><fmt:formatDate value="${arSvc.getOneAcceptReserve(ReserveSituationVO.storeId,ReserveSituationVO.periodId).startTime}" pattern="HH:mm"/></td>
			<td>${ReserveSituationVO.acceptGroups}</td>
			<td>${ReserveSituationVO.reservedGroups}</td> 
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-customer-end/ReserveSituation/ReserveSituation.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="reserveid"  value="${ReserveSituationVO.reserveId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="page2.file" %>

</body>
</html>