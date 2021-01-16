<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reservesituation.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%
    ReserveSituationService rsSvc = new ReserveSituationService();
    List<ReserveSituationVO> list = rsSvc.fors(((RestaurantVO)(session.getAttribute("storeLogin"))).getStoreId());
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
<%@include file="../selectpage/selectstorepage.jsp" %>
<html>
<head>
<title>訂位狀況資料</title>

<style>

.table-striped{
width:80%;
margin-top:50px;
margin-left:200px;
}

</style>

</head>
<body bgcolor='white'>
<div>
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
<table class="table table-striped" style="text-align:center">
	<tr>
	<td colspan="7" style="text-align:center ;background-color: #FF615F; color:white;">訂位狀況資料</td>
	</tr>
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
</div>
</body>
</html>