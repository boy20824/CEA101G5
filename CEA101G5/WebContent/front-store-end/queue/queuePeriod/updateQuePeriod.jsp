<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queueperiod.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% 
String storeid = request.getParameter("storeid"); 
Integer queueperiodid =new Integer(request.getParameter("queueperiodid"));
pageContext.setAttribute("storeid", storeid);
pageContext.setAttribute("queueperiodid", queueperiodid);

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
<title>addQuePeriod.jsp</title>
<style>
.btn{
	background-color: #FA7E23; 
	border-color: #FA7E23;
}
div.label{
	text-align: center;
}
select{
	height: 40px;
	width: 100px;
}
</style>

</head>
<body bgcolor='white'>
	<div class="row reserve1"></div>
<div class="container">

	
		<div class="row reserve1"></div>
		<form METHOD="post" ACTION="queuePeriod.do" name="form1">
			<div class="form-group">
				<div class="row">
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleFormControlSelect1"><b>時段：</b></label>
					</div>
					<div class="c○l-6">
				
					<c:out value="${queueperiodid }"></c:out>

					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleInputPassword1"><b>請設定開放狀態：</b></label>
					</div>
					<div class="c○l-6">
						<div class="form-group">
<input type="radio" id="open" name="queuest" value="1" checked="checked">
  <label for="open">Open</label>
  <input type="radio" id="close" name="queuest" value="0" >
  <label for="close">Close</label>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleInputPassword1"><b>請設定起始時間：</b></label>
					</div>
					<div class="c○l-6">
					<select  class="form-control inputH" style="height:30px;" name="queuestarttime">
						<% int hr = 0; String minEven = ":00"; String minOdd = ":30";%>
						<c:forEach var="i" begin="0" end="47">
						<c:choose>
						<c:when test="${i%2==0 }">
						<option value="${i}"><%=hr+minEven %></option>
						</c:when>
						<c:otherwise>
						<option value="${i}"><%=hr+minOdd %></option>
						<%hr++; %>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						</select>
<!-- 						<input class="form-control inputH" name="queueperiodusable" id="queueperiodusable" value="1" -->
<!-- 							readonly> <input onMousemove="showUsableValue()" -->
<!-- 							type="range" class="form-control inputH-range" id="formControlUsable" -->
<!-- 							min="1" max="1" step="1"> -->

					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-3"></div>
					<div class="col-3">
						<label for="exampleInputPassword1"><b>請設定結束時間：</b></label>
					</div>
					<div class="c○l-6">
					<select  class="form-control inputH" style="height:30px;" name="queueendtime">
						<% int hr2 = 0; String minEven2 = ":00"; String minOdd2 = ":30";%>
						<c:forEach var="i" begin="0" end="47">
						<c:choose>
						<c:when test="${i%2==0 }">
						<option value="${i}"><%=hr2+minEven2 %></option>
						</c:when>
						<c:otherwise>
						<option value="${i}"><%=hr2+minOdd2 %></option>
						<%hr2++; %>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						</select>
<!-- 						<input class="form-control inputH" name="queueperiodusable" id="queueperiodusable" value="1" -->
<!-- 							readonly> <input onMousemove="showUsableValue()" -->
<!-- 							type="range" class="form-control inputH-range" id="formControlUsable" -->
<!-- 							min="1" max="1" step="1"> -->

					</div>
				</div>
			</div>
			<div class="row reserve1"></div>
			<input name="storeid" value="${storeid }" hidden="hidden">
			<input name="queueperiodid" value="${queueperiodid }" type="hidden"> 		
			<input
				type="hidden" name="action" value="update"> 
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
		document.getElementById("queueperiodttl").value = $("#formControlTtl")
				.val();
		document.getElementById("formControlUsable").max = $("#formControlTtl")
				.val();
		document.getElementById("formControlUsable").value = $(
				"#formControlTtl").val();
	}
	function showUsableValue() {
		document.getElementById("queueperiodusable").value = $(
				"#formControlUsable").val();
	}
</script>
</html>


