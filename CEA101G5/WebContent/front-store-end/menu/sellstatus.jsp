<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>

<%
	MenuService menuSvc = new MenuService(); //創建 實體
	List<MenuVO> list = menuSvc.getAllByStoreId(((RestaurantVO)session.getAttribute("storeLogin")).getStoreId());//呼叫DAO並執行getAll()取得VO為每一列資訊的所有欄位並裝入List集合<泛型只能裝取該VO型別>;
	Iterator it = list.iterator();
	while (it.hasNext()) {
		MenuVO m = (MenuVO) it.next();
		if (m.getMenuSellStatus().equals(0)) {
			it.remove();
		}
	}
	pageContext.setAttribute("list", list); //存在JSP的最小Scope命名為list
%>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link href="<%=request.getContextPath()%>/front-store-end/menu/sellstatuscss.css" rel="stylesheet" />

</head>
<body>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<%@include file="../selectpage/selectstorepage.jsp"%>
	<FORM name="form1" method="post" action="<%=request.getContextPath()%>/menu/MenuServlet.do" enctype="multipart/form-data">
	<div class="all">
	<input type="hidden" name="size" value="<%=list.size() %>">
		<div class="title">
			<h1>可售商品管理</h1>
			<input type="submit" value="完成" />
		</div>
		<%@ include file="page1.file"%>
		<!-- include先省略 -->
		<c:forEach var="menuVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<c:if test="${menuVO.menuSellStatus == 1}">
				<div class="row">
					<div class="show">
						<div class="name">${menuVO.menuName}</div>
						<div class="status"${(menuVO.menuStatus=='0')?'style="color:orange"':'' }>${(menuVO.menuStatus=='0')?'開放中':'關閉中' }</div>
					</div>
					<div class="onoffswitch">
						<input type="checkbox" class="onoffswitch-checkbox" id="${menuVO.menuId}" tabindex="2" ${(menuVO.menuStatus=='0')?'checked':'' } />
						<label class="onoffswitch-label" for="${menuVO.menuId}"></label>
						<input type="hidden" value="${menuVO.menuStatus}" name="menuStatus" class="status1">
					</div>
				</div>
				<input type="hidden" name="menuId" value="${menuVO.menuId}" />
				<input type="hidden" name="action" value="updatesellstatus" />
				</FORM>
			</c:if>
		</c:forEach>
	
	
	<%@ include file="page2.file"%>
	<script src="<%=request.getContextPath()%>/front-store-end/menu/sellstatus.js"></script>
</body>
</html>