<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	session.getAttribute("memLogin");
%>

<jsp:useBean id="promotionDetailService" scope="page" class="com.promotiondetail.model.PromotionDetailService"/>
<jsp:useBean id="memService" scope="page" class="com.member.model.MemService"/>
<jsp:useBean id="orderMasterService" scope="page" class="com.ordermaster.model.OrderMasterService"/>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EatNAK | 瀏覽商品</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
    crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/shopProduct/shopProductCSS.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/util/slick-1.8.1/slick/slick.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/util/slick-1.8.1/slick/slick-theme.css">
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

	<!-- Product Details -->
	<div class="container productContainer">
		<div class="productContainerLeft">
			<div class="productPhotoMain">
				<c:forEach var="productPhotoVO" items="${productPhotoVOList}">
					<img src="<%=request.getContextPath()%>/shop/shopproductphotoreader.do?productId=${productPhotoVO.getProductId()}&productPhotoId=${productPhotoVO.getProductPhotoId()}">
				</c:forEach>
			</div>
			<div class="productPhotoPreviews">
				<c:forEach var="productPhotoVO" items="${productPhotoVOList}">
					<img src="<%=request.getContextPath()%>/shop/shopproductphotoreader.do?productId=${productPhotoVO.getProductId()}&productPhotoId=${productPhotoVO.getProductPhotoId()}">
				</c:forEach>
			</div>
			<div class="socialMedia">
				<span>分享:</span>
				<img src="<%=request.getContextPath()%>/front-end/shopProduct/images/facebook.svg">
				<img src="<%=request.getContextPath()%>/front-end/shopProduct/images/instagram.svg">
				<img src="<%=request.getContextPath()%>/front-end/shopProduct/images/twitter.svg">
				<img src="<%=request.getContextPath()%>/front-end/shopProduct/images/pinterest.svg">
				<img src="<%=request.getContextPath()%>/front-end/shopProduct/images/snapchat.svg" id="snapChat">
			</div>
		</div>
		<div class="productContainerRight">
			<div id="productTitle">
				<p class="text-muted">商品名稱:</p>
				<h5>${productVO.getProductName()}</h5>
			</div>
			<div id="productDescription">
				<p class="text-muted">商品介紹:</p>
				<p>${productVO.getProductDescription()}</p>
			</div>
			<div id="productPrice">
				<p class="text-muted">商品售價: </p>
				<small id="productListPrice">$${productVO.getProductMSRP()}</small>
				<c:choose>
					<c:when test = "${productVO.getProductPrice() > promotionDetailService.getPromotionDetailByProductId(productVO.getProductId()).getProductPrice()}">
						<h5 id="productPromotionalPrice">限時特賣$${promotionDetailService.getPromotionDetailByProductId(productVO.getProductId()).getProductPrice()}</h5>
					</c:when>
					<c:otherwise>
						<h5 id="productPromotionalPrice">$${productVO.getProductPrice()}</h5>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="QuantityContainer">
				<p class="text-muted">購買數量:<p>
				<form method="POST" action="<%=request.getContextPath()%>/shop/ordermaster.do?action=addToCartAndCheckOut&productId=${productVO.getProductId()}" id="purchaseQtyForm" class="input-group">
					<button type="button" id="qtyDecreaseBtn">-</button>
					<input type="number" id="qtyToPurchase" name="productQty" value="1">
					<button type="button" id="qtyIncreaseBtn">+</button>
				</form>
			</div>
			<div id="actionButtons">
				<button id="addToCart" type="button" class="btn btn-warning">加入購物車</button>
				<input id="productIdToJS" type="hidden" value="${productVO.getProductId()}"></input>
				<button id="buyNow" type="submit" form="purchaseQtyForm" class="btn btn-danger">直接購買</button>
			</div>
			<div id="productMemos">
				<span><img src="<%=request.getContextPath()%>/front-end/shopProduct/images/return-button.svg"></span>
				<small>15天鑑賞期</small>
				<span><img src="<%=request.getContextPath()%>/front-end/shopProduct/images/truck.svg"></span>
				<small>退貨無負擔</small>
				<span><img src="<%=request.getContextPath()%>/front-end/shopProduct/images/tick.svg"></span>
				<small>假一賠二</small>
			</div>
		</div>
	</div>
	
	<!-- Product To Cart -->
	<div class="container addToCartPopContainer">
		<div class="addToCartImgContainer">
			<img src="<%=request.getContextPath()%>/front-end/shopProduct/images/check.svg">
		</div>
		<div>
			<p>商品已加入購物車</p>
		</div>
	</div>
	
	<!-- Product QA & Review Tab -->
	<div class="container qaOrReviewTab">
		<div class="qaTab">
			<h6 class="qaReviewBtnFocus" id="qaButton">商品問與答(${productQAVOList.size()})</h6>
		</div>
		<div class="reviewTab">
			<h6 class="qaReviewBtnBlur" id="reviewButton">商品評價(${orderDetailVOListNumOfReviews.size()})</h6>
		</div>
	</div>
	
	<!-- Product QA -->
	<div id="productQAContainer" class="container productQAContainer">
		<c:forEach var="productQAVO" items="${productQAVOList}" varStatus="QACount">
			<div class="productQA">
				<div class="productQAQuestion">
					<p id="productQAQuestionNo">問題 ${QACount.count}</p>
					<p id="productQAQuestionMemName">${memService.getOneMem(productQAVO.getMemPhone()).getMemName()}</p>
					<p id="productQAQuestionMemPhone">${productQAVO.getMemPhone()}</p>
					<p id="productQAQuestionTS">${productQAVO.getProductQuesTstamp()}</p>
					<p>${productQAVO.getProductQues()}</p>
				</div>
				<div class="productQAReply">
					<c:choose>
						<c:when test = "${not empty productQAVO.getProductReply()}">
							<p id="productQAReplyTitle">商城回覆</p>
							<p id="productQAReplyTS">${productQAVO.getProductReplyTstamp()}</p>
							<p>${productQAVO.getProductReply()}</p>
						</c:when>
						<c:otherwise>
							<p id="productQAReplyTitle">等待商城回覆中....</p>
						</c:otherwise>	
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<!-- Product Review -->
	<div id="productReviewContainer" class="container productReviewContainer qaAndReviewDisplayController">
		<c:forEach var="orderDetailVO" items="${orderDetailVOList}">
			<c:if test = "${orderDetailVO.getProductReview() != null}">
				<div class="productReview">
					<div class="productReviewUnit">
						<p id="productQAQuestionMemName">${memService.getOneMem(orderMasterService.getOrderById(orderDetailVO.getOrderId()).getMemPhone()).getMemName()}</p>
						<p id="productQAQuestionMemPhone">${orderMasterService.getOrderById(orderDetailVO.getOrderId()).getMemPhone()}</p>
						<p id="productQAQuestionTS">${orderDetailVO.getProductReviewTS()}</p>
						<p>${orderDetailVO.getProductReview()}</p>
						<img src="<%=request.getContextPath()%>/shop/orderdetailphotoreader.do?productId=${orderDetailVO.getProductId()}&orderId=${orderDetailVO.getOrderId()}">
					</div>
				</div>
			</c:if>			
		</c:forEach>
	</div>

	<!-- QA Submission Container -->
	<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			<script>alert("${message}")</script>
		</c:forEach>
	</c:if>
	<c:if test="${memLogin != null}">
		<div class="container qaSubmissionContainer">
			<form id="qaForm" method="POST" action="<%=request.getContextPath()%>/shop/productqa.do">
				<h5>我要發問</h5>
				<div>
					<textarea id="qaSubmissionTextArea" name="productQues" form="qaForm" rows="6" cols="100" maxlength="300" placeholder=""></textarea>
					<input type="hidden" name="action" value="submitQA">
					<input type="hidden" name="productId" value="${productVO.getProductId()}">
					<input type="hidden" name="memPhone" value="${memLogin.getMemPhone()}">
				</div>
				<div class="qaNotesUnderTextArea">
					<small>私下完成交易受詐風險高，請勿透過 Line 等通訊軟體私下聯絡或匯款，選擇賣場結帳付款交易享最高五萬元保障。</small>
				</div>
				<div class="qaTwoButtonGroup">
					<button id="productQuesResetBtn" type="button" class="btn btn-warning">重設</button>
					<button type="submit" class="btn btn-warning">提出問題</button>
				</div>
			</form>
		</div>
	</c:if>
	<c:if test="${memLogin == null}">
		<div class="container">
			<div class="d-grid gap-2 col-3 mx-auto loginRequiredContainer">
				<button id="loginBtn" class="btn btn-secondary" type="button">請先登入在進行發問</button>
			</div>
		</div>
	</c:if>

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
    <script src="<%=request.getContextPath()%>/front-end/util/slick-1.8.1/slick/slick.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/shopProduct/shopProductScript.js"></script>
    
</body>

</html>