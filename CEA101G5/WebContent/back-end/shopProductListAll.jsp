<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productcategory.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ProductService pSvc = new ProductService();
    List<ProductVO> list = pSvc.getAllProducts();
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="pcSvc" scope="page" class="com.productcategory.model.ProductCategoryService"/>
<html>
<head>
<title>所有商品資料</title>

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

<a href="<%=request.getContextPath() %>/back-end/shopProductAddProduct.jsp" class="button">新增商品</a>
<br>
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
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品圖片</th>
		<th>商品描述</th>
		<th>商品原價</th>
		<th>商品售價</th>
		<th>商品銷量</th>
		<th>商品分類</th>
		<th>商品狀態</th>
		<!-- 我是不是要給一個商品照片欄位? -->
		<th>修改</th>
	</tr>
	<c:forEach var="pVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/product.do" style="margin-bottom: 0px;">
			<tr>
				<td>${pVO.productId}</td>
				<td><input type="text" name="productName" value="${pVO.productName}" style="height: 100px; "></td>
				<td><img src="<%=request.getContextPath()%>/shop/productphotoreader.do?productId=${pVO.getProductId()}" class="card-img-top" alt="..." width="100" height="100"></td>
				<td><input type="text" name="productDescription" value="${pVO.productDescription}"></td>
				<td><input type="text" name="productMSRP"  value="${pVO.productMSRP}" style="width: 50px;"/></td>
				<td><input type="text" name="productPrice" value="${pVO.productPrice}" style="width: 50px;"/></td>
				<td>${pVO.productQtySold}</td>
				<td>	
				<select name="categoryId">
						<c:if test="${pVO.categoryId==1}">
							<option value=1 >環保餐具</option>
							<option value=2>美食餐卷</option>
						</c:if>
						<c:if test="${pVO.categoryId==2}">
							<option value=2 >美食餐卷</option>
							<option value=1>環保餐具</option>
						</c:if>
					</select></td>
				<td>
					<select name="productStatus">
						<c:if test="${pVO.productStatus==0}">
							<option value=0>停售中</option>
							<option value=1>上架中</option>
						</c:if>
						<c:if test="${pVO.productStatus==1}">
							<option value=1>上架中</option>
							<option value=0>停售</option>
						</c:if>
						<!-- 有狀態2 = 暫時售完的選項嗎?有的話到時候要加上去 -->
					</select>
				</td>
				<td>
						<input type="submit" value="送出修改	" class="button" id="openp">
					    <input type="hidden" name="action" value="updateProduct">
					    
					    <input type="hidden" name="productId" value="${pVO.productId}">
				    
				</td>
			</tr>
		</FORM>
	</c:forEach>
</table>

</div>
<%@ include file="/front-end/util/page2.file" %>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/shopProductListAllByName.jsp" >
	<b>依商品名稱搜尋:</b>
	<input type="text" name="nameSearch">
	<input type="submit" value="送出" class="button">
</FORM>
</body>
</html>