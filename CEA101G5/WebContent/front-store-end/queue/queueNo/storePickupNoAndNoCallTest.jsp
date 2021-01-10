<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queueno.model.*"%>
<%@ page import="com.queueperiod.model.*"%>
<%
	List<QuePeriodVO> list = new ArrayList<QuePeriodVO>();
	list = (List<QuePeriodVO>) session.getAttribute("quePeriodVO");
	pageContext.setAttribute("list", list);
	Integer pickupNo = (Integer) session.getAttribute("pickupNo");
%>
<%=list == null%>
<%=pickupNo == null%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<html>
<head>
<title>storePickupAndNoCall</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap-grid.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap-reboot.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/storePickupNoAndNoCall.css" />
</head>
<body>
	<div class="container">
		<div class="row reserve"></div>
		<!-- <div class="row reserve1"></div> -->
		<div class="row">
			<div class="col-4 left">
				<div class="row left-top">
					<div id="display">
						<div id="nowTime"></div>
					</div>
				</div>
				<form id="info">
					<div class="row left-mid">
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="name" class="col-sm-2 col-form-label">Name:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-9">
								<input class="form-control" id="name">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="phone" class="col-sm-2 col-form-label">Phone:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-9">
								<input name="memphone" type="tel" class="form-control" id="memphone" minlength="10"
									maxlength="10">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="party" class="col-sm-2 col-form-label">Party:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-8">
								<select class="form-control" name="party" id="party" style="width: 275px">
									<option value="2">2人以下</option>
									<option value="4">3~4人</option>
									<option value="8">5~8人</option>
								</select>
							</div>
						</div>
						
<jsp:useBean id="quePeriodSvc" scope="page" class="com.queueperiod.model.QuePeriodService" />
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="queueperiodid" class="col-sm-2 col-form-label">Period:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-8">
								<select class="form-control" name="queueperiodid"
									id="queueperiodid" style="width: 275px">
									<c:forEach var="quePeriodV" items="${quePeriodSvc.all}">
									<c:choose>
									<c:when test="${quePeriodV.storeid== 'S000001'}">
									<c:choose>
											<c:when test="${quePeriodV.queueperiodid == 1}">
												<option value="${quePeriodV.queueperiodid}">上午</option>
											</c:when>
											<c:when test="${quePeriodV.queueperiodid == 2}">
												<option value="${quePeriodV.queueperiodid}">下午</option>
											</c:when>
											<c:when test="${quePeriodV.queueperiodid == 3}">
												<option value="${quePeriodV.queueperiodid}">上午test</option>
											</c:when>
											<c:when test="${quePeriodV.queueperiodid == 4}">
												<option value="${quePeriodV.queueperiodid}">上午test</option>
											</c:when>
										</c:choose>
									</c:when></c:choose></c:forEach>
<%-- 									<c:forEach var="quePeriodVO" items="${list}"> --%>
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${quePeriodVO.queueperiodid == 1}"> --%>
<%-- 												<option value="${quePeriodVO.queueperiodid}">上午</option> --%>
<%-- 											</c:when> --%>
<%-- 											<c:when test="${quePeriodVO.queueperiodid == 2}"> --%>
<%-- 												<option value="${quePeriodVO.queueperiodid}">下午</option> --%>
<%-- 											</c:when> --%>
<%-- 											<c:when test="${quePeriodVO.queueperiodid == 3}"> --%>
<%-- 												<option value="${quePeriodVO.queueperiodid}">上午test</option> --%>
<%-- 											</c:when> --%>
<%-- 											<c:when test="${quePeriodVO.queueperiodid == 4}"> --%>
<%-- 												<option value="${quePeriodVO.queueperiodid}">上午test</option> --%>
<%-- 											</c:when> --%>
<%-- 										</c:choose> --%>
<%-- 									</c:forEach> --%>
								</select>
							</div>
						</div>
					</div>
					<div class="row left-bot">
						<div class="input-group-prepend">
							<h3>預計取號</h3>

						</div>
						<input name="queuenum" type="text" class="form-control"
							value="<%=pickupNo%>" aria-label="Amount (to the nearest dollar)"
							readonly> 
							<input type="hidden" id="queuenotime"
							name="queuenotime" class="quenotime"> 
							<input
							type="hidden" id="storeid" name="storeid" value="S000001">
							<input type="hidden" id="queuetableid" name="queuetableid"
							value="1"> 
							<input type="hidden" id="queuelineno"
							name="queuelineno" value="1"> 
							<input type="hidden"
							name="action" value="storeInsert">
						<button id="" type="button" class="btn btn-primary" value="新增">新增取號資訊</button>
						<button id="submit" type="submit" class="btn btn-primary" value="送出新增">新增取號</button>
					</div>
				</form>
			</div>
			<div class="col-8 right">
				<div class="row right-li">
					<div class="col-2">
						<div class="tabletype">桌位</div>
						<div class="table_type">YY</div>
					</div>
					<div class="col-2">
						<div class="nocurrent">目前取號</div>
						<div class="no_current">YY</div>
					</div>
					<div class="col-2">
						<div class="nocall">目前叫號</div>
						<div class="no_call">YY</div>
					</div>
					<div class="col-2">
						<div class="tableavail">空桌數</div>
						<div class="table_avail">YY</div>
					</div>
					<div class="col-2">
						<div class="queuenums">預備叫號</div>
						<div class="queue_nums">YY</div>
					</div>
					<div class="col-2">
						<button type="button" class="btn btn-danger">叫號</button>
						<button type="button" class="btn btn-success">收桌</button>
					</div>
				</div>
				<div class="row right-li"></div>
				<div class="row right-li"></div>
				<!-- <div class="row right-bot"></div> -->
			</div>


		</div>

	</div>
</body>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/jquery-3.4.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/popper.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/customerPickupNo.js"></script>

<!-- 		顯示動態時間 -->
<script type="text/javascript">
	let now = new Date();

	function fillZero(value) {
		if (value < 10) {
			value = '0' + value;
		}
		return value;
	}

	function timeFormat(now) {
		let hours;
		let AMPM;
		if (now.getHours() >= 12) {
			AMPM = 'PM';
			hours = fillZero(now.getHours() - 12);
		} else {
			AMPM = 'AM';
			hours = fillZero(now.getHours());
		}

		return now.getFullYear() + '/' + (now.getMonth() + 1) + '/'
				+ now.getDate() + ' ' + hours + ':'
				+ fillZero(now.getMinutes()) + ':' + fillZero(now.getSeconds())
				+ ' ' + AMPM;
	}
	console.log('現在時間：' + timeFormat(now));
	let nowTime = document.getElementById('nowTime');
	nowTime.innerText = timeFormat(now);

	setInterval(function() {
		time = new Date();
		nowTime.innerText = timeFormat(time);
	}, 1000)
</script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('zh');
	$('.quenotime').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 30, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y/m/d H:i', //format:'Y-m-d H:i:s',
		value : new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
<script>
	$("#party").click(function() {// 指派按鈕值給資料庫party欄位
		switch ($(this).attr("value")) {
		case "option1":
			$("[name='party']").attr("value", "2");
			$("[name='queuetableid']").attr("value", "1");
			$("[name='queuelineno']").attr("value", "1");
			break;
		case "option2":
			$("[name='party']").attr("value", "4");
			$("[name='queuetableid']").attr("value", "2");
			$("[name='queuelineno']").attr("value", "2");
			break;
		case "option3":
			$("[name='party']").attr("value", "8");
			$("[name='queuetableid']").attr("value", "3");
			$("[name='queuelineno']").attr("value", "3");
			break;
		default:
			break;
		}
	});
</script>
<script>
// 開始先關送出
$(document).ready(function() {
	$("#submit").prop("disabled", true);
});
</script>
</body>


</html>