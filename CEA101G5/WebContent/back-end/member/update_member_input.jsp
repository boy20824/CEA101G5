<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/storeChar/css/css.css">
<title>會員資料修改</title>

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
	<div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/back-end/member/mem.do"
			name="form1" enctype="multipart/form-data">
			<table class="table table-striped"
				style="width: 50%; margin-left: 20%;">
				<td colspan="2" class="bg-danger" style="color: white;">資料修改:</td>
				<tr>
					<td>會員手機號碼: <font
						color=red><b>*</b></font>
					</td>
					<td>${memVO.memPhone}</td>
				</tr>

				<tr>
					<td>密碼:</td>
					<td><input type="password" name="memPwd" size="45"
						value="<%=memVO.getMemPwd()%>" /></td>
				</tr>

				<tr>
					<td>姓名:</td>
					<td><input type="TEXT" name="memName" size="45"
						value="<%=memVO.getMemName()%>" /></td>
				</tr>

				<tr>
					<td>地址:</td>
					<td><input type="TEXT" name="memAddress" size="45"
						value="<%=memVO.getMemAddress()%>" /></td>
				</tr>

				<tr>
					<td>電子信箱:</td>
					<td><input type="TEXT" name="memEmail" size="45"
						value="<%=memVO.getMemEmail()%>" /></td>
				</tr>

				<tr>
					<td>暱稱:</td>
					<td><input type="TEXT" name="memNick" size="45"
						value="<%=memVO.getMemNick()%>" /></td>
				</tr>

				<tr>
					<td>商業營業統一編號:</td>
					<td><input type="TEXT" name="memLice" size="45"
						value="<%=memVO.getMemLice()%>" /></td>
				</tr>

				<tr>
					<td>會員狀態:</td>
					<td><input type="TEXT" name="memCondition" size="45"
						value="<%=memVO.getMemCondition()%>" /></td>
				</tr>

				<tr>
					<td>會員權限:</td>
					<td><input type="TEXT" name="memAuth" size="45"
						value="<%=memVO.getMemAuth()%>" /></td>
				</tr>

				<tr>
					<td>儲值餘額:</td>
					<td><input type="TEXT" name="memTotalRechar" size="45"
						value="<%=memVO.getMemTotalRechar()%>" /></td>
				</tr>
				<tr>
					<td>信用卡卡號:</td>
					<td><input type="TEXT" name="memCardNumber" size="45"
						value="<%=(memVO == null) ? "" : memVO.getMemCardNumber()%>" /></td>
				</tr>
				<tr>
					<td>信用卡姓名:</td>
					<td><input type="TEXT" name="memCardHolder" size="45"
						value="<%=(memVO == null) ? "" : memVO.getMemCardHolder()%>" /></td>
				</tr>
				<tr>
					<td>到期日:</td>
					<td><input type="TEXT" name="memCardExpirationDate" size="45"
						value="<%=(memVO == null) ? "" : memVO.getMemCardExpirationDate()%>" /></td>
				</tr>
				<tr>
					<td>CCV:</td>
					<td><input type="TEXT" name="memCardCCV" size="45"
						value="<%=(memVO == null) ? "" : memVO.getMemCardCCV()%>" /></td>
				</tr>
				<tr>
					<td>大頭照:</td>
					<td><input type="file" name="memPhoto"></td>
				</tr>
				
			</table>
			<div style="margin-left:auto;margin-right:auto; width:50%;">
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="memPhone" value="<%=memVO.getMemPhone()%>">
				<input type="hidden" name="memSex" value="<%=memVO.getMemSex()%>">
				<input type="hidden" name="memIdentity" value="<%=memVO.getMemIdentity()%>"> 
				<input type="hidden" name="memBirth" value="<%=memVO.getMemBirth()%>"> 
				<input type="submit" value="送出修改" class="btn btn-danger">
			</div>
				
		</FORM>
</body>

</html>