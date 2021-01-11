<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	OrderDetailService odSvc = new OrderDetailService();
    List<OrderDetailVO> list = odSvc.getAllReview();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有商品評論</title>

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

<div class="tableborder">
<%@ include file="/front-end/util/page1.file" %>

<table class="info">
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th><!-- 應該不用弄成商品名稱ㄅ -->
		<th>商品評論內容</th>
		<th>商品評論時間</th>
		<th>顯示狀態</th>
		<!-- 我是不是要給一個評價照片欄位? -->
		<th>送出修改</th>
	</tr>
	<c:forEach var="odVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/orderdetail.do" style="margin-bottom: 0px;">
			<tr>
				<td>${odVO.orderId}</td>
				<td>${odVO.productId}</td>
				<td>${odVO.productReview}</td>
				<td><fmt:formatDate value="${odVO.productReviewTS}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
					<select name="productReviewStatus">
						<c:if test="${odVO.productReviewStatus==0}">
							<option value=0>關閉</option>
							<option value=1>顯示</option>
						</c:if>
						<c:if test="${odVO.productReviewStatus==1}">
							<option value=1>顯示</option>
							<option value=0>關閉</option>
						</c:if>
						<!-- 有狀態2 = 暫時售完的選項嗎?有的話到時候要加上去 -->
					</select>
				</td>
				<td>
						<input type="submit" value="送出修改	" class="button" id="openp">
					    <input type="hidden" name="action" value="updateodr">
					    <input type="hidden" name=orderId value="${odVO.orderId}">
					    <input type="hidden" name="productId" value="${odVO.productId}">
				</td>
			</tr>
		</FORM>
	</c:forEach>
</table>

</div>
<%@ include file="/front-end/util/page2.file" %>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/shopProductReviewListById.jsp" >
	<b>依商品編號搜尋:</b>
	<input type="text" name="nameSearch">
	<input type="submit" value="送出" class="button">
</FORM>
</body>
</html>