<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productqa.model.*"%>
<%
    ProductQAService productqaSvc = new ProductQAService();
    List<ProductQAVO> list = productqaSvc.getAllQA();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<title>所有商城問答</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  
<style>
li {
list-style-type:none;
}
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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<a href="<%=request.getContextPath()%>/back-end/productqa/select_productqa_page.jsp" class="btn btn-danger">回首頁</a></button>
<table  class="table  table-striped">
	<tr>
		<th>問與答編號</th>
		<th>商品編號</th>
		<th>會員編號</th>
		<th>顧客提問</th>
		<th>顧客提問時間</th>
		<th>商城回覆</th>
		<th>商城回覆時間</th>
		<th>修改</th>
		<th>回覆</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/util/page1.file" %> 
	<c:forEach var="productQAVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${productQAVO.pqaId}</td>
			<td>${productQAVO.productId}</td>
			<td>${productQAVO.memPhone}</td>
			<td>${productQAVO.productQues}</td>
			<td>${productQAVO.productQuesTstamp}</td>
			<td>${productQAVO.productReply}</td>
			<td>${productQAVO.productReplyTstamp}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/productqa/productqa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-success">
			     <input type="hidden" name="pqaId"  value="${productQAVO.pqaId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/productqa/productqa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="回覆" class="btn btn-info">
			     <input type="hidden" name="pqaId"  value="${productQAVO.pqaId}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/productqa/productqa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-danger">
			     <input type="hidden" name="pqaId"  value="${productQAVO.pqaId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/front-end/util/page2.file" %>
<div align="center">

</div>
</body>
</html>