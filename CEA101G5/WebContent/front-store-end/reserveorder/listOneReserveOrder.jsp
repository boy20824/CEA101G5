<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.reserveorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	String paramA = request.getParameter("phonesearch");
	if (paramA != null){	//第一頁從input來的參數不會=null, 所以存到session裡
		session.setAttribute("paramA", paramA);
	}else{					//第二頁從input來的參數不見了, 所以要取剛剛存到session裡的參數
		paramA = (String) session.getAttribute("paramA");
	}
	List<ReserveOrderVO> list = null;
	ReserveOrderService reserveOrderSvc = new ReserveOrderService();
	list = reserveOrderSvc.getOneReserveOrder(paramA);
	pageContext.setAttribute("list",list);
%>
<%@include file="../selectpage/selectstorepage.jsp" %>

<html>
<head>
<title>依客戶電話查詢訂位</title>

<style>
.orderBlock{
margin-left:300px;
margin-top:100px;
}

.orderBlock a{
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

.button{
 margin: inherit;
 margin: initial;
  marg

</style>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
</head>
<body bgcolor='white'>

<div class="orderBlock">
<a href="<%=request.getContextPath() %>/front-store-end/reserveorder/listAllReserveOrder.jsp" class="button">回預定中的訂位</a>
<%@ include file="page1.file" %> 
	<table class="table table-striped">
	<td colspan="9" style="text-align:center ;background-color: #FF615F; color:white;">依<%=paramA %>電話查詢</td>
		<tr>
			<th>會員電話</th>
			<th>用餐日期</th>
			<th>用餐時間</th>
			<th>用餐人數</th>
			<th>訂位狀態</th>
			<th>特殊需求</th>
			<th>下訂時間</th>
			<th>未到店用餐</th>
			<th>店家因素取消</th>
		</tr>
<c:forEach var="reserveOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${reserveOrderVO.memPhone}</td>
			<td>${reserveOrderVO.reserveTime}</td>
			<td><fmt:formatDate value="${arSvc.getOneAcceptReserve(reserveOrderVO.storeId,reserveOrderVO.periodId).startTime}" pattern="HH:mm"/></td>
			<td>${reserveOrderVO.reserveAdult}</td>
			<td>
				<c:if test="${reserveOrderVO.reserveStatus==0}">
					預訂中
				</c:if>
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
			<td><fmt:formatDate value="${reserveOrderVO.reserveCreateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			<c:if test="${reserveOrderVO.reserveStatus == 0}">
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reserveorder/reserveorder.do" style="margin-bottom: 0px;">
				     <input type="submit" value="未到店用餐" class="button btn btn-danger"><!-- 送出該筆要修改的資料 -->
				     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}">
				     <input type="hidden" name="storeid" value="${reserveOrderVO.storeId}">
				     <input type="hidden" name="memphone" value="${reserveOrderVO.memPhone}">
				     <input type="hidden" name="reservetime" value="${reserveOrderVO.reserveTime}">
				     <input type="hidden" name="periodid" value="${reserveOrderVO.periodId}">
				     <input type="hidden" name="reserveadult" value="${reserveOrderVO.reserveAdult}">
				     <input type="hidden" name="reservechild" value="${reserveOrderVO.reserveChild}">
				     <input type="hidden" name="reservestatus" value="${reserveOrderVO.reserveStatus}">
				     <input type="hidden" name="reservenote" value="${reserveOrderVO.reserveNote}">
				     <input type="hidden" name="reservecreatetime" value="${reserveOrderVO.reserveCreateTime}">
				     
				     <input type="hidden" name="action"	value="getOne_For_Update">
				     <input type="hidden" name="choose"	value="updatefors">
				     <input type="hidden" name="lateorcancel"	value="late">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reserveorder/reserveorder.do" style="margin-bottom: 0px;">
						 <input type="submit" value="店家因素取消" class="button btn btn-danger"><!-- 送出該筆要修改的資料 -->
					     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}">
					     <input type="hidden" name="storeid" value="${reserveOrderVO.storeId}">
					     <input type="hidden" name="memphone" value="${reserveOrderVO.memPhone}">
					     <input type="hidden" name="reservetime" value="${reserveOrderVO.reserveTime}">
					     <input type="hidden" name="periodid" value="${reserveOrderVO.periodId}">
					     <input type="hidden" name="reserveadult" value="${reserveOrderVO.reserveAdult}">
					     <input type="hidden" name="reservechild" value="${reserveOrderVO.reserveChild}">
					     <input type="hidden" name="reservestatus" value="${reserveOrderVO.reserveStatus}">
					     <input type="hidden" name="reservenote" value="${reserveOrderVO.reserveNote}">
					     <input type="hidden" name="reservecreatetime" value="${reserveOrderVO.reserveCreateTime}">
					     <input type="hidden" name="action"	value="getOne_For_Update">
					     <input type="hidden" name="choose"	value="updatefors">
					     <input type="hidden" name="lateorcancel"	value="cancel">
					</FORM>
				</td>
			</c:if>
			<c:if test="${reserveOrderVO.reserveStatus != 0}">
			<td></td>
			<td></td>
			</c:if>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-customer-end/reserveorder/reserveorder.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }

</script>

</body>
</html>