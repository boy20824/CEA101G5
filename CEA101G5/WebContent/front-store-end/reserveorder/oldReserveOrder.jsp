<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reserveorder.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%  
    ReserveOrderService reserveOrderSvc = new ReserveOrderService();
    List<ReserveOrderVO> list = reserveOrderSvc.getForsold(((RestaurantVO)(session.getAttribute("storeLogin"))).getStoreId(),0);
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
<%@include file="../selectpage/selectstorepage.jsp" %>
<html>
<head>
<title>歷史訂位資訊</title>
<style>
.tableborder{
margin-left:300px;
margin-top:100px;

}
.tableborder a{
	width: 100%;
    background-color: #FF615F;
    color: white;
    padding: 16px 20px;
    margin: 8px 575px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-top:30px;
    
}
.table-striped{
width:80%;
margin-top:50px;
}

</style>

</head>
<body bgcolor='white'>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="tableborder">
<div>
<a href="<%=request.getContextPath() %>/front-store-end/reserveorder/listAllReserveOrder.jsp" class="button">查詢預定中的訂位</a>
<%@ include file="page1.file" %>
</div>
<table class="table table-striped">
	<tr>
	<td colspan="7" style="text-align:center ;background-color: #FF615F; color:white;">歷史訂位資訊</td>
	</tr>
	<tr>
		<th>會員電話</th>
		<th>用餐日期</th>
		<th>用餐時間</th>
		<th>用餐人數</th>
		<th>訂位狀態</th>
		<th>特殊需求</th>
		<th>下訂時間</th>
	</tr>
	
	<c:forEach var="reserveOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${reserveOrderVO.memPhone}</td>
			<td>${reserveOrderVO.reserveTime}</td>
			<td><fmt:formatDate value="${arSvc.getOneAcceptReserve(reserveOrderVO.storeId,reserveOrderVO.periodId).startTime}" pattern="HH:mm"/></td>
			<td>${reserveOrderVO.reserveAdult}</td>
			<!-- JSP會自動幫你setAttribute EL會自動幫你getAttribute -->
<%-- 			<td>${reserveOrderVO.reserveStatus}</td> <!-- 如果要用jsp寫法java.text.DateFormat 要先setAttribute 再get很麻煩 --> --%>
			<td>
				<c:if test="${reserveOrderVO.reserveStatus==1}">
					已結束
				</c:if>
				<c:if test="${reserveOrderVO.reserveStatus==2}">
					已取消
				</c:if>
				<c:if test="${reserveOrderVO.reserveStatus==3}">
					未到店用餐
				</c:if>
				<c:if test="${reserveOrderVO.reserveStatus==4}">
					店家因素取消
				</c:if>
			</td>
			
			<td>${reserveOrderVO.reserveNote}</td>
			<!-- 直接DateTimePicker的formatDate  然後上面要加 tablib fmt 1210 11有講 -->
			<td><fmt:formatDate value="${reserveOrderVO.reserveCreateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-store-end/reserveorder/listOneReserveOrder.jsp" >
        <b>依客戶電話查詢:</b>
        <input type="text" name="phonesearch">
<!--         <input type="hidden" name="action" value="ww"> -->
        <input type="submit" value="送出" class="btn btn-danger">
    </FORM>
</div>
<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }

</script>

</body>
</html>