<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queueperiod.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	QuePeriodService quePeriodSvc = new QuePeriodService();
	List<QuePeriodVO> list = quePeriodSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/quePeriod/css/css.css" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
<title>取號時段設定</title>
</head>

<body>
	<div id="mySidebar" class="sidebar">
		<span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="utensils"
				class="svg-inline--fa fa-utensils fa-w-13" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 416 512">
          <a href="https://www.google.com/">
            <path fill="#9C9C9C"
						d="M207.9 15.2c.8 4.7 16.1 94.5 16.1 128.8 0 52.3-27.8 89.6-68.9 104.6L168 486.7c.7 13.7-10.2 25.3-24 25.3H80c-13.7 0-24.7-11.5-24-25.3l12.9-238.1C27.7 233.6 0 196.2 0 144 0 109.6 15.3 19.9 16.1 15.2 19.3-5.1 61.4-5.4 64 16.3v141.2c1.3 3.4 15.1 3.2 16 0 1.4-25.3 7.9-139.2 8-141.8 3.3-20.8 44.7-20.8 47.9 0 .2 2.7 6.6 116.5 8 141.8.9 3.2 14.8 3.4 16 0V16.3c2.6-21.6 44.8-21.4 48-1.1zm119.2 285.7l-15 185.1c-1.2 14 9.9 26 23.9 26h56c13.3 0 24-10.7 24-24V24c0-13.2-10.7-24-24-24-82.5 0-221.4 178.5-64.9 300.9z"></path>
          </a>
        </svg> <a href="https://www.google.com/">
				<p>餐廳</p>
		</a>
		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="concierge-bell"
				class="svg-inline--fa fa-concierge-bell fa-w-16" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <a href="https://www.google.com/">
            <path fill="#9C9C9C"
						d="M288 130.54V112h16c8.84 0 16-7.16 16-16V80c0-8.84-7.16-16-16-16h-96c-8.84 0-16 7.16-16 16v16c0 8.84 7.16 16 16 16h16v18.54C115.49 146.11 32 239.18 32 352h448c0-112.82-83.49-205.89-192-221.46zM496 384H16c-8.84 0-16 7.16-16 16v32c0 8.84 7.16 16 16 16h480c8.84 0 16-7.16 16-16v-32c0-8.84-7.16-16-16-16z"></path>
          </a>
        </svg> <a href="https://www.google.com/">
				<p>外帶</p>
		</a>
		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="vote-yea"
				class="svg-inline--fa fa-vote-yea fa-w-20" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512">
          <a
					href="<%=request.getContextPath()%>/quePeriod/select_page_.jsp">
            <path fill="#9C9C9C"
						d="M608 320h-64v64h22.4c5.3 0 9.6 3.6 9.6 8v16c0 4.4-4.3 8-9.6 8H73.6c-5.3 0-9.6-3.6-9.6-8v-16c0-4.4 4.3-8 9.6-8H96v-64H32c-17.7 0-32 14.3-32 32v96c0 17.7 14.3 32 32 32h576c17.7 0 32-14.3 32-32v-96c0-17.7-14.3-32-32-32zm-96 64V64.3c0-17.9-14.5-32.3-32.3-32.3H160.4C142.5 32 128 46.5 128 64.3V384h384zM211.2 202l25.5-25.3c4.2-4.2 11-4.2 15.2.1l41.3 41.6 95.2-94.4c4.2-4.2 11-4.2 15.2.1l25.3 25.5c4.2 4.2 4.2 11-.1 15.2L300.5 292c-4.2 4.2-11 4.2-15.2-.1l-74.1-74.7c-4.3-4.2-4.2-11 0-15.2z"></path>
          </a>
        </svg> <a
			href="<%=request.getContextPath()%>/quePeriod/select_page_.jsp">
				<p>取號</p>
		</a>
		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="far" data-icon="calendar-check"
				class="svg-inline--fa fa-calendar-check fa-w-14" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
          <a href="https://www.google.com/">
            <path fill="#9C9C9C"
						d="M400 64h-48V12c0-6.627-5.373-12-12-12h-40c-6.627 0-12 5.373-12 12v52H160V12c0-6.627-5.373-12-12-12h-40c-6.627 0-12 5.373-12 12v52H48C21.49 64 0 85.49 0 112v352c0 26.51 21.49 48 48 48h352c26.51 0 48-21.49 48-48V112c0-26.51-21.49-48-48-48zm-6 400H54a6 6 0 0 1-6-6V160h352v298a6 6 0 0 1-6 6zm-52.849-200.65L198.842 404.519c-4.705 4.667-12.303 4.637-16.971-.068l-75.091-75.699c-4.667-4.705-4.637-12.303.068-16.971l22.719-22.536c4.705-4.667 12.303-4.637 16.97.069l44.104 44.461 111.072-110.181c4.705-4.667 12.303-4.637 16.971.068l22.536 22.718c4.667 4.705 4.636 12.303-.069 16.97z"></path>
          </a>
        </svg> <a href="https://www.google.com/">
				<p>訂位</p>
		</a>
		</span> <span> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="cash-register"
				class="svg-inline--fa fa-cash-register fa-w-16" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <a href="https://www.google.com/">
            <path fill="#9C9C9C"
						d="M511.1 378.8l-26.7-160c-2.6-15.4-15.9-26.7-31.6-26.7H208v-64h96c8.8 0 16-7.2 16-16V16c0-8.8-7.2-16-16-16H48c-8.8 0-16 7.2-16 16v96c0 8.8 7.2 16 16 16h96v64H59.1c-15.6 0-29 11.3-31.6 26.7L.8 378.7c-.6 3.5-.9 7-.9 10.5V480c0 17.7 14.3 32 32 32h448c17.7 0 32-14.3 32-32v-90.7c.1-3.5-.2-7-.8-10.5zM280 248c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16zm-32 64h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16zm-32-80c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16zM80 80V48h192v32H80zm40 200h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16zm16 64v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16zm216 112c0 4.4-3.6 8-8 8H168c-4.4 0-8-3.6-8-8v-16c0-4.4 3.6-8 8-8h176c4.4 0 8 3.6 8 8v16zm24-112c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16zm48-80c0 8.8-7.2 16-16 16h-16c-8.8 0-16-7.2-16-16v-16c0-8.8 7.2-16 16-16h16c8.8 0 16 7.2 16 16v16z"></path>
          </a>
        </svg> <a href="https://www.google.com/">
				<p>金流</p>
		</a>
		</span> <span id="setting"> <svg aria-hidden="true" focusable="false"
				data-prefix="fas" data-icon="cog"
				class="svg-inline--fa fa-cog fa-w-16" role="img"
				xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
          <a href="https://www.google.com/">
            <path fill="#9C9C9C"
						d="M487.4 315.7l-42.6-24.6c4.3-23.2 4.3-47 0-70.2l42.6-24.6c4.9-2.8 7.1-8.6 5.5-14-11.1-35.6-30-67.8-54.7-94.6-3.8-4.1-10-5.1-14.8-2.3L380.8 110c-17.9-15.4-38.5-27.3-60.8-35.1V25.8c0-5.6-3.9-10.5-9.4-11.7-36.7-8.2-74.3-7.8-109.2 0-5.5 1.2-9.4 6.1-9.4 11.7V75c-22.2 7.9-42.8 19.8-60.8 35.1L88.7 85.5c-4.9-2.8-11-1.9-14.8 2.3-24.7 26.7-43.6 58.9-54.7 94.6-1.7 5.4.6 11.2 5.5 14L67.3 221c-4.3 23.2-4.3 47 0 70.2l-42.6 24.6c-4.9 2.8-7.1 8.6-5.5 14 11.1 35.6 30 67.8 54.7 94.6 3.8 4.1 10 5.1 14.8 2.3l42.6-24.6c17.9 15.4 38.5 27.3 60.8 35.1v49.2c0 5.6 3.9 10.5 9.4 11.7 36.7 8.2 74.3 7.8 109.2 0 5.5-1.2 9.4-6.1 9.4-11.7v-49.2c22.2-7.9 42.8-19.8 60.8-35.1l42.6 24.6c4.9 2.8 11 1.9 14.8-2.3 24.7-26.7 43.6-58.9 54.7-94.6 1.5-5.5-.7-11.3-5.6-14.1zM256 336c-44.1 0-80-35.9-80-80s35.9-80 80-80 80 35.9 80 80-35.9 80-80 80z"></path>
          </a>
        </svg> <a href="https://www.google.com/">
				<p>設定</p>
		</a>
		</span>
	</div>
	<div id="main">
		<div id="main_top">
			<div id="store_name">
				<h3>台雞店</h3>
			</div>
			<div id="page_title">
				<h1>取號時段設定</h1>
			</div>
		</div>

		<div id="main_bot">
			<table class="status_table" id="status_table">
				<tr id="fst_row">
					<th>取號時段</th>
					<th>可否取號</th>
					<th>開始時間</th>
					<th>結束時間</th>
					<th></th>
				</tr>

				<%-- 				<%@ include file="page1.file"%> --%>


				<c:forEach var="quePeriodVO" items="${list}">
					<%-- 					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
					<c:choose>
						<c:when test="${quePeriodVO.storeid == 'S000001'}">
							<tr>
								<c:choose>
									<c:when test="${quePeriodVO.queueperiodid == 1}">
										<td>上午</td>
									</c:when>
									<c:otherwise>
										<td>下午</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${quePeriodVO.queuest == 1}">
										<td>可取號</td>
									</c:when>
									<c:otherwise>
										<td>不可取號</td>
									</c:otherwise>
								</c:choose>


								<td><fmt:formatDate value="${quePeriodVO.queuestarttime}" pattern="yyyy-MM-dd HH:mm"/></td>
								<td><fmt:formatDate value="${quePeriodVO.queueendtime}" pattern="yyyy-MM-dd HH:mm"/></td>

								<td class="btn_position">
									<div class="update">
										<form method="post"
											action="<%=request.getContextPath()%>/quePeriod/quePeriod.do"
											style="margin-bottom: 0px;">
											<input class="btn" type="submit" value="修改"> <input
												type="hidden" name="queueperiodid"
												value="${quePeriodVO.queueperiodid}"> <input
												type="hidden" name="storeid" value="${quePeriodVO.storeid}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</form>
									</div>
									<div class="clear">
										<form method="post"
											ACTION="<%=request.getContextPath()%>/quePeriod/quePeriod.do"
											style="margin-bottom: 0px;">
<!-- 											<input class="btn" type="submit" value="清空"> <input -->
<!-- 												type="hidden" name="queueperiodid" -->
<%-- 												value="${quePeriodVO.queueperiodid}"> <input --%>
<%-- 												type="hidden" name="storeid" value="${quePeriodVO.storeid}"> --%>
<!-- 											<input type="hidden" name="action" value="delete"> -->
										</form>
									</div>
								</td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>



			</table>
			<%-- 			<%@ include file="page2.file"%> --%>
		</div>
	</div>

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date queuestarttime = null;
	//   try {
	// 	  queuestarttime = quePeriodVO.getQueuestarttime();
	//    } catch (Exception e) {
	queuestarttime = new java.sql.Date(System.currentTimeMillis());
	//    }
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('.q_date').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d H:i', //format:'Y-m-d H:i:s',
		value :
<%=queuestarttime%>
	// value:   new Date(),
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