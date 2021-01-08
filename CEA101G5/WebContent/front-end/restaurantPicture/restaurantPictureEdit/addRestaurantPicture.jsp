<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurantpicture.model.*"%>

<%
RestaurantPictureVO restPicVO = (RestaurantPictureVO) request.getAttribute("restaurantPictureVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>RestaurantPicture.jsp</title>



</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>addstorePic.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath() %>/front-end/restaurantPicture/restaurantPictureEdit/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/restaurantPicture/restaurantPictureEdit/restaurantPictureEdit.do" name="form1" enctype="multipart/form-data">
<table>
<!-- 	<tr> -->
<!-- 		<td>storepicid</td> -->
<!-- 		<td><input type="TEXT" name="storePictureId" size="45"  -->
<%-- 			 value="<%= (restPicVO==null)? "SP000007" : restPicVO.getStorePictureId()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>storeid:</td>
		<td><input type="TEXT" name="storeId" size="45"
			 value="<%= (restPicVO==null)? "S000001" : restPicVO.getStoreId()%>" /></td>
	</tr>
	<tr>
		<td>storepic:</td>
		<td><input type="file" id="storePicture" name="storePicture" size="45" multiple="multiple" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>


<div>
		<div>
			<h1>圖片預覽</h1>

		</div>
		<div id="photoArea">
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