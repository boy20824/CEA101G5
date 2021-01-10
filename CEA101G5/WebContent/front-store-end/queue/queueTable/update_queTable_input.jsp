<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queuetable.model.*"%>

<%
  QueTableVO queTableVO = (QueTableVO) request.getAttribute("queTableVO"); 
%>
<%= queTableVO==null %>--${queTableVO.queuetableid}--
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
		 <h3>update_quetable_input.jsp</h3>
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

<FORM METHOD="post" ACTION="queueTable.do" name="form1">
<table>
	<tr>
		<td>tableid:<font color=red><b>*</b></font></td>
		<td><%=queTableVO.getQueuetableid()%></td>
	</tr>
	<tr>
		<td>tabletype:<font color=red><b>*</b></font></td>
		<td><%=queTableVO.getQueuetabletype()%></td>
	</tr>
	<tr>
		<td>storeid:<font color=red><b>*</b></font></td>
		<td><%=queTableVO.getStoreid()%></td>
	</tr>
	<tr>
		<td>tablettl:</td>
		<td><input type="TEXT" name="queuetablettl" size="45" value="<%=queTableVO.getQueuetablettl()%>" /></td>
	</tr>
	<tr>
		<td>tableusable:</td>
		<td><input type="TEXT" name="queuetableusable" size="45" value="<%=queTableVO.getQueuetableusable()%>" /></td>
	</tr>
	<tr>
		<td>tableocc:</td>
		<td><input type="TEXT" name="queuetableocc" size="45" value="<%=queTableVO.getQueuetableocc()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="queuetableid" value="<%=queTableVO.getQueuetableid()%>">
<input type="hidden" name="storeid" value="<%=queTableVO.getStoreid()%>">
<input type="hidden" name="queuetabletype" value="<%=queTableVO.getQueuetabletype()%>">
<input type="submit" value="修改"></FORM>
</body>
</html>