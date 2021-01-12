<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.empauth.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
EmpAuthService eaSvc = new EmpAuthService();
List<EmpAuthVO> list = eaSvc.getAll();
pageContext.setAttribute("list", list);
%>

<jsp:useBean id="eacSvc" scope="page" class="com.empauthcategory.model.EmpAuthCategoryService" />

<html>
<head>
<title>所有員工權限</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限功能新增</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/empauthcategory/css/css.css">

<style>
.lsitAllEmpAuth {
	margin-left: 220px;
	width: 80%;
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
	<div class="lsitAllEmpAuth">

		<button class="btn btn-danger">
			<a href="<%=request.getContextPath()%>/back-end/empauth/addEmpAuth.jsp">新增員工權限</a>
		</button>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table class="table  table-striped">
			<tr class="bg-danger">
				<th>員工編號</th>
				<th>權限功能</th>
				<th>修改</th>
				<th>刪除</th>

			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="eaVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${eaVO.emp_id}</td>
					<td>
					<c:forEach var="eacList" items="${eacSvc.all}">
					<c:if test="${eacList.auth_no == eaVO.auth_no}">
			        			<p for="${eacList.auth_no}">${eacList.auth_name}</p></br>
			        </c:if>
			        </c:forEach>
			        </td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/empauth/ea.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-danger"> <input type="hidden"
								name="emp_id" value="${eaVO.emp_id}"> <input
								type="hidden" name="auth_no" value="${eaVO.auth_no}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/empauth/ea.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除" class="btn btn-danger"> <input type="hidden"
								name="emp_id" value="${eaVO.emp_id}"> <input
								type="hidden" name="auth_no" value="${eaVO.auth_no}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>
</html>