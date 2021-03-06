<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productcategory.model.*"%>

<%
	String nameSearch = request.getParameter("nameSearch");
	if (nameSearch != null){	//第一頁從input來的參數不會=null, 所以存到session裡
		session.setAttribute("nameSearch", nameSearch);
	}else{					//第二頁從input來的參數不見了, 所以要取剛剛存到session裡的參數
		nameSearch = (String) session.getAttribute("nameSearch");
	}
	
	//這樣寫是為了 再查詢頁面修改完, 還能保有搜尋條件回到查詢頁面, 而不是回到listAll
	String turn = (String) request.getAttribute("turn");
	if (turn == null){	//如果是第一次才將???轉碼, 如果按下修改經過控制器則不進入if區塊轉碼
		nameSearch = new String(nameSearch.getBytes("ISO-8859-1"),"UTF-8");
	}
	ProductService pSvc = new ProductService();
	List<ProductVO> list = null;
    list = pSvc.getAllByKeywordSearch(nameSearch);
    pageContext.setAttribute("list",list);
%>

<jsp:useBean id="pcSvc" scope="page" class="com.productcategory.model.ProductCategoryService"/>
<html>
<head>
<title>所有商品資料</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/emp/css/css.css">
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
<body>
	<div id="mySidebar" class="sidebar">
		<div>
		<a href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
			<img id="logo" src="<%=request.getContextPath()%>/front-end/shared/logoMain2.png" class="img-circle"
				alt="User Image">
				</a>
		</div>
		<br><br><br><br><br>
		<span id="empFront">首頁<br>
			<a href="#"> FAQ</a>
			<a href="#"> 評論審核</a>
			<a href="#"> 最新消息管理</a>
		</span> 
		<span id="empShop">商城管理<br>
			<a href="<%=request.getContextPath()%>/back-end/shopOrderMasterListAll.jsp">訂單處理</a> 
			<a href="<%=request.getContextPath()%>/back-end/shopProductListAll.jsp"> 商品管理</a>
			<a href="<%=request.getContextPath()%>/back-end/productqa/select_productqa_page.jsp">商品問與答</a>
			<a href="<%=request.getContextPath()%>/back-end/shopProductReviewListAll.jsp">商品評價管理 </a> 
			<a href="<%=request.getContextPath()%>/back-end/shopBackEndPromotion.jsp"> 促銷活動設置</a>
		</span>
		<span id="empStore">餐廳管理<br> 
			<a href="<%=request.getContextPath()%>/back-end/storeChar/listAllStoreChar.jsp">餐廳分類管理</a> 
			<a href="<%=request.getContextPath()%>/back-end/restaurant/listAllStore.jsp"> 餐廳資訊管理 </a>
			<a href="<%=request.getContextPath()%>/back-end/restaurant/storeapply.jsp"> 餐廳申請審核 </a>
		</span> 
		<span id="empMem">會員管理<br> 
			<a href="<%=request.getContextPath()%>/back-end/member/listAllMem.jsp">會員資料管理</a>
			<a href="#">會員儲值管理</a>
		</span>
		<span id="empBack">後台管理<br> 
			<a href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp"> 員工管理 </a>
			<a href="<%=request.getContextPath()%>/back-end/empauthcategory/listAllEmpAuthCategory.jsp">權限管理</a>
		</span>
	</div>
	<div>
		<c:if test="${empty sessionScope.empLogin}">
			<a href="<%=request.getContextPath()%>/back-end/emp/EmpLogin.jsp"
				id="sidebarlogin"> 員工登入/登出 </a>
		</c:if>
		<c:if test="${not empty sessionScope.empLogin}">
			<a href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=logout" id="sidebarlogin">員工登入/登出 </a>
		</c:if>

	</div>
	<div id=backSidebar></div>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<a href="<%=request.getContextPath() %>/back-end/shopProductListAll.jsp" class="button">回所有商品列表</a>
<%@ include file="/front-end/util/page1.file" %>
<div class="tableborder">
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
					<select name="categoryId" class="qq">
						<c:forEach var="pcVO" items="${pcSvc.allCategories}">
							<c:if test="${pVO.categoryId == pcVO.categoryId}">
								<option value="${pcVO.categoryId }" selected>${pcVO.categoryName}</option>
							</c:if>
							<c:if test="${pVO.categoryId != pcVO.categoryId}">
								<option value="${pcVO.categoryId }">${pcVO.categoryName}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
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
					</select>
				</td>
				<td>
						<input type="submit" value="送出修改	" class="button" id="openp">
					    <input type="hidden" name="action" value="updateProduct">
					    <input type="hidden" name="searchyn" value="yes">
					    <input type="hidden" name="productId" value="${pVO.productId}">
				    	<input type="hidden" name="nameSearch" value="<%=nameSearch %>">
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