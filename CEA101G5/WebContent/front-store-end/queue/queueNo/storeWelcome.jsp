<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<%-- <meta http-equiv="refresh" content="300;url=<%=request.getContextPath()%>/front-store-end/queue/queueNo/queueNo.do?action=storeGetQueNo&storeid=${storeLogin.storeId}"/> --%>

<title>Insert title here</title>
</head>
<body>
<form id="send" method="post" action="queueNo.do">
	<input name="action" value="storeGetQueNo" type="hidden">
	<input name="storeid" value="${storeLogin.storeId}" type="hidden">
</form>
<script>
	send.submit();
</script>
</body>
<script src="<%=request.getContextPath()%>/front-store-end/bootstrap-4.5.3-dist/js/jquery-3.4.1.min.js"></script>
</html>