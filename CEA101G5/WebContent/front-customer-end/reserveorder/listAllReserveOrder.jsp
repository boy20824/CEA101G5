<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reserveorder.model.*"%>

<jsp:useBean id="memSvc" scope="session" class="com.member.model.MemService"/>
<%	
	String memPhone = request.getParameter("memId");
	if (memPhone == null){
		memPhone = (String)session.getAttribute("memPhone");
		if (memPhone == null){
			memPhone = request.getParameter("memphone");
		}
	}
    ReserveOrderService reserveOrderSvc = new ReserveOrderService();
    List<ReserveOrderVO> list = reserveOrderSvc.getForc(memPhone,0);
    pageContext.setAttribute("list",list);
    session.setAttribute("memPhone",memPhone);
%>
<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
<%-- <jsp:useBean id="roSvc" scope="page" class="com.reserveorder.model.ReserveOrderService"/> --%>
<jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
<html>
<head>
<title>預定中的訂位</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>加入Enak</title>
<link rel="icon"
	href="<%=request.getContextPath()%>/front-customer-end/front/img/favicon.ico"
	type="image/x-icon" />
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-customer-end/member/css/css.css" />
<link
	href="<%=request.getContextPath()%>/front-customer-end/member/css/all.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/front-customer-end/member/js/all.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous" />
<!-- <script -->
<!-- 	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" -->
<!-- 	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" -->
<!-- 	crossorigin="anonymous"></script> -->
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
<!--AJAX -->
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
<style>
  table {
 	width: 800px;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th{
     color:#ffffff;
  }
  
  table th{
     background-color:#FF615F;
  }
  
  table, td{
     color:#000000;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .button{
	  background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
  }

  .tableborder{
  background-color: white;
  margin:20px;
  }
 .listAllReserveOrderBlock{
 	padding-top:200px;
 	margin-botton:100px;
 	text-align:center;
 }
 .listAllReserveOrderBlock a{
 	width: 100%;
    background-color: #FF615F;
    color: white;
    padding: 16px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-top:30px;
 
 }
</style>

</head>
<body bgcolor='white'>

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
						href="<%=request.getContextPath()%>/front-store-end/restaurant/WelcomePage.jsp"><i
						class="fas fa-bullhorn"></i>商家入口 </a>
				</nav>
			</div>
			<div class="side-menu-black"></div>
		</div>
		<!--  上標題  -->
		<div class="forfiexed">
			<ul class="title">
				<li><img class="side-menu-p"
					src="<%=request.getContextPath()%>/front-customer-end/member/img/ICON/hambugers.png"
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
						href="<%=request.getContextPath()%>/back-end/member/mem.do?action=logout">
						${sessionScope.memLogin.memName} </a>
				</c:if>
			</div>
			</div>
		</div>
		<div class="listAllReserveOrderBlock">
		
		<a href="<%=request.getContextPath() %>/front-customer-end/reserveorder/oldReserveOrder.jsp" class="button">查詢歷史訂位訊息</a>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="tableborder">
<%@ include file="page1.file" %> 
<table  class="table table-striped">
<tr>
	<td colspan="8" style="background-color:#ffffff;">預定中的訂位</td>
</tr>
	<tr>
		<th>餐廳名稱</th>
		<th>用餐日期</th>
		<th>用餐時間</th>
		<th>用餐人數</th>
		<th>訂位狀態</th>
		<th>特殊需求</th>
		<th>下訂時間</th>
		<th>取消訂位</th>
	</tr>
	
	<c:forEach var="reserveOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${restSvc.getOneRestaurant(reserveOrderVO.storeId).storeName}</td>
			<td>${reserveOrderVO.reserveTime}</td>
			<td><fmt:formatDate value="${arSvc.getOneAcceptReserve(reserveOrderVO.storeId,reserveOrderVO.periodId).startTime}" pattern="HH:mm"/></td>
			<td>${reserveOrderVO.reserveAdult}</td>
			<!-- JSP會自動幫你setAttribute EL會自動幫你getAttribute -->
<%-- 			<td>${reserveOrderVO.reserveStatus}</td> <!-- 如果要用jsp寫法java.text.DateFormat 要先setAttribute 再get很麻煩 --> --%>
			<td>
				<c:if test="${reserveOrderVO.reserveStatus==0}">
					預訂中
				</c:if>
			</td>
			
			<td>${reserveOrderVO.reserveNote}</td>
			<!-- 直接DateTimePicker的formatDate  然後上面要加 tablib fmt 1210 11有講 -->
			<td><fmt:formatDate value="${reserveOrderVO.reserveCreateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/reserveorder/reserveorder.do" style="margin-bottom: 0px;">
				     <input type="submit" value="取消訂位" class="button"><!-- 送出該筆要修改的資料 -->
				     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}">
				     <input type="hidden" name="storeid" value="${reserveOrderVO.storeId}">
				     <input type="hidden" name="memphone" value="${reserveOrderVO.memPhone}">
				     <input type="hidden" name="reservetime" value="${reserveOrderVO.reserveTime}">
				     <input type="hidden" name="periodid" value="${reserveOrderVO.periodId}">
				     <input type="hidden" name="reserveadult" value="${reserveOrderVO.reserveAdult}">
				     <input type="hidden" name="reservechild" value="${reserveOrderVO.reserveChild}">
				     <input type="hidden" name="reservestatus" value="${reserveOrderVO.reserveStatus}">
				     <input type="hidden" name="reservenote" value="${reserveOrderVO.reserveNote}">
				     <input type="hidden" name="reservecreatetime" value="${reserveOrderVO.reserveCreateTime}">
				     
				     <input type="hidden" name="action"	value="getOne_For_Update">
				     <input type="hidden" name="choose"	value="updateforc">
				     </FORM>
				</td>
			<c:if test="${reserveOrderVO.reserveStatus != 0}">
			<td></td>
			</c:if>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-customer-end/reserveorder/reserveorder.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
</div>
<script>
if ( window.history.replaceState ) {
    window.history.replaceState( null, null, window.location.href );
}</script>

<div class="footer">
		<div class="footer-image">
			<img
				src="<%=request.getContextPath()%>/front-customer-end/member/img/LOGO/Logo3 (2).png"
				alt="" />
			<div class="footer-image-bottom">
				<img
					src="<%=request.getContextPath()%>/front-customer-end/member/img/ICON/fb.png"
					alt=""> <img
					src="<%=request.getContextPath()%>/front-customer-end/member/img/ICON/tw.png"
					alt=""> <img
					src="<%=request.getContextPath()%>/front-customer-end/member/img/ICON/ig.png"
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

</body>
</html>