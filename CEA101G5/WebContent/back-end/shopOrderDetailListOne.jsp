<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%
	String orderId = request.getParameter("orderId");
	if (orderId != null){	//第一頁從input來的參數不會=null, 所以存到session裡
		session.setAttribute("orderId", orderId);
	}else{					//第二頁從input來的參數不見了, 所以要取剛剛存到session裡的參數
		orderId = (String) session.getAttribute("orderId");
	}
	Integer oid = new Integer(orderId);
	OrderDetailService odSvc = new OrderDetailService();
	List<OrderDetailVO> list = null;
    list = odSvc.getAllOrderDetail(oid);
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>依訂單編號查詢明細</title>

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
 	width: 900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
     border: 1px solid #404040; 
     border-spacing: 0; 
     background-color: #F6F6F6;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .button{
	  background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
  }
  body{
  	magin:0;
  	padding:0;
  	text-align:center;
/*   	background-image: url("images/r1.jpg"); */
  	background-size: cover;
  	background-attachment: fixed;
  	background-position: center;
  	background-repeat: no-repeat;
  }
  .info{
  	text-align: center;
  	margin: 50px auto;
  }
  .tableborder{
  background-color: white;
  margin:20px;
  }
</style>

</head>
<body bgcolor='white'>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<a href="<%=request.getContextPath() %>/back-end/shopOrderMasterListAll.jsp" class="button">回商品訂單列表</a>
<%@ include file="/front-end/util/page1.file" %>
<div class="tableborder">
<table class="info">
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>商品售價</th>
		<th>訂購數量</th>
	</tr>
	<c:forEach var="odVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${odVO.orderId}</td>
				<td>${odVO.productId}</td>
				<td>${odVO.productPrice}</td>
				<td>${odVO.quantity}</td>
			</tr>
	</c:forEach>
</table>

</div>
<%@ include file="/front-end/util/page2.file" %>
</body>
</html>