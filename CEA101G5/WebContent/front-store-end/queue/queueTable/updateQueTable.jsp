<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queuetable.model.*"%>
<%@ page import="java.util.*"%>

<% 
String storeid = request.getParameter("storeid"); 
Integer queuetableid =new Integer(request.getParameter("queuetableid"));
pageContext.setAttribute("storeid", storeid);
pageContext.setAttribute("queuetableid", queuetableid);

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

	
		<div class="row reserve1"></div>
		<form METHOD="post" ACTION="queueTable.do" name="form1">
			<div class="form-group">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-3 label">
						<label for="exampleFormControlSelect1"><b>餐桌類型：</b></label>
					</div>
					<div class="c○l-3">
					<c:choose><c:when test="${queuetableid==1 }">
					<input class="form-control inputH" value="二人桌" readonly>
						</c:when>
						<c:when test="${queuetableid==2 }">
					<input class="form-control inputH" value="四人桌" readonly>
						</c:when>
						<c:when test="${queuetableid==3 }">
					<input class="form-control inputH" value="八人桌" readonly>
						</c:when>
						<c:when test="${queuetableid==4 }">
					<input class="form-control inputH" value="十人桌" readonly>
						</c:when>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-3 label">
						<label for="exampleInputPassword1"><b>請設定桌數：</b></label>
					</div>
					<div class="c○l-6">
						<div class="form-group">
							<input class="form-control inputH" name="queuetablettl" id="queuetablettl" value="15"
								readonly> <input value="10" onMousemove="showTtlValue()"
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
			<input name="queuetableid" value="${queuetableid }" type="hidden"> 		
			<input
				type="hidden" name="action" value="updateTableAmount"> 
				<input
				value="修改" type="submit" class="btn btn-primary">
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
</html>


