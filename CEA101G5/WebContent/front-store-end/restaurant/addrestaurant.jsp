<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>
<%
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restaurantVO");
%>
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
	<input type="checkbox" id="side-menu-switch" />
	<div id="mySidebar" class="sidebar">
		<span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="utensils"
				class="svg-inline--fa fa-utensils fa-w-13" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 416 512">
            <path fill="#9C9C9C"
					d="M207.9 15.2c.8 4.7 16.1 94.5 16.1 128.8 0 52.3-27.8 89.6-68.9 104.6L168 486.7c.7 13.7-10.2 25.3-24 25.3H80c-13.7 0-24.7-11.5-24-25.3l12.9-238.1C27.7 233.6 0 196.2 0 144 0 109.6 15.3 19.9 16.1 15.2 19.3-5.1 61.4-5.4 64 16.3v141.2c1.3 3.4 15.1 3.2 16 0 1.4-25.3 7.9-139.2 8-141.8 3.3-20.8 44.7-20.8 47.9 0 .2 2.7 6.6 116.5 8 141.8.9 3.2 14.8 3.4 16 0V16.3c2.6-21.6 44.8-21.4 48-1.1zm119.2 285.7l-15 185.1c-1.2 14 9.9 26 23.9 26h56c13.3 0 24-10.7 24-24V24c0-13.2-10.7-24-24-24-82.5 0-221.4 178.5-64.9 300.9z"></path>
          
        </svg>
			<p>餐廳</p>
			<ul>
				<li>餐廳設定
					<ul>
						<li><a href="<%=request.getContextPath()%>/front-store-end/restaurant/addrestaurant.jsp">餐廳資訊設定</a></li>
						<li><a href="<%=request.getContextPath()%>/front-store-end/restaurant/addrestaurant.jsp">餐廳照片管理</a></li>
						<li>功能開關選項</li>
					</ul>
				</li>
				<li>餐點設定
					<ul>
						<li><a
							href="<%=request.getContextPath()%>/front-store-end/menu/addmenu.jsp">新增餐點</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/front-store-end/menu/menulist.jsp">餐點管理</a>
						</li>
						<li><a
							href="<%=request.getContextPath()%>/front-store-end/menu/sellstatus.jsp">可售餐點管理</a>
						</li>
					</ul>
				</li>
				<li>外帶選項設定</li>
				<li>取號選項設定
				</li>
				<li>定位選項設定</li>
			</ul>
		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="concierge-bell"
				class="svg-inline--fa fa-concierge-bell fa-w-16" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
            <path fill="#9C9C9C"
					d="M288 130.54V112h16c8.84 0 16-7.16 16-16V80c0-8.84-7.16-16-16-16h-96c-8.84 0-16 7.16-16 16v16c0 8.84 7.16 16 16 16h16v18.54C115.49 146.11 32 239.18 32 352h448c0-112.82-83.49-205.89-192-221.46zM496 384H16c-8.84 0-16 7.16-16 16v32c0 8.84 7.16 16 16 16h480c8.84 0 16-7.16 16-16v-32c0-8.84-7.16-16-16-16z"></path>
         
        </svg>
			<p>外帶</p>

		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="vote-yea"
				class="svg-inline--fa fa-vote-yea fa-w-20" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512">
            <path fill="#9C9C9C"
					d="M608 320h-64v64h22.4c5.3 0 9.6 3.6 9.6 8v16c0 4.4-4.3 8-9.6 8H73.6c-5.3 0-9.6-3.6-9.6-8v-16c0-4.4 4.3-8 9.6-8H96v-64H32c-17.7 0-32 14.3-32 32v96c0 17.7 14.3 32 32 32h576c17.7 0 32-14.3 32-32v-96c0-17.7-14.3-32-32-32zm-96 64V64.3c0-17.9-14.5-32.3-32.3-32.3H160.4C142.5 32 128 46.5 128 64.3V384h384zM211.2 202l25.5-25.3c4.2-4.2 11-4.2 15.2.1l41.3 41.6 95.2-94.4c4.2-4.2 11-4.2 15.2.1l25.3 25.5c4.2 4.2 4.2 11-.1 15.2L300.5 292c-4.2 4.2-11 4.2-15.2-.1l-74.1-74.7c-4.3-4.2-4.2-11 0-15.2z"></path>
        </svg>
			<p>取號</p>

		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="far" data-icon="calendar-check"
				class="svg-inline--fa fa-calendar-check fa-w-14" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
            <path fill="#9C9C9C"
					d="M400 64h-48V12c0-6.627-5.373-12-12-12h-40c-6.627 0-12 5.373-12 12v52H160V12c0-6.627-5.373-12-12-12h-40c-6.627 0-12 5.373-12 12v52H48C21.49 64 0 85.49 0 112v352c0 26.51 21.49 48 48 48h352c26.51 0 48-21.49 48-48V112c0-26.51-21.49-48-48-48zm-6 400H54a6 6 0 0 1-6-6V160h352v298a6 6 0 0 1-6 6zm-52.849-200.65L198.842 404.519c-4.705 4.667-12.303 4.637-16.971-.068l-75.091-75.699c-4.667-4.705-4.637-12.303.068-16.971l22.719-22.536c4.705-4.667 12.303-4.637 16.97.069l44.104 44.461 111.072-110.181c4.705-4.667 12.303-4.637 16.971.068l22.536 22.718c4.667 4.705 4.636 12.303-.069 16.97z"></path>
          
        </svg>
			<p>訂位</p></span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="cash-register"
				class="svg-inline--fa fa-cash-register fa-w-16" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
            <path fill="#9C9C9C"
					d="M511.1 378.8l-26.7-160c-2.6-15.4-15.9-26.7-31.6-26.7H208v-64h96c8.8 0 16-7.2 16-16V16c0-8.8-7.2-16-16-16H48c-8.8 0-16 7.2-16 16v96c0 8.8 7.2 16 16 16h96v64H59.1c-15.6 0-29 11.3-31.6 26.7L.8 378.7c-.6 3.5-.9 7-.9 10.5V480c0 17.7 14.3 32 32 32h448c17.7 0 32-14.3 32-32v-90.7c.1-3.5-.2-7-.8-10.5zM280 248c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16zm-32 64h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16zm-32-80c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16zM80 80V48h192v32H80zm40 200h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16zm16 64v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16zm216 112c0 4.4-3.6 8-8 8H168c-4.4 0-8-3.6-8-8v-16c0-4.4 3.6-8 8-8h176c4.4 0 8 3.6 8 8v16zm24-112c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16zm48-80c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16z"></path>
       
        </svg>
			<p>金流</p>

		</span> <label for="side-menu-switch"> <i class="fas fa-angle-right"></i>
		</label>
	</div>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/restaurant/restaurant.do"
		enctype="multipart/form-data">
		<div class="addStorecontainer">
			<div class="row">
				<div class="col-2">
					<div class="preview"></div>
				</div>
				<div class="col" id="addTextBlock">
					<div class="col-10">
						<label for="storeName">餐廳名稱</label> <input type="text"
							id="storeName" name="storeName" value="${restaurantVO.storeName}" />
					</div>

					<jsp:useBean id="storeCharSvc" scope="page"
						class="com.storechardetail.model.StoreCharDetailService" />
					<div class="col-10">
						<label for="storeChar">餐廳分類</label> <select size="1"
							name="storeChar">
							<c:forEach var="storeCharDetailVO" items="${storeCharSvc.all}">
								<option value="${storeCharDetailVO.storeChar}"
									${(restaurantVO.storeChar==storeCharDetailVO.storeChar)? 'selected':'' }>${storeCharDetailVO.storeCharName}
							</c:forEach>
						</select>
					</div>

					<div class="col-10">
						<label for="storeAddress">餐點地址</label> <input type="text"
							id="storeAddress" name="storeAddress" value="${restaurantVO.storeAddress}" />
					</div>

					<div class="col-10">
						<label for="storePhone">餐點電話</label> <input type="text"
							id="storePhone" name="storePhone" value="${restaurantVO.storePhone}" />
					</div>
				</div>
			</div>
			<div class="col-5" style="display: inline-block; padding-top:10px; margin-left:5%;">
				<label for="storeOpenTime">開始營業時間</label> 
				<input type="text" id="OpenTime" name="storeOpenTime" />
			</div>
			<div class="col-5" style="display: inline-block; padding-top:10px;">
				<label for="storeCloseTime">結束營業時間</label> 
				<input type="text" id="CloseTime" name="storeCloseTime" />
			</div>
			<div class="col">
				<div id="storeInfo">
					<h6>詳細資訊</h6>
					<textarea style="width: 80%; height: 8.7em" name="storeInfo" value="${restaurantVO.storeInfo}"></textarea>
				</div>

				<div class="button">
					<label for="myFile" style="margin-left: 2vw">請上傳圖片檔案</label> 
					<input type="file" id="myFile" style="margin: 2px" name="storePic" /><br/>
					<label for="fileName" style="margin-left: 2vw; margin-right: 1vw;">檔案名稱</label>
					<input type="text" id="fileName" disabled="disabled" /> 
					<input type="button" value="刪除" id="clear" style="margin: 5px 10px 10px 10px" /> 
					<input type="hidden" name="storeStatus" value="0"> 
					<input type="hidden" name="memPhone" value="${memLogin.memPhone}"> 
					<input type="hidden" name="action" value="easyinsertWithPic"> 
					<input type="submit" value="確定新增" /> 
					<input type="reset" value="取消" />

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
	value : '${restaurantVO.storeOpenTime}',
});

$('#CloseTime').timepicker({
	timeFormat : "H:mm", // 時間隔式
	interval : 60, //時間間隔
	startTime : "14:00", // 開始時間
	value : '${restaurantVO.storeCloseTime}'
});
</script>

</html>