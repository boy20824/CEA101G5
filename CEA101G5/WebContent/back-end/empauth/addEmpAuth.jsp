<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.empauth.model.*"%>

<%
	EmpAuthVO eaVO = (EmpAuthVO) request.getAttribute("eaVO");
%>
<%-- <%= eaVO==null %>--${eaVO.deptno}-- --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>權限功能新增</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/empauthcategory/css/css.css">
<title>員工權限資料新增</title>
<style>
.addEmpAuthBlock {
	width: 30%;
	margin-left: 750px;
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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="addEmpAuthBlock">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/back-end/empauth/ea.do"
			name="form1">
			<table class="table table-striped">
				<td class="bg-danger" colspan="2"
					style="color: white; text-align: center;">新增員工權限</td>
				<tr>
					<td>員工編號:</td>
					<jsp:useBean id="empSvc" scope="page"
						class="com.emp.model.EmpService" />
					<td><select size="1" name="emp_id">
							<c:forEach var="empVO" items="${empSvc.all}">
								<option value="${empVO.emp_id}"
									${(empAuthVO.emp_id == empVO.emp_id)? 'selected':'' }>${empVO.emp_name}
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>權限編號:</td>
					<jsp:useBean id="empAuthCateSvc" scope="page"
						class="com.empauthcategory.model.EmpAuthCategoryService" />
					<td><select size="1" name="auth_no">
							<c:forEach var="empAuthCategoryVO" items="${empAuthCateSvc.all}">
								<option value="${empAuthCategoryVO.auth_no}"
									${(empAuthVO.auth_no == empAuthCategoryVO.auth_no)? 'selected':'' }>${empAuthCategoryVO.auth_name}
							</c:forEach>
					</select></td>
				</tr>

			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增" class="btn btn-danger" style="text-align:center;">
		</FORM>
	</div>
</body>



</html>