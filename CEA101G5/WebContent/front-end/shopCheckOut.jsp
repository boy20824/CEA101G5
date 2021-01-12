<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EatNAK | 結帳</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
    crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/shopCheckOut/shopCheckOutCSS.css">
</head>

<%
	session.getAttribute("memLogin");
%>

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
                <form method="POST" action="<%=request.getContextPath()%>/back-end/member/mem.do?">
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

	<!-- Check Out (Order Listing) -->
	<div class="container">
		<div class="checkOutListingContainer">
			<table>
				<tr>
					<td class="tdAlignMiddle">訂單商品</td>
					<td class="text-muted">商品名稱</td>
					<td class="tdAlignMiddle text-muted">單價</td>
					<td class="tdAlignMiddle text-muted">數量</td>
					<td class="tdAlignMiddle text-muted">總價</td>
					<td class="tdAlignMiddle text-muted"></td>
				</tr>
				<c:set var="orderTotalPrice" value="0"/>
				<c:forEach var="productVO" items="${buyList}" varStatus="varStatus">
					<c:set var="orderTotalPrice" value="${orderTotalPrice + productVO.getProductPrice() * productVO.getProductQty()}"/>
					<tr class="productRowCounter${varStatus.index}">
						<td class="tdAlignMiddle"><img src="<%=request.getContextPath()%>/shop/productphotoreader.do?productId=${productVO.getProductId()}"></td>
						<td>${productVO.getProductName()}</td>
						<td class="tdAlignMiddle productPrice">$${productVO.getProductPrice()}</td>
						<td class="tdAlignMiddle">
							<div id="QuantityContainer">
								<form class="input-group">
									<button type="button" class="qtyDecreaseBtn" data-productId="${productVO.getProductId()}">-</button>
									<input type="number" class="qtyToPurchase" id="${productVO.getProductId()}qtyToPurchase" data-productId="${productVO.getProductId()}" value="${productVO.getProductQty()}">
									<button type="button" class="qtyIncreaseBtn" data-productId="${productVO.getProductId()}">+</button>
								</form>
							</div>
						</td>
						<td class="tdAlignMiddle totalByProduct">$${productVO.getProductPrice() * productVO.getProductQty()}</td>
						<td class="tdAlignMiddle">
							<button id="deleteBtn" type="button" class="btn-close deleteBtn" data-productId="${productVO.getProductId()}" aria-label="Close"></button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="checkOutListingContainerMemo">
				<p>訂單金額 (${buyList.size()} 件商品) : </p>
				<p id="orderTotalPrice">$${orderTotalPrice}</p>
			</div>
		</div>
	</div>
	
	<!-- Check Out (Order Form) -->
	<div class="container">
		<div class="checkOutFormContainer">
			<form method="POST" action="<%=request.getContextPath()%>/shop/ordermaster.do" id="checkOutForm" class="row g-3">
				<div class="col-md-9">
					<label for="checkOutMemId" class="form-label">會員編號</label>
					<input type="text" class="form-control" id="checkOutMemId" value="${memLogin.getMemPhone()}" name="memPhone" readonly>
				</div>
				<div class="col-md-3">
					<button type="button" class="btn btn-outline-secondary defaultInfoBtn" style="width: 100%;">匯入預設</button>
				</div>
				<div class="col-md-4">
					<label for="checkOutRecipientName" class="form-label">收件人姓名*</label>
					<input type="text" class="form-control" id="checkOutRecipientName" name="recipientName" required>
				</div>
				<div class="col-md-8">
					<label for="checkOutRecipientEmail" class="form-label">電子郵件*</label>
					<input type="email" class="form-control" id="checkOutRecipientEmail" name="recipientEmail" required>
				</div>
				<div class="col-md-6">
					<label for="checkOutRecipientMobNumber" class="form-label">行動電話*</label>
					<input type="number" class="form-control" id="checkOutRecipientMobNumber" name="recipientMobNumber" required>
				</div>
				<div class="col-md-6">
					<label for="checkOutRecipientTelNumber" class="form-label">室內電話</label>
					<input type="number" class="form-control" id="checkOutRecipientTelNumber" name="recipientTelNumber">
				</div>
				<div class="col-md-8">
					<label for="checkOutDeliveryAddress" class="form-label">配送地址*</label>
					<input type="text" class="form-control" id="checkOutDeliveryAddress" name="deliveryAddress" required>
				</div>
				<div class="col-md-4">
					<label for="checkOutBusinessNumber" class="form-label">統一編號</label>
					<input type="number" class="form-control" id="checkOutBusinessNumber" name="businessNumber">
				</div>
				<div class="col-md-12">
  					<label for="checkOutMemo" class="form-label">訂單備註</label>
  					<textarea class="form-control" id="checkOutMemo" name="orderMemo" rows="4"></textarea>
				</div>
				
				<div class='card-wrapper'></div>
				<script src="<%=request.getContextPath()%>/front-end/util/card-master/dist/card.js"></script>

				<div class="col-md-9">
					<label for="creditCardNumber" class="form-label">信用卡號碼</label>
					<input type="text" class="form-control" id="creditCardNumber" placeholder="" name="number" required>
				</div>
				
				<div class="col-md-3">
					<button type="button" class="btn btn-outline-secondary defaultInfoBtn" style="width: 100%;">匯入預設</button>
				</div>

				<div class="col-md-4">
					<label for="creditCardName" class="form-label">持卡人姓名</label>
					<input type="text" class="form-control" id="creditCardName" placeholder="" name="name" required>
				</div>
				
				<div class="col-md-4">
					<label for="creditCardExpiry" class="form-label">有效日期(MM/YY)</label>
					<input type="text" class="form-control" id="creditCardExpiry" placeholder="" name="expiry" required>
				</div>
				
				<div class="col-md-4">
					<label for="creditCardCVC" class="form-label">授權碼</label>
					<input type="text" class="form-control" id="creditCardCVC" placeholder="" name="cvc" required>
				</div>
				
				<div class="checkOutBtns d-flex justify-content-center">
					<button type="reset" class="btn btn-secondary">重設表單</button>
					<button type="submit" class="btn btn-danger">送出訂單</button>
				</div>
				
				<input type="hidden" name="action" value="checkOut">
			</form>
		</div>
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
    <script src="<%=request.getContextPath()%>/front-end/shopCheckOut/shopCheckOutScript.js"></script>
    
    
</body>

</html>