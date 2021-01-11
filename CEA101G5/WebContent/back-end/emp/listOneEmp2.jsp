<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<title>員工資料</title>
</head>
<body bgcolor='white'>
	<div id="mySidebar" class="sidebar">
		<div>
			<img id="logo" src="./image/Logo2 (2).png" class="img-circle"
				alt="User Image">
		</div>
		<br> <br> <br> <br> <br> <span id="empFront">首頁<br>
			<a href="#"> FAQ</a> <a href="#"> 評論檢舉審核</a> <a href="#"> 最新消息管理</a>
		</span> <span id="empShop">商城管理<br> <a href="#"> 商品管理</a> <a
			href="#">訂單處理 </a> <a href="#">回覆買家留言 </a> <a href="#">廣告設置管理 </a> <a
			href="#">促銷活動設置 </a>
		</span> <span id="empStore">餐廳管理<br> <a
			href="<%=request.getContextPath()%>/back-end/storeChar/listAllStoreChar.jsp">
				餐廳分類管理 </a> <a
			href="<%=request.getContextPath()%>/back-end/restaurant/listAllStore.jsp">
				餐廳資訊管理 </a> <a href="#"> 餐廳申請審核 </a>
		</span> <span id="empMem">會員管理<br> <a
			href="<%=request.getContextPath()%>/back-end/member/listAllMem.jsp">
				會員資料管理 </a> <a
			href="<%=request.getContextPath()%>/back-end/member/storeapply.jsp">
				商家註冊審核 </a>
		</span> <span id="empBack">後台管理<br> <a
			href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp">
				員工管理 </a> <a
			href="<%=request.getContextPath()%>/back-end/empauthcategory/listAllEmpAuthCategory.jsp">權限管理
		</a>
		</span>
	</div>
	<div>
		<c:if test="${empty sessionScope.empLogin}">
			<a href="<%=request.getContextPath()%>/back-end/emp/EmpLogin.jsp"
				id="sidebarlogin"> 員工登入/登出 </a>
		</c:if>
		<c:if test="${not empty sessionScope.empLogin}">
			<a href="<%=request.getContextPath()%>/back-end/emp/emp.do?action=logout" id="sidebarlogin">員工登入/登出 </a>
		</c:if>

	</div>
	<div id=backSidebar></div>

<table class="table  table-striped">
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>到職日</th>
		<th>員工狀態</th>
		<th>圖片</th>
		
	</tr>
	<tr>
		<td>${empVO.emp_id}</td>
		<td>${empVO.emp_name}</td>
		<td>${empVO.emp_account}</td>
		<td>${empVO.emp_password}</td>
		<td>${empVO.emp_date}</td>
		<td>${empVO.emp_status}</td>
		<td ><img width=100 height=100
       src="<%=request.getContextPath()%>/back-end/emp/emp.do?emp_id=${empVO.emp_id}&action=getEmpPic"></td>
<%-- 		<td><%=empVO.getEmp_pic()%></td> --%>
	</tr>
</table>
<div align="center">
<a href="<%=request.getContextPath()%>/back-end/back-empselect.jsp" class="btn btn-danger">回首頁</a>
</div>
</body>
</html>