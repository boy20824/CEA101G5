<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>修改會員資料</title>
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
				<li><img class="side-menu-p" src="<%=request.getContextPath()%>/front-customer-end/member/img/ICON/hambugers.png"
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
		<div class="memPageBlock">
			<p>會員中心</p>
			<ul class="memPageList">
				<li>
					<div class="memChoseList">
						<a href="#"><i class="far fa-address-card"></i></i></i></a> <a
							href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
							<p>個人資料</p>
						</a>
					</div>
				</li>
				<li>
					<div class="memChoseList">
						<a href="#"><i class="fas fa-concierge-bell"></i></i></i></a> <a
							href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
							<p>訂位資訊</p>
						</a>
					</div>
				</li>
				<li>
					<div class="memChoseList">
						<a href="#"><i class="far fa-credit-card"></i></i></i></a> <a
							href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
							<p>儲值專區</p>
						</a>
					</div>
				</li>
				<li>
					<div class="memChoseList">
						<a href="#"><i class="fas fa-pizza-slice"></i></i></a> <a
							href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
							<p>外帶訂單</p>
						</a>
					</div>
				</li>
				<li>
					<div class="memChoseList">
						<a href="#"><i class="far fa-comments"></i></i></a> <a
							href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
							<p>歷史評論</p>
						</a>
					</div>
				</li>
				<li>
					<div class="memChoseList">
						<a href="#"><i class="fas fa-exclamation-circle"></i></a> <a
							href="<%=request.getContextPath()%>/front-customer-end/front/front.jsp">
							<p>檢舉資訊</p>
						</a>
					</div>
		</div>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/back-end/member/mem.do"
			name="form1" enctype="multipart/form-data">
			<div id="memberPageTableBlock">
				<table id="memberPageTable">

					<tr>
						<td colspan="2" align="center"><font color="#475665"
							id="joinUs">你的資料</font></td>
					</tr>

					<tr>
						<td>大頭照:</td>
						<td><img width=100 height=100
							src="<%=request.getContextPath()%>/back-end/member/memPhotoReader.do?memPhone=${memLogin.memPhone}">
							<input type="file" name="memPhoto"></td>
					</tr>

					<tr>
						<td>手機號碼:</td>
						<td><input type="TEXT" name="memPhone" size="45"
							value="<%=memVO.getMemPhone()%>" readonly /></td>
					</tr>

					<tr>
						<td>密碼:</td>
						<td><input type="password" name="memPwd" size="45"
							value="<%=memVO.getMemPwd()%>" /></td>
					</tr>

					<tr>
						<td>姓名:</td>
						<td><input type="TEXT" name="memName" size="45"
							value="<%=memVO.getMemName()%>" /></td>
					</tr>

					<tr>
						<td>地址:</td>
						<td><input type="TEXT" name="memAddress" size="45"
							value="<%=memVO.getMemAddress()%>" /></td>
					</tr>

					<tr>
						<td>性別:</td>
						<c:choose>
							<c:when test="${memLogin.memSex ==0}">
								<td>男<input type="radio" name="memSex" value="0" checked
									readonly> 女 <input type="radio" name="memSex" value="1"
									readonly>
								</td>
							</c:when>
							<c:when test="${memLogin.memSex ==1}">
								<td>男<input type="radio" name="memSex" value="0" readonly>
									女 <input type="radio" name="memSex" value="1" checked readonly>
								</td>
							</c:when>
						</c:choose>
					</tr>

					<tr>
						<td>電子信箱:</td>
						<td><input type="email" name="memEmail" size="45"
							value="<%=memVO.getMemEmail()%>" /></td>
					</tr>

					<tr>
						<td>身分證字號:</td>
						<td><input type="TEXT" name="memIdentity" size="45"
							value="<%=memVO.getMemIdentity()%>" readonly /></td>
					</tr>

					<tr>
						<td>生日:</td>
						<td><input name="memBirth" id="Birthday" type="TEXT"
							value="<%=memVO.getMemBirth()%>" readonly /></td>

					</tr>

					<tr>
						<td>暱稱:</td>
						<td><input type="TEXT" name="memNick" size="45"
							value="<%=memVO.getMemNick()%>" /></td>
					</tr>

					<tr>
						<td>商業營業統一編號:</td>
						<td><input type="TEXT" name="memLice" size="45"
							data-max-words="10" value="<%=memVO.getMemLice()%>" /></td>
					</tr>

					<tr>
						<td>信用卡卡號:</td>
						<td><input type="TEXT" name="memCardNumber" size="45"
							value="<%=memVO.getMemCardNumber()%>"></td>
					</tr>
					<tr>
						<td>卡片持有者:</td>
						<td><input type="TEXT" name="memCardHolder" size="45"
							value="<%=memVO.getMemCardHolder()%>"></td>
					</tr>
					<tr>
						<td>到期日:</td>
						<td><input type="TEXT" name="memCardExpirationDate" size="45"
							value="<%=memVO.getMemCardExpirationDate()%>" /></td>
					</tr>
					<tr>
						<td>CCV:</td>
						<td><input type="TEXT" name="memCardCCV" size="45"
							value="<%=memVO.getMemCardCCV()%>" /></td>
					</tr>

					<tr>
						<td colspan="2" align="center">
						<input type="hidden" name="memAuth" value="<%=memVO.getMemAuth()%>"> 
						<input type="hidden" name="memTotalRechar" value="<%=memVO.getMemTotalRechar()%>"> 
						<input type="hidden" name="memCondition" value="<%=memVO.getMemCondition()%>"> 
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="comeFrom" value="fromMemUpdate"> 
						<input type="submit" value="送出修改"></td>
					</tr>
				</table>
			</div>
		</FORM>

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
	</div>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date memBirth = null;
try {
	memBirth = memVO.getMemBirth();
} catch (Exception e) {
	memBirth = new java.sql.Date(System.currentTimeMillis());
}
%>
<script>
                        $.datetimepicker.setLocale('zh');
                        $('#Birthday').datetimepicker({
                            theme: '', //theme: 'dark',
                            timepicker: false, //timepicker:true,
                            step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                            format: 'Y-m-d', //format:'Y-m-d H:i:s',
                            value: '<%=memBirth%>
                            ', // value:   new Date(),
                            maxDate: '+1970-01-01' // 去除今日(不含)之後

                        });
                    </script>

</html>