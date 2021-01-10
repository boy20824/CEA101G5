<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.productqa.model.*"%>
<%
  ProductQAVO productqaVO = (ProductQAVO) request.getAttribute("productqaVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
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
    text-align: center;
  }
</style>
</head>
<body bgcolor='white'>
<table class="table  table-striped">
	<tr>
		<th>問與答編號</th>
		<th>商品編號</th>
		<th>會員編號</th>
		<th>顧客提問</th>
		<th>顧客提問時間</th>
		<th>商城回覆</th>
		<th>商城回覆時間</th>
		
	</tr>
	<tr>
			<td>${productqaVO.pqaId}</td>
			<td>${productqaVO.productId}</td>
			<td>${productqaVO.memPhone}</td>
			<td>${productqaVO.productQues}</td>
			<td>${productqaVO.productQuesTstamp}</td>
			<td>${productqaVO.productReply}</td>
			<td>${productqaVO.productReplyTstamp}</td> 
	</tr>

	</table>
	<div align="center">
<a href="<%=request.getContextPath()%>/back-end/productqa/select_productqa_page.jsp" class="btn btn-danger">回首頁</a>
</div>
</body>
</html>