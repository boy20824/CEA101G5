<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.acceptreserve.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
AcceptReserveVO arVO = (AcceptReserveVO) request.getAttribute("arVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>訂位時段資料 - listOneAcceptReserve.jsp</title>

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
		 <h3>時段資料 - ListOneAcceptReserve.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>時段編號</th>
		<th>餐廳編號</th>
		<th>開始時間</th>
		<th>結束時間</th>
		<th>訂位時段狀態</th>
	</tr>
	<tr>
		<td><%=arVO.getPeriodId()%></td>
		<td><%=arVO.getStoreId()%></td>
		<td><%=arVO.getStartTime()%></td>
		<td><%=arVO.getEndTime()%></td>
		<td><%=arVO.getPeriodStatus()%></td>
	</tr>
</table>

</body>
</html>