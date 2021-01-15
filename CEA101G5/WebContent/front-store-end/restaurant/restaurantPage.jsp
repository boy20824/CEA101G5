<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restVO");
	
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

<!--JQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/front-store-end/restaurant/js/js.js"></script>

</head>

<body>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/restaurant/restaurant.do"
		enctype="multipart/form-data">
		<div class="addStorecontainer">
			<div class="row">
				<div class="col-2">
					<div class="preview"><img style="width: 160px; height: 150px;"
						src="<%=request.getContextPath()%>/back-end/restaurantpicture/restaurantPicture.do?storeId=${storeLogin.storeId}&action=getFirst_For_Display"></div>
				</div>
				<div class="col" id="addTextBlock">
					<div class="col-10">
						<label for="storeName">餐廳名稱</label> <input type="text"
							id="storeName" name="storeName" value="${storeLogin.storeName}" />
					</div>

					<jsp:useBean id="storeCharSvc" scope="page"
						class="com.storechardetail.model.StoreCharDetailService" />
					<div class="col-10">
						<label for="storeChar">餐廳分類</label> <select size="1"
							name="storeChar">
							<c:forEach var="storeCharDetailVO" items="${storeCharSvc.all}">
								<option value="${storeCharDetailVO.storeChar}"
									${(storeLogin.storeChar==storeCharDetailVO.storeChar)? 'selected':'' }>${storeCharDetailVO.storeCharName}
							</c:forEach>
						</select>
					</div>

					<div class="col-10">
						<label for="storeAddress">餐點地址</label> <input type="text"
							id="storeAddress" name="storeAddress" value="${storeLogin.storeAddress}" />
					</div>

					<div class="col-10">
						<label for="storePhone">餐點電話</label> <input type="text"
							id="storePhone" name="storePhone" value="${storeLogin.storePhone}" />
					</div>
				</div>
			</div>
			<div class="col-5" style="display: inline-block; padding-top:10px; margin-left:5%;">
				<label for="storeOpenTime">開始營業時間</label>
				<input type="text" id="OpenTime" name="storeOpenTime" value="<fmt:formatDate value="${storeLogin.storeOpenTime}" pattern="HH:mm"/>">
			</div>
			<div class="col-5" style="display: inline-block; padding-top:10px;">
				<label for="storeCloseTime">結束營業時間</label> 
				<input type="text" id="CloseTime" name="storeCloseTime" value="<fmt:formatDate value="${storeLogin.storeCloseTime}" pattern="HH:mm"/>"/>
			</div>
			<div class="col">
				<div id="storeInfo">
					<h6>詳細資訊</h6>
					<textarea style="width: 80%; height: 8.7em" name="storeInfo" value="${storeLogin.storeInfo}">${storeLogin.storeInfo}</textarea>
				</div>

				<div class="button">
				<input type="hidden" name="storeId" value="${storeLogin.storeId}"> 
				<input type="hidden" name="storeStatus" value="0">
				<input type="hidden" name="action" value="easyupdate"> 
					<input type="submit" value="修改餐廳資訊" /> 

				</div>
			</div>
		</div>

	</FORM>

</body>
<% 
  java.sql.Timestamp storeOpenTime = null;
  try {
	  storeOpenTime = restaurantVO.getStoreOpenTime();
   } catch (Exception e) {
	   storeOpenTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp storeCloseTime = null;
  try {
	  storeCloseTime = restaurantVO.getStoreCloseTime();
   } catch (Exception e) {
	   storeCloseTime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<script>
$('#OpenTime').timepicker({
	timeFormat : "H:mm", // 時間隔式
	interval : 60, //時間間隔
	startTime : "10:00", // 開始時間
	value : '${storeLogin.storeOpenTime}',
});

$('#CloseTime').timepicker({
	timeFormat : "H:mm", // 時間隔式
	interval : 60, //時間間隔
	startTime : "14:00", // 開始時間
	value : '${storeLogin.storeCloseTime}'
});
</script>
</html>