<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurantpicture.model.*"%>

<%
RestaurantPictureVO restPicVO = (RestaurantPictureVO) request.getAttribute("restaurantPictureVO");
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
.addPicBlock{
margin-left:200px;

}

</style>
</head>

<body>
<div class="addPicBlock">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/restaurantpicture/restaurantPicture.do" name="form1" enctype="multipart/form-data">
<table class="table table-striped">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<tr class=".bg-secondary">
		<td>餐廳名稱:</td>
		<td>${storeLogin.storeName}</td>
	</tr>
	<tr class=".bg-secondary">
		<td>新增照片:</td>
		<td><input type="file" id="storePicture" name="storePicture" size="45" multiple="multiple" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="storeId" value="${storeLogin.storeId}">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</div>
<div>
		<div style="margin-left:50%;" id="photoArea">
		<h3>照片預覽</h3>
			<div id="display" style="width: 800px;"></div>
		</div>
	</div>
	
	<script>
		window.onload = photoPreview;

		function photoPreview() {
			let display = document.getElementById("display");
			let photo = document.getElementById("storePicture");

			photo.addEventListener("change", function() {
				for (let i = 0; i < this.files.length; i++) {
					let file = this.files[i];
					if (file.type.indexOf('image') > -1) {
						let reader = new FileReader();
						reader.addEventListener('load', function(e) {
							//新增div(照片預覽)
							let oneSet = document.createElement('div');
							oneSet.setAttribute("id", "oneSet");
							oneSet.setAttribute("name", "oneSet");
							display.append(oneSet);
							//新增照片
							let img = document.createElement('img');
							img.src = e.target.result;
							img.setAttribute("name", "img");
							img.setAttribute("width", "100px");
							img.setAttribute("style", "float:left");
							oneSet.append(img);
						});
						reader.readAsDataURL(file);
					} else {
						alert("plz upload an img")
					}
					//只show當前上傳圖片
					let sets = document.getElementsByName("oneSet");
					while (sets.length > 0) {
						sets[0].remove();
					}
				}
			});
		}
	</script>
</body>
 
</html>