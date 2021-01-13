<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.menu.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodorderdetail.model.*"%>
<%@ page import="com.foodorder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.member.model.*"%>
<!-- 從session拿到會員編號 -->
<%MemVO member =(MemVO)session.getAttribute("memLogin") ;%>
<%
	FoodOrderService foodOrderSvc = new FoodOrderService();
	List<FoodOrderVO> list = foodOrderSvc.getAllByMemberPhoneStatus2(member.getMemPhone());
	pageContext.setAttribute("list", list);
%>

<!-- 呼叫訂單明細 -->
<jsp:useBean id="foodOrderDetailSvc" scope="page" class="com.foodorderdetail.model.FoodOrderDetailService" />
<!-- 呼叫餐點 -->
<jsp:useBean id="menuSvc" scope="page" class="com.menu.model.MenuService" />
<!-- 呼叫餐廳 -->
<jsp:useBean id="restaurantSvc" scope="page" class="com.restaurant.model.RestaurantService" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-customer-end/customerorder/customerordercss.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/front-customer-end/customerorder/customerorderjs.js"></script>
    <script src="<%=request.getContextPath()%>/front-customer-end/customerorder/js/all.js"></script>
    <link href="<%=request.getContextPath()%>/front-customer-end/menu/css/all.css" rel="stylesheet" />
</head>
<body>

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
			</div>
		</div>

    <div class="content">
        <p>過去的訂單</p>
        <% int i = 0 ; %>
        <c:forEach var="foodOrderVO" items="${list}">
        <div class="detail">
            <img src="<%=request.getContextPath()%>/front-customer-end/customerorder/餐廳.jpg" alt="">
            <div class="detailMenu">
                <h1>${restaurantSvc.getOneRestaurant(foodOrderVO.storeId).storeName}</h1>
				<!--取得總價 -->
                <c:set var="total" value="${0}" />
				<c:forEach var="foodOrderDetailVO" items="${foodOrderDetailSvc.getAll(foodOrderVO.getFoodOrderId())}">
					<c:set var="total" value="${total + foodOrderDetailVO.menuNum * foodOrderDetailVO.menuPrice}" />
				</c:forEach>
                <p><span>${foodOrderDetailSvc.getAll(foodOrderVO.getFoodOrderId()).size()}</span>份餐點,共$<span>${total}</span>元,<span><fmt:formatDate value="${foodOrderVO.foodOrderCompleteTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
                <div class="menu">
                 <c:forEach var="foodOrderDetailVO" items="${foodOrderDetailSvc.getAll(foodOrderVO.getFoodOrderId())}">
                    <p><span>數量:</span><span>${foodOrderDetailVO.menuNum}份</span>${menuSvc.getOneMenu(foodOrderDetailVO.getMenuId()).menuName}</p>
                    </c:forEach>
                    <div class="open-btn">
                        <a class="showMore">查看更多..</a>
                    </div>
                    <input type="button" class="cmt" value="${foodOrderVO.foodOrderCmtStatus==0?'評論餐點':'已評論'}"${foodOrderVO.foodOrderCmtStatus==0?'':'disabled style="background-color:gray;"'}>
                </div> 
                
            </div>
        </div> 
        <div class="box">
        <div class="content1">
            <div class="cancel">
                <img src="<%=request.getContextPath()%>/front-customer-end/customerorder/取消.png" alt="">
            </div>
            <div class="picture">
                <img src="<%=request.getContextPath()%>/front-customer-end/customerorder/餐廳.jpg" alt="">
            </div>
            <div class="restaurantContent">
                <p>是否滿意<span>${restaurantSvc.getOneRestaurant(foodOrderVO.storeId).storeName}餐廳</span>?</p>
                <p>讓餐廳知道您的想法。</p>
            </div>
            <form method="post" action="<%=request.getContextPath()%>/restaurantcmt/RestaurantCmt.do">
            <input type="hidden" name="storeId" value="${foodOrderVO.storeId}">
            <input type="hidden" name="foodOrderId" value="${foodOrderVO.foodOrderId }">
            <input type="hidden" name="memPhone" value="<%=member.getMemPhone()%>">
            <input type="hidden" name="action" value="insert">
            <div class="star">
                <input type="radio" name="item<%= ++i%>" id="item01_<%= i%>" value="1"/>
                <label class="star-item" for="item01_<%= i%>" title="不良"></label>
                <input type="radio" name="item<%= i%>" id="item02_<%= i%>" value="2"/>
                <label class="star-item" for="item02_<%= i%>" title="不推薦"></label>
                <input type="radio" name="item<%= i%>" id="item03_<%= i%>" value="3" />
                <label class="star-item" for="item03_<%= i%>" title="普通"></label>
                <input type="radio" name="item<%= i%>" id="item04_<%= i%>" value="4"/>
                <label class="star-item" for="item04_<%= i%>" title="很好"></label>
                <input type="radio" name="item<%= i%>" id="item05_<%= i%>" value="5"/>
                <label class="star-item" for="item05_<%= i%>" title="完美"></label>
            </div>
            <textarea name="cmt" placeholder="提供更多意見回饋"></textarea>
            <input type="submit" value="送出">
        </div>
    </div>
    </form>
        </c:forEach>
      
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
    <script>
    	let menu = document.querySelectorAll('.menu');
    	let openBtn = document.querySelectorAll('.open-btn')
    	
    	for(let i=0;i<menu.length;i++){
    		 if(menu[i].offsetHeight >=94){
    			 	openBtn[i].style.display="block";
    	            menu[i].style.height="95px";
    	            menu[i].style.overflow="hidden";
    	        }
    	}
        // 点击显示更多按钮
        let openBtnA = document.querySelectorAll(".open-btn a")
        let showMore = document.querySelectorAll('.showMore')
        for(let i=0;i<showMore.length;i++){
        	showMore[i].addEventListener('click',function(){
        		menu[i].style.height="auto";
        		openBtnA[i].style.display="none"
        		openBtn[i].style.display="none"
        	})
        }
    </script>
</body>
</html>