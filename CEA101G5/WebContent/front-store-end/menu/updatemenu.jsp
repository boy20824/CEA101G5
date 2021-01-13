<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>

<%
	MenuVO menuVO = (MenuVO) request.getAttribute("menuVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-store-end/menu/updatemenucss.css" />
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<%@include file="../selectpage/selectstorepage.jsp"%>
<script src="<%=request.getContextPath()%>/front-store-end/menu/updatemenujs.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-store-end/menu/addmenusupcharcss.css" />
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<script type="text/javascript" src="<%=request.getContextPath()%>/front-store-end/menu/addmenusupcharjs.js"></script>
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
	filter: saturate(1);
}

.error {
	position: absolute;
	top: 3.8%;
	left: 45%;
}

font {
	position: absolute;
	top: 1%;
	left: 45%;
}

.preview>img {
	width: 20em;
	height: 20em;
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
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/menu/MenuServlet.do" name="form1" enctype="multipart/form-data">
		<div class="container">
			<div class="top1">
				<div class="pic">
					<div class="preview" style="display: flex; align-items: center">
						<img class="begin" src="<%=request.getContextPath()%>/menu/MenuServlet.do?menuId=${menuVO.menuId}&action=getOnePicture">
					</div>
					<label for="myFile" style="margin-left: 2vw">請上傳圖片檔案</label> 
					<input type="file" id="myFile" style="margin: 2px" name="menuPic" /><br />
					<label for="fileName" style="margin-left: 2vw; margin-right: 1vw">檔案名稱</label>
					<input type="text" id="fileName" disabled="disabled" /> 
					<input type="button" value="刪除" id="clear" style="margin: 5px 10px 10px 10px" />
				</div>
				<div class="add">
					<label for="menuName">餐點名稱</label>
					<input type="text" id="menuName" name="menuName" value="<%=menuVO.getMenuName()%>" />
					<label for="menuChar">餐點種類</label> 
					<select id="menuChar" name="menuChar">
						<option value="主餐">主餐</option>
						<option value="副餐">副餐</option>
						<option value="湯品">湯品</option>
						<option value="甜品">甜品</option>
						<option value="飲品">飲品</option>
					</select> <label for="menuPrice">餐點價格</label>
					<input type="text" id="menuPrice" name="menuPrice" value="<%=menuVO.getMenuPrice()%>" />
					<div class="detail">
						<h2>詳細資訊</h2>
						<textarea style="width: 25em; height: 8.7em" name="menuDetail"><%=menuVO.getMenuDetail()%></textarea>
					</div>
				</div>
			</div>
			<div class="bottom">
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="menuId" value="<%=menuVO.getMenuId()%>">
				<input type="hidden" name="menuStatus" value="0"> 
				<input type="hidden" name="menuSellStatus" value="1">
				<input type="button" value="客製化選項" class="addmenubox" /> 
				<input type="submit" value="確定修改" /> 
				<input type="button" onclick="history.back()" value="取消" /> 
				
			</div>
		</div>
	</form>
<!-- 	客製化燈箱 -->
	<form method="post" action="">
	<div class="menuChar" style="display:none;">
      <div class="menuCharTitle">
        <i class="fas fa-arrow-left"><span>編輯選項類別</span></i>
        <input type="button" value="新增" />
      </div>
      <div class="menuCharTop">
        <div class="menuChars">
          <p>選項的詳細內容</p>
          <p>名稱*</p>
          <input type="text" placeholder="請輸入客製化選項" />
          <p>例如:配類選擇、加料選擇</p>
        </div>
      </div>
      <div class="menuCharBottom">
        <div class="menuCharDetail">
          <div class="menuCharDetailTitle">
            <p>客製化選項</p>
            <p>+新增選項</p>
          </div>
          <p>新增選項以提供消費者更多選擇</p>
        </div>
        <div class="menuCharAdd">
            <div class="put">
                <img src="<%=request.getContextPath()%>/front-store-end/menu/1.png" alt="">
                <input type="text" name="menuChar" />
            </div>
            <div class="put">
                <img src="<%=request.getContextPath()%>/front-store-end/menu/1.png" alt="">
                <input type="text" name="menuChar" />
            </div>
            <div class="put">
                <img src="<%=request.getContextPath()%>/front-store-end/menu/1.png" alt="">
                <input type="text" name="menuChar" />
            </div>
        </div>
      </div>
    </div>
	</form>
	
</body>
</html>