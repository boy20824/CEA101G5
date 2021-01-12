<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/emp/css/css.css">
<title>員工資料新增</title>

<style>
.addEmpBlock {
	width: 30%;
	margin-left: 750px;
	margin-top: 20px;
}
</style>

</head>
<body>
	<div id="mySidebar" class="sidebar">
		<div>
			<img id="logo" src="<%=request.getContextPath()%>/front-end/shared/logoMain2.png" class="img-circle"
				alt="User Image">
		</div>
		<br><br><br><br><br>
		<span id="empFront">首頁<br>
			<a href="#"> FAQ</a>
			<a href="#"> 評論審核</a>
			<a href="#"> 最新消息管理</a>
		</span> 
		<span id="empShop">商城管理<br>
			<a href="<%=request.getContextPath()%>/back-end/shopOrderMasterListAll.jsp">訂單處理</a> 
			<a href="<%=request.getContextPath()%>/back-end/shopProductListAll.jsp"> 商品管理</a>
			<a href="<%=request.getContextPath()%>/back-end/productqa/select_productqa_page.jsp">商品問與答</a>
			<a href="<%=request.getContextPath()%>/back-end/shopProductReviewListAll.jsp"> 商品評價管理</a>
			<a href="#">廣告設置管理 </a> 
			<a href="#">促銷活動設置 </a>
		</span>
		<span id="empStore">餐廳管理<br> 
			<a href="<%=request.getContextPath()%>/back-end/storeChar/listAllStoreChar.jsp">餐廳分類管理</a> 
			<a href="<%=request.getContextPath()%>/back-end/restaurant/listAllStore.jsp"> 餐廳資訊管理 </a>
			<a href="<%=request.getContextPath()%>/back-end/restaurant/storeapply.jsp"> 餐廳申請審核 </a>
		</span> 
		<span id="empMem">會員管理<br> 
			<a href="<%=request.getContextPath()%>/back-end/member/listAllMem.jsp">會員資料管理</a>
			<a href="#">會員儲值管理</a>
		</span>
		<span id="empBack">後台管理<br> 
			<a href="<%=request.getContextPath()%>/back-end/emp/listAllEmp.jsp"> 員工管理 </a>
			<a href="<%=request.getContextPath()%>/back-end/empauthcategory/listAllEmpAuthCategory.jsp">權限管理</a>
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
	<div class="main">
		<img style="margin-left: 600px;"
			src="<%=request.getContextPath()%>/back-end/emp/image/Logo2 (2).png"
			alt="">
		<p style="margin-left: 620px; font-size: 40px; font-weight: bold;">歡迎來到管理者後台</p>
	</div>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date emp_date = null;
try {
	emp_date = empVO.getEmp_date();
} catch (Exception e) {
	emp_date = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
	/* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
	/* height:  151px; */
}
</style>

<script>
                        $.datetimepicker.setLocale('zh');
                        $('#f_date1').datetimepicker({
                            theme: '', //theme: 'dark',
                            timepicker: false, //timepicker:true,
                            step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                            format: 'Y-m-d', //format:'Y-m-d H:i:s',
                            value: '<%=emp_date%>
                            ', // value:   new Date(),
                            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
                            //startDate:	            '2017/07/10',  // 起始日
                            //minDate:               '-1970-01-01', // 去除今日(不含)之前
                            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
                        });

                        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

                        //      1.以下為某一天之前的日期無法選擇
                        //      var somedate1 = new Date('2017-06-15');
                        //      $('#f_date1').datetimepicker({
                        //          beforeShowDay: function(date) {
                        //        	  if (  date.getYear() <  somedate1.getYear() || 
                        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
                        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                        //              ) {
                        //                   return [false, ""]
                        //              }
                        //              return [true, ""];
                        //      }});

                        //      2.以下為某一天之後的日期無法選擇
                        //      var somedate2 = new Date('2017-06-15');
                        //      $('#f_date1').datetimepicker({
                        //          beforeShowDay: function(date) {
                        //        	  if (  date.getYear() >  somedate2.getYear() || 
                        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
                        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                        //              ) {
                        //                   return [false, ""]
                        //              }
                        //              return [true, ""];
                        //      }});

                        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
                        //      var somedate1 = new Date('2017-06-15');
                        //      var somedate2 = new Date('2017-06-25');
                        //      $('#f_date1').datetimepicker({
                        //          beforeShowDay: function(date) {
                        //        	  if (  date.getYear() <  somedate1.getYear() || 
                        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
                        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                        //		             ||
                        //		            date.getYear() >  somedate2.getYear() || 
                        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
                        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                        //              ) {
                        //                   return [false, ""]
                        //              }
                        //              return [true, ""];
                        //      }});
                    </script>

</html>