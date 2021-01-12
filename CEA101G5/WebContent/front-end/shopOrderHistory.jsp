<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	session.getAttribute("memLogin");
%>

<jsp:useBean id="orderDetailService" scope="page" class="com.orderdetail.model.OrderDetailService"/>
<jsp:useBean id="productService" scope="page" class="com.product.model.ProductService"/>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EatNAK | 查詢訂單</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
    crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/shopOrderHistory/shopOrderHistoryCSS.css">
</head>

<body>
    <!-- NavBar + Shopping Cart -->
    <div class="sticky-top">
        <nav id="homeNavBar" class="navbar navbar-expand-lg navbar-light bg-warning">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/shopMain.jsp">EatNAK</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/front-end/shopMain.jsp">商城首頁</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/shopProductListing.jsp">商品專區</a>
                        </li>
                        <c:if test = "${memLogin != null}">	
                        	<li class="nav-item">
                            	<a class="nav-link active" href="<%=request.getContextPath()%>/shop/ordermaster.do?action=orderHistoryQuery">查詢訂單</a>
                        	</li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link active" href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp" tabindex="-1" aria-disabled="true">訂餐專區</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                    	<c:if test = "${memLogin != null}">
	                    	<li class="nav-item">
    	                        <a class="nav-link active" aria-current="page" id="memLogOut" href="<%=request.getContextPath()%>/back-end/member/mem.do?action=shopLogout">登出</a>
        	                </li>
						</c:if>
						<c:if test = "${memLogin == null}">
	                        <li class="nav-item">
	                            <img src="<%=request.getContextPath()%>/front-end/shared/user2.svg" alt="" id="navbarUserIcon">
	                        </li>
                        </c:if>
                        <li class="nav-item">
                            <img src="<%=request.getContextPath()%>/front-end/shared/cart3.svg" alt="" id="navbarCartIcon">
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <!-- Shopping Cart Container -->
        <div id="shoppingCartContainer">
			<c:if test = "${buyList == null || buyList.size() == 0}">
				<div id="EmptyCartController" style="text-align: center; padding: 15px 0px;">
					<small class="text-muted">尚無商品</small>
				</div>
			</c:if>
			<table id="shoppingCartTable">
				<c:if test = "${buyList != null || buyList.size() > 0}">
					<c:forEach var="productVO" items="${buyList}">
						<tr>
							<td>
								<img src="<%=request.getContextPath()%>/shop/productphotoreader.do?productId=${productVO.getProductId()}" alt='' width='50' height='50'>
							</td>
							<td>
								<p>${productVO.getProductName()}</p>
							</td>
							<td>
								<p>$${productVO.getProductPrice()}</p>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
            <div>
				<div id="cartButtonContainer">
					<div id=cartCloseButton>
						<button type="button" class="btn btn-warning cartTwoButtonSetup">關閉</button>
					</div>
					<div id=cartCheckOutButton>
						<c:choose>
							<c:when test = "${buyList == null || buyList.size() == 0}">
								<button type="button" class="btn btn-danger cartTwoButtonSetup" disabled>前往結帳</button>
							</c:when>
							<c:otherwise>
								<a href="<%=request.getContextPath()%>/front-end/shopCheckOut.jsp"><button type="button" class="btn btn-danger cartTwoButtonSetup">前往結帳</button></a>
							</c:otherwise>
						</c:choose>						
                    </div>
                </div>
            </div>
        </div>
        <!-- Shopping Cart Container -->
    </div>

    <!-- Signing In -->
    <div id="signInMain" class="signInBackground">
        <div id="signInContainer" class="container">
            <button id="signInCloseButton" type="button" class="btn-close" aria-label="Close"></button>
            <main class="form-signin">
                <form method="POST" action="<%=request.getContextPath()%>/back-end/member/mem.do">
                    <img class="mb-4" src="<%=request.getContextPath()%>/front-end/shared/logoMain2.png" alt="" width="125" height="125">
                    <h1 class="h3 mb-3 fw-normal">登入</h1>
                    <div class="form-group">
                        <label for="inputText" class="visually-hidden">Email address</label>
                        <input type="text" name="memPhone" id="inputText" class="form-control" placeholder="電話號碼" required autofocus>
                        <label for="inputPassword" class="visually-hidden">Password</label>
                        <input type="password" name="memPwd" id="inputPassword" class="form-control" placeholder="密碼" required>
                        <input type="hidden" name="action" value="shopLogin">
                        <div class="checkbox mb-3">
                            <label>
                                <input type="checkbox" value="remember-me"> Remember me
                            </label>
                        </div>
                        <button id="signInButton" class="w-100 btn btn-lg btn-warning" type="submit">Sign
                            in</button>
                        <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
                    </div>
                </form>
            </main>
        </div>
    </div>

	<!-- Order History : Main -->
	<div class="container orderHistoryContainer">
		<c:forEach var="orderMasterVO" items="${orderMasterVOList}">
			<div class="orderHistoryOrder">
				<table>
					<tr>
						<td class="formTitleColor" style="width: 140px;">訂單編號</td>
						<td>${orderMasterVO.getOrderId()}</td>
						<td class="formTitleColor" style="width: 88px;">訂購日期</td>
						<td>${orderMasterVO.getOrderDate()}</td>
						<td class="formTitleColor" style="width: 88px;">會員編號</td>
						<td>${orderMasterVO.getMemPhone()}</td>
					</tr>
					<tr>
						<td class="formTitleColor">收件人姓名</td>
						<td>${orderMasterVO.getRecipientName()}</td>
						<td class="formTitleColor">行動電話</td>
						<td>${orderMasterVO.getRecipientMobNumber()}</td>
						<td class="formTitleColor">室內電話</td>
						<td>${orderMasterVO.getRecipientTelNumber()}</td>
					</tr>
					<tr>
						<td class="formTitleColor">收件人電子郵件</td>
						<td colspan="3">${orderMasterVO.getRecipientEmail()}</td>
						<td class="formTitleColor">統一編號</td>
						<td>${orderMasterVO.getBusinessNumber()}</td>
					</tr>
						<tr>
						<td class="formTitleColor">配送方式</td>
						<c:if test = "${orderMasterVO.getDeliveryMethod() == 0}">
							<td>宅配</td>
						</c:if>
						<c:if test = "${orderMasterVO.getDeliveryMethod() == 1}">
							<td>電子郵件</td>
						</c:if>
						<td class="formTitleColor">配送地址</td>
						<td colspan="4">${orderMasterVO.getDeliveryAddress()}</td>
					</tr>
					<tr>
						<td class="formTitleColor">訂單金額</td>
						<td>$${orderMasterVO.getInvoicePrice()}</td>
						<td class="formTitleColor">結帳日期</td>
						<td>${orderMasterVO.getInvoicePaidDate()}</td>
						<td class="formTitleColor">訂單狀態</td>
						<c:if test = "${orderMasterVO.getOrderStatus() == 0}">
							<td style="color: red;">等待付款中</td>
						</c:if>
						<c:if test = "${orderMasterVO.getOrderStatus() == 1}">
							<td style="color: red;">訂單已確認</td>
						</c:if>
						<c:if test = "${orderMasterVO.getOrderStatus() == 2}">
							<td style="color: red;">訂單處理中</td>
						</c:if>
						<c:if test = "${orderMasterVO.getOrderStatus() == 3}">
							<td style="color: red;">訂單已出貨</td>
						</c:if>
						<c:if test = "${orderMasterVO.getOrderStatus() == 4}">
							<td style="color: red;">取消</td>
						</c:if>
					</tr>
					<tr>
						<td class="formTitleColor">訂單備註</td>
						<td colspan="5">${orderMasterVO.getOrderMemo()}</td>
					</tr>
				</table>
			</div>
			<div class="orderHistoryItems">
				<table>
						<tr style="background-color: #f9f9ff;">
							<td class="formTitleColor">訂單商品</td>					
							<td class="formTitleColor">商品名稱</td>
							<td class="formTitleColor tdAlignCenter">單價</td>
							<td class="formTitleColor tdAlignCenter">數量</td>
						</tr>
					<c:forEach var="orderDetailVO" items="${orderDetailService.getAllOrderDetail(orderMasterVO.getOrderId())}">
						<tr>
							<td class="tdAlignCenter"><img src="<%=request.getContextPath()%>/shop/productphotoreader.do?productId=${orderDetailVO.getProductId()}"></td>					
							<td>${productService.getProductById(orderDetailVO.getProductId()).getProductName()}</td>
							<td class="tdAlignCenter">$${orderDetailVO.getProductPrice()}</td>
							<td class="tdAlignCenter">${orderDetailVO.getQuantity()}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:forEach>
	</div>

	
    <!-- Footer -->
    <footer class="bd-footer p-3 p-md-5 mt-5 bg-warning text-center text-sm-start">
        <div class="container">
            <ul class="bd-footer-links ps-0 mb-3">
                <li class="d-inline-block"><a href="https://www.tibame.com/">TibaMe</a></li>
                <li class="d-inline-block ms-3"><a href="https://developer.mozilla.org/en-US/docs/Web">MDN</a></li>
                <li class="d-inline-block ms-3"><a href="https://www.w3schools.com/">w3schools</a></li>
                <li class="d-inline-block ms-3"><a href="https://tomcat.apache.org/">Tomcat</a></li>
            </ul>
            <p class="mb-0">Designed and built with all the love in the world by <u><strong>CEA101-G5</strong></u>
                with
                the
                help of our fellow
                instructors at TibaMe.</p>
            <p class="mb-0">Currently v1.0.0-beta1. Code licensed MIT, docs CC BY 3.0.</p>
        </div>

        <div class="footerLogoContainer">
            <img src="<%=request.getContextPath()%>/front-end/shared/logoMain.png" class="img-fluid" alt="">
        </div>
    </footer>

    <!-- Javascript  -->
    <script src="<%=request.getContextPath()%>/front-end/shopOrderHistory/shopOrderHistoryScript.js"></script>
    
</body>

</html>