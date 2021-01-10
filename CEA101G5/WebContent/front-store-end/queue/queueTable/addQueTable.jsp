<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queuetable.model.*"%>
<%@ page import="java.util.*"%>

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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>addQueTable.jsp</title>


</head>
<body bgcolor='white'>
<div class="container">

	<jsp:useBean id="queTableSvc" scope="session"
		class="com.queuetable.model.QueTableService" />

	
		<div class="row reserve"></div>
		<form METHOD="post" ACTION="queueTable.do" name="form1">
			<div class="form-group">
				<div class="row">
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleFormControlSelect1"><b>請選擇餐桌類型：</b></label>
					</div>
					<div class="c○l-6">
						<select class="form-control" id="queuetableid" name="queuetableid"
							style="width: 280px">
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
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleInputPassword1"><b>請設定桌數：</b></label>
					</div>
					<div class="c○l-6">
						<div class="form-group">
							<input class="form-control" name="queuetablettl" id="queuetablettl" value="15"
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
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleInputPassword1"><b>請設定開放桌數：</b></label>
					</div>
					<div class="c○l-6">
						<input class="form-control" name="queuetableusable" id="queuetableusable" value="1"
							readonly> <input onMousemove="showUsableValue()"
							type="range" class="form-control-range" id="formControlUsable"
							min="1" max="1" step="1">

					</div>
				</div>
			</div>
			<div class="row reserve1"></div>
			<input name="storeid" value="S000001" hidden="hidden"> 		
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
</html>


