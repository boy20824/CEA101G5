<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.restaurant.model.*"%>

<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); // JSP一開時得到null值   ;EL為空字串
%>
<%@include file="../selectpage/selectstorepage.jsp" %>
<%-- <jsp:include page="<%=request.getContextPath() %>/front-store-end/selectpage/selectstorepage.jsp" > --%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-store-end/menu/css.css" />
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/front-store-end/menu/js.js"></script>
<style>
.forDiv {
	position: relative;
	width: 100%;
	height: 100%;
}

.positionForCheckbox {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 10;
}

.preview {
	width: 20rem;
	height: 20rem;
	border: 5px solid gray;
	border-radius: 1vw;
	padding: 0.2em;
	margin-left: 2vw;
	margin-bottom: 1vw;
}

img {
	box-shadow: 0 0;
	filter: saturate(1)
}
.error{
position:absolute;
top:3.8%;
left:25%;
}
font{
position:absolute;
top:1%;
left:25%;
}
.sidebar {
	height: 100%;
    width: 100px;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #404040;
    transition: 0.5s;
    padding-top: 10px;
    transform: translateX(-100%);
    transition: 0.5s;
    box-sizing: content-box;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
</style>
</head>
<body>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul class="error">
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/menu/MenuServlet.do" name="form1" enctype="multipart/form-data">
		<div class="container">
			<div class="top1">
				<div class="pic">
					<div class="preview" style="display: flex; align-items: center"></div>
						<label for="myFile" style="margin-left: 2vw">請上傳圖片檔案</label>
						<input type="file" id="myFile" style="margin: 2px" name="menuPic" /><br /> 
						<label for="fileName" style="margin-left: 2vw; margin-right: 1vw;">檔案名稱</label>
						<input type="text" id="fileName" disabled="disabled" /> 
						<input type="button" value="刪除" id="clear" style="margin: 5px 10px 10px 10px" />
					</div>
				<div class="add">
					<label for="menuName">餐點名稱</label><input type="text" id="menuName" name="menuName" value="<%=(menuVO == null) ? "" : menuVO.getMenuName()%>" />
					<label for="menuChar">餐點種類</label> 
					<select name="menuChar" id="menuChar">
						<option value="主餐">主餐</option>
						<option value="副餐">副餐</option>
						<option value="湯品">湯品</option>
						<option value="甜品">甜品</option>
						<option value="飲品">飲品</option>
					</select> <label for="menuPrice">餐點價格</label>
					<input type="text" id="menuPrice" name="menuPrice" value="<%=(menuVO == null) ? "" : menuVO.getMenuPrice()%>"/>
					<div class="detail">
						<h2>詳細資訊</h2>
						<textarea style="width: 25em; height: 8.7em" name="menuDetail"><%=(menuVO == null) ? "" : menuVO.getMenuDetail()%>
						</textarea>
					</div>
				</div>

			</div>
			<div class="bottom">
				<input type="hidden" name="menuStatus" value="1"> 
				<input type="hidden" name="menuSellStatus" value="1">
				<input type="hidden" name="storeId" value="<%=((RestaurantVO)session.getAttribute("storeLogin")).getStoreId()%>">
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="確定新增" /> 
				<input type="reset" value="取消" />
			</div>
		</div>
	</FORM>
</body>
</html>