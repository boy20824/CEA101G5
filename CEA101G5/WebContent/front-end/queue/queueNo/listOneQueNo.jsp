<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queueno.model.*"%>

<%
    QueNoVO queNoVO = (QueNoVO) request.getAttribute("queNoVO");
%>

<html>
<head>
<title>取號 - listOneQueNo.jsp</title>
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
<td><%=queNoVO.getMemphone() %></td>
<td><%=queNoVO.getQueuenum() %></td>
<td><%=queNoVO.getQueuetableid() %></td>
<td><%=queNoVO.getQueuenotime() %></td>
<td><%=queNoVO.getQueuelineno() %></td>
</tr>
</table>
</body>
</html>