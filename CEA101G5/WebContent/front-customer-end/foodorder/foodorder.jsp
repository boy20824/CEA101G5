<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.text.*"%>

<!-- 取得存在session的購物車元清單 -->
<%
	List<MenuVO> list = (List) session.getAttribute("memuList");
	
%>
<%
// 防止快取
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-customer-end/foodorder/css/css.css" />
<script src="<%=request.getContextPath()%>/front-customer-end/foodorder/js/all.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link href="<%=request.getContextPath()%>/front-customer-end/foodorder/css/all.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
</head>
<body>
<!-- 存餐廳編號 -->
<% session.setAttribute("storeId", list.get(0).getStoreId().trim()); %>
<!-- 從session拿到會員編號 -->
<%MemVO member =(MemVO)session.getAttribute("memLogin") ;%>
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
				<li>
					<img class="side-menu-p" src="<%=request.getContextPath()%>/front-customer-end/foodorder/img/ICON/hambugers.png" alt="menu" /></li>
				<li>
					<a href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">Enak</a>
				</li>
			</ul>
			<div class="loge">
				<c:if test="${empty sessionScope.memLogin}">
					<a href="<%=request.getContextPath()%>/front-customer-end/member/MemLogin.jsp">登入</a>
				</c:if>
				<c:if test="${not empty sessionScope.memLogin}">
					<a href="<%=request.getContextPath()%>/front-customer-end/member/memberPage.jsp">${sessionScope.memLogin.memName}</a>
				</c:if>
			</div>
		</div>

		<div class="order animate__animated animate__bounceInLeft">
			<ul>
				<li>
					<p>訂餐時間</p>
					<%SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
					Date date = new Date();
					String strDate = sdFormat.format(date); %>
					<p><%= sdFormat.format(date)%></p>
				</li>
				<li>
					<p>取餐人姓名</p>
					<p><%=member.getMemName() %></p>
				</li>
				<li>
					<p>取餐人手機</p>
					<p><%=member.getMemPhone() %></p>
				</li>
			</ul>
			<p>訂單資訊</p>
			<FORM method="post" action="<%=request.getContextPath()%>/foodorder/FoodOrderServlet.do">
			<input type="hidden" name="memberPhone" value="<%=member.getMemPhone() %>">
			<c:set var="total" value="${0}" />
			<c:forEach var="menuVO" items="${memuList}">
				<c:set var="total" value="${total + menuVO.quantity * menuVO.menuPrice}" />
			</c:forEach>
			<p>總計:${total}</p>
			<input type="hidden" name="foodOrderTotalPrice" value="${total}">

			<%@ include file="page1.file"%>
			<!-- include先省略 -->
			<c:forEach var="menuVO" items="${memuList}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<input type="hidden" name="menuId" value="${menuVO.menuId}">
				<div class="detail">
					<img src="<%=request.getContextPath()%>/menu/MenuServlet.do?menuId=${menuVO.menuId}&action=getOnePicture" alt="" />
					<div class="detaillist">
						<div class="name">
							<p>品名</p>
							<p>${menuVO.menuName}</p>
							<p>${menuVO.menuDetail}</p>
						</div>
						<div class="math">
							<p>數量</p>
							<p>${menuVO.quantity}</p>
							<input type="hidden" name="menuNum" value="${menuVO.quantity}">
						</div>
						<div class="price">
							<p>金額</p>
							<p>${menuVO.menuPrice}</p>
							<input type="hidden" name="menuPrice" value="${menuVO.menuPrice}">
						</div>
					</div>
				</div>
			</c:forEach>
			<%@ include file="page2.file"%>

			<div class="bthTwo">
				<input type="submit" onclick="webSocketGo()" value="確認送出" /> <input type="button" onclick="location.href='<%=request.getContextPath()%>/front-customer-end/menu/menuindex.jsp'" value="取消" />
			</div>
		</div>
		<input type="hidden" name="storeId"  class="storeId" value="<%= list.get(0).getStoreId().trim()%>">
		<input type="hidden" name="action" value="insert">
	</Form>
<!-- 	註冊推播事件 -->
	<script>
	var storeId = document.querySelector(".storeId");
	var MyPoint = "/TogetherWS/"+123;
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	
	var webSocket;
	function webSocketGo(){
		
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			
			webSocket.send(storeId.value);
			webSocket.close();
	};
		
		
			
	}
	</script>
	
	
		<!--        底端列  -->
		<div class="footer">
			<div class="footer-image">
				<img src="<%=request.getContextPath()%>/front-customer-end/foodorder/img/LOGO/Logo3 (2).png" alt="" />
				<div class="footer-image-bottom">
					<img src="<%=request.getContextPath()%>/front-customer-end/foodorder/img/ICON/fb.png" alt="" /> <img src="<%=request.getContextPath()%>/front-customer-end/foodorder/img/ICON/tw.png"
						alt="" /> <img src="<%=request.getContextPath()%>/front-customer-end/foodorder/img/ICON/ig.png" alt="" />
				</div>
			</div>
			<div class="footer-item">
				<h2>關於Enak</h2>
				<ul>
					<li><i class="fas fa-angle-right"></i> 閱讀我們的部落落</li>
					<li><i class="fas fa-angle-right"></i> 建立企業帳戶</li>
					<li><i class="fas fa-angle-right"></i> 新增您的餐聽</li>
				</ul>
			</div>
			<div class="footer-item">
				<h2>餐廳列表</h2>
				<ul>
					<li><i class="fas fa-angle-right"></i> 辛辣饗宴</li>
					<li><i class="fas fa-angle-right"></i> 暖心火鍋</li>
					<li><i class="fas fa-angle-right"></i> 夜半宵點</li>
					<li><i class="fas fa-angle-right"></i> 台式早餐</li>
				</ul>
			</div>
			<div class="footer-item">
				<h2>服務項目</h2>
				<ul>
					<li><i class="fas fa-angle-right"></i> 美食廣告</li>
					<li><i class="fas fa-angle-right"></i> 取號</li>
					<li><i class="fas fa-angle-right"></i> 定位訂桌</li>
					<li><i class="fas fa-angle-right"></i> 購物商城</li>
				</ul>
			</div>
		</div>
		<div class="footer-bottom">©2020 Enak Food Platform Inc.</div>
	</div>
</body>
</html>