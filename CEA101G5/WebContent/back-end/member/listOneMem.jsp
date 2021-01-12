<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), 存入req的memVO物件
%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/storeChar/css/css.css">

<style>
.addEmpBlock {
	width: 30%;
	margin-left: 750px;
	margin-top: 20px;
}
</style>
<head>
<title>會員資料</title>
<style>
.addEmpBlock {
	width: 80%;
	margin-left: 220px;
	margin-top: 20px;
}
</style>
</head>
<body>
	<div id="mySidebar" class="sidebar">
		<div>
			<img id="logo" src="<%=request.getContextPath()%>/front-end/shared/logoMain2.png" class="img-circle"
				alt="User Image">
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
			<a href="<%=request.getContextPath()%>/back-end/shopProductReviewListAll.jsp"> 商品評價管理</a>
			<a href="#">廣告設置管理 </a> 
			<a href="#">促銷活動設置 </a>
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
	<div class="addEmpBlock">
	<table class="table table-striped">
		<tr class="bg-danger">
			<th>會員手機</th>
			<th>會員密碼</th>
			<th>會員姓名</th>
			<th>會員地址</th>
			<th>會員性別</th>
			<th>會員電子信箱</th>
			<th>會員身份證字號</th>
			<th>會員生日</th>
			<th>會員暱稱</th>
			<th>統一編號</th>
			<th>會員狀態</th>
			<th>會員權限</th>
			<th>儲值餘額</th>
			<th>信用卡卡號</th>
			<th>信用卡擁有者</th>
			<th>信用卡到期日</th>
			<th>信用卡CCV</th>
			<th>大頭照</th>

		</tr>
		<tr>
			<td>${memVO.memPhone}</td>
			<td>${memVO.memPwd}</td>
			<td>${memVO.memName}</td>
			<td>${memVO.memAddress}</td>
			<td><c:choose>
					<c:when test="${memVO.memSex ==0}">男</c:when>
					<c:otherwise>女</c:otherwise>
				</c:choose></td>
			<td>${memVO.memEmail}</td>
			<td>${memVO.memIdentity}</td>
			<td>${memVO.memBirth}</td>
			<td>${memVO.memNick}</td>
			<td>${memVO.memLice}</td>
			<td>${memVO.memCondition}</td>
			<td>${memVO.memAuth}</td>
			<td>${memVO.memTotalRechar}</td>
			<td>${memVO.memCardNumber}</td>
			<td>${memVO.memCardHolder}</td>
			<td>${memVO.memCardExpirationDate}</td>
			<td>${memVO.memCardCCV}</td>
			<td><img width=50 height=50
				src="<%=request.getContextPath()%>/back-end/member/memPhotoReader.do?memPhone=${memVO.memPhone}"></td>
		</tr>
	</table>
	</div>
</body>

</html>