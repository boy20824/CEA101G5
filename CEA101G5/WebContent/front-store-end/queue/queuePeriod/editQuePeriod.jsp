<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queueperiod.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.restaurant.model.*"%>

<%
QuePeriodService quePeriodSvc = new QuePeriodService(); //創建 實體
RestaurantService restSvc = new RestaurantService();

String storeid = ((RestaurantVO)session.getAttribute("storeLogin")).getStoreId();

	List<QuePeriodVO> list = new ArrayList<QuePeriodVO>();
	list = quePeriodSvc.getOneQuePeriod(storeid);
	// 取出servlet request 再設定pagecontext供查詢
// 	list = (List<QuePeriodVO>) request.getAttribute("quePeriodVO");
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("storeid", storeid);
// 	String storeid = (String) request.getAttribute("storeid");
%>
<%@include file="../sidebar.jsp" %>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap-grid.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap-reboot.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/customerPickupNo.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-store-end/restaurant/css/css.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>editQuePeriod.jsp</title>
<style>
.btn{
	background-color: #FA7E23; 
	border-color: #FA7E23;
}
div.label{
	text-align: center;
}
</style>
</head>
<body bgcolor='white'>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		<div class="row reserve1"></div>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">時段</th>
					<th scope="col">時段起迄</th>
					<th scope="col">取號狀態</th>
					<th scope="col">取號起始時間</th>
					<th scope="col">取號結束時間</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
					int count = 1;// 序列號
				%>
<%-- 				<jsp:useBean id="quePeriodSvc" scope="page" class="com.queueperiod.model.QuePeriodService" /> --%>
<%-- <c:forEach var="quePeriodVO" items="${quePeriodSvc.all}"> --%>
<%-- <c:choose> --%>
<%-- <c:when test="${quePeriodVO.storeid='S000001' }"> --%>
				<c:forEach var="quePeriodVO" items="${list}">
					<tr>
						<td scope="row"><%=count%></td>
						<td><fmt:formatDate value="${quePeriodVO.queuestarttime}"
								pattern="HH:mm" /> ~ <fmt:formatDate
								value="${quePeriodVO.queueendtime}" pattern="HH:mm" /></td>
						<c:choose>
							<c:when test="${quePeriodVO.queuest==1}">
								<td>開放</td>
							</c:when>
							<c:when test="${quePeriodVO.queuest==0}">
								<td>未開放</td>
							</c:when>
						</c:choose>
						<td><fmt:formatDate value="${quePeriodVO.queuestarttime}"
								pattern="HH:mm" /></td>
						<td><fmt:formatDate value="${quePeriodVO.queueendtime}"
								pattern="HH:mm" /></td>
						<td><input id="storeid" name="storeid" value="${storeid }"
							type="hidden"> <input name="queuest"
							value="${quePeriodVO.queuest}" type="hidden"> <input
							name="queuestarttime" value="${quePeriodVO.queuestarttime}"
							type="hidden"> <input name="queueendtime"
							value="${quePeriodVO.queueendtime}" type="hidden"> <input
							id="queueperiodid" class="queueperiodid" name="queueperiodid"
							value="${quePeriodVO.queueperiodid}" type="hidden"> <input
							name="update" value="時段修改" type="button" class="btn btn-primary"></td>
						<td><form method="post" action="queuePeriod.do" style="width:100px;">
						<input id="storeid" name="storeid" value="${storeid }"
							type="hidden"> <input id="queueperiodid"
							class="queueperiodid" name="queueperiodid"
							value="${quePeriodVO.queueperiodid}" type="hidden">
							<input name="action" value="delete" type="hidden">
							<input
							name="delete" value="時段刪除" type="submit" class="btn btn-primary"></form></td>
					</tr>
					<%
						count++;
					%>
<%-- 					</c:when> --%>
<%-- 					</c:choose> --%>
				</c:forEach>
			</tbody>
		</table>
		<div class="row">
			<div class="col-4"></div>
			<input id="storeid4Ajax" value="${storeid }" type="hidden">
			<input id="add" onClick="showAddPage()" name="add" value="新增時段"
				type="button" class="btn btn-primary">
			<div class="col-2"></div>

		</div>

		<div id="showPage"></div>
	</div>
</body>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/jquery-3.4.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/popper.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/customerPickupNo.js"></script>

<script type="text/javascript">
	function showAddPage() {
		var url = "address";
		$.ajax({
			type : "get",
			async : false, //同步请求
			url : "addQuePeriodf.jsp",
			data : {
				storeid : $("#storeid4Ajax").val(),
				queueperiodid :<%=count%>,
			},
			success : function(dates) {
				//alert(dates);
				$("#showPage").html(dates);//要刷新的div
			},
			error : function() {
				// alert("失败，请稍后再试！");
			}
		});
	}
	$("[name='update']").click(function(e) {
		console.log($("#storeid").val());
		console.log($(e.target).prev($(".queueperiodid")).val());
		var url = "address";
		// 		var target = $(e.target);
		$.ajax({
			type : "get",
			async : false, //同步请求
			url : "updateQuePeriod.jsp",
			data : {
				storeid : $("#storeid4Ajax").val(),
				queueperiodid : $(e.target).prev($(".queueperiodid")).val(),

			},
			success : function(dates) {
				//alert(dates);
				$("#showPage").html(dates);//要刷新的div
			},
			error : function() {
				// alert("失败，请稍后再试！");
			}
		});

	});
// 	$("[name='delete']").click(function(e) {
// 		console.log($("#storeid").val());
// 		console.log($(e.target).prev($(".queueperiodid")).val());
// 		var url = "address";
// 		// 		var target = $(e.target);
// 		$.ajax({
// 			type : "get",
// 			async : false, //同步请求
// 			url : "queuePeriod.do",
// 			data : {
// 				action: "delete",
// 				storeid : $("#storeid").val(),
// 				queueperiodid : $(e.target).prev($(".queueperiodid")).val(),

// 			},
// 			success : function(dates) {
// 				//alert(dates);
// 			},
// 			error : function() {
// 				// alert("失败，请稍后再试！");
// 			}
// 		});

// 	});
</script>

</html>


