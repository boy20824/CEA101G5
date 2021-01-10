<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queueno.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	// 取出servlet request 再設定pagecontext供查詢
	List<QueNoVO> list = new ArrayList<QueNoVO>();
	list = (List<QueNoVO>) request.getAttribute("queNoVO");
    pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>listOneStore.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>listOneStore.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>queuenum</th>
		<th>memphone</th>
		<th>party</th>
		<th>queuenotime</th>
		<th>storeid</th>
		<th>queueperiodid</th>		
		<th>queuelineno</th>
		<th>queuetableid</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="queNoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${queNoVO.queuenum}</td>
			<td>${queNoVO.memphone}</td>
			<td>${queNoVO.party}</td>
			<td><fmt:formatDate value="${queNoVO.queuenotime}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td>${queNoVO.storeid}</td>
			<td>${queNoVO.queueperiodid}</td>
			<td>${queNoVO.queuelineno}</td>
			<td>${queNoVO.queuetableid}</td>
			 
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>