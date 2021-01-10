<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Enak，找到你最愛的餐廳</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/front-customer-end/front/img/favicon.ico"
	type="image/x-icon" />
<!--CSS -->
<link rel="stylesheet" href="css/css.css" />
<script src="js/all.js"></script>

<!-- Bootstrap -->

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous" />

<!--FontAsesome -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<!--JQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!--CSS -->
<link href="css/all.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="slick/slick.css" />
<link rel="stylesheet" type="text/css" href="slick/slick-theme.css" />
<script type="text/javascript" src="slick/slick.min.js"></script>

<script>
	window.onload = function() {
		$(".forfiexed2").slideDown(10);
		$(".forfiexed2").slideUp(10);
	}
</script>
</head>

<body style="overflow-y: scroll;overflow-x: hidden;">
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
					</a> <a
						href="<%=request.getContextPath()%>/front-store-end/restaurant/WelcomePage.jsp">
						<i class="fas fa-bullhorn"></i>商家入口
					</a>
				</nav>
			</div>
			<div class="side-menu-black"></div>
		</div>
		<!--  上標題  -->
		<div class="forfiexed">
			<ul class="title">
				<li><img class="side-menu-p" src="<%=request.getContextPath()%>/front-customer-end/front/img/ICON/hambugers.png"
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
		<!-- 上標題滑動出現 -->
		<div class="forfiexed2">
			<ul class="title">
				<li><img class="side-menu-p" src="img/ICON/hambugers.png"
					alt="menu"/></li>
				<li><a href="#">Enak</a></li>
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
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel" data-interval="5000" data-wrap="true">
			<div class="banner">
				<p>你最愛的餐廳，由Enak幫你找到</p>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/restaurant/restaurant.do" name="form1">	
				<input type="text" placeholder="開始搜尋您的美食" class="search" name="store_Name"/><br>
				<input type="submit" value="尋找美食" class="submit" />
				<input type="hidden" name="action" value="listRestaurants_ByCompositeQuery">
			</FORM>
			</div>
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="img/背景圖/1.jpg" class="d-block w-100" alt="..." />
				</div>
				<div class="carousel-item">
					<img src="img/背景圖/2.jpg" class="d-block w-100" alt="..." />
				</div>
				<div class="carousel-item">
					<img src="img/背景圖/3.jpg" class="d-block w-100" alt="..." />
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
		<!-- 餐廳瀏覽標題  -->
		<div class="title1">
			<i class="fas fa-utensils fa-4x"></i>
			<p>Recommend</p>
		</div>
		<!-- 餐廳瀏覽  -->
		<div class="menu">
			<li>
				<div class="storecard">
					<div class="card__image">
						<img src="img//圖片/louisa.png" alt="">
					</div>
					<div class="card__content">
						<div class="card__title">Flex</div>
						<p class="card__text">This is the shorthand for flex-grow,
							flex-shrink and flex-basis combined. The second and third
							parameters (flex-shrink and flex-basis) are optional. Default is
							0 1 auto.</p>
						<ul>
							<li><img src="img/ICON/utensils-solid.svg" alt="" /> <span><p>餐廳名稱</p></span>
							</li>
							<li><img src="img/ICON/star-solid.svg" alt="" /> <span><p>餐廳評分</p></span>
							</li>
							<li><img src="img/ICON/phone-solid.svg" alt="" /> <span><p>餐廳電話</p></span>
							</li>
							<li><img src="img/ICON/map-marker-alt-solid.svg" alt="" />
								<span><p>餐廳地址</p></span></li>
						</ul>
						<button class="btn btn--block card__btn">Button</button>
					</div>
				</div>
			</li>
			<li>
				<div class="storecard">
					<div class="card__image">
						<img src="img//圖片/louisa.png" alt="">
					</div>
					<div class="card__content">
						<div class="card__title">Flex</div>
						<p class="card__text">This is the shorthand for flex-grow,
							flex-shrink and flex-basis combined. The second and third
							parameters (flex-shrink and flex-basis) are optional. Default is
							0 1 auto.</p>
						<ul>
							<li><img src="img/ICON/utensils-solid.svg" alt="" /> <span><p>餐廳名稱</p></span>
							</li>
							<li><img src="img/ICON/star-solid.svg" alt="" /> <span><p>餐廳評分</p></span>
							</li>
							<li><img src="img/ICON/phone-solid.svg" alt="" /> <span><p>餐廳電話</p></span>
							</li>
							<li><img src="img/ICON/map-marker-alt-solid.svg" alt="" />
								<span><p>餐廳地址</p></span></li>
						</ul>
						<button class="btn btn--block card__btn">Button</button>
					</div>
				</div>
			</li>
			<li>
				<div class="storecard">
					<div class="card__image">
						<img src="img//圖片/louisa.png" alt="">
					</div>
					<div class="card__content">
						<div class="card__title">Flex</div>
						<p class="card__text">This is the shorthand for flex-grow,
							flex-shrink and flex-basis combined. The second and third
							parameters (flex-shrink and flex-basis) are optional. Default is
							0 1 auto.</p>
						<ul>
							<li><img src="img/ICON/utensils-solid.svg" alt="" /> <span><p>餐廳名稱</p></span>
							</li>
							<li><img src="img/ICON/star-solid.svg" alt="" /> <span><p>餐廳評分</p></span>
							</li>
							<li><img src="img/ICON/phone-solid.svg" alt="" /> <span><p>餐廳電話</p></span>
							</li>
							<li><img src="img/ICON/map-marker-alt-solid.svg" alt="" />
								<span><p>餐廳地址</p></span></li>
						</ul>
						<button class="btn btn--block card__btn">Button</button>
					</div>
				</div>
			</li>
			<li>
				<div class="storecard">
					<div class="card__image">
						<img src="img//圖片/louisa.png" alt="">
					</div>
					<div class="card__content">
						<div class="card__title">Flex</div>
						<p class="card__text">This is the shorthand for flex-grow,
							flex-shrink and flex-basis combined. The second and third
							parameters (flex-shrink and flex-basis) are optional. Default is
							0 1 auto.</p>
						<ul>
							<li><img src="img/ICON/utensils-solid.svg" alt="" /> <span><p>餐廳名稱</p></span>
							</li>
							<li><img src="img/ICON/star-solid.svg" alt="" /> <span><p>餐廳評分</p></span>
							</li>
							<li><img src="img/ICON/phone-solid.svg" alt="" /> <span><p>餐廳電話</p></span>
							</li>
							<li><img src="img/ICON/map-marker-alt-solid.svg" alt="" />
								<span><p>餐廳地址</p></span></li>
						</ul>
						<button class="btn btn--block card__btn">Button</button>
					</div>
				</div>
			</li>
			<li>
				<div class="storecard">
					<div class="card__image">
						<img src="img//圖片/louisa.png" alt="">
					</div>
					<div class="card__content">
						<div class="card__title">Flex</div>
						<p class="card__text">This is the shorthand for flex-grow,
							flex-shrink and flex-basis combined. The second and third
							parameters (flex-shrink and flex-basis) are optional. Default is
							0 1 auto.</p>
						<ul>
							<li><img src="img/ICON/utensils-solid.svg" alt="" /> <span><p>餐廳名稱</p></span>
							</li>
							<li><img src="img/ICON/star-solid.svg" alt="" /> <span><p>餐廳評分</p></span>
							</li>
							<li><img src="img/ICON/phone-solid.svg" alt="" /> <span><p>餐廳電話</p></span>
							</li>
							<li><img src="img/ICON/map-marker-alt-solid.svg" alt="" />
								<span><p>餐廳地址</p></span></li>
						</ul>
						<button class="btn btn--block card__btn">Button</button>
					</div>
				</div>
			</li>
			<li>
				<div class="storecard">
					<div class="card__image">
						<img src="img//圖片/louisa.png" alt="">
					</div>
					<div class="card__content">
						<div class="card__title">Flex</div>
						<p class="card__text">This is the shorthand for flex-grow,
							flex-shrink and flex-basis combined. The second and third
							parameters (flex-shrink and flex-basis) are optional. Default is
							0 1 auto.</p>
						<ul>
							<li><img src="img/ICON/utensils-solid.svg" alt="" /> <span><p>餐廳名稱</p></span>
							</li>
							<li><img src="img/ICON/star-solid.svg" alt="" /> <span><p>餐廳評分</p></span>
							</li>
							<li><img src="img/ICON/phone-solid.svg" alt="" /> <span><p>餐廳電話</p></span>
							</li>
							<li><img src="img/ICON/map-marker-alt-solid.svg" alt="" />
								<span><p>餐廳地址</p></span></li>
						</ul>
						<button class="btn btn--block card__btn">Button</button>
					</div>
				</div>
			</li>
		</div>
		<!--  商品瀏覽標題  -->
		<div class="title1">
			<i class="fas fa-gift fa-4x"></i>
			<p>Latest Product</p>
		</div>
		<!-- 商品瀏覽  -->
		<div class="shopCard">
			<ul>
				<li>
					<div class="productcard" style="width: 18rem;">
						<img src="img/圖片/shop1.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">Elephant Cuppa｜環保減塑 - 大象杯</h5>
							<p class="card-text">170克超輕巧，700cc大容量 透明 Tritan 吸管，粗細吸管任你選擇
								100% 防漏，倒著放也不擔心</p>
							<div class="price">
								<img src="img/ICON/dollar-sign-solid.svg" alt="" />
								<p>99</p>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="productcard" style="width: 18rem;">
						<img src="img/圖片/shop2.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">GT空氣刀 全球同步款 18cm 日式三德刀</h5>
							<p class="card-text">刀具界的革命，世界多國專利保護 刀刃使用日本進口鋼材，鋒利又耐用
								完美的刀刃弧度，使用輕巧流暢更輕鬆 波浪凹紋空氣力學設計，無化學塗層，物理不沾黏</p>
							<div class="price">
								<img src="img/ICON/dollar-sign-solid.svg" alt="" />
								<p>99</p>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="productcard" style="width: 18rem;">
						<img src="img/圖片/shop3.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">CinCin Déco 時尚巧手保溫瓶</h5>
							<p class="card-text">高級 18/8 (304) 食品級不銹鋼。 不含雙酚A BPA Free。
								符合人因提把設計。</p>
							<div class="price">
								<img src="img/ICON/dollar-sign-solid.svg" alt="" />
								<p>99</p>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="productcard" style="width: 18rem;">
						<img src="img/圖片/shop4.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">LEYE 日本製蜂蜜匙</h5>
							<p class="card-text">利用V字型的表面張力設計，只要水平輕輕一轉，蜂蜜不滴落方便使用。
								一體成型好清洗，職人多次嘗試打造，V型匙部與握把平衡簡單好翻轉。 特殊V型角度與縱長，能杓取大量蜂蜜。約一般茶匙(5g)的4倍
							</p>
							<div class="price">
								<img src="img/ICON/dollar-sign-solid.svg" alt="" />
								<p>99</p>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="productcard" style="width: 18rem;">
						<img src="img/圖片/shop5.jpg" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title">Nature 炻釉彩小碗2入-亞麻綠</h5>
							<p class="card-text">Sagaform-瑞典知名的餐廚傢飾品牌，一系列產品皆與北歐最出色的設計師共同合作完成，結合北歐的自然元素以及流行趨勢，完美地呈現北歐餐桌上的精神以及豐富居家生活品質，不論是送禮或是自用，Sagaform都是最佳的選擇。</p>
							<div class="price">
								<img src="img/ICON/dollar-sign-solid.svg" alt="" />
								<p>99</p>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<!--  下一頁標籤  -->
		<div id="shopButton">
			<input type="button" value="more>" class="moreBtn" />
		</div>
		<img src="img/圖片/inline (1).jpg" alt="">
		<div></div>
		<img src="img/圖片/inline (2).jpg" alt="">
		<div></div>
		<img src="img/圖片/inline (3).jpg" alt="">
		<div></div>
		<img src="img/圖片/inline (4).jpg" alt="">
		<div></div>
		<!--  底端列  -->
		<div class="footer">
			<div class="footer-image">
				<img src="img/LOGO/Logo3 (2).png" alt="" />
				<div class="footer-image-bottom">
					<img src="img/ICON/fb.png" alt=""> <img src="img/ICON/tw.png"
						alt=""> <img src="img/ICON/ig.png" alt="">
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