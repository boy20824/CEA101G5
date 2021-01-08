<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.empauth.model.*"%>   
<%
EmpAuthVO eaVO=(EmpAuthVO) request.getAttribute("eaVO");
%>
<%
	EmpAuthService eaSvc = new EmpAuthService();
    List<EmpAuthVO> list = eaSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<title> 員工權限ListOneEmpAuth.jsp</title>
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
    text-align: ceter;
  }
</style>
</head>
<body bgcolor='white'>
	<table id="table-1">
	<tr><td>
		 <h3>員工權限 - ListOneEmpAuth.jsp</h3>
		 <h4><a href="select_empauth_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
	<table>
	<tr>
		<th>員工編號</th>
		<th>權限編號</th>
	</tr>
	
	<tr>
		<td>${eaVO.emp_id}</td>
		<td>${eaVO.auth_no}</td>
	</tr>
</table>

</body>
</html>