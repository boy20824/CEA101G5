<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.queuetable.model.*"%>
<%@ page import="java.util.*"%>

<% 
String storeid = request.getParameter("storeid");
pageContext.setAttribute("storeid", storeid);
QueTableService queTableSv = new QueTableService();
List<QueTableVO> list = queTableSv.getStoreQueTable(storeid);
pageContext.setAttribute("list", list);
%>
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

<title>addQueTable.jsp</title>
<style>
.btn{
	background-color: #FA7E23; 
	border-color: #FA7E23;
}
div.label{
	text-align: center;
}
input.inputH{
	height: 40px;
	width: 100px;
}
</style>
</head>
<body bgcolor='white'>
<div class="container">

	<jsp:useBean id="queTableSvc" scope="session"
		class="com.queuetable.model.QueTableService" />
		
<c:forEach var="queTableVO" items="${list}">
	<input class="testValue" value="${queTableVO.queuetableid }" type="hidden">
</c:forEach>
	
		<div class="row reserve1"></div>
		<form METHOD="post" ACTION="queueTable.do" name="form1">
			<div class="form-group">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-3 label">
						<label for="exampleFormControlSelect1"><b>請選擇餐桌類型：</b></label>
					</div>
					<div class="c○l-6">
						<select class="form-control" id="queuetableid" name="queuetableid"
							style="width: 150px; height: 40px;">
							<option value="1">二人桌</option>
							<option value="2">四人桌</option>
							<option value="3">八人桌</option>
							<option value="4">十人桌</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-3 label">
						<label for="exampleInputPassword1"><b>請設定桌數：</b></label>
					</div>
					<div class="c○l-3">
						<div class="form-group">
							<input class="form-control inputH" name="queuetablettl" id="queuetablettl" value="15"
								readonly> <input value="10" onMousemove="showTtlValue()" style="backgourn-color:#FA7123;"
								onMouseup="showUsableValue()" type="range"
								class="form-control-range" id="formControlTtl" min="1" max="30"
								step="1">
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-3 label">
						<label for="exampleInputPassword1"><b>請設定開放桌數：</b></label>
					</div>
					<div class="c○l-6">
						<input class="form-control inputH" name="queuetableusable" id="queuetableusable" value="1"
							readonly> <input onMousemove="showUsableValue()"
							type="range" class="form-control-range" id="formControlUsable"
							min="1" max="1" step="1">

					</div>
				</div>
			</div>
			<div class="row reserve1"></div>
			<input name="storeid" value="${storeid }" hidden="hidden"> 		
			<input
				type="hidden" name="action" value="insert"> 
				<input
				value="送出新增" type="submit" class="btn btn-primary">
		</form>

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

<script>
	function showTtlValue() {
		document.getElementById("queuetablettl").value = $("#formControlTtl")
				.val();
		document.getElementById("formControlUsable").max = $("#formControlTtl")
				.val();
		document.getElementById("formControlUsable").value = $(
				"#formControlTtl").val();
	}
	function showUsableValue() {
		document.getElementById("queuetableusable").value = $(
				"#formControlUsable").val();
	}
</script>
<script>

// $(document).ready(function(){
// 	let numArr = [];
// 	let arr = document.getElementsByClassName("testValue");
// 	for(let i =0; i<arr.length; i++){
// 		numArr.push(arr[i].value);
// 		console.log(numArr);
// 	}
// 	if(numArr!=null){
// 	if(!numArr.includes("1")){
// 		if(!nunArr.includes("2")){
// 			if(!numArr.includes("3")){
				
// 			}
// 		}
// 		console.log("aa");
// 	}else(!numArr.includes("2")){
		
// 	}}
// });
</script>
</html>


