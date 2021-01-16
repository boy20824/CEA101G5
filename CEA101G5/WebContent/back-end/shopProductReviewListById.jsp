<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%
	String nameSearch = request.getParameter("nameSearch");
	if (nameSearch != null){	//第一頁從input來的參數不會=null, 所以存到session裡
		session.setAttribute("nameSearch", nameSearch);
	}else{					//第二頁從input來的參數不見了, 所以要取剛剛存到session裡的參數
		nameSearch = (String) session.getAttribute("nameSearch");
	}
	
	//這樣寫是為了 再查詢頁面修改完, 還能保有搜尋條件回到查詢頁面, 而不是回到listAll
	OrderDetailService odSvc = new OrderDetailService();
	List<OrderDetailVO> list = null;
    list = odSvc.getReviewById(nameSearch);
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>依商品編號查詢評論</title>
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
<a href="<%=request.getContextPath() %>/back-end/shopProductReviewListAll.jsp" class="button">回所有評論列表</a>
<%@ include file="/front-end/util/page1.file" %>
<div class="tableborder">
<table class="info">
	<tr>
		<th>訂單編號</th>
		<th>商品編號</th><!-- 應該不用弄成商品名稱ㄅ -->
		<th>商品評論圖片</th>
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
				<td style="width: 250; padding: 10px"><img style="width: 100%; height: auto;"src="<%=request.getContextPath()%>/shop/orderdetailphotoreader.do?&productId=${odVO.getProductId()}&orderId=${odVO.getOrderId()}" class="card-img-top" alt="..." width="100" height="100"></td>
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
					    <input type="hidden" name="searchyn" value="yes">
					    <input type="hidden" name="nameSearch" value="<%=nameSearch %>">
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