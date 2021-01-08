<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queueline.model.*"%>

<%
  QueLineVO queLineVO = (QueLineVO) request.getAttribute("queLineVO"); 
%>
<%= queLineVO==null %>--${queLineVO.queuelineno}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>update_queline_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>update_queline_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="queueLine.do" name="form1">
<table>
	<tr>
		<td>lineno:<font color=red><b>*</b></font></td>
		<td><%=queLineVO.getQueuelineno()%></td>
	</tr>
	<tr>
		<td>storeid:<font color=red><b>*</b></font></td>
		<td><%=queLineVO.getStoreid()%></td>
	</tr>
	<tr>
		<td>tableid:<font color=red><b>*</b></font></td>
		<td><%=queLineVO.getQueuetableid()%></td>
	</tr>
	<tr>
		<td>nocall:</td>
		<td><input type="TEXT" name="queuenocall" size="45" value="<%=queLineVO.getQueuenocall()%>" /></td>
	</tr>
	
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.queueline.model.QueLineService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="queuelineno" value="<%=queLineVO.getQueuelineno()%>">
<input type="hidden" name="storeid" value="<%=queLineVO.getStoreid()%>">
<input type="hidden" name="queuetableid" value="<%=queLineVO.getQueuetableid()%>">
<input type="submit" value="叫號"></FORM>
</body>
</html>