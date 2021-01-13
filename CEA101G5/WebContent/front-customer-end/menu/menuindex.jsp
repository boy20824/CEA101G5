<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>

<%
	RestaurantService restaurantSvc = new RestaurantService(); //創建 實體
	if(request.getParameter("storeId")==null){
		RestaurantVO restaurantVO = restaurantSvc.getOneRestaurant(((RestaurantVO)session.getAttribute("restaurantVO")).getStoreId());
	}else{
		RestaurantVO restaurantVO = restaurantSvc.getOneRestaurant(request.getParameter("storeId")); //呼叫DAO並執行getAll()取得VO為每一列資訊的所有欄位並裝入List集合<泛型只能裝取該VO型別>;
		session.setAttribute("restaurantVO", restaurantVO);
	}

%>
<%
// 防止快取
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
<!-- 餐廳評論 -->
<jsp:useBean id="cmtSvc" scope="page" class="com.restaurantcmt.model.RestaurantCmtService" />



<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-customer-end/menu/css/css.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="<%=request.getContextPath()%>/front-customer-end/menu/js/all.js"></script>
<link href="<%=request.getContextPath()%>/front-customer-end/menu/css/all.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/menu/slick/slick.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/menu/slick/slick-theme.css" />
<script src="<%=request.getContextPath()%>/front-customer-end/menu/js/ajax.js"></script>
<style>
.ratings {
	position: relative;
	vertical-align: middle;
	display: inline-block;
	color: #ddd; /*背景星星顏色*/
	overflow: hidden;
	font-size: 20px; /*調整字體大小可放大縮小星星*/
	text-shadow: 0px 1px 0 #999;
}

.full_star {
	width: 0%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #D56A16; /*前景星星顏色*/
}
</style>


</head>
<body>

	<!--存放外會員進來的參數以及餐點編號 -->
	<input type="hidden" class="storeId" value="${restaurantVO.storeId}" />
	<% MemVO memberPhone =(MemVO)session.getAttribute("memLogin");%>
	<c:if test="${not empty sessionScope.memLogin}"> 
	<input type="hidden" class="memberId" value="<%= memberPhone.getMemPhone() %>" />
	</c:if>

	<div class="wrap">
		<!--  側拉選單  -->
		<div class="side-menu-all">
			<div class="side-menu">
				<nav>
					<c:if test="${empty sessionScope.memLogin}">
						<input type="button" value="加入會員" onclick="location.href='<%=request.getContextPath()%>/front-customer-end/member/addMem.jsp';" />
					</c:if>
					<c:if test="${not empty sessionScope.memLogin}">
						<input type="button" value="登出" onclick="location.href='<%=request.getContextPath()%>/back-end/member/mem.do?action=logout';" />
					</c:if>
					<a href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp"> <i class="fas fa-bullhorn"></i>尋找美食</a>
					<a href=""> <i class="fas fa-bullhorn"></i>購物商城</a>
					<a href="<%=request.getContextPath()%>/front-store-end/restaurant/WelcomePage.jsp"> <i class="fas fa-bullhorn"></i>商家入口</a>
				</nav>
			</div>
			<div class="side-menu-black"></div>
		</div>
		<!--  上標題  -->
		<div class="forfiexed">
			<ul class="title">
				<li><img class="side-menu-p" src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/hambugers.png" alt="menu" /></li>
				<li><a href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">Enak</a></li>
			</ul>
			<div class="loge">
				<c:if test="${empty sessionScope.memLogin}">
					<a href="<%=request.getContextPath()%>/front-customer-end/member/MemLogin.jsp">登入</a>
				</c:if>
				<c:if test="${not empty sessionScope.memLogin}">
					<a href="<%=request.getContextPath()%>/front-customer-end/member/memberPage.jsp">${sessionScope.memLogin.memName}</a>
				</c:if>
				<div class="shopCard">
					<i class="fas fa-shopping-cart"></i>
					<p>購物車</p>
				</div>
			</div>
		</div>
		
		<div class="shopping-cart">
			<div class="shopping-cart-header">
				<i class="fa fa-shopping-cart cart-icon"></i><span class="badge">0</span>
				<div class="shopping-cart-total">
					<span class="lighter-text">總計:</span> <span class="main-color-text"></span>
				</div>
			</div>
			
			<!--end shopping-cart-header -->

			<div class="forCar" style="overflow: auto; height: 500px;width:320px;">
				<c:forEach var="item" items="${memuList}">
					<div id="${item.menuId}" class="carlist">
						<ul class="shopping-cart-items">
							<li class="clearfix">
							<img src="/CEA101G5/menu/MenuServlet.do?menuId=${item.menuId}&action=getOnePicture" alt="item1" /> <span class="item-name">${item.menuName}</span>
							<span class="item-price">$${item.menuPrice}</span> 
							<span class="item-quantity">Quantity:${item.quantity}</span>
							<span class="item-remove">移除</span></li>
						</ul>
					</div>
				</c:forEach>
				<a href="<%=request.getContextPath()%>/front-customer-end/foodorder/foodorder.jsp" class="button">結帳</a>
			</div>
		</div>
		
		<!-- 餐點內容 -->
		<div class="container">
			<div class="restaurantInfo">
				<!-- 餐廳圖片 -->

				<div class="text">
					<ul class="cb-slideshow">
						<img src="img/圖片/餐廳.jpg" alt="" />
						<img src="img/圖片/餐廳.jpg" alt="" />
						<img src="img/圖片/餐廳.jpg" alt="" />
						<img src="img/圖片/餐廳.jpg" alt="" />
					</ul>
				</div>

				<div class="info">
					<ul>
						<li>
							<img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/utensils-solid.svg" alt="" />
							<p>餐廳名稱 :</p>
							<span>${restaurantVO.storeName}</span>
						</li>
						<li>
							<img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/star-solid.svg" alt="" />
							<p>餐廳評分 :</p>
							<span>
								<div class="ratings">
									<div class="empty_star">★★★★★</div>
									<div class="full_star">★★★★★</div>
								</div>
							</span>
						</li>
						<li>
							<img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/phone-solid.svg" alt="" />
							<p>餐廳電話 :</p>
							<span>${restaurantVO.storePhone}</span>
						</li>
						<li>
							<img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/map-marker-alt-solid.svg" alt="" />
							<p>餐廳地址 :</p> <span>${restaurantVO.storeAddress}</span></li>
						<li class="time">
							<img src="<%=request.getContextPath()%>/front-customer-end/menu/img/圖片/time.png" alt="" />
							<p>營業時間 :</p>
							<span><fmt:formatDate value="${restaurantVO.storeOpenTime}" pattern="HH:mm"/>-<fmt:formatDate value="${restaurantVO.storeCloseTime}" pattern="HH:mm"/></span>
						</li>
					</ul>
				</div>
				<div class="functionPick">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-store-end/queue/queueNo/queueNo.do" >
				
       <input type="hidden" name="storeid" value="${restaurantVO.storeId}">
       <input type="hidden" name="action" value="getQueNo">       
       
					<input type="submit" value="線上取號" class="orderNum" />
     </FORM>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-customer-end/reserveorder/addReserveOrder.jsp" >
     <input type="hidden" name="storeId" value="${restaurantVO.storeId}" />
					<input type="submit" value="預約定位" class="orderSet" />
	 </FORM>
				</div>
			</div>
			       <div class="data-slick">
			       <c:set var="rating" value="${0}" />
			       <c:forEach var="cmtVO" items="${cmtSvc.getAll(restaurantVO.storeId)}">
			       		<c:set var="rating" value="${rating + cmtVO.storeRating }" />
          				<div><div class="dog">${cmtVO.storeCmtContent }</div></div>
          			</c:forEach>
        			</div>
        			<input type="hidden" class="rating" value="${rating/cmtSvc.getAll(restaurantVO.storeId).size()*10}" />
        			<script>
          				let rate = document.querySelector('.rating')
          				document.querySelector('.full_star').style.width=Math.round(rate.value)+'%'
          			</script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/front-customer-end/menu/slick/slick.min.js"></script>
    	<script>
      		$(document).ready(function () {
        		$('.data-slick').slick({
  					infinite: true,
  					slidesToShow: 3,
  					slidesToScroll: 3,
  					dots: true,
				});
      		});
      	</script>
			<!-- 餐點分類選單 -->
			<div class="listBar">
				<h1>菜單</h1>
				<ul>
					<li class="menuChar">主餐</li>
					<li class="menuChar">副餐</li>
					<li class="menuChar">湯品</li>
					<li class="menuChar">甜品</li>
					<li class="menuChar">飲品</li>
				</ul>
			</div>
			<!-- 餐點資訊表 -->
			<div class="show"></div>
		</div>

		<!--  底端列  -->
		   <div class="footer">
                        <div class="footer-image">
                            <img src="<%=request.getContextPath()%>/front-customer-end/menu/img/LOGO/Logo3 (2).png" alt="" />
                            <div class="footer-image-bottom">
                                <img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/fb.png" alt=""> <img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/tw.png" alt=""> <img src="<%=request.getContextPath()%>/front-customer-end/menu/img/ICON/ig.png" alt="">
                            </div>
                        </div>
                        <div class="footer-item">
                            <h2>關於Enak</h2>
                            <ul>
                                <li><i class="fas fa-angle-right"></i> 閱讀我們的部落落</li>
                                <li><i class="fas fa-angle-right"></i> <a href="<%=request.getContextPath()%>/front-customer-end/restaurant/application.jsp">建立企業帳戶</a></li>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/front-customer-end/menu/slick/slick.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".cb-slideshow").slick({
				slidesToShow : 1,
				slidesToScroll : 1,
				autoplay : true,
				autoplaySpeed : 5000,
			});
		});
	</script>
</body>
</html>