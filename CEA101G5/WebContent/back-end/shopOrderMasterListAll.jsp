<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	OrderMasterService omSvc = new OrderMasterService();
    List<OrderMasterVO> list = omSvc.getAllOrders();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有商品訂單</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/emp/css/css.css">
<style>
.main{
width:89%;
margin-left:185px;

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
			<a href="<%=request.getContextPath()%>/back-end/shopBackEndPromotion.jsp"> 促銷活動設置</a>
			<a href="#">廣告設置管理 </a> 
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
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			${message}
		</c:forEach>
</c:if>

<div class="tableborder">
<%@ include file="/front-end/util/page1.file" %>

<table class="table table-striped">
	<tr class="bg-danger">
		<th>訂單編號</th>
		<th>訂單日期</th>
		<th>會員編號</th>
		<th>收貨人姓名</th>
		<th>行動電話</th>
		<th>室內電話</th>
		<th>收貨人電子郵件</th>
		<th>統一編號</th>
		<th>配送方式</th><!-- 0:宅配-實體商品 1:電子郵件寄送-虛擬商品 -->
		<th>配送地址</th>
		<th>訂單備註</th>
		<th>訂單金額</th>
		<th>結帳日期</th>
		<th>配送時間</th>
		<th>訂單狀態</th><!-- 0:等待付款中 1:訂單已確認 2:訂單處理中 3:訂單已出貨 4:取消 -->
		
		<th>送出修改</th>
		<th>查看明細</th>
	</tr>
	<c:forEach var="omVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/ordermaster.do" style="margin-bottom: 0px;">
			<tr>
				<td>${omVO.orderId}</td>
				<td><fmt:formatDate value="${omVO.orderDate}" pattern="yyyy-MM-dd"/></td>
				<td>${omVO.memPhone}</td>
				<td>${omVO.recipientName}</td>
				<td>${omVO.recipientMobNumber}</td>
				<td>${omVO.recipientTelNumber}</td>
				<td>${omVO.recipientEmail}</td>
				<td>${omVO.businessNumber}</td>
				<td>
					<c:if test="${omVO.deliveryMethod==0}">
						宅配
					</c:if>
					<c:if test="${omVO.deliveryMethod==1}">
						電子郵件寄送
					</c:if>
				</td>
				<td>${omVO.deliveryAddress}</td>
				<td>${omVO.orderMemo}</td>
				<td>${omVO.invoicePrice}</td>
				<td><fmt:formatDate value="${omVO.invoicePaidDate}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${omVO.deliveryTime}" pattern="yyyy-MM-dd"/></td>
				
				
				<td>
					<select name="orderStatus">
						<c:if test="${omVO.orderStatus==0}">
							<option value=0 selected>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="${omVO.orderStatus==1}">
							<option value=0>等待付款中</option>
							<option value=1  selected>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="${omVO.orderStatus==2}">
							<option value=0>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2 selected>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="${omVO.orderStatus==3}">
							<option value=0>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3 selected>訂單已出貨</option>
							<option value=4>取消</option>
						</c:if>
						<c:if test="${omVO.orderStatus==4}">
							<option value=0>等待付款中</option>
							<option value=1>訂單已確認</option>
							<option value=2>訂單處理中</option>
							<option value=3>訂單已出貨</option>
							<option value=4 selected>取消</option>
						</c:if>
					</select>
				</td>
				<td>
						<input type="submit" value="送出修改	" class="button">
					    <input type="hidden" name="action" value="updateom">
					    <input type="hidden" name=orderId value="${omVO.orderId}">
				</td>
				</FORM>
				<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/shopOrderDetailListOne.jsp">
					<td>
							<input type="submit" value="查看明細" class="button">
						    <input type="hidden" name="action" value="getDetail">
						    <input type="hidden" name=orderId value="${omVO.orderId}">
					</td>
				</FORM>
			</tr>
		
		
	</c:forEach>
</table>

</div>
<%@ include file="/front-end/util/page2.file" %>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/back-end/shopOrderMasterListOne.jsp" >
	<b>依訂單編號搜尋:</b>
	<input type="text" name="orderId">
	<input type="submit" value="送出" class="button">
</FORM>
</div>
</body>
</html>