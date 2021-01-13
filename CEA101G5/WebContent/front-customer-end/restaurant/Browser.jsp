<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.restaurant.model.*"%>

<jsp:useBean id="listRestaurants_ByCompositeQuery" scope="request"
	type="java.util.List<RestaurantVO>" />
<jsp:useBean id="restSvc" scope="page"
	class="com.restaurant.model.RestaurantService" />
<jsp:useBean id="restPicSvc" scope="page"
	class="com.restaurantpicture.model.RestaurantPictureService" />
<jsp:useBean id="restaurantPictureVO" scope="page"
	class="com.restaurantpicture.model.RestaurantPictureVO" />
	<!-- 餐廳評論 -->
<jsp:useBean id="cmtSvc" scope="page" class="com.restaurantcmt.model.RestaurantCmtService" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>尋找美食</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/front-customer-end/front/img/favicon.ico"
	type="image/x-icon" />
<!--CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-customer-end/restaurant/css/css.css" />
<link
	href="<%=request.getContextPath()%>/front-customer-end/restaurant/css/all.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/front-customer-end/restaurant/js/all.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous" />
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />


<!--FontAsesome -->
<script src="https://kit.fontawesome.com/ec3da2c09a.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<!--JQuery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<!--DatePicker -->
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

</head>
<style>
.ratings {
	position: relative;
	vertical-align: middle;
	display: inline-block;
	color: #ddd; /*背景星星顏色*/
	overflow: hidden;
	font-size: 16px; /*調整字體大小可放大縮小星星*/
	text-shadow: 0px 1px 0 #999;
}

.full_star {
	/*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #F5E960; /*前景星星顏色*/
}
</style>
<body>
	<div class="wrap">
		<!--  側拉選單  -->
		<div class="side-menu-all">
			<div class="side-menu">
				<nav>
					<c:if test="${empty sessionScope.memLogin}">
						<input type="button" value="加入會員"
							onclick="location.href='<%=request.getContextPath()%>/front-customer-end/member/addMem.jsp';" />
					</c:if>
					<c:if test="${not empty sessionScope.memLogin}">
						<input type="button" value="登出"
							onclick="location.href='<%=request.getContextPath()%>/back-end/member/mem.do?action=logout';" />
					</c:if>

					<a href=""> <i class="fas fa-bullhorn"></i>尋找美食
					</a> <a href=""> <i class="fas fa-bullhorn"></i>購物商城
					</a> <a href=""> <i class="fas fa-bullhorn"></i>商家入口
					</a>
				</nav>
			</div>
			<div class="side-menu-black"></div>
		</div>
		<!--  上標題  -->
		<div class="forfiexed">
			<ul class="title">
				<li><img class="side-menu-p"
					src="<%=request.getContextPath()%>/front-customer-end/restaurant/img/ICON/hambugers.png"
					alt="menu" /></li>
				<li><a
					href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">Enak</a>
				</li>
			</ul>
			<div class="loge">
				<c:if test="${empty sessionScope.memLogin}">
					<a
						href="<%=request.getContextPath()%>/front-customer-end/member/MemLogin.jsp">登入</a>
				</c:if>
				<c:if test="${not empty sessionScope.memLogin}">
					<a
						href="<%=request.getContextPath()%>/front-customer-end/member/memberPage.jsp">
						${sessionScope.memLogin.memName}</a>
				</c:if>
			</div>
		</div>
		<!--  搜尋Bar 套用bootStrap  -->
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/back-end/restaurant/restaurant.do"
			name="form1">
			<div class="search">
				<input type="text" class="searchTerm"
					placeholder="What are you looking for?" name="store_Name">
				<button type="submit" class="searchButton">
					<i class="fa fa-search"></i>
				</button>
				<input type="hidden" name="action"
					value="listRestaurants_ByCompositeQuery">
			</div>
		</FORM>

		<!-- 分類 -->
		<div class="searchByStoreChar">
			<ul class="charList">
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/1.png" alt="" /> <span><p>台式傳統</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/2.png" alt="" /> <span><p>法式美饌</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/3.png" alt="" /> <span><p>中華料理</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/4.png" alt="" /> <span><p>甜點</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/5.png" alt="" /> <span><p>美式經典</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/6.png" alt="" /> <span><p>奶茶咖啡</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/7.png" alt="" /> <span><p>健康料理</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/8.png" alt="" /> <span><p>素食料理</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/9.png" alt="" /> <span><p>義式經典</p></span>
						</div>
				</a></li>
				<li><a href="#">
						<div class="foodList">
							<img src="img/food/10.png" alt="" /> <span><p>日式料理</p></span>
						</div>
				</a></li>
			</ul>
		</div>
		<!-- 餐廳瀏覽  -->
		<c:forEach var="restaurantVO"
			items="${listRestaurants_ByCompositeQuery}">
			<div class="store-container">
				<div class="store-img">
					<img style="width: 250px; height: 280px;"
						src="<%=request.getContextPath()%>/back-end/restaurantpicture/restaurantPicture.do?storeId=${restaurantVO.storeId}&action=getOne_For_Display">
				</div>
				<div class="store-text">
					<ul>
						<li><div>
								<p>
									<i class="fas fa-utensils"></i> 
									<span class="store-text-word">餐廳名稱:${restaurantVO.storeName}</span>
								</p>
							</div></li>
						<li><div>
								<p>
									<i class="fas fa-star"></i> <span class="store-text-word">餐廳評分: <span class="ratings">
										<span class="empty_star">★★★★★</span> <span class="full_star"
										style="width:${restaurantVO.storeRatingTotal}%">★★★★★</span></span></span>
								</p>
								
							</div></li>
							<c:set var="rating" value="${0}" />
			       			<c:forEach var="cmtVO" items="${cmtSvc.getAll(restaurantVO.storeId)}">
			       				<c:set var="rating" value="${rating + cmtVO.storeRating }" />
          					</c:forEach>
        					<input type="hidden" class="rating" value="${rating/cmtSvc.getAll(restaurantVO.storeId).size()*10}" />
        					<script>
          						let rate = document.querySelector('.rating')
          						document.querySelector('.full_star').style.width=Math.round(rate.value)+'%'
          					</script>
						<li><div>
								<p>
									<i class="fas fa-phone"></i> <span class="store-text-word">餐廳電話:${restaurantVO.storePhone}</span>
								</p>
							</div></li>
						<li><div>
								<p>
									<i class="fas fa-thumbtack"></i><span class="store-text-word">餐廳地址:${restaurantVO.storeAddress}</span>
								</p>
							</div></li>
					</ul>
					<button class="btn btn--block card__btn"
						onclick="window.location.href='<%=request.getContextPath()%>/front-customer-end/menu/menuindex.jsp?storeId=${restaurantVO.storeId}'">查看更多</button>
				</div>
			</div>
		</c:forEach>
		<!--  底端列  -->
		<div class="footer">
			<div class="footer-image">
				<img
					src="<%=request.getContextPath()%>/front-customer-end/restaurant/img/LOGO/Logo3 (2).png"
					alt="" />
				<div class="footer-image-bottom">
					<img
						src="<%=request.getContextPath()%>/front-customer-end/restaurant/img/ICON/fb.png"
						alt=""> <img
						src="<%=request.getContextPath()%>/front-customer-end/restaurant/img/ICON/tw.png"
						alt=""> <img
						src="<%=request.getContextPath()%>/front-customer-end/restaurant/img/ICON/ig.png"
						alt="">
				</div>
			</div>
			<div class="footer-item">
				<h2>關於Enak</h2>
				<ul>
					<li><i class="fas fa-angle-right"></i> 閱讀我們的部落落</li>
					<li><i class="fas fa-angle-right"></i> <a
						href="<%=request.getContextPath()%>/front-customer-end/restaurant/application.jsp">建立企業帳戶</a></li>
					<li><i class="fas fa-angle-right"></i> 新增您的餐聽</li>
				</ul>
			</div>
			<div class="footer-item">
				<h2>餐廳列表</h2>
				<ul>
					<li><i class="fas fa-angle-right"></i> 台式傳統</li>
					<li><i class="fas fa-angle-right"></i> 美式經典</li>
					<li><i class="fas fa-angle-right"></i> 奶茶咖啡</li>
					<li><i class="fas fa-angle-right"></i> 日式料理</li>
				</ul>
			</div>
			<div class="footer-item">
				<h2>服務項目</h2>
				<ul>
					<li><i class="fas fa-angle-right"></i> 美食廣告</li>
					<li><i class="fas fa-angle-right"></i> 取號</li>
					<li><i class="fas fa-angle-right"></i> 訂位訂桌</li>
					<li><i class="fas fa-angle-right"></i> 購物商城</li>
				</ul>
			</div>
		</div>
		<div class="footer-bottom">©2020 Enak Food Platform Inc.</div>
	</div>
</body>

</html>