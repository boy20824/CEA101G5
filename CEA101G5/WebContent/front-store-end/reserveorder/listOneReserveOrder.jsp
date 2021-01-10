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

<html>
<head>
<title>依客戶電話查詢訂位</title>

<style>

  table#table-1 {
	background-color: #f3853d;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
 	width: 900px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
     border: 1px solid #404040; 
     border-spacing: 0; 
     background-color: #F6F6F6;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .button{
	  background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
  }
  body{
  	magin:0;
  	padding:0;
  	text-align:center;
/*   	background-image: url("images/r1.jpg"); */
  	background-size: cover;
  	background-attachment: fixed;
  	background-position: center;
  	background-repeat: no-repeat;
  }
  .info{
  	text-align: center;
  	margin: 50px auto;
  }
  .tableborder{
  background-color: white;
  margin:20px;
  }
</style>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
</head>
<body bgcolor='white'>

<a href="<%=request.getContextPath() %>/front-store-end/reserveorder/listAllReserveOrder.jsp" class="button">回首頁</a>

<div class="tableborder">
<%@ include file="page1.file" %> 
	<table class="info">
	<caption>依<%=paramA %>電話查詢</caption>
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
				     <input type="submit" value="未到店用餐" class="button"><!-- 送出該筆要修改的資料 -->
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
						 <input type="submit" value="店家因素取消" class="button"><!-- 送出該筆要修改的資料 -->
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