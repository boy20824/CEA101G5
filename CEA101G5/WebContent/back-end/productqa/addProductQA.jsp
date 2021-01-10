<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productqa.model.*"%>

<%
  ProductQAVO productqaVO = (ProductQAVO) request.getAttribute("productqaVO");
%>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
 
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
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
<h3 align="center">問與答新增:</h3>
<%-- 錯誤表列 --%>
<div align="center">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
<FORM align="center" METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/productqa/productqa.do" name="form1">
<table  align="center">
<!-- 	<tr> -->
<!-- 		<td>問與答編號:</td> -->
<!-- 		<td><input type="TEXT" name="pqaId" size="45" -->
<%-- 			 value="<%= (productqaVO==null)? "20" : productqaVO.getPqaId()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="productId" size="45"
			 value="<%= (productqaVO==null)? "ENP0006" : productqaVO.getProductId()%>" /></td>
	</tr>
	<tr>
		<td>會員:</td>
		<td><input type="TEXT" name="memPhone" size="45"
			 value="<%= (productqaVO==null)? "0921842851" : productqaVO.getMemPhone()%>" /></td>
	</tr>
	<tr>
		<td>顧客提問:</td>
		<td><input type="TEXT" name="productQues" size="45"
			 value="<%= (productqaVO==null)? "這個有期限嗎" : productqaVO.getProductQues()%>" /></td>
	</tr>
	<tr>
		<td>商城回覆:</td>
		<td><input type="TEXT" name="productReply" size="45"
			 value="<%= (productqaVO==null)? "您好這個商品無使用期限" : productqaVO.getProductReply()%>" /></td>
	</tr>
</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn btn-info"></FORM>
<br><br><br><br><br>
	<div align="center">
<a href="<%=request.getContextPath()%>/back-end/productqa/select_productqa_page.jsp" class="btn btn-danger">回首頁</a></button>
</div>
</body>
</html>