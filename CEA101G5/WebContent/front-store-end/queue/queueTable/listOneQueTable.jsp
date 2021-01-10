<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queuetable.model.*"%>

<%
    QueTableVO queTableVO = (QueTableVO) request.getAttribute("queTableVO");
%>

<html>
<head>
<title>listOneQueTable.jsp</title>
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
<table>
<tr>
<td><%=queTableVO.getQueuetableid() %></td>
<td><%=queTableVO.getQueuetabletype() %></td>
<td><%=queTableVO.getStoreid() %></td>
<td><%=queTableVO.getQueuetablettl() %></td>
<td><%=queTableVO.getQueuetableusable() %></td>
<td><%=queTableVO.getQueuetableocc() %></td>
</tr>
</table>
</body>
</html>