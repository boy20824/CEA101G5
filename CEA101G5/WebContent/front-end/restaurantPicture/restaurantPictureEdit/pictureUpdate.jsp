<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurantpicture.model.*"%>

<%
	RestaurantPictureVO restPicVO = (RestaurantPictureVO) request.getAttribute("restaurantPictureVO");
%>
<%=restPicVO == null%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/front-end/bootstrap-4.5.3-dist/css/bootstrap-grid.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/front-end/bootstrap-4.5.3-dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/front-end/bootstrap-4.5.3-dist/css/bootstrap-reboot.min.css" />
<link rel="stylesheet" href="css/pictureUpdate.css" />
<title>storePicEdit</title>


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

	<div class="container">
		<div class="row">
			<div class="col">
				<div id="store-name">
					<p>台雞店</p>
				</div>
			</div>
			<div class="col-10">
				<div id="page-title">
					<p>照片修改</p>
				</div>
			</div>

		</div>
		<div class="row reserve">
			<table id="table-1">
				<tr>
					<td>
						<h3>pictureUpdate.jsp</h3>
					</td>
					<td>
						<h4>
							<a href="<%=request.getContextPath() %>/front-end/restaurantPicture/restaurantPictureEdit/select_page.jsp">回首頁</a>
						</h4>
					</td>
				</tr>
			</table>
		</div>
		<div class="row reserve">
			<h3></h3>
		</div>
		<div class="row request">
			<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/front-end/restaurantPicture/restaurantPictureEdit/restaurantPictureEdit.do" name="form1"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td>storepicid:</td>
						<td><input type="TEXT" name="storePictureId"
							readonly="readonly" size="10"
							value="${restaurantPictureVO.storePictureId}" /></td>
						<td>storepic:</td>
						<td><img style="width: 100px;"
							src="<%=request.getContextPath() %>/front-end/restaurantPicture/restaurantPictureEdit/restaurantPictureEdit.do?storePicId=${restaurantPictureVO.storePictureId}&action=getOne_For_Display"></td>
						<td><div class="btn-group-toggle" data-toggle="buttons">
								<label class="btn btn-secondary active"><input
									style="opacity: 0; width: 1px;" type="file" name="storePicture"
									id="storePicture" size="10" />upload</label>
							</div></td>
						<td><div id="preview"></div></td>
						<td><input type="hidden" name="action" value="update">
							<input type="submit" value="修改"></td>
					</tr>
				</table>

			</FORM>
		</div>


		<div class="row main">


			
				<div>
					<h1>圖片預覽</h1>

				</div>
				<div id="photoArea">
					<div id="display"></div>
			
		</div>
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
							//新增div(照片)
							let oneSet = document.createElement('div');
							oneSet.setAttribute("id", "oneSet");
							oneSet.setAttribute("name", "oneSet");
							display.append(oneSet);
							//新增照片
							let img = document.createElement('img');
							img.src = e.target.result;
							img.setAttribute("name", "img");
							img.setAttribute("width", "700px");
							img.setAttribute("style", "margin-top:10%")
							oneSet.append(img);
						});
						reader.readAsDataURL(file);
					} else {
						alert("plz upload an img")
					}
					//只show一張圖片
					let sets = document.getElementsByName("oneSet");
					while (sets.length > 0) {
						sets[0].remove();
					}
				}
			});
		}
	</script>
	<script src="../../bootstrap-4.5.3-dist/js/jquery-3.4.1.min.js"></script>
	<script src="../../bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
	<script src="../../bootstrap-4.5.3-dist/js/popper.min.js"></script>
</body>

</html>