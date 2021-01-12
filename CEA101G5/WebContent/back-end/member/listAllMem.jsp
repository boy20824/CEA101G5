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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/storeChar/css/css.css">
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
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemService" />
	<span style="display:inline-block;">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do" >
        <b>輸入會員手機號碼:</b>
        <input type="text" name="memPhone">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn btn-danger">
    </FORM>
    </span>
	<span style="display:inline-block;">
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="memPhone">
         <c:forEach var="memVO" items="${memberSvc.all}" > 
          <option value="${memVO.memPhone}">${memVO.memName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-danger">
     </FORM>
     </span>
     <span style="display:inline-block;">
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/member/mem.do" >
       <b>選擇會員手機號碼:</b>
       <select size="1" name="memPhone">
         <c:forEach var="memVO" items="${memberSvc.all}" > 
          <option value="${memVO.memPhone}">${memVO.memPhone}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-danger">
    </FORM>
     </span>
		<table id="listAllMem" class="table table-striped">
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
				<!--<th>會員權限</th> -->
				<!--<th>儲值餘額</th> -->
				<!--<th>信用卡卡號</th> -->
				<!--<th>信用卡擁有者</th> -->
				<!--<th>信用卡到期日</th> -->
				<!--<th>信用卡CCV</th> -->
				<th>大頭照</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

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
					<td><c:choose>
							<c:when test="${memVO.memCondition == 0}">未驗證會員</c:when>
							<c:when test="${memVO.memCondition == 1}">一般會員</c:when>
							<c:when test="${memVO.memCondition == 2}">進階會員</c:when>
							<c:when test="${memVO.memCondition == 3}">待驗證商家會員</c:when>
							<c:when test="${memVO.memCondition == 4}">商家會員</c:when>
							<c:when test="${memVO.memCondition == 5}">已停權</c:when>
						</c:choose></td>
					<%--<td>${memVO.memAuth}</td> --%>
					<%--<td>${memVO.memTotalRechar}</td> --%>
					<%--<td>${memVO.memCardNumber}</td> --%>
					<%--<td>${memVO.memCardHolder}</td> --%>
					<%--<td>${memVO.memCardExpirationDate}</td> --%>
					<%--<td>${memVO.memCardCCV}</td> --%>
					<td><img width=50 height=50
						src="<%=request.getContextPath()%>/back-end/member/memPhotoReader.do?memPhone=${memVO.memPhone}"></td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/member/mem.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-danger"> 
							<input type="hidden" name="memPhone" value="${memVO.memPhone}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/member/mem.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除" class="btn btn-danger"> 
							<input type="hidden" name="memPhone" value="${memVO.memPhone}">
							<input type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>

</body>

</html>