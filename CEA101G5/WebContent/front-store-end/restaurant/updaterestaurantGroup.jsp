<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>
<%
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restaurantVO");
%>
<%@include file="../selectpage/selectstorepage.jsp" %>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="https://kit.fontawesome.com/ec3da2c09a.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-store-end/restaurant/css/css.css" />
<script type="text/javascript" src="js/js.js"></script>

<!--JQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<style>
.button{
margin-left:320px;
}

.addStorecontainer{
width:500px;
}
</style>
</head>

<body>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/restaurant/restaurant.do"
		name="form1" enctype="multipart/form-data">
		<div class="addStorecontainer">
			<div class="row">
				<div class="col" id="addTextBlock">
					<div class="col-10">
						<label for="storeFinalReservDate">最終可訂位天數</label> 
						 <select size="1" name="storeFinalReservDate" style="width:200px">
							<c:forEach var="storeFinalReservDatelist"  begin="1" end="60" step="1">
								<option value="${storeFinalReservDatelist}"
									${(storeLogin.storeFinalReservDate==restaurantVO.storeFinalReservDate)? 'selected':'' }>${storeFinalReservDatelist}
							</c:forEach>
						</select>
						<p>目前最終訂位日期 : ${storeLogin.storeFinalReservDate} 天後</p>
					</div>


					<div class="col-10">
						<label for="acceptGroups">可接受訂位組數</label> 
						 <select size="1" name="acceptGroups" style="width:200px">
							<c:forEach var="acceptGroupslist"  begin="1" end="60" step="1">
								<option value="${acceptGroupslist}"
									${(storeLogin.acceptGroups==restaurantVO.acceptGroups)? 'selected':'' }>${acceptGroupslist}
							</c:forEach>
						</select>
						<p>可接受訂位組數 : ${storeLogin.acceptGroups} 組</p>
					</div>

				</div>
			</div>
			<div class="col">
				<div class="button">
					<input type="hidden" name="storeId" value="${storeLogin.storeId}"> 
					<input type="hidden" name="action" value="updateGroup"> 
					<input type="submit" value="送出修改" /> <input type="reset" value="取消" />

				</div>
			</div>
		</div>

	</FORM>

</body>
</html>