<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.queueno.model.*"%>
<%@ page import="com.queueperiod.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	List<QuePeriodVO> list = new ArrayList<QuePeriodVO>();
	list = (List<QuePeriodVO>) session.getAttribute("quePeriodVO"); // 取得server送來之list
	pageContext.setAttribute("list", list);
	Integer pickupNo = (Integer) session.getAttribute("pickupNo");
	String storeid = (String) session.getAttribute("storeid");
%>
<%=list == null%>
<%=pickupNo == null%>
<html>
<head>
<title>customerpickup</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap-grid.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/bootstrap-reboot.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/css/customerPickupNo.css" />

</head>
<body>
	<div class="container">
		<div class="row reserve">
			<div id="display">
				<div id="nowTime"></div>
			</div>
		</div>
		<div class="row form">
			<form>
<div class="row">
					<div class="col-sm-2"></div>
					<label for="queueperiodid" class="col-sm-2 col-form-label">時段選擇</label>
					<div class="col-sm-6">
						<select class="form-control" name="queueperiodid"
							id="queueperiodid" onclick="getNoCurrent();">
							<c:forEach var="quePeriodVO" items="${list}">
							<c:choose>
							<c:when test="${quePeriodVO.queuest==1 }">
										<option value="${quePeriodVO.queueperiodid}"><fmt:formatDate value="${quePeriodVO.queuestarttime}" pattern="HH:mm"/> ~ <fmt:formatDate value="${quePeriodVO.queueendtime}" pattern="HH:mm"/></option>
							</c:when>
							</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row reserve1"></div>
				<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="inlineRadioOptions" class="col-sm-2 col-form-label">用餐人數</label>
					<div class="col-sm-6">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								onchange="getNoCall();" name="inlineRadioOptions"
								id="inlineRadio1" value="option1"> <label
								class="form-check-label" for="inlineRadio1">1~2人</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" checked type="radio"
								onchange="getNoCall();" name="inlineRadioOptions"
								id="inlineRadio2" value="option2"> <label
								class="form-check-label" for="inlineRadio2">3~4人</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio"
								onchange="getNoCall();" name="inlineRadioOptions"
								id="inlineRadio3" value="option3"> <label
								class="form-check-label" for="inlineRadio3">5~8人 </label>
						</div>
						
					</div>
				</div>

				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-4">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text table">已為您分配</span>
							</div>
							<input type="text" class="form-control" name="party" id="party"
								value="4" aria-label="Amount (to the nearest dollar)"
								readonly="readonly" />
							<div class="input-group-append">
								<span class="input-group-text table">人桌</span>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text table">您的號碼</span>
							</div>
							<input name="queuenum" type="text" class="form-control" id="queuenum"
								value="<%=pickupNo%>"
								aria-label="Amount (to the nearest dollar)" readonly="readonly" />
							<div class="input-group-append">
								<span class="input-group-text table">號</span>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="validationServer01" class="col-sm-2 col-form-label">您的大名</label>
					<div class="col-sm-6">
						<input name="memberName" type="text" class="form-control <%-- is-(in)valid --%>"
							minlength="1" maxlength="10" id="memberName" placeholder="王大明"
							required>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="inputPassword3" class="col-sm-2 col-form-label">您的手機</label>
					<div class="col-sm-6">
						<input id="memphone" name="memphone" type="tel"
							class="form-control" placeholder="請輸入手機號碼：如 0987654321"
							minlength="10" maxlength="10"
							 required />
					</div>
				</div>
<!-- 				<div class="form-group row"> -->
<!-- 					<div class="col-sm-2"></div> -->
<!-- 					<label for="inputPassword3" class="col-sm-2 col-form-label">密碼設定</label> -->
<!-- 					<div class="col-sm-3"> -->
<!-- 						<input id="psw" type="password" class="form-control" value="enak" -->
<!-- 							readonly minlength="4" maxlength="8" /> -->
<!-- 					</div> -->
<!-- 					<div class="col-sm-1"> -->
<!-- 						<button id="reset" type="button" class="btn btn-outline-primary">reset</button> -->
<!-- 					</div> -->
<!-- 					<div class="col-sm-2"> -->
<!-- 						<small id="passwordHelpInline" class="text-muted"> -->
<!-- 							預設密碼為：enak </small> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="form-group row" id="showPswCheck" -->
<!-- 					style="visibility: hidden"> -->
<!-- 					<div class="col-sm-2"></div> -->
<!-- 					<label for="inputPassword3" class="col-sm-2 col-form-label">密碼確認</label> -->
<!-- 					<div class="col-sm-3"> -->
<!-- 						<input type="password" id="pswCheck" class="form-control" -->
<!-- 							value="enak"> -->
<!-- 					</div> -->
<!-- 				</div> -->


				<div class="form-group row">
					<div class="col-sm-2"></div>
					<div class="col-sm-3"></div>
					<div class="col-sm-1"></div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary">放棄取號</button>
					</div>
					<div class="col-sm-2">
						<input type="hidden" id="queuetableid" name="queuetableid"
							value="2"> <input type="hidden" id="queuelineno"
							name="queuelineno" value="2"> <input type="hidden"
							id="queuenotime" name="queuenotime" class="quenotime"> <input
							type="hidden" id="storeid" name="storeid" value="${storeid }">
						<input type="hidden" name="action" value="insert">
						<button id="submit" type="submit" class="btn btn-primary"
							value="送出新增">確認取號</button>
					</div>
				</div>
			</form>
		</div>
		<div class="row reserve1"></div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-3">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">目前取號</span>
					</div>
					<div class="form-control" id="nocurrent"
						aria-label="Amount (to the nearest dollar)" /></div>
					<div class="input-group-append">
						<span class="input-group-text">號</span>
					</div>
				</div>
			</div>

			<div class="col-sm-1"></div>
			<div class="col-sm-3">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">目前叫號</span>
					</div>
					<div class="form-control" id="nocall"
						aria-label="Amount (to the nearest dollar)" /></div>
					<div class="input-group-append">
						<span class="input-group-text">號</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
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
					+ fillZero(now.getMinutes()) + ':'
					+ fillZero(now.getSeconds()) + ' ' + AMPM;
		}
		console.log('現在時間：' + timeFormat(now));
		let nowTime = document.getElementById('nowTime');
		nowTime.innerText = timeFormat(now);

		setInterval(function() {
			time = new Date();
			nowTime.innerText = timeFormat(time);
		}, 1000)
	</script>

	<!-- 	插入動態最新取號 -->
	<script>
		var xhr = null;

		function showNoCurrent(jsonStr) {
			//剖析json字串,將其轉成jsob物件
			let noCurrent = JSON.parse(jsonStr);
			let html;
			if (noCurrent.queuenocurrent === undefined) {
				html = "<center>查無資料</center>"
			} else {
				html = "<div>" + noCurrent.queuenocurrent + "</div>";
			}
			document.getElementById("nocurrent").innerHTML = html;
		}
		function getNoCurrent() {
			var xhr = new XMLHttpRequest();
			//設定好回呼函數   
			xhr.onload = function() {
				if (xhr.status == 200) {
					showNoCurrent(xhr.responseText);
					//showEmployee(xhr.responseText);
				} else {
					alert(xhr.status);
				}//xhr.status == 200
			};//onload 

			//建立好Get連接
			var url = "getCurrentNo.jsp?storeid="
					+ document.getElementById("storeid").value
					+ "&queueperiodid="
					+ document.getElementById("queueperiodid").value
					+ "&queuenum="
					+ document.getElementById("queuenum").value
			xhr.open("Get", url, true);
			//送出請求 
			xhr.send(null);
		}
	</script>

	<!-- 	插入動態最新叫號 -->
	<script>
		var xhr = null;

		function showNoCall(jsonStr) {
			//剖析json字串,將其轉成jsob物件
			let noCall = JSON.parse(jsonStr);
			let html;
			if (noCall.queuenocall === undefined) {
				html = "<center>查無資料</center>"
			} else {
				html = "<div>" + noCall.queuenocall + "</div>";
			}
			document.getElementById("nocall").innerHTML = html;
		}
		function getNoCall() {
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
			var url = "getNoCall.jsp?storeid="
					+ document.getElementById("storeid").value
					+ "&queuetableid="
					+ document.getElementById("queuetableid").value
					+ "&queuelineno="
					+ document.getElementById("queuelineno").value;
			xhr.open("Get", url, true);
			//送出請求 
			xhr.send(null);
		}
	</script>

</body>
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
	window.onload = function() {
		getNoCall();
		getNoCurrent();
	}
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

</html>