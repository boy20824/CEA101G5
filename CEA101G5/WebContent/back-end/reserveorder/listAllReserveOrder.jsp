<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reserveorder.model.*"%>

<%
    ReserveOrderService reserveOrderSvc = new ReserveOrderService();
    List<ReserveOrderVO> list = reserveOrderSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>

<html>
<head>
<title>所有訂位訂單資料</title>

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
 	width: 900px;
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
<%@ include file="page1.file" %>
	<table class="info">
		<tr>
			<th>訂位訂單編號</th>
			<th>餐廳名稱</th>
			<th>會員電話</th>
			<th>訂位日期</th>
			<th>用餐時間</th>
			<th>用餐人數</th>
			<th>訂位狀態</th>
			<th>特殊需求</th>
			<th>下訂時間</th>
		</tr>
		<c:forEach var="reserveOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
			<tr>
				<td>${reserveOrderVO.reserveId}</td>
				<td>${reserveOrderVO.storeId}</td>
				<td>${reserveOrderVO.memPhone}</td>
				<td>${reserveOrderVO.reserveTime}</td>
				<td><fmt:formatDate value="${arSvc.getOneAcceptReserve(reserveOrderVO.storeId,reserveOrderVO.periodId).startTime}" pattern="HH:mm"/></td>
				<td>${reserveOrderVO.reserveAdult}</td> 
							<td>
					<c:if test="${reserveOrderVO.reserveStatus==0}">
						預訂中
					</c:if>
					<c:if test="${reserveOrderVO.reserveStatus==1}">
						已結束
					</c:if>
					<c:if test="${reserveOrderVO.reserveStatus==2}">
						已取消
					</c:if>
					<c:if test="${reserveOrderVO.reserveStatus==3}">
						未到店用餐
					</c:if>
					<c:if test="${reserveOrderVO.reserveStatus==4}">
						店家因素取消
					</c:if>
				</td> <!-- 如果要用jsp寫法java.text.DateFormat 要先setAttribute 再get很麻煩 -->
				<td>${reserveOrderVO.reserveNote}</td><!-- 直接DateTimePicker的formatDate  然後上面要加 tablib fmt 1210 11有講 -->
				<td><fmt:formatDate value="${reserveOrderVO.reserveCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<!-- 			<td> -->
	<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-customer-end/reserveorder/reserveorder.do" style="margin-bottom: 0px;"> --%>
	<!-- 			     <input type="submit" value="刪除"> -->
	<%-- 			     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}"> --%>
	<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
	<!-- 			</td> -->
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="page2.file" %>

</body>
</html>