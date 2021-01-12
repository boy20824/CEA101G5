<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemService memSvc = new MemService();
List<MemVO> list = memSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/storeChar/css/css.css">
<!--JQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
</head>
<header id="header" class=""> </header>
<!-- /header -->

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
	<div class="main">

		<table id="listAllMem" class="table table-striped">
			<tr class="bg-danger">
				<th>會員手機</th>
				<th>會員姓名</th>
				<th>會員電子信箱</th>
				<th>會員身份證字號</th>
				<th>統一編號</th>
				<th>大頭照</th>
				<th colspan="2" style="text-align: center">申請狀態</th>

			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<c:if test="${memVO.memLice!=0 && memVO.memCondition == 3}">

					<tr>
						<td>${memVO.memPhone}</td>
						<td>${memVO.memName}</td>
						<td>${memVO.memEmail}</td>
						<td>${memVO.memIdentity}</td>
						<td>${memVO.memLice}</td>
						<td><img width=50 height=50
							src="<%=request.getContextPath()%>/back-end/member/memPhotoReader.do?memPhone=${memVO.memPhone}"></td>
						<td style="text-align: center">

							<FORM METHOD="post" style="margin-bottom: 0px;"
								action="<%=request.getContextPath()%>/back-end/member/mem.do">
								<input type="submit" value="通過申請" class="btn btn-danger"
									id="agreeApply" type="button"> <input type="hidden"
									name="memPhone" value="${memVO.memPhone}"> <input
									type="hidden" name="memLice" value="${memVO.memLice}" />
								<input type="hidden" name="memCondition" value="4"> <input
									type="hidden" name="action" value="updateMemLice">
							</FORM>
						</td>
						<td style="text-align: center">
							<FORM METHOD="post" style="margin-bottom: 0px;"
								action="<%=request.getContextPath()%>/back-end/member/mem.do">
								<input type="submit" value="撤銷申請" class="btn btn-danger"
									id="refuseApply" type="button"> <input type="hidden"
									name="memPhone" value="${memVO.memPhone}"> <input
									type="hidden" name="memLice" value="${memVO.memLice}" />
								<input type="hidden" name="memCondition" value="2"> <input
									type="hidden" name="action" value="updateMemLice">
							</FORM>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>

</html>