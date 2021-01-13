<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queueno.model.*"%>
<%@ page import="com.queueperiod.model.*"%>
<%@ page import="com.queuetable.model.*"%>
<%@ page import="com.queueline.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	List<QuePeriodVO> quePeriodVO = new ArrayList<QuePeriodVO>();
	quePeriodVO = (List<QuePeriodVO>) session.getAttribute("quePeriodVO"); // 取得server送來之list
	pageContext.setAttribute("quePeriodVO", quePeriodVO);

	List<QueTableVO> queTableVO = new ArrayList<QueTableVO>();
	queTableVO = (List<QueTableVO>) session.getAttribute("queTableVO");
	pageContext.setAttribute("queTableVO", queTableVO);

	List<QueLineVO> queLineVO = new ArrayList<QueLineVO>();
	queLineVO = (List<QueLineVO>) session.getAttribute("queLineVO");
	pageContext.setAttribute("queLineVO", queLineVO);

	List<QueNoVO> queNoVO = new ArrayList<QueNoVO>();
	queNoVO = (List<QueNoVO>) session.getAttribute("queNoVO");
	pageContext.setAttribute("queNoVO", queNoVO);

	Integer pickupNo = (Integer) session.getAttribute("pickupNo");
	String storeid = (String) session.getAttribute("storeid");
%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
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
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-store-end/restaurant/css/css.css" /> --%>
<style>
.header {
	position: fixed;
	background-color: #FA7E23;
	height: 120px;
	width: 100%;
	z-index: 2;
	opacity: 85%;
	margin-top: -30;
}

img {
	position: fixed;
	z-index: 4;
	margin-top: 10;
}
</style>
</head>
<body>
	<div class="header">
		<!-- Just an image -->
		<nav class="navbar navbar-light bg-light">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/front-store-end/restaurant/WelcomePage.jsp">
				<img src="../img/LOGO/Logo3(2).png" width="180" height="100" alt=""
				loading="lazy">
			</a>
		</nav>
		<a class="icon"
			href="<%=request.getContextPath()%>/front-store-end/restaurant/WelcomePage.jsp"></a>
	</div>
	<div class="container">
		<div class="row reserve2"></div>
		<div class="row">
			<div class="col-4 left">
				<div class="row left-top">
					<div class="row" id="display">
						<div style="font-size: 60px; text-align: center;" id="nowTime"></div>
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
								<input name="memName" class="form-control" id="name" required>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="phone" class="col-sm-2 col-form-label">Phone:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-9">
								<input name="memphone" type="tel" class="form-control" required
									id="memphone" minlength="10" maxlength="10">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="party" class="col-sm-2 col-form-label">Party:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-8">
								<select class="form-control" name="party" id="party"
									style="width: 275px">
									<option value="2">2人以下</option>
									<option value="4">3~4人</option>
									<option value="8">5~8人</option>
								</select>
							</div>
						</div>

						<jsp:useBean id="quePeriodSvc" scope="page"
							class="com.queueperiod.model.QuePeriodService" />
						<div class="form-group row">
							<div class="col-sm-2">
								<label for="queueperiodid" class="col-sm-2 col-form-label">Period:</label>
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-8">
								<select class="form-control" name="queueperiodid"
									id="queueperiodid" style="width: 275px">
									<c:forEach var="quePeriodVO" items="${quePeriodVO}">
										<c:choose>
											<c:when test="${quePeriodVO.queuest==1 }">
												<option value="${quePeriodVO.queueperiodid}"><fmt:formatDate
														value="${quePeriodVO.queuestarttime}" pattern="HH:mm" />
													~
													<fmt:formatDate value="${quePeriodVO.queueendtime}"
														pattern="HH:mm" /></option>
											</c:when>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row left-bot">
						<div class="input-group-prepend">
							<h3>預計取號</h3>

						</div>
						<input name="storeid" id="storeid" value="${storeid }"
							hidden="hidden" /> <input style="text-align: center;"
							name="queuenum" type="text" class="form-control"
							value="<%=pickupNo%>" aria-label="Amount (to the nearest dollar)"
							readonly> <input type="hidden" id="queuenotime"
							name="queuenotime" class="quenotime"> <input
							type="hidden" id="queuetableid" name="queuetableid" value="1">
						<input type="hidden" id="queuelineno" name="queuelineno" value="1">
						<input type="hidden" name="action" value="storeInsert">
						<button id="submit" type="submit" class="btn btn-primary"
							value="送出新增">新增取號</button>
					</div>
				</form>
			</div>

			<!-- 表格右邊 -->
			<!-- 第一種桌子 -->
			<div class="col-8 right">
				<c:forEach var="queTableVO" items="${queTableVO}">
					<div class="row right-li tabledata">
						<div class="col-2">
							<div class="tabletype">桌位</div>
							<c:choose>
								<c:when test="${queTableVO.queuetableid ==1}">
									<div class="table_type">2人桌</div>
									<input class="queuetableid" value="${queTableVO.queuetableid}"
										type="hidden">
								</c:when>
								<c:when test="${queTableVO.queuetableid ==2}">
									<div class="table_type">4人桌</div>
									<input class="queuetableid" value="${queTableVO.queuetableid}"
										type="hidden">
								</c:when>
								<c:when test="${queTableVO.queuetableid ==3}">
									<div class="table_type">8人桌</div>
									<input class="queuetableid" value="${queTableVO.queuetableid}"
										type="hidden">
								</c:when>
								<c:when test="${queTableVO.queuetableid ==4}">
									<div class="table_type">10人桌</div>
									<input class="queuetableid" value="${queTableVO.queuetableid}"
										type="hidden">
								</c:when>
							</c:choose>
						</div>

						<div class="col-2">
							<div class="tableavail">空桌數</div>
							<div class="queuetableusable">${queTableVO.queuetableusable}</div>
							<input class="queuetableocc" value="${queTableVO.queuetableocc }"
								type="hidden"> <input class="quetableusable"
								value="${queTableVO.queuetableusable}" type="hidden">
						</div>



						<div class="col-2">
							<div class="queuenums">預備叫號</div>
							<div class="queue_nums">
								<c:choose>
									<c:when test="${queTableVO.queuetableid==1 }">
										<c:forEach var="queNoVO" items="${queNoVO}" begin="0" end="3">
											<c:choose>
												<c:when test="${queNoVO.queuetableid==1 }">
													<div>
														<c:out value="${queNoVO.queuenum } " />
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:when test="${queTableVO.queuetableid==2 }">
										<c:forEach var="queNoVO" items="${queNoVO}" begin="0" end="3">
											<c:choose>
												<c:when test="${queNoVO.queuetableid==2 }">
													<div>
														<c:out value="${queNoVO.queuenum } " />
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:when test="${queTableVO.queuetableid==3 }">
										<c:forEach var="queNoVO" items="${queNoVO}" begin="0" end="3">
											<c:choose>
												<c:when test="${queNoVO.queuetableid==3 }">
													<div>
														<c:out value="${queNoVO.queuenum } " />
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:when test="${queTableVO.queuetableid==4 }">
										<c:forEach var="queNoVO" items="${queNoVO}" begin="0" end="3">
											<c:choose>
												<c:when test="${queNoVO.queuetableid==4 }">
													<div>
														<c:out value="${queNoVO.queuenum } " />
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</c:when>
								</c:choose>
							</div>
						</div>

						<div class="col-2">
							<div class="nocall">目前叫號</div>
							<c:choose>
								<c:when test="${queTableVO.queuetableid==1}">
									<c:forEach var="queLineVO" items="${queLineVO}">
										<c:choose>
											<c:when test="${queLineVO.queuelineno==1}">
												<div id="queuenum" class="no_call">${queLineVO.queuenocall}</div>
												<input class="queuenum" value="${queLineVO.queuenocall}"
													type="hidden">
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:when test="${queTableVO.queuetableid==2}">
									<c:forEach var="queLineVO" items="${queLineVO}">
										<c:choose>
											<c:when test="${queLineVO.queuelineno==2}">
												<div id="queuenum" class="no_call">${queLineVO.queuenocall}</div>
												<input class="queuenum" value="${queLineVO.queuenocall}"
													type="hidden">
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:when test="${queTableVO.queuetableid==3}">
									<c:forEach var="queLineVO" items="${queLineVO}">
										<c:choose>
											<c:when test="${queLineVO.queuelineno==3}">
												<div id="queuenum" class="no_call">${queLineVO.queuenocall}</div>
												<input class="queuenum" value="${queLineVO.queuenocall}"
													type="hidden">
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:when test="${queTableVO.queuetableid==4}">
									<c:forEach var="queLineVO" items="${queLineVO}">
										<c:choose>
											<c:when test="${queLineVO.queuelineno==4}">
												<div id="queuenum" class="no_call">${queLineVO.queuenocall}</div>
												<input class="queuenum" value="${queLineVO.queuenocall}"
													type="hidden">
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</div>

						<div class="col-2">
							<button type="button" class="call btn btn-danger">叫號</button>
							<button type="button" class="clear btn btn-success">收桌</button>
						</div>

					</div>
				</c:forEach>

				<!-- -------------------------------------------------------------------------------------------------------- -->

				<%-- 				<jsp:useBean id="queTableSvc" scope="page" --%>
				<%-- 					class="com.queuetable.model.QueTableService" /> --%>
				<!-- 				<div class="row right-li"> -->
				<%-- 					<c:forEach var="queTableVO" items="${queTableSvc.all}"> --%>
				<%-- 						<c:choose> --%>
				<%-- 							<c:when --%>
				<%-- 								test="${queTableVO.storeid=='S000001' && queTableVO.queuetableid ==1}"> --%>
				<!-- 								<div class="col-2"> -->
				<!-- 									<div class="tabletype">桌位</div> -->
				<!-- 									<div class="table_type">2人桌</div> -->
				<!-- 								</div> -->
				<!-- 								<div class="col-2"> -->
				<!-- 									<div class="tableavail">空桌數</div> -->
				<%-- 									<div class="table_avail">${queTableVO.queuetablettl-queTableVO.queuetableocc }</div> --%>
				<!-- 								</div> -->
				<%-- 							</c:when> --%>
				<%-- 						</c:choose> --%>
				<%-- 					</c:forEach> --%>

				<%-- 					<c:forEach var="quePeriodVO" items="${quePeriodSvc.all}"> --%>
				<%-- 						<c:choose> --%>
				<%-- 							<c:when test="${quePeriodVO.storeid== 'S000001'}"> --%>
				<%-- 								<c:choose> --%>
				<%-- 									<c:when test="${quePeriodVO.queueperiodid ==1}"> --%>
				<!-- 										<div class="col-2"> -->
				<!-- 											<div class="nocurrent">目前取號</div> -->
				<%-- 											<div class="no_current">${quePeriodVO.queuenocurrent }</div> --%>
				<!-- 										</div> -->
				<%-- 									</c:when> --%>
				<%-- 								</c:choose> --%>
				<%-- 							</c:when> --%>
				<%-- 						</c:choose> --%>
				<%-- 					</c:forEach> --%>

				<%-- 					<jsp:useBean id="queLineSvc" scope="page" --%>
				<%-- 						class="com.queueline.model.QueLineService" /> --%>

				<%-- 					<c:forEach var="queLineVO" items="${queLineSvc.all }"> --%>
				<%-- 						<c:choose> --%>
				<%-- 							<c:when --%>
				<%-- 								test="${queLineVO.storeid=='S000001' && queLineVO.queuetableid==1}"> --%>
				<!-- 								<div class="col-2"> -->
				<!-- 									<div class="nocall">目前叫號</div> -->
				<%-- 									<div id="queuenum1" class="no_call">${queLineVO.queuenocall}</div> --%>
				<!-- 								</div> -->
				<%-- 							</c:when> --%>
				<%-- 						</c:choose> --%>
				<%-- 					</c:forEach> --%>

				<%-- 					<jsp:useBean id="queNoSvc" scope="page" --%>
				<%-- 						class="com.queueno.model.QueNoService" /> --%>

				<!-- 					<div class="col-2"> -->
				<!-- 						<div class="queuenums">預備叫號</div> -->
				<!-- 						<div class="queue_nums"> -->
				<%-- 							<c:forEach var="queNoVO" items="${queNoSvc.all}" begin="0" --%>
				<%-- 								end="3"> --%>
				<%-- 								<c:choose> --%>
				<%-- 									<c:when --%>
				<%-- 										test="${queNoVO.storeid=='S000001' && queNoVO.queuetableid==1 }"> --%>
				<!-- 										<div> -->
				<%-- 											<c:out value="${queNoVO.queuenum } " /> --%>
				<!-- 										</div> -->
				<%-- 									</c:when> --%>
				<%-- 								</c:choose> --%>
				<%-- 							</c:forEach> --%>
				<!-- 						</div> -->
				<!-- 					</div> -->
				<!-- 					<div class="col-2"> -->
				<!-- 						<input id="queuetableid1" name="queuetableid1" value="1" -->
				<!-- 							hidden="hidden"> <input onclick="" -->
				<!-- 							type="button" class="btn btn-danger" value="叫號" /> <input -->
				<!-- 							onclick="" type="button" class="btn btn-success" value="收桌"> -->
				<!-- 					</div> -->
				<!-- 				</div> -->


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
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/customerPickupNo.js"></script> --%>

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
// 	//先關送出
// 	$(document).ready(function() {
// 		$("#submit").prop("disabled", true);
// 	});
</script>

<!-- 	插入動態最新叫號 -->
<script>
	var xhr = null;
	let tableUsableArr = document.getElementsByClassName("queuetableusable");//顯示usableTable
	let quenumShowArr = document.getElementsByClassName("no_call");//顯示quenum
	let tableUsableInputArr = document.getElementsByClassName("quetableusable");//inputUsableTable改值用
	let quenumInputArr = document.getElementsByClassName("queuenum");//inputQuenum改值用
	function showNoCall(jsonStr) {
		//剖析json字串,將其轉成jsob物件
		let noCall = JSON.parse(jsonStr);
		let html;
		let html2 = "<div>" + noCall.queuetableusable + "</div>";
		if (noCall.queuenocall === undefined) {
			html = "<center>查無資料</center>"
		} else {
			html = "<div>" + noCall.queuenocall + "</div>";
		}
// 		document.getElementById("queuenum1").innerHTML = html;
		switch (noCall.queuetableid){
			case 1:
				quenumShowArr[0].innerHTML = html;
				tableUsableArr[0].innerHTML = html2;
				tableUsableInputArr[0].value = noCall.queuetableusable;
				quenumInputArr[0].value = noCall.queuenocall;
				break;
			case 2:
				quenumShowArr[1].innerHTML = html;
				tableUsableArr[1].innerHTML = html2;
				tableUsableInputArr[1].value = noCall.queuetableusable;
				quenumInputArr[1].value = noCall.queuenocall;
				break;
			case 3:
				quenumShowArr[2].innerHTML = html;
				tableUsableArr[2].innerHTML = html2;
				tableUsableInputArr[2].value = noCall.queuetableusable;
				quenumInputArr[2].value = noCall.queuenocall;
				break;
			case 4:
				quenumShowArr[3].innerHTML = html;
				tableUsableArr[3].innerHTML = html2;
				tableUsableInputArr[3].value = noCall.queuetableusable;
				quenumInputArr[3].value = noCall.queuenocall;
				break;
				default:
					break;}
}


// 	function updateNoCall() {
	$(".call").click(function(e) {
		var xhr = new XMLHttpRequest();	
		//設定好回呼函數   
		xhr.onload = function() {
			if (xhr.status == 200) {
				showNoCall(xhr.responseText);
			} else {
				alert(xhr.status);
			}//xhr.status == 200 
		};//onload 
		//建立好Get連接
		var url = "updateNoCall.jsp?storeid="
				+ document.getElementById("storeid").value 
				+ "&queuetableid="	+ $(e.target).parent().prev().prev().prev().prev().children(".queuetableid").val() 
				+ "&queuenum="	+ $(e.target).parent().prev().children(".queuenum").val()
				+ "&queuetableocc=" + $(e.target).parent().prev().prev().prev().children(".queuetableocc").val()
				+ "&queuetableusable=" + $(e.target).parent().prev().prev().prev().children(".quetableusable").val()	
		xhr.open("Get", url, true);
		//送出請求 
		xhr.send();
	});
</script>

<!-- 	插入收桌 -->
<script>
	var xhr = null;
	function showTableClear(jsonStr) {
		//剖析json字串,將其轉成jsob物件
		let noCall = JSON.parse(jsonStr);
		let html;
		let html2 = "<div>" + noCall.queuetableusable + "</div>";
// 		document.getElementById("queuenum1").innerHTML = html;
		switch (noCall.queuetableid){
			case 1:
				tableUsableArr[0].innerHTML = html2;
				tableUsableInputArr[0].value = noCall.queuetableusable;
				break;
			case 2:
				tableUsableArr[1].innerHTML = html2;
				tableUsableInputArr[1].value = noCall.queuetableusable;
				break;
			case 3:
				tableUsableArr[2].innerHTML = html2;
				tableUsableInputArr[2].value = noCall.queuetableusable;
				break;
			case 4:
				tableUsableArr[3].innerHTML = html2;
				tableUsableInputArr[3].value = noCall.queuetableusable;
				break;
				default:
					break;}
}


// 	function updateNoCall() {
	$(".clear").click(function(e) {
		var xhr = new XMLHttpRequest();	
		//設定好回呼函數   
		xhr.onload = function() {
			if (xhr.status == 200) {
				showTableClear(xhr.responseText);
			} else {
				alert(xhr.status);
			}//xhr.status == 200 
		};//onload 
		//建立好Get連接
		var url = "tableUpdate.jsp?storeid="
				+ document.getElementById("storeid").value 
				+ "&queuetableid="	+ $(e.target).parents(".tabledata").find(".queuetableid").val() 
				+ "&queuetableocc=" + $(e.target).parents(".tabledata").find(".queuetableocc").val()
				+ "&queuetableusable=" + $(e.target).parents(".tabledata").find(".quetableusable").val()	
		xhr.open("Get", url, true);
		//送出請求 
		xhr.send();
	});
</script>
<script>
	$("#party").change(function(){
		switch($(this).val()){
		case "2":
			$("#queuetableid").attr("value", "1");
			$("#queuelineno").attr("value", "1");
			break;
		case "4":
			$("#queuetableid").attr("value", "2");
			$("#queuelineno").attr("value", "2");
			break;
		case "8":
			$("#queuetableid").attr("value", "3");
			$("#queuelineno").attr("value", "3");
			break;
		case "10":
			$("#queuetableid").attr("value", "4");
			$("#queuelineno").attr("value", "4");
			break;
			default:
				break;
		}
		
	});
	
	
</script>

<script>
//  電話驗證
$("#memphone").blur(
function() {
	var MobileReg = /^[0]{1}[9]{1}[0-9]{8}$/;
	console.log($(this).val());
	console.log($(this).val().match(MobileReg));
	
	if($(this).val().match(MobileReg)){
			$(this).addClass("is-valid").removeClass("is-invalid");
	}else{
			$(this).addClass("is-invalid").removeClass("is-valid");
	}		
});
</script>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<c:out value="${check }"></c:out>
<c:if test="${check=='addNo' }">
	<script>
swal("新增成功", "ok", "success");
</script>
</c:if>
<c:if test="${check=='repeat' }">
	<script>
swal("已取過號，請確認", "fail", "error");
</script>
</c:if>


</html>