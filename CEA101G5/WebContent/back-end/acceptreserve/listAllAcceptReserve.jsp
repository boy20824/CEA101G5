<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.acceptreserve.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AcceptReserveService arSvc = new AcceptReserveService();
    List<AcceptReserveVO> list = arSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有訂位時段資料</title>

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
		<th>時段編號</th>
		<th>餐廳名稱</th>
		<th>開始時間</th>
		<th>結束時間</th>
		<th>訂位時段狀態</th>
	</tr>
	<c:forEach var="arVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${arVO.periodId}</td>
			<td>${arVO.storeId}</td>
			<td><fmt:formatDate value="${arVO.startTime}" pattern="HH:mm:ss"/></td>
			<td><fmt:formatDate value="${arVO.endTime}" pattern="HH:mm:ss"/></td>
			<td>
				<c:if test="${arVO.periodStatus==0}">
					<span>關閉中</span>
				</c:if>
				<c:if test="${arVO.periodStatus==1}">
					<span>開放中</span>
				</c:if>
			</td> <!-- JSP會自動幫你setAttribute EL會自動幫你getAttribute -->
										 <!-- 如果要用jsp寫法java.text.DateFormat 要先setAttribute 再get很麻煩 -->
										 <!-- 直接DateTimePicker的formatDate  然後上面要加 tablib fmt 1210 11有講 -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/reserveorder/reserveorder.do" style="margin-bottom: 0px;"> --%>
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