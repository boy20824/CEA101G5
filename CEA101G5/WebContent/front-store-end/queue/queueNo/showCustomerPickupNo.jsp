<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.queueno.model.*"%>
<%@ page import="com.queueline.model.*"%>
<%@ page import="com.queueperiod.model.*"%>

<%
	QueNoVO queNoVO = (QueNoVO) session.getAttribute("queNoVO");
	QueNoVO queNoVO2 = (QueNoVO) session.getAttribute("queNoVO2");
	List<QuePeriodVO> quePeriodVO = new ArrayList<QuePeriodVO>();
	quePeriodVO = (List<QuePeriodVO>) session.getAttribute("quePeriodVO");
	String memberName = (String) session.getAttribute("memberName");
// 	List<QueNoVO> list = new ArrayList<QueNoVO>();//取得by storeid and tableid 未決定時段
// 	list = (List<QueNoVO>) session.getAttribute("list");
// 	pageContext.setAttribute("list", list);
	session.setAttribute("queNoVO", queNoVO);
	session.setAttribute("queNoVO2", queNoVO);
	session.setAttribute("quePeriodVO", quePeriodVO);
	session.setAttribute("memberName", memberName);
%>
<%-- <%=list == null%> --%>

<html>
<head>
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
<title>customerpickup</title>
</head>
<body>
	<div class="container">
		<div class="row reserve"></div>
		<div class="row reserve"></div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-4">
				<h1>
					<span class="badge badge-secondary">您好！！</span>
				</h1>
			</div>
			<div class="col-sm-4">
				<h1>
					${memberName }
				</h1>
			</div>
		</div>
		<div class="row reserve1"></div>
		<div class="row form">
			<form>
			<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="inputEmail3" class="col-sm-2 col-form-label">候位時段</label>
					<div class="col-sm-6">
					<c:forEach var="quePeriodVO" items="${quePeriodVO}">
					<c:choose>
					<c:when test="${queNoVO2.queueperiodid==quePeriodVO.queueperiodid}">
					<c:choose>
					<c:when test="${queNoVO2.queueperiodid==1}">
						<div class="form-control" id="queueperiodid"><fmt:formatDate value="${quePeriodVO.queuestarttime}" pattern="HH:mm"/> ~ <fmt:formatDate value="${quePeriodVO.queueendtime}" pattern="HH:mm"/></div>
						</c:when><c:when test="${queNoVO2.queueperiodid==2 }">
						<div class="form-control" id="queueperiodid"><fmt:formatDate value="${quePeriodVO.queuestarttime}" pattern="HH:mm"/> ~ <fmt:formatDate value="${quePeriodVO.queueendtime}" pattern="HH:mm"/></div>
						</c:when><c:when test="${queNoVO2.queueperiodid==3 }">
						<div class="form-control" id="queueperiodid"><fmt:formatDate value="${quePeriodVO.queuestarttime}" pattern="HH:mm"/> ~ <fmt:formatDate value="${quePeriodVO.queueendtime}" pattern="HH:mm"/></div>
						</c:when><c:when test="${queNoVO2.queueperiodid==4 }">
						<div class="form-control" id="queueperiodid"><fmt:formatDate value="${quePeriodVO.queuestarttime}" pattern="HH:mm"/> ~ <fmt:formatDate value="${quePeriodVO.queueendtime}" pattern="HH:mm"/></div>
						</c:when></c:choose></c:when></c:choose>
</c:forEach>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="inputEmail3" class="col-sm-2 col-form-label">候位號碼</label>
					<div class="col-sm-6">
						<div class="form-control" id="queuenum">
<%-- 						<%=queNoVO.getQueuenum()%> --%>
						<c:out value="${queNoVO.queuenum }"></c:out>
						</div>
					</div>
				</div>
				<div class="row reserve1"></div>

				<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="inputPassword3" class="col-sm-2 col-form-label">建議來店時間</label>
					<div class="col-sm-6">

						<div class="form-control" id="queuenotime">
							<fmt:formatDate value="${queNoVO.queuenotime}"
								pattern="yyyy-MM-dd HH:mm" />
						</div>

					</div>
				</div>
				<div class="row reserve1"></div>
				<div class="form-group row">
					<div class="col-sm-2"></div>
					<label for="inputPassword3" class="col-sm-2 col-form-label">人數</label>
					<div class="col-sm-6">
						<div class="form-control" id="party">
<%-- 						<%=queNoVO.getParty()%> --%>
						<c:out value="${queNoVO.party }"></c:out>
						</div>
					</div>
				</div>

			</form>
		</div>
		<div class="row reserve1"></div>

		<jsp:useBean id="queLineSvc" scope="page"
			class="com.queueline.model.QueLineService" />
		<div class="row">
			<div class="col-sm-6"></div>
			<div class="col-sm-3">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">目前叫號</span>
					</div>
					<c:forEach var="queLineVO" items="${queLineSvc.all}">
						<c:choose>
							<c:when
								test="${queNoVO.storeid==queLineVO.storeid && queNoVO.queuetableid==queLineVO.queuetableid }">

								<div class="form-control" id="nocall"
									style="text-align: center;"
									aria-label="Amount (to the nearest dollar)" >
								<c:out value="${queLineVO.queuenocall }" />
				</div>
				</c:when>
				</c:choose>
				</c:forEach>
				
				<div class="input-group-append">
					<span class="input-group-text">號</span>
				</div>
			</div>
		</div>
</div>
	<jsp:useBean id="queNoSvc" scope="page"
		class="com.queueno.model.QueNoService" />
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">預備號碼</span>
				</div>
				<div class="form-control"
					aria-label="Amount (to the nearest dollar)">
<!-- 					抓出queNo後序號碼排序 -->
					<c:forEach var="queNoVOList" items="${queNoSvc.all}" begin="0"
						end="4">
						<c:choose>
							<c:when
								test="${queNoVO.storeid==queNoVOList.storeid && queNoVO.queuetableid==queNoVOList.queuetableid }">
								<c:out value="${queNoVOList.queuenum }" />
							</c:when>
						</c:choose>
					</c:forEach>
					<%-- 						<c:forEach var="queNoVOList" items="${list}" begin="1" end="4"> --%>
					<%-- 							<div>${queNoVOList.queuelineno} 1</div> --%>
					<%-- 						</c:forEach> --%>
				</div>
				<div class="input-group-append"></div>
			</div>
		</div>
	</div>
	<div class="row reserve1"></div>
	<div class="row">
		<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
			Suscipit fuga ut odit, modi totam doloribus, eius facilis explicabo
			facere ducimus error delectus sit reprehenderit ipsam architecto
			possimus! Nesciunt, mollitia rerum!</p>

	</div>
	</div>
	<script
		src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/jquery-3.4.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/popper.min.js"></script>
	<!-- 	插入動態最新叫號 -->
	<script>
// 		var xhr = null;

// 		function showNoCall(jsonStr) {
// 			//剖析json字串,將其轉成jsob物件
// 			let noCall = JSON.parse(jsonStr);
// 			let html;
// 			if (noCall.queuenocall === undefined) {
// 				html = "<center>查無資料</center>"
// 			} else {
// 				html = "<div>" + noCall.queuenocall + "</div>";
// 			}
// 			document.getElementById("nocall").innerHTML = html;
// 		}
// 		function getNoCall() {
// 			var xhr = new XMLHttpRequest();
// 			//設定好回呼函數   
// 			xhr.onload = function() {
// 				if (xhr.status == 200) {
// 					showNoCall(xhr.responseText);
// 				} else {
// 					alert(xhr.status);
// 				}//xhr.status == 200
// 			};//onload 

// 			//建立好Get連接
// 			var url = "getNoCall.jsp?storeid="
<%-- 					+ "<%=queNoVO.getStoreid()%>" --%>
// 					+ "&queuetableid=" +
<%-- 	<%=queNoVO.getQueuetableid()%> --%>
// 		+ "&queuelineno=" +
<%-- 	<%=queNoVO.getQueuelineno()%> --%>
// 		;
// 			xhr.open("Get", url, true);
// 			//送出請求 
// 			xhr.send(null);
// 		}
// 		window.onload = getNoCall();
</script>
</body>

</html>