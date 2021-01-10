<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.queueperiod.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	QuePeriodVO quePeriodVO = (QuePeriodVO) request.getAttribute("quePeriodVO"); //EmpServlet.java(Concroller), 存入req的quePeriodVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
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
		<tr>
			<td>
				<h3>ListOneQuePeriod.jsp</h3>
				<h4>
					<a href="Que_Period_Setting.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回設定頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>Queueperiodid</th>
			<th>Storeid</th>
			<th>Queuest</th>
			<th>Queuestarttime</th>
			<th>Queueendtime</th>
			<th>Queuenocurrent</th>
		</tr>
		<tr>
			<td><%=quePeriodVO.getQueueperiodid()%></td>
			<td><%=quePeriodVO.getStoreid()%></td>
			<td><c:choose>
					<c:when test="${quePeriodVO.queuest == 1}">
						<p>可取號</p>
					</c:when>
					<c:otherwise>
						<p>不可取號</p>
					</c:otherwise>
				</c:choose></td>
			<td><fmt:formatDate value="${quePeriodVO.getQueuestarttime()}"
					pattern="yyyy-MM-dd HH:mm" /></td>
			<td><fmt:formatDate value="${quePeriodVO.getQueueendtime()}"
					pattern="yyyy-MM-dd HH:mm" /></td>
			<td><%=quePeriodVO.getQueuenocurrent()%></td>
		</tr>
	</table>

</body>
</html>