<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.reserveorder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ReserveOrderVO roVO = (ReserveOrderVO) request.getAttribute("roVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂位訂單資料 - listOneReserveOrder.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneReserveOrder.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂位訂單編號</th>
		<th>餐廳編號</th>
		<th>會員電話</th>
		<th>時段編號</th>
		<th>訂位日期</th>
		<th>大人人數</th>
		<th>小孩人數</th>
		<th>訂位訂單狀態</th>
		<th>備註</th>
		<th>訂位訂單產生時間</th>
	</tr>
	<tr>
		<td><%=roVO.getReserveId()%></td>
		<td><%=roVO.getStoreId()%></td>
		<td><%=roVO.getMemPhone()%></td>
		<td><%=roVO.getPeriodId()%></td>
		<td><%=roVO.getReserveTime()%></td>
		<td><%=roVO.getReserveAdult()%></td>
		<td><%=roVO.getReserveChild()%></td>
		<td><%=roVO.getReserveStatus()%></td>
		<td><%=roVO.getReserveNote()%></td>
		<td><%=roVO.getReserveCreateTime()%></td>
	</tr>
</table>

</body>
</html>