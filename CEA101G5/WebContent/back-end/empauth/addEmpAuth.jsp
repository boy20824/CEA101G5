<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.empauth.model.*"%>

<%
	EmpAuthVO eaVO = (EmpAuthVO) request.getAttribute("eaVO");
%>
<%-- <%= eaVO==null %>--${eaVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工權限資料新增 - addEmpAuth.jsp</title>

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
		<tr>
			<td>
				<h3>員工資料新增 - addEmpAuth.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_empauth_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/empauth/ea.do"
		name="form1">
		<table>
			<tr>
				<td>員工編號:</td>
				<td><input type="TEXT" name="emp_id" size="45"
					value="<%=(eaVO == null) ? "EMP0001" : eaVO.getEmp_id()%>" /></td>
			</tr>
			<tr>
				<td>權限編號:</td>
				<jsp:useBean id="empAuthCateSvc" scope="page"
					class="com.empauthcategory.model.EmpAuthCategoryService" />
				<td><select size="1" name="auth_no">
						<c:forEach var="empAuthCategoryVO" items="${empAuthCateSvc.all}">
							<option value="${empAuthCategoryVO.auth_no}"
								${(empAuthVO.auth_no == empAuthCategoryVO.auth_no)? 'selected':'' }>${empAuthCategoryVO.auth_name}
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>



</html>