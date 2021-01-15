<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurantpicture.model.*"%>

<%
    RestaurantPictureService resPicSvc = new RestaurantPictureService();
    List<RestaurantPictureVO> list = resPicSvc.getAll();
    pageContext.setAttribute("list",list);
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

<style>
.restauranPicBlock{
margin-left:200px
}
</style>
</head>

<body>


<div>
<div class="restauranPicBlock">
<table class="table table-striped">
	<tr class=".bg-secondary">
		<th>圖片編號</th>
		<th>店家編號</th>
		<th>圖片</th>
		<th>刪除</th>
	</tr>
	<jsp:useBean id="restPicSvc" scope="page" class="com.restaurantpicture.model.RestaurantPictureService" />
	<c:forEach var="restPiclist" items="${restPicSvc.getStorePicByStoreId(storeLogin.storeId)}">
		
		<tr>
			<td><input type="checkbox">${restPiclist.storePictureId}</td>
			<td>${storeLogin.storeId}</td>
			<td><img style="width:100px;" src="<%=request.getContextPath() %>/back-end/restaurantpicture/restaurantPicture.do?storePictureId=${restPiclist.storePictureId}&action=getOne_For_Display"></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/restaurantpicture/restaurantPicture.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-secondary">
			     <input type="hidden" name="storePictureId"  value="${restPiclist.storePictureId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<div >
<button style="margin-left:50%" class="btn btn-secondary" onclick="location.href='<%=request.getContextPath()%>/front-store-end/restaurant/addRestaurantPicturePage.jsp'">新增餐廳照片</button>
</div>
</div>
</body>
 
</html>